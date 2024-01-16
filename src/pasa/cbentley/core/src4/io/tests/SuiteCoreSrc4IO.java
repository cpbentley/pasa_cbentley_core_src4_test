/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.io.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteCoreSrc4IO extends TestSuite {
   
   public static Test suite() {

      TestSuite suite = new TestSuite("Tests for IO_src4");
      suite.addTestSuite(TestBADataIS.class);
      suite.addTestSuite(TestFileLineReader.class);

      return suite;
   }

}
