/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.core.src4.utils.MathUtils;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestMathUtils extends TestCaseBentley {
   MathUtils mu;

   public TestMathUtils() {
   }

   public void setupAbstract() {
      mu = new MathUtils(new UCtx());

   }

   public void testIntDivisions() {
      assertEquals(5400, 180 * 30);
      assertEquals(54, 5400 / 100);
      assertEquals(54, 180 * 30 / 100);
      assertEquals(20, 100 / 5);
      assertEquals(13, 143 / 11);

   }

   public void testACOS() {
      StringUtils su = uc.getStrU();

      assertEquals("1.159", su.prettyDouble(Math.acos(0.4), 3));
      assertEquals("1.159", su.prettyDouble(MathUtils.acos(0.4), 3));

      assertEquals("1.104", su.prettyDouble(Math.acos(0.45), 3));
      assertEquals("1.104", su.prettyDouble(MathUtils.acos(0.45), 3));

      assertEquals("0.0", su.prettyDouble(Math.acos(1), 3));
      assertEquals("3.141", su.prettyDouble(Math.acos(-1), 3));
      assertEquals(Double.NaN, Math.acos(2));
      assertEquals("NaN", su.prettyDouble(Math.acos(2), 3));

   }

   public void testacosDemo() {
      StringUtils su = uc.getStrU();

      assertEquals("1.159", su.prettyDouble(Math.acos(0.4), 3));
      assertEquals("1.158", su.prettyDouble(MathUtils.acosApproxDesmos(0.4f), 3));

      assertEquals("1.047", su.prettyDouble(Math.acos(0.5), 3));
      assertEquals("1.046", su.prettyDouble(MathUtils.acosApproxDesmos(0.5f), 3));

   }

   public void testacosNVidia() {
      StringUtils su = uc.getStrU();

      assertEquals("1.159", su.prettyDouble(Math.acos(0.4), 3));
      assertEquals("1.159", su.prettyDouble(MathUtils.acosNVidia(0.4f), 3));

      assertEquals("1.047", su.prettyDouble(Math.acos(0.5), 3));
      assertEquals("1.047", su.prettyDouble(MathUtils.acosNVidia(0.5f), 3));

   }

   public void testASIN() {
      StringUtils su = uc.getStrU();

      assertEquals("0.411", su.prettyDouble(Math.asin(0.4), 3));
      assertEquals("0.411", su.prettyDouble(MathUtils.asin3(0.4), 3));

      assertEquals("23.548", su.prettyDouble(Math.toDegrees(0.411d), 3));

      assertEquals("0.466", su.prettyDouble(Math.asin(0.45), 3));
      assertEquals("0.466", su.prettyDouble(MathUtils.asin3(0.45), 3));

      assertEquals("26.699", su.prettyDouble(Math.toDegrees(0.466d), 3));

      assertEquals("1.570", su.prettyDouble(Math.asin(1), 3));
      assertEquals("-1.570", su.prettyDouble(Math.asin(-1), 3));
      assertEquals(Double.NaN, Math.asin(2));
      assertEquals("NaN", su.prettyDouble(Math.asin(2), 3));

      assertEquals("-1.570", su.prettyDouble(MathUtils.asin(-1), 3));
      assertEquals(Double.NaN, MathUtils.asin(2));
      assertEquals("NaN", su.prettyDouble(MathUtils.asin(2), 3));

      assertEquals("1.536", su.prettyDouble(MathUtils.asin3(1), 3));
      assertEquals("1.566", su.prettyDouble(MathUtils.asin5(1), 3));
      assertEquals("1.569", su.prettyDouble(MathUtils.asin6(1), 3));
      assertEquals("1.570", su.prettyDouble(MathUtils.asin7(1), 3));

   }

   public void testAtan() {
      StringUtils su = uc.getStrU();

      assertEquals("0.785", su.prettyDouble(MathUtils.aTan(1, 1), 3));

      assertEquals("0.785", su.prettyDouble(Math.atan2(1, 1), 3));

      assertEquals("1.047", su.prettyDouble(MathUtils.aTan(2, 1), 3));

      System.out.println(Math.toDegrees(MathUtils.aTan(2, 1)));
      System.out.println(Math.toDegrees(MathUtils.aTan(3, 1)));
      System.out.println(Math.toDegrees(MathUtils.aTan(4, 1)));
      System.out.println(Math.toDegrees(MathUtils.aTan(5, 1)));
      System.out.println(Math.toDegrees(MathUtils.aTan(6, 1)));
      System.out.println(Math.toDegrees(MathUtils.aTan(7, 1)));
      System.out.println(Math.toDegrees(MathUtils.aTan(8, 1)));
      System.out.println(Math.toDegrees(MathUtils.aTan(9, 1)));

      assertEquals("59.999", su.prettyDouble(Math.toDegrees(MathUtils.aTan(2, 1)), 3));
      assertEquals("67.5", su.prettyDouble(Math.toDegrees(MathUtils.aTan(3, 1)), 3));
      assertEquals("72.0", su.prettyDouble(Math.toDegrees(MathUtils.aTan(4, 1)), 3));
      assertEquals("74.999", su.prettyDouble(Math.toDegrees(MathUtils.aTan(5, 1)), 3));

      assertEquals("0.523", su.prettyDouble(MathUtils.aTan(1, 2), 3));

   }

   public void testfastInverseSqrt() {

      System.out.println(MathUtils.fastInverseSqrt(4));
      System.out.println(MathUtils.fastInverseSqrt(8));
      System.out.println(MathUtils.fastInverseSqrt(16));

   }

   public void testPow() {

      //assertEquals(MathPow.pow(8.0, -2), 4.756828460010884);
      assertEquals(MathUtils.pow(8.0, 0.75), 4.756828460010884);
      assertEquals(MathUtils.pow(16.0, 8.0), 4294967296.0);
      assertEquals(MathUtils.pow(32.0, 5.0), 33554432.0);
      assertEquals(MathUtils.pow(11.0, 3.0), 1331.0);
      //real is     0.99391353
      assertEquals(MathUtils.pow(0.24352, 0.004322), 0.9944973974118516);
      assertEquals(MathUtils.powTaylor(0.24352, 2), 0.05930199039999999);
      assertEquals(MathUtils.pow(0.49, 2), 0.0);

   }
}
