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

public class TestStatorable1 extends TestStatorableAbstract {

   private String stringColor;

   private int    intColor;

   public TestStatorable1(TestCtx tc) {
      super(tc);

   }

   public void stateWriteTo(StatorWriter state) {
      state.getDataWriter().writeString(getStringColor());
      state.getDataWriter().writeInt(getIntColor());
   }

   public void stateReadFrom(StatorReader state) {
      setStringColor(state.getDataReader().readString());
      setIntColor(state.getDataReader().readInt());
   }

   public int getIntColor() {
      return intColor;
   }

   public void setIntColor(int intColor) {
      this.intColor = intColor;
   }

   public String getStringColor() {
      return stringColor;
   }

   public void setStringColor(String stringColor) {
      this.stringColor = stringColor;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "TestStatorable1");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("stringColor", stringColor);
      dc.appendVarWithSpace("intColor", intColor);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "TestStatorable1");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
