package pasa.cbentley.core.src4.structs;

import junit.framework.Test;
import junit.framework.TestSuite;
import pasa.cbentley.core.src4.structs.listdoublelink.tests.TestLinkedListDouble;
import pasa.cbentley.core.src4.structs.tests.TestBufferObject;
import pasa.cbentley.core.src4.structs.tests.TestBufferString;
import pasa.cbentley.core.src4.structs.tests.TestCircularObjects;
import pasa.cbentley.core.src4.structs.tests.TestFifoQueue;
import pasa.cbentley.core.src4.structs.tests.TestIntBuffer;
import pasa.cbentley.core.src4.structs.tests.TestIntBufferMatrix;
import pasa.cbentley.core.src4.structs.tests.TestIntInterval;
import pasa.cbentley.core.src4.structs.tests.TestIntIntervalRelation;
import pasa.cbentley.core.src4.structs.tests.TestIntIntervals;
import pasa.cbentley.core.src4.structs.tests.TestIntSorter;
import pasa.cbentley.core.src4.structs.tests.TestIntToInts;
import pasa.cbentley.core.src4.structs.tests.TestIntToObjects;
import pasa.cbentley.core.src4.structs.tests.TestIntToStrings;
import pasa.cbentley.core.src4.structs.tests.TestSorterString;

public class SuiteCoreSrc4Structs extends TestSuite {

   public static Test suite() {

      TestSuite suite = new TestSuite("Tests for src4 Structs");
      
      suite.addTestSuite(TestLinkedListDouble.class);

      suite.addTestSuite(TestBufferObject.class);
      suite.addTestSuite(TestBufferString.class);
      suite.addTestSuite(TestCircularObjects.class);
      suite.addTestSuite(TestFifoQueue.class);
      suite.addTestSuite(TestIntBuffer.class);
      suite.addTestSuite(TestIntBufferMatrix.class);
      suite.addTestSuite(TestIntInterval.class);
      suite.addTestSuite(TestIntIntervalRelation.class);
      suite.addTestSuite(TestIntIntervals.class);
      
      suite.addTestSuite(TestIntSorter.class);
      suite.addTestSuite(TestIntToInts.class);
      suite.addTestSuite(TestIntToObjects.class);
      suite.addTestSuite(TestIntToStrings.class);

      suite.addTestSuite(TestSorterString.class);
      
      return suite;
   }
}
