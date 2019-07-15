package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.CircularObjects;
import pasa.cbentley.testing.BentleyTestCase;

public class TestCircularObjects extends BentleyTestCase {

   public TestCircularObjects() {
      super(true);
   }
   
   public void testBasic() {
      
      CircularObjects co = new CircularObjects(uc,5);
      
      assertEquals(0, co.getSizeNonNulls());
      
      co.addObject("1");
      
      assertEquals(1, co.getSizeNonNulls());
      
      Object[] ar = co.getObjectsNotNullNewestFirst();
      assertEquals(ar[0], "1");
      
      co.addObject("2");
      
      assertEquals(2, co.getSizeNonNulls());
      
      ar = co.getObjectsNotNullNewestFirst();
      assertEquals(ar[0], "2");
      assertEquals(ar[1], "1");
      
      
      co.addObject("3");
      
      ar = co.getObjectsNotNullNewestFirst();
      assertEquals(ar[0], "3");
      assertEquals(ar[1], "2");
      assertEquals(ar[2], "1");
      
      co.addObject("4");
      
      ar = co.getObjectsNotNullNewestFirst();
      assertEquals(ar[0], "4");
      assertEquals(ar[1], "3");
      assertEquals(ar[2], "2");
      assertEquals(ar[3], "1");
      
      co.addObject("5");
      
      assertEquals(5, co.getSizeNonNulls());
      
      ar = co.getObjectsNotNullNewestFirst();
      assertEquals(ar[0], "5");
      assertEquals(ar[1], "4");
      assertEquals(ar[2], "3");
      assertEquals(ar[3], "2");
      assertEquals(ar[4], "1");
      
      co.addObject("6");
      
      ar =  co.getObjectsNotNullNewestFirst();
      assertEquals(ar[0], "6");
      assertEquals(ar[1], "5");
      assertEquals(ar[2], "4");
      assertEquals(ar[3], "3");
      assertEquals(ar[4], "2");
      assertEquals(5, co.getSizeNonNulls());
      
      co.addObject(null);
      
      ar =  co.getObjectsNotNullNewestFirst();
      assertEquals(ar[0], "6");
      assertEquals(ar[1], "5");
      assertEquals(ar[2], "4");
      assertEquals(ar[3], "3");
      
      assertEquals(4, co.getSizeNonNulls());
      
      co.addObject(null);
      assertEquals(3, co.getSizeNonNulls());
      
      co.addObject("7");
      //7 replace a non null value
      assertEquals(3, co.getSizeNonNulls());
      ar =  co.getObjectsNotNullNewestFirst();
      assertEquals(ar[0], "7");
      assertEquals(ar[1], "6");
      assertEquals(ar[2], "5");
      
   }

   public void setupAbstract() {
      // TODO Auto-generated method stub
      
   }
}
