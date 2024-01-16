/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.i8n.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteCoreSrc4I8n extends TestSuite {
   
   public static Test suite() {

      TestSuite suite = new TestSuite("Tests for i8n");
      suite.addTestSuite(TestLString.class);

      return suite;
   }

}
