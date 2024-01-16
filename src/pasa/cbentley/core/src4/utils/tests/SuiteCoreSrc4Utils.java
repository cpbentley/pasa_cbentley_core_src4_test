/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteCoreSrc4Utils extends TestSuite {
   
   public static Test suite() {

      TestSuite suite = new TestSuite("Tests for utils_src4");
      suite.addTestSuite(TestArrayUtils.class);
      suite.addTestSuite(TestBigDecimalUtils.class);
      suite.addTestSuite(TestBitUtils.class);
      suite.addTestSuite(TestCharUtils.class);
      suite.addTestSuite(TestColorUtils.class);
      suite.addTestSuite(TestDateUtils.class);
      suite.addTestSuite(TestGeo2dUtils.class);
      suite.addTestSuite(TestIntUtils.class);
      suite.addTestSuite(TestIOUtils.class);
      suite.addTestSuite(TestLongUtils.class);
      suite.addTestSuite(TestMathUtils.class);
      suite.addTestSuite(TestShortUtils.class);
      suite.addTestSuite(TestStringUtils.class);
      suite.addTestSuite(TestURLUtils.class);

      return suite;
   }

}
