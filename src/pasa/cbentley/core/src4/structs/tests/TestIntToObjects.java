package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntToObjects;
import pasa.cbentley.testing.BentleyTestCase;

public class TestIntToObjects extends BentleyTestCase {

   public TestIntToObjects() {
      super(false);
   }

   public void setUpMord() {

   }
   public void testDelete() {
      IntToObjects ito = new IntToObjects(uc,0);
      
      ito.add("1");
      ito.add("2");
      
      assertEquals(2,  ito.getLength());
      assertEquals("1",  ito.getObjectAtIndex(0));
      assertEquals("2",  ito.getObjectAtIndex(1));
      
      ito.delete(0, 1);
      
      assertEquals(1,  ito.getLength());
      assertEquals("2",  ito.getObjectAtIndex(0));
      assertEquals(null,  ito.getObjectAtIndex(1));
      assertEquals(null,  ito.getObjectAtIndex(2));
    
   }
   
   
   public void testDeleteRef() {
      IntToObjects ito = new IntToObjects(uc,1);
      
      Integer i = new Integer(4);
      
      ito.add(i);
      
      ito.removeRef(i);
      
      assertEquals(0, ito.nextempty);
   }
}
