package pasa.cbentley.core.src4.utils.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.CharUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestCharUtils extends BentleyTestCase {
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

   public void setUpMord() {
      
   }

}
