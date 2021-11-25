package model.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.beans.PropertyChangeListener;
import org.junit.jupiter.api.Test;

class DisplayTest {

  @Test
  void testDisplayConstructor() {
    assertEquals("42", (new Display("42")).getValue());
    assertEquals("68", (new Display("68")).getValue());
  }

  @Test
  void testSetValue() {
    Display display = new Display("42");
    display.setValue("88");
    assertEquals("88", display.getValue());
  }

  @Test
  void testSetValue2() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(any());

    Display display = new Display("42");
    display.addDigit("8");
    display.addPropertyChangeListener(propertyChangeListener);
    assertEquals("428", display.getValue());
    display.setValue("42");
    verify(propertyChangeListener).propertyChange(any());
    assertEquals("42", display.getValue());
  }

  @Test
  void testAddDigit() {
    Display display = new Display("42");
    display.addDigit("68");
    assertEquals("4268", display.getValue());
    assertNotEquals("426", display.getValue());
  }

  @Test
  void testAddDigit2() {
    Display display = new Display("0");
    display.addDigit("9");
    assertEquals("9", display.getValue());
    assertNotEquals("0", display.getValue());
  }

  @Test
  void testAddDigit3() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange((any()));

    Display display = new Display("42");
    display.addPropertyChangeListener(propertyChangeListener);
    display.addDigit("99");
    verify(propertyChangeListener).propertyChange((any()));
    assertEquals("4299", display.getValue());
  }

  @Test
  void testAddDigit4() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange((any()));

    Display display = new Display("42");
    display.addPropertyChangeListener(propertyChangeListener);
    display.addDigit(".05");
    verify(propertyChangeListener).propertyChange((any()));
    assertEquals("42.05", display.getValue());
    assertNotEquals("4205", display.getValue());
  }

  @Test
  void testAddDigit5() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange((any()));

    Display display = new Display("0.5");
    display.addPropertyChangeListener(propertyChangeListener);
    display.addDigit("432");
    assertEquals("0.5432", display.getValue());
  }

  @Test
  void testAddDigit6() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(any());

    Display display = new Display("0");
    assertEquals("0", display.getValue());
    display.addPropertyChangeListener(propertyChangeListener);
    display.addDigit("0.11");
    verify(propertyChangeListener).propertyChange(any());
    assertEquals("0.11", display.getValue());
  }

  @Test
  void testDeleteDigit() {
    Display display = new Display("42");
    display.deleteDigit();
    assertEquals("4", display.getValue());
  }

  @Test
  void testDeleteDigit2() {
    Display display = new Display("6");
    display.deleteDigit();
    assertEquals("0", display.getValue());
  }

  @Test
  void testDeleteDigit3() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(any());

    Display display = new Display("42");
    display.addPropertyChangeListener(propertyChangeListener);
    display.deleteDigit();
    verify(propertyChangeListener).propertyChange(any());
    assertEquals("4", display.getValue());
  }

  @Test
  void testDeleteDigit4() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(any());
    PropertyChangeListener propertyChangeListener1 = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener1).propertyChange(any());

    Display display = new Display("999");
    display.addPropertyChangeListener(propertyChangeListener1);
    display.addPropertyChangeListener(propertyChangeListener);
    display.deleteDigit();
    verify(propertyChangeListener1).propertyChange(any());
    verify(propertyChangeListener).propertyChange(any());
    assertEquals("99", display.getValue());
  }

  @Test
  void testClear() {
    Display display = new Display("42");
    display.clear();
    assertEquals("0", display.getValue());
  }

  @Test
  void testClear2() {
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(any());

    Display display = new Display("66");
    display.addPropertyChangeListener(propertyChangeListener);
    display.clear();
    verify(propertyChangeListener).propertyChange(any());
    assertEquals("0", display.getValue());
  }

  @Test
  void testAddPropertyChangeListener() {
    Display display = new Display("42");
    display.addPropertyChangeListener(mock(PropertyChangeListener.class));
    assertEquals("42", display.getValue());
  }
}
