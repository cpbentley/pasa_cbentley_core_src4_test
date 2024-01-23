package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.interfaces.IPrefsReader;
import pasa.cbentley.core.src4.interfaces.IPrefsWriter;
import pasa.cbentley.core.src4.stator.ITechStator;
import pasa.cbentley.core.src4.stator.Stator;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestStator extends TestCaseStator  {


   public void testWriterOnlyKeys() {

      IPrefsWriter pw = stator.getPrefsWriter();

      pw.put("color", "black");
      pw.putInt("saturation", 100);

      byte[] data = stator.serializePrefs();

      Stator statorFuture = new Stator(uc);

      statorFuture.importPrefsFrom(data);

      IPrefsReader prefsFuture = statorFuture.getPrefsReader();

      assertEquals("black", prefsFuture.get("color", ""));
      assertEquals(100, prefsFuture.getInt("saturation", 0));
   }

   public void testSerializeAllPrefAlone() {

      IPrefsWriter pw = stator.getPrefsWriter();

      pw.put("color", "black");
      pw.putInt("saturation", 100);

      byte[] data = stator.serializeAll();
      Stator statorFuture = new Stator(uc);
      statorFuture.importFrom(data);

      IPrefsReader prefsFuture = statorFuture.getPrefsReader();

      assertEquals("black", prefsFuture.get("color", ""));
      assertEquals(100, prefsFuture.getInt("saturation", 0));
   }

   public void testSerializeAll1Writer() {

      assertEquals(false, stator.hasReader(TYPE_0_MASTER));
      assertEquals(false, stator.hasReader(TYPE_1_VIEW));
      assertEquals(false, stator.hasReader(TYPE_2_MODEL));
      assertEquals(false, stator.hasReader(TYPE_3_CTX));

      assertEquals(false, stator.hasWriter(TYPE_0_MASTER));
      assertEquals(false, stator.hasWriter(TYPE_1_VIEW));
      assertEquals(false, stator.hasWriter(TYPE_2_MODEL));
      assertEquals(false, stator.hasWriter(TYPE_3_CTX));

      StatorWriter pw = stator.getWriter(TYPE_0_MASTER);

      assertEquals(true, stator.hasWriter(TYPE_0_MASTER));

      pw.getWriter().writeInt(452);
      pw.getWriter().writeInt(6);
      pw.getWriter().writeString("hello");

      byte[] data = stator.serializeAll();
      Stator statorFuture = new Stator(uc);
      statorFuture.importFrom(data);

      assertEquals(true, statorFuture.hasReader(TYPE_0_MASTER));
      assertEquals(false, statorFuture.hasReader(TYPE_1_VIEW));
      assertEquals(false, statorFuture.hasReader(TYPE_2_MODEL));
      assertEquals(false, statorFuture.hasReader(TYPE_3_CTX));

      assertEquals(false, statorFuture.hasWriter(TYPE_0_MASTER));
      assertEquals(false, statorFuture.hasWriter(TYPE_1_VIEW));
      assertEquals(false, statorFuture.hasWriter(TYPE_2_MODEL));
      assertEquals(false, statorFuture.hasWriter(TYPE_3_CTX));

      StatorReader reader = statorFuture.getReader(TYPE_0_MASTER);

      assertEquals(true, reader.hasMore());
      assertEquals(452, reader.getReader().readInt());
      assertEquals(6, reader.getReader().readInt());
      assertEquals("hello", reader.getReader().readString());

      assertEquals(false, reader.hasMore());
   }

   public void testSerializeAll2Writer() {

      StatorWriter pw0 = stator.getWriter(TYPE_0_MASTER);
      StatorWriter pw2 = stator.getWriter(TYPE_2_MODEL);

      assertEquals(true, stator.hasWriter(TYPE_0_MASTER));
      assertEquals(true, stator.hasWriter(TYPE_2_MODEL));

      pw0.getWriter().writeInt(452);
      pw2.getWriter().writeInt(6);

      byte[] data = stator.serializeAll();
      Stator statorFuture = new Stator(uc);
      statorFuture.importFrom(data);

      assertEquals(true, statorFuture.hasReader(TYPE_0_MASTER));
      assertEquals(false, statorFuture.hasReader(TYPE_1_VIEW));
      assertEquals(true, statorFuture.hasReader(TYPE_2_MODEL));
      assertEquals(false, statorFuture.hasReader(TYPE_3_CTX));

      StatorReader reader0 = statorFuture.getReader(TYPE_0_MASTER);

      assertEquals(true, reader0.hasMore());
      assertEquals(452, reader0.getReader().readInt());
      assertEquals(false, reader0.hasMore());

      StatorReader reader1 = statorFuture.getReader(TYPE_2_MODEL);

      assertEquals(true, reader1.hasMore());
      assertEquals(6, reader1.getReader().readInt());
      assertEquals(false, reader1.hasMore());
   }

   public void testSerializeAll() {

      stator.getPrefsWriter().put("key", "hello");
      StatorWriter pw0 = stator.getWriter(TYPE_0_MASTER);
      StatorWriter pw1 = stator.getWriter(TYPE_1_VIEW);
      StatorWriter pw2 = stator.getWriter(TYPE_2_MODEL);
      StatorWriter pw3 = stator.getWriter(TYPE_3_CTX);

      pw0.getWriter().writeInt(10);
      pw1.getWriter().writeInt(11);
      pw2.getWriter().writeInt(12);
      pw3.getWriter().writeInt(13);

      byte[] data = stator.serializeAll();
      Stator statorFuture = new Stator(uc);
      statorFuture.importFrom(data);

      assertEquals(true, statorFuture.hasReader(TYPE_0_MASTER));
      assertEquals(true, statorFuture.hasReader(TYPE_1_VIEW));
      assertEquals(true, statorFuture.hasReader(TYPE_2_MODEL));
      assertEquals(true, statorFuture.hasReader(TYPE_3_CTX));

      assertEquals("hello", statorFuture.getPrefsReader().get("key", ""));

      assertEquals(10, statorFuture.getReader(TYPE_0_MASTER).readInt());
      assertEquals(11, statorFuture.getReader(TYPE_1_VIEW).readInt());
      assertEquals(12, statorFuture.getReader(TYPE_2_MODEL).readInt());
      assertEquals(13, statorFuture.getReader(TYPE_3_CTX).readInt());

   }



   public void testObject1() {

      StatorWriter writer = stator.getWriter(TYPE_0_MASTER);

      Statorable2ForTests red = new Statorable2ForTests(tsc);
      red.setName("red");

      writer.writerToStatorable(red);

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_0_MASTER);

      assertEquals(true, reader.hasMore());

      Statorable2ForTests redFuture = new Statorable2ForTests(tsc);

      reader.readerToStatorable(redFuture);

      assertEquals("red", redFuture.getName());

      assertEquals(false, reader.hasMore());
   }

   public void testObject11() {

      StatorWriter writer = stator.getWriter(TYPE_0_MASTER);

      Statorable2ForTests red = new Statorable2ForTests(tsc);
      red.setName("red");

      writer.writerToStatorable(red);

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_0_MASTER);

      assertEquals(true, reader.hasMore());
      Statorable2ForTests redFuture = new Statorable2ForTests(tsc);
      reader.readerToStatorable(redFuture);

      assertEquals("red", redFuture.getName());

      assertEquals(false, reader.hasMore());
   }

   public void testObject2() {

      StatorWriter writer = stator.getWriter(TYPE_0_MASTER);

      Statorable2ForTests red = new Statorable2ForTests(tsc);
      red.setName("red");

      writer.writerToStatorable(red);

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_0_MASTER);

      assertEquals(true, reader.hasMore());
      Statorable2ForTests redFuture = (Statorable2ForTests) reader.readObject(tsc);

      assertEquals("red", redFuture.getName());
      assertEquals(false, reader.hasMore());
   }

   public void testFancyStuff() {

      StatorWriter writer = stator.getWriter(TYPE_0_MASTER);

      Statorable2ForTests red = new Statorable2ForTests(tsc);
      FancyStuffB fb = new FancyStuffB(tsc);
      fb.setFancyString("FancyString");

      red.setFancyStuff(fb);

      red.setName("red");

      writer.writerToStatorable(red);

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_0_MASTER);

      assertEquals(true, reader.hasMore());
      Statorable2ForTests redFuture = (Statorable2ForTests) reader.readObject(tsc);
      assertEquals(false, reader.hasMore());
      assertEquals("red", redFuture.getName());

      FancyStuffB fbFuture = (FancyStuffB) redFuture.getFancyStuff();
      assertEquals("FancyString", fbFuture.getFancyString());
   }

   public void testFancyStuffSetOver() {

      StatorWriter writer = stator.getWriter(TYPE_0_MASTER);

      Statorable2ForTests red = new Statorable2ForTests(tsc);
      FancyStuffB fb = new FancyStuffB(tsc);
      fb.setFancyString("FancyStringRed");
      red.setFancyStuff(fb);
      red.setName("red");

      Statorable2ForTests green = new Statorable2ForTests(tsc);
      FancyStuffB fbGreen = new FancyStuffB(tsc);
      fbGreen.setFancyString("FancyStringGreen");
      green.setFancyStuff(fbGreen);

      FancyStuffB fbGreenFuture = (FancyStuffB) green.getFancyStuff();
      assertEquals("FancyStringGreen", fbGreenFuture.getFancyString());

      writer.writerToStatorable(red);

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      //test over existing one
      Stator statorFuturGreen = createNewStator();
      statorFuturGreen.importFrom(data);
      StatorReader readerGreen = statorFuturGreen.getReader(TYPE_0_MASTER);

      //overwrite green with state of red 
      readerGreen.readerToStatorable(green);

      assertEquals("red", green.getName());
      fbGreenFuture = (FancyStuffB) green.getFancyStuff();

      assertEquals("FancyStringRed", fbGreenFuture.getFancyString());

   }

   public void testLevel2_New() {

      Level2Statorable lvl2 = new Level2Statorable(tsc);

      lvl2.populateBufferF();

      assertEquals(null, lvl2.getS1());
      assertEquals(null, lvl2.getS2());

      StatorWriter sw = stator.getWriter(TYPE_2_MODEL);
      sw.writerToStatorable(lvl2);

      assertEquals(4, sw.getNumWrittenObject());

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_2_MODEL);

      Level2Statorable lvl2Future = (Level2Statorable) reader.readObject();

      assertEquals(null, lvl2Future.getS1());
      assertEquals(null, lvl2Future.getS2());
      assertEquals(7, lvl2Future.getBuffer().getSize());

      assertEquals("f1a", ((IFancyStuffTest) lvl2Future.getBuffer().get(0)).getFancyString());
      assertEquals("f2a", ((IFancyStuffTest) lvl2Future.getBuffer().get(1)).getFancyString());
      assertEquals("f3b", ((IFancyStuffTest) lvl2Future.getBuffer().get(2)).getFancyString());
      assertEquals("f3b", ((IFancyStuffTest) lvl2Future.getBuffer().get(3)).getFancyString());
      assertEquals("f3b", ((IFancyStuffTest) lvl2Future.getBuffer().get(4)).getFancyString());
      assertEquals("f2a", ((IFancyStuffTest) lvl2Future.getBuffer().get(5)).getFancyString());
      assertEquals("f1a", ((IFancyStuffTest) lvl2Future.getBuffer().get(6)).getFancyString());

   }

   public void testLevel2_Pop() {

      Level2Statorable lvl2 = new Level2Statorable(tsc);

      lvl2.populateBufferF();

      assertEquals(null, lvl2.getS1());
      assertEquals(null, lvl2.getS2());

      StatorWriter sw = stator.getWriter(TYPE_2_MODEL);
      sw.writerToStatorable(lvl2);

      assertEquals(4, sw.getNumWrittenObject());

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_2_MODEL);

      Level2Statorable existing = new Level2Statorable(tsc);
      existing.populateBufferG();
      
      assertEquals("g1a", ((IFancyStuffTest) existing.getBuffer().get(0)).getFancyString());
      assertEquals("g2a", ((IFancyStuffTest) existing.getBuffer().get(1)).getFancyString());
      assertEquals("g3b", ((IFancyStuffTest) existing.getBuffer().get(2)).getFancyString());
      assertEquals("g3b", ((IFancyStuffTest) existing.getBuffer().get(3)).getFancyString());
      assertEquals("g3b", ((IFancyStuffTest) existing.getBuffer().get(4)).getFancyString());
      assertEquals("g2a", ((IFancyStuffTest) existing.getBuffer().get(5)).getFancyString());
      assertEquals("g1a", ((IFancyStuffTest) existing.getBuffer().get(6)).getFancyString());

      
      Level2Statorable fromExisting = (Level2Statorable) reader.readObject(existing);
      
      assertEquals(true, existing == fromExisting);
      

      assertEquals(null, existing.getS1());
      assertEquals(null, existing.getS2());
      assertEquals(7, existing.getBuffer().getSize());

      assertEquals("f1a", ((IFancyStuffTest) existing.getBuffer().get(0)).getFancyString());
      assertEquals("f2a", ((IFancyStuffTest) existing.getBuffer().get(1)).getFancyString());
      assertEquals("f3b", ((IFancyStuffTest) existing.getBuffer().get(2)).getFancyString());
      assertEquals("f3b", ((IFancyStuffTest) existing.getBuffer().get(3)).getFancyString());
      assertEquals("f3b", ((IFancyStuffTest) existing.getBuffer().get(4)).getFancyString());
      assertEquals("f2a", ((IFancyStuffTest) existing.getBuffer().get(5)).getFancyString());
      assertEquals("f1a", ((IFancyStuffTest) existing.getBuffer().get(6)).getFancyString());

   }
}
