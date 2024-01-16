/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.core.src4.utils.tests;

import java.math.BigDecimal;

import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestBigDecimalUtils extends TestCaseBentley {

   public TestBigDecimalUtils() {
   }

   /**
    * Issue is that double cannot be trusted
    */
   public void testSeparateDecimalFromInteger() {
      Double d = new Double(156.1578);
      Double d2 = new Double(156.95789);
      Double d3 = new Double(156.95000);
      
      BigDecimal bigDecimal = BigDecimal.valueOf(d.doubleValue());
      BigDecimal bigDecimal2 = BigDecimal.valueOf(d2.doubleValue());
      BigDecimal bigDecimal3 = BigDecimal.valueOf(d3.doubleValue());
      
      assertEquals(4, bigDecimal.scale());
      assertEquals(5, bigDecimal2.scale());
      assertEquals(2, bigDecimal3.scale());
      assertEquals(2, bigDecimal3.stripTrailingZeros().scale());

      assertEquals(1561578, bigDecimal.unscaledValue().intValue());
      assertEquals(15695789, bigDecimal2.unscaledValue().intValue());
      
      assertEquals(156, bigDecimal.intValue());
      assertEquals(156, bigDecimal2.intValue());
      
      assertEquals(7, bigDecimal.precision());
      assertEquals(8, bigDecimal2.precision());
     
      assertEquals(0.1578, bigDecimal.remainder(BigDecimal.ONE).doubleValue());
      assertEquals(0.95789, bigDecimal2.remainder(BigDecimal.ONE).doubleValue());
      
      assertEquals(1578, bigDecimal.remainder(BigDecimal.ONE).unscaledValue().intValue());
      assertEquals(95789, bigDecimal2.remainder(BigDecimal.ONE).unscaledValue().intValue());
      
     
   }

   public void setupAbstract() {
   }
}
