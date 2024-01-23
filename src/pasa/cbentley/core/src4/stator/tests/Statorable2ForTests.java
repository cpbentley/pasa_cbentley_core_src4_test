/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.testing.ctx.TestCtx;

public class Statorable2ForTests extends StatorableAbstractForTests {

   private IFancyStuffTest     fancyStuff;

   private String              name;

   private Statorable1ForTests state1;

   public Statorable2ForTests(TestStatorCtx tsc) {
      super(tsc);

   }

   public IFancyStuffTest getFancyStuff() {
      return fancyStuff;
   }

   public String getName() {
      return name;
   }
   
   public ICtx getCtxOwner() {
      return tsc;
   }

   public Statorable1ForTests getState1() {
      return state1;
   }

   public void setFancyStuff(IFancyStuffTest fancyStuff) {
      this.fancyStuff = fancyStuff;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setState1(Statorable1ForTests state1) {
      this.state1 = state1;
   }

   public void stateReadFrom(StatorReader state) {
      name = state.getReader().readString();
      //here we know 100% the name of the implementation ?or
      state1 = (Statorable1ForTests) state.readObject(tsc, state1);
      //use this if interface or sub implementation
      fancyStuff = (IFancyStuffTest) state.readObject(tsc, fancyStuff);
   }

   public int getStatorableClassID() {
      return ITechStatorableTest.CLASSID_Statorable2ForTests;
   }

   public void stateWriteTo(StatorWriter state) {
      state.getWriter().writeString(name);
      state.writerToStatorable(state1);
      state.writerToStatorable(fancyStuff);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "TestStatorable2");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.nlLvl(state1);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "TestStatorable2");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("name", name);
   }

   //#enddebug

}
