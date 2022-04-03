package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 * Represents a user-friendly view of the animation by converting it to text.
 */
public class SimpleAnimationTextualView extends AnimationTextViews {


  /**
   * Constructs Animation text View.
   */
  public SimpleAnimationTextualView(ViewOnlyAnimationModel model, Appendable out, int tempo) {
    super(model, out, tempo);
  }

  /**
   * Return the description of the animation as a string. The string is formatted
   * as follows:
   * <pre>
   * Create red rectangle R with corner at (200,200), width 50 and height 100
   * Create blue oval C with center at (500,100), radius 60 and 30
   *
   * From time 1 to 10, R moves from (200,200) to (10,200), stays size 50x100, and stays (255,0,0)
   * From time 50 to 51, R does nothing.
   * </pre>
   *
   * @return the formatted string as above
   */
  @Override
  public String toString() {
    return getString(model);
  }

  /**
   * Helper method to get a textual description of the animation.
   *
   * @param m the model of a simple animation
   * @return string description of the model
   */
  private String getString(ViewOnlyAnimationModel m) {
    Map<String, Shape> allShapes = new HashMap<>();
    String animation = "";

    for (ArrayList<Keyframe> keyframes : m.getAllKeyframes()) {
      for (Keyframe k : keyframes) {
        if (allShapes.containsKey(k.getShape().getName())) {
          continue;
        } else {
          allShapes.put(k.getShape().getName(), k.getShape());
        }
      }
    }

    for (Shape shape : allShapes.values()) {
      animation = animation + shape.toString() + "\n";
    }

    animation = animation + "\n";

    for (ArrayList<Keyframe> keyframes : m.getAllKeyframes()) {
      for (int i = 0; i < keyframes.size() - 1; i++) {
        Keyframe next = keyframes.get(i + 1);
        int timeInSecStart = (1 / tempo * keyframes.get(i).getTime());
        int timeInSecEnd = (1 / tempo * next.getTime());

        animation = animation + "From time " + timeInSecStart + " to " + timeInSecEnd + ", " +
                keyframes.get(i).getDescription(next) + "\n";
      }
      animation = animation + "\n";
    }

    return animation;
  }

  @Override
  public void makeVisible() throws IOException {
    this.out.append(this.toString());

  }
}

