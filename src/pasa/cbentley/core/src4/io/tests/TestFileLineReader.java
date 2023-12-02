package pasa.cbentley.core.src4.io.tests;

import pasa.cbentley.core.src4.io.FileLineReader;
import pasa.cbentley.core.src4.io.ILineReader;
import pasa.cbentley.core.src4.io.XString;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestFileLineReader extends TestCaseBentley {

   public void setupAbstract() {
      
   }

   
   public void testFileLineReader1() {
      ILineReader reader = new ILineReader() {
         
         public void lineRead(FileLineReader reader, XString line, int numA, int numF) {
            if(numF == 0) {
               String str = line.getString();
               assertEquals("avoid", str);
            }
            if(numF == 0) {
               String str = line.getString();
               assertEquals("check this out", str);
            }
         }
      };
      FileLineReader flr = new FileLineReader(uc, reader, "/testFileLineReader1.txt");
      int numLines = flr.read();
      assertEquals(6, numLines);
   }
   
   public void testFileLineReader2() {
      ILineReader reader = new ILineReader() {
         
         public void lineRead(FileLineReader reader, XString line, int numA, int numF) {
            if(numA == 1) {
               String str = line.getString();
               assertEquals("first line", str);
            }
            if(numA == 2) {
               String str = line.getString();
               assertEquals("avoid", str);
            }
            if(numA == 3) {
               String str = line.getString();
               assertEquals("", str);
            }
            if(numA == 4) {
               String str = line.getString();
               assertEquals("again", str);
            }
         }
      };
      FileLineReader flr = new FileLineReader(uc, reader, "/testFileLineReader2.txt");
      int numLines = flr.read();
      assertEquals(4, numLines);
   }
   
   public void testFileLineReader3() {
      ILineReader reader = new ILineReader() {
         
         public void lineRead(FileLineReader reader, XString line, int numA, int numF) {
            if(numA == 1) {
               String str = line.getString();
               assertEquals("first line", str);
            }
            if(numA == 2) {
               String str = line.getString();
               assertEquals("avoid", str);
            }
            if(numA == 3) {
               String str = line.getString();
               assertEquals("", str);
            }
            if(numA == 4) {
               String str = line.getString();
               assertEquals("", str);
            }
         }
      };
      FileLineReader flr = new FileLineReader(uc, reader, "/testFileLineReader3.txt");
      int numLines = flr.read();
      assertEquals(4, numLines);
   }
}
