package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntInterval;
import pasa.cbentley.testing.engine.TestCaseBentley;

/**
 * {@link IntInterval}
 * @author Charles Bentley
 *
 */
public class TestIntInterval extends TestCaseBentley {

   public void setupAbstract() {

   }
   public void testInsideOutside() {

      IntInterval i = new IntInterval(uc);
      i.setOffsets(5, 10);
      
      assertEquals("5,10", i.toStringOffsets());
      assertEquals(false, i.isInside(4));
      assertEquals(true, i.isInside(5));
      assertEquals(true, i.isInside(6));
      assertEquals(true, i.isInside(7));
      assertEquals(true, i.isInside(8));
      assertEquals(true, i.isInside(9));
      assertEquals(false, i.isInside(10));
      assertEquals(false, i.isInside(11));
      
      assertEquals(5, i.getLen());
      
      assertEquals("5,10", i.toStringOffsets());
      assertEquals(true, i.isOutside(4));
      assertEquals(false, i.isOutside(5));
      assertEquals(false, i.isOutside(6));
      assertEquals(false, i.isOutside(7));
      assertEquals(false, i.isOutside(8));
      assertEquals(false, i.isOutside(9));
      assertEquals(true, i.isOutside(10));
      assertEquals(true, i.isOutside(11));
      
      assertEquals(5, i.getLen());
      
   }
   public void testPositionIndex() {

      IntInterval ii1 = new IntInterval(uc, 10, 4);

      assertEquals(-1, ii1.getIndexPosition(9));
      assertEquals(0, ii1.getIndexPosition(10));
      assertEquals(0, ii1.getIndexPosition(11));
      assertEquals(0, ii1.getIndexPosition(12));
      assertEquals(0, ii1.getIndexPosition(13));
      assertEquals(1, ii1.getIndexPosition(14));
      assertEquals(1, ii1.getIndexPosition(15));
   }

   public void testIntersectSmall() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);
      IntInterval three = new IntInterval(uc);

      one.setOffsets(0, 1);
      two.setOffsets(0, 2);
      three.setOffsets(1,1);
      
      IntInterval inter = one.getIntersectionWith(two);
      assertEquals(0, inter.getOffset());
      assertEquals(1, inter.getLen());
     
      assertEquals(null, one.getIntersectionWith(three));
      
      inter = one.getIntersectionWith(two);
      assertEquals(0, inter.getOffset());
      assertEquals(1, inter.getLen());
      
   }
   public void testIntersect() {
      IntInterval one = new IntInterval(uc);
      IntInterval two = new IntInterval(uc);

      one.setOffsets(11, 19);
      two.setOffsets(18, 20);
      
      assertEquals(true, one.isIntersect(two));

      IntInterval inter = one.getIntersectionWith(two);
      
      assertEquals(18, inter.getOffset());
      assertEquals(1, inter.getLen());
      
      one.setOffsets(11, 19);
      two.setOffsets(19, 20);
      
      
      inter = one.getIntersectionWith(two);
      assertEquals(null, inter);
      assertEquals(false, one.isIntersect(two));
         
      one.setOffsets(11, 18);
      two.setOffsets(19, 20);
      
      assertEquals(false, one.isIntersect(two));
      
      
   }
   public void testCreateIntersect() {
      IntInterval ii = new IntInterval(uc, 10, 5); //10-15

      assertEquals(null, ii.getIntersectionWith(0, 5));
      assertEquals(null, ii.getIntersectionWith(15, 5));
      
      IntInterval nii = ii.getIntersectionWith(5, 10);
      assertEquals(10, nii.getOffset());
      assertEquals(5, nii.getLen());
      
      nii = ii.getIntersectionWith(5, 9);
      assertEquals(10, nii.getOffset());
      assertEquals(4, nii.getLen());
      
      nii = ii.getIntersectionWith(13, 40);
      assertEquals(13, nii.getOffset());
      assertEquals(2, nii.getLen());
      
      nii = ii.getIntersectionWith(11, 1);
      assertEquals(11, nii.getOffset());
      assertEquals(1, nii.getLen());
      
      nii = ii.getIntersectionWith(11, 4);
      assertEquals(11, nii.getOffset());
      assertEquals(4, nii.getLen());
      
      nii = ii.getIntersectionWith(11, 5);
      assertEquals(11, nii.getOffset());
      assertEquals(4, nii.getLen());
      
      nii = ii.getIntersectionWith(0, 40);
      assertEquals(10, nii.getOffset());
      assertEquals(5, nii.getLen());
   }
   
   public void testCreateFromIntersection() {
      IntInterval ii1 = new IntInterval(uc, 0, 8);
      IntInterval ii2 = new IntInterval(uc, 2, 3);
      
      IntInterval createFromIntersectionWith = ii2.getIntersectionWith(ii1);
      
      assertEquals(2, createFromIntersectionWith.getOffset());
      assertEquals(3, createFromIntersectionWith.getLen());
      
      //test the bijectivity
      
      createFromIntersectionWith = ii1.getIntersectionWith(ii2);
      assertEquals(2, createFromIntersectionWith.getOffset());
      assertEquals(3, createFromIntersectionWith.getLen());
   }
   
   public void testMerge() {

      IntInterval ii1 = new IntInterval(uc, 10, 5);
      IntInterval ii2 = new IntInterval(uc, 40, 2);

      ii1.merge(ii2);

      assertEquals(10, ii2.getOffset());
      assertEquals(32, ii2.getLen());
   }

   public void testSubstractBelowAll() {

      IntInterval ii1 = new IntInterval(uc, 12, 5); //12-17
      IntInterval ii2 = new IntInterval(uc, 10, 7); //10-17
      assertEquals(5, ii1.substract(ii2));
      assertEquals(12, ii1.getOffset());
      assertEquals(0, ii1.getLen());
   }

   public void testSubstractItself() {
      IntInterval ii1 = new IntInterval(uc, 12, 6);
      assertEquals(6, ii1.substract(ii1));
      assertEquals(12, ii1.getOffset());
      assertEquals(0, ii1.getLen());

   }
   
   public void testContaining() {
      IntInterval ii = new IntInterval(uc);
      ii.setOffsets(10, 15);

      IntInterval iTest = new IntInterval(uc);
      iTest.setOffsets(11, 13);
      
      
      assertEquals(true, ii.isContaining(iTest));
      assertEquals(false, ii.isContainedBy(iTest));
      //inverse
      assertEquals(true, iTest.isContainedBy(ii));
      
      iTest.setOffsets(11, 16);
      
      assertEquals(false, ii.isContaining(iTest));
      
   }

   public void testSubstractTopAll() {

      IntInterval ii1 = new IntInterval(uc, 12, 5);
      IntInterval ii2 = new IntInterval(uc, 12, 7);
      assertEquals(5, ii1.substract(ii2));
      assertEquals(12, ii1.getOffset());
      assertEquals(0, ii1.getLen());
   }

   public void testSubstractContaining() {
      IntInterval ii1 = new IntInterval(uc, 12, 5);
      IntInterval ii2 = new IntInterval(uc, 11, 7);
      assertEquals(5, ii1.substract(ii2));
      assertEquals(12, ii1.getOffset());
      assertEquals(0, ii1.getLen());
      
      IntInterval i08 = new IntInterval(uc, 0, 8);
      IntInterval i23 = new IntInterval(uc, 2, 3);
      assertEquals(6, i08.substract(i23));
      assertEquals(0, i08.getOffset());
      assertEquals(2, i08.getLen());
   
   }

   public void testSubstractTopAllBut1() {

      IntInterval ii1 = new IntInterval(uc, 12, 5);
      IntInterval ii2 = new IntInterval(uc, 13, 7);
      assertEquals(4, ii1.substract(ii2));
      assertEquals(12, ii1.getOffset());
      assertEquals(1, ii1.getLen());
   }

   public void testSubstract() {
      IntInterval ii1 = new IntInterval(uc, 10, 5);
      IntInterval ii2 = new IntInterval(uc, 40, 2);

      IntInterval ii3 = new IntInterval(uc, 12, 5);

      assertEquals(0, ii1.substract(ii2));

      assertEquals(3, ii1.substract(ii3));
      assertEquals(10, ii1.getOffset());
      assertEquals(2, ii1.getLen());

   }
}
