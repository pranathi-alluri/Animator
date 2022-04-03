
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;


import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.SimpleKeyframe;
import cs3500.animator.model.SimpleOval;
import cs3500.animator.model.SimpleRectangle;
import cs3500.animator.model.ViewOnlyAnimationModel;
import cs3500.animator.view.AnimationTextViews;
import cs3500.animator.view.SimpleAnimationTextualView;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for SimpleAnimationTextualView.
 */

public class SimpleAnimationTextualViewTest {
  private Shape redSquare;
  private Shape greenRectangle;
  private Shape greenSquare;
  private Shape blueCircle1;
  private Shape blueCircle2;
  private Keyframe kf11;
  private Keyframe kf12;
  private Keyframe kf13;
  private Keyframe kf21;
  private Keyframe kf22;
  private Keyframe kf23;
  private ArrayList<Keyframe> morphingRectangle;
  private ArrayList<Keyframe> blueCircle;
  private ArrayList<ArrayList<Keyframe>> allKeyframes;
  private AnimationModel sam;

  @Before
  public void setUp() {
    redSquare = new SimpleRectangle("morphing rectangle", 20, 20,
            new Color(100, 0, 0), 20, 20);
    greenRectangle = new SimpleRectangle("morphing rectangle", 30, 30,
            new Color(0, 100, 0), 40, 10);
    greenSquare = new SimpleRectangle("morphing rectangle", 30, 30,
            new Color(0, 100, 0), 40, 40);
    blueCircle1 = new SimpleOval("blue circle", 50, 50,
            new Color(0, 0, 100), 10, 10);
    blueCircle2 = new SimpleOval("blue circle", 70, 60,
            new Color(0, 0, 100), 10, 10);
    kf11 = new SimpleKeyframe(redSquare, 10);
    kf12 = new SimpleKeyframe(greenRectangle, 30);
    kf13 = new SimpleKeyframe(greenSquare, 40);
    kf21 = new SimpleKeyframe(blueCircle1, 20);
    kf22 = new SimpleKeyframe(blueCircle1, 30);
    kf23 = new SimpleKeyframe(blueCircle2, 40);
    morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    morphingRectangle.add(kf12);
    morphingRectangle.add(kf13);
    blueCircle = new ArrayList<>();
    blueCircle.add(kf21);
    blueCircle.add(kf22);
    blueCircle.add(kf23);
    allKeyframes = new ArrayList<>();
    allKeyframes.add(morphingRectangle);
    allKeyframes.add(blueCircle);
    sam = new SimpleAnimationModel();
    sam.addKeyframe(kf11);
    sam.addKeyframe(kf13);
    sam.addKeyframe(kf21);
    sam.addKeyframe(kf12);
    sam.addKeyframe(kf22);
    sam.addKeyframe(kf23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Appendable ap = new StringBuilder();
    new SimpleAnimationTextualView(null, ap, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    ViewOnlyAnimationModel m = new SimpleAnimationModel();
    new SimpleAnimationTextualView(m, null, 1);
  }

  @Test(expected = IOException.class)
  public void testFailAppendable() throws IOException {
    Appendable ap = new FailingAppendable();
    AnimationTextViews v = new SimpleAnimationTextualView(sam, ap, 1);
    v.makeVisible();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTempo() {
    Appendable ap = new StringBuilder();
    ViewOnlyAnimationModel m = new SimpleAnimationModel();
    new SimpleAnimationTextualView(m, ap, -1);
  }



  @Test
  public void testMakeVisible() throws IOException {
    Appendable ap = new StringBuilder();
    AnimationTextViews v = new SimpleAnimationTextualView(sam, ap, 1);
    v.makeVisible();
    assertEquals("Create RECTANGLE named morphing rectangle with center at (20.0,20.0)," +
            " width 20.0 and height 20.0\n" +
            "Create OVAL named blue circle with center at (50.0,50.0)," +
            " width 10.0 and height 10.0\n" +
            "\n" +
            "From time 10.0 to 30.0, morphing rectangle moves from (20.0,20.0) " +
            "to (30.0,30.0), scales from 20.0x20.0 to 10.0x40.0,  and the color " +
            "changes from java.awt.Color[r=100,g=0,b=0] to java.awt.Color[r=100,g=0,b=0].\n" +
            "From time 30.0 to 40.0, morphing rectangle stays put at (30.0,30.0), " +
            "scales from 10.0x40.0 to 40.0x40.0,  " +
            "and the color stays java.awt.Color[r=0,g=100,b=0].\n" +
            "\n" +
            "From time 20.0 to 30.0, blue circle stays put at (50.0,50.0), " +
            "stays size 10.0x10.0,  and the color stays java.awt.Color[r=0,g=0,b=100].\n" +
            "From time 30.0 to 40.0, blue circle moves from (50.0,50.0) to " +
            "(70.0,60.0), stays size 10.0x10.0,  " +
            "and the color stays java.awt.Color[r=0,g=0,b=100].\n" +
            "\n", v.getText());
  }

  @Test
  public void testMakeVisibleTempo10() throws IOException {
    Appendable ap = new StringBuilder();
    AnimationTextViews v = new SimpleAnimationTextualView(sam, ap, 10);
    v.makeVisible();
    assertEquals("Create RECTANGLE named morphing rectangle with center at (20.0,20.0)," +
            " width 20.0 and height 20.0\n" +
            "Create OVAL named blue circle with center at (50.0,50.0)," +
            " width 10.0 and height 10.0\n" +
            "\n" +
            "From time 1.0 to 3.0, morphing rectangle moves from (20.0,20.0) " +
            "to (30.0,30.0), scales from 20.0x20.0 to 10.0x40.0,  and the color " +
            "changes from java.awt.Color[r=100,g=0,b=0] to java.awt.Color[r=100,g=0,b=0].\n" +
            "From time 3.0 to 4.0, morphing rectangle stays put at (30.0,30.0), " +
            "scales from 10.0x40.0 to 40.0x40.0,  " +
            "and the color stays java.awt.Color[r=0,g=100,b=0].\n" +
            "\n" +
            "From time 2.0 to 3.0, blue circle stays put at (50.0,50.0), " +
            "stays size 10.0x10.0,  and the color stays java.awt.Color[r=0,g=0,b=100].\n" +
            "From time 3.0 to 4.0, blue circle moves from (50.0,50.0) to " +
            "(70.0,60.0), stays size 10.0x10.0,  " +
            "and the color stays java.awt.Color[r=0,g=0,b=100].\n" +
            "\n", v.getText());
  }

}
