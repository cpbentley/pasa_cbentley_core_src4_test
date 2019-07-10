package pasa.cbentley.core.src4.structs.tests;

import pasa.cbentley.core.src4.structs.IntToInts;
import pasa.cbentley.testing.BentleyTestCase;

public class TestIntToInts extends BentleyTestCase {

   public TestIntToInts() {
      super(false);
   }

   public void testIntToInts_Add() {
      IntToInts ii = new IntToInts(uc);
      ii.add(0, 1);
      ii.add(1, 2);
      ii.add(2, 3);
      ii.add(4, 5);
      System.out.println(ii);

      assertEquals(1, ii.getDuoFromUno(0));
      assertEquals(2, ii.getDuoFromUno(1));
      assertEquals(3, ii.getDuoFromUno(2));
      assertEquals(5, ii.getDuoFromUno(4));

      assertEquals(Integer.MAX_VALUE, ii.getDuoFromUno(3));

      ii = new IntToInts(uc, IntToInts.TYPE_1UNO_ORDER);

      ii.add(5, 15);
      ii.add(1, 11);
      System.out.println(ii);

      ii.add(6, 16);
      System.out.println(ii);

      ii.add(4, 14);
      System.out.println(ii);
      ii.add(5, 15);
      ii.add(55, 155);
      ii.add(0, 10);

      assertEquals(15, ii.getDuoFromUno(5));
      assertEquals(11, ii.getDuoFromUno(1));
      assertEquals(16, ii.getDuoFromUno(6));
      assertEquals(14, ii.getDuoFromUno(4));
      assertEquals(155, ii.getDuoFromUno(55));
      assertEquals(10, ii.getDuoFromUno(0));
      System.out.println(ii);
      ii.removeUno(5);
      System.out.println(ii);

      assertEquals(Integer.MAX_VALUE, ii.getDuoFromUno(5));

   }

   public void testIntToInts_Remove() {
      IntToInts pointersData = new IntToInts(uc);

      pointersData.addUnoDuo(4, 5);

      assertEquals(3, pointersData.getLenData());
      assertEquals(1, pointersData.getNumKeys());

      assertEquals(4, pointersData.getIndexedUno(0));
      assertEquals(5, pointersData.getIndexedDuo(0));

      pointersData.addUnoDuo(5, 6);

      assertEquals(5, pointersData.getLenData());
      assertEquals(2, pointersData.getNumKeys());

      pointersData.removeUno(4);

      assertEquals(3, pointersData.getLenData());
      assertEquals(1, pointersData.getNumKeys());

      assertEquals(5, pointersData.getIndexedUno(0));
      assertEquals(6, pointersData.getIndexedDuo(0));

      pointersData.removeUno(6);
      assertEquals(3, pointersData.getLenData());
      assertEquals(1, pointersData.getNumKeys());

      pointersData.removeUno(5);

      assertEquals(1, pointersData.getLenData());
      assertEquals(0, pointersData.getNumKeys());

   }

   public void setUpMord() {

   }
}
