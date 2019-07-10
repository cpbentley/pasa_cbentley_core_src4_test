package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.URLUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestURLUtils extends BentleyTestCase {

   public TestURLUtils() {
      super(true);
   }

   URLUtils uu = new URLUtils(new UCtx());

   public void testGetURLRootFrom() throws Exception {

      assertEquals("http://www.something.com/images/", uu.getURLRootFrom("http://www.something.com/images/01.jpg"));
      assertEquals("http://www.something.com/images/", uu.getURLRootFrom("http://www.something.com/images/01."));
      assertEquals("http://www.something.com/images/", uu.getURLRootFrom("http://www.something.com/images/01"));
      assertEquals("http://www.something.com/images/", uu.getURLRootFrom("http://www.something.com/images/0"));
      assertEquals("http://www.something.com/images/", uu.getURLRootFrom("http://www.something.com/images/"));

   }

   public void setUpMord() {

   }

}
