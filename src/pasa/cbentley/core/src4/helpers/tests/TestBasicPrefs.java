/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.helpers.tests;

import pasa.cbentley.core.src4.helpers.BasicPrefs;
import pasa.cbentley.core.src4.io.BADataOS;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestBasicPrefs extends TestCaseBentley {

   public void setupAbstract() {
      // TODO Auto-generated method stub

   }

   public void testSimple() {

      BasicPrefs prefs = new BasicPrefs(uc);
      prefs.put("color", "black");
      prefs.putInt("val", 100);

      assertEquals("black", prefs.get("color", ""));
      assertEquals(100, prefs.getInt("val", 0));
   }

   public void testExport() {

      BasicPrefs prefs = new BasicPrefs(uc);
      prefs.put("color", "black");
      prefs.putInt("val", 100);

      BADataOS dos = uc.createNewBADataOS();
      prefs.export(dos);
      
      byte[] data = dos.getByteCopy();
      
      BasicPrefs prefs2 = new BasicPrefs(uc);
      prefs2.importPrefs(uc.createNewBADataIS(data));
      
      
      assertEquals("black", prefs.get("color", ""));
      assertEquals(100, prefs.getInt("val", 0));
 
      assertEquals("black", prefs2.get("color", ""));
      assertEquals(100, prefs2.getInt("val", 0));
 
      
   }
}
