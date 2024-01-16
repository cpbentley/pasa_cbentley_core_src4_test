/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.logging.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteCoreSrc4Logging extends TestSuite {
   
   public static Test suite() {

      TestSuite suite = new TestSuite("Tests for Logging");
      suite.addTestSuite(TestDLogConfig.class);
      suite.addTestSuite(TestDLogEntry.class);

      return suite;
   }

}
