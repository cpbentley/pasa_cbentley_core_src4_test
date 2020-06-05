/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntToObjects;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIntToObjects extends TestCaseBentley {

   public TestIntToObjects() {
      super(false);
   }

   public void setupAbstract() {

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
