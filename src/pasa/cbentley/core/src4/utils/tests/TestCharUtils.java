/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.CharUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestCharUtils extends TestCaseBentley {
   CharUtils uu = new CharUtils(new UCtx());

   public TestCharUtils() {
      super(true);
   }

   @Test
   public void testUnMapZero() throws Exception {
      assertEquals('a', uu.unMapZero(0, CharUtils.PLANE_0_EN));
      assertEquals('b', uu.unMapZero(1, CharUtils.PLANE_0_EN));
      assertEquals('c', uu.unMapZero(2, CharUtils.PLANE_0_EN));
      assertEquals('d', uu.unMapZero(3, CharUtils.PLANE_0_EN));
      assertEquals('e', uu.unMapZero(4, CharUtils.PLANE_0_EN));
      assertEquals('f', uu.unMapZero(5, CharUtils.PLANE_0_EN));
      assertEquals('g', uu.unMapZero(6, CharUtils.PLANE_0_EN));
      assertEquals('h', uu.unMapZero(7, CharUtils.PLANE_0_EN));

      assertEquals('x', uu.unMapZero(23, CharUtils.PLANE_0_EN));
      assertEquals('y', uu.unMapZero(24, CharUtils.PLANE_0_EN));
      assertEquals('z', uu.unMapZero(25, CharUtils.PLANE_0_EN));

      assertEquals('а', uu.unMapZero(0, CharUtils.PLANE_4_RU));
      assertEquals('б', uu.unMapZero(1, CharUtils.PLANE_4_RU));
      assertEquals('ч', uu.unMapZero(23, CharUtils.PLANE_4_RU));
      assertEquals('я', uu.unMapZero(31, CharUtils.PLANE_4_RU));

      assertEquals('ё', uu.unMapZero(33, CharUtils.PLANE_4_RU));

   }

   public void testGetFirstIndex() {
      char[] array = "456879".toCharArray();
      assertEquals(-1, CharUtils.getFirstIndex('0', array));
      assertEquals(0, CharUtils.getFirstIndex('4', array));
      assertEquals(1, CharUtils.getFirstIndex('5', array));
      assertEquals(2, CharUtils.getFirstIndex('6', array));
      assertEquals(3, CharUtils.getFirstIndex('8', array));
      assertEquals(4, CharUtils.getFirstIndex('7', array));
      assertEquals(5, CharUtils.getFirstIndex('9', array));

      assertEquals(5, CharUtils.getFirstIndex("9", array));
      assertEquals(4, CharUtils.getFirstIndex("79", array));
      assertEquals(0, CharUtils.getFirstIndex("45", array));

      assertEquals(1, CharUtils.getFirstIndex("45", "i45b45g".toCharArray()));
      assertEquals(4, CharUtils.getFirstIndex("45", "i45b45g".toCharArray(), 3));
      
      //border cases
      assertEquals(-1, CharUtils.getFirstIndex("", "i45b45g".toCharArray()));
      assertEquals(-1, CharUtils.getFirstIndex("", "".toCharArray()));

      
      assertEquals(4, CharUtils.getFirstIndex("this", "eat this now".toCharArray()));
      assertEquals(9, CharUtils.getFirstIndex("now", "eat this now".toCharArray()));
      assertEquals(1, CharUtils.getFirstIndex("now", "enownow".toCharArray()));

   }

   public void testIsNumerical() {
      assertEquals(true, uu.isNumerical('0'));
      assertEquals(true, uu.isNumerical('1'));
      assertEquals(true, uu.isNumerical('2'));
      assertEquals(true, uu.isNumerical('3'));
      assertEquals(true, uu.isNumerical('4'));
      assertEquals(true, uu.isNumerical('5'));
      assertEquals(true, uu.isNumerical('6'));
      assertEquals(true, uu.isNumerical('7'));
      assertEquals(true, uu.isNumerical('8'));
      assertEquals(true, uu.isNumerical('9'));
      assertEquals(false, uu.isNumerical('r'));

   }

   public void testMapZero() throws Exception {
      assertEquals(0, uu.mapZero('a'));
      assertEquals(1, uu.mapZero('b'));
      assertEquals(2, uu.mapZero('c'));
      assertEquals(3, uu.mapZero('d'));
      assertEquals(4, uu.mapZero('e'));
      assertEquals(5, uu.mapZero('f'));
      assertEquals(6, uu.mapZero('g'));
      assertEquals(7, uu.mapZero('h'));
      assertEquals(8, uu.mapZero('i'));
      assertEquals(9, uu.mapZero('j'));
      assertEquals(10, uu.mapZero('k'));
      assertEquals(11, uu.mapZero('l'));
      assertEquals(12, uu.mapZero('m'));
      assertEquals(24, uu.mapZero('y'));
      assertEquals(25, uu.mapZero('z'));
      assertEquals(0, uu.mapZero('.'));
      assertEquals(0, uu.mapZero(' '));
      assertEquals(0, uu.mapZero('+'));

      assertEquals(0, uu.mapZero('.'));
      assertEquals(31, uu.mapZero('я'));
      assertEquals(31, uu.mapZero('ё'));
      assertEquals(0, uu.mapZero('а'));
      assertEquals(1, uu.mapZero('б'));
      assertEquals(2, uu.mapZero('в'));
      assertEquals(3, uu.mapZero('г'));
      assertEquals(4, uu.mapZero('д'));

      assertEquals(23, uu.mapZero('ч'));

   }

   public void testShiftCharUp() {

      char[] cs = new char[] { '1', '2', '3', '4', '5' };
      CharUtils.shiftCharUp(cs, 1, 0, 1);
      assertEquals("11245", new String(cs));

      cs = new char[] { '1', '2', '3', '4', '5' };
      CharUtils.shiftCharUp(cs, 2, 0, 1);
      assertEquals("12125", new String(cs));

      cs = new char[] { '1', '2', '3', '4', '5' };
      CharUtils.shiftCharUp(cs, 3, 0, 1);
      assertEquals("12312", new String(cs));

      cs = new char[] { '1', '2', '3', '4', '5' };
      CharUtils.shiftCharUp(cs, 4, 0, 1);
      assertEquals("12341", new String(cs));

      cs = new char[] { '1', '2', '3', '4', '5' };
      CharUtils.shiftCharUp(cs, 5, 0, 1);
      assertEquals("12345", new String(cs));
   }

   @Test
   public void testGetCyrillicChar() throws Exception {
      char[] c = uu.getCyrillicChar();
      assertEquals(c.length, 33);
      int offset = 0;
      assertEquals('а', c[offset++]);
      assertEquals('б', c[offset++]);
      assertEquals('в', c[offset++]);
      assertEquals('г', c[offset++]);
      assertEquals('д', c[offset++]);
      assertEquals('е', c[offset++]);
      assertEquals('ж', c[offset++]);
      assertEquals('з', c[offset++]);
      assertEquals('и', c[offset++]);
      assertEquals('й', c[offset++]);
      assertEquals('к', c[offset++]);
      assertEquals('л', c[offset++]);
      assertEquals('м', c[offset++]);
      assertEquals('н', c[offset++]);
      assertEquals('о', c[offset++]);
      assertEquals('п', c[offset++]);
      assertEquals('р', c[offset++]);
      assertEquals('с', c[offset++]);
      assertEquals('т', c[offset++]);
      assertEquals('у', c[offset++]);
      assertEquals('ф', c[offset++]);
      assertEquals('х', c[offset++]);
      assertEquals('ц', c[offset++]);
      assertEquals('ч', c[offset++]);
      assertEquals('ш', c[offset++]);
      assertEquals('щ', c[offset++]);
      assertEquals('ъ', c[offset++]);
      assertEquals('ы', c[offset++]);
      assertEquals('ь', c[offset++]);
      assertEquals('э', c[offset++]);
      assertEquals('ю', c[offset++]);
      assertEquals('я', c[offset++]);
   }

   @Test
   public void testCharFromLowByteInt() throws Exception {
   }

   public void setupAbstract() {

   }

}
