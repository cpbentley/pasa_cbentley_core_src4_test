/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStatorable2 extends TestStatorableAbstract {

   private String          name;

   private TestStatorable1 state1;

   public TestStatorable2(TestCtx tc) {
      super(tc);

   }

   public String getName() {
      return name;
   }

   public TestStatorable1 getState1() {
      return state1;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setState1(TestStatorable1 state1) {
      this.state1 = state1;
   }

   public void stateReadFrom(StatorReader state) {
      name = state.getDataReader().readString();
      state1 = (TestStatorable1) state.createObject(TestStatorable1.class, state1);
   }

   public void stateWriteTo(StatorWriter state) {
      state.getDataWriter().writeString(name);
      state.stateWriteOf(state1);
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "TestStatorable2");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.nlLvl(state1);
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("name", name);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "TestStatorable2");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   


}
