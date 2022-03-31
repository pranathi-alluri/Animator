import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.SimpleShapeFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * Abstract testing class for SimpleShapes.
 */
public abstract class SimpleShapeTest {

  protected ShapeType type;

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    Shape shape = SimpleShapeFactory.getShape(type, null, 20, 20,
            new Color(20, 20, 20), 20, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColor() {
    Shape shape = SimpleShapeFactory.getShape(type, "colorless", 20, 20,
            null, 20, 20);
  }

  @Test
  public void testGetName() {
    Shape shape = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 20);
    assertEquals(shape.getName(), "red square");
  }

  @Test
  public void testGetX() {
    Shape shape = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 20);
    assertEquals(shape.getX(), 20, 0.0001);
  }

  @Test
  public void testGetY() {
    Shape shape = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 20);
    assertEquals(shape.getY(), 20, 0.0001);
  }

  @Test
  public void testGetColor() {
    Shape shape = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 20);
    assertEquals(shape.getColor(), new Color(255, 0, 0));
  }

  @Test
  public void testGetHeight() {
    Shape shape = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 30, 20);
    assertEquals(shape.getHeight(), 30, 0.0001);
  }

  @Test
  public void testGetWidth() {
    Shape shape = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    assertEquals(shape.getWidth(), 50, 0.0001);
  }

  @Test
  public void testGetType() {
    Shape shape = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    assertEquals(shape.getType(), type);
  }

  @Test
  public void testNotEqualsShape() {
    Shape shape1 = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    Shape shape2 = SimpleShapeFactory.getShape(type, "green square", 20, 20,
            new Color(0, 255, 0), 20, 50);
    assertFalse(shape1.equals(shape2));
  }

  @Test
  public void testNotEqualsNotShape() {
    Shape shape1 = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    assertFalse(shape1.equals("shape2"));
  }

  @Test
  public void testEquals() {
    Shape shape1 = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    Shape shape2 = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    assertEquals(shape1, shape2);
  }

  @Test
  public void testDifferentHashcode() {
    Shape shape1 = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    Shape shape2 = SimpleShapeFactory.getShape(type, "green square", 20, 20,
            new Color(0, 255, 0), 20, 50);
    assertNotEquals(shape1.hashCode(), shape2.hashCode());
  }

  @Test
  public void testSameHashcode() {
    Shape shape1 = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    Shape shape2 = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    assertEquals(shape1.hashCode(), shape2.hashCode());
  }

  @Test
  public void testToString() {
    Shape shape1 = SimpleShapeFactory.getShape(type, "red square", 20, 20,
            new Color(255, 0, 0), 20, 50);
    assertEquals(shape1.toString(), "Create " + type + " named red square with center at " +
            "(20.0,20.0), width 50.0 and height 20.0");
  }
}
