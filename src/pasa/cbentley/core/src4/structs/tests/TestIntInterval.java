package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntInterval;
import pasa.cbentley.testing.engine.TestCaseBentley;

/**
 * {@link IntInterval}
 * @author Charles Bentley
 *
 */
public class TestIntInterval extends TestCaseBentley {

   public void setupAbstract() {

   }

   public void testMerge() {

      IntInterval ii1 = new IntInterval(uc, 10, 5);
      IntInterval ii2 = new IntInterval(uc, 40, 2);

      ii1.merge(ii2);

      assertEquals(10, ii2.getOffset());
      assertEquals(32, ii2.getLen());
   }
}
