
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
import cs3500.animator.view.SimpleAnimationSVGView;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for SimpleAnimationSVGView.
 */

public class SVGViewTest {
  private AnimationModel sam;

  @Before
  public void setUp() {
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
    new SimpleAnimationSVGView(null, ap, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    ViewOnlyAnimationModel m = new SimpleAnimationModel();
    new SimpleAnimationSVGView(m, null, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailAppendable() throws IOException {
    Appendable ap = new FailingAppendable();
    AnimationTextViews v = new SimpleAnimationSVGView(sam, ap, 1);
    v.makeVisible();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTempo() {
    Appendable ap = new StringBuilder();
    ViewOnlyAnimationModel m = new SimpleAnimationModel();
    new SimpleAnimationSVGView(m, ap, -1);
  }


  @Test
  public void testMakeVisible() throws IOException {
    Appendable ap = new StringBuilder();
    AnimationTextViews v = new SimpleAnimationSVGView(sam, ap, 1);
    v.makeVisible();
    assertEquals("<svg width=\"0\" height=\"0\" version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"morphing rectangle\" x=\"20.0\" y=\"20.0\" width=\"20.0\" "
            + "height=\"20.0\" fill=\"rgb(100,0,0)\" visibility=\"hidden\" >\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"20000.0ms\" "
            + "attributeName=\"x\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"20000.0ms\" "
            + "attributeName=\"y\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"20000.0ms\" "
            + "attributeName=\"width\" from=\"20.0\" to=\"10.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"20000.0ms\" "
            + "attributeName=\"height\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"20000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(100,0,0)\" to=\"rgb(0,100,0)\" fill=\"freeze\" />"
            + "\n<animate attributeType=\"xml\" begin=\"30000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"width\" from=\"10.0\" to=\"40.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"blue circle\" cx=\"50.0\" cy=\"50.0\" rx=\"5.0\" ry=\"5.0\" "
            + "fill=\"rgb(0,0,100)\" visibility=\"hidden\" >\n"
            + "<animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"30000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"cx\" from=\"50.0\" to=\"70.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"30000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"cy\" from=\"50.0\" to=\"60.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>", v.getText());
  }

  @Test
  public void testMakeVisibleTemp10() throws IOException {
    Appendable ap = new StringBuilder();
    AnimationTextViews v = new SimpleAnimationSVGView(sam, ap, 10);
    v.makeVisible();
    assertEquals("<svg width=\"0\" height=\"0\" version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"morphing rectangle\" x=\"20.0\" y=\"20.0\" width=\"20.0\" "
            + "height=\"20.0\" fill=\"rgb(100,0,0)\" visibility=\"hidden\" >\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" "
            + "attributeName=\"x\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" "
            + "attributeName=\"y\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" "
            + "attributeName=\"width\" from=\"20.0\" to=\"10.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" "
            + "attributeName=\"height\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(100,0,0)\" to=\"rgb(0,100,0)\" fill=\"freeze\" />"
            + "\n<animate attributeType=\"xml\" begin=\"3000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"width\" from=\"10.0\" to=\"40.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"blue circle\" cx=\"50.0\" cy=\"50.0\" rx=\"5.0\" ry=\"5.0\" "
            + "fill=\"rgb(0,0,100)\" visibility=\"hidden\" >\n"
            + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"3000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"cx\" from=\"50.0\" to=\"70.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"3000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"cy\" from=\"50.0\" to=\"60.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>", v.getText());
  }

}
