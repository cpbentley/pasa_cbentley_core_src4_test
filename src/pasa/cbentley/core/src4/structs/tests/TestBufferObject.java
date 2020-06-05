/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.BufferObject;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestBufferObject extends TestCaseBentley {

   public TestBufferObject() {
      super(false);
   }

   public void setupAbstract() {

   }

   public void testB() {
      BufferObject bo = new BufferObject(uc, 10, 5, 2);

      bo.add("Str");

      assertEquals(1, bo.getSize());

      bo.add("Hi");
      assertEquals(2, bo.getSize());
   }

   public void testGetIndex() {
      int bufferFront = 2;
      BufferObject bo = new BufferObject(uc, 10, 5, bufferFront);

      bo.add("Str1");
      bo.add("Str2");

      assertEquals(2, bo.getObjectIndex("Str1"));
      assertEquals(3, bo.getObjectIndex("Str2"));
      assertEquals(-1, bo.getObjectIndex("Str3"));
   }
   
   public void testGetLast() {
      int bufferFront = 2;
      BufferObject bo = new BufferObject(uc, 10, 5, bufferFront);

      assertEquals(null, bo.getLast());

      bo.add("Str1");
      assertEquals("Str1", bo.getLast());

      bo.add("Str2");
      assertEquals("Str2", bo.getLast());

      bo.add("Str3");
      assertEquals("Str3", bo.getLast());

      bo.add("Str4");
      assertEquals("Str4", bo.getLast());
      
      
   }
   
   public void testGetFirst() {
      int bufferFront = 2;
      BufferObject bo = new BufferObject(uc, 10, 5, bufferFront);

      assertEquals(null, bo.getFirst());

      bo.add("Str1");
      assertEquals("Str1", bo.getFirst());

      bo.add("Str2");
      assertEquals("Str1", bo.getFirst());

      
      bo.addLeft("Str3");
      
      assertEquals("Str3", bo.getFirst());
      
      assertEquals("Str2", bo.getLast());

      
   }
}
