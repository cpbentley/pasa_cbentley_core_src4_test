/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.io.tests;

import pasa.cbentley.core.src4.io.BADataIS;
import pasa.cbentley.core.src4.io.BADataOS;
import pasa.cbentley.testing.BentleyTestCase;

public class TestBADataIS extends BentleyTestCase {

   public TestBADataIS() {
      super(false);
   }

   public void setupAbstract() {

   }

   public void testWriteInt() {

      BADataOS daos = new BADataOS(uc);
      daos.writeInt(5);
      daos.writeInt(Integer.MAX_VALUE);
      daos.writeInt(Integer.MIN_VALUE);

      BADataIS bais = new BADataIS(uc, daos);

      assertEquals(5, bais.readInt());
      assertEquals(Integer.MAX_VALUE, bais.readInt());
      assertEquals(Integer.MIN_VALUE, bais.readInt());
      testEndOfFile(bais);
   }

   public void testWriteByte() {
      BADataOS daos = new BADataOS(uc);
      daos.writeByte(5);
      daos.writeByte(127);
      daos.writeByte(255);
      daos.writeByte(256);

      BADataIS bais = new BADataIS(uc, daos);
      assertEquals(5, bais.readByte());
      assertEquals(127, bais.readByte());
      assertEquals(-1, bais.readByte());
      assertEquals(0, bais.readByte());

      testEndOfFile(bais);
   }

   public void testWriteShort() {
      BADataOS daos = new BADataOS(uc);
      daos.writeShort(5);
      daos.writeShort(127);
      daos.writeShort(Short.MIN_VALUE);
      BADataIS bais = new BADataIS(uc, daos);
      assertEquals(5, bais.readShort());
      assertEquals(127, bais.readShort());
      assertEquals(Short.MIN_VALUE, bais.readShort());
      testEndOfFile(bais);
   }

   public void testWriteInt24() {
      BADataOS daos = new BADataOS(uc);
      daos.writeInt24(5);
      daos.writeInt24(151020);
      BADataIS bais = new BADataIS(uc, daos);
      assertEquals(5, bais.readInt24());
      assertEquals(151020, bais.readInt24());
      testEndOfFile(bais);
   }

   public void testWriteLong() {
      BADataOS daos = new BADataOS(uc);
      daos.writeLong(5);
      daos.writeLong(127);
      daos.writeLong(Long.MIN_VALUE);
      BADataIS bais = new BADataIS(uc, daos);
      assertEquals(5, bais.readLong());
      assertEquals(127, bais.readLong());
      assertEquals(Long.MIN_VALUE, bais.readLong());
      testEndOfFile(bais);
   }

   public void testWriteUTF8() {
      BADataOS daos = new BADataOS(uc);
      daos.writeUTF("bonjour");
      daos.writeUTF("йцу кен");
      daos.writeUTF("é`a à");
      BADataIS bais = new BADataIS(uc, daos);
      assertEquals("bonjour", bais.readUTF());
      assertEquals("йцу кен", bais.readUTF());
      assertEquals("é`a à", bais.readUTF());
      testEndOfFile(bais);
   }

   private void testEndOfFile(BADataIS bais) {
      try {
         bais.readBoolean();
         assertNotReachable("End of Array");
      } catch (IllegalStateException e) {

      }
   }

   public void testWriteByteArray() {
      BADataOS daos = new BADataOS(uc);
      byte[] data = new byte[] { 1, 2, 3, 4, 5, 6 };
      daos.writeByteArray(data);
      daos.writeByteArray(data, 1, 2);

      BADataIS bais = new BADataIS(uc, daos);

      byte[] firstArray = bais.readByteArray();
      assertEqualsByteArray(data, firstArray);

      byte[] sm = bais.readByteArray();
      assertEquals(2, sm[0]);
      assertEquals(3, sm[1]);
      assertEquals(2, sm.length);
      testEndOfFile(bais);
   }

}
