import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.SimpleKeyframe;
import cs3500.animator.model.SimpleOval;
import cs3500.animator.model.SimpleRectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Testing class for SimpleAnimationModel.
 */
public class SimpleAnimationModelTest {


  private final Shape redSquare;
  private final Shape greenSquare;
  private final Shape blueCircle;
  private final Keyframe kf11;
  private final Keyframe kf12;
  private final Keyframe kf13;
  private final Keyframe kf21;
  private final Keyframe kf22;
  private final ArrayList<ArrayList<Keyframe>> allKeyframes;
  private SimpleAnimationModel sam;

  /**
   * Constructor for SimpleAnimationModelTest.
   */
  public SimpleAnimationModelTest() {
    this.redSquare = new SimpleRectangle("morphing rectangle", 20, 20,
            new Color(100, 0, 0), 20, 20);
    Shape greenRectangle = new SimpleRectangle("morphing rectangle", 30, 30,
            new Color(0, 100, 0), 40, 10);
    this.greenSquare = new SimpleRectangle("morphing rectangle", 30, 30,
            new Color(0, 100, 0), 40, 40);
    this.blueCircle = new SimpleOval("blue circle", 50, 50,
            new Color(0, 0, 100), 10, 10);
    this.kf11 = new SimpleKeyframe(redSquare, 10);
    this.kf12 = new SimpleKeyframe(greenRectangle, 30);
    this.kf13 = new SimpleKeyframe(greenSquare, 40);
    this.kf21 = new SimpleKeyframe(blueCircle, 20);
    this.kf22 = new SimpleKeyframe(blueCircle, 30);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    morphingRectangle.add(kf12);
    morphingRectangle.add(kf13);
    ArrayList<Keyframe> blueCircle = new ArrayList<>();
    blueCircle.add(kf21);
    blueCircle.add(kf22);
    allKeyframes = new ArrayList<>();
    allKeyframes.add(morphingRectangle);
    allKeyframes.add(blueCircle);
    this.sam = new SimpleAnimationModel();
    this.sam.addKeyframe(kf11);
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf22);
  }

  @Test
  public void testGetAllShapesBeforeAnyAdded() {
    ArrayList<Shape> shapesAtTime = new ArrayList<>();
    assertEquals(this.sam.getAllShapesAtTime(5), shapesAtTime);
  }

  @Test
  public void testGetAllShapesAtTimeOfKeyframe() {
    ArrayList<Shape> shapesAtTime = new ArrayList<>();
    shapesAtTime.add(redSquare);
    assertEquals(this.sam.getAllShapesAtTime(10), shapesAtTime);
  }

  @Test
  public void testGetAllShapesInterpolate() {
    ArrayList<Shape> shapesAtTime = new ArrayList<>();
    Shape interpolatedRectangle = new SimpleRectangle("morphing rectangle",
            25, 25, new Color(50, 50, 0), 30, 15);
    shapesAtTime.add(interpolatedRectangle);
    shapesAtTime.add(blueCircle);
    assertEquals(this.sam.getAllShapesAtTime(20), shapesAtTime);
  }

  @Test
  public void testGetAllShapesAfterAnimations() {
    ArrayList<Shape> shapesAtTime = new ArrayList<>();
    assertEquals(this.sam.getAllShapesAtTime(50), shapesAtTime);
  }

  @Test
  public void testAddFirstKeyframe() {
    this.sam = new SimpleAnimationModel();
    this.sam.addKeyframe(kf11);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    assertEquals(this.sam.getAllKeyframesOfShape("morphing rectangle"), morphingRectangle);
  }

  @Test
  public void testAddSecondKeyframe() {
    this.sam = new SimpleAnimationModel();
    this.sam.addKeyframe(kf11);
    this.sam.addKeyframe(kf13);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    morphingRectangle.add(kf13);
    assertEquals(this.sam.getAllKeyframesOfShape("morphing rectangle"), morphingRectangle);
  }

  @Test
  public void testAddThirdKeyframe() {
    this.sam = new SimpleAnimationModel();
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
    this.sam = new SimpleAnimationModel();
    this.sam.addKeyframe(kf11);
    this.sam.addKeyframe(kf22);
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testAddKeyframeReorder() {
    this.sam = new SimpleAnimationModel();
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf11);
    this.sam.addKeyframe(kf22);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveKeyframe11() {
    this.sam.removeKeyframe(kf11);
    allKeyframes.get(0).remove(0);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveKeyframe12() {
    this.sam.removeKeyframe(kf12);
    allKeyframes.get(0).remove(1);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveKeyframe13() {
    this.sam.removeKeyframe(kf13);
    allKeyframes.get(0).remove(2);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveKeyframe2122() {
    this.sam.removeKeyframe(kf21);
    this.sam.removeKeyframe(kf22);
    allKeyframes.remove(1);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveKeyframeNotInAnimation() {
    this.sam.removeKeyframe(kf11);
    this.sam.removeKeyframe(kf11);
  }

  @Test
  public void testRemoveMultipleKeyframes() {
    this.sam.removeKeyframe(kf21);
    this.sam.removeKeyframe(kf12);
    allKeyframes.get(1).remove(0);
    allKeyframes.get(0).remove(1);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveShapeNameTime11() {
    this.sam.removeKeyframe("morphing rectangle", 10);
    allKeyframes.get(0).remove(0);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveShapeNameTime12() {
    this.sam.removeKeyframe("morphing rectangle", 30);
    allKeyframes.get(0).remove(1);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveShapeNameTime13() {
    this.sam.removeKeyframe("morphing rectangle", 40);
    allKeyframes.get(0).remove(2);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveShapeNameTime21() {
    this.sam.removeKeyframe("blue circle", 20);
    allKeyframes.get(1).remove(0);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShapeNameTimeNotInAnimation() {
    this.sam.removeKeyframe("morphing rectangle", 10);
    this.sam.removeKeyframe("morphing rectangle", 10);
  }

  @Test
  public void testRemoveMultipleShapeNameTime() {
    this.sam.removeKeyframe("blue circle", 20);
    this.sam.removeKeyframe("morphing rectangle", 30);
    allKeyframes.get(1).remove(0);
    allKeyframes.get(0).remove(1);
    assertEquals(this.sam.getAllKeyframes(), allKeyframes);
  }

  @Test
  public void testRemoveShape1() {
    this.sam.removeShape("morphing rectangle");
    this.allKeyframes.remove(0);
    assertEquals(this.sam.getAllKeyframes(), this.allKeyframes);
  }

  @Test
  public void testRemoveShape2() {
    this.sam.removeShape("blue circle");
    this.allKeyframes.remove(1);
    assertEquals(this.sam.getAllKeyframes(), this.allKeyframes);
  }

  @Test
  public void testGetSetWidth() {
    assertEquals(this.sam.getWidth(), 0);
    this.sam.setWidth(100);
    assertEquals(this.sam.getWidth(), 100);
  }

  @Test
  public void testGetSetHeight() {
    assertEquals(this.sam.getHeight(), 0);
    this.sam.setHeight(200);
    assertEquals(this.sam.getHeight(), 200);
  }

  @Test
  public void testGetShapeNotInModel() {
    assertNull(this.sam.getAllKeyframesOfShape("Triangle"));
  }

  @Test
  public void testGetShapeNotInModel2() {
    assertNull(this.sam.getShapeAtTime("Triangle", 5));
  }
}
