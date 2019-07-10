package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntBuffer;
import pasa.cbentley.core.src4.structs.IntBufferMatrix;
import pasa.cbentley.testing.BentleyTestCase;

public class TestIntBufferMatrix extends BentleyTestCase {

   public TestIntBufferMatrix() {
      super(false);
   }

   public void setUpMord() {

   }

   public void testIntBufferMatrix() {

      IntBufferMatrix ibm = new IntBufferMatrix(uc);

      assertEquals(0, ibm.getSize());

      IntBuffer ib = new IntBuffer(uc, 5);
      ib.addInt(1, 2, 5, 10);

      ibm.addCopyRow(ib);

      IntBuffer b1 = ibm.getRow(0);

      assertEquals(false, b1 == ib);

      assertEquals(b1.get(0), 1);
      assertEquals(b1.get(1), 2);
      assertEquals(b1.get(2), 5);
      assertEquals(b1.get(3), 10);
      assertEquals(b1.getSize(), 4);

   }

}
