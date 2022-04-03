
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.SimpleKeyframe;
import cs3500.animator.model.SimpleOval;
import cs3500.animator.model.SimpleRectangle;
import cs3500.animator.view.SimpleAnimationTextualView;
import cs3500.animator.view.SimpleAnimationView;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for SimpleAnimationView.
 */

public class SimpleAnimationViewTest {

  private final SimpleAnimationView sav;

  /**
   * Constructor for SimpleAnimationModelTest.
   */
  public SimpleAnimationViewTest() {
    Shape redSquare = new SimpleRectangle("morphing rectangle", 20, 20,
            new Color(100, 0, 0), 20, 20);
    Shape greenRectangle = new SimpleRectangle("morphing rectangle", 30, 30,
            new Color(0, 100, 0), 40, 10);
    Shape greenSquare = new SimpleRectangle("morphing rectangle", 30, 30,
            new Color(0, 100, 0), 40, 40);
    Shape blueCircle1 = new SimpleOval("blue circle", 50, 50,
            new Color(0, 0, 100), 10, 10);
    Shape blueCircle2 = new SimpleOval("blue circle", 70, 60,
            new Color(0, 0, 100), 10, 10);
    Keyframe kf11 = new SimpleKeyframe(redSquare, 10);
    Keyframe kf12 = new SimpleKeyframe(greenRectangle, 30);
    Keyframe kf13 = new SimpleKeyframe(greenSquare, 40);
    Keyframe kf21 = new SimpleKeyframe(blueCircle1, 20);
    Keyframe kf22 = new SimpleKeyframe(blueCircle1, 30);
    Keyframe kf23 = new SimpleKeyframe(blueCircle2, 40);
    ArrayList<Keyframe> morphingRectangle = new ArrayList<>();
    morphingRectangle.add(kf11);
    morphingRectangle.add(kf12);
    morphingRectangle.add(kf13);
    ArrayList<Keyframe> blueCircle = new ArrayList<>();
    blueCircle.add(kf21);
    blueCircle.add(kf22);
    blueCircle.add(kf23);
    ArrayList<ArrayList<Keyframe>> allKeyframes = new ArrayList<>();
    allKeyframes.add(morphingRectangle);
    allKeyframes.add(blueCircle);
    SimpleAnimationModel sam = new SimpleAnimationModel();
    sam.addKeyframe(kf11);
    sam.addKeyframe(kf13);
    sam.addKeyframe(kf21);
    sam.addKeyframe(kf12);
    sam.addKeyframe(kf22);
    sam.addKeyframe(kf23);
    this.sav = new SimpleAnimationTextualView(sam);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new SimpleAnimationTextualView(null);
  }

  @Test
  public void testToString() {
    assertEquals("Create RECTANGLE named morphing rectangle with center at (20.0,20.0)," +
            " width 20.0 and height 20.0\n" +
            "Create OVAL named blue circle with center at (50.0,50.0)," +
            " width 10.0 and height 10.0\n" +
            "\n" +
            "From time 10 to 30, morphing rectangle moves from (20.0,20.0) " +
            "to (30.0,30.0), scales from 20.0x20.0 to 10.0x40.0,  and the color " +
            "changes from java.awt.Color[r=100,g=0,b=0] to java.awt.Color[r=100,g=0,b=0].\n" +
            "From time 30 to 40, morphing rectangle stays put at (30.0,30.0), " +
            "scales from 10.0x40.0 to 40.0x40.0,  " +
            "and the color stays java.awt.Color[r=0,g=100,b=0].\n" +
            "\n" +
            "From time 20 to 30, blue circle stays put at (50.0,50.0), " +
            "stays size 10.0x10.0,  and the color stays java.awt.Color[r=0,g=0,b=100].\n" +
            "From time 30 to 40, blue circle moves from (50.0,50.0) to " +
            "(70.0,60.0), stays size 10.0x10.0,  " +
            "and the color stays java.awt.Color[r=0,g=0,b=100].\n" +
            "\n", sav.toString());
  }


}
