package pasa.cbentley.core.src4.thread.tests;

import pasa.cbentley.core.src4.ctx.IEventsCore;
import pasa.cbentley.core.src4.ctx.ObjectU;
import pasa.cbentley.core.src4.ctx.ToStringStaticUc;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.event.BusEvent;
import pasa.cbentley.core.src4.event.IEventConsumer;
import pasa.cbentley.core.src4.logging.Dctx;

public class PulseConsumerForTests extends ObjectU implements IEventConsumer {

   private int pulseOnCount;

   private int pulseOffCount;

   public PulseConsumerForTests(UCtx uc) {
      super(uc);
   }

   public void consumeEvent(BusEvent e) {
      if (e.getEventID() == IEventsCore.PID_5_THREAD_1_PULSE_ON) {
         pulseOnCount++;
      } else if (e.getEventID() == IEventsCore.PID_5_THREAD_1_PULSE_ON) {
         pulseOffCount++;
      }
      System.out.println("Pulse " + ToStringStaticUc.toStringThreadState(e.getParam1()));
   }

   public int getPulseOffCount() {
      return pulseOffCount;
   }

   public int getPulseOnCount() {
      return pulseOnCount;
   }

   public void setPulseCount(int pulseCount) {
      this.pulseOnCount = pulseCount;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, PulseConsumerForTests.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("pulseCount", pulseOnCount);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, PulseConsumerForTests.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
