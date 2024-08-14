/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.structs.FiFoQueue;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestFifoQueue extends TestCaseBentley {

   public TestFifoQueue() {
   }

   public void setupAbstract() {

   }

   public void testFifo() {
      FiFoQueue q = createNewQueue();

      assertEquals(0, q.size());
      q.put("1");

      assertEquals(1, q.size());

      assertEquals("1", q.getHead());

      q.put("1");
      q.put("2");
      q.put("3");
      q.put("4");
      assertEquals("1", q.getHead());
      assertEquals("2", q.getHead());
      assertEquals("3", q.getHead());
      assertEquals("4", q.getHead());

      assertEquals(0, q.size());

   }

   private FiFoQueue createNewQueue() {
      return new FiFoQueue(new UCtx());
   }

   public void testTail() {
      FiFoQueue q = createNewQueue();

      assertEquals(0, q.size());
      assertEquals(null, q.getTail());

      q.put("1");

      assertEquals("1", q.getTail());

      q.put("2");

      assertEquals("2", q.getTail());

      assertEquals("1", q.getHead());

      assertEquals("2", q.getTail());
      assertEquals("2", q.getHead());
      assertEquals(null, q.getTail());

   }
   public void testSize() {
      FiFoQueue q = createNewQueue();

      q.put("1");
      assertEquals(1, q.size());
      q.put("2");
      assertEquals(2, q.size());
      q.put("3");
      assertEquals(3, q.size());

      assertEquals("1", q.getHead());
      assertEquals(2, q.size());
      assertEquals("2", q.getHead());
      assertEquals(1, q.size());
      assertEquals("3", q.getHead());
      assertEquals(0, q.size());

      assertEquals(null, q.getHead());
      assertEquals(0, q.size());

      q.put("1");
      assertEquals(1, q.size());

   }
   
   public void testOverFlow() {
      FiFoQueue q = createNewQueue();

      q.put("1");
      q.put("2");
      q.put("3");
      q.put("4");
      q.put("5");
      q.put("6");

      assertEquals(6, q.size());

      assertEquals("1", q.getHead());
      assertEquals("2", q.getHead());
      assertEquals("3", q.getHead());
      assertEquals("4", q.getHead());
      assertEquals("5", q.getHead());
      assertEquals("6", q.getHead());

      
   }

}
