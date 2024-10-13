/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.interfaces.IPrefsWriter;
import pasa.cbentley.core.src4.io.BADataOS;
import pasa.cbentley.core.src4.stator.ITechStator;
import pasa.cbentley.core.src4.stator.Stator;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestStatorWriter extends TestCaseStator {


   public void testWriterEmpty() {

      StatorWriter writer = new StatorWriter(stator, TYPE_0_MASTER);

      BADataOS dataWriter = writer.getWriter();

      IPrefsWriter keyValuePairs = writer.getPrefs();

      assertEquals(0, writer.getNumWrittenObject());
      assertEquals(TYPE_0_MASTER, writer.getType());

      byte[] data = writer.serialize();

   }

   public void testIsContinue() {

      StatorWriter writer = stator.getWriter(TYPE_0_MASTER);

      Statorable2ForTests red = new Statorable2ForTests(tsc);
      red.setName("red");

      Statorable1ForTests blue = new Statorable1ForTests(tsc);
      blue.setStringColor("blue");

      writer.dataWriterToStatorable(blue);
      writer.dataWriterToStatorable(red);

      //write it again.. but it won't affect the stator.. just writing the object ID
      writer.dataWriterToStatorable(blue);
      
      assertEquals(2, writer.getNumWrittenObject());

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_0_MASTER);

      Statorable1ForTests blueFuture = new Statorable1ForTests(tsc);
      
      reader.readerToStatorable(blueFuture);
      

      assertEquals("blue", blueFuture.getStringColor());

      Statorable2ForTests redFuture = new Statorable2ForTests(tsc);
      reader.readerToStatorable(redFuture);

      assertEquals("red", redFuture.getName());

      assertEquals(true, reader.hasMore());

      reader.readerToStatorable(blueFuture);

      assertEquals(false, reader.hasMore());
   }

   public void testWriteTwice() {

      StatorWriter writer = stator.getWriter(TYPE_0_MASTER);

      Statorable1ForTests blue = new Statorable1ForTests(tsc);
      blue.setStringColor("blue");

      writer.dataWriterToStatorable(blue);
      writer.dataWriterToStatorable(blue);
      
      assertEquals(1, writer.getNumWrittenObject());

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_0_MASTER);

      Statorable1ForTests blueFuture = new Statorable1ForTests(tsc);
      
      reader.readerToStatorable(blueFuture);
      assertEquals("blue", blueFuture.getStringColor());
      assertEquals(true, reader.hasMore());
      reader.readerToStatorable(blueFuture);
      assertEquals(false, reader.hasMore());
   }

   
   public void testWriter() {

      StatorWriter writer = stator.getWriter(TYPE_0_MASTER);

      Statorable2ForTests ts2 = new Statorable2ForTests(tsc);
      ts2.setName("red");

      Statorable1ForTests ts1 = new Statorable1ForTests(tsc);
      ts1.setIntColor(464);
      ts1.setStringColor("blue");

      ts2.setState1(ts1);

      writer.dataWriterToStatorable(ts2);

      assertEquals(2, writer.getNumWrittenObject());

      byte[] data = stator.serializeAll();
      Stator statorFutur = createNewStator();
      statorFutur.importFrom(data);

      StatorReader reader = statorFutur.getReader(TYPE_0_MASTER);

      assertEquals(2, reader.getNumObjects());

      Statorable2ForTests ts2Read = (Statorable2ForTests) reader.dataReadObject(tsc);

      assertNotNull(ts2Read);
      assertNotNull(ts2Read.getName());
      assertNotNull(ts2Read.getState1());

      assertEquals(ts2.getName(), ts2Read.getName());
      assertNotNull(ts2Read.getState1());
      assertEquals(ts1.getIntColor(), ts2Read.getState1().getIntColor());
      assertEquals(ts1.getStringColor(), ts2Read.getState1().getStringColor());
   }

}
