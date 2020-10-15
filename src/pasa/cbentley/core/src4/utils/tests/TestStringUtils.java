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
   public void testGetBreakTabs() {

      String word = "Hello\tWorld";

      int[] breakOffsets = su.getBreaksTab(word);

      int count = 0;
      assertEquals(4, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(5, breakOffsets[count++]);
      assertEquals(6, breakOffsets[count++]);
      assertEquals(5, breakOffsets[count++]);
      
   }
   public void testGetBreakNaturalBasic() {

      String word = "Hello World";

      int[] breakOffsets = su.getBreaksLineNatural(word);

      assertEquals(2, breakOffsets.length);
      assertEquals(0, breakOffsets[0]);
      assertEquals(11, breakOffsets[1]);

      breakOffsets = su.getBreaksLineNatural("Hello\nWorld");

      int count = 0;
      assertEquals(4, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(5, breakOffsets[count++]);
      assertEquals(6, breakOffsets[count++]);
      assertEquals(5, breakOffsets[count++]);

      breakOffsets = su.getBreaksLineNatural("Hello\r\nWorld");

      count = 0;
      assertEquals(4, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(5, breakOffsets[count++]);
      assertEquals(7, breakOffsets[count++]); //+1 offset because \r
      assertEquals(5, breakOffsets[count++]);

      breakOffsets = su.getBreaksLineNatural("A\nB\nC\nD");
      assertEquals(8, breakOffsets.length);

      breakOffsets = su.getBreaksLineNatural("A\nB\nC\nD\n");
      assertEquals(10, breakOffsets.length);
      count = 0;
      assertEquals(0, breakOffsets[count++]); //position of A
      assertEquals(1, breakOffsets[count++]); //len of A

      assertEquals(2, breakOffsets[count++]); //position of B
      assertEquals(1, breakOffsets[count++]); //len of B

      assertEquals(4, breakOffsets[count++]); //position of C
      assertEquals(1, breakOffsets[count++]); //len of C
      assertEquals(6, breakOffsets[count++]); //position of D
      assertEquals(1, breakOffsets[count++]); //len of D
      assertEquals(8, breakOffsets[count++]); //position of ''
      assertEquals(0, breakOffsets[count++]); //len of ''

   }

   public void testControlCharacters() {
      //b t f
      String word = "A\tB\tC\fA\tB\tC";

      //#debug
      toDLog().pTest(word, this, TestStringUtils.class, "testGetBreakNaturalBorderCases", LVL_05_FINE, true);

   }

   public void testGetBreakNaturalBorderCases() {
      int[] breakOffsets = null;
      int count = 0;
      
      breakOffsets = su.getBreaksLineNatural("");
      count = 0;
      assertEquals(2, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      
      breakOffsets = su.getBreaksLineNatural("\n");
      count = 0;
      assertEquals(4, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(1, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      
      breakOffsets = su.getBreaksLineNatural("\n\n");
      count = 0;
      assertEquals(6, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(1, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(2, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      
      
      breakOffsets = su.getBreaksLineNatural("\n\n\n");
      count = 0;
      assertEquals(8, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(1, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(2, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(3, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      
      String str = "\n\n\nA";
      assertEquals(4, str.length());

      breakOffsets = su.getBreaksLineNatural(str);
      count = 0;
      assertEquals(8, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(1, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(2, breakOffsets[count++]);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(3, breakOffsets[count++]);
      assertEquals(1, breakOffsets[count++]);
   }

   public void testGetBreaksWordNoSpace() {

      String word = "word";

      int[] breakOffsets = su.getBreaksWord(word);
      assertEquals(2, breakOffsets.length);
      assertEquals(0, breakOffsets[0]);
      assertEquals(4, breakOffsets[1]);

      char[] cs = new char[] { 'c', 'a', 'b', 'e' };

      breakOffsets = su.getBreaksWord(cs, 1, 2);
      assertEquals(2, breakOffsets.length);
      assertEquals(1, breakOffsets[0]);
      assertEquals(2, breakOffsets[1]);

      breakOffsets = su.getBreaksWord(cs, 1, 3);
      assertEquals(2, breakOffsets.length);
      assertEquals(1, breakOffsets[0]);
      assertEquals(3, breakOffsets[1]);

   }

   public void testGetBreaksWord() {
      String word = "Hello Worldsx";

      int[] breakOffsets = su.getBreaksWord(word);

      int count = 0;
      assertEquals(4, breakOffsets.length);
      assertEquals(0, breakOffsets[count++]);
      assertEquals(5, breakOffsets[count++]);
      //second word
      assertEquals(6, breakOffsets[count++]);
      assertEquals(7, breakOffsets[count++]);

      word = " word";

      breakOffsets = su.getBreaksWord(word);
      assertEquals(2, breakOffsets.length);
      assertEquals(1, breakOffsets[0]);
      assertEquals(4, breakOffsets[1]);

      word = " word ";
      breakOffsets = su.getBreaksWord(word);
      assertEquals(2, breakOffsets.length);
      assertEquals(1, breakOffsets[0]);
      assertEquals(4, breakOffsets[1]);

      word = "word ";
      breakOffsets = su.getBreaksWord(word);
      assertEquals(2, breakOffsets.length);
      assertEquals(0, breakOffsets[0]);
      assertEquals(4, breakOffsets[1]);
   }

   public void testIsWordDelimiter() {

      assertEquals(true, StringUtils.isWordDelimiterInvisible(' '));
      assertEquals(true, StringUtils.isWordDelimiterInvisible('\n'));
      assertEquals(true, StringUtils.isWordDelimiterInvisible('\r'));
      assertEquals(true, StringUtils.isWordDelimiterInvisible('\t'));

      assertEquals(true, StringUtils.isWordDelimiter(' '));
      assertEquals(true, StringUtils.isWordDelimiter('\n'));
      assertEquals(true, StringUtils.isWordDelimiter('\r'));
      assertEquals(true, StringUtils.isWordDelimiter('\t'));
      assertEquals(true, StringUtils.isWordDelimiter('.'));
      assertEquals(true, StringUtils.isWordDelimiter(','));
      assertEquals(true, StringUtils.isWordDelimiter('!'));
      assertEquals(true, StringUtils.isWordDelimiter('?'));

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
   public void testTrimAtChar() throws Exception {

      assertEquals("manger", su.trimAtChar("manger\n", '\n'));

      assertEquals("manger", su.trimAtChar("manger", '\n'));

      assertEquals("mang", su.trimAtChar("manger", 'e'));

   }
}
