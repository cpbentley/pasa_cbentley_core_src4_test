package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.ctx.ACtx;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.stator.IStatorFactory;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStatorCtx extends ACtx {

   private TestCtx                   tc;

   private StatorableFactoryForTests statorFactory;

   public TestStatorCtx(TestCtx tc) {
      super(tc.getUC());
      this.tc = tc;
      statorFactory = new StatorableFactoryForTests(this);
   }

   public TestCtx getTC() {
      return tc;
   }

   public IStatorFactory getStatorFactory() {
      return statorFactory;
   }
}
