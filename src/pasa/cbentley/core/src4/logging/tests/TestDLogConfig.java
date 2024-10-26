/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.logging.tests;

import pasa.cbentley.core.src4.logging.DLogConfig;
import pasa.cbentley.core.src4.logging.DLogEntry;
import pasa.cbentley.core.src4.logging.DLogEntryOfConfig;
import pasa.cbentley.core.src4.logging.ITechDLogConfig;
import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestDLogConfig extends TestCaseBentley implements ITechTags, ITechDLogConfig {

   public void setupAbstract() {

   }

   public void testConfigDefault() {

      DLogConfig c = new DLogConfig(uc);

      assertEquals(c.getLogLevel(), LVL_05_FINE);
      c.setLevelGlobal(LVL_03_FINEST);
      assertEquals(c.getLogLevel(), LVL_03_FINEST);

      DLogEntry entry = new DLogEntry(uc);
      entry.setLevel(LVL_05_FINE);
      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);

      assertEquals(true, ec.isAccepted());
      assertEquals(false, ec.isOneLineConfig());
      assertEquals(false, ec.isStackConfig());

   }

   public void testConfigClassNegative() {

      DLogConfig c = new DLogConfig(uc);
      c.setClassNegative(Integer.class, true);

      DLogEntry entry = new DLogEntry(uc);
      entry.setLevel(LVL_05_FINE);
      entry.setClassL(Integer.class);

      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(false, ec.isAccepted());

      DLogEntry entryString = new DLogEntry(uc);
      entryString.setClassL(String.class);
      ec = entryString.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

   }

   public void testConfigFlagTag() {

      DLogConfig c = new DLogConfig(uc);
      //by default all flags not explicitely set are refused
      c.setFlagTag(FLAG_05_PRINT_UI, false); //do not print UI

      c.setFlagTag(FLAG_06_PRINT_WORK, true);

      DLogEntry entry = new DLogEntry(uc);
      entry.setTagID(FLAG_05_PRINT_UI);
      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(false, ec.isAccepted());

      entry = new DLogEntry(uc);
      entry.setTagID(FLAG_09_PRINT_FLOW);
      ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(false, ec.isAccepted());

      entry = new DLogEntry(uc);
      entry.setTagID(FLAG_06_PRINT_WORK);
      ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

   }

   public void testConfigFlagTagAllPositives() {

      DLogConfig c = new DLogConfig(uc);
      c.setFlagMaster(MASTER_FLAG_02_OPEN_ALL_PRINT, true);

      c.setFlagTag(FLAG_05_PRINT_UI, false);
      c.setFlagTag(FLAG_06_PRINT_WORK, false);

      DLogEntry entry = new DLogEntry(uc);
      entry.setTagID(FLAG_05_PRINT_UI);
      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

      entry = new DLogEntry(uc);
      entry.setTagID(FLAG_09_PRINT_FLOW);
      ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

      entry = new DLogEntry(uc);
      entry.setTagID(FLAG_06_PRINT_WORK);
      ec = entry.computeDLogEntryOfConfig(c);
      assertEquals(true, ec.isAccepted());

      assertEquals(null, entry.getThreadName());
   }

   public void testThreadName() {
      DLogConfig c = new DLogConfig(uc);
      c.setFlagMaster(MASTER_FLAG_02_OPEN_ALL_PRINT, true);
      DLogEntry entry = new DLogEntry(uc);
      entry.setDevFlags(DEV_4_THREAD);

      DLogEntryOfConfig ec = entry.computeDLogEntryOfConfig(c);

      assertEquals(true, ec.hasFormatFlag(ITechDLogConfig.FORMAT_FLAG_04_THREAD));
      assertEquals(true, ec.isAccepted());
      assertNotNull(entry.getThreadName());
      assertEquals("main", entry.getThreadName());

   }
}
