package pasa.cbentley.core.src4.memory.tests;

import pasa.cbentley.core.src4.memory.MemorySimpleCreator;
import pasa.cbentley.testing.BentleyTestCase;

public class TestMemorySimpleCreator extends BentleyTestCase {

   MemorySimpleCreator mem;

   public void setupAbstract() {
      mem = new MemorySimpleCreator(uc);
   }

   public void testStringString() {

      String[][] stringString = new String[3][];
      stringString[0] = new String[] {"a","b"};
      stringString[1] = new String[] {"aa","bv"};
      stringString[2] = new String[] {"a"};
      
      stringString = mem.increaseCapacity(stringString, 2);
      
      assertEquals(5, stringString.length);
      assertEquals(2, stringString[0].length);
      assertEquals(2, stringString[1].length);
      assertEquals(1, stringString[2].length);
      assertEquals(null, stringString[3]);
      assertEquals(null, stringString[4]);
   }

}
