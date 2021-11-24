package view;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ButtonPanelTest {

  @Test
  void testConstructor() {

    ButtonPanel actualButtonPanel = new ButtonPanel(null);
    assertEquals(0, actualButtonPanel.getMouseListeners().length);
    assertNull(actualButtonPanel.getActiveOperationButton());
    assertFalse(actualButtonPanel.getAutoscrolls());
    assertEquals(0, actualButtonPanel.getHierarchyBoundsListeners().length);
    assertEquals(0, actualButtonPanel.getHierarchyListeners().length);
    assertNull(actualButtonPanel.getComponentPopupMenu());
    assertFalse(actualButtonPanel.getIgnoreRepaint());
    assertFalse(actualButtonPanel.getInheritsPopupMenu());
    assertEquals(0, actualButtonPanel.getKeyListeners().length);
    assertNull(actualButtonPanel.getInputVerifier());
    assertEquals(24, actualButtonPanel.getComponents().length);
    assertNull(actualButtonPanel.getInputContext());
    assertEquals(0, actualButtonPanel.getContainerListeners().length);
    assertEquals(0, actualButtonPanel.getInputMethodListeners().length);
    assertNull(actualButtonPanel.getInputMethodRequests());
    assertEquals(0.5f, actualButtonPanel.getAlignmentX());

    assertEquals(
        Component.BaselineResizeBehavior.OTHER, actualButtonPanel.getBaselineResizeBehavior());
    assertEquals(0, actualButtonPanel.getDebugGraphicsOptions());
    assertNull(actualButtonPanel.getBorder());
    assertNull(actualButtonPanel.getDropTarget());
    assertEquals(0, actualButtonPanel.getHeight());
    assertNull(actualButtonPanel.getGraphicsConfiguration());
    assertNull(actualButtonPanel.getGraphics());
    assertNull(actualButtonPanel.getFocusTraversalPolicy());
    assertTrue(actualButtonPanel.getFocusTraversalKeysEnabled());
    assertEquals(0, actualButtonPanel.getFocusListeners().length);
    assertNull(actualButtonPanel.getFocusCycleRootAncestor());
    assertEquals(0.5f, actualButtonPanel.getAlignmentY());

    Rectangle expectedBounds = actualButtonPanel.getBounds();
    assertEquals(expectedBounds, actualButtonPanel.getBounds());
    assertEquals(0, actualButtonPanel.getAncestorListeners().length);
    assertEquals(24, actualButtonPanel.getComponentCount());
    assertEquals(0, actualButtonPanel.getComponentListeners().length);
  }

}
