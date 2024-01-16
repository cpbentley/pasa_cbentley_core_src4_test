/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.stator.IStatorFactory;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.testing.ctx.TestCtx;

public class StatorableFactoryForTests implements IStatorFactory {

   protected final TestCtx tc;

   public StatorableFactoryForTests(TestCtx tc) {
      this.tc = tc;

   }

   public Object createObject(StatorReader state) {

      int classID = state.getDataReader().readInt();
      if (classID == 0) {
         Statorable1ForTests ts1 = new Statorable1ForTests(tc);
         ts1.stateReadFrom(state);
         return ts1;
      }
      if (classID == 1) {
         Statorable2ForTests ts2 = new Statorable2ForTests(tc);
         ts2.stateReadFrom(state);
         return ts2;
      }
      return null;
   }

   public Object createObject(StatorReader state, Class type) {
      if (type == Statorable1ForTests.class) {
         Statorable1ForTests ts1 = new Statorable1ForTests(tc);
         ts1.stateReadFrom(state);
         return ts1;
      }
      if (type == Statorable2ForTests.class) {
         Statorable2ForTests ts2 = new Statorable2ForTests(tc);
         ts2.stateReadFrom(state);
         return ts2;
      }
      return null;
   }

   public boolean isTypeSupported(Class cl) {
      if (cl == Statorable1ForTests.class || cl == Statorable2ForTests.class) {
         return true;
      }
      return false;
   }

   public Object[] createArray(Class cl, int size) {
      if (cl == Statorable1ForTests.class) {
         return new Statorable1ForTests[size];
      }
      if (cl == Statorable2ForTests.class) {
         return new Statorable2ForTests[size];
      }
      return null;
   }
}
