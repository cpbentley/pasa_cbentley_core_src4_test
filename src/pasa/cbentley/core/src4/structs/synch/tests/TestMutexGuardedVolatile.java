package pasa.cbentley.core.src4.structs.synch.tests;

import pasa.cbentley.core.src4.structs.IntToStrings;
import pasa.cbentley.core.src4.structs.synch.MutexGuardedVolatile;
import pasa.cbentley.core.src4.thread.WorkerThread;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestMutexGuardedVolatile extends TestCaseBentley {

   private WorkerThread wt;

   public void setupAbstract() {

   }

   public void testBasic() {

      final MutexGuardedVolatile ms = new MutexGuardedVolatile(uc);

      final IntToStrings orderEvents = new IntToStrings(uc);

      wt = uc.getWorkerThread();

      Runnable run1 = new Runnable() {

         public void run() {
            orderEvents.add(1, "One");
            ms.releaseAll();
         }
      };

      wt.addToQueue(run1);

      try {
         ms.acquire();
         orderEvents.add(2, "Two");
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      assertEquals(2, orderEvents.nextempty);

      assertEquals("One", orderEvents.getString(0));
      assertEquals("Two", orderEvents.getString(1));
   }
}
