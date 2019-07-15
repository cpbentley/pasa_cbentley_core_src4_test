package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.utils.IOUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestIOUtils extends BentleyTestCase {

   private IOUtils iu;

   public TestIOUtils() {
      super(true);
   }

   public void setupAbstract() {
      iu = new IOUtils(uc);
   }

}
