package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.ctx.ObjectU;
import pasa.cbentley.core.src4.stator.IStatorable;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.core.src4.structs.BufferObject;

public class Level3Statorable extends ObjectU implements IStatorable, ITechStatorableTest {

   private BufferObject        buffer;

   private Statorable1ForTests s1;

   private Statorable2ForTests s2;

   private TestStatorCtx       tsc;

   public Level3Statorable(TestStatorCtx tsc) {
      super(tsc.getUC());
      this.tsc = tsc;
      buffer = new BufferObject(uc);
   }

   public void clear() {
      buffer.clear();
   }

   public BufferObject getBuffer() {
      return buffer;
   }

   public ICtx getCtxOwner() {
      return tsc;
   }

   public int getStatorableClassID() {
      return CLASSID_Lvl3;
   }

   public void populateBuffer() {

      FancyStuffA f1 = new FancyStuffA(tsc);
      f1.setFancyString("f1a");

      FancyStuffA f2 = new FancyStuffA(tsc);
      f2.setFancyString("f2a");

      FancyStuffB f3 = new FancyStuffB(tsc);
      f3.setFancyString("f2B");

      buffer.add(f1);
      buffer.add(f2);
      buffer.add(f3);
      buffer.add(f3);
      buffer.add(f3);
      buffer.add(f2);
      buffer.add(f1);
   }

   public void stateReadFrom(StatorReader state) {
      s1 = (Statorable1ForTests) state.dataReadObject(tsc, s1);
      s2 = (Statorable2ForTests) state.dataReadObject(tsc, s2);

      int len = state.readStartIndex();

      if (buffer != null && buffer.getLength() == len) {
         //replace
         for (int i = 0; i < len; i++) {
            IStatorable object = (IStatorable) buffer.get(i);
            Object s = state.dataReadObject(object);
            buffer.setUnsafe(i, s);
         }
      } else {
         //new one
         buffer = new BufferObject(uc);
         for (int i = 0; i < len; i++) {
            Object s = state.dataReadObject();
            buffer.add(s);
         }
      }
   }

   public void stateWriteTo(StatorWriter state) {
      state.dataWriterToStatorable(s1);
      state.dataWriterToStatorable(s2);
      int len = buffer.getLength();
      state.dataWriteStartIndex(len);
      for (int i = 0; i < len; i++) {
         IStatorable object = (IStatorable) buffer.get(i);
         state.dataWriterToStatorable(object);
      }

   }

   public void stateWriteToParamSub(StatorWriter state) {

   }
}
