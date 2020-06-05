/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.thread.tests;

import pasa.cbentley.core.src4.event.BusEvent;
import pasa.cbentley.core.src4.event.IEventBus;
import pasa.cbentley.core.src4.event.IEventConsumer;
import pasa.cbentley.core.src4.thread.PulseThread;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestPulseThread extends TestCaseBentley implements IEventConsumer {

   PulseThread         pt = null;


   public TestPulseThread() {
      super(true);

   }

   public void testPulseThread() throws InterruptedException {

      IEventBus eventBusRoot = uc.getEventBusRoot();
      int pulseEventPID = eventBusRoot.createNewProducerID(1);
      eventBusRoot.addConsumer(this, PulseThread.EVENT_ID_0_PULSE, pulseEventPID);

      pt = new PulseThread(eventBusRoot, PulseThread.STATE_0_ON, pulseEventPID);

      assertEquals(pt.getPulseState(), PulseThread.STATE_0_ON);

      pt.start();

      Thread.sleep(1000);

      pt.resetToOn();

      Thread.sleep(5000);

      pt.setPulseState(PulseThread.STATE_2_SHUT_DOWN);

   }

   /**
    * Event when pulse is going as ON
    */
   public void consumeEvent(BusEvent e) {
      if (e.getProducer() == pt) {
         System.out.println("Pulse " + PulseThread.debuState(e.getParam1()));
      }
   }

   public void setupAbstract() {
   }

}
