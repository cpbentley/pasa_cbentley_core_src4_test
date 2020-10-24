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

   public void testCreateIntersect() {
      IntInterval ii = new IntInterval(uc, 10, 5); //10-15

      assertEquals(null, ii.createFromIntersectionWith(0, 5));
      assertEquals(null, ii.createFromIntersectionWith(15, 5));
      
      IntInterval nii = ii.createFromIntersectionWith(5, 10);
      assertEquals(10, nii.getOffset());
      assertEquals(5, nii.getLen());
      
      nii = ii.createFromIntersectionWith(5, 9);
      assertEquals(10, nii.getOffset());
      assertEquals(4, nii.getLen());
      
      nii = ii.createFromIntersectionWith(13, 40);
      assertEquals(13, nii.getOffset());
      assertEquals(2, nii.getLen());
      
      nii = ii.createFromIntersectionWith(11, 1);
      assertEquals(11, nii.getOffset());
      assertEquals(1, nii.getLen());
      
      nii = ii.createFromIntersectionWith(11, 4);
      assertEquals(11, nii.getOffset());
      assertEquals(4, nii.getLen());
      
      nii = ii.createFromIntersectionWith(11, 5);
      assertEquals(11, nii.getOffset());
      assertEquals(4, nii.getLen());
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
