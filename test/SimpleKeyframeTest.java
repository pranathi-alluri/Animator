import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.SimpleKeyframe;
import cs3500.animator.model.SimpleRectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for SimpleKeyframe
 */
public class SimpleKeyframeTest {

  private Shape shape;
  private int time;

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

  //Test interpolate                                                                                   ...

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
}
