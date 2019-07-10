package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.ctx.UCtx;

import pasa.cbentley.core.src4.utils.BitUtils;
import pasa.cbentley.core.src4.utils.BitCoordinate;
import pasa.cbentley.core.src4.utils.IntUtils;
import pasa.cbentley.core.src4.utils.ShortUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestBitUtils extends BentleyTestCase {

   private BitUtils bu;

   public TestBitUtils() {
      super(true);
   }

   public void setUpMord() {
      UCtx uc = new UCtx();
      bu = uc.getBU();
   }

   public static final int MINUS_SIGN_16BITS_FLAG = 32768;

   public void testBitDecimalValue() {
      assertEquals(0b1, 1); //1
      assertEquals(0b10, 2);
      assertEquals(0b100, 4);
      assertEquals(0b1000, 8); //4 
      assertEquals(0b10000, 16);
      assertEquals(0b100000, 32);
      assertEquals(0b1000000, 64);
      assertEquals(0b10000000, 128); //8
      assertEquals(0b100000000, 256);
      assertEquals(0b1000000000, 512);
      assertEquals(0b10000000000, 1024);
      assertEquals(0b100000000000, 2048); //12
      assertEquals(0b1000000000000, 4096);
      assertEquals(0b10000000000000, 8192);
      assertEquals(0b100000000000000, 16384);
      assertEquals(0b1000000000000000, 32768); //16
      assertEquals(0b10000000000000000, 65536);
      assertEquals(0b100000000000000000, 131072);
      assertEquals(0b1000000000000000000, 262144);
      assertEquals(0b10000000000000000000, 524288); //20
      assertEquals(0b100000000000000000000, 1048576);
      assertEquals(0b1000000000000000000000, 2097152);
      assertEquals(0b10000000000000000000000, 4194304);
      assertEquals(0b100000000000000000000000, 8388608); //24
      assertEquals(0b1000000000000000000000000, 16777216);
      assertEquals(0b10000000000000000000000000, 33554432);
      assertEquals(0b100000000000000000000000000, 67108864);
      assertEquals(0b1000000000000000000000000000, 134217728); //28
      assertEquals(0b10000000000000000000000000000, 268435456);
      assertEquals(0b100000000000000000000000000000, 536870912); // 30
      assertEquals(0b1000000000000000000000000000000, 1073741824); //31
      assertEquals(0b10000000000000000000000000000000, -2147483648); //32

      // error 33 bits is out of range 
      //assertEquals(0b100000000000000000000000000000000, 0);

      int val = BitUtils.setBitMaskOnData(4, BitUtils.BIT_MASK_32);
      assertEquals(val, -2147483644);
      assertEquals(4, BitUtils.getDataOutOfBitMask(val, BitUtils.BIT_MASK_32));

      assertEquals(true, BitUtils.isBitMaskSet(val, BitUtils.BIT_MASK_32));
   }

   public void testHexadecimal() {
      int val = 0xFF0F;
      System.out.println("0xFF0F = " + val + " " + bu.toBinString8(val));

      val = 0x0F;
      System.out.println("0xF = " + val + " " + bu.toBinString8(val));

      val = 0x03;
      System.out.println("0x03 = " + val + " " + bu.toBinString8(val));

      val = 0x0C;
      System.out.println("0x0C = " + val + " " + bu.toBinString8(val));

      val = 0x30;
      System.out.println("0x30 = " + val + " " + bu.toBinString8(val));

      val = 0xC0;
      System.out.println("0xC0 = " + val + " " + bu.toBinString8(val));

   }

   public void testByteSize() {
      assertEquals(1, BitUtils.byteSize(1));
      assertEquals(1, BitUtils.byteSize(255));
      assertEquals(2, BitUtils.byteSize(256));
      assertEquals(4, BitUtils.byteSize(-1));
      assertEquals(4, BitUtils.byteSize(-3));
      assertEquals(4, BitUtils.byteSize(Integer.MAX_VALUE));
      assertEquals(4, BitUtils.byteSize(Integer.MIN_VALUE));

   }

   public void testBytesConsumed() {
      int index = 0;
      System.out.println((++index));
      System.out.println((index++));
      System.out.println(index++);
      System.out.println(index++);

      System.out.println(Integer.toBinaryString(1));
      System.out.println(Integer.toBinaryString(-1));
      System.out.println(Integer.toBinaryString(((-1 ^ 0xFFFFFFFF)) + 1));

      System.out.println(Integer.toBinaryString(MINUS_SIGN_16BITS_FLAG));

      System.out.println(Integer.toBinaryString(-2));
      System.out.println(Integer.toBinaryString(-3));

      int v = -40;
      System.out.println(Integer.toBinaryString(v));
      System.out.println(Integer.toBinaryString(40 | MINUS_SIGN_16BITS_FLAG));
      System.out.println(Integer.toBinaryString(40));

      v = v & 0xFFFF;
      System.out.println(Integer.toBinaryString(v));

      v |= (MINUS_SIGN_16BITS_FLAG);
      System.out.println(Integer.toBinaryString(v));

      assertEquals(1, BitUtils.countBits(1));
      assertEquals(1, BitUtils.countBits(2));
      assertEquals(2, BitUtils.countBits(3));
      assertEquals(2, BitUtils.countBits(40));
      assertEquals(28, BitUtils.countBits(-40));

      assertEquals(1, (int) 1.1f);
      assertEquals(1, (int) 1.5f);
      assertEquals(1, (int) 1.6f);
      assertEquals(1, (int) 1.9f);
      assertEquals(2, (int) Math.ceil(1.9f));
      //assertEquals((2 & 1) == 0, true); //truism test
      //assertEquals((2 & 2) == 2, true); //truism test

      assertEquals(0, BitUtils.byteConsumed(0));
      assertEquals(1, BitUtils.byteConsumed(1));
      assertEquals(1, BitUtils.byteConsumed(2));
      assertEquals(1, BitUtils.byteConsumed(3));
      assertEquals(1, BitUtils.byteConsumed(4));
      assertEquals(1, BitUtils.byteConsumed(5));
      assertEquals(1, BitUtils.byteConsumed(6));
      assertEquals(1, BitUtils.byteConsumed(7));
      assertEquals(1, BitUtils.byteConsumed(8));
      assertEquals(2, BitUtils.byteConsumed(9));
      assertEquals(2, BitUtils.byteConsumed(16));

   }

   public void testByte() {
      double d = 2.5;

      String s = Double.toString(d);
      int indexdot = s.indexOf('.');
      int prim = Integer.parseInt(s.substring(0, indexdot));
      int comp = Integer.parseInt(s.substring(indexdot + 1, s.length()));

      assertEquals(2, prim);
      assertEquals(5, comp);

      int MASK_VOL = 0x00000FFF;
      int MASK_Q = 0x00FFF000;
      int MASK_UNIT = 0xFF000000;

      //first 8, then 12 then 12
      int mvol = 250 & 0xFF;
      int mqut = 5 & 0xFFF;
      int munit = 257 & 0xFF;

      int data = 4095;

      assertEquals(true, (munit & 0xFF) <= 256);

      assertEquals(0, data & ~MASK_VOL);

      data = Integer.MAX_VALUE;

      int unit = ((munit & 0xFF) << 24);
      int qua = ((mqut & 0xFFF) << 12);
      int vol = ((mvol & 0xFFF) << 0);

      //set the volume
      data = (data & ~MASK_VOL) + vol;

      assertEquals(mvol, data & 0xFFF);

      //set the quantity
      data = (data & ~MASK_Q) + qua;

      assertEquals(mqut, (data >>> 12) & 0xFFF);

      //add unit
      data = (data & ~MASK_UNIT) + unit;

      assertEquals(mqut, (data >>> 12) & 0xFFF);
      assertEquals(mvol, data & 0xFFF);

      assertEquals(munit, (data >>> 24) & 0xFF);

   }

   public void testBitShiftAddForBitTrie() {
      int intchars = 5;
      assertEquals("101", Integer.toBinaryString(intchars));
      int valuehistory = 1;
      int len = BitUtils.widthInBits(intchars);
      int pointer = 5;
      assertEquals((pointer - len - 1), 1);
      intchars = intchars << (pointer - len - 1);
      valuehistory = valuehistory | intchars;

      assertEquals("1011", Integer.toBinaryString(valuehistory));

   }

   public void testToBinaryString() {
      assertEquals("1", Integer.toBinaryString(BitUtils.BIT_MASK_01));
      assertEquals("10", Integer.toBinaryString(BitUtils.BIT_MASK_02));

   }

   public void testMisc() {

      assertEquals(2, 0x2);
      assertEquals((3 & 0x2), 2);
      assertEquals((1 & 0x2), 0);

      //assertEquals(true, (1 & 0x1) == 1);
      //assertEquals(true, (3 & 0x2) == 2);
      //assertEquals(true, (2 & 0x2) == 2);
      assertEquals(false, (1 & 0x2) == 1);
      assertEquals(false, (2 & 0x1) == 1);

      int value = 10555;

      assertEquals("101001001110110", Integer.toBinaryString(value << 1));

      assertEquals("10100100111011", Integer.toBinaryString(value));
      assertEquals("1010010011101", Integer.toBinaryString(value >>> 1));
      assertEquals("101001001110", Integer.toBinaryString(value >>> 2));
      assertEquals("1010", Integer.toBinaryString(value >>> 10));
      assertEquals("1010", Integer.toBinaryString(value >> 10));
      assertEquals("11111111111111111111111111111111", Integer.toBinaryString(-1));
      assertEquals("11111111111111111111111111111110", Integer.toBinaryString(-2));
      assertEquals("1110", Integer.toBinaryString(14));
      assertEquals("11111111111111111111111111110010", Integer.toBinaryString(-14));
      assertEquals("110010", Integer.toBinaryString(50));
      assertEquals("11111111111111111111111111001110", Integer.toBinaryString(-50));

      assertEquals("11111111111111111101011011000101", Integer.toBinaryString(0 - value));
      assertEquals("11111111111111111111111111110101", Integer.toBinaryString((0 - value) >> 10));

      assertEquals(1, 1 << 0);
      assertEquals(2, 1 << 1);
      assertEquals(4, 1 << 2);
      assertEquals(8, 1 << 3);

      int m8 = 0x80;

      System.out.println("Integer.toString(0x80, 9) \t = " + Integer.toString(m8, 9));
      System.out.println("Integer.toBinaryString(0x80) \t = " + Integer.toBinaryString(m8));

      assertFalse(BitUtils.isBitSet(m8, 1));
      assertFalse(BitUtils.isBitSet(m8, 2));
      assertFalse(BitUtils.isBitSet(m8, 3));
      assertFalse(BitUtils.isBitSet(m8, 4));
      assertFalse(BitUtils.isBitSet(m8, 6));
      assertFalse(BitUtils.isBitSet(m8, 7));
      assertTrue(BitUtils.isBitSet(m8, 8));

      assertEquals(1, BitUtils.countBits(m8));
      assertEquals(8, BitUtils.widthInBits(m8));

      assertEquals("10000000", Integer.toString(m8, 2));

      assertEquals(m8 >>> 1, 0x40);

      assertEquals(m8 >>> 2, 0x20);

      assertEquals(m8 | (m8 >>> 1), 0xC0);

      assertEquals(m8 + m8 - 1, 0xFF);

      int mask = 0x80000000;
      int newmask = mask | (mask >>> 1);
      assertEquals(newmask, 0xC0000000);

   }

   public void testBitMask() {

      assertEquals(1, BitUtils.getMask(1));
      assertEquals(3, BitUtils.getMask(2));
      assertEquals(7, BitUtils.getMask(3));
      assertEquals(0xF, BitUtils.getMask(4));
      assertEquals(0xFF, BitUtils.getMask(8));
      assertEquals(0xFFF, BitUtils.getMask(12));
      assertEquals(0xFFFF, BitUtils.getMask(16));
      assertEquals(0xFFFFFFFF, BitUtils.getMask(32));

      int val = 0;
      val = BitUtils.setData(val, 0, 12, 456);
      val = BitUtils.setData(val, 12, 12, 56);
      val = BitUtils.setData(val, 24, 8, 6);

      int bit = 0;
      assertEquals(BitUtils.getBit(1, bit), 0);
      bit++;
      assertEquals(BitUtils.getBit(1, bit), 1);
      assertEquals(BitUtils.getBit(2, bit), 0);
      bit++;
      assertEquals(BitUtils.getBit(1, bit), 0);
      assertEquals(BitUtils.getBit(2, bit), 1);

      //getBit and set bit, for a bit by bit copy
      String s49 = Integer.toBinaryString(49);
      System.out.println(s49);
      int count = 1;
      int val49 = 0;
      for (int i = s49.length() - 1; i >= 0; i--) {
         assertEquals("For " + i, BitUtils.getBit(count, 49), Integer.parseInt("" + s49.charAt(i)));
         val49 = BitUtils.setBit(val49, count, BitUtils.getBit(count, 49));
         count++;
      }
      assertEquals(val49, 49);

      //bit = 10
      assertEquals(bit, 2);
      bit = BitUtils.setBit(bit, 2, 0);
      assertEquals(bit, 0);
      bit = BitUtils.setBit(bit, 2, 1);
      assertEquals(bit, 2);
      bit = BitUtils.setBit(bit, 1, 1);
      assertEquals(bit, 3);

      assertEquals(456, BitUtils.getData(val, 0, 12));
      assertEquals(56, BitUtils.getData(val, 12, 12));
      assertEquals(6, BitUtils.getData(val, 24, 8));

      assertEquals("1111111111111111111111111111111", bu.toBinString8(Integer.MAX_VALUE));
      assertEquals(0, BitUtils.getData(Integer.MIN_VALUE, 0, 2));
      assertEquals(-1, BitUtils.toInteger("011111111111111111111111111111111"));
      assertEquals(3, BitUtils.getData(BitUtils.toInteger("0011111111111111111111111111111111"), 0, 2));

      assertEquals(true, BitUtils.areBitsEqual(2, 0, 1));
      assertEquals(false, BitUtils.areBitsEqual(2, 0, 2));
      assertEquals(true, BitUtils.areBitsEqual(5, 9, 1));
      assertEquals(true, BitUtils.areBitsEqual(5, 9, 2));
      assertEquals(false, BitUtils.areBitsEqual(5, 9, 3));
      assertEquals(false, BitUtils.areBitsEqual(5, 9, 4));

      assertEquals(true, BitUtils.areBitsEqual(5, 13, 1));
      assertEquals(true, BitUtils.areBitsEqual(5, 13, 2));
      assertEquals(true, BitUtils.areBitsEqual(5, 13, 3));
      assertEquals(false, BitUtils.areBitsEqual(5, 13, 4));

      assertEquals(true, BitUtils.areBitsEqual(2, 6, 1));
      assertEquals(true, BitUtils.areBitsEqual(2, 6, 2));
      assertEquals(false, BitUtils.areBitsEqual(2, 6, 3));

   }

   public void testCopyRead() {
      byte[] pointers = new byte[300];
      BitCoordinate c = new BitCoordinate(uc,0, 0);
      // copy 1-3 2-3 3-3 ... 49-3
      for (int i = 1; i < 50; i++) {
         BitUtils.copyBits(pointers, c, i, 10);
         BitUtils.copyBits(pointers, c, 3, 4);
      }
      int offset = 14;
      c.map(0);
      assertEquals(0, c.getBitnum());
      assertEquals(0, c.getBytenum());
      assertEquals(1, BitUtils.readBits(pointers, c, 10));
      assertEquals(3, BitUtils.readBits(pointers, c, 4));
      assertEquals(6, c.getBitnum());
      assertEquals(1, c.getBytenum());

   }

   public void testWidthInB() {
      assertEquals(0, BitUtils.widthInBits(0));
      assertEquals(1, BitUtils.widthInBits(1));
      assertEquals(2, BitUtils.widthInBits(2));
      assertEquals(32, BitUtils.widthInBits(-1));
      assertEquals(32, BitUtils.widthInBits(-10000));

   }

   public void testBitShift() {
      // 0010       0101 >> becomes 0010
      assertEquals(2, (5 >> 1));
      assertEquals(1, (5 >> 2));
      assertEquals(1, (2 >> 1));
      assertEquals(2147483647, (-1 >>> 1));
      // signed bit shift means the 1 sign bit stays
      assertEquals(-1, (-1 >> 1));
      assertEquals(-1, (-1 >> 2));

      assertEquals(2, (1 << 1));
      assertEquals(4, (1 << 2));
      assertEquals(8, (1 << 3));

   }

   public void testFirstBitRight() {


      assertEquals(true, BitUtils.isFirstRight(0, 2));
      assertEquals(true, BitUtils.isFirstRight(4, 8));
      assertEquals(true, BitUtils.isFirstRight(4, 12));
      assertEquals(false, BitUtils.isFirstRight(4, 16));

      int base = 20;
      assertEquals(false, BitUtils.isFirstRight(base, 1));
      assertEquals(true, BitUtils.isFirstRight(base, 52));
      assertEquals(false, BitUtils.isFirstRight(base, 85));
      assertEquals(true, BitUtils.isFirstRight(base, 436));

   }

   public void testRewindForward() {
      BitCoordinate c = new BitCoordinate(uc);
      c.forward(0);
      assertEquals(c.getBitnum(), 0);
      assertEquals(c.getBytenum(), 0);
      c.rewind(1);
      assertEquals(c.getBitnum(), 0);
      assertEquals(c.getBytenum(), 0);
      c.forward(1);
      assertEquals(c.getBitnum(), 1);
      assertEquals(c.getBytenum(), 0);
      c.rewind(3);
      assertEquals(c.getBitnum(), 0);
      assertEquals(c.getBytenum(), 0);
      c.forward(9);
      assertEquals(c.getBitnum(), 1);
      assertEquals(c.getBytenum(), 1);
      c.rewind(3);
      assertEquals(c.getBitnum(), 6);
      assertEquals(c.getBytenum(), 0);

   }

   public void testShiftBitUp() {
      boolean debug = false;

      byte[] data = new byte[3];
      data[1] = 1;
      BitCoordinate c = new BitCoordinate(uc);
      c.map(0);
      int value = 5;
      int numbits = 8;
      BitUtils.copyBits(data, c, value, numbits);
      assertEquals(c.getBitnum(), 8);
      assertEquals(c.getBytenum(), 0);
      c.rewind(8);
      assertEquals(c.getBitnum(), 0);
      assertEquals(c.getBytenum(), 0);
      assertEquals(value, BitUtils.readBits(data, c, numbits));

      for (int i = 0; i < data.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8(data[i]) + " " + data[i]);
      }

      //shift for one bit from bit 1 to bit 5
      bu.shiftBitsUp(data, 1, 1, 8, true);
      c.setByteAndBit(0,1);
      assertEquals(value, BitUtils.readBits(data, c, numbits));
      if (debug)
         System.out.println("After Shift of 1 bit 1-8");
      for (int i = 0; i < data.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8(data[i]) + " " + data[i]);
      }

      byte[] ne = new byte[3];
      ne[0] = 5;
      ne[1] = 1;
      System.out.println("---");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8(ne[i]) + " " + ne[i]);
      }
      int shiftsize = 9;
      //shift for one bit from bit 1 to bit 5
      bu.shiftBitsUp(ne, shiftsize, 1, 10, true);
      c.reset();
      c.forward(shiftsize);
      assertEquals(5, BitUtils.readBits(ne, c, 3));
      if (debug)
         System.out.println("After Shift of 9 bits 1-10");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8((ne[i] & 0xFF)) + " " + (ne[i] & 0xFF));
      }

      ne = new byte[3];
      ne[0] = 5;
      ne[1] = 1;
      if (debug)
         System.out.println("---");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8(ne[i]) + " " + ne[i]);
      }
      shiftsize = 9;
      //shift for one bit from bit 1 to bit 5
      bu.shiftBitsUp(ne, shiftsize, 4, 15, true);
      c.setByteAndBit(0, 0);
      c.forward(shiftsize);
      if (debug)
         System.out.println("After Shift of 9 bits 3-10");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8((ne[i] & 0xFF)) + " " + (ne[i] & 0xFF));
      }

      //test boundary
      if (debug)
         System.out.println("--- #Boundary");
      ne = new byte[3];
      ne[0] = (byte) BitUtils.toInteger("00011111");
      ne[1] = (byte) BitUtils.toInteger("11100000");
      ne[2] = (byte) BitUtils.toInteger("11101111");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8(ne[i]) + " " + ne[i]);
      }
      shiftsize = 4;
      //shift for one bit from bit 1 to bit 5
      bu.shiftBitsUp(ne, shiftsize, 5, 24, true);
      if (debug)
         System.out.println("After Shift of 4 bits 4-24");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8((ne[i] & 0xFF)) + " " + (ne[i] & 0xFF));
      }
      assertEquals("00001111", bu.toBinString8(ne[0] & 0xFF));
      assertEquals("00000001", bu.toBinString8((ne[1] & 0xFF)));
      assertEquals("11111110", bu.toBinString8((ne[2] & 0xFF)));

   }

   public void testShiftBitDown() {
      boolean debug = true;
      byte[] data = new byte[3];
      data[1] = 5;
      data[2] = 1;
      int value = 5;
      BitCoordinate c = new BitCoordinate(uc);

      for (int i = 0; i < data.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8((data[i] & 0xFF)) + " " + (data[i] & 0xFF));
      }

      //shift for one bit from bit 1 to bit 5
      bu.shiftBitsDown(data, 1, 1, 11, true);
      c.setByteAndBit(0, 7);
      //assertEquals(value, BitUtils.readBits(data, c, 3));
      if (debug)
         System.out.println("After Shift Down of 1 bit 1-11");
      for (int i = 0; i < data.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8((data[i] & 0xFF)) + " " + (data[i] & 0xFF));
      }
      assertEquals(128, data[0] & 0xFF);
      assertEquals(2, data[1] & 0xFF);
      assertEquals(1, data[2] & 0xFF);

      byte[] ne = new byte[3];
      ne[0] = 4;
      ne[1] = 1;
      ne[2] = 111;
      System.out.println("---#2");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8((ne[i] & 0xFF)) + " " + (ne[i] & 0xFF));
      }

      int shiftsize = 8;
      //shift for one bit from bit 1 to bit 5
      bu.shiftBitsDown(ne, shiftsize, 1, 16, true);

      if (debug)
         System.out.println("After Shift of " + shiftsize + " bits 1-16");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8((ne[i] & 0xFF)) + " " + (ne[i] & 0xFF));
      }
      assertEquals(1, ne[0]);
      assertEquals(0, ne[1]);
      assertEquals(111, ne[2]);

      if (debug)
         System.out.println("--- #3");
      ne = new byte[3];
      ne[0] = (byte) BitUtils.toInteger("11111");
      ne[1] = (byte) BitUtils.toInteger("11100000");
      ne[2] = (byte) BitUtils.toInteger("11111111");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8(ne[i]) + " " + ne[i]);
      }
      shiftsize = 4;
      //shift for one bit from bit 1 to bit 5
      bu.shiftBitsDown(ne, shiftsize, 4, 24, true);
      if (debug)
         System.out.println("After Shift of 4 bits 4-24");
      for (int i = 0; i < ne.length; i++) {
         if (debug)
            System.out.println(i + ":" + bu.toBinString8((ne[i] & 0xFF)) + " " + (ne[i] & 0xFF));
      }
      assertEquals("00000001", bu.toBinString8(ne[0] & 0xFF));
      assertEquals("11111110", bu.toBinString8((ne[1] & 0xFF)));
      assertEquals("00001111", bu.toBinString8((ne[2] & 0xFF)));

   }

   /**
    * Test the memory gains possible for storing RecordStore IDs 
    * in memory efficient structures
    *
    */
   public void stestNumbersLengthRecordStore() {
      int[] ar = new int[33];
      int bt = 0;
      for (int i = 0; i <= Integer.MAX_VALUE; i++) {
         bt = BitUtils.widthInBits(i);
         if (i % 10000 == 0)
            System.out.println(i + " \t = " + bt);
         ar[bt]++;
         if (bt == 17)
            break;
      }

      for (int i = 1; i < bt; i++) {
         String gain = "\t";
         for (int j = i + 1; j < bt; j++) {
            int won = ar[i] * (j - i);
            String sr = getStringMem(won);
            if (won < 256)
               sr = " small ";
            gain += j + ":" + sr + "  ";
         }
         System.out.println(i + " = \t" + ar[i] + gain);
      }
   }

   public int getSum(int[] array, int index) {
      int sum = 0;
      for (int i = 0; i <= index; i++) {
         sum += array[i];
      }
      return sum;
   }

   private String getStringMem(int m) {
      if (m > 10000) {
         return (m / 1000) + " kb";
      } else {
         return m + " bytes";
      }

   }

   public void testByteShiftUp() {
      byte[] ar = new byte[5];
      ar[1] = 3;

      bu.shiftBytesUp(ar, 2, 1, 4);
      assertEquals(ar[1], 0);
      assertEquals(ar[2], 0);
      assertEquals(ar[3], 3);
      assertEquals(ar[4], 0);

   }

   public void testByteShiftDown() {
      byte[] ar = new byte[5];
      ar[1] = 3;

      bu.shiftBytesDown(ar, 1, 0, 4);
      assertEquals(ar[0], 3);
      assertEquals(ar[1], 0);
      assertEquals(ar[2], 0);
      assertEquals(ar[3], 0);
      assertEquals(ar[4], 0);

   }

   public void testMinusOne() {

      System.out.println(Integer.toBinaryString(-1));
      System.out.println(Byte.toString((byte) -1));

      int count = 0;
      byte[] array = new byte[10];
      array[count += 1] = 1;
      array[count += 4] = 5;
      array[count += 2] = 7;
      assertEquals(array[1], 1);
      assertEquals(count, 7);
      for (int i = 0; i < array.length; i++) {
         System.out.println(i + " = " + array[i]);
      }
      //you have to set the += to the written number before
      count = -4;
      IntUtils.writeIntLE(array, count += 4, 45);
      ShortUtils.writeShortLESigned(array, count += 4, 85);
      array[count += 2] = 6;
      assertEquals(IntUtils.readIntLE(array, 0), 45);
      assertEquals(ShortUtils.readShortLESigned(array, 4), 85);
      assertEquals(array[6], 6);

   }

   public void testExpandBit() {
      //basic a = 0. x = 4 b = 0 y=6
      myTestExpandBitSize(0, 4, 0, 6, "11111100001100001100001111000000", "0011");

      //basic a = 2. x = 2 b = 0 y=3 // 11010
      myTestExpandBitSize(2, 2, 0, 3, "11111101011010110101111000000000", "1011");
      //                       11111100111001110011111000000000                        

      //basic a = 2. x = 1 b = 1 y=2
      myTestExpandBitSize(2, 1, 1, 2, "11111100111001110011111000000000", "1011");

      //basic a = 1. x = 1 b = 2 y=2 // 11010
      myTestExpandBitSize(1, 1, 2, 2, "11111100111001110011111000000000", "1011");
      //                       11111010110101101011111000000000
      //basic a = 0. x = 3 b = 1 y=5
      myTestExpandBitSize(0, 3, 1, 5, "11111100011100011100011111000000", "1011");

   }

   private void myTestExpandBitSize(int a, int x, int b, int y, String result, String val) {
      System.out.println("a=" + a + " x=" + x + " b=" + b + " y=" + y);
      byte[] array = new byte[4];
      BitCoordinate c = new BitCoordinate(uc);
      int n = val.length();
      BitUtils.copyBits(array, c, BitUtils.toInteger("1111"), 4);
      BitUtils.copyBits(array, c, BitUtils.toInteger(val), n);
      BitUtils.copyBits(array, c, BitUtils.toInteger(val), n);
      BitUtils.copyBits(array, c, BitUtils.toInteger(val), n);
      BitUtils.copyBits(array, c, BitUtils.toInteger("1111"), 4);
      int lastusedbit = c.unmap();
      c.setByteAndBit(0,4);
      //1101 becomes 11001 
      bu.expandBitSize(array, c, 3, a, x, b, y, lastusedbit);
      assertEquals(result, bu.toBinString(array));
   }

   public void testExpandBitSize1To10() {
      byte[] array = new byte[3];
      BitCoordinate c = new BitCoordinate(uc);
      c.map(0);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);

      assertEquals("111110000000000000000000", bu.toBinString(array));

      c.map(0);
      bu.expandBitSize(array, c, 2, 0, 2, 0, 3, 6);
      assertEquals("110110100000000000000000", bu.toBinString(array));

      array = new byte[3];
      c = new BitCoordinate(uc);
      c.map(0);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);

      //doesnt'change
      c.map(0);
      bu.expandBitSize(array, c, 3, 0, 1, 0, 3, 5);
      assertEquals("100100100110000000000000", bu.toBinString(array));

      array = new byte[3];
      c = new BitCoordinate(uc);
      c.map(0);
      BitUtils.copyBit(array, c, 1);
      BitUtils.copyBit(array, c, 1);
      c.map(0);
      assertEquals("110000000000000000000000", bu.toBinString(array));
      bu.expandBitSize(array, c, 2, 0, 1, 0, 10, 2);
      assertEquals("100000000010000000000000", bu.toBinString(array));
   }

   public void testCompatibility() {
      BitCoordinate c = new BitCoordinate(uc);
      byte[] bitar = new byte[10];
      byte[] bytear = new byte[10];
      BitUtils.copyBits(bitar, c, 7, 8);
      bytear[0] = (byte) 7;
      BitCoordinate r = new BitCoordinate(uc);
      assertEquals(7, BitUtils.readBits(bytear, r, 8));
      assertEquals(7, bitar[0]);

      ShortUtils.writeShortLESigned(bytear, 2, 6666);
      c.map(16);
      BitUtils.copyBits(bitar, c, 6666, 16);
      r.map(16);
      assertEquals(6666, BitUtils.readBits(bytear, r, 16));
      assertEquals(6666, ShortUtils.readShortLESigned(bitar, 2));
      for (int i = 0; i < bytear.length; i++) {
         bytear[i] = 0;
         bitar[i] = 0;
      }
      IntUtils.writeInt24LE(bytear, 2, 77777);
      c.map(16);
      BitUtils.copyBits(bitar, c, 77777, 24);
      r.map(16);
      assertEquals(77777, BitUtils.readBits(bytear, r, 24));
      assertEquals(77777, IntUtils.readInt24LE(bitar, 2));

      for (int i = 0; i < bytear.length; i++) {
         bytear[i] = 0;
         bitar[i] = 0;
      }
      IntUtils.writeIntLE(bytear, 2, 77777999);
      c.map(16);
      BitUtils.copyBits(bitar, c, 77777999, 32);
      r.map(16);
      assertEquals(77777999, BitUtils.readBits(bytear, r, 32));
      assertEquals(77777999, IntUtils.readIntLE(bitar, 2));

      for (int i = 0; i < bytear.length; i++) {
         bytear[i] = 0;
         bitar[i] = 0;
      }
      IntUtils.writeIntXXLE(bytear, 2, 77777999, 4);
      c.map(16);
      BitUtils.copyBits(bitar, c, 77777999, 32);
      r.map(16);
      assertEquals(77777999, BitUtils.readBits(bytear, r, 32));
      assertEquals(77777999, IntUtils.readIntXXLE(bitar, 2, 4));

   }

   /**
    * 
    *
    */
   public void testExpandByteSize() {
      byte[] ar = new byte[10];
      for (int i = 0; i < 5; i++) {
         ar[i] = 1;
      }

      //shift from byte 0, 5 chunks, a=0,x=1,y=0,y=2, lastusedbyte must be greater or equal to 5
      bu.expandByteSize(ar, 0, 5, 0, 1, 0, 2, 5);

      for (int i = 0; i < ar.length; i += 2) {
         assertEquals(1, ar[i]);
         assertEquals(0, ar[i + 1]);
      }

      ar = new byte[10];
      for (int i = 0; i < 5; i++) {
         ar[i] = 1;
      }

      bu.expandByteSize(ar, 0, 5, 1, 1, 0, 2, 5);

      assertEquals(1, ar[0]);
      assertEquals(1, ar[1]);
      assertEquals(0, ar[2]);
      assertEquals(1, ar[3]);
      assertEquals(1, ar[4]);
      assertEquals(0, ar[5]);
      assertEquals(1, ar[6]);
      assertEquals(0, ar[7]);
      assertEquals(0, ar[8]);
      assertEquals(0, ar[9]);

      ar = new byte[10];
      for (int i = 0; i < ar.length; i++) {
         ar[i] = (byte) (i + 1);
      }

      bu.expandByteSize(ar, 2, 5, 0, 1, 0, 2, 0);

      for (int i = 0; i < ar.length; i++) {
         // System.out.println(ar[i]);
      }

      for (int i = 0; i < ar.length; i++) {
         ar[i] = (byte) (i + 1);
      }
      bu.expandByteSize(ar, 1, 5, 0, 1, 0, 3, 11);
      assertEquals(1, ar[0]);
      assertEquals(2, ar[1]);
      assertEquals(0, ar[2]);
      assertEquals(0, ar[3]);
      assertEquals(3, ar[4]);
      assertEquals(0, ar[5]);
      assertEquals(0, ar[6]);
      assertEquals(4, ar[7]);
      assertEquals(0, ar[8]);
      assertEquals(0, ar[9]);

      byte[] a = new byte[11];
      for (int i = 0; i < a.length; i++) {
         a[i] = 1;
      }
      IntUtils.writeInt24LE(a, 2, 5555);
      IntUtils.writeInt24LE(a, 5, 6);

      assertEquals(5555, IntUtils.readInt24LE(a, 2));
      assertEquals(6, IntUtils.readInt24LE(a, 5));
      for (int i = 0; i < a.length; i++) {
         System.out.println(a[i]);
      }
      System.out.println("--");

      bu.expandByteSize(a, 2, 2, 0, 3, 0, 4, 0);
      for (int i = 0; i < a.length; i++) {
         System.out.println(a[i]);
      }

      assertEquals(5555, IntUtils.readInt24LE(a, 2));
      assertEquals(6, IntUtils.readInt24LE(a, 6));
      assertEquals(1, a[0]);
      assertEquals(1, a[1]);
      assertEquals(-77, a[2]);
      assertEquals(21, a[3]);
      assertEquals(0, a[4]);
      assertEquals(0, a[5]);
      assertEquals(6, a[6]);
      assertEquals(0, a[7]);
      assertEquals(0, a[8]);
      assertEquals(1, a[9]);
      assertEquals(1, a[10]);

   }

}
