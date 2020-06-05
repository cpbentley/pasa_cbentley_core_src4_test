/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import pasa.cbentley.core.src4.utils.LongUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestLongUtils extends TestCaseBentley {

   public TestLongUtils() {
      super(true);
   }

   @Test
   public void testReadLongLE() throws Exception {

      byte[] bOne = LongUtils.byteArrayLEFromLong(1);
      assertEquals(1, LongUtils.readLongLE(bOne, 0));

      byte[] b1234567 = LongUtils.byteArrayLEFromLong(1234567);
      assertEquals(1234567, LongUtils.readLongLE(b1234567, 0));

      byte[] bOneM = LongUtils.byteArrayLEFromLong(-1);
      assertEquals(-1, LongUtils.readLongLE(bOneM, 0));

      byte[] bMin = LongUtils.byteArrayLEFromLong(Long.MIN_VALUE);
      assertEquals(Long.MIN_VALUE, LongUtils.readLongLE(bMin, 0));

      byte[] bMax = LongUtils.byteArrayLEFromLong(Long.MAX_VALUE);
      assertEquals(Long.MAX_VALUE, LongUtils.readLongLE(bMax, 0));

   }

   @Test
   public void testReadLongBE() throws Exception {

      byte[] bOne = LongUtils.byteArrayBEFromLong(1);
      assertEquals(1, LongUtils.readLongBE(bOne, 0));

      byte[] b1234567 = LongUtils.byteArrayBEFromLong(1234567);
      assertEquals(1234567, LongUtils.readLongBE(b1234567, 0));

      byte[] bOneM = LongUtils.byteArrayBEFromLong(-1);
      assertEquals(-1, LongUtils.readLongBE(bOneM, 0));

      byte[] bMax = LongUtils.byteArrayBEFromLong(Long.MAX_VALUE);
      assertEquals(Long.MAX_VALUE, LongUtils.readLongBE(bMax, 0));

      byte[] bMin = LongUtils.byteArrayBEFromLong(Long.MIN_VALUE);
      assertEquals(Long.MIN_VALUE, LongUtils.readLongBE(bMin, 0));

   }

   public void setupAbstract() {
      // TODO Auto-generated method stub

   }

}
