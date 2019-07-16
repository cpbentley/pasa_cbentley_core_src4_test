/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.logging.tests;

import pasa.cbentley.core.src4.logging.Config;
import pasa.cbentley.core.src4.logging.DLogEntry;
import pasa.cbentley.core.src4.logging.DLogEntryOfConfig;
import pasa.cbentley.core.src4.logging.ITechConfig;
import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.testing.BentleyTestCase;

public class TestConfig extends BentleyTestCase implements ITechTags, ITechConfig {

   public void setupAbstract() {

   }

   public void testConfigDefault() {

      Config c = new Config(uc);

      assertEquals(c.getLogLevel(), LVL_05_FINE);
      c.setLevelGlobal(LVL_03_FINEST);
      assertEquals(c.getLogLevel(), LVL_03_FINEST);

      DLogEntry entry = new DLogEntry();
      entry.setLevel(LVL_05_FINE);
      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);

      assertEquals(true, ec.isAccepted());
      assertEquals(false, ec.isOneLineConfig());
      assertEquals(false, ec.isStackConfig());

   }

   public void testConfigClassNegative() {

      Config c = new Config(uc);
      c.setClassNegative(Integer.class, true);

      DLogEntry entry = new DLogEntry();
      entry.setLevel(LVL_05_FINE);
      entry.setClassL(Integer.class);

      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(false, ec.isAccepted());

      DLogEntry entryString = new DLogEntry();
      entryString.setClassL(String.class);
      ec = entryString.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

   }

   public void testConfigFlagTag() {

      Config c = new Config(uc);
      //by default all flags not explicitely set are refused
      c.setFlagTag(FLAG_05_PRINT_UI, false); //do not print UI

      c.setFlagTag(FLAG_06_PRINT_WORK, true);

      DLogEntry entry = new DLogEntry();
      entry.setTagID(FLAG_05_PRINT_UI);
      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(false, ec.isAccepted());

      entry = new DLogEntry();
      entry.setTagID(FLAG_09_PRINT_FLOW);
      ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(false, ec.isAccepted());

      entry = new DLogEntry();
      entry.setTagID(FLAG_06_PRINT_WORK);
      ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

   }

   public void testConfigFlagTagAllPositives() {

      Config c = new Config(uc);
      c.setFlagPrint(MASTER_FLAG_02_OPEN_ALL_PRINT, true);

      c.setFlagTag(FLAG_05_PRINT_UI, false);
      c.setFlagTag(FLAG_06_PRINT_WORK, false);

      DLogEntry entry = new DLogEntry();
      entry.setTagID(FLAG_05_PRINT_UI);
      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

      entry = new DLogEntry();
      entry.setTagID(FLAG_09_PRINT_FLOW);
      ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

      entry = new DLogEntry();
      entry.setTagID(FLAG_06_PRINT_WORK);
      ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

      assertEquals(null, entry.getThreadName());
   }

   public void testThreadName() {
      Config c = new Config(uc);
      c.setFlagPrint(MASTER_FLAG_02_OPEN_ALL_PRINT, true);
      DLogEntry entry = new DLogEntry();
      entry.setDevFlags(DEV_4_THREAD);

      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);

      assertEquals(true, ec.hasConfigFlag(ITechConfig.CONFIG_FLAG_04_SHOW_THREAD));
      assertEquals(true, ec.isAccepted());
      assertNotNull(entry.getThreadName());
      assertEquals("main", entry.getThreadName());

   }
}
