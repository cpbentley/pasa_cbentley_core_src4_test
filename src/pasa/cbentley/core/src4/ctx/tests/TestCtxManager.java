package pasa.cbentley.core.src4.ctx.tests;

import pasa.cbentley.core.src4.ctx.CtxManager;
import pasa.cbentley.core.src4.io.BADataOS;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestCtxManager extends TestCaseBentley {

   public TestCtxManager() {

      setTestFlag(TEST_FLAG_01_PRINT_ANYWAYS, true);
   }

   public void testBasic() {

      CtxManager cm = uc.getCtxManager();

      //#debug
      toDLog().pTest("", cm, TestCtxManager.class, "testBasic", LVL_05_FINE, false);

      assertEquals(uc, cm.getCtx(1));
   }

   public void testReadWrite() {

      
      CtxManager cm = uc.getCtxManager();


   }

   public void setupAbstract() {
   }
}
