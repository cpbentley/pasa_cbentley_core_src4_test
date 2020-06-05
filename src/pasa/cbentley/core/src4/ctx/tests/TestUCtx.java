/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.ctx.tests;

import java.util.Random;

import pasa.cbentley.core.src4.ctx.CtxManager;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestUCtx extends TestCaseBentley {

   public void setupAbstract() {

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
      
      assertEquals(0, uc.getRegistrationID());
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

   public void testCreationTwice() {
      //you can create 2 core ctxs
      UCtx uc1 = new UCtx();

      UCtx uc2 = new UCtx();

      assertEquals(0, uc1.getRegistrationID());
      assertEquals(0, uc2.getRegistrationID());
   }
   
   public void testCreationTwiceSameManager() {
      //you can create 2 core ctxs
      Random r1 = new Random();
      UCtx uc1 = new UCtx();
      uc1.setRandom(r1);
      
      //same ctx with same ctx manager
      UCtx uc2 = new UCtx(uc1.getCtxManager());

      Random r2 = new Random();
      //why do this?
      //this uc2 will have different behavior
      uc2.setRandom(r2);
      
      assertEquals(0, uc1.getRegistrationID());
      assertEquals(0, uc2.getRegistrationID());
   }
}
