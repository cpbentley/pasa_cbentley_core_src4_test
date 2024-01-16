/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import org.junit.Test;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestColorUtils extends TestCaseBentley {
   ColorUtils uu = new ColorUtils(new UCtx());

   public TestColorUtils() {
   }

   @Test
   public void testGetBlue() throws Exception {
      assertEquals(0, ColorUtils.getBlue(ColorUtils.WEB_olive));
      assertEquals(255, ColorUtils.getBlue(ColorUtils.WEB_azure));
   }

   @Test
   public void testGetGreen() throws Exception {
      assertEquals(128, ColorUtils.getGreen(ColorUtils.WEB_olive));
   }

   @Test
   public void testGetRed() throws Exception {
      assertEquals(128, ColorUtils.getRed(ColorUtils.WEB_olive));
   }
   
   public void testGetHSL() {
        //TODO
   }

   public void setupAbstract() {

   }
}
