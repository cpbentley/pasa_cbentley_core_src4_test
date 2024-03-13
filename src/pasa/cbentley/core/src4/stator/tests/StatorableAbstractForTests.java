/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.stator.IStatorable;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.testing.ctx.TestCtx;

public abstract class StatorableAbstractForTests implements IStatorable {

   protected final TestCtx       tc;

   protected final TestStatorCtx tsc;

   public StatorableAbstractForTests(TestStatorCtx tsc) {
      this.tsc = tsc;
      this.tc = tsc.getTC();

   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "TestStatorableAbstract");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "TestStatorableAbstract");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return tc.getUC();
   }

   //#enddebug

}
