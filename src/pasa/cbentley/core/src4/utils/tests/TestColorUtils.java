/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import org.junit.Test;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestColorUtils extends BentleyTestCase {
   ColorUtils uu = new ColorUtils(new UCtx());

   public TestColorUtils() {
      super(true);
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
