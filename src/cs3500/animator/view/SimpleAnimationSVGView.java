package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ViewOnlyAnimationModel;

public class SimpleAnimationSVGView extends AnimationTextViews{

  /**
   * Constructor for Animation SVG view.
   */
  public SimpleAnimationSVGView(ViewOnlyAnimationModel model, Appendable out, int tempo) {
    super(model, out, tempo);
  }


  @Override
  public void makeVisible() {
    try {
      out.append("<svg width=\"" + model.getWidth() + "\" height=\""
              + model.getHeight() + "\" version=\"1.1\"\n    "
              + "xmlns=\"http://www.w3.org/2000/svg\">\n");
      for (ArrayList<Keyframe> keyframes : model.getAllKeyframes()) {
        out.append(this.keyFramesToSVG(keyframes));
      }
      out.append("</svg>");
    } catch (IOException e) {
      throw new IllegalStateException("Invalid appendable.");
    }
  }

  /**
   * Converts the shape in the given keyframes to an SVG view.
   * @param k all the keyframes for a single shape
   * @return the SVG of the shape in text.
   */
  private String keyFramesToSVG(ArrayList<Keyframe> k) {
    String type = "";
    Shape s = k.get(0).getShape();

    switch (s.getType()) {
      case RECTANGLE:
        type = "rect";
        break;
      case OVAL:
        type = "ellipse";
        break;
      default:
        throw new IllegalArgumentException("Invalid Shape type.");
    }

    StringBuilder output = new StringBuilder();
    output.append("<" + type + " id=\"" + s.getName());

    switch (type) {
      case "rect" :
        output.append("\" x=\"").append(s.getX())
                .append("\" y=\"").append(s.getY())
                .append("\" width=\"").append(s.getWidth())
                .append("\" height=\"").append(s.getHeight())
                .append("\" fill=\"rgb(").append(s.getColor().getRed()).append(",")
                .append(s.getColor().getGreen()).append(",")
                .append(s.getColor().getBlue())
                .append(")\" visibility=\"hidden\" >\n");
        break;
      case "ellipse" :
        output.append("\" cx=\"").append(s.getX())
                .append("\" cy=\"").append(s.getY())
                .append("\" rx=\"").append(s.getWidth() / 2)
                .append("\" ry=\"").append(s.getHeight() / 2)
                .append("\" fill=\"rgb(").append(s.getColor().getRed()).append(",")
                .append(s.getColor().getGreen()).append(",")
                .append(s.getColor().getBlue())
                .append(")\" visibility=\"hidden\" >\n");
        break;
      default:
        return output.toString();
    }
    output.append("<animate attributeType=\"xml\" begin=\"").append(ticksToMS(k.get(0).getTime()))
            .append("\" dur=\"1ms\" attributeName=\"visibility\" from=\"hidden\" to=\"visible\" " +
                    "fill=\"freeze\" />\n");

    for (int i = 0; i < k.size() - 1; i++) {
      Keyframe next = k.get(i + 1);
      output.append(motionToSVG(k.get(i), next, type));
    }

    output.append("</");
    output.append(type);
    output.append(">\n");

    return output.toString();
  }

  /**
   * Returns the given transformation as a textual description of changes that occur to the SVG.
   * @param k the starting keyframe.
   * @param next the next keyframe.
   * @param type the shape that is being transformed.
   * @return SVG transformations in a textual view.
   */
  private String motionToSVG(Keyframe k, Keyframe next, String type) {
    StringBuilder output = new StringBuilder();
    String begin = "<animate attributeType=\"xml\" begin=\""
            + ticksToMS(k.getTime()) + "\" dur=\""
            + ticksToMS(next.getTime() - k.getTime())
            + "\" attributeName=\"";;
    String end = "\" fill=\"freeze\" />\n";

    if (k.getShape().getX() != next.getShape().getX()) {
      if (type.equals("rect")) {
        output.append(begin).append("x\" from=\"").append(k.getShape().getX())
                .append("\" to=\"").append(next.getShape().getX()).append(end);
      } else if (type.equals("ellipse")) {
        output.append(begin).append("cx\" from=\"").append(k.getShape().getX())
                .append("\" to=\"").append(next.getShape().getX()).append(end);
      }
    }

    if (k.getShape().getY() != next.getShape().getY()) {
      if (type.equals("rect")) {
        output.append(begin).append("y\" from=\"").append(k.getShape().getY())
                .append("\" to=\"").append(next.getShape().getY()).append(end);
      } else if (type.equals("ellipse")) {
        output.append(begin).append("cy\" from=\"").append(k.getShape().getX())
                .append("\" to=\"").append(next.getShape().getX()).append(end);
      }
    }

    if (k.getShape().getWidth() != next.getShape().getWidth()) {
      if (type.equals("rect")) {
        output.append(begin).append("width\" from=\"").append(k.getShape().getWidth())
                .append("\" to=\"").append(next.getShape().getWidth()).append(end);
      } else if (type.equals("ellipse")) {
        output.append(begin).append("rx\" from=\"").append(k.getShape().getWidth() / 2)
                .append("\" to=\"").append(next.getShape().getWidth() / 2).append(end);
      }
    }

    if (k.getShape().getHeight() != next.getShape().getHeight()) {
      if (type.equals("rect")) {
        output.append(begin).append("height\" from=\"").append(k.getShape().getHeight())
                .append("\" to=\"").append(next.getShape().getHeight()).append(end);
      } else if (type.equals("ellipse")) {
        output.append(begin).append("ry\" from=\"").append(k.getShape().getHeight() / 2)
                .append("\" to=\"").append(next.getShape().getHeight() / 2).append(end);
      }
    }

    if (k.getShape().getColor().getRed() != next.getShape().getColor().getRed()
            || k.getShape().getColor().getGreen() != next.getShape().getColor().getGreen()
            || k.getShape().getColor().getBlue() != next.getShape().getColor().getBlue()) {
      output.append(begin).append("fill\" from=\"rgb(")
              .append(k.getShape().getColor().getRed()).append(",")
              .append(k.getShape().getColor().getGreen()).append(",")
              .append(k.getShape().getColor().getBlue()).append(")\" to=\"rgb(")
              .append(next.getShape().getColor().getRed()).append(",")
              .append(next.getShape().getColor().getGreen()).append(",")
              .append(next.getShape().getColor().getBlue()).append(")").append(end);
    }
    return output.toString();
  }

  /**
   * Converts the given tick value to MS.
   * @param t the time of keyframe in ticks
   * @return String representation of keyframe time in ms.
   */
  private String ticksToMS(int t) {
    return t / tempo * 1000 + "ms";
  }
}
