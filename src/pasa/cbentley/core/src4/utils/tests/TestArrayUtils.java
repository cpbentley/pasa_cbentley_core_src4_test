/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.utils.ArrayUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestArrayUtils extends TestCaseBentley {

   private ArrayUtils au = new ArrayUtils(uc);

   public TestArrayUtils() {
      super(true);
   }

   public void setupAbstract() {

   }

   public void testGetLastNotNullIndexBorder() {
      Object[] os = new Object[0];

      assertEquals(-1, ArrayUtils.getLastNotNullIndex(os));

      os = new Object[1];

      assertEquals(-1, ArrayUtils.getLastNotNullIndex(os));

      os = new Object[1];
      os[0] = "string";

      assertEquals(0, ArrayUtils.getLastNotNullIndex(os));

   }

   public void testGetLastNotNullIndex() {
      Object[] os = new Object[] { "asd", null, "asd", null };

      assertEquals(2, ArrayUtils.getLastNotNullIndex(os));

      os = new Object[] { "asd", null, "asd", null, "asdsad" };

      assertEquals(4, ArrayUtils.getLastNotNullIndex(os));

   }
   
   public void getTrim() {
      Object[] os = new Object[] { "asd", null, "asd", null };
      
      Object[] trimmed = uc.getAU().getTrim(os);
      
      assertEquals(3, trimmed.length);
      
   }
}
