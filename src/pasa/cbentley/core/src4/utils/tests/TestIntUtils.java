/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import java.util.Random;

import org.junit.Test;

import pasa.cbentley.core.src4.utils.BitUtils;
import pasa.cbentley.core.src4.utils.IntUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIntUtils extends TestCaseBentley {

   private IntUtils iu;

   public TestIntUtils() {
      super(true);
   }

   public void setupAbstract() {
      iu = new IntUtils(uc);
   }

   public void testGeneratedInterval() {
      int[] vals = iu.getGeneratedInterval(5, 6);
      assertEquals(2, vals.length);
      assertEquals(5, vals[0]);
      assertEquals(6, vals[1]);

   }

   public void testGetIntByteSize() {
      int val = BitUtils.toInteger("100000000");
      assertEquals(val, 256);
      assertEquals(1, iu.getIntByteSize(val - 1));
      assertEquals(2, iu.getIntByteSize(val));
      val = BitUtils.toInteger("10000000000000000");
      assertEquals(val, 65536);
      assertEquals(2, iu.getIntByteSize(val - 1));
      assertEquals(3, iu.getIntByteSize(val));

      val = BitUtils.toInteger("1000000000000000000000000");
      assertEquals(val, 16777216);
      assertEquals(3, iu.getIntByteSize(val - 1));
      assertEquals(4, iu.getIntByteSize(val));
      assertEquals(4, iu.getIntByteSize(-1));

   }

   public void testMinize() {
      int[] ar = new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 9, 6, 3, 2, 1, 1, 1 };
      assertEquals(ar.length, 15);

      int[][][] Bigresults = iu.minize(ar);
      int[][] results = Bigresults[0];
      int[][] indexes = Bigresults[1];

      assertEquals(8, results.length);

      for (int i = 0; i < results.length; i++) {
         System.out.println(" \n" + i);
         iu.debug(results[i], System.out);
      }
      assertEquals(results[0][0], 9);
      assertEquals(indexes[0][0], 8);

      assertEquals(results[1][0], 9);
      assertEquals(indexes[1][0], 7);

      assertEquals(results[2].length, 2);
      assertEquals(results[2][0], 8);
      assertEquals(results[2][1], 1);

      assertEquals(indexes[2][0], 6);
      assertEquals(indexes[2][1], 14);

      assertEquals(results[3].length, 2);
      assertEquals(results[3][0], 7);
      assertEquals(results[3][1], 2);
      assertEquals(indexes[3][0], 5);
      assertEquals(indexes[3][1], 0);

      assertEquals(results[4].length, 2);
      assertEquals(results[4][0], 6);
      assertEquals(results[4][1], 3);

      assertEquals(results[5].length, 2);
      assertEquals(results[5][0], 6);
      assertEquals(results[5][1], 3);

      assertEquals(results[6].length, 2);
      assertEquals(results[6][0], 5);
      assertEquals(results[6][1], 4);

      assertEquals(results[7].length, 3);
      assertEquals(results[7][0], 2);
      assertEquals(results[7][1], 1);
      assertEquals(results[7][2], 1);

      int[] mem = new int[] { 345, 6577, 323, 24, 567, 3456, 2345, 6777 };
      int[][][] bigr = iu.minize(mem);
      int[][] res = bigr[0];

      for (int i = 0; i < res.length; i++) {
         System.out.println(" \n" + i);
         iu.debug(res[i], System.out);
      }

      assertEquals(res[0].length, 1);
      assertEquals(res[0][0], 6777);

      assertEquals(res[1].length, 2);
      assertEquals(res[1][0], 6577);
      assertEquals(res[1][1], 24);

      mem = new int[] { 345, 6577, 323, 24, 567, 3456, 2345, 6777, 456, 1245 };
      bigr = iu.minize(mem);
      res = bigr[0];

      for (int i = 0; i < res.length; i++) {
         System.out.println(" \n#" + i);
         iu.debug(res[i], System.out);
         System.out.println("sum=" + iu.sum(res[i]));

      }

   }

   public void testPatch() {

      int[] vals = new int[] { 1, 2, 3, 4, 6, 7, 8, 8, 9, 10, 11, 15, 16 };

      int[] patches = iu.patch(vals, 0, vals.length, 6);

      int offset = 0;
      assertEquals(patches[offset++], 4);
      assertEquals(patches[offset++], 1);

      assertEquals(patches[offset++], 6);
      assertEquals(patches[offset++], 6);

      assertEquals(patches[offset++], 2);
      assertEquals(patches[offset++], 15);

      assertEquals(patches.length, 6);

      vals = new int[] { 1, 2, 3, 4, 6, 7, 8, 8, 9, 10, 11, 15, 16, 21 };

      patches = iu.patch(vals, 0, vals.length, 4);

      offset = 0;
      assertEquals(patches[offset++], 4);
      assertEquals(patches[offset++], 1);

      assertEquals(patches[offset++], 4);
      assertEquals(patches[offset++], 6);

      assertEquals(patches[offset++], 2);
      assertEquals(patches[offset++], 10);

      assertEquals(patches[offset++], 2);
      assertEquals(patches[offset++], 15);

      assertEquals(patches[offset++], 1);
      assertEquals(patches[offset++], 21);

      assertEquals(patches.length, 10);
   }

   @Test
   public void testReadIntBE() throws Exception {

      byte[] bOne = IntUtils.byteArrayBEFromInt(1);
      assertEquals(1, IntUtils.readIntBE(bOne, 0));

      byte[] b1234567 = IntUtils.byteArrayBEFromInt(1234567);
      assertEquals(1234567, IntUtils.readIntBE(b1234567, 0));

      byte[] bOneM = IntUtils.byteArrayBEFromInt(-1);
      assertEquals(-1, IntUtils.readIntBE(bOneM, 0));

      byte[] bMin = IntUtils.byteArrayBEFromInt(Integer.MIN_VALUE);
      assertEquals(Integer.MIN_VALUE, IntUtils.readIntBE(bMin, 0));

      byte[] bMax = IntUtils.byteArrayBEFromInt(Integer.MAX_VALUE);
      assertEquals(Integer.MAX_VALUE, IntUtils.readIntBE(bMax, 0));
   }

   public void testGetFirstIndex() {

      int[] ar = new int[0];
      assertEquals(-1, IntUtils.getFirstIndex(5, ar, 0, 0));
      ar = new int[] { 5, 66, 8, 89, 9, 9, 38 };
      assertEquals(0, IntUtils.getFirstIndex(5, ar, 0, ar.length));
      assertEquals(1, IntUtils.getFirstIndex(66, ar, 0, ar.length));
      assertEquals(2, IntUtils.getFirstIndex(8, ar, 0, ar.length));
      assertEquals(3, IntUtils.getFirstIndex(89, ar, 0, ar.length));
      assertEquals(4, IntUtils.getFirstIndex(9, ar, 0, ar.length));
      assertEquals(6, IntUtils.getFirstIndex(38, ar, 0, ar.length));
   }

   public void testGetFirstIndexASC() {

      int[] ar = new int[0];
      assertEquals(-1, IntUtils.getFirstIndexASC(5, ar, 0, ar.length));
      ar = new int[] { 5, 5, 5, 89 };
      assertEquals(0, IntUtils.getFirstIndexASC(5, ar, 0, ar.length));
      assertEquals(3, IntUtils.getFirstIndexASC(89, ar, 0, ar.length));

      ar = new int[] { 1, 1, 1, 1, 5, 5, 5, 89 };
      assertEquals(4, IntUtils.getFirstIndexASC(5, ar, 0, ar.length));

   }

   public void testGetIndexSimilarValueDown() {
      int[] ar = new int[] { 5, 5, 5, 89 };
      assertEquals(0, IntUtils.getIndexSimilarValueDown(0, ar, 0, ar.length));
      assertEquals(0, IntUtils.getIndexSimilarValueDown(1, ar, 0, ar.length));
      assertEquals(0, IntUtils.getIndexSimilarValueDown(2, ar, 0, ar.length));
      assertEquals(3, IntUtils.getIndexSimilarValueDown(3, ar, 0, ar.length));
   }

   public void testGetIndexSimilarValueUp() {
      int[] ar = new int[] { 5, 5, 5, 89 };
      assertEquals(2, IntUtils.getIndexSimilarValueUp(0, ar, 0, ar.length));
      assertEquals(2, IntUtils.getIndexSimilarValueUp(1, ar, 0, ar.length));
      assertEquals(2, IntUtils.getIndexSimilarValueUp(2, ar, 0, ar.length));
      assertEquals(3, IntUtils.getIndexSimilarValueDown(3, ar, 0, ar.length));
   }

   @Test
   public void testReadIntLE() throws Exception {
      System.out.println("testReadIntLE");
      byte[] bOne = IntUtils.byteArrayLEFromInt(1);
      assertEquals(1, IntUtils.readIntLE(bOne, 0));

      byte[] b1234567 = IntUtils.byteArrayLEFromInt(1234567);
      assertEquals(1234567, IntUtils.readIntLE(b1234567, 0));

      byte[] bOneM = IntUtils.byteArrayLEFromInt(-1);
      assertEquals(-1, IntUtils.readIntLE(bOneM, 0));

      byte[] bMin = IntUtils.byteArrayLEFromInt(Integer.MIN_VALUE);
      assertEquals(Integer.MIN_VALUE, IntUtils.readIntLE(bMin, 0));

      byte[] bMax = IntUtils.byteArrayLEFromInt(Integer.MAX_VALUE);
      assertEquals(Integer.MAX_VALUE, IntUtils.readIntLE(bMax, 0));
   }

   public void testReverseIntArray() {

      int[] data = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

      iu.reverse(data);

      assertEquals(data[0], 9);
      assertEquals(data[1], 8);
      assertEquals(data[2], 7);
      assertEquals(data[3], 6);
      assertEquals(data[4], 5);
      assertEquals(data[5], 4);
      assertEquals(data[6], 3);
      assertEquals(data[7], 2);
      assertEquals(data[8], 1);

      data = new int[] { 1 };
      iu.reverse(data);
      assertEquals(data[0], 1);

      data = new int[] { 1, 2 };
      iu.reverse(data);
      assertEquals(data[0], 2);
      assertEquals(data[1], 1);

      data = new int[] { 1, 2, 3 };
      iu.reverse(data);
      assertEquals(data[0], 3);
      assertEquals(data[1], 2);
      assertEquals(data[2], 1);

      data = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

      iu.reverse(data, 1, 3);

      assertEquals(data[0], 1);
      assertEquals(data[1], 4);
      assertEquals(data[2], 3);
      assertEquals(data[3], 2);
      assertEquals(data[4], 5);
      assertEquals(data[5], 6);
      assertEquals(data[6], 7);
      assertEquals(data[7], 8);
      assertEquals(data[8], 9);
   }

   public void testSortMap() {

      int[] tobesorted = new int[] { 4, 2, 5 };
      int[] map = iu.sortMapped(tobesorted, 0, tobesorted.length);
      assertEquals(tobesorted[0], 2);
      assertEquals(tobesorted[1], 4);
      assertEquals(tobesorted[2], 5);
      assertEquals(map[0], 1);
      assertEquals(map[1], 0);
      assertEquals(map[2], 2);

      System.out.println("---");

      tobesorted = new int[] { 11, 4, 2 };
      map = iu.sortMapped(tobesorted, 0, tobesorted.length);
      assertEquals(tobesorted[0], 2);
      assertEquals(tobesorted[1], 4);
      assertEquals(tobesorted[2], 11);
      assertEquals(map[0], 2);
      assertEquals(map[1], 1);
      assertEquals(map[2], 0);

      System.out.println("---");

      tobesorted = new int[] { 11, 4, 2, 5 };
      map = iu.sortMapped(tobesorted, 0, tobesorted.length);
      assertEquals(tobesorted[0], 2);
      assertEquals(tobesorted[1], 4);
      assertEquals(tobesorted[2], 5);
      assertEquals(tobesorted[3], 11);

      iu.debug(map, System.out);
      assertEquals(map[0], 3);
      assertEquals(map[1], 1);
      assertEquals(map[2], 0);
      assertEquals(map[3], 2);

   }

   public void testDivideCeil() {

      assertEquals(1, IntUtils.divideCeil(1, 1));

      assertEquals(1, IntUtils.divideCeil(1, 6));
      assertEquals(0, IntUtils.divideCeil(1, 0));
      assertEquals(1, IntUtils.divideCeil(6, 6));
      assertEquals(2, IntUtils.divideCeil(7, 6));
      assertEquals(1, IntUtils.divideCeil(1, 2));

      assertEquals(1, IntUtils.divideCeil(8, 8));
      assertEquals(2, IntUtils.divideCeil(9, 8));
      assertEquals(2, IntUtils.divideCeil(16, 8));
      assertEquals(3, IntUtils.divideCeil(17, 8));

      assertEquals(7, IntUtils.divideCeil(50, 8));
      assertEquals(7, IntUtils.divideCeil(56, 8));
      assertEquals(8, IntUtils.divideCeil(57, 8));

   }

   public void testModulo() {

      assertEquals(0, 0 % 4);
      assertEquals(1, 1 % 4);
      assertEquals(2, 2 % 4);
      assertEquals(3, 3 % 4);
      assertEquals(0, 4 % 4);

      assertEquals(0, 0 % 1);
      assertEquals(0, 1 % 1);
      assertEquals(0, 2 % 1);
   }

   public void testSplitDigits() {

      byte[] data = IntUtils.splitIntoDigits(145, 3, 10);
      assertEquals(data.length, 3);
      assertEquals(5, data[0]);
      assertEquals(4, data[1]);
      assertEquals(1, data[2]);

      data = IntUtils.splitIntoDigits(1405, 5, 10);
      assertEquals(data.length, 5);
      assertEquals(5, data[0]);
      assertEquals(0, data[1]);
      assertEquals(4, data[2]);
      assertEquals(1, data[3]);
      assertEquals(0, data[4]);

      data = IntUtils.splitIntoDigits(10400, 5, 10);
      assertEquals(data.length, 5);
      assertEquals(0, data[0]);
      assertEquals(0, data[1]);
      assertEquals(4, data[2]);
      assertEquals(0, data[3]);
      assertEquals(1, data[4]);
   }

   public void testValueCreator() {
      int[] vals = iu.getValues(5, 24, 24);
      assertEquals(vals[0], 24);
      assertEquals(vals[1], 24);
      assertEquals(vals[2], 24);
      assertEquals(vals[3], 24);
      assertEquals(vals[4], 24);
      vals = iu.getValues(1, 20, 40);
      assertEquals(vals[0], 20);
      vals = iu.getValues(2, 20, 40);
      assertEquals(vals[0], 20);
      assertEquals(vals[1], 40);
      vals = iu.getValues(3, 20, 40);
      assertEquals(vals[0], 20);
      assertEquals(vals[1], 30);
      assertEquals(vals[2], 40);

      vals = iu.getValues(4, 20, 60);
      assertEquals(vals[0], 20);
      assertEquals(vals[1], 33);
      assertEquals(vals[2], 46);
      assertEquals(vals[3], 60);

      vals = iu.getValues(5, 24, 255);
      assertEquals(vals[0], 24);
      assertEquals(vals[1], 81);
      assertEquals(vals[2], 139);
      assertEquals(vals[3], 197);
      assertEquals(vals[4], 255);
   }

   @Test
   public void testReadIntBEUnsigned() throws Exception {
      byte[] data = new byte[4];
      IntUtils.writeIntBEUnsigned(data, 0, Integer.MAX_VALUE + 1);

      assertEquals(Integer.MAX_VALUE + 1, IntUtils.readIntBEUnsigned(data, 0));
   }

   public void testShuffle() {

      int[] ar = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

      Random r = new Random(50000);
      uc.setRandom(r);

      uc.getIU().shuffle(ar);

      System.out.println(uc.getIU().toStringIntArray(ar));
      
      uc.getIU().shuffle(ar);

      System.out.println(uc.getIU().toStringIntArray(ar));

      ar = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
      System.out.println("----");
      uc.getIU().shuffle(ar, 1, 5);
      System.out.println(uc.getIU().toStringIntArray(ar));
      uc.getIU().shuffle(ar, 1, 5);
      System.out.println(uc.getIU().toStringIntArray(ar));

   }
}
