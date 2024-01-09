package pasa.cbentley.core.src4.text.tests;

import pasa.cbentley.core.src4.structs.IntIntervals;
import pasa.cbentley.core.src4.text.StringInterval;
import pasa.cbentley.core.src4.text.TextModel;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestTextModel extends TestCaseBentley {

   public void setupAbstract() {

   }
   
   public void testNewLineSimple() {
      TextModel tm = new TextModel(uc);

      String text = "##Relative\nAbsolute###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      tm.setChars(chars, offset, len);

      tm.buildModel();

      assertEquals(2, tm.getNumWords());
      StringInterval[] intervalsWords = tm.getIntervalsWords();
      int count = 0;
      assertEquals("Relative", tm.getString(intervalsWords[count++]));
      assertEquals("Absolute", tm.getString(intervalsWords[count++]));
      
      assertEquals(1, tm.getNumSentences());
      assertEquals(1, tm.getNumNewLines());

      StringInterval[] intervalsSentences = tm.getIntervalsSentences();
      count = 0;
      assertEquals("Relative\nAbsolute", tm.getString(intervalsSentences[count++]));
    
      
   }

   public void testAbsoluteVsRelative() {
      TextModel tm = new TextModel(uc);

      //first absolute
      String text = "Relative vs Absolute!";
      tm.setString(text);

      assertEquals("Relative vs Absolute!", tm.getTextAsString());

      tm.buildModel();

      assertEquals(3, tm.getNumWords());
      StringInterval[] intervalsWords = tm.getIntervalsWords();
      int count = 0;
      assertEquals("Relative", tm.getString(intervalsWords[count++]));
      assertEquals("vs", tm.getString(intervalsWords[count++]));
      assertEquals("Absolute", tm.getString(intervalsWords[count++]));
      
      assertEquals(1, tm.getNumSentences());

      StringInterval[] intervalsSentences = tm.getIntervalsSentences();
      count = 0;
      assertEquals("Relative vs Absolute!", tm.getString(intervalsSentences[count++]));
      
      //first relative
      text = "##Relative vs Absolute!###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      tm.setChars(chars, offset, len);

      assertEquals("Relative vs Absolute!", tm.getTextAsString());

      tm.buildModel();

      assertEquals(3, tm.getNumWords());
      intervalsWords = tm.getIntervalsWords();
       count = 0;
      assertEquals("Relative", tm.getString(intervalsWords[count++]));
      assertEquals("vs", tm.getString(intervalsWords[count++]));
      assertEquals("Absolute", tm.getString(intervalsWords[count++]));
      
      assertEquals(1, tm.getNumSentences());

      intervalsSentences = tm.getIntervalsSentences();
      count = 0;
      assertEquals("Relative vs Absolute!", tm.getString(intervalsSentences[count++]));
  
   }
   
   
   public void testWord() {
      TextModel tm = new TextModel(uc);

      //first absolute
      String text = "Word";
      tm.setString(text);

      assertEquals("Word", tm.getTextAsString());

      tm.buildModel();

      assertEquals(1, tm.getNumWords());
      StringInterval[] intervalsWords = tm.getIntervalsWords();
      int count = 0;
      assertEquals("Word", tm.getString(intervalsWords[count++]));
      
      assertEquals(1, tm.getNumSentences());

      StringInterval[] intervalsSentences = tm.getIntervalsSentences();
      count = 0;
      assertEquals("Word", tm.getString(intervalsSentences[count++]));
      
      //first relative
      text = "##Word###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      tm.setChars(chars, offset, len);

      assertEquals("Word", tm.getTextAsString());

      tm.buildModel();

      assertEquals(1, tm.getNumWords());
      intervalsWords = tm.getIntervalsWords();
       count = 0;
      assertEquals("Word", tm.getString(intervalsWords[count++]));
      
      assertEquals(1, tm.getNumSentences());

      intervalsSentences = tm.getIntervalsSentences();
      count = 0;
      assertEquals("Word", tm.getString(intervalsSentences[count++]));
      
      assertEquals(0, tm.getNumSeparators());
      assertEquals(1, tm.getParagraph());

  
   }
   public void testWierd() {
      TextModel tm = new TextModel(uc);

      String text = "##Don't $%leave me ,alone..###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      tm.setChars(chars, offset, len);

      assertEquals("Don't $%leave me ,alone..", tm.getTextAsString());

      tm.buildModel();

      assertEquals(4, tm.getNumWords());
      StringInterval[] intervalsWords = tm.getIntervalsWords();
      int count = 0;
      assertEquals("Don't", tm.getString(intervalsWords[count++]));
      assertEquals("$%leave", tm.getString(intervalsWords[count++]));
      assertEquals("me", tm.getString(intervalsWords[count++]));
      assertEquals("alone", tm.getString(intervalsWords[count++]));

      assertEquals(1, tm.getNumSentences());

      StringInterval[] intervalsSentences = tm.getIntervalsSentences();
      count = 0;
      assertEquals("Don't $%leave me ,alone..", tm.getString(intervalsSentences[count++]));
      
   }

   public void testBasic() {

      TextModel tm = new TextModel(uc);

      String text = "Once.  Twice I want. Please Help me! Don't leave me alone...";
      char[] chars = text.toCharArray();
      int len = text.length() - 5 - 1;
      tm.setChars(chars, 5, len);

      assertEquals("  Twice I want. Please Help me! Don't leave me alone..", tm.getTextAsString());

      tm.buildModel();

      assertEquals(10, tm.getNumWords());

      StringInterval[] intervalsWords = tm.getIntervalsWords();

      assertEquals(10, intervalsWords.length);

      assertEquals("Twice", tm.getString(intervalsWords[0]));
      assertEquals("I", tm.getString(intervalsWords[1]));
      assertEquals("want", tm.getString(intervalsWords[2]));
      assertEquals("Please", tm.getString(intervalsWords[3]));
      assertEquals("Help", tm.getString(intervalsWords[4]));
      assertEquals("me", tm.getString(intervalsWords[5]));
      assertEquals("Don't", tm.getString(intervalsWords[6]));
      assertEquals("leave", tm.getString(intervalsWords[7]));
      assertEquals("me", tm.getString(intervalsWords[8]));
      //assertEquals("alone", tm.getString(intervalsWords[9]));

      assertEquals(10, tm.getNumSeparators());

      StringInterval[] intervalsSpaces = tm.getIntervalsSpaces();
      assertEquals("  ", tm.getString(intervalsSpaces[0]));
      assertEquals(" ", tm.getString(intervalsSpaces[1]));

      assertEquals(3, tm.getNumSentences());

      StringInterval[] intervalsSentences = tm.getIntervalsSentences();
      assertEquals("Twice I want.", tm.getString(intervalsSentences[0]));
      assertEquals("Please Help me!", tm.getString(intervalsSentences[1]));
      assertEquals("Don't leave me alone..", tm.getString(intervalsSentences[2]));

   }

   public void testBasic1Sentence() {

      TextModel tm = new TextModel(uc);

      String text = "Twice I want.";
      tm.setString(text);

      tm.buildModel();

      assertEquals(3, tm.getNumWords());
      StringInterval[] intervalsWords = tm.getIntervalsWords();

      assertEquals("Twice", tm.getString(intervalsWords[0]));
      assertEquals("I", tm.getString(intervalsWords[1]));
      assertEquals("want", tm.getString(intervalsWords[2]));

      assertEquals(2, tm.getNumSeparators());

      StringInterval[] intervalsSpaces = tm.getIntervalsSpaces();
      assertEquals(" ", tm.getString(intervalsSpaces[0]));
      assertEquals(" ", tm.getString(intervalsSpaces[1]));

      assertEquals(1, tm.getNumSentences());

      StringInterval[] intervalsSentences = tm.getIntervalsSentences();
      assertEquals("Twice I want.", tm.getString(intervalsSentences[0]));

   }

   public void test2Sentence() {

      TextModel tm = new TextModel(uc);

      String text = "Twice I want. Once!";
      tm.setString(text);

      tm.buildModel();

      assertEquals(4, tm.getNumWords());
      StringInterval[] intervalsWords = tm.getIntervalsWords();

      assertEquals("Twice", tm.getString(intervalsWords[0]));
      assertEquals("I", tm.getString(intervalsWords[1]));
      assertEquals("want", tm.getString(intervalsWords[2]));
      assertEquals("Once", tm.getString(intervalsWords[3]));

      assertEquals(3, tm.getNumSeparators());

      assertEquals(2, tm.getNumSentences());

      StringInterval[] intervalsSentences = tm.getIntervalsSentences();
      assertEquals("Twice I want.", tm.getString(intervalsSentences[0]));
      assertEquals("Once!", tm.getString(intervalsSentences[1]));

   }

}
