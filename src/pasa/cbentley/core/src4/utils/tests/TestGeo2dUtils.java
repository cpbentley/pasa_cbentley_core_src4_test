/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.utils.Geo2dUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestGeo2dUtils extends TestCaseBentley {
   
   
   Geo2dUtils gu = new Geo2dUtils(uc);

   public TestGeo2dUtils() {
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
   public void testInterSection() {
      int[] t = new int[4];
      int[] v = gu.getIntersection(10, 10, 40, 40, 0, 0, 200, 140, t);
      assertNotNull(v);
      assertEquals(v[0], 10);
      assertEquals(v[1], 10);
      assertEquals(v[2], 40);
      assertEquals(v[3], 40);

      v = gu.getIntersection(-10, -10, 40, 40, 0, 0, 200, 140, t);
      assertNotNull(v);
      assertEquals(v[0], 0);
      assertEquals(v[1], 0);
      assertEquals(v[2], 30);
      assertEquals(v[3], 30);

      v = gu.getIntersection(-10, -10, 5, 40, 0, 0, 200, 140, t);
      assertNull(v);

      v = gu.getIntersection(0, -50, 5, 40, 0, 0, 200, 140, t);
      assertNull(v);

      v = gu.getIntersection(100, 50, 400, 400, 0, 0, 200, 140, t);
      assertNotNull(v);
      assertEquals(v[0], 100);
      assertEquals(v[1], 50);
      assertEquals(v[2], 100);
      assertEquals(v[3], 90);

      v = gu.getIntersection(156, 73, 38, 36, 0, 0, 176, 176, t);
      assertNotNull(v);
      assertEquals(v[0], 156);
      assertEquals(v[1], 73);
      assertEquals(v[2], 20);
      assertEquals(v[3], 36);

      v = gu.getIntersection(156, 156, 38, 36, 0, 0, 176, 176, t);
      assertNotNull(v);
      assertEquals(v[0], 156);
      assertEquals(v[1], 156);
      assertEquals(v[2], 20);
      assertEquals(v[3], 20);

      v = gu.getIntersection(30, 30, 40, 60, 0, 0, 176, 176, t);
      assertNotNull(v);
      assertEquals(v[0], 30);
      assertEquals(v[1], 30);
      assertEquals(v[2], 40);
      assertEquals(v[3], 60);

      v = gu.getIntersection(0, 0, 176, 176, 30, 30, 40, 60, t);
      assertNotNull(v);
      assertEquals(v[0], 30);
      assertEquals(v[1], 30);
      assertEquals(v[2], 40);
      assertEquals(v[3], 60);

      v = gu.getIntersection(0, 0, 176, 176, 10, 10, 176, 176, t);
      assertNotNull(v);
      assertEquals(v[0], 10);
      assertEquals(v[1], 10);
      assertEquals(v[2], 166);
      assertEquals(v[3], 166);
   }

   public void testUnion() {
      int[] t = new int[4];
      //first is fully inside
      int[] v = gu.getUnion(10, 10, 40, 40, 0, 0, 200, 140, t);
      assertNotNull(v);
      assertEquals(v[0], 0);
      assertEquals(v[1], 0);
      assertEquals(v[2], 200);
      assertEquals(v[3], 140);

      //first is fully outside
      v = gu.getUnion(0, 0, 200, 140, 10, 10, 40, 40, t);
      assertNotNull(v);
      assertEquals(v[0], 0);
      assertEquals(v[1], 0);
      assertEquals(v[2], 200);
      assertEquals(v[3], 140);

      v = gu.getUnion(0, 0, 200, 140, -1, -2, 40, 150, t);
      assertEquals(v[0], -1);
      assertEquals(v[1], -2);
      assertEquals(v[2], 201);
      assertEquals(v[3], 150);

      v = gu.getUnion(5, 5, 10, 15, 40, 45, 55, 60, t);
      assertEquals(v[0], 5);
      assertEquals(v[1], 5);
      assertEquals(v[2], 90);
      assertEquals(v[3], 100);

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
