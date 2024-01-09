package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntInterval;
import pasa.cbentley.core.src4.structs.IntIntervalRelation;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIntIntervalRelation extends TestCaseBentley {

   public void setupAbstract() {

   }
   public void testClose() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(40, 50);
      two.setOffsets(30, 50);
     
      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(40, iir.getIntervalIntersect().getOffset()); 
      assertEquals(50, iir.getIntervalIntersect().getOffsetEnd()); 
 
      assertEquals(false, iir.isOneFirst());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(true, iir.isOneContainedInTwo());
      
      iir = two.getRelation(one);

      assertEquals(40, iir.getIntervalIntersect().getOffset()); 
      assertEquals(50, iir.getIntervalIntersect().getOffsetEnd()); 
 
      assertEquals(true, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());

   }
   public void testContainedBijection() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(40, 50);
      two.setOffsets(41, 44);
      
      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isOneFirst());
      assertEquals(true, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());

      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isAdjacent());
      assertEquals(41, iir.getIntervalIntersect().getOffset()); 
      assertEquals(44, iir.getIntervalIntersect().getOffsetEnd()); 
    

      iir = two.getRelation(one);
     
      assertEquals(true, iir.isTwoFirst());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(true, iir.isOneContainedInTwo());

      assertEquals(false, iir.isOneFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isAdjacent());
      
      assertEquals(41, iir.getIntervalIntersect().getOffset()); 
      assertEquals(44, iir.getIntervalIntersect().getOffsetEnd()); 
    
      
      
   }
   
   public void testSameStartOffset() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(40, 50);
      two.setOffsets(40, 42);
      
      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isSameOffsetStart());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(true, iir.isTwoContainedInOne());

      assertEquals(false, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isAdjacent());


      iir = two.getRelation(one);

      assertEquals(true, iir.isSameOffsetStart());
      assertEquals(true, iir.isOneContainedInTwo());
      assertEquals(false, iir.isTwoContainedInOne());

      assertEquals(false, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isAdjacent());
      
   }
   
   public void testSameEndOffset() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(40, 50);
      two.setOffsets(48, 50);
      
      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isSameOffsetEnd());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(true, iir.isTwoContainedInOne());
      assertEquals(true, iir.isOneFirst());

      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isAdjacent());


      iir = two.getRelation(one); //two is one and one is two

      assertEquals(true, iir.isSameOffsetEnd());
      assertEquals(true, iir.isOneContainedInTwo());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(true, iir.isTwoFirst());

      assertEquals(false, iir.isOneFirst());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isAdjacent());
      
   }
   
   public void test01NoRelation() {

      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(5, 8);
      two.setOffsets(15, 18);

      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(true, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(false, iir.isAdjacent());

      assertEquals(null, iir.getIntervalIntersect());

      assertEquals(5, iir.getIntersectComplementLeft().getOffset()); //interval between
      assertEquals(10, iir.getIntersectComplementLeft().getLen()); //interval between
      assertEquals(null, iir.getIntersectComplementRight());
   }

   public void test02Adjacent() {

      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(5, 8);
      two.setOffsets(8, 18);

      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(true, iir.isAdjacent());
      assertEquals(null, iir.getIntervalIntersect());

      one.setOffsets(5, 8);
      two.setOffsets(3, 5);

      iir = one.getRelation(two);

      assertEquals(true, iir.isTwoFirst());
      assertEquals(true, iir.isAdjacent());

      assertEquals(false, iir.isOneFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(null, iir.getIntervalIntersect());

   }

   public void test033IntersectNear() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(11, 19);
      two.setOffsets(18, 20);

      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isOneFirst());

      assertEquals(false, iir.isAdjacent());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());

      assertEquals(18, iir.getIntervalIntersect().getOffset());
      assertEquals(1, iir.getIntervalIntersect().getLen());

      one.setOffsets(11, 19);
      two.setOffsets(19, 20);

      iir = one.getRelation(two);

      assertEquals(true, iir.isOneFirst());
      assertEquals(true, iir.isAdjacent());
      assertEquals(0, iir.getDistance());

      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());

      assertEquals(null, iir.getIntervalIntersect());

   }

   public void test03Intersect() {

      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(10, 20);
      two.setOffsets(15, 30);

      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(false, iir.isAdjacent());

      assertEquals(15, iir.getIntervalIntersect().getOffset());
      assertEquals(20, iir.getIntervalIntersect().getOffsetEnd());
      assertEquals(5, iir.getIntervalIntersect().getLen());

      assertEquals(10, iir.getIntersectComplementLeft().getOffset());
      assertEquals(15, iir.getIntersectComplementLeft().getOffsetEnd());

      assertEquals(null, iir.getIntersectComplementRight());

      //exchange positions
      two.setOffsets(10, 20);
      one.setOffsets(15, 30);

      iir = one.getRelation(two);

      assertEquals(false, iir.isOneFirst());
      assertEquals(true, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(false, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(false, iir.isAdjacent());

      assertEquals(15, iir.getIntervalIntersect().getOffset());
      assertEquals(20, iir.getIntervalIntersect().getOffsetEnd());
      assertEquals(5, iir.getIntervalIntersect().getLen());

      assertEquals(10, iir.getIntersectComplementLeft().getOffset());
      assertEquals(15, iir.getIntersectComplementLeft().getOffsetEnd());

      assertEquals(null, iir.getIntersectComplementRight());
   }

   public void test04IntersectContainedLeft() {

      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(10, 20);
      two.setOffsets(10, 15);

      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(false, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(true, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(true, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(false, iir.isAdjacent());

      assertEquals(10, iir.getIntervalIntersect().getOffset());
      assertEquals(15, iir.getIntervalIntersect().getOffsetEnd());
      assertEquals(5, iir.getIntervalIntersect().getLen());

      assertEquals(null, iir.getIntersectComplementLeft());

      assertEquals(15, iir.getIntersectComplementRight().getOffset());
      assertEquals(20, iir.getIntersectComplementRight().getOffsetEnd());

   }

   public void test05IntersectContainedRight() {

      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(10, 20);
      two.setOffsets(15, 20);

      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(true, iir.isSameOffsetEnd());
      assertEquals(true, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(false, iir.isAdjacent());

      assertEquals(15, iir.getIntervalIntersect().getOffset());
      assertEquals(20, iir.getIntervalIntersect().getOffsetEnd());
      assertEquals(5, iir.getIntervalIntersect().getLen());

      assertEquals(10, iir.getIntersectComplementLeft().getOffset());
      assertEquals(15, iir.getIntersectComplementLeft().getOffsetEnd());

      assertEquals(null, iir.getIntersectComplementRight());
   }

   public void test06IntersectContainedFull() {

      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(10, 20);
      two.setOffsets(15, 19);

      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(true, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(false, iir.isSameLength());
      assertEquals(false, iir.isSameOffsetStart());
      assertEquals(false, iir.isSameOffsetEnd());
      assertEquals(true, iir.isTwoContainedInOne());
      assertEquals(false, iir.isOneContainedInTwo());
      assertEquals(false, iir.isAdjacent());

      assertEquals(15, iir.getIntervalIntersect().getOffset());
      assertEquals(19, iir.getIntervalIntersect().getOffsetEnd());
      assertEquals(4, iir.getIntervalIntersect().getLen());

      assertEquals(10, iir.getIntersectComplementLeft().getOffset());
      assertEquals(15, iir.getIntersectComplementLeft().getOffsetEnd());

      assertEquals(19, iir.getIntersectComplementRight().getOffset());
      assertEquals(20, iir.getIntersectComplementRight().getOffsetEnd());
      assertEquals(1, iir.getIntersectComplementRight().getLen());

   }
   
   public void test08Union() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(10, 20);
      two.setOffsets(15, 40);
      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(10, iir.getUnion().getOffset());
      assertEquals(40, iir.getUnion().getOffsetEnd());
   }
   
   public void test09Distance() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(10, 20);
      two.setOffsets(15, 40);
      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(-5, iir.getDistance());
      
      
      one.setOffsets(10, 20);
      two.setOffsets(25, 40);
      
      iir = one.getRelation(two);

      assertEquals(5, iir.getDistance());
      
      one.setOffsets(100, 200);
      two.setOffsets(25, 40);
      
      iir = one.getRelation(two);

      assertEquals(60, iir.getDistance());
   }
   public void test10Same() {

      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(10, 20);
      two.setOffsets(10, 20);

      IntIntervalRelation iir = one.getRelation(two);

      assertEquals(false, iir.isOneFirst());
      assertEquals(false, iir.isTwoFirst());
      assertEquals(true, iir.isSameLength());
      assertEquals(true, iir.isSameOffsetStart());
      assertEquals(true, iir.isSameOffsetEnd());
      assertEquals(true, iir.isTwoContainedInOne());
      assertEquals(true, iir.isOneContainedInTwo());
      assertEquals(false, iir.isAdjacent());

      assertEquals(-10, iir.getDistance());

      
      assertEquals(10, iir.getIntervalIntersect().getOffset());
      assertEquals(20, iir.getIntervalIntersect().getOffsetEnd());
      assertEquals(10, iir.getIntervalIntersect().getLen());

      assertEquals(null, iir.getIntersectComplementLeft());
      assertEquals(null, iir.getIntersectComplementRight());

   }
}
