package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.interfaces.IPrefsReader;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.testing.engine.TestCaseBentley;

/**
 * To test readings, use {@link TestStatorWriter}
 * 
 * @author Charles Bentley
 *
 */
public class TestStatorReader extends TestCaseBentley {

   public void setupAbstract() {
      //what about the logging ?
   }
   
   public void testBasic() {
      
      StatorReader reader = new StatorReader(uc);
      
      TestStatorableFactory factory = new TestStatorableFactory(tc);
      reader.addFactory(factory);
      
      
      long timestamp = reader.getTimestamp();
      
      byte[] data = reader.getData();
      assertEquals(null, data);
      
      int numObjects = reader.getNumObjects();
      assertEquals(0, numObjects);
      
      IPrefsReader keyValuePairs = reader.getKeyValuePairs();
      
      assertNotNull(keyValuePairs);
      
       String string = keyValuePairs.get("key", "def");
       assertEquals("def", string);
      
   }

}
