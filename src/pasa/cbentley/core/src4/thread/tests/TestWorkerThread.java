package pasa.cbentley.core.src4.thread.tests;

import pasa.cbentley.core.src4.structs.IntToStrings;
import pasa.cbentley.core.src4.thread.WorkerThread;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestWorkerThread extends TestCaseBentley {

   public void setupAbstract() {

   }

   public void testBasic() {

      WorkerThread wt = uc.getWorkerThread();

      assertEquals(0, wt.size());

      final IntToStrings data = new IntToStrings(uc);

      Runnable run = new Runnable() {

         public void run() {
            data.add("One");
         }
      };

      wt.addToQueue(run);

   
      while (wt.size() != 0) {
         try {
            Thread.currentThread().sleep(20);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

      assertEquals(data.getSize(), 1);
      assertEquals("One", data.getString(0));
      
      Runnable run2 = new Runnable() {
         public void run() {
            data.add("Two");
         }
      };
      Runnable run3 = new Runnable() {
         public void run() {
            data.add("Three");
         }
      };
      wt.addToQueue(run2);
      wt.addToQueue(run3);
      
      
      while (wt.size() != 0) {
         try {
            Thread.currentThread().sleep(20);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      
      assertEquals(data.getSize(), 3);
      assertEquals("One", data.getString(0));
      assertEquals("Two", data.getString(1));
      assertEquals("Three", data.getString(2));
     
   }
}
