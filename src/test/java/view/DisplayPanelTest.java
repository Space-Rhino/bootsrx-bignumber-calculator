package view;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DisplayPanelTest {

  @Test
  void testConstructor() {

    DisplayPanel actualDisplayPanel = new DisplayPanel();
    assertEquals(0, actualDisplayPanel.getMouseMotionListeners().length);
    assertEquals(0.5f, actualDisplayPanel.getAlignmentX());
    assertNull(actualDisplayPanel.getComponentPopupMenu());
    assertEquals(0, actualDisplayPanel.getMouseListeners().length);
    assertEquals(0, actualDisplayPanel.getHierarchyListeners().length);
    assertFalse(actualDisplayPanel.getIgnoreRepaint());
    assertEquals(1, actualDisplayPanel.getComponents().length);
    assertFalse(actualDisplayPanel.getInheritsPopupMenu());
    assertNull(actualDisplayPanel.getInputContext());

    assertEquals(
        Component.BaselineResizeBehavior.OTHER, actualDisplayPanel.getBaselineResizeBehavior());
    assertEquals(0, actualDisplayPanel.getKeyListeners().length);
    assertNull(actualDisplayPanel.getInputVerifier());
    assertEquals(0, actualDisplayPanel.getContainerListeners().length);
    assertEquals(0, actualDisplayPanel.getInputMethodListeners().length);
    assertNull(actualDisplayPanel.getInputMethodRequests());
    assertEquals(0.5f, actualDisplayPanel.getAlignmentY());
    assertNull(actualDisplayPanel.getBorder());
    assertEquals(0, actualDisplayPanel.getDebugGraphicsOptions());
    assertNull(actualDisplayPanel.getDropTarget());

    Rectangle expectedBounds = actualDisplayPanel.getBounds();
    assertEquals(expectedBounds, actualDisplayPanel.getBounds());
    assertNull(actualDisplayPanel.getFocusCycleRootAncestor());
    assertEquals(0, actualDisplayPanel.getFocusListeners().length);
    assertEquals(0, actualDisplayPanel.getAncestorListeners().length);
    assertEquals(0, actualDisplayPanel.getHierarchyBoundsListeners().length);
    assertEquals(0, actualDisplayPanel.getHeight());
    assertNull(actualDisplayPanel.getGraphicsConfiguration());
    assertNull(actualDisplayPanel.getGraphics());
    assertTrue(actualDisplayPanel.getFocusTraversalKeysEnabled());
    assertNull(actualDisplayPanel.getFocusTraversalPolicy());
    assertEquals(1, actualDisplayPanel.getComponentCount());
    assertFalse(actualDisplayPanel.getAutoscrolls());
    assertEquals(0, actualDisplayPanel.getComponentListeners().length);
  }
}
