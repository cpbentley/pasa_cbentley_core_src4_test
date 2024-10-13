/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;

public class Statorable1ForTests extends StatorableAbstractForTests {

   private int    intColor;

   private String stringColor;

   public Statorable1ForTests(TestStatorCtx tc) {
      super(tc);

   }

   public ICtx getCtxOwner() {
      return tsc;
   }

   public int getIntColor() {
      return intColor;
   }

   public int getStatorableClassID() {
      return ITechStatorableTest.CLASSID_Statorable1ForTests;
   }

   public String getStringColor() {
      return stringColor;
   }

   public void setIntColor(int intColor) {
      this.intColor = intColor;
   }

   public void setStringColor(String stringColor) {
      this.stringColor = stringColor;
   }

   public void stateReadFrom(StatorReader state) {
      setStringColor(state.getReader().readString());
      setIntColor(state.getReader().readInt());
   }

   public void stateWriteTo(StatorWriter state) {
      state.getWriter().writeString(getStringColor());
      state.getWriter().writeInt(getIntColor());
   }

   public void stateWriteToParamSub(StatorWriter state) {

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "TestStatorable1");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "TestStatorable1");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("stringColor", stringColor);
      dc.appendVarWithSpace("intColor", intColor);
   }

   //#enddebug

}
