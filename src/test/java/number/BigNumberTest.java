package number;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import testutilities.TestNumbers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class BigNumberTest {
  
  private static BigNumber expected;
  private static BigNumber actual;
  TestNumbers number = new TestNumbers();
  
  @Nested
  @Order(1)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberInstantiation {
    
    BigNumber bigNumber;
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: invalid data type")
    void bigIntegerInvalidDataType_ThrowsNumberFormatException() {
      String invalidIntegerType = "123abc456789";
      assertThrows(
          NumberFormatException.class, () -> bigNumber = new BigNumber(invalidIntegerType));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigDecimal: invalid data type")
    void bigDecimalInvalidDataType_ThrowsNumberFormatException() {
      String invalidDecimalType = "12.3abc456789";
      Assertions.assertThrows(
          NumberFormatException.class, () -> bigNumber = new BigNumber(invalidDecimalType));
    }
  }
  
  @Nested
  @Order(2)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberAdd {
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: two pos operands | x + y")
    void bigInteger_TwoPositiveOperands() {
      expected = new BigNumber("60000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntPosX().add(number.getIntPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigInteger: two neg operands | -x + -y")
    void bigInteger_TwoNegativeOperands() {
      expected = new BigNumber("-60000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntNegX().add(number.getIntNegY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(3)
    @DisplayName("BigInteger: one neg & one pos operand | -x + y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-20000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntNegX().add(number.getIntPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(4)
    @DisplayName("BigDecimal: two pos operands | x + y")
    void bigDecimal_TwoPositiveOperands() {
      expected = new BigNumber("0.0000000000000000000000000000000000000000000000006");
      actual = new BigNumber(number.getDecPosX().add(number.getDecPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(5)
    @DisplayName("BigDecimal: two neg operands | -x + -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000006");
      actual = new BigNumber(number.getDecNegX().add(number.getDecNegY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(6)
    @DisplayName("BigDecimal: one neg & one pos operand | -x + y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000002");
      actual = new BigNumber(number.getDecNegX().add(number.getDecPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
  }
  
  @Nested
  @Order(3)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberSubtract {
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: two pos operands | x - y")
    void bigInteger_TwoPositiveOperands() {
      expected = new BigNumber("20000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntPosX().subtract(number.getIntPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigInteger: two neg operands | -x - -y")
    void bigInteger_TwoNegativeOperands() {
      expected = new BigNumber("-20000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntNegX().subtract(number.getIntNegY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(3)
    @DisplayName("BigInteger: one neg & one pos operand | -x - y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-60000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntNegX().subtract(number.getIntPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(4)
    @DisplayName("BigDecimal: two pos operands | x - y")
    void bigDecimal_TwoPositiveOperands() {
      expected = new BigNumber("0.0000000000000000000000000000000000000000000000002");
      actual = new BigNumber(number.getDecPosX().subtract(number.getDecPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(5)
    @DisplayName("BigDecimal: two neg operands | -x - -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000002");
      actual = new BigNumber(number.getDecNegX().subtract(number.getDecNegY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(6)
    @DisplayName("BigDecimal: one neg & one pos operand | -x - y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000006");
      actual = new BigNumber(number.getDecNegX().subtract(number.getDecPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
  }
  
  @Nested
  @Order(4)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberMultiply {
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: two pos operands | x * y")
    void bigInteger_TwoPositiveOperands() {
      expected =
          new BigNumber(
              "800000000000000000000000000000000000000000000000000"
              + "000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntPosX().multiply(number.getIntPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigInteger: two neg operands | -x * -y")
    void bigInteger_TwoNegativeOperands() {
      expected =
          new BigNumber(
              "800000000000000000000000000000000000000000000000000"
              + "000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntNegX().multiply(number.getIntNegY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(3)
    @DisplayName("BigInteger: one neg & one pos operand | -x * y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected =
          new BigNumber(
              "-80000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntNegX().multiply(number.getIntPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(4)
    @DisplayName("BigDecimal: two pos operands | x * y")
    void bigDecimal_TwoPositiveOperands() {
      expected =
          new BigNumber(
              "0.0000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000008");
      actual = new BigNumber(number.getDecPosX().multiply(number.getDecPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(5)
    @DisplayName("BigDecimal: two neg operands | -x * -y")
    void bigDecimal_TwoNegativeOperands() {
      expected =
          new BigNumber(
              "0.0000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000008");
      actual = new BigNumber(number.getDecNegX().multiply(number.getDecNegY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(6)
    @DisplayName("BigDecimal: one neg & one pos operand | -x * y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected =
          new BigNumber(
              "-0.000000000000000000000000000000000000000000000000"
              + "00000000000000000000000000000000000000000000000008");
      actual = new BigNumber(number.getDecNegX().multiply(number.getDecPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
  }
  
  @Nested
  @Order(5)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberDivide {
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: two pos operands | x / y")
    void bigInteger_TwoPositiveOperands() {
      expected = new BigNumber("2");
      actual = new BigNumber(number.getIntPosX().divide(number.getIntPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigInteger: two neg operands | -x / -y")
    void bigInteger_TwoNegativeOperands() {
      expected = new BigNumber("2");
      actual = new BigNumber(number.getIntNegX().divide(number.getIntNegY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(3)
    @DisplayName("BigInteger: one neg & one pos operand | -x / y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-2");
      actual = new BigNumber(number.getIntNegX().divide(number.getIntPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(4)
    @DisplayName("BigDecimal: two pos operands | x / y")
    void bigDecimal_TwoPositiveOperands() {
      expected = new BigNumber("2");
      actual = new BigNumber(number.getDecPosX().divide(number.getDecPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(5)
    @DisplayName("BigDecimal: two neg operands | -x / -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = new BigNumber("2");
      actual = new BigNumber(number.getDecNegX().divide(number.getDecNegY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(6)
    @DisplayName("BigDecimal: one neg & one pos operand | -x / y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-2");
      actual = new BigNumber(number.getDecNegX().divide(number.getDecPosY()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(7)
    @DisplayName("BigInteger: divide zero by number | 0 / x && 0 / -x")
    void bigNumber_DivideZeroByBigNumber() {
      expected = new BigNumber("0");
      actual = new BigNumber(number.getZERO().divide(number.getIntPosX()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
      
      actual = new BigNumber(number.getZERO().divide(number.getIntNegX()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(8)
    @DisplayName("BigDecimal: divide zero by number | 0 / x && 0 / -x")
    void bigDecimal_DivideZeroByBigDecimal() {
      expected = new BigNumber("0");
      actual = new BigNumber(number.getZERO().divide(number.getDecPosX()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
      
      actual = new BigNumber(number.getZERO().divide(number.getDecNegX()).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(9)
    @DisplayName("BigInteger: divide by zero | throws ArithmeticException")
    void bigNumber_DivideByZero() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(number.getIntPosX().divide(number.getZERO()).toString()));
      
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(number.getIntNegX().divide(number.getZERO()).toString()));
    }
    
    @Test
    @Order(10)
    @DisplayName("BigDecimal: divide zero by number | throws ArithmeticException")
    void bigDecimal_DivideByZero() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(number.getDecPosX().divide(number.getZERO()).toString()));
      
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(number.getDecNegX().divide(number.getZERO()).toString()));
    }
  }
  
  @Nested
  @Order(6)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberSquare {
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: one pos operand | x")
    void bigInteger_OnePositiveOperand() {
      expected =
          new BigNumber(
              "160000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntPosX().square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigInteger: one neg operand | x")
    void bigInteger_OneNegativeOperand() {
      expected =
          new BigNumber(
              "160000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntNegX().square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(3)
    @DisplayName("BigDecimal: one pos operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected =
          new BigNumber(
              "0.000000000000000000000000000000000000000000000000"
              + "00000000000000000000000000000000000000000000000016");
      actual = new BigNumber(number.getDecPosX().square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(4)
    @DisplayName("BigDecimal: one neg operand | x")
    void bigDecimal_OneNegativeOperand() {
      expected =
          new BigNumber(
              "0.000000000000000000000000000000000000000000000000"
              + "00000000000000000000000000000000000000000000000016");
      actual = new BigNumber(number.getDecNegX().square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
  }
  
  @Nested
  @Order(7)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberSquareRoot {
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: one pos operand | x")
    void bigInteger_OnePositiveOperand() {
      expected =
          new BigNumber(
              "6324555320336758663997787"
              + ".08886543706743911027865043365371500970558518887727847644268849621675860059");
      actual = new BigNumber(number.getIntPosX().squareRoot().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigInteger: one neg operand | x")
    void bigInteger_OneNegativeOperand() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(number.getIntNegX().squareRoot().toString()));
    }
    
    @Test
    @Order(3)
    @DisplayName("BigDecimal: one pos operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected =
          new BigNumber(
              "6.32455532033675866399778708886543706743911027865"
              + "04336537150097055851888772784764426884962167586E-25");
      actual = new BigNumber(number.getDecPosX().squareRoot().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(4)
    @DisplayName("BigDecimal: one neg operand | x")
    void bigDecimal_OneNegativeOperand() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(number.getDecNegX().squareRoot().toString()));
    }
  }
  
  @Nested
  @Order(8)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberNegate {
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: one pos operand | x")
    void bigInteger_OnePositiveOperand() {
      expected = new BigNumber("-40000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntPosX().negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigInteger: one neg operand | x")
    void bigInteger_OneNegativeOperand() {
      expected = new BigNumber("40000000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getIntNegX().negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(3)
    @DisplayName("BigDecimal: one pos operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000004");
      actual = new BigNumber(number.getDecPosX().negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(4)
    @DisplayName("BigDecimal: one neg operand | x")
    void bigDecimal_OneNegativeOperand() {
      expected = new BigNumber("0.0000000000000000000000000000000000000000000000004");
      actual = new BigNumber(number.getDecNegX().negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
  }
  
  @Nested
  @Order(9)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberInverse {
    
    @Test
    @Order(1)
    @DisplayName("BigInteger: one pos operand | x")
    void bigInteger_OnePositiveOperand() {
      expected = new BigNumber("0.000000000000000000000000000000000000000000000000025");
      actual = new BigNumber(number.getIntPosX().inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(2)
    @DisplayName("BigInteger: one neg operand | x")
    void bigInteger_OneNegativeOperand() {
      expected = new BigNumber("-0.000000000000000000000000000000000000000000000000025");
      actual = new BigNumber(number.getIntNegX().inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongIntResult()));
    }
    
    @Test
    @Order(3)
    @DisplayName("BigDecimal: one pos operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = new BigNumber("2500000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getDecPosX().inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
    
    @Test
    @Order(4)
    @DisplayName("BigDecimal: one neg operand | x")
    void bigDecimal_OneNegativeOperand() {
      expected = new BigNumber("-2500000000000000000000000000000000000000000000000");
      actual = new BigNumber(number.getDecNegX().inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(number.getWrongDecResult()));
    }
  }
}
