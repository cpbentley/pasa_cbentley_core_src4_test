/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.ctx.tests;

import java.util.Random;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.event.IEventBus;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestUCtx extends TestCaseBentley {

   public TestUCtx() {
      
   }
   public void setupAbstract() {

   }

   public void testBusOnStart() {

      UCtx uc = new UCtx();

      IEventBus eventBusRoot = uc.getEventBusRoot();

      //#debug
      toDLog().pTest("", eventBusRoot, TestUCtx.class, "testBusOnStart", LVL_05_FINE, false);

   }

   public void testCreationTwice() {
      //you can create 2 core ctxs
      UCtx uc1 = new UCtx();

      UCtx uc2 = new UCtx();

      assertEquals(1, uc1.getRegistrationID());
      assertEquals(1, uc2.getRegistrationID());
   }


   public void testSingleCreation() {

      UCtx uc = new UCtx();

      assertNotNull(uc.getIU());
      assertNotNull(uc.getAU());
      assertNotNull(uc.getBU());
      assertNotNull(uc.getColorU());
      assertNotNull(uc.getCU());
      assertNotNull(uc.getLU());
      assertNotNull(uc.getMem());
      assertNotNull(uc.getStrU());
      assertNotNull(uc.getSU());
      assertNotNull(uc.getUrlU());
      assertNotNull(uc.getUserLog());

      assertEquals(1, uc.getRegistrationID());
   }

   public void testSingleSetter() {

      UCtx uc = new UCtx();

      assertNotNull(uc.getStrU());
      uc.setStrU(null); // no effect
      assertNotNull(uc.getStrU());

      //this is how to set a new kind of util object
      StringUtils su = new StringUtils(uc);
      uc.setStrU(su);

      assertEquals(su, uc.getStrU());
   }
}
