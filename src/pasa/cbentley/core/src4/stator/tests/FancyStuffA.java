package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.ctx.ObjectU;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;

public class FancyStuffA extends ObjectU implements IFancyStuffTest {

   private String        fancyString;

   private TestStatorCtx tsc;

   public FancyStuffA(TestStatorCtx tsc) {
      super(tsc.getUC());
      this.tsc = tsc;
   }

   public ICtx getCtxOwner() {
      return tsc;
   }

   public String getFancyString() {
      return fancyString;
   }

   public int getStatorableClassID() {
      return ITechStatorableTest.CLASSID_CLASSID_FANCYA;
   }

   public void setFancyString(String fancyString) {
      this.fancyString = fancyString;
   }

   public void stateReadFrom(StatorReader state) {
      fancyString = state.getReader().readString();
   }

   public void stateWriteTo(StatorWriter state) {
      state.getWriter().writeString(fancyString);
   }

   public void stateWriteToParamSub(StatorWriter state) {

   }

}
