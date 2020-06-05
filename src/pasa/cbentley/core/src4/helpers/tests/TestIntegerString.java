/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.helpers.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.helpers.IntegerString;
import pasa.cbentley.core.src4.utils.CharUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIntegerString extends TestCaseBentley {

   public TestIntegerString() {
      super(true);
   }

   public void setupAbstract() {
      // TODO Auto-generated method stub

   }

   
   public void testZeros() {
      IntegerString istr = new IntegerString(uc);
      istr.setNumber(0);
      assertEquals("0", istr.getString());
      assertEquals(1, istr.getSize());
      assertEquals(1, istr.getNumDifferentDigits());
      assertEquals(true, istr.isPalindrome());

      istr.setNumber(00);
      assertEquals("0", istr.getString());
      assertEquals(1, istr.getSize());
      assertEquals(1, istr.getNumDifferentDigits());
      assertEquals(true, istr.isPalindrome());
   }
   
   public void testSameDigiNumbers() {
      IntegerString istr = new IntegerString(uc);
      
      for (int i = 1; i < 9; i++) {
         String str = "";
         for (int j = 1; j < 9; j++) {
            str = str + i;
            int value = Integer.parseInt(str);
            istr.setNumber(value);
            assertEquals(j, istr.getSize());
            assertEquals(1, istr.getNumDifferentDigits());
            assertEquals(true, istr.isPalindrome());
            assertEquals(j, istr.getNumIdenticalDigitsFromLeft());
            assertEquals(j, istr.getNumIdenticalDigitsFromLeft());     
         }
      }
      
   }
   
   public void testPalindromes() {
      IntegerString istr = new IntegerString(uc);

      testPalindrome(istr, 599985, false);
      testPalindrome(istr, 59985, false);
      testPalindrome(istr, 5985, false);
      testPalindrome(istr, 599995, true);
   }
   
   private void testPalindrome(IntegerString istr, int value, boolean res) {
      istr.setNumber(value);
      assertEquals(res, istr.isPalindrome());
   }
   
   public void test_599985() {

      IntegerString istr = new IntegerString(uc);
      istr.setNumber(599985);
      assertEquals("599985", istr.getString());
      assertEquals(6, istr.getSize());
      assertEquals(3, istr.getNumDifferentDigits());
      assertEquals(false, istr.isPalindrome());
      assertEquals(1, istr.getNumIdenticalDigitsFromLeft());
      assertEquals(1, istr.getNumIdenticalDigitsFromRight());
      
   }
   
   public void test_666666_7500_47897191() {

      IntegerString istr = new IntegerString(uc);
      istr.setNumber(666666);
      assertEquals("666666", istr.getString());
      assertEquals(6, istr.getSize());
      assertEquals(1, istr.getNumDifferentDigits());
      assertEquals(true, istr.isPalindrome());
      
      istr.setNumber(7500);
      assertEquals("7500", istr.getString());
      assertEquals(4, istr.getSize());
      assertEquals(3, istr.getNumDifferentDigits());
      assertEquals(false, istr.isPalindrome());
      
      istr.setNumber(47897191);
      assertEquals("47897191", istr.getString());
      assertEquals(8, istr.getSize());
      assertEquals(5, istr.getNumDifferentDigits());
      assertEquals(false, istr.isPalindrome());

   }

   public void test_1452_11500_191() {

      IntegerString istr = new IntegerString(uc);

      istr.setNumber(1452);

      assertEquals("1452", istr.getString());
      assertEquals(4, istr.getSize());
      assertEquals(4, istr.getNumDifferentDigits());
      assertEquals(false, istr.isPalindrome());

      assertEquals(1, istr.getDigitFromLeft1());
      assertEquals(4, istr.getDigitFromLeft2());
      assertEquals(5, istr.getDigitFromLeft3());
      assertEquals(2, istr.getDigitFromLeft4());

      assertEquals('1', istr.getCharFromLeft1());
      assertEquals('4', istr.getCharFromLeft2());
      assertEquals('5', istr.getCharFromLeft3());
      assertEquals('2', istr.getCharFromLeft4());

      assertEquals(2, istr.getDigitFromRight1());
      assertEquals(5, istr.getDigitFromRight2());
      assertEquals(4, istr.getDigitFromRight3());
      assertEquals(1, istr.getDigitFromRight4());

      assertEquals(1, istr.getOccurenceOfNumber(1));
      assertEquals(1, istr.getOccurenceOfNumber(4));
      assertEquals(1, istr.getOccurenceOfNumber(5));
      assertEquals(1, istr.getOccurenceOfNumber(2));
      assertEquals(0, istr.getOccurenceOfNumber(3));

      assertEquals(1, istr.getNumIdenticalDigitsFromLeft());
      assertEquals(1, istr.getNumIdenticalDigitsFromRight());

      istr.setNumber(11500);

      assertEquals("11500", istr.getString());
      assertEquals(5, istr.getSize());
      assertEquals(3, istr.getNumDifferentDigits());
      assertEquals(false, istr.isPalindrome());

      assertEquals(1, istr.getDigitFromLeft1());
      assertEquals(1, istr.getDigitFromLeft2());
      assertEquals(5, istr.getDigitFromLeft3());
      assertEquals(0, istr.getDigitFromLeft4());

      assertEquals('1', istr.getCharFromLeft1());
      assertEquals('1', istr.getCharFromLeft2());
      assertEquals('5', istr.getCharFromLeft3());
      assertEquals('0', istr.getCharFromLeft4());

      assertEquals(0, istr.getDigitFromRight1());
      assertEquals(0, istr.getDigitFromRight2());
      assertEquals(5, istr.getDigitFromRight3());
      assertEquals(1, istr.getDigitFromRight4());

      assertEquals(2, istr.getOccurenceOfNumber(0));
      assertEquals(2, istr.getOccurenceOfNumber(1));
      assertEquals(0, istr.getOccurenceOfNumber(2));
      assertEquals(0, istr.getOccurenceOfNumber(3));
      assertEquals(0, istr.getOccurenceOfNumber(4));
      assertEquals(1, istr.getOccurenceOfNumber(5));

      assertEquals(2, istr.getNumIdenticalDigitsFromLeft());
      assertEquals(2, istr.getNumIdenticalDigitsFromRight());

      istr.setNumber(191);

      assertEquals("191", istr.getString());
      assertEquals(3, istr.getSize());
      assertEquals(2, istr.getNumDifferentDigits());
      assertEquals(true, istr.isPalindrome());
      assertEquals(1, istr.getNumIdenticalDigitsFromLeft());
      assertEquals(1, istr.getNumIdenticalDigitsFromRight());
   }

   public void assertOccurences() {

   }
}
