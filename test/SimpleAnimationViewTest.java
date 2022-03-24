import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.SimpleKeyframe;
import cs3500.animator.model.SimpleOval;
import cs3500.animator.model.SimpleRectangle;
import cs3500.animator.view.SimpleAnimationTextView;
import cs3500.animator.view.SimpleAnimationView;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for SimpleAnimationView.
 */

public class SimpleAnimationViewTest {

  private final Shape redSquare;
  private final Shape greenSquare;
  private final Shape blueCircle;
  private final Shape blueCircle2;
  private final Keyframe kf11;
  private final Keyframe kf12;
  private final Keyframe kf13;
  private final Keyframe kf21;
  private final Keyframe kf22;
  private final Keyframe kf23;
  private final ArrayList<ArrayList<Keyframe>> allKeyframes;
  private SimpleAnimationModel sam;
  private SimpleAnimationView sav;

  /**
   * Constructor for SimpleAnimationModelTest.
   */
  public SimpleAnimationViewTest() {
    this.redSquare = new SimpleRectangle("morphing rectangle", 20, 20,
            new Color(100, 0, 0), 20, 20);
    Shape greenRectangle = new SimpleRectangle("morphing rectangle", 30, 30,
            new Color(0, 100, 0), 40, 10);
    this.greenSquare = new SimpleRectangle("morphing rectangle", 30, 30,
            new Color(0, 100, 0), 40, 40);
    this.blueCircle = new SimpleOval("blue circle", 50, 50,
            new Color(0, 0, 100), 10, 10);
    this.blueCircle2 = new SimpleOval("blue circle", 70, 60,
            new Color(0, 0, 100), 10, 10);
    this.kf11 = new SimpleKeyframe(redSquare, 10);
    this.kf12 = new SimpleKeyframe(greenRectangle, 30);
    this.kf13 = new SimpleKeyframe(greenSquare, 40);
    this.kf21 = new SimpleKeyframe(blueCircle, 20);
    this.kf22 = new SimpleKeyframe(blueCircle, 30);
    this.kf23 = new SimpleKeyframe(blueCircle2, 40);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    morphingRectangle.add(kf12);
    morphingRectangle.add(kf13);
    ArrayList<Keyframe> blueCircle = new ArrayList<>();
    blueCircle.add(kf21);
    blueCircle.add(kf22);
    blueCircle.add(kf23);
    allKeyframes = new ArrayList<>();
    allKeyframes.add(morphingRectangle);
    allKeyframes.add(blueCircle);
    this.sam = new SimpleAnimationModel();
    this.sam.addKeyframe(kf11);
    this.sam.addKeyframe(kf13);
    this.sam.addKeyframe(kf21);
    this.sam.addKeyframe(kf12);
    this.sam.addKeyframe(kf22);
    this.sam.addKeyframe(kf23);
    this.sav = new SimpleAnimationTextView(this.sam);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new SimpleAnimationTextView(null);
  }

  @Test
  public void testToString() {
    assertEquals("Create RECTANGLE named morphing rectangle with corner at (20.0,20.0)," +
            " width 20.0 and height 20.0\n" +
            "Create OVAL named blue circle with corner at (50.0,50.0)," +
            " width 10.0 and height 10.0\n" +
            "\n" +
            "From time 10 to 30, morphing rectangle moves from (20.0,20.0) " +
            "to (30.0,30.0), scales from 20.0x20.0 to 10.0x40.0,  and the color " +
            "changes from java.awt.Color[r=100,g=0,b=0] to java.awt.Color[r=100,g=0,b=0].\n" +
            "From time 30 to 40, morphing rectangle stays put at (30.0,30.0), " +
            "scales from 10.0x40.0 to 40.0x40.0,  " +
            "and the color staysjava.awt.Color[r=0,g=100,b=0].\n" +
            "\n" +
            "From time 20 to 30, blue circle stays put at (50.0,50.0), " +
            "stays size 10.0x10.0,  and the color staysjava.awt.Color[r=0,g=0,b=100].\n" +
            "From time 30 to 40, blue circle moves from (50.0,50.0) to " +
            "(70.0,60.0), stays size 10.0x10.0,  " +
            "and the color staysjava.awt.Color[r=0,g=0,b=100].\n" +
            "\n", sav.toString());
  }


}
