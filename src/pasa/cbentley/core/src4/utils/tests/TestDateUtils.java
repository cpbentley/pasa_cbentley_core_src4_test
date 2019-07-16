/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import java.util.Calendar;

import pasa.cbentley.core.src4.utils.DateUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestDateUtils extends BentleyTestCase {

   private DateUtils du = new DateUtils(uc);

   public TestDateUtils() {
      super(true);
   }

   public void testDeci() {

      long d = 101;

      String s = du.getPeriodSecDeci(121);

      assertEquals("12,1", s);

      String s2 = du.getPeriodSecDeci(12);

      assertEquals("1,2", s2);

      s2 = du.getPeriodSecDeci(0);

      assertEquals("0", s2);

      s2 = du.getPeriodSecDeci(5);

      assertEquals("5", s2);
   }

   public void testConversionLongMinuteLong() {
      long longFullDate = du.getDateFromDslashMslashY("5/1/2007");
      int dateMinute = du.getIntDateMinute(longFullDate);
      assertEquals("5/1/2007", du.getDslashMslashYFromMin(dateMinute));

   }

   public void testConversionLongMinuteDayLong() {
      long longFullDate = du.getDateFromDslashMslashY("5/1/2007");
      int dateMinute = du.getIntDateMinute(longFullDate);
      assertEquals("5/1/2007", du.getDslashMslashYFromMin(dateMinute));

      int dateDayFromMin = du.getIntDateDayFromMin(dateMinute);
      assertEquals(13518, dateDayFromMin);

      long date13MillisFromDay = du.getLongDateFromDateDay(dateDayFromMin);

      assertEquals("5/1/2007", du.getDslashMslashY(date13MillisFromDay));
   }

   public void testBasic() {

      Calendar cal = Calendar.getInstance();
      cal.set(2007, Calendar.JANUARY, 13);

      long calMillis = cal.getTimeInMillis();
      assertEquals("13/1/2007", du.getDslashMslashY(calMillis));

      int dateMin13FromCal = du.getIntDateMinute(calMillis);
      assertEquals("13/1/2007", du.getDslashMslashYFromMin(dateMin13FromCal));
      assertEquals("13/1/7", du.getDslashMslashYsmall(dateMin13FromCal));

      long ld13 = du.getDateFromDslashMslashY("13/1/2007");
      int dateMin13 = du.getIntDateMinute(ld13);
      assertEquals("13/1/2007", du.getDslashMslashYFromMin(dateMin13));
      int dateDay13FromMin = du.getIntDateDayFromMin(dateMin13);
      long date13MillisFromDay = du.getLongDateFromDateDay(dateDay13FromMin);

      assertEquals("13/1/2007", du.getDslashMslashY(date13MillisFromDay));

      //test the function property on 14:
      long ld14 = du.getDateFromDslashMslashY("14/1/2007");
      int dateMin14 = du.getIntDateMinute(ld14);
      assertEquals("14/1/2007", du.getDslashMslashYFromMin(dateMin14));
      int dateDay14FromMin = du.getIntDateDayFromMin(dateMin14);
      long date14MillisFromDay = du.getLongDateFromDateDay(dateDay14FromMin);
      assertEquals("14/1/2007", du.getDslashMslashY(date14MillisFromDay));

      long ld15 = du.getDateFromDslashMslashY("15/1/2007");
      long ld16 = du.getDateFromDslashMslashY("16/1/2007");

      //going from long to minDate to DayDate. you lose data
      int day13 = du.getIntDateDayFromMin(dateMin13);

      long day13B = du.getLongDateFromDateDay(day13);
      assertEquals("13/1/2007", du.getDslashMslashY(day13B));

      int day14 = du.getIntDateDayFromLong(ld14);

      long day14Millis = du.getLongDateFromDateDay(day14);
      assertEquals("14/1/2007", du.getDslashMslashY(day14Millis));

      int day15 = du.getIntDateDayFromLong(ld15);
      int day16 = du.getIntDateDayFromLong(ld16);

      int day16Min = du.getIntDateMinFromDayDate(day16);
      assertEquals("16/1/2007", du.getDslashMslashYFromMin(day16Min));

      assertEquals(13526, day13);
      assertEquals(13527, day14);
      assertEquals(13528, day15);
      assertEquals(13529, day16);

      assertEquals(1, day14 - day13);

   }

   public void setupAbstract() {

   }

}
