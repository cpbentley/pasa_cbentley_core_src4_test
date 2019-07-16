/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.utils.Geo2dUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestGeo2dUtils extends BentleyTestCase {
   
   
   Geo2dUtils gu = new Geo2dUtils(uc);

   public TestGeo2dUtils() {
      super(true);
   }

   public void testHoleOne2() {

      int x = 0;
      int y = 0;
      int w = 50;
      int h = 25;

      int[] holes = new int[] { 0, 0, 20, 15 };

      int[] ag = gu.getHolesInverse(x, y, w, h, holes);

      assertEquals(ag.length, 8);

      int k = 0;
      assertEquals(ag[k++], 20);
      assertEquals(ag[k++], 0);
      assertEquals(ag[k++], 30);
      assertEquals(ag[k++], 25);

      assertEquals(ag[k++], 0);
      assertEquals(ag[k++], 15);
      assertEquals(ag[k++], 20);
      assertEquals(ag[k++], 10);

   }

   public void testHoleOne() {

      int x = 0;
      int y = 0;
      int w = 50;
      int h = 25;

      int[] holes = new int[] { 15, 5, 15, 15 };

      int[] ag = gu.getHolesInverse(x, y, w, h, holes);

      assertEquals(ag.length, 16);

      int k = 0;
      assertEquals(ag[k++], 0);
      assertEquals(ag[k++], 0);
      assertEquals(ag[k++], 50);
      assertEquals(ag[k++], 5);

      assertEquals(ag[k++], 0);
      assertEquals(ag[k++], 5);
      assertEquals(ag[k++], 15);
      assertEquals(ag[k++], 20);

      assertEquals(ag[k++], 30);
      assertEquals(ag[k++], 5);
      assertEquals(ag[k++], 20);
      assertEquals(ag[k++], 20);

      assertEquals(ag[k++], 15);
      assertEquals(ag[k++], 20);
      assertEquals(ag[k++], 15);
      assertEquals(ag[k++], 5);

   }

   public void testHolesTwo() {

      int x = 0;
      int y = 0;
      int w = 50;
      int h = 10;

      int[] holes = new int[] { 5, 7, 5, 3, 40, 5, 5, 5 };

      int[] ag = gu.getHolesInverse(x, y, w, h, holes);

      //not implemented for several holes so NULL!!
      assertNull(ag);
      //assertEquals(ag.length, 24);

      //      assertEquals(ag[0], 0);
      //      assertEquals(ag[1], 0);
      //      assertEquals(ag[2], 50);
      //      assertEquals(ag[3], 5);

   }

   public void setupAbstract() {
      // TODO Auto-generated method stub

   }
}
