/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.helpers.tests;

import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestStringBBuilder extends TestCaseBentley {

   public TestStringBBuilder() {
   }

   public void setupAbstract() {

   }

   public void testAppendBad() {
      StringBBuilder sb = new StringBBuilder(uc);
      char[] cs = new char[] { 'W', 'o', 'r' };
      try {
         sb.append(cs, 0, 4);
         super.assertReachableNot("");
      } catch (ArrayIndexOutOfBoundsException e) {
         super.assertReachable();
      }

      assertEquals("", sb.toString());
   }

   public void testAppendIgnore() {
      StringBBuilder sb = new StringBBuilder(uc);
      char[] cs = new char[] { 'W', 'o', 'r' };
     
      assertEquals("or", sb.appendIgnore(cs, 0, 3, 'W').toString());
      assertEquals("orWr", sb.appendIgnore(cs, 0, 3, 'o').toString());
      assertEquals("orWrWo", sb.appendIgnore(cs, 0, 3, 'r').toString());
   }
   public void testAppend() {
      StringBBuilder sb = new StringBBuilder(uc);

      sb.append("Hello");

      assertEquals("Hello", sb.toString());

      sb.append(' ');

      assertEquals("Hello ", sb.toString());

      char[] cs = new char[] { 'W', 'o', 'r' };
      sb.append(cs, 0, 3);

      assertEquals("Hello Wor", sb.toString());
   }

   public void testReplaceAll() {

      StringBBuilder sb = new StringBBuilder(uc);

      sb.append("eat this %1 in five minutes");

      sb.replaceAll("%1", "apple");
      assertEquals("eat this apple in five minutes", sb.toString());

      sb.reset();
      sb.append("%1 eat this %1");
      sb.replaceAll("%1", "apple");
      assertEquals("apple eat this apple", sb.toString());

      sb.reset();
      sb.append("eat %1 this %1 now");
      sb.replaceAll("%1", "1");
      assertEquals("eat 1 this 1 now", sb.toString());

      sb.reset();
      sb.append("%1%1%1");
      sb.replaceAll("%1", "1");
      assertEquals("111", sb.toString());

      sb.reset();
      sb.append("%1%1%1");
      sb.replaceAll("%1", "");
      assertEquals("", sb.toString());

      sb.reset();
      sb.append("eat %1 this %1 now");
      sb.replaceAll("%1", "");
      assertEquals("eat  this  now", sb.toString());

      //border case
      sb.reset();
      sb.append("eat %1 this %1 now");
      sb.replaceAll("%1", "%1");
      assertEquals("eat %1 this %1 now", sb.toString());

      sb.reset();
      sb.append("eat %1 this %11 now");
      sb.replaceAll("%1", "%");
      assertEquals("eat % this %1 now", sb.toString());

      sb.reset();
      sb.append("eat this now");
      sb.replaceAll("this", "");
      assertEquals("eat  now", sb.toString());

      sb.reset();
      sb.append("eat this now");
      sb.replaceAll("", "now");
      assertEquals("eat this now", sb.toString());

      sb.reset();
      sb.append("eat this now");
      sb.replaceAll("", "");
      assertEquals("eat this now", sb.toString());

   }

   public void testReplaceFirst() {

      StringBBuilder sb = new StringBBuilder(uc);

      sb.append("eat this %1 in five minutes");

      sb.replaceFirst("%1", "apple");
      assertEquals("eat this apple in five minutes", sb.toString());

      sb.reset();
      sb.append("eat this %1");
      sb.replaceFirst("%1", "apple");
      assertEquals("eat this apple", sb.toString());

      sb.reset();
      sb.append("eat this %1 %1");
      sb.replaceFirst("%1", "apple");
      assertEquals("eat this apple %1", sb.toString());

      sb.reset();
      sb.append("eat this %1");
      sb.replaceFirst("%1", "1");
      assertEquals("eat this 1", sb.toString());

      sb.reset();
      sb.append("eat this %1");
      sb.replaceFirst("%1", "");
      assertEquals("eat this ", sb.toString());

   }

   public void testReplaceFirstNew() {
      StringBBuilder sb = null;

      sb = new StringBBuilder(uc, 4);
      sb.append("eat this %1 in five minutes");
      sb.replaceFirst("%1", "apple");
      assertEquals("eat this apple in five minutes", sb.toString());

      sb = new StringBBuilder(uc, 4);
      sb.append("eat this %1");
      sb.replaceFirst("%1", "apple");
      assertEquals("eat this apple", sb.toString());

      sb = new StringBBuilder(uc, 4);
      sb.append("eat this %1 %1");
      sb.replaceFirst("%1", "apple");
      assertEquals("eat this apple %1", sb.toString());

      sb = new StringBBuilder(uc, 4);
      sb.append("eat this %1");
      sb.replaceFirst("%1", "1");
      assertEquals("eat this 1", sb.toString());

      sb = new StringBBuilder(uc, 4);
      sb.append("eat this %1");
      sb.replaceFirst("%1", "");
      assertEquals("eat this ", sb.toString());

   }

}
