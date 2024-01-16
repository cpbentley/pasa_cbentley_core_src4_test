package pasa.cbentley.core.src4.i8n.tests;

import pasa.cbentley.core.src4.i8n.IStringProducer;
import pasa.cbentley.core.src4.i8n.LString;
import pasa.cbentley.testing.engine.TestCaseBentley;

/**
 * Abstract class that must be tested by the test module of the implementation of {@link IStringProducer}
 * 
 * @author Charles Bentley
 *
 */
public class TestLString extends TestCaseBentley {

   public static final int    KEY_FOR_HELLO = 2;

   public static final String HELLO         = "Hello";

   protected IStringProducer  producer;

   public void setupAbstract() {

   }

   public void testGetStr() {

      int key = 1;

      LString str = new LString(producer, key);

      str.setPrefix("en");
      assertEquals("en", str.getPrefix());
   }

}
