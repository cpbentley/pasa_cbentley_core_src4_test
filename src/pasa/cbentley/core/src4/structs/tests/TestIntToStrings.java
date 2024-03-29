/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntToStrings;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIntToStrings extends TestCaseBentley {

   public TestIntToStrings() {
   }

   public void setupAbstract() {

   }

   public void testBasic() {

      IntToStrings its = new IntToStrings(uc);

   }

   public void testEnsureRoom() {

      IntToStrings its = new IntToStrings(uc, 5);

      assertEquals(its.nextempty, 0);
      assertEquals(its.ints.length, 5);

      its.ensureRoomFor(10);

      assertEquals(its.nextempty, 0);

      assertEquals(true, its.ints.length > 10);

   }

   public void testSet() {
      IntToStrings its = new IntToStrings(uc, 5);

      its.setUnsafe(1, "1");

   }
}
