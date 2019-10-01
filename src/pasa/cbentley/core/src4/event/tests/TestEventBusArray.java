/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.event.tests;

import pasa.cbentley.core.src4.ctx.IEventsCore;
import pasa.cbentley.core.src4.event.BusEvent;
import pasa.cbentley.core.src4.event.EventBusArray;
import pasa.cbentley.core.src4.event.EventConsumerAdapter;
import pasa.cbentley.core.src4.event.IEventConsumer;
import pasa.cbentley.core.src4.helpers.CounterInt;
import pasa.cbentley.core.src4.interfaces.ITechThread;
import pasa.cbentley.testing.BentleyTestCase;

public class TestEventBusArray extends BentleyTestCase implements ITechThread {
   int   count        = 0;

   int   countUser    = 0;

   int[] testTopology = new int[] { 3, 4, 5, 5 }; // 3 producers with num of events

   public void setupAbstract() {
      count = 0;
      countUser = 0;
   }

   public void testDynamicProducer() {
      EventBusArray bus = new EventBusArray(uc, uc, testTopology);

      final int dynamicallyProducedPID = bus.getNextProducerID(5);
      assertEquals(testTopology.length, dynamicallyProducedPID);

      final int eventID = 4;
      bus.addConsumer(new EventConsumerAdapter(uc) {
         public void consumeEvent(BusEvent e) {
            count++;
            assertEquals(eventID, e.getEventID());
            assertEquals(dynamicallyProducedPID, e.getProducerID());
            assertEquals(TestEventBusArray.this, e.getProducer());
         }
      }, dynamicallyProducedPID, eventID);

      assertEquals(count, 0);
      BusEvent be = bus.createEvent(dynamicallyProducedPID, 3, this);
      bus.putOnBus(be);
      assertEquals(count, 0);

      be = bus.createEvent(dynamicallyProducedPID, 4, this);
      bus.putOnBus(be);
      assertEquals(count, 1);

   }

   public void testEventUser() {
      EventBusArray bus = new EventBusArray(uc, uc, testTopology);

      BusEvent be = bus.createEvent(1, 0, this);
      be.setUserEvent();
      be.setParam1(5);
      be.setParam2(6);

      assertEquals(0, be.getEventID()); //can be zero
      assertEquals(true, be.hasFlag(BusEvent.FLAG_3_USER_EVENT));
      assertEquals(1, be.getProducerID());
      assertEquals(5, be.getParam1());
      assertEquals(6, be.getParam2());
      assertEquals(this, be.getProducer());
   }

   public void testNew1() {
      int eventID = 1;
      int producerID = 2;
      EventBusArray bus = new EventBusArray(uc, uc, testTopology);
      bus.addConsumer(new EventConsumerAdapter(uc) {
         public void consumeEvent(BusEvent e) {
            count++;
            assertEquals(1, e.getEventID());
            assertEquals(2, e.getProducerID());
            assertEquals(TestEventBusArray.this, e.getProducer());
         }
      }, producerID, eventID);
      assertEquals(count, 0);
      BusEvent be = bus.createEvent(producerID, eventID, this);
      bus.putOnBus(be);
      assertEquals(count, 1);
   }

   public void testNew2() {
      int eventID = 1;
      int producerID = 2;

      EventBusArray bus = new EventBusArray(uc, uc, testTopology);
      bus.addConsumer(new EventConsumerAdapter(uc) {
         public void consumeEvent(BusEvent e) {
            count++;
            assertEquals(1, e.getEventID());
            assertEquals(2, e.getProducerID());
            assertEquals(TestEventBusArray.this, e.getProducer());
         }
      }, producerID, eventID);

      bus.addConsumer(new EventConsumerAdapter(uc) {
         public void consumeEvent(BusEvent e) {
            count++;
            assertEquals(1, e.getEventID());
            assertEquals(2, e.getProducerID());
            assertEquals(TestEventBusArray.this, e.getProducer());
         }
      }, producerID, eventID);

      assertEquals(count, 0);
      BusEvent be = bus.createEvent(producerID, eventID, this);
      bus.putOnBus(be);
      assertEquals(count, 2);

      toDLog().pTest("", bus, TestEventBusArray.class, "");
   }

   public void testAnyEventID0() {
      EventBusArray bus = new EventBusArray(uc, uc, testTopology);
      IEventConsumer allMemoryEventConsumer = new EventConsumerAdapter(uc) {
         public void consumeEvent(BusEvent e) {
            count++;
            if (count == 1) {
               assertEquals(IEventsCore.EID_MEMORY_2_USER_REQUESTED_GC, e.getEventID());
            } else if (count == 2) {
               assertEquals(IEventsCore.EID_MEMORY_1_OUT_OF_MEMORY_GC, e.getEventID());
            } else {
               assertEquals(IEventsCore.EID_MEMORY_0_ANY, e.getEventID());
            }
            assertEquals(IEventsCore.PID_3_MEMORY, e.getProducerID());
            assertEquals(TestEventBusArray.this, e.getProducer());
         }
      };

      bus.addConsumer(allMemoryEventConsumer, IEventsCore.PID_3_MEMORY, IEventsCore.EID_MEMORY_0_ANY);

      bus.addConsumer(new EventConsumerAdapter(uc) {
         public void consumeEvent(BusEvent e) {
            countUser++;
            assertEquals(IEventsCore.EID_MEMORY_2_USER_REQUESTED_GC, e.getEventID());
            assertEquals(IEventsCore.PID_3_MEMORY, e.getProducerID());
            assertEquals(TestEventBusArray.this, e.getProducer());
         }
      }, IEventsCore.PID_3_MEMORY, IEventsCore.EID_MEMORY_2_USER_REQUESTED_GC);

      BusEvent be = bus.createEvent(IEventsCore.PID_3_MEMORY, IEventsCore.EID_MEMORY_2_USER_REQUESTED_GC, this);
      bus.putOnBus(be);

      assertEquals(1, count);
      assertEquals(1, countUser);

      BusEvent be2 = bus.createEvent(IEventsCore.PID_3_MEMORY, IEventsCore.EID_MEMORY_1_OUT_OF_MEMORY_GC, this);
      bus.putOnBus(be2);
      assertEquals(2, count);
      assertEquals(1, countUser);

      //you cannot create an event with id 0
      try {
         BusEvent be3 = bus.createEvent(IEventsCore.PID_3_MEMORY, IEventsCore.EID_MEMORY_0_ANY, this);
         bus.putOnBus(be3);
         assertNotReachable("eid 0");
         assertEquals(3, count);
         assertEquals(1, countUser);
      } catch (IllegalArgumentException e) {
         assertReachable();
      }

   }

   public void testPIDAny() {
      int eventID = 1;
      int producerID = 2;
      EventBusArray bus = new EventBusArray(uc, uc, testTopology);
      bus.addConsumer(new EventConsumerAdapter(uc) {
         public void consumeEvent(BusEvent e) {
            count++;
         }
      });

      bus.addConsumer(new EventConsumerAdapter(uc) {
         public void consumeEvent(BusEvent e) {
            count++;
            assertEquals(1, e.getEventID());
            assertEquals(2, e.getProducerID());
            assertEquals(TestEventBusArray.this, e.getProducer());
         }
      }, producerID, eventID);

      assertEquals(count, 0);
      BusEvent be = bus.createEvent(producerID, eventID, this);
      bus.putOnBus(be);
      assertEquals(count, 2);

      int otherPID = 1;
      BusEvent be2 = bus.createEvent(otherPID, eventID, this);
      bus.putOnBus(be2);
      assertEquals(count, 3);
   }
   
   
   public void testThreadModes() {
      EventBusArray bus = new EventBusArray(uc, uc, testTopology);
      
      CounterInt counter = new CounterInt(uc, 0);
      
      TestExecutor test = new TestExecutor(uc);
      
      bus.setExecutor(test);
      
      assertEquals(-1, test.getLastMode());
      
      final int eventID = 1;
      final int producerID = 2;
      
      EventConsumerAdapterTest consumer1 = new EventConsumerAdapterTest(uc, this, producerID, eventID, counter);
      bus.addConsumer(consumer1, producerID, eventID, THREAD_MODE_3_WORKER);
      
      assertEquals(counter.getCount(), 0);
      
      bus.sendNewEvent(producerID, eventID, this);
      
      assertEquals(counter.getCount(), 1);
      assertEquals(THREAD_MODE_3_WORKER, test.getLastMode());

      EventConsumerAdapterTest consumer2 = new EventConsumerAdapterTest(uc, this, producerID, eventID, counter);
      bus.addConsumer(consumer2, producerID, eventID, THREAD_MODE_1_MAIN_NOW);
      
      bus.sendNewEvent(producerID, eventID, this);
      assertEquals(counter.getCount(), 3);
      
      //consumers are called in the order they were added so the last mode on top of stack is the last one
      assertEquals(THREAD_MODE_1_MAIN_NOW, test.getLastMode());
      assertEquals(THREAD_MODE_3_WORKER, test.getLastMode());
      
      EventConsumerAdapterTest consumer3 = new EventConsumerAdapterTest(uc, this, producerID, eventID, counter);
      bus.addConsumer(consumer3, producerID, eventID, THREAD_MODE_2_MAIN_LATER);
      
      bus.sendNewEvent(producerID, eventID, this);
      assertEquals(counter.getCount(), 6);
      
      assertEquals(THREAD_MODE_2_MAIN_LATER, test.getLastMode());
      assertEquals(THREAD_MODE_1_MAIN_NOW, test.getLastMode());
      assertEquals(THREAD_MODE_3_WORKER, test.getLastMode());
     
      EventConsumerAdapterTest consumer4 = new EventConsumerAdapterTest(uc, this, producerID, eventID, counter);
      bus.addConsumer(consumer4, producerID, eventID, THREAD_MODE_0_POST_NOW);
      
      bus.sendNewEvent(producerID, eventID, this);
      assertEquals(counter.getCount(), 10);
      
      assertEquals(THREAD_MODE_2_MAIN_LATER, test.getLastMode());
      assertEquals(THREAD_MODE_1_MAIN_NOW, test.getLastMode());
      assertEquals(THREAD_MODE_3_WORKER, test.getLastMode());
      assertEquals(-1, test.getLastMode()); //post now is not executed by executor
     
      
      
     
   }
}
