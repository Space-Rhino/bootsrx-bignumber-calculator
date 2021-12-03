package model.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.beans.PropertyChangeListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DisplayTest {

  @Test
  @DisplayName("case# 2.200: Check display constructor")
  void testDisplayConstructor() {
    Display actual = new Display("0");
    String expected = "0";
    assertThat(expected).isEqualTo(actual.getValue());
  }

  @Test
  @DisplayName("case# 2.201: Confirm setValue and getValue function")
  void testSetValueAndGetValue() {
    Display actual = new Display("111");
    String expected = "111";
    assertThat(expected).isEqualTo(actual.getValue());
  }

  @Test
  @DisplayName("case# 2.202: Add digit staying in given constraints")
  void testAddDigit_NoBranches() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange((any()));

    Display actual = new Display("1");
    actual.addPropertyChangeListener(propertyChangeListener);

    String expected = "12";
    actual.addDigit("2");
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "123";
    actual.addDigit("3");
    assertThat(expected).isEqualTo(actual.getValue());
    verify(propertyChangeListener, times(2)).propertyChange((any()));
  }

  @Test
  @DisplayName("case# 2.203: Confirm display does not add more than 100 digits to a number")
  void testAddDigit_PreventMaxLength() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange((any()));

    String digits98Random =
        "99753565104611429918062340521754864702455885394648442"
            + "834770940562713968298250453055718916532651312";
    Display actual = new Display(digits98Random);
    actual.addPropertyChangeListener(propertyChangeListener);

    // 99th digits added
    digits98Random += "3";
    String expected = digits98Random;
    actual.addPropertyChangeListener(propertyChangeListener);
    actual.addDigit("3");
    assertThat(expected).isEqualTo(actual.getValue()).hasSize(99);

    // 100th digit max digits added, should be last digit added
    digits98Random += "4";
    expected = digits98Random;
    actual.addPropertyChangeListener(propertyChangeListener);
    actual.addDigit("4");
    assertThat(expected).isEqualTo(actual.getValue()).hasSize(100);

    // 101 digit should not be added to display
    expected = digits98Random;
    actual.addPropertyChangeListener(propertyChangeListener);
    actual.addDigit("7");
    assertThat(expected).isEqualTo(actual.getValue());

    // 102 digit should not be added to display
    expected = digits98Random;
    actual.addPropertyChangeListener(propertyChangeListener);
    actual.addDigit("8");
    assertThat(expected).isEqualTo(actual.getValue());
    verify(propertyChangeListener, times(5)).propertyChange((any()));
  }

  @Test
  @DisplayName("case# 2.204: Confirm display does not add more than one decimal to a number")
  void testAddDigit_PreventMultipleDecimals() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange((any()));

    Display actual = new Display("3.14");
    actual.addPropertyChangeListener(propertyChangeListener);

    String expected = "3.148";
    actual.addDigit("8");
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "3.148";
    actual.addDigit(".");
    assertThat(actual.getValue()).contains(".");
    assertThat(expected).isEqualTo(actual.getValue()).contains(".");

    expected = "3.1487";
    actual.addPropertyChangeListener(propertyChangeListener);
    actual.addDigit("7");
    assertThat(expected).isEqualTo(actual.getValue());
    verify(propertyChangeListener, times(3)).propertyChange((any()));
  }

  @Test
  @DisplayName("case# 2.205: Confirm display does not add leading zeros to a number")
  void testAddDigit_DeleteLeadingZeros() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange((any()));

    Display actual = new Display("0");
    actual.addPropertyChangeListener(propertyChangeListener);

    String expected = "0";
    actual.addDigit("0");
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "0";
    actual.addDigit("0");
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "0";
    actual.addDigit("0");
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "4";
    actual.addDigit("4");
    assertThat(expected).isEqualTo(actual.getValue());
    verify(propertyChangeListener, times(4)).propertyChange((any()));
  }

  @Test
  @DisplayName("case# 2.206: Confirm display value of number is correct after deleting a digit")
  void testDeleteDigit() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange((any()));

    Display actual = new Display("12345");
    actual.addPropertyChangeListener(propertyChangeListener);

    String expected = "1234";
    actual.deleteDigit();
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "123";
    actual.deleteDigit();
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "12";
    actual.deleteDigit();
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "1";
    actual.deleteDigit();
    assertThat(expected).isEqualTo(actual.getValue());

    expected = "0";
    actual.deleteDigit();
    assertThat(expected).isEqualTo(actual.getValue());
    verify(propertyChangeListener, times(5)).propertyChange((any()));
  }

  @Test
  @DisplayName("case# 2.207: Confirm clear display with propertyChangeListener works as intended")
  void testClear_CallSetValue() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(any());

    Display actual = new Display("12345");
    actual.addPropertyChangeListener(propertyChangeListener);

    String expected = "0";
    actual.clear();
    assertThat(expected).isEqualTo(actual.getValue());
    verify(propertyChangeListener, times(1)).propertyChange((any()));
  }

  @Test
  @DisplayName("case# 2.208: Confirm clear display with by setting directly works as intended")
  void testReset_SetDirectly() {
    Display actual = new Display("12345");
    String expected = "0";

    actual.clear();
    assertThat(actual.getValue()).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.209: Confirm testAddPropertyChangeListener works as intended")
  void testAddPropertyChangeListener() {
    Display actual = new Display("12345");
    String expected = "12345";

    actual.addPropertyChangeListener(mock(PropertyChangeListener.class));
    assertThat(actual.getValue()).isEqualTo(expected);
  }
}
