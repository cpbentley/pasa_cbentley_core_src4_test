/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.stator.tests;

import javax.swing.text.LabelView;

import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.stator.IStatorFactory;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.testing.ctx.TestCtx;

public class StatorableFactoryForTests implements IStatorFactory, ITechStatorableTest {

   protected final TestCtx tc;

   private TestStatorCtx   tsc;

   public StatorableFactoryForTests(TestStatorCtx tsc) {
      this.tsc = tsc;
      this.tc = tsc.getTC();

   }


   public ICtx getCtx() {
      return tsc;
   }
   
   public Object createObject(int classID) {
      switch (classID) {
         case CLASSID_Statorable1ForTests:
            return new Statorable1ForTests(tsc);
         case CLASSID_Statorable2ForTests:
            return new Statorable2ForTests(tsc);
         case CLASSID_CLASSID_FANCYA:
            return new FancyStuffA(tsc);
         case CLASSID_FANCYB:
            return new FancyStuffB(tsc);
         case CLASSID_Lvl2:
            return new Level2Statorable(tsc);
         case CLASSID_Lvl3:
            return new Level3Statorable(tsc);

         default:
            return null;
      }
   }

   public Object[] createArray(int classID, int size) {
      switch (classID) {
         case CLASSID_Statorable1ForTests:
            return new Statorable1ForTests[size];
         case CLASSID_Statorable2ForTests:
            return new Statorable2ForTests[size];
         case CLASSID_CLASSID_FANCYA:
            return new FancyStuffA[size];
         case CLASSID_FANCYB:
            return new FancyStuffB[size];
         case CLASSID_Lvl2:
            return new Level2Statorable[size];
         case CLASSID_Lvl3:
            return new Level3Statorable[size];

         default:
            return null;
      }
   }

   public boolean isSupported(int classID) {
      switch (classID) {
         case CLASSID_Statorable1ForTests:
         case CLASSID_Statorable2ForTests:
         case CLASSID_CLASSID_FANCYA:
         case CLASSID_FANCYB:
         case CLASSID_Lvl2:
         case CLASSID_Lvl3:
            return true;
         default:
            return false;
      }
   }
}
