package pasa.cbentley.core.src4.structs.synch.tests;

import pasa.cbentley.core.src4.structs.IntToStrings;
import pasa.cbentley.core.src4.structs.synch.FairLock;
import pasa.cbentley.core.src4.thread.WorkerThread;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestFairLock extends TestCaseBentley {

   private WorkerThread wt;

   public void setupAbstract() {

   }

   public void testLock() {

      final FairLock lock = new FairLock(uc);

      final FairLock lock2 = new FairLock(uc);

      final IntToStrings orderEvents = new IntToStrings(uc);

      wt = uc.getWorkerThread();

      Runnable run1 = new Runnable() {

         public void run() {
            try {
               lock.lock();
               orderEvents.add(2, "Two");
               
               lock.unlock();
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      };

      try {
         //this lock prevents worker thread to quickly write 2 before 1
         lock.lock();
         wt.addToQueue(run1);
         
         //now if worker thread is lazy, it has not process anything yet
         orderEvents.add(1, "One");

         lock.unlock();
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      //now we want to continue only until two has been written

      try {
         Thread.currentThread().sleep(30);
      } catch (InterruptedException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      try {
         //before doing asserts make sure the other thread has finished
         lock.lock();
         //once inside, we know other threads are finished
         assertEquals(2, orderEvents.nextempty);

         assertEquals("One", orderEvents.getString(0));
         assertEquals("Two", orderEvents.getString(1));

         lock.unlock();
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void testComplex() {
      
   }

}
