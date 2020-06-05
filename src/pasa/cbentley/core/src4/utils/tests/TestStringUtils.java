/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import org.junit.Test;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.io.BADataOS;
import pasa.cbentley.core.src4.structs.IntBuffer;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestStringUtils extends TestCaseBentley {

   private StringUtils su = new StringUtils(new UCtx());

   public TestStringUtils() {
      super(true);
   }

   public void setupAbstract() {

   }

   @Test
   public void testGetIndexesAsBuffer() {

      IntBuffer ib = su.getIndexesAsBuffer("range;try;fly", ";");
      assertEquals(2, ib.getSize());
      assertEquals(5, ib.get(0));
      assertEquals(9, ib.get(1));

      ib = su.getIndexesAsBuffer("range;try;fly", "");
      assertEquals(0, ib.getSize());

      ib = su.getIndexesAsBuffer("range;try;fly", "range;try;fly");
      assertEquals(1, ib.getSize());

   }

   @Test
   public void testGetSplitArray() {
      String[] ar = su.getSplitArray("range;try;fly", ";");
      assertEquals(3, ar.length);
      assertEquals("range", ar[0]);
      assertEquals("try", ar[1]);
      assertEquals("fly", ar[2]);

      //borderline cases
      ar = su.getSplitArray("range;try;fly", "");
      assertEquals(1, ar.length);
      assertEquals("range;try;fly", ar[0]);

      ar = su.getSplitArray("aetryae", "ae");
      assertEquals(3, ar.length);
      assertEquals("", ar[0]);
      assertEquals("try", ar[1]);
      assertEquals("", ar[2]);

      ar = su.getSplitArray("range;try;fly", "range;try;fly");
      assertEquals(2, ar.length);
      assertEquals("", ar[0]);
      assertEquals("", ar[1]);

      ar = su.getSplitArray(";;", ";");
      assertEquals(3, ar.length);
      assertEquals("", ar[0]);
      assertEquals("", ar[1]);
      assertEquals("", ar[2]);

   }

   public void testGetSubString() {
      String str = "United Kingdom (+44)";

      assertEquals("+44", su.getSubstring(str, '(', ')'));
      assertEquals("United Kingdom ", su.getSubstringStartToChar(str, '('));
      assertEquals("United Kingdom", su.getSubstringStartToString(str, " ("));
      assertEquals("+44)", su.getSubstringCharToEnd(str, '('));

      //border cases
      assertEquals("United Kingdom (+44", su.getSubstringStartToChar(str, ')'));
      assertEquals("", su.getSubstringCharToEnd(str, ')'));

   }

   @Test
   public void testIsLastLetter() throws Exception {

      assertEquals(true, su.isLastLetter('z'));
      assertEquals(true, su.isLastLetter('Z'));

      assertEquals(true, su.isLastLetter('Я'));
      assertEquals(true, su.isLastLetter('я'));

   }

   public void testStringIntLong() throws Exception {

      BADataOS ba = uc.createNewBADataOS();
      ba.writeByte(4);
      ba.writeChars("eat");

      byte[] data = ba.getByteCopy();

      assertEquals("eat", su.getStringIntLong(data, 1));

      su.writeStringIntLong("grt", data, 0);
      assertEquals("grt", su.getStringIntLong(data, 0));

      
   }

   @Test
   public void testPrettyStringMem() throws Exception {

      assertEquals("10 bytes", su.prettyStringMem(10));

      assertEquals("999 bytes", su.prettyStringMem(999));
      assertEquals("1000 bytes", su.prettyStringMem(1000));
      assertEquals("1001 bytes", su.prettyStringMem(1001));

      assertEquals("2 kb", su.prettyStringMem(2000));
      assertEquals("2 kb", su.prettyStringMem(2100));

      assertEquals("1000 kb", su.prettyStringMem(1000000));
      assertEquals("1001 kb", su.prettyStringMem(1001000));

      assertEquals("9 mb", su.prettyStringMem(9000000));
      assertEquals("9 mb", su.prettyStringMem(9001000));

      assertEquals("9 gb", su.prettyStringMem(9000000000L));
      assertEquals("9 gb", su.prettyStringMem(9001000000L));

   }

   public void testPrettyFloat() {
      assertEquals("1.0", su.prettyFloat(1.025f, 1));
      assertEquals("1.02", su.prettyFloat(1.025f, 2));
      assertEquals("1.025", su.prettyFloat(1.025f, 3));
      assertEquals("1.025", su.prettyFloat(1.025f, 4));

      assertEquals("11.02", su.prettyFloat(11.02f, 2));

      //bad parameters
      assertEquals("1", su.prettyFloat(1.025f, 0));

      try {
         assertEquals("1.0", su.prettyFloat(1.025f, -1));
         assertNotReachable("Must throw");
      } catch (IllegalArgumentException e) {
         assertReachable();
      }

   }

   public void testSplitAnySpace() {

      String text = "sasd sadas qwe \t sad \ntry";

      String[] ar = text.split(" \\s{2,}");

      for (int i = 0; i < ar.length; i++) {
         System.out.println(ar[i].trim());
      }

      ar = text.split("\\s+");

      for (int i = 0; i < ar.length; i++) {
         System.out.println(ar[i].trim());
      }

      //assertEquals(true, false);
   }

   @Test
   public void testTrimAtChar() throws Exception {

      assertEquals("manger", su.trimAtChar("manger\n", '\n'));

      assertEquals("manger", su.trimAtChar("manger", '\n'));

      assertEquals("mang", su.trimAtChar("manger", 'e'));

   }
}
