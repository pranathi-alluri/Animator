package cs3500.animator.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;

/**
 * Represents a user-friendly view of the animation by converting it to text.
 */
public class SimpleAnimationTextView implements SimpleAnimationView {
  private final AnimationModel model;


  /**
   * Constructs Animation View which takes in a model.
   *
   * @param model a animation model of the simple animation
   * @throws IllegalArgumentException if the given model is null.
   */
  public SimpleAnimationTextView(AnimationModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null.");
    }
    this.model = model;
  }

  @Override
  public String toString() {
    return getString(model);
  }

  private String getString(AnimationModel m) {
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
        animation = animation + keyframes.get(i).getDescription(next) + "\n";
      }
      animation = animation + "\n";
    }

    return animation;
  }
}

