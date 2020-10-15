package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntInterval;
import pasa.cbentley.core.src4.structs.IntIntervals;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIntIntervals extends TestCaseBentley {

   public void setupAbstract() {

   }

   public void testAddBefore3() {

      IntIntervals ii = new IntIntervals(uc);
      assertEquals(0, ii.getSize());

      IntInterval in = ii.addInterval(100, 5); //10
      assertEquals(1, ii.getSize());
      assertEquals("100,5", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(100, in.getOffset());
      assertEquals(5, in.getLen());

      ii.addInterval(20, 10);
      assertEquals("20,10,100,5", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(2, ii.getSize());

      in = ii.addInterval(10, 5); //10
      assertEquals(10, in.getOffset());
      assertEquals(5, in.getLen());

      assertEquals(3, ii.getSize());
      assertEquals("10,5,20,10,100,5", uc.getIU().debugString(ii.getArrayOffsetLen()));
   }

   public void testAddMix() {
      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(100, 5); //10
      assertEquals("100,5", uc.getIU().debugString(ii.getArrayOffsetLen()));
      ii.addInterval(10, 5); //10
      assertEquals("10,5,100,5", uc.getIU().debugString(ii.getArrayOffsetLen()));
      ii.addInterval(20, 10);
      assertEquals("10,5,20,10,100,5", uc.getIU().debugString(ii.getArrayOffsetLen()));
      ii.addInterval(200, 10);
      assertEquals("10,5,20,10,100,5,200,10", uc.getIU().debugString(ii.getArrayOffsetLen()));
      ii.addInterval(120, 4);
      assertEquals("10,5,20,10,100,5,120,4,200,10", uc.getIU().debugString(ii.getArrayOffsetLen()));
   }

   public void testAddAfter3() {

      IntIntervals ii = new IntIntervals(uc);
      assertEquals(0, ii.getSize());

      ii.addInterval(10, 5); //10
      assertEquals(1, ii.getSize());

      assertEquals("10,5", uc.getIU().debugString(ii.getArrayOffsetLen()));

      ii.addInterval(20, 10);
      assertEquals("10,5,20,10", uc.getIU().debugString(ii.getArrayOffsetLen()));

      ii.addInterval(45, 2);
      assertEquals("10,5,20,10,45,2", uc.getIU().debugString(ii.getArrayOffsetLen()));

      ii.addInterval(55, 2);
      assertEquals("10,5,20,10,45,2,55,2", uc.getIU().debugString(ii.getArrayOffsetLen()));

   }

   public void testAddMergeOver() {
      IntIntervals ii = new IntIntervals(uc);

      ii.addInterval(10, 5);
      ii.addInterval(20, 10);
      assertEquals("10,5,20,10", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(2, ii.getSize());

      ii.addInterval(5, 50);
      assertEquals(1, ii.getSize());
      assertEquals("5,50", uc.getIU().debugString(ii.getArrayOffsetLen()));

   }

   public void testAddMergeOverWithAbove() {
      IntIntervals ii = new IntIntervals(uc);

      ii.addInterval(10, 5);
      ii.addInterval(20, 10);
      ii.addInterval(100, 10);

      assertEquals("10,5,20,10,100,10", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(3, ii.getSize());

      ii.addInterval(5, 50);
      assertEquals(2, ii.getSize());
      assertEquals("5,50,100,10", uc.getIU().debugString(ii.getArrayOffsetLen()));

   }

   public void testAddMergeBetween() {
      IntIntervals ii = new IntIntervals(uc);

      ii.addInterval(10, 5);
      ii.addInterval(20, 10);
      assertEquals("10,5,20,10", uc.getIU().debugString(ii.getArrayOffsetLen()));

      ii.addInterval(15, 5);

      assertEquals("10,20", uc.getIU().debugString(ii.getArrayOffsetLen()));

   }

   public void testAddInside() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15

      ii.addInterval(11, 2);

      assertEquals("10,5", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(1, ii.getSize());

   }

   public void testAddInside3() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15
      ii.addInterval(40, 10);
      ii.addInterval(60, 10);
      assertEquals(3, ii.getSize());

      ii.addInterval(41, 2);
      assertEquals(3, ii.getSize());
      ii.addInterval(40, 2);
      assertEquals(3, ii.getSize());

      ii.addInterval(61, 2);
      assertEquals(3, ii.getSize());

      assertEquals("10,5,40,10,60,10", uc.getIU().debugString(ii.getArrayOffsetLen()));

      ii.addInterval(60, 2);
      assertEquals(3, ii.getSize());

   }

   public void testAddInsideExtentRight1() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15

      ii.addInterval(11, 8); //11-19

      assertEquals("10,9", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(1, ii.getSize());

   }

   public void testAddInsideExtentRightWithMerge() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15
      ii.addInterval(19, 1); 
      
      ii.addInterval(11, 8); //11-19

      assertEquals("10,10", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(1, ii.getSize());

   }
   
   public void testAddInsideExtentRight1Middle() {

      IntIntervals ii = new IntIntervals(uc);

      ii.addInterval(1, 2); 
      ii.addInterval(100, 2); 

      ii.addInterval(10, 5); //10-15
      ii.addInterval(11, 8); //11-19

      assertEquals("1,2,10,9,100,2", uc.getIU().debugString(ii.getArrayOffsetLen()));

   }

   public void testAddInsideExtentRight2() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15

      ii.addInterval(10, 8); //11-19

      assertEquals("10,8", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(1, ii.getSize());

   }

   public void testAddInsideExtentLeft() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15

      ii.addInterval(5, 8); //11-19

      assertEquals("5,10", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(1, ii.getSize());

   }
}
