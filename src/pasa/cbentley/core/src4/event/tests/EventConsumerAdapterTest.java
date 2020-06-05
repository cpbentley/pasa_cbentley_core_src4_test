/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.event.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.event.BusEvent;
import pasa.cbentley.core.src4.event.IEventConsumer;
import pasa.cbentley.core.src4.helpers.CounterInt;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;

public class EventConsumerAdapterTest implements IEventConsumer {

   protected final UCtx uc;
   private TestEventBusArray producer;
   private int pid;
   private int eid;
   private CounterInt counter;

   public EventConsumerAdapterTest(UCtx uc, TestEventBusArray producer, int pid, int eid, CounterInt counter) {
      this.uc = uc;
      this.producer = producer;
      this.pid = pid;
      this.eid = eid;
      this.counter = counter;
      
   }
   
   public void consumeEvent(BusEvent e) {
      counter.increment();
      TestEventBusArray.assertEquals(eid, e.getEventID());
      TestEventBusArray.assertEquals(pid, e.getProducerID());
      TestEventBusArray.assertEquals(producer, e.getProducer());
   }
   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "EventConsumerAdapterTest");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "EventConsumerAdapterTest");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return uc;
   }

   //#enddebug
   

}
