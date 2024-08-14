/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.event.tests;

import java.util.Stack;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.IExecutor;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;

public class ExecutorForTests implements IExecutor {

   private Stack<Integer> stackModes;

   protected final UCtx uc;

   public ExecutorForTests(UCtx uc) {
      this.uc = uc;
      stackModes = new Stack<Integer>();
   }

   public void executeMainLater(Runnable run) {
      stackModes.add(THREAD_MODE_2_MAIN_LATER);
      run.run();
   }

   public void executeMainNow(Runnable run) {
      stackModes.add(THREAD_MODE_1_MAIN_NOW);
      run.run();
   }

   public void executeWorker(Runnable run) {
      stackModes.add(THREAD_MODE_3_WORKER);
      run.run();
   }

   public int getLastMode() {
      if (stackModes.isEmpty()) {
         return -1;
      } else {
         return stackModes.pop();
      }
   }

   public boolean isMainThread() {
      // TODO Auto-generated method stub
      return false;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "TestExecutor");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "TestExecutor");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return uc;
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
