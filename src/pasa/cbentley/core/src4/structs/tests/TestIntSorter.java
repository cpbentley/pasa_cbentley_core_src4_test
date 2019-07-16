/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntArray;
import pasa.cbentley.core.src4.structs.IntSorter;
import pasa.cbentley.testing.BentleyTestCase;

public class TestIntSorter extends BentleyTestCase {

   public TestIntSorter() {
      super(false);
   }

   public void setupAbstract() {

   }

   public void testSimpleAppendSuffixUnique() {
      IntSorter sorter = new IntSorter(uc, IntSorter.SORT_ORDER_0_NONE_APPEND_SUFFIX, true);
      assertEquals(IntSorter.SORT_ORDER_0_NONE_APPEND_SUFFIX, sorter.getType());
      IntArray ia = populateArrayWith(sorter);
      int[] copy = ia.getTrimmedCopy();
      assertEquals(5, copy[0]);
      assertEquals(3, copy[1]);
      assertEquals(6, copy[2]);
      assertEquals(7, copy[3]);
      assertEquals(1, copy[4]);
      assertEquals(0, copy[5]);
      assertEquals(6, copy.length);
   }

   public void testSimpleAppendPrefixUnique() {
      IntSorter sorter = new IntSorter(uc, IntSorter.SORT_ORDER_1_NONE_APPEND_PREFIX, true);
      assertEquals(IntSorter.SORT_ORDER_1_NONE_APPEND_PREFIX, sorter.getType());
      IntArray ia = populateArrayWith(sorter);
      int[] copy = ia.getTrimmedCopy();
      assertEquals(5, copy[5]);
      assertEquals(3, copy[4]);
      assertEquals(6, copy[3]);
      assertEquals(7, copy[2]);
      assertEquals(1, copy[1]);
      assertEquals(0, copy[0]);
      assertEquals(6, copy.length);
   }

   public void testSimpleSortedASCUnique() {
      IntSorter sorter = new IntSorter(uc, IntSorter.SORT_ORDER_2_ASC, true);
      assertEquals(IntSorter.SORT_ORDER_2_ASC, sorter.getType());
      IntArray ia = populateArrayWith(sorter);
      int[] copy = ia.getTrimmedCopy();
      assertEquals(0, copy[0]);
      assertEquals(1, copy[1]);
      assertEquals(3, copy[2]);
      assertEquals(5, copy[3]);
      assertEquals(6, copy[4]);
      assertEquals(7, copy[5]);
      assertEquals(6, copy.length);
   }

   public void testSimpleSortedDESCUnique() {
      IntSorter sorter = new IntSorter(uc, IntSorter.SORT_ORDER_3_DESC, true);
      assertEquals(IntSorter.SORT_ORDER_3_DESC, sorter.getType());
      IntArray ia = populateArrayWith(sorter);
      int[] copy = ia.getTrimmedCopy();
      assertEquals(0, copy[5]);
      assertEquals(1, copy[4]);
      assertEquals(3, copy[3]);
      assertEquals(5, copy[2]);
      assertEquals(6, copy[1]);
      assertEquals(7, copy[0]);
      assertEquals(6, copy.length);
   }

   public void testSimpleAppendSuffixNonUnique() {
      IntSorter sorter = new IntSorter(uc, IntSorter.SORT_ORDER_0_NONE_APPEND_SUFFIX, false);
      assertEquals(IntSorter.SORT_ORDER_0_NONE_APPEND_SUFFIX, sorter.getType());
      IntArray ia = populateArrayWith(sorter);
      int[] copy = ia.getTrimmedCopy();
      assertEquals(5, copy[0]);
      assertEquals(3, copy[1]);
      assertEquals(5, copy[2]);
      assertEquals(6, copy[3]);
      assertEquals(7, copy[4]);
      assertEquals(1, copy[5]);
      assertEquals(0, copy[6]);
      assertEquals(7, copy.length);
   }

   public void testSimpleAppendPrefixNonUnique() {
      IntSorter sorter = new IntSorter(uc, IntSorter.SORT_ORDER_1_NONE_APPEND_PREFIX, false);
      assertEquals(IntSorter.SORT_ORDER_1_NONE_APPEND_PREFIX, sorter.getType());
      IntArray ia = populateArrayWith(sorter);
      int[] copy = ia.getTrimmedCopy();
      assertEquals(5, copy[6]);
      assertEquals(3, copy[5]);
      assertEquals(5, copy[4]);
      assertEquals(6, copy[3]);
      assertEquals(7, copy[2]);
      assertEquals(1, copy[1]);
      assertEquals(0, copy[0]);
      assertEquals(7, copy.length);
   }

   public void testSimpleSortedASCNonUnique() {
      IntSorter sorter = new IntSorter(uc, IntSorter.SORT_ORDER_2_ASC, false);
      assertEquals(IntSorter.SORT_ORDER_2_ASC, sorter.getType());
      IntArray ia = populateArrayWith(sorter);
      int[] copy = ia.getTrimmedCopy();
      assertEquals(0, copy[0]);
      assertEquals(1, copy[1]);
      assertEquals(3, copy[2]);
      assertEquals(5, copy[3]);
      assertEquals(5, copy[4]);
      assertEquals(6, copy[5]);
      assertEquals(7, copy[6]);
      assertEquals(7, copy.length);
   }

   public void testSimpleSortedDESCNonUnique() {
      IntSorter sorter = new IntSorter(uc, IntSorter.SORT_ORDER_3_DESC, false);
      assertEquals(IntSorter.SORT_ORDER_3_DESC, sorter.getType());
      IntArray ia = populateArrayWith(sorter);
      int[] copy = ia.getTrimmedCopy();
      assertEquals(0, copy[6]);
      assertEquals(1, copy[5]);
      assertEquals(3, copy[4]);
      assertEquals(5, copy[3]);
      assertEquals(5, copy[2]);
      assertEquals(6, copy[1]);
      assertEquals(7, copy[0]);
      assertEquals(7, copy.length);
   }

   // 5 3 5 6 7 1 0
   public IntArray populateArrayWith(IntSorter sorter) {
      int[] array = new int[3];
      IntArray ia = new IntArray(array, 0, 0);
      sorter.addToIntArray(5, ia);

      assertEquals(true, array == ia.getArray());
      assertEquals(1, ia.getLen());

      sorter.addToIntArray(3, ia);
      assertEquals(2, ia.getLen());
      assertEquals(true, array == ia.getArray());

      sorter.addToIntArray(5, ia);
      if (sorter.isUnique()) {
         assertEquals(2, ia.getLen());
      } else {
         assertEquals(3, ia.getLen());
      }
      assertEquals(true, array == ia.getArray());

      sorter.addToIntArray(6, ia);
      if (sorter.isUnique()) {
         assertEquals(3, ia.getLen());
      } else {
         assertEquals(4, ia.getLen());
      }
      if (sorter.isUnique()) {
      } else {
         assertEquals(false, array == ia.getArray());
      }

      sorter.addToIntArray(7, ia);
      sorter.addToIntArray(1, ia);
      sorter.addToIntArray(0, ia);
      if (sorter.isUnique()) {
         assertEquals(6, ia.getLen());
      } else {
         assertEquals(7, ia.getLen());
      }

      return ia;
   }

}
