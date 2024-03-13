package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.ctx.ObjectU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.stator.IStatorable;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.core.src4.stator.StatorWriter;
import pasa.cbentley.core.src4.structs.BufferObject;

public class Level2Statorable extends ObjectU implements IStatorable, ITechStatorableTest {

   private TestStatorCtx       tsc;

   private Statorable1ForTests s1;

   private Statorable2ForTests s2;

   private BufferObject        buffer;

   public Level2Statorable(TestStatorCtx tsc) {
      super(tsc.getUC());
      this.tsc = tsc;
      buffer = new BufferObject(uc);
   }

   public int getStatorableClassID() {
      return CLASSID_Lvl2;
   }

   public void populateBufferF() {

      FancyStuffA f1 = new FancyStuffA(tsc);
      f1.setFancyString("f1a");

      FancyStuffA f2 = new FancyStuffA(tsc);
      f2.setFancyString("f2a");

      FancyStuffB f3 = new FancyStuffB(tsc);
      f3.setFancyString("f3b");

      buffer.add(f1);
      buffer.add(f2);
      buffer.add(f3);
      buffer.add(f3);
      buffer.add(f3);
      buffer.add(f2);
      buffer.add(f1);
   }
   
   public void populateBufferG() {

      FancyStuffA f1 = new FancyStuffA(tsc);
      f1.setFancyString("g1a");

      FancyStuffA f2 = new FancyStuffA(tsc);
      f2.setFancyString("g2a");

      FancyStuffB f3 = new FancyStuffB(tsc);
      f3.setFancyString("g3b");

      buffer.add(f1);
      buffer.add(f2);
      buffer.add(f3);
      buffer.add(f3);
      buffer.add(f3);
      buffer.add(f2);
      buffer.add(f1);
   }

   public BufferObject getBuffer() {
      return buffer;
   }

   public Statorable2ForTests getS2() {
      return s2;
   }

   public Statorable1ForTests getS1() {
      return s1;
   }

   public ICtx getCtxOwner() {
      return tsc;
   }

   public void clear() {
      buffer.clear();
   }

   public void stateWriteTo(StatorWriter state) {
      state.writerToStatorable(s1);
      state.writerToStatorable(s2);
      int len = buffer.getLength();
      
      state.writeStartIndex(len);
      for (int i = 0; i < len; i++) {
         IStatorable object = (IStatorable) buffer.get(i);
         state.writerToStatorable(object);
      }

   }

   public void stateReadFrom(StatorReader state) {
      s1 = (Statorable1ForTests) state.readObject(tsc, s1);
      s2 = (Statorable2ForTests) state.readObject(tsc, s2);

      int len = state.readStartIndex();

      if (buffer != null && buffer.getLength() == len) {
         //replace
         for (int i = 0; i < len; i++) {
            IStatorable object = (IStatorable) buffer.get(i);
            Object s = state.readObject(object);
            buffer.setUnsafe(i, s);
         }
      } else {
         //new one
         buffer = new BufferObject(uc);
         for (int i = 0; i < len; i++) {
            Object s = state.readObject();
            buffer.add(s);
         }
      }
   }
}
