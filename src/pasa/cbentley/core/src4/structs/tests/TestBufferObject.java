/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.BufferObject;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestBufferObject extends TestCaseBentley {

   public TestBufferObject() {
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
   
   public void testSwap() {
      BufferObject bo = new BufferObject(uc, 5);

      assertEquals(0, bo.getSize());

      bo.add("1");
      bo.add("2");
      bo.add("3");
      bo.add("4");
      
      assertEquals(4, bo.getSize());

      bo.swap(0, 1);
      
      assertEquals("2", bo.get(0));
      assertEquals("1", bo.get(1));
   }

   public void testInsertAt() {

      BufferObject bo = new BufferObject(uc, 5);

      assertEquals(0, bo.getSize());

      bo.add("1");
      bo.add("3");
      bo.add("4");
      bo.add("6");
      bo.add("9");

      assertEquals(5, bo.getSize());

      bo.insertAt(1, "2");

      //#debug
      toDLog().pTest("", bo, TestBufferObject.class, "testInsertAt", LVL_05_FINE, false);

      assertEquals(6, bo.getSize());

      bo.insertAt(3, "8");
      //#debug
      toDLog().pTest("", bo, TestBufferObject.class, "testInsertAt", LVL_05_FINE, false);

      bo.insertAt(0, "0");
      
      //#debug
      toDLog().pTest("", bo, TestBufferObject.class, "testInsertAt", LVL_05_FINE, false);

      bo.insertAt(5, "5");

      assertEquals(9, bo.getSize());

      
      //#debug
      toDLog().pTest("", bo, TestBufferObject.class, "testInsertAt", LVL_05_FINE, false);

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
