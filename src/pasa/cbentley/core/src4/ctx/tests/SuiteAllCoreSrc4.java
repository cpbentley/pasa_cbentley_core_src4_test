package pasa.cbentley.core.src4.ctx.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import pasa.cbentley.core.src4.event.tests.SuiteCoreSrc4Events;
import pasa.cbentley.core.src4.helpers.tests.SuiteCoreSrc4Helpers;
import pasa.cbentley.core.src4.i8n.tests.SuiteCoreSrc4I8n;
import pasa.cbentley.core.src4.io.tests.SuiteCoreSrc4IO;
import pasa.cbentley.core.src4.logging.tests.SuiteCoreSrc4Logging;
import pasa.cbentley.core.src4.memory.tests.SuiteCoreSrc4Memory;
import pasa.cbentley.core.src4.stator.tests.SuiteCoreSrc4Stator;
import pasa.cbentley.core.src4.strings.tests.SuiteCoreSrc4Strings;
import pasa.cbentley.core.src4.structs.SuiteCoreSrc4Structs;
import pasa.cbentley.core.src4.text.tests.SuiteCoreSrc4Text;
import pasa.cbentley.core.src4.thread.tests.SuiteCoreSrc4Thread;
import pasa.cbentley.core.src4.utils.tests.SuiteCoreSrc4Utils;

public class SuiteAllCoreSrc4  extends TestSuite {

   public static Test suite() {

      TestSuite suite = new TestSuite("Tests for src4");
      
      suite.addTestSuite(TestUCtx.class);
      suite.addTest(SuiteCoreSrc4Events.suite());
      suite.addTest(SuiteCoreSrc4Helpers.suite());
      suite.addTest(SuiteCoreSrc4I8n.suite());
      suite.addTest(SuiteCoreSrc4IO.suite());
      suite.addTest(SuiteCoreSrc4Logging.suite());
      suite.addTest(SuiteCoreSrc4Memory.suite());
      suite.addTest(SuiteCoreSrc4Stator.suite());
      suite.addTest(SuiteCoreSrc4Strings.suite());
      suite.addTest(SuiteCoreSrc4Structs.suite());
      suite.addTest(SuiteCoreSrc4Text.suite());
      suite.addTest(SuiteCoreSrc4Thread.suite());
      suite.addTest(SuiteCoreSrc4Utils.suite());

      
      return suite;
   }
}
