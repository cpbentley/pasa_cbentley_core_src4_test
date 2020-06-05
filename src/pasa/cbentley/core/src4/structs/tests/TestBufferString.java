/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.BufferString;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestBufferString extends TestCaseBentley {

   public TestBufferString() {
      super(false);
   }

   public void setupAbstract() {

   }

   
   public void testBufferStringEmtpy() {
      BufferString bs = new BufferString(uc);
      
      assertEquals(0, bs.getSize());
      
      assertEquals(-1, bs.getFirstIndexEqual("two"));
      assertEquals(-1, bs.getFirstIndexSimilar("two"));
      
      bs.removeAll("two");
   }
   
   public void testBufferString1() {
      BufferString bs = new BufferString(uc);
      
      bs.addStr("one");
      assertEquals(1, bs.getSize());
      
      assertEquals(0, bs.getFirstIndexEqual("one"));
      assertEquals(0, bs.getFirstIndexSimilar("one"));

      assertEquals(-1, bs.getFirstIndexEqual("two"));
      assertEquals(-1, bs.getFirstIndexSimilar("two"));
      
      
      BufferString bsCloned = bs.cloneMe();
      
      assertEquals(0,bs.removeAll("two"));
      assertEquals(1,bs.removeAll("one"));
      assertEquals(0, bs.getSize());
      
      assertEquals("one", bs.getLast());
      assertEquals("one", bs.getLast());
      
      bsCloned.getLast();
      
   }
}
