package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.stator.ITechStator;
import pasa.cbentley.core.src4.stator.Stator;
import pasa.cbentley.testing.engine.TestCaseBentley;

public abstract class TestCaseStator extends TestCaseBentley implements ITechStator {

   protected Stator        stator;

   protected TestStatorCtx tsc;

   public void setupAbstract() {
      //what about the logging ?
      tsc = new TestStatorCtx(tc);
      stator = new Stator(uc);
   }

   protected Stator createNewStator() {
      Stator stator = new Stator(uc);
      return stator;
   }
}
