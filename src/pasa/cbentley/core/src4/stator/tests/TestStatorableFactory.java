/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import pasa.cbentley.core.src4.stator.IStatorFactory;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStatorableFactory implements IStatorFactory {

   protected final TestCtx tc;

   public TestStatorableFactory(TestCtx tc) {
      this.tc = tc;

   }

   public Object createObject(StatorReader state) {

      int classID = state.getDataReader().readInt();
      if (classID == 0) {
         TestStatorable1 ts1 = new TestStatorable1(tc);
         ts1.stateReadFrom(state);
         return ts1;
      }
      if (classID == 1) {
         TestStatorable2 ts2 = new TestStatorable2(tc);
         ts2.stateReadFrom(state);
         return ts2;
      }
      return null;
   }

   public Object createObject(StatorReader state, Class type) {
      if (type == TestStatorable1.class) {
         TestStatorable1 ts1 = new TestStatorable1(tc);
         ts1.stateReadFrom(state);
         return ts1;
      }
      if (type == TestStatorable2.class) {
         TestStatorable2 ts2 = new TestStatorable2(tc);
         ts2.stateReadFrom(state);
         return ts2;
      }
      return null;
   }

   public boolean isTypeSupported(Class cl) {
      if (cl == TestStatorable1.class || cl == TestStatorable2.class) {
         return true;
      }
      return false;
   }

   public Object[] createArray(Class cl, int size) {
      if (cl == TestStatorable1.class) {
         return new TestStatorable1[size];
      }
      if (cl == TestStatorable2.class) {
         return new TestStatorable2[size];
      }
      return null;
   }
}
