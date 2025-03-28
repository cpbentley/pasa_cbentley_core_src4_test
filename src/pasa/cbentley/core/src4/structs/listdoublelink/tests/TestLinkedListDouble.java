/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.structs.listdoublelink.tests;

import pasa.cbentley.core.src4.structs.listdoublelink.LinkedListDouble;
import pasa.cbentley.core.src4.structs.listdoublelink.ListElement;
import pasa.cbentley.core.src4.structs.listdoublelink.ListElementHolder;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestLinkedListDouble extends TestCaseBentley {

   public TestLinkedListDouble() {
   }

   public void setupAbstract() {

   }

   public void testDoubleAdd() {
      LinkedListDouble lld = new LinkedListDouble(uc);
      ListElement le1 = new ListElement(lld);

      le1.addToList();
      le1.addToList();

      assertEquals(1, lld.getNumElements());
      assertEquals(le1, lld.getHead());
      assertEquals(le1, lld.getTail());

   }

   public void testGetArray1() {
      LinkedListDouble lld = new LinkedListDouble(uc);

      lld.addFreeHolder("1");

      Object[] ar = lld.getArray();

      assertEquals(1, ar.length);

      assertEquals(ar[0], "1");
   }

   public void testGetArray() {
      LinkedListDouble lld = new LinkedListDouble(uc);

      lld.addFreeHolder("1");
      lld.addFreeHolder("2");
      lld.addFreeHolder("3");
      lld.addFreeHolder("4");

      Object[] ar = lld.getArray();

      assertEquals(4, ar.length);

      assertEquals(ar[0], "1");
      assertEquals(ar[1], "2");
      assertEquals(ar[2], "3");
      assertEquals(ar[3], "4");
   }

   public void testList() {

      LinkedListDouble lld = new LinkedListDouble(uc);
      assertEquals(true, lld.isEmpty());
      ListElement le1 = new ListElement(lld);
      assertEquals(true, lld.isEmpty());
      ListElement le2 = new ListElement(lld);
      assertEquals(true, lld.isEmpty());
      ListElement le3 = new ListElement(lld);
      assertEquals(true, lld.isEmpty());

      lld.toString();

      assertEquals(false, le1.isListed());
      assertEquals(false, le1.isHead());
      assertEquals(false, le1.isTail());

      assertEquals(0, lld.getNumElements());
      le1.addToList();
      assertEquals(1, lld.getNumElements());

      assertEquals(true, le1.isListed());
      assertEquals(true, le1.isHead());
      assertEquals(true, le1.isTail());

      //add it a second time
      le1.addToList();
      assertEquals(1, lld.getNumElements());

      le2.addToList();
      assertEquals(2, lld.getNumElements());
      assertEquals(true, le1.isListed());
      assertEquals(true, le1.isHead());
      assertEquals(false, le1.isTail());

      assertEquals(true, le2.isListed());
      assertEquals(false, le2.isHead());
      assertEquals(true, le2.isTail());

      assertEquals(null, le1.getPrev());
      assertEquals(le2, le1.getNext());
      assertEquals(le1, le2.getPrev());
      assertEquals(null, le2.getNext());

      assertEquals(false, lld.isEmpty());

      le2.addToList();
      assertEquals(2, lld.getNumElements());

      le3.addToList();
      assertEquals(3, lld.getNumElements());

      assertEquals(null, le1.getPrev());
      assertEquals(le2, le1.getNext());
      assertEquals(le1, le2.getPrev());
      assertEquals(le3, le2.getNext());
      assertEquals(le2, le3.getPrev());
      assertEquals(null, le3.getNext());

      lld.toString();

      le2.removeFromList();
      assertEquals(2, lld.getNumElements());
      assertEquals(false, le2.isListed());

      assertEquals(null, le1.getPrev());
      assertEquals(le3, le1.getNext());
      assertEquals(le1, le3.getPrev());
      assertEquals(null, le3.getNext());

      le1.removeFromList();
      assertEquals(1, lld.getNumElements());

      assertEquals(false, le1.isListed());

      assertEquals(null, le3.getPrev());
      assertEquals(null, le3.getNext());
      assertEquals(true, le3.isListed());
      assertEquals(true, le3.isHead());
      assertEquals(true, le3.isTail());

      le3.removeFromList();
      assertEquals(0, lld.getNumElements());

      assertEquals(false, le3.isListed());
      assertEquals(false, le3.isHead());
      assertEquals(false, le3.isTail());

      assertEquals(true, lld.isEmpty());

      le1.addToList();
      le3.addToList();
      le2.addToList();
      assertEquals(3, lld.getNumElements());
      assertEquals(true, le2.isTail());

      //

      //add even if already there
      le3.addToList();
      assertEquals(true, le2.isTail());
      assertEquals(3, lld.getNumElements());

      assertEquals(null, le1.getPrev());
      assertEquals(le3, le1.getNext());
      assertEquals(le1, le3.getPrev());
      assertEquals(le2, le3.getNext());
      assertEquals(le3, le2.getPrev());
      assertEquals(null, le2.getNext());

      le2.removeFromList();

      assertEquals(true, le1.isHead());
      assertEquals(true, le3.isTail());
      assertEquals(null, le1.getPrev());
      assertEquals(le3, le1.getNext());
      assertEquals(le1, le3.getPrev());
      assertEquals(null, le3.getNext());

   }

   public void testMoveHeadToTail() {

      LinkedListDouble lld = new LinkedListDouble(uc);
      ListElement le1 = new ListElement(lld);
      ListElement le2 = new ListElement(lld);
      ListElement le3 = new ListElement(lld);
      ListElement le4 = new ListElement(lld);
      assertEquals(true, lld.isEmpty());

      assertEquals(0, lld.getNumElements());

      lld.moveHeadToTail();

      le1.addToList();
      assertEquals(1, lld.getNumElements());
      assertEquals(le1, lld.getHead());
      assertEquals(le1, lld.getTail());

      lld.moveHeadToTail();

      assertEquals(le1, lld.getHead());
      assertEquals(le1, lld.getTail());

      le2.addToList();

      assertEquals(le1, lld.getHead());
      assertEquals(le2, le1.getNext());
      assertEquals(le2, lld.getTail());
      assertEquals(le1, le2.getPrev());

      lld.moveHeadToTail();

      assertEquals(le2, lld.getHead());
      assertEquals(le1, lld.getTail());

      lld.moveHeadToTail();
      assertEquals(le1, lld.getHead());
      assertEquals(le2, lld.getTail());

      le3.addToList();

      assertEquals(le1, lld.getHead());
      assertEquals(le3, lld.getTail());

      lld.moveHeadToTail();

      assertEquals(le2, lld.getHead());
      assertEquals(le1, lld.getTail());

      lld.moveHeadToTail();

      assertEquals(le3, lld.getHead());
      assertEquals(le2, lld.getTail());

   }

   public void testRemoveObjectRef() {
      LinkedListDouble lld = new LinkedListDouble(uc);

      Integer i1 = 1;
      Integer i3 = 2;
      Integer i2 = 3;

      lld.addFreeHolder(i1);
      lld.addFreeHolder(i2);
      lld.addFreeHolder(i3);
      lld.addFreeHolder(i2);
      lld.addFreeHolder(i1);

      assertEquals(i1, ((ListElementHolder) lld.getHead()).getObject());

      assertEquals(5, lld.getNumElements());

      lld.removeObjectRef(i3);

      assertEquals(4, lld.getNumElements());

      lld.removeObjectRef(i1);

      assertEquals(2, lld.getNumElements());

   }
}
