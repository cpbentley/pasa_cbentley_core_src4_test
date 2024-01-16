/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.logging.tests;

import pasa.cbentley.core.src4.logging.DLogEntry;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestDLogEntry extends TestCaseBentley {

   public TestDLogEntry() {
      
   }
   public void setupAbstract() {
      
   }

   public void testBasic() {
      
      DLogEntry e = new DLogEntry();
      
      e.setLevel(1);
      
      assertEquals(1, e.getLevel());
   }
}
