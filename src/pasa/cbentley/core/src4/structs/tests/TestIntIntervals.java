package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntInterval;
import pasa.cbentley.core.src4.structs.IntIntervals;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIntIntervals extends TestCaseBentley {

   public void setupAbstract() {

   }

   public void testAddAdjacentLeft() {
      IntIntervals ii = new IntIntervals(uc);
      ii.addIntervalOffset(20, 35);

      assertEquals(1, ii.getSize());

      ii.addIntervalOffset(15, 20);

      assertEquals("15,35", ii.toStringOffsets());

      ii.setPayLoadCheck(true);

      IntInterval i = new IntInterval(uc);
      i.setPayload("pay");
      i.setOffsets(10, 15);

      ii.addInterval(i);

      assertEquals("10,15-15,35", ii.toStringOffsets());

      assertEquals(2, ii.getSize());

   }

   public void testAddAdjacentRight() {
      IntIntervals ii = new IntIntervals(uc);
      ii.addIntervalOffset(10, 15); //10-15

      assertEquals(1, ii.getSize());

      ii.addIntervalOffset(15, 20); //15-20

      assertEquals("10,20", ii.toStringOffsets());

      ii.setPayLoadCheck(true);

      IntInterval i = new IntInterval(uc);
      i.setPayload("pay");
      i.setOffsets(21, 25);

      ii.addInterval(i);

      assertEquals("10,20-21,25", ii.toStringOffsets());

      assertEquals(2, ii.getSize());

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
      assertEquals("10,5-40,10-60,10", ii.toStringOffsetLengthPairs());

      ii.addInterval(41, 2);
      assertEquals(3, ii.getSize());
      assertEquals("10,5-40,10-60,10", ii.toStringOffsetLengthPairs());

      ii.addInterval(40, 2);
      assertEquals(3, ii.getSize());
      assertEquals("10,5-40,10-60,10", ii.toStringOffsetLengthPairs());

      ii.addInterval(61, 2);
      assertEquals(3, ii.getSize());

      assertEquals("10,5,40,10,60,10", uc.getIU().debugString(ii.getArrayOffsetLen()));

      ii.addInterval(60, 2);
      assertEquals(3, ii.getSize());

   }

   public void testAddInsideExtentLeft() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15

      ii.addInterval(5, 8); //11-19

      assertEquals("5,10", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(1, ii.getSize());

   }

   public void testAddInsideExtentRight1() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15

      ii.addInterval(11, 8); //11-19

      assertEquals("10,19", ii.toStringOffsets());
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

   public void testAddInsideExtentRightWithMerge() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(10, 5); //10-15
      ii.addInterval(19, 1); //19-20
      assertEquals("10,15-19,20", ii.toStringOffsets());
      assertEquals(2, ii.getSize());

      ii.addInterval(11, 8); //11-19 creates a full merge

      assertEquals("10,10", uc.getIU().debugString(ii.getArrayOffsetLen()));
      assertEquals(1, ii.getSize());

   }

   public void testAddInsideExtentRightWithMergeExtremities() {

      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(0, 3); //10-15
      ii.addInterval(10, 5); //10-15
      ii.addInterval(19, 1); //19-20
      ii.addInterval(30, 10); //19-20

      assertEquals("0,3-10,15-19,20-30,40", ii.toStringOffsets());
      assertEquals(4, ii.getSize());

      ii.addInterval(11, 8); //11-19 creates a full merge

      assertEquals("0,3-10,20-30,40", ii.toStringOffsets());
      assertEquals(3, ii.getSize());

   }

   public void testAddInsideWithPayload() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addInterval(new IntInterval(uc, 0, 8, "payload1"));
      assertEquals(0, ii.getInterval(0).getOffset());
      assertEquals(8, ii.getInterval(0).getLen());
      assertEquals("payload1", ii.getInterval(0).getPayload());

      ii.addInterval(new IntInterval(uc, 2, 3, "payload2"));

      //#debug
      toDLog().pTest("", ii, TestIntIntervals.class, "testAddInsideWithPayload", LVL_05_FINE, false);

      assertEquals(3, ii.getSize());

      assertEquals(0, ii.getInterval(0).getOffset());
      assertEquals(2, ii.getInterval(0).getLen());
      assertEquals("payload1", ii.getInterval(0).getPayload());

      assertEquals(2, ii.getInterval(1).getOffset());
      assertEquals(3, ii.getInterval(1).getLen());
      assertEquals("payload2", ii.getInterval(1).getPayload());

      IntInterval i3 = ii.getInterval(2);

      assertEquals(5, i3.getOffset());
      assertEquals(3, i3.getLen());
      assertEquals("payload1", i3.getPayload());

      assertEquals(3, ii.getSize());

   }

   public void testAddInterval() {

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

      ii = new IntIntervals(uc);

      ii.addInterval(10, 5);
      ii.addInterval(20, 10);
      ii.addInterval(5, 2);

      assertEquals("5,7-10,15-20,30", ii.toStringOffsets());
      assertEquals(3, ii.getSize());

      ii.addInterval(5, 50);
      assertEquals("5,50", uc.getIU().debugString(ii.getArrayOffsetLen()));

      assertEquals(1, ii.getSize());

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

   public void testAddMergeOverWithExtremities() {
      IntIntervals ii = new IntIntervals(uc);

      ii.addIntervalOffset(10, 15);
      ii.addIntervalOffset(20, 25);
      ii.addIntervalOffset(30, 35);
      ii.addIntervalOffset(40, 45);

      assertEquals(4, ii.getSize());

      ii.addIntervalOffset(18, 37);

      assertEquals("10,15-18,37-40,45", ii.toStringOffsets());

      assertEquals(3, ii.getSize());

   }

   public void testAddMergeOverWithExtremitiesAdjacent() {
      IntIntervals ii = new IntIntervals(uc);

      ii.addIntervalOffset(10, 15);
      ii.addIntervalOffset(20, 25);
      ii.addIntervalOffset(30, 35);
      ii.addIntervalOffset(40, 45);
      ii.addIntervalOffset(50, 55);

      assertEquals(5, ii.getSize());

      ii.addIntervalOffset(18, 40);

      assertEquals("10,15-18,45-50,55", ii.toStringOffsets());

      assertEquals(3, ii.getSize());

   }

   public void testAddMergeOverWithExtremitiesInterRight() {
      IntIntervals ii = new IntIntervals(uc);

      ii.addIntervalOffset(10, 15);
      ii.addIntervalOffset(20, 25);
      ii.addIntervalOffset(30, 35);
      ii.addIntervalOffset(40, 45);
      ii.addIntervalOffset(50, 55);

      assertEquals(5, ii.getSize());

      ii.addIntervalOffset(18, 41);

      assertEquals("10,15-18,45-50,55", ii.toStringOffsets());

      assertEquals(3, ii.getSize());

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

   public void testAddOutsideWithPayload() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);
      IntInterval i1 = new IntInterval(uc, 3, 4, "payload1");
      IntInterval i2 = new IntInterval(uc, 10, 8, "payload2");
      IntInterval i3 = new IntInterval(uc, 20, 8, "payload3");

      ii.addInterval(i1);
      ii.addInterval(i2);
      ii.addInterval(i3);

      assertEquals(3, ii.getSize());

      assertEquals(3, ii.getBounds().getOffset());
      assertEquals(28, ii.getBounds().getOffsetEnd());

      IntInterval iv = new IntInterval(uc, 2, 50, "over");

      ii.addInterval(iv);

      assertEquals(1, ii.getSize());

      assertEquals("2,50", ii.toStringOffsetLengthPairs());

      IntInterval iv2 = ii.getInterval(0);

      assertEquals("over", iv2.getPayload());

      assertEquals(2, ii.getBounds().getOffset());
      assertEquals(52, ii.getBounds().getOffsetEnd());

   }

   public void testAddOverWithPayloadMix() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      IntInterval i1 = new IntInterval(uc, 3, 4, "payload1");
      IntInterval i2 = new IntInterval(uc, 10, 8, "over");
      IntInterval i3 = new IntInterval(uc, 20, 8, "payload3");

      ii.addInterval(i1);
      ii.addInterval(i2);
      ii.addInterval(i3);

      IntInterval iv = new IntInterval(uc, 2, 50, "over");

      ii.addInterval(iv);

      assertEquals(1, ii.getSize());

   }

   public void testAddSame() {
      IntIntervals ii = new IntIntervals(uc);

      ii.addInterval(10, 5); //10-15
      ii.addInterval(10, 5); //10-15

      assertEquals(1, ii.getSize());

   }

   public void testAdjacentLeftOverRightPayloadDifferent() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addIntervalOffset(0, 35, "main");

      ii.addIntervalOffset(3, 5, "one");

      assertEquals(3, ii.getSize());

      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

      //adjacent to the left and over the right
      ii.addIntervalOffset(5, 8, "two");

      assertEquals(4, ii.getSize());
      assertEquals("[0,2] [3,4] [5,7] [8,34]", ii.toStringOffsetBracket());

      assertEquals("main", ii.getInterval(0).getPayload());
      assertEquals("one", ii.getInterval(1).getPayload());
      assertEquals("two", ii.getInterval(2).getPayload());
      assertEquals("main", ii.getInterval(3).getPayload());
   }
   
   public void testSameLeftPayloadDifferent() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addIntervalOffset(0, 35, "main");

      ii.addIntervalOffset(3, 5, "one");

      assertEquals(3, ii.getSize());

      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

      //adjacent to the left and over the right
      ii.addIntervalOffset(3, 5, "two");

      assertEquals(3, ii.getSize());
      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

      assertEquals("main", ii.getInterval(0).getPayload());
      assertEquals("two", ii.getInterval(1).getPayload());
      assertEquals("main", ii.getInterval(2).getPayload());
   }

   public void testSameRightPayloadDifferent() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addIntervalOffset(0, 35, "main");

      ii.addIntervalOffset(3, 5, "one");

      assertEquals(3, ii.getSize());

      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

      //adjacent to the left and over the right
      ii.addIntervalOffset(5, 35, "two");

      assertEquals(3, ii.getSize());
      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

      assertEquals("main", ii.getInterval(0).getPayload());
      assertEquals("one", ii.getInterval(1).getPayload());
      assertEquals("two", ii.getInterval(2).getPayload());
   }

   
   public void testAdjacentLeftOverRightPayloadSameLeft() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addIntervalOffset(0, 35, "main");

      ii.addIntervalOffset(3, 5, "one");

      assertEquals(3, ii.getSize());

      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

      //adjacent to the left and over the right
      ii.addIntervalOffset(5, 8, "one");

      assertEquals(3, ii.getSize());
      assertEquals("[0,2] [3,7] [8,34]", ii.toStringOffsetBracket());

   }

   public void testAdjacentLeftOverRightPayloadSameRight() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addIntervalOffset(0, 35, "main");

      ii.addIntervalOffset(3, 5, "one");

      assertEquals(3, ii.getSize());

      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

      //adjacent to the left and over the right
      ii.addIntervalOffset(5, 8, "main");

      assertEquals(3, ii.getSize());
      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

   }

   public void testGetIntersection() {
      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(100, 5); //10
      ii.addInterval(10, 5); //10
      ii.addInterval(20, 10);
      ii.addInterval(200, 10);
      ii.addInterval(120, 4);

      assertEquals("10,15-20,30-100,105-120,124-200,210", ii.toStringOffsets());
      assertEquals(5, ii.getSize());

      IntInterval[] inter = ii.getIntersection(8, 5);
      assertEquals(1, inter.length);
      assertEquals(10, inter[0].getOffset());

      inter = ii.getIntersection(8, 55);
      assertEquals(2, inter.length);
      assertEquals(10, inter[0].getOffset());
      assertEquals(20, inter[1].getOffset());

      inter = ii.getIntersection(90, 255);
      assertEquals(3, inter.length);
      assertEquals(100, inter[0].getOffset());
      assertEquals(120, inter[1].getOffset());
      assertEquals(200, inter[2].getOffset());
   }

   public void testGetIntervalIntersectIntersectIndex() {
      IntIntervals ii = new IntIntervals(uc);
      ii.addInterval(100, 5); //10
      ii.addInterval(10, 5); //10
      ii.addInterval(20, 10);
      ii.addInterval(200, 10);
      ii.addInterval(120, 4);

      assertEquals(null, ii.getIntervalIntersect(0));
      assertEquals(null, ii.getIntervalIntersect(-1));
      assertEquals(null, ii.getIntervalIntersect(1000));

      assertEquals(100, ii.getIntervalIntersect(101).getOffset());

      assertEquals(10, ii.getIntervalIntersect(12).getOffset());

      assertEquals(20, ii.getIntervalIntersect(22).getOffset());

      assertEquals(200, ii.getIntervalIntersect(202).getOffset());

      assertEquals(120, ii.getIntervalIntersect(122).getOffset());

   }

   public void testIntersectLeftIntersectRightPayloadDifferent() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addIntervalOffset(0, 35, "main");

      ii.addIntervalOffset(3, 10, "one");

      assertEquals(3, ii.getSize());

      assertEquals("[0,2] [3,9] [10,34]", ii.toStringOffsetBracket());

      //adjacent to the left and over the right
      ii.addIntervalOffset(5, 12, "two");

      assertEquals(4, ii.getSize());
      assertEquals("[0,2] [3,4] [5,11] [12,34]", ii.toStringOffsetBracket());

   }

   public void testIntersectLeftIntersectRightPayloadSameLeft() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addIntervalOffset(0, 35, "main");

      ii.addIntervalOffset(3, 10, "one");

      assertEquals(3, ii.getSize());

      assertEquals("[0,2] [3,9] [10,34]", ii.toStringOffsetBracket());

      //adjacent to the left and over the right
      ii.addIntervalOffset(5, 12, "one");

      assertEquals(3, ii.getSize());
      assertEquals("[0,2] [3,11] [12,34]", ii.toStringOffsetBracket());

   }

   public void testIntersectLeftIntersectRightPayloadSameRight() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      ii.addIntervalOffset(0, 35, "main");

      ii.addIntervalOffset(3, 10, "one");

      assertEquals(3, ii.getSize());

      assertEquals("[0,2] [3,9] [10,34]", ii.toStringOffsetBracket());

      //adjacent to the left and over the right
      ii.addIntervalOffset(5, 12, "main");

      assertEquals(3, ii.getSize());
      assertEquals("[0,2] [3,4] [5,34]", ii.toStringOffsetBracket());

   }

   public void testMergeBetween2Adjacents() {
      IntIntervals ii = new IntIntervals(uc);

      ii.addInterval(10, 5);
      ii.addInterval(20, 10);
      assertEquals("10,15-20,30", ii.toStringOffsets());

      assertEquals(2, ii.getSize());

      ii.addInterval(15, 5);

      assertEquals(1, ii.getSize());

      assertEquals("10,20", uc.getIU().debugString(ii.getArrayOffsetLen()));

      ii.clear();
      assertEquals(0, ii.getSize());

      ii.addInterval(10, 5, "10-15");
      ii.addInterval(20, 10, "20-30");
      ii.addInterval(40, 10, "40-50");

      assertEquals(3, ii.getSize());

      assertEquals(false, ii.isPayLoadCheck());

      ii.addInterval(15, 5, "15-20");

      IntInterval i1 = ii.getInterval(0);
      assertEquals(10, i1.getOffset());
      assertEquals(30, i1.getOffsetEnd());
      assertEquals("15-20", i1.getPayload()); //the payload of the merging interval is used for all
      //which payload survives, the new payload overwrites on any merge

      assertEquals(2, ii.getSize());

   }

   public void testMergeBetween2AdjacentsPayloads() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      IntInterval i1 = new IntInterval(uc);
      i1.setOffsets(10, 15);
      i1.setPayload("i1");
      ii.addInterval(i1);

      IntInterval i2 = new IntInterval(uc);
      i2.setOffsets(20, 30);
      i2.setPayload("i1");

      ii.addInterval(i2);

      assertEquals("10,15-20,30", ii.toStringOffsets());

      assertEquals(2, ii.getSize());

      IntInterval i3 = new IntInterval(uc);
      i3.setOffsets(15, 20);
      i3.setPayload("i3");

      ii.addInterval(i3);

      assertEquals(3, ii.getSize());

      assertEquals("10,15-15,20-20,30", ii.toStringOffsets());

   }

   public void testMergeBetween2AdjacentsPayloads1MergeLeft() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      IntInterval i1 = new IntInterval(uc);
      i1.setOffsets(10, 15);
      i1.setPayload("samepay");
      ii.addInterval(i1);

      IntInterval i2 = new IntInterval(uc);
      i2.setOffsets(20, 30);
      i2.setPayload("i2");

      ii.addInterval(i2);

      assertEquals("10,15-20,30", ii.toStringOffsets());

      assertEquals(2, ii.getSize());

      IntInterval i3 = new IntInterval(uc);
      i3.setOffsets(15, 20);
      i3.setPayload("samepay");

      ii.addInterval(i3);

      assertEquals(2, ii.getSize());

      assertEquals("10,20-20,30", ii.toStringOffsets());
      assertEquals("samepay", ii.getInterval(0).getPayload());
      assertEquals("i2", ii.getInterval(1).getPayload());

   }

   public void testMergeBetween2AdjacentsPayloads1MergeRight() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      IntInterval i1 = new IntInterval(uc);
      i1.setOffsets(10, 15);
      i1.setPayload("i1");
      ii.addInterval(i1);

      IntInterval i2 = new IntInterval(uc);
      i2.setOffsets(20, 30);
      i2.setPayload("samepay");

      ii.addInterval(i2);

      assertEquals("10,15-20,30", ii.toStringOffsets());

      assertEquals(2, ii.getSize());

      IntInterval i3 = new IntInterval(uc);
      i3.setOffsets(15, 20);
      i3.setPayload("samepay");

      ii.addInterval(i3);

      assertEquals("10,15-15,30", ii.toStringOffsets());
      assertEquals(2, ii.getSize());

   }

   public void testMergeBetween2AdjacentsPayloads1MergeRightExtremities() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      IntInterval i57 = new IntInterval(uc);
      i57.setOffsets(5, 7);
      i57.setPayload("pay57");

      ii.addInterval(i57);

      IntInterval i1 = new IntInterval(uc);
      i1.setOffsets(10, 15);
      i1.setPayload("pay1");
      ii.addInterval(i1);

      IntInterval i2 = new IntInterval(uc);
      i2.setOffsets(20, 30);
      i2.setPayload("samepay");

      ii.addInterval(i2);

      assertEquals("5,7-10,15-20,30", ii.toStringOffsets());

      IntInterval i50 = new IntInterval(uc);
      i50.setOffsets(50, 60);
      i50.setPayload("pay50");
      ii.addInterval(i50);

      assertEquals("5,7-10,15-20,30-50,60", ii.toStringOffsets());

      IntInterval i3 = new IntInterval(uc);
      i3.setOffsets(15, 20);
      i3.setPayload("samepay");

      ii.addInterval(i3);

      assertEquals("5,7-10,15-15,30-50,60", ii.toStringOffsets());
      assertEquals(4, ii.getSize());

      IntInterval[] intersection = ii.getIntersection(12, 50);
      assertEquals(3, intersection.length);
      assertEquals(10, intersection[0].getOffset());
      assertEquals(15, intersection[1].getOffset());
      assertEquals(50, intersection[2].getOffset());

   }

   /**
    * Payloads changes everything
    */
   public void testMergeComplex1() {
      IntIntervals ii = new IntIntervals(uc);

      IntInterval i57 = new IntInterval(uc);
      i57.setOffsets(5, 7);
      i57.setPayload("pay5");

      ii.addInterval(i57);

      IntInterval i1 = new IntInterval(uc);
      i1.setOffsets(10, 15);
      i1.setPayload("pay10");
      ii.addInterval(i1);

      IntInterval i2 = new IntInterval(uc);
      i2.setOffsets(20, 30);
      i2.setPayload("pay20");

      ii.addInterval(i2);

      assertEquals("5,7-10,15-20,30", ii.toStringOffsets());

      IntInterval i50 = new IntInterval(uc);
      i50.setOffsets(50, 60);
      i50.setPayload("pay50");
      ii.addInterval(i50);

      assertEquals("5,7-10,15-20,30-50,60", ii.toStringOffsets());

      IntInterval i17 = new IntInterval(uc);
      i17.setOffsets(17, 18);
      i17.setPayload("payi");

      ii.addInterval(i17);
      assertEquals("5,7-10,15-17,18-20,30-50,60", ii.toStringOffsets());

      //now add a complex merge
      IntInterval i3 = new IntInterval(uc);
      i3.setOffsets(10, 25);
      i3.setPayload("pay3");

      ii.addInterval(i3);

      assertEquals("[5,6] [10,29] [50,59]", ii.toStringOffsetBracket());
      assertEquals(3, ii.getSize());

   }

   public void testMergeComplex1Payloads() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      IntInterval i57 = new IntInterval(uc);
      i57.setOffsets(5, 7);
      i57.setPayload("pay5");

      ii.addInterval(i57);

      IntInterval i1 = new IntInterval(uc);
      i1.setOffsets(10, 15);
      i1.setPayload("pay10");
      ii.addInterval(i1);

      IntInterval i2 = new IntInterval(uc);
      i2.setOffsets(20, 30);
      i2.setPayload("pay20");

      ii.addInterval(i2);

      assertEquals("5,7-10,15-20,30", ii.toStringOffsets());

      IntInterval i50 = new IntInterval(uc);
      i50.setOffsets(50, 60);
      i50.setPayload("pay50");
      ii.addInterval(i50);

      assertEquals("5,7-10,15-20,30-50,60", ii.toStringOffsets());

      IntInterval i17 = new IntInterval(uc);
      i17.setOffsets(17, 18);
      i17.setPayload("payi");

      ii.addInterval(i17);
      assertEquals("5,7-10,15-17,18-20,30-50,60", ii.toStringOffsets());

      //now add a complex merge
      IntInterval i3 = new IntInterval(uc);
      i3.setOffsets(10, 25);
      i3.setPayload("pay3");

      ii.addInterval(i3);

      assertEquals("[5,6-pay5] [10,24-pay3] [25,29-pay20] [50,59-pay50]", ii.toStringOffsetBracketPayload());
      assertEquals(4, ii.getSize());

   }

   public void testMergeComplex2Payloads() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      IntInterval i57 = new IntInterval(uc);
      i57.setOffsets(5, 7);
      i57.setPayload("pay5");

      ii.addInterval(i57);

      IntInterval i1 = new IntInterval(uc);
      i1.setOffsets(10, 15);
      i1.setPayload("pay10");
      ii.addInterval(i1);

      IntInterval i2 = new IntInterval(uc);
      i2.setOffsets(20, 30);
      i2.setPayload("samepay");

      ii.addInterval(i2);

      assertEquals("5,7-10,15-20,30", ii.toStringOffsets());

      IntInterval i50 = new IntInterval(uc);
      i50.setOffsets(50, 60);
      i50.setPayload("pay50");
      ii.addInterval(i50);

      assertEquals("5,7-10,15-20,30-50,60", ii.toStringOffsets());

      IntInterval i17 = new IntInterval(uc);
      i17.setOffsets(17, 18);
      i17.setPayload("payi");
      ii.addInterval(i17);

      IntInterval i18 = new IntInterval(uc);
      i18.setOffsets(18, 20);
      i18.setPayload("pay18");
      ii.addInterval(i18);

      assertEquals("[5,6] [10,14] [17,17] [18,19] [20,29] [50,59]", ii.toStringOffsetBracket());

      //now add a complex merge
      IntInterval i3 = new IntInterval(uc);
      i3.setOffsets(11, 20);
      i3.setPayload("samepay");
      ii.addInterval(i3);

      assertEquals("[5,6] [10,10] [11,29] [50,59]", ii.toStringOffsetBracket());

   }

   public void testMergeDoubleIntersectPayloads() {
      IntIntervals ii = new IntIntervals(uc);
      ii.setPayLoadCheck(true);

      IntInterval i57 = new IntInterval(uc);
      i57.setOffsets(5, 7);
      i57.setPayload("pay5");

      ii.addInterval(i57);

      IntInterval i1 = new IntInterval(uc);
      i1.setOffsets(10, 15);
      i1.setPayload("pay10");
      ii.addInterval(i1);

      IntInterval i2 = new IntInterval(uc);
      i2.setOffsets(20, 30);
      i2.setPayload("pay20");
      ii.addInterval(i2);

      IntInterval i50 = new IntInterval(uc);
      i50.setOffsets(50, 60);
      i50.setPayload("pay50");
      ii.addInterval(i50);

      IntInterval i3 = new IntInterval(uc);
      i3.setOffsets(12, 24);
      i3.setPayload("pay12");
      ii.addInterval(i3);

      assertEquals("[5,6] [10,11] [12,23] [24,29] [50,59]", ii.toStringOffsetBracket());

   }
}
