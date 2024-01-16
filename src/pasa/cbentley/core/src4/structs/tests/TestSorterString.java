package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.SorterString;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestSorterString extends TestCaseBentley {

   public TestSorterString() {
   }

   public void setupAbstract() {

   }

   public void testBasic() {
      SorterString ss = new SorterString(uc);

      assertEquals(false, ss.isAscending());

      ss.setAscending();

      assertEquals(true, ss.isAscending());
   }
}
