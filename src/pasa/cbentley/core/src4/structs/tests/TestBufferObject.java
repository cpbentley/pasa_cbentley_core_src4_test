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

   public void testRemoveFirst() {
      BufferObject bo = new BufferObject(uc, 5);

      assertEquals(0, bo.getSize());

      bo.add("1");
      bo.add("2");
      bo.add("3");
      bo.add("4");

      Object removeLast = bo.removeFirst();

      assertEquals("1", removeLast);

      assertEquals(3, bo.getLength());
      
      
      assertEquals("2", bo.get(0));
      
   }

   public void testRemoveLast() {
      BufferObject bo = new BufferObject(uc, 5);

      assertEquals(0, bo.getSize());

      bo.add("1");
      bo.add("2");
      bo.add("3");
      bo.add("4");

      Object removeLast = bo.removeLast();

      assertEquals("4", removeLast);

      assertEquals(3, bo.getLength());

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

   public void testGetTrimmedArray() {
      BufferObject buffer = new BufferObject(uc, 1, 2, 3);

      buffer.add("1");
      buffer.add("2");
      buffer.add("3");

      assertEquals(3, buffer.getSize());

      Object[] ar = buffer.getClonedTrimmed();

      assertEquals("1", ar[0]);
      assertEquals("2", ar[1]);
      assertEquals("3", ar[2]);

      buffer.addLeft("0");

      ar = buffer.getClonedTrimmed();

      assertEquals("0", ar[0]);
      assertEquals("1", ar[1]);
      assertEquals("2", ar[2]);
      assertEquals("3", ar[3]);

   }

   public void testAdd1() {

      BufferObject buffer = new BufferObject(uc, 1);

      buffer.add("1");

      //#debug
      toDLog().pTest("msg", buffer, TestBufferObject.class, "testAdd1", LVL_05_FINE, false);

      assertEquals(1, buffer.getSize());

   }

   public void testAddArray() {
      BufferObject buffer = new BufferObject(uc, 1);

      buffer.add("1");

      String[] ar = new String[] { "2", "3" };

      buffer.add(ar);

      assertEquals(3, buffer.getSize());

      assertEquals("1", buffer.getFirst());
      assertEquals("3", buffer.getLast());
   }

   public void testAddBigArray() {
      BufferObject buffer = new BufferObject(uc, 1);

      buffer.add("1");

      String[] ar = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", };

      buffer.add(ar);

      assertEquals(9, buffer.getSize());

      assertEquals("1", buffer.getFirst());
      assertEquals("9", buffer.getLast());

      assertEquals("1", buffer.removeFirst());

      buffer.add(ar);

      assertEquals(16, buffer.getSize());

   }

   public void testRemoveAtIndex() {
      BufferObject buffer = new BufferObject(uc, 1);

      String[] ar = new String[] { "0", "1", "2", "3", "9", };

      buffer.add(ar);

      buffer.removeAtIndex(2);

      assertEquals("0", buffer.getFirst());
      assertEquals("1", buffer.get(1));
      assertEquals("3", buffer.get(2));
      assertEquals("9", buffer.get(3));
      assertEquals("9", buffer.getLast());

   }

   public void testRemoveAllFromIndex() {
      BufferObject buffer = new BufferObject(uc, 1);

      String[] ar = new String[] { "0", "1", "2", "3", "9", };

      buffer.add(ar);

      BufferObject rem = buffer.removeAllForIndex(2);

      assertEquals("0", buffer.getFirst());
      assertEquals("1", buffer.getLast());

      assertEquals(2, buffer.getSize());

      assertEquals("2", rem.getFirst());
      assertEquals("9", rem.getLast());
      assertEquals(3, rem.getSize());

   }

   public void testAddArray3Times() {
      BufferObject buffer = new BufferObject(uc);

      String[] ar1 = new String[] { "1", "2", "3", "4" };
      String[] ar2 = new String[] { "5", "6", "7" };
      String[] ar3 = new String[] { "5", "6", "7" };

      buffer.add(ar1);
      assertEquals(4, buffer.getSize());

      buffer.removeFirst();
      buffer.add(ar2);
      buffer.removeFirst();
      buffer.add(ar3);

      assertEquals(8, buffer.getSize());
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
