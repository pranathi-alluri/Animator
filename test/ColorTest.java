import org.junit.Test;

import cs3500.animator.model.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for Color.
 */
public class ColorTest {
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRed() {
    Color c = new Color(-1, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGreen() {
    Color c = new Color(0, 256, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBlue() {
    Color c = new Color(255, 0, -50);
  }

  @Test
  public void testLegalRed() {
    Color c = new Color(59, 255, 0);
    assertEquals(c.getRed(), 59);
  }

  @Test
  public void testLegalGreen() {
    Color c = new Color(0, 140, 255);
    assertEquals(c.getGreen(), 140);
  }

  @Test
  public void testLegalBlue() {
    Color c = new Color(255, 0, 235);
    assertEquals(c.getBlue(), 235);
  }

  @Test
  public void testNotEqualsNotColor() {
    Color c = new Color(0, 0, 0);
    assertFalse(c.equals("0, 0, 0"));
  }

  @Test
  public void testNotEqualsColors() {
    Color c1 = new Color(0, 30, 0);
    Color c2 = new Color(30, 0, 0);
    assertFalse(c1.equals(c2));
    assertFalse(c2.equals(c1));
  }

  @Test
  public void testEqualsSameColor() {
    Color c1 = new Color(245, 31, 50);
    Color c2 = new Color(245, 31, 50);
    assertTrue(c1.equals(c2));
    assertTrue(c2.equals(c1));
  }

  @Test
  public void testHashCode() {
    Color c1 = new Color(100, 20, 255);
    assertEquals(c1.hashCode(), 2147483647);
  }

  @Test
  public void testToString() {
    Color c = new Color(34, 97, 240);
    assertEquals(c.toString(), "red: 34, green: 97, blue: 240");
  }
}
