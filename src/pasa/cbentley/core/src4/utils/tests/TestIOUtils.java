/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.utils.IOUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIOUtils extends TestCaseBentley {

   private IOUtils iu;

   public TestIOUtils() {
      super(true);
   }

   public void setupAbstract() {
      iu = new IOUtils(uc);
   }

}
