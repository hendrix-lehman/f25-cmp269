package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

  @Test
  void testAddingZeroPlusZeroEqualsZero() {
    Calculator calculator = new Calculator();
    int result = calculator.add(0, 0);
    assertEquals(0, result);
  }

  @Test
  void testAddingZeroPlusOneEqualsOne() {
    Calculator calculator = new Calculator();
    int result = calculator.add(0, 1);
    assertEquals(1, result);
  }

  @Test
  void testAddingTwoNegativeValuesShouldReturnNegative() {
    Calculator calculator = new Calculator();
    int result = calculator.add(-20, -4);
    assertEquals(-24, result);
  }

  /// testing our print sum method
  /// this method should the word "The sum is: " followed by the sum of the two numbers
  @Test
  void testPrintSum() {
    Calculator calculator = new Calculator();
    String result = calculator.printSum(3, 4);
    assertEquals("The sum is: 7", result, "printSum should return 'The sum is: 7' for inputs 3 and 4");
  }


}
