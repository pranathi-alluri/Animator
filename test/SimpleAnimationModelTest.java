import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.SimpleKeyframe;
import cs3500.animator.model.SimpleOval;
import cs3500.animator.model.SimpleRectangle;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for SimpleAnimationModel
 */
public class SimpleAnimationModelTest {


  private final Shape redSquare;
  private final Shape greenRectangle;
  private final Shape greenSquare;
  private final Shape blueCircle;
  private final Keyframe kf11;
  private final Keyframe kf12;
  private final Keyframe kf13;
  private final Keyframe kf21;
  private final ArrayList<ArrayList<Keyframe>> allKeyframes;
  private final SimpleAnimationModel sam;

  public SimpleAnimationModelTest() {
    this.redSquare = new SimpleRectangle("morphing rectangle", 20, 20, new Color(100, 0, 0), 20, 20);
    this.greenRectangle = new SimpleRectangle("morphing rectangle", 30, 30, new Color(0, 100, 0), 40, 10);
    this.greenSquare = new SimpleRectangle("morphing rectangle", 30, 30, new Color(0, 100, 0), 40, 40);
    this.blueCircle = new SimpleOval("blue circle", 50, 50, new Color(0, 0, 100), 10, 10);
    this.kf11 = new SimpleKeyframe(redSquare, 10);
    this.kf12 = new SimpleKeyframe(greenRectangle, 30);
    this.kf13 = new SimpleKeyframe(greenSquare, 40);
    this.kf21 = new SimpleKeyframe(blueCircle, 20);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    morphingRectangle.add(kf12);
    morphingRectangle.add(kf13);
    ArrayList<Keyframe> blueCircle = new ArrayList<>();
    blueCircle.add(kf21);
    allKeyframes = new ArrayList<>();
    allKeyframes.add(morphingRectangle);
    allKeyframes.add(blueCircle);
    this.sam = new SimpleAnimationModel();
  }


  /*
  Tests needed
  removing the last keyframe from a shape should also remove the empty list from the animation, some tests work assuming this is not the case
  getAllShapesAtTime
   */

  @Test
  public void testAddFirstKeyframe() {
    this.sam.addKeyframe(kf11);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    assertEquals(this.sam.getAllKeyframesOfShape("morphing rectangle"), morphingRectangle);
  }

  @Test
  public void testAddSecondKeyframe() {
    this.sam.addKeyframe(kf11);
    this.sam.addKeyframe(kf13);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    morphingRectangle.add(kf13);
    assertEquals(this.sam.getAllKeyframesOfShape("morphing rectangle"), morphingRectangle);
  }

  @Test
  public void testAddThirdKeyframe() {
    this.sam.addKeyframe(kf11);
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    morphingRectangle.add(kf13);
    ArrayList<Keyframe> blueCircle = new ArrayList<>();
    blueCircle.add(kf21);
    ArrayList<ArrayList<Keyframe>> allKeyframes1 = new ArrayList<>();
    allKeyframes1.add(morphingRectangle);
    allKeyframes1.add(blueCircle);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes1);
  }

  @Test
  public void testAddFourthKeyframe() {
    this.sam.addKeyframe(kf11);
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testAddKeyframeReorder() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf11);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveKeyframe11() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf11);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
    this.sam.removeKeyframe(kf11);
    allKeyframes.get(0).remove(0);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveKeyframe12() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf11);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
    this.sam.removeKeyframe(kf12);
    allKeyframes.get(0).remove(1);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveKeyframe13() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf11);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
    this.sam.removeKeyframe(kf13);
    allKeyframes.get(0).remove(2);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveKeyframe21() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf11);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
    this.sam.removeKeyframe(kf21);
    allKeyframes.get(1).remove(0);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveKeyframeNotInAnimation() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.removeKeyframe(kf11);
  }

  @Test
  public void testRemoveMultipleKeyframes() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf11);
    assertEquals(this.sam.getAllKeyframes(), this.allKeyframes);
    this.sam.removeKeyframe(kf21);
    this.sam.removeKeyframe(kf12);
    allKeyframes.get(1).remove(0);
    allKeyframes.get(0).remove(1);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  // test removeKeyframe(shapename, time)                                                               ...

  @Test
  public void testRemoveShape1() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf11);
    assertEquals(this.sam.getAllKeyframes(), this.allKeyframes);
    this.sam.removeShape("morphing rectangle");
    this.allKeyframes.remove(0);
    assertEquals(this.sam.getAllKeyframes(), this.allKeyframes);
  }

  @Test
  public void testRemoveShape2() {
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf11);
    assertEquals(this.sam.getAllKeyframes(), this.allKeyframes);
    this.sam.removeShape("blue circle");
    this.allKeyframes.remove(1);
    assertEquals(this.sam.getAllKeyframes(), this.allKeyframes);
  }


}