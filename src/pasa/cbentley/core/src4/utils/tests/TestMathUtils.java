package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.core.src4.utils.MathUtils;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestMathUtils extends BentleyTestCase {
   MathUtils mu = new MathUtils(new UCtx());

   public TestMathUtils() {
      super(true);
   }

   public void setUpMord() {
      // TODO Auto-generated method stub

   }
   
   public void testAtan() {
      StringUtils su = uc.getStrU();
      assertEquals("0.785", su.prettyDouble(MathUtils.aTan(1, 1), 3));
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
