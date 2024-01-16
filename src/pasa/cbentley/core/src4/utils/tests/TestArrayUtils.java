/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.utils.ArrayUtils;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestArrayUtils extends TestCaseBentley {

   private ArrayUtils au = new ArrayUtils(uc);
   private StringUtils su = new StringUtils(uc);

   public TestArrayUtils() {
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
   public void testShiftDown0() {
      String[] ar = new String[]    {"0","1","2","3","4","5",};
      assertEquals("0 1 2 3 4 5", su.getString(ar, " "));
      ArrayUtils.shiftDown(ar, 0, 2, 5, false);
      assertEquals("0 1 2 3 4 5", su.getString(ar, " "));
   }
   
   
   public void testShiftDown() {
      String[] ar = new String[]    {"0","1","2","3","4","5",};
      assertEquals("0 1 2 3 4 5", su.getString(ar, " "));
      ArrayUtils.shiftDown(ar, 1, 2, 5, false);
      assertEquals("0 2 3 4 5 5", su.getString(ar, " "));
      
      ar = new String[]    {"0","1","2","3","4","5",};
      ArrayUtils.shiftDown(ar, 2, 2, 5, true);
      assertEquals("2 3 4 5 null null", su.getString(ar, " "));
   }

   public void testShiftUp() {
      String[] ar = new String[]    {"0","1","2","3","4","5",};
      assertEquals("0 1 2 3 4 5", su.getString(ar, " "));
      ArrayUtils.shiftUp(ar, 1, 2, 5, false);
      assertEquals("0 1 2 2 3 4", su.getString(ar, " "));
      
      ar = new String[]    {"0","1","2","3","4","5",};
      ArrayUtils.shiftUp(ar, 1, 0, 5, true);
      assertEquals("null 0 1 2 3 4", su.getString(ar, " "));
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
