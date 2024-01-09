/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestStatorWriter extends TestCaseBentley {

   public void setupAbstract() {
      //what about the logging ?
   }

   public void testWriterEmpty() {

      StatorWriter sw = new StatorWriter(uc);
      assertEquals(null, sw.getData());
      byte[] data = sw.serialize();
      
      
   }
   
   
   public void testWriterOnlyKeys() {
      StatorWriter sw = new StatorWriter(uc);

      sw.getKeyValuePairs().put("color", "black");
      sw.getKeyValuePairs().putInt("saturation", 100);
      
      byte[] data = sw.serialize();
      
      StatorReader reader = new StatorReader(uc);
      reader.importFrom(data);
      
      assertEquals("black", reader.getKeyValuePairs().get("color", ""));
      assertEquals(100, reader.getKeyValuePairs().getInt("saturation", 0));
   }
   
   public void testWriter() {

      StatorWriter sw = new StatorWriter(uc);

      assertEquals(null, sw.getData());
      
      TestStatorable2 ts2 = new TestStatorable2(tc);
      ts2.setName("ts2");
      TestStatorable1 ts1 = new TestStatorable1(tc);
      ts1.setIntColor(464);
      ts1.setStringColor("blue");
      
      ts2.setState1(ts1);
      
      sw.stateWriteOf(ts2);
      
      
      assertEquals(2, sw.getNumWrittenObject());
      
      byte[] data = sw.serialize();
      
      assertEquals(data.length, 52);
      
      StatorReader reader = new StatorReader(uc);
      reader.addFactory(new TestStatorableFactory(tc));
      reader.importFrom(data);
      
      assertEquals(2, reader.getNumObjects());
      
      TestStatorable2 ts2Read = (TestStatorable2) reader.createObject(TestStatorable2.class);
      
      assertNotNull(ts2Read);
      assertNotNull(ts2Read.getName());
      assertNotNull(ts2Read.getState1());
      
      assertEquals(ts2.getName(), ts2Read.getName());
      assertNotNull(ts2Read.getState1());
      assertEquals(ts1.getIntColor(), ts2Read.getState1().getIntColor());
      assertEquals(ts1.getStringColor(), ts2Read.getState1().getStringColor());
   }

}
