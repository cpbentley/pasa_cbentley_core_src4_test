/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.thread.tests;

import pasa.cbentley.core.src4.ctx.IEventsCore;
import pasa.cbentley.core.src4.event.IEventBus;
import pasa.cbentley.core.src4.thread.PulseThread;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestPulseThread extends TestCaseBentley  {

   PulseThread pulse = null;

   public TestPulseThread() {
      super();

      setTestFlag(TEST_FLAG_01_PRINT_ANYWAYS, true);
   }

   public void setupAbstract() {
   }

   public void testPulseThread() throws InterruptedException {

      IEventBus eventBusRoot = uc.getEventBusRoot();
      int pulseEventPID = eventBusRoot.createNewProducerID(IEventsCore.PID_05_XX);
      assertEquals(5, pulseEventPID);

      PulseConsumerForTests pct = new PulseConsumerForTests(uc);
      eventBusRoot.addConsumer(pct, pulseEventPID, IEventsCore.PID_05_THREAD_0_ANY);

      //#debug
      toDLog().pTest("IEventBus after adding consumer", eventBusRoot, TestPulseThread.class, "testPulseThread", LVL_05_FINE, false);

      pulse = new PulseThread(eventBusRoot, PulseThread.STATE_0_ON, pulseEventPID);

      pulse.setOnOffWaitTimes(40, 40);

      pulse.setNumPulses(5);
      
      assertEquals(false, pulse.isPulseRunning());
      
      assertEquals(pulse.getPulseState(), PulseThread.STATE_0_ON);

      pulse.start();

      assertEquals(true, pulse.isPulseRunning());

      assertEquals(pulse.getPulseState(), PulseThread.STATE_0_ON);

      Thread.sleep(600);

      assertEquals(5, pct.getPulseOnCount());
      
      pulse.resetToOn();

      Thread.sleep(200);

      pulse.setPulseState(PulseThread.STATE_2_SHUT_DOWN);

   }

}
