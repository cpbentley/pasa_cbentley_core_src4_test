package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.stator.StatorReader;

/**
 * To test readings, use {@link TestStatorWriter}
 * 
 * @author Charles Bentley
 *
 */
public class TestStatorReader extends TestCaseStator {


   public void testBasic() {

      StatorReader reader = stator.getReader(TYPE_0_MASTER);

      assertEquals(null, reader);

   }

}
