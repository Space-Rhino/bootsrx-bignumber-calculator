package testutilities;

import number.BigNumber;

public class TestNumbers {
  
  private static final BigNumber ZERO = new BigNumber("0");
  private static final BigNumber PI =
      new BigNumber("3.1415926535897932384626433832795028841971693993751"
                    + "0582097494459230781640628620899862803482534211707");
  
  private final BigNumber intPosX =
      new BigNumber("40000000000000000000000000000000000000000000000000");
  private final BigNumber intPosY =
      new BigNumber("20000000000000000000000000000000000000000000000000");
  private final BigNumber intNegX =
      new BigNumber("-40000000000000000000000000000000000000000000000000");
  private final BigNumber intNegY =
      new BigNumber("-20000000000000000000000000000000000000000000000000");
  private final BigNumber wrongIntResult = new BigNumber("123456789");
  
  private final BigNumber decPosX =
      new BigNumber("0.0000000000000000000000000000000000000000000000004");
  private final BigNumber decPosY =
      new BigNumber("0.0000000000000000000000000000000000000000000000002");
  private final BigNumber decNegX =
      new BigNumber("-0.0000000000000000000000000000000000000000000000004");
  private final BigNumber decNegY =
      new BigNumber("-0.0000000000000000000000000000000000000000000000002");
  private final BigNumber wrongDecResult = new BigNumber("123.456789");
  
  public BigNumber getZERO() {
    return ZERO;
  }
  
  public BigNumber getPI() {
    return PI;
  }
  
  public BigNumber getIntPosX() {
    return intPosX;
  }
  
  public BigNumber getIntPosY() {
    return intPosY;
  }
  
  public BigNumber getIntNegX() {
    return intNegX;
  }
  
  public BigNumber getIntNegY() {
    return intNegY;
  }
  
  public BigNumber getWrongIntResult() {
    return wrongIntResult;
  }
  
  public BigNumber getDecPosX() {
    return decPosX;
  }
  
  public BigNumber getDecPosY() {
    return decPosY;
  }
  
  public BigNumber getDecNegX() {
    return decNegX;
  }
  
  public BigNumber getDecNegY() {
    return decNegY;
  }
  
  public BigNumber getWrongDecResult() {
    return wrongDecResult;
  }
}
