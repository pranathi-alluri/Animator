import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.SimpleKeyframe;
import cs3500.animator.model.SimpleOval;
import cs3500.animator.model.SimpleRectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for SimpleKeyframe.
 */
public class SimpleKeyframeTest {

  private Shape shape;
  private int time;

  /**
   * Constructor for SimpleKeyframeTest.
   */
  public SimpleKeyframeTest() {
    this.shape = new SimpleRectangle("red square", 20, 20,
            new Color(255, 0, 0), 20, 20);
    this.time = 20;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShape() {
    Keyframe keyframe = new SimpleKeyframe(null, 20);
  }

  @Test
  public void testGetShape() {
    Keyframe keyframe = new SimpleKeyframe(this.shape, this.time);
    assertEquals(keyframe.getShape(), this.shape);
  }

  @Test
  public void testGetTime() {
    Keyframe keyframe = new SimpleKeyframe(this.shape, this.time);
    assertEquals(keyframe.getTime(), this.time);
  }

  @Test
  public void testNotEqualsKeyframe() {
    Keyframe keyframe1 = new SimpleKeyframe(this.shape, this.time);
    Keyframe keyframe2 = new SimpleKeyframe(this.shape, 21);
    assertNotEquals(keyframe1, keyframe2);
  }

  @Test
  public void testNotEqualsNotKeyframe() {
    Keyframe keyframe1 = new SimpleKeyframe(this.shape, this.time);
    assertFalse(keyframe1.equals("keyframe2"));
  }

  @Test
  public void testEqualsKeyframe() {
    Keyframe keyframe1 = new SimpleKeyframe(this.shape, this.time);
    Keyframe keyframe2 = new SimpleKeyframe(this.shape, this.time);
    assertEquals(keyframe1, keyframe2);
  }

  @Test
  public void testDifferentHashcode() {
    Keyframe keyframe1 = new SimpleKeyframe(this.shape, this.time);
    Keyframe keyframe2 = new SimpleKeyframe(this.shape, 21);
    assertNotEquals(keyframe1.hashCode(), keyframe2.hashCode());
  }

  @Test
  public void testSameHashcode() {
    Keyframe keyframe1 = new SimpleKeyframe(this.shape, this.time);
    Keyframe keyframe2 = new SimpleKeyframe(this.shape, this.time);
    assertEquals(keyframe1.hashCode(), keyframe2.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullKeyframeInterpolate() {
    Keyframe kf = new SimpleKeyframe(this.shape, this.time);
    kf.interpolate(null, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTimeInterpolate() {
    Keyframe kf = new SimpleKeyframe(this.shape, this.time);
    Shape shape2 = new SimpleRectangle("red square", 30, 30,
            new Color(255, 0, 0), 40, 10);
    Keyframe kf2 = new SimpleKeyframe(shape2, 40);
    kf.interpolate(kf2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNameInterpolate() {
    Keyframe kf = new SimpleKeyframe(this.shape, this.time);
    Shape shape2 = new SimpleRectangle("red rectangle", 30, 30,
            new Color(255, 0, 0), 40, 10);
    Keyframe kf2 = new SimpleKeyframe(shape2, 40);
    kf.interpolate(kf2, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeInterpolate() {
    Keyframe kf = new SimpleKeyframe(this.shape, this.time);
    Shape shape2 = new SimpleOval("red square", 30, 30,
            new Color(255, 0, 0), 40, 10);
    Keyframe kf2 = new SimpleKeyframe(shape2, 40);
    kf.interpolate(kf2, 30);
  }

  @Test
  public void testInterpolate() {
    Keyframe kf = new SimpleKeyframe(this.shape, this.time);
    Shape shape2 = new SimpleRectangle("red square", 30, 30,
            new Color(255, 0, 0), 40, 10);
    Keyframe kf2 = new SimpleKeyframe(shape2, 40);
    Shape interpolatedShape = new SimpleRectangle("red square", 25, 25,
            new Color(255, 0, 0), 30, 15);
    assertEquals(kf.interpolate(kf2, 30), interpolatedShape);
  }

  @Test
  public void testInterpolate2() {
    Shape shape1 = new SimpleOval("Oval", 10, 10,
            new Color(0, 200, 100), 10, 10);
    Shape shape2 = new SimpleOval("Oval", 50, 90,
            new Color(100, 100, 200), 10, 30);
    Shape interpolatedShape = new SimpleOval("Oval", 20, 30,
            new Color(25, 175, 125), 10, 15);
    Keyframe kf1 = new SimpleKeyframe(shape1, 0);
    Keyframe kf2 = new SimpleKeyframe(shape2, 40);
    assertEquals(kf1.interpolate(kf2, 10), interpolatedShape);
  }

  @Test
  public void testToString() {
    Shape shape1 = new SimpleOval("Oval", 10, 10,
            new Color(0, 200, 100), 10, 10);
    Shape shape2 = new SimpleOval("Oval", 50, 90,
            new Color(100, 100, 200), 10, 30);
    Keyframe kf1 = new SimpleKeyframe(shape1, 0);
    Keyframe kf2 = new SimpleKeyframe(shape2, 40);
    assertEquals(kf1.getDescription(kf2), "Oval moves from (10.0,10.0) to (50.0,90.0), scales from 10.0x10.0 to 30.0x10.0,  and the color changes from java.awt.Color[r=0,g=200,b=100] to java.awt.Color[r=0,g=200,b=100].");

  }
}
