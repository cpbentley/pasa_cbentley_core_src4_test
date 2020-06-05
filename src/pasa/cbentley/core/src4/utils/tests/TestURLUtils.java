/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.URLUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestURLUtils extends TestCaseBentley {

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

   public void setupAbstract() {

   }

}
