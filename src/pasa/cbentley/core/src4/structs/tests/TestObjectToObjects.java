package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.ObjectToObjects;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestObjectToObjects extends TestCaseBentley {

   public TestObjectToObjects() {
   }

   public void setupAbstract() {

   }

   public void testSimple() {

      ObjectToObjects oto = new ObjectToObjects(uc);

      oto.add("key1", "value1");
      oto.add("key2", "value2");
      oto.add("key3", "value3");

      assertEquals(3, oto.getSize());

      assertEquals("value1", oto.getValue("key1"));
      assertEquals("value2", oto.getValue("key2"));
      assertEquals("value3", oto.getValue("key3"));
   }

   public void testMulti() {

      ObjectToObjects oto = new ObjectToObjects(uc,true);

      oto.add("key1", "value1");
      oto.add("key2", "value2");
      oto.add("key3", "value3");

      assertEquals(3, oto.getSize());

      assertEquals("value1", oto.getValue("key1"));
      assertEquals("value2", oto.getValue("key2"));
      assertEquals("value3", oto.getValue("key3"));
      
      oto.add("key1", "value11");
      assertEquals(3, oto.getSize());
      assertEquals("value1", oto.getValues("key1")[0]);
      assertEquals("value11", oto.getValues("key1")[1]);
      
      
      oto.add("key1", "value12");
      assertEquals(3, oto.getSize());
      
      assertEquals("value1", oto.getValues("key1")[0]);
      assertEquals("value11", oto.getValues("key1")[1]);
      assertEquals("value12", oto.getValues("key1")[2]);
      
      
   }
}
