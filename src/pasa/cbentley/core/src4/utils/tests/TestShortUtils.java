/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import org.junit.Test;

import pasa.cbentley.core.src4.utils.ShortUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestShortUtils extends BentleyTestCase {

   public TestShortUtils() {
      super(true);
   }

   public void setupAbstract() {

   }

   @Test
   public void testReadShortBEUnsigned() throws Exception {
      byte[] b = ShortUtils.byteArrayBEUnsignedFromShort((short) 1056);
      assertEquals(1056, ShortUtils.readShortBEUnsigned(b, 0));

      byte[] bMax = ShortUtils.byteArrayBEUnsignedFromShort(Short.MAX_VALUE);
      assertEquals(Short.MAX_VALUE, ShortUtils.readShortBEUnsigned(bMax, 0));

      byte[] bMin = ShortUtils.byteArrayBEUnsignedFromShort(Short.MIN_VALUE);
      assertEquals(32768, ShortUtils.readShortBEUnsigned(bMin, 0));
   }

   public void testReadShortBESigned() throws Exception {
      byte[] b = ShortUtils.byteArrayBESignedFromShort((short) 1056);
      assertEquals(1056, ShortUtils.readShortBESigned(b, 0));

      byte[] bMax = ShortUtils.byteArrayBESignedFromShort(Short.MAX_VALUE);
      assertEquals(Short.MAX_VALUE, ShortUtils.readShortBEUnsigned(bMax, 0));

      byte[] bMin = ShortUtils.byteArrayBESignedFromShort(Short.MIN_VALUE);
      assertEquals(Short.MIN_VALUE, ShortUtils.readShortBESigned(bMin, 0));
   }

   @Test
   public void testReadShortLESigned() throws Exception {

      byte[] b = ShortUtils.byteArrayLESignedFromShort((short) 1056);
      assertEquals(1056, ShortUtils.readShortLESigned(b, 0));

      byte[] bOneM = ShortUtils.byteArrayLESignedFromShort((short) -1);
      assertEquals(-1, ShortUtils.readShortLESigned(bOneM, 0));

      byte[] bMax = ShortUtils.byteArrayLESignedFromShort(Short.MAX_VALUE);
      assertEquals(Short.MAX_VALUE, ShortUtils.readShortLESigned(bMax, 0));

      byte[] bMin = ShortUtils.byteArrayLESignedFromShort(Short.MIN_VALUE);
      assertEquals(Short.MIN_VALUE, ShortUtils.readShortLESigned(bMin, 0));
   }

   public void testReadShortLEUnsigned() throws Exception {

      byte[] b = ShortUtils.byteArrayLEUnsignedFromShort((short) 1056);
      assertEquals(1056, ShortUtils.readShortLEUnsigned(b, 0));

      byte[] bOneM = ShortUtils.byteArrayLEUnsignedFromShort((short) -1);
      assertEquals(65535, ShortUtils.readShortLEUnsigned(bOneM, 0));

      byte[] bMax = ShortUtils.byteArrayLEUnsignedFromShort(Short.MAX_VALUE);
      assertEquals(Short.MAX_VALUE, ShortUtils.readShortLEUnsigned(bMax, 0));

      byte[] bMin = ShortUtils.byteArrayLESignedFromShort(Short.MIN_VALUE);
      assertEquals(32768, ShortUtils.readShortLEUnsigned(bMin, 0));
   }

   @Test
   public void testWriteReadShortBE() throws Exception {
      byte[] data = new byte[5];
      int index = 1;
      int value = 1230;
      ShortUtils.writeShortBEUnsigned(data, index, value);

      assertEquals(1230, ShortUtils.readShortBEUnsigned(data, index));
   }

   public void testWriteReadShortBE60k() throws Exception {
      byte[] data = new byte[5];
      int index = 1;
      int value = 60100;
      ShortUtils.writeShortBEUnsigned(data, index, value);
      assertEquals(60100, ShortUtils.readShortBEUnsigned(data, index));

      ShortUtils.writeShortBESigned(data, index, value);
      assertEquals(-5436, ShortUtils.readShortBESigned(data, index));
   }

   public void testWriteReadShortBENegative() throws Exception {
      byte[] data = new byte[5];
      int index = 1;
      int value = -1230;
      ShortUtils.writeShortBEUnsigned(data, index, value);
      assertEquals(64306, ShortUtils.readShortBEUnsigned(data, index));

      ShortUtils.writeShortBEUnsigned(data, 3, -1);
      assertEquals(65535, ShortUtils.readShortBEUnsigned(data, 3));

   }

   public void testWriteReadShortLE() throws Exception {
      byte[] data = new byte[5];
      int index = 1;
      int value = 1230;
      ShortUtils.writeShortLESigned(data, index, value);

      assertEquals(1230, ShortUtils.readShortLESigned(data, index));
   }

   public void testWriteReadShortLE60k() throws Exception {
      byte[] data = new byte[5];
      int index = 1;
      int value = 60100;
      ShortUtils.writeShortLESigned(data, index, value);
      assertEquals(-5436, ShortUtils.readShortLESigned(data, index));
   }

   public void testWriteReadShortLENegative() throws Exception {
      byte[] data = new byte[5];
      int index = 1;
      int value = -1230;
      ShortUtils.writeShortLESigned(data, index, value);
      assertEquals(-1230, ShortUtils.readShortLESigned(data, index));
   }
}
