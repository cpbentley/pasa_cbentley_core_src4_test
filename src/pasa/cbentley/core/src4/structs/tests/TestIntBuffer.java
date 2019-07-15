package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntBuffer;
import pasa.cbentley.core.src4.structs.IntBufferMatrix;
import pasa.cbentley.testing.BentleyTestCase;

public class TestIntBuffer extends BentleyTestCase {

   public TestIntBuffer() {
      super(false);
   }

   public void setupAbstract() {

   }

   public void testAddInts() {
      IntBuffer ib = new IntBuffer(uc);

      ib.addInt(new int[] { 1, 2, 3 });
      
      assertEquals(ib.get(0), 1);
      assertEquals(ib.get(1), 2);
      assertEquals(ib.get(2), 3);
      
   }


   public void testBasic() {
      IntBuffer ib = new IntBuffer(uc,5);

      assertEquals(0, ib.getSize());

      ib.addInt(9);

      assertEquals(1, ib.getSize());

      assertEquals(9, ib.get(0));

      ib.addInt(5, 5, 40, 60);

      assertEquals(5, ib.getSize());

      assertEquals(60, ib.getLast());

      assertEquals(60, ib.removeLast());

      assertEquals(4, ib.getSize());

      assertEquals(9, ib.removeFirst());

      assertEquals(3, ib.getSize());

      assertEquals(5, ib.get(0));
      assertEquals(5, ib.get(1));
      assertEquals(40, ib.get(2));

   }
   
   public void testRemove() {
      IntBuffer ib = new IntBuffer(uc,10);

      ib.addInt(5);
      ib.addInt(2);
      ib.addInt(5);
      ib.addInt(4);
      ib.addInt(6);
      
      assertEquals(5, ib.getSize());
      assertEquals(6, ib.getLast());
      
      ib.removeAll(6);
      
      assertEquals(4, ib.getSize());
      assertEquals(4, ib.getLast());
      ib.removeAll(5);
      
      assertEquals(2, ib.getSize());
      assertEquals(4, ib.getLast());
      assertEquals(2, ib.get(0));
   }
}
