/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.core.src4.utils.IOUtils;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestIOUtils extends TestCaseBentley {

   private IOUtils iu;
   private StringUtils su;

   public TestIOUtils() {
   }

   public void setupAbstract() {
      iu = new IOUtils(uc);
   }

   public void testReadFileAsString_1() {
      String data = uc.getIOU().readFileAsString("/file1.txt", "UTF-8");

      assertNotNull(data);
      assertEquals(data, "12345_67890");
   }

   public void testReadFileAsString_2() {
      String data = iu.readFileAsString("/file2.txt", "UTF-8");

      assertNotNull(data);

      //#debug
      toDLog().pTest(data, null, TestIOUtils.class, "testReadFileAsString_2", LVL_05_FINE, true);
  
      int numNewLines = su.countChars(data, '\n');
      assertEquals(numNewLines, 9);
      
   }

   public void testReadFileAsString_4_BuilderWindows() {
      StringBBuilder data = iu.readFileAsBuilder("/string_multilines.txt", "UTF-8");

      assertNotNull(data);
      assertEquals(1307, data.length());
    
   }
   public void testReadFileAsString_4() {
      String data = iu.readFileAsStringWindows("/string_multilines.txt", "UTF-8");

      assertNotNull(data);
      assertEquals(1268, data.length());
      
      //we have windows line
      assertEquals(-1, data.indexOf(StringUtils.NEW_LINE_CARRIAGE_RETURN));
      
      //#debug
      toDLog().pTest(data, null, TestIOUtils.class, "testReadFileAsString_2", LVL_05_FINE, true);
  
   }
}
