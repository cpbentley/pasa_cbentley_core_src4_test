package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.BufferObject;
import pasa.cbentley.testing.BentleyTestCase;

public class TestBufferObject extends BentleyTestCase {

   public TestBufferObject() {
      super(false);
   }

   public void setupAbstract() {

   }


   public void testB() {
      BufferObject bo = new BufferObject(uc, 10, 5, 2);
      
      bo.add("Str");
      
      assertEquals(1, bo.getSize());
   }
}
