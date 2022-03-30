package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an animation in the form of lists of Keyframes.
 */
public class SimpleAnimationModel implements AnimationModel {

  //INVARIANT: None of the lists in allKeyframes are empty
  //INVARIANT: all the keyframes in each ArrayList<Keyframe> have the same name and ShapeType
  private final ArrayList<ArrayList<Keyframe>> allKeyframes;
  private int width;
  private int height;

  public SimpleAnimationModel() {
    this.allKeyframes = new ArrayList<>();
    this.width = 0;
    this.height = 0;
  }

  public SimpleAnimationModel(int width, int height) {
    this.allKeyframes = new ArrayList<>();
    this.width = width;
    this.height = height;
  }

  /**
   * Gets all the shapes at a specific time in the model. If there isn't a keyframe at that time,
   * it interpolates between the two closest keyframes.
   *
   * @param time the time to get the shapes from.
   * @return a list of shapes that exist that the given time in the model
   */
  @Override
  public List<Shape> getAllShapesAtTime(int time) {
    ArrayList<Shape> shapesAtTime = new ArrayList<>();
    for (ArrayList<Keyframe> keyframes : allKeyframes) {
      Shape shape = null;
      for (int i = 0; i < keyframes.size() - 1; i++) {
        if (keyframes.get(i).getTime() == time) {
          shape = keyframes.get(i).getShape();
          break;
        } else if (keyframes.get(i).getTime() < time && keyframes.get(i + 1).getTime() > time) {
          shape = keyframes.get(i).interpolate(keyframes.get(i + 1), time);
        }
      }
      if (shape == null) {
        if (keyframes.get(keyframes.size() - 1).getTime() <= time) {
          shapesAtTime.add(keyframes.get(keyframes.size() - 1).getShape());
        }
      } else {
        shapesAtTime.add(shape);
      }
    }
    return shapesAtTime;
  }

  /**
   * Gets all the keyframes of a specific shape in the model in order of time.
   *
   * @param shapeName name of the shape to get all the keyframes of
   * @return all the keyframes of a specific shape in the model in order of time
   */
  @Override
  public List<Keyframe> getAllKeyframesOfShape(String shapeName) {
    if (shapeName == null) {
      throw new IllegalArgumentException("shapeName must not be null");
    }
    List<Keyframe> outputKeyframes = null;
    for (ArrayList<Keyframe> keyframes : allKeyframes) {
      if (keyframes.get(0).getShape().getName().equals(shapeName)) {
        outputKeyframes = keyframes;
      }
    }
    if (outputKeyframes == null) {
      throw new IllegalArgumentException("There is no shape of name shapeName in this animation");
    }
    return outputKeyframes;
  }

  /**
   * Adds the given keyframe to the animation.
   * If it shares its name with a shape already in the animation,
   * it adds it as another keyframe for that shape.
   * If it shares its name and time with a keyframe already in the animation,
   * it overrides that keyframe for that shape.
   * If it does not share its name with a shape already in the animation,
   * it adds it as a new shape to the animation.
   *
   * @param keyframe the Keyframe to add to the animation
   */
  @Override
  public void addKeyframe(Keyframe keyframe) {
    if (keyframe == null) {
      throw new IllegalArgumentException("keyframe must not be null");
    }
    boolean keyframeAdded = false;
    for (ArrayList<Keyframe> keyframes : allKeyframes) {
      if (keyframes.get(0).getShape().getName().equals(keyframe.getShape().getName())) {
        keyframeAdded = true;
        if (keyframes.get(0).getTime() > keyframe.getTime()) {
          keyframes.add(0, keyframe);
        }
        int keyframesSize = keyframes.size();
        for (int i = 0; i < keyframesSize - 1; i++) {
          if (keyframes.get(i).getTime() == keyframe.getTime()) {
            keyframes.set(i, keyframe);
          } else if (keyframes.get(i).getTime() < keyframe.getTime() &&
                  keyframes.get(i + 1).getTime() > keyframe.getTime()) {
            keyframes.add(i + 1, keyframe);
          }
        }
        if (keyframes.get(keyframes.size() - 1).getTime() == keyframe.getTime()) {
          keyframes.set(keyframes.size() - 1, keyframe);
        } else if (keyframes.get(keyframes.size() - 1).getTime() < keyframe.getTime()) {
          keyframes.add(keyframe);
        }
      }
    }
    if (!keyframeAdded) {
      ArrayList<Keyframe> keyframes = new ArrayList<>();
      keyframes.add(keyframe);
      allKeyframes.add(keyframes);
    }
  }

  /**
   * Removes one Keyframe from the model, if keyframe is not in the model
   * throws an IllegalArgumentException. If the keyframe removed was the last one of that shape in
   * the model, this removes the empty list left behind.
   *
   * @param keyframe the Keyframe to remove from the model
   */
  @Override
  public void removeKeyframe(Keyframe keyframe) {
    boolean keyframeRemoved = false;
    for (ArrayList<Keyframe> keyframes : allKeyframes) {
      keyframeRemoved = keyframeRemoved || keyframes.remove(keyframe);
    }
    allKeyframes.remove(new ArrayList<Keyframe>());
    if (!keyframeRemoved) {
      throw new IllegalArgumentException("Animation does not contain keyframe");
    }
  }

  /**
   * Removes one Keyframe from the model using its name and time as identifiers,
   * if keyframe is not in the model throws an IllegalArgumentException. If the keyframe removed
   * was the last one of that shape in the model, this removes the empty list left behind.
   *
   * @param shapeName the name of the shape in the keyframe to remove
   * @param time      the time of the keyframe to remove
   */
  @Override
  public void removeKeyframe(String shapeName, int time) {
    boolean keyframeRemoved = false;
    for (ArrayList<Keyframe> keyframes : allKeyframes) {
      if (keyframes.get(0).getShape().getName().equals(shapeName)) {
        for (int i = 0; i < keyframes.size(); i++) {
          if (keyframes.get(i).getTime() == time) {
            keyframes.remove(i);
            keyframeRemoved = true;
            break;
          }
        }
      }
    }
    allKeyframes.remove(new ArrayList<Keyframe>());
    if (!keyframeRemoved) {
      throw new IllegalArgumentException("Animation does not contain keyframe");
    }
  }

  /**
   * removes all keyframes that contain shapes of that name from the model.
   *
   * @param shapeName the name of the shape to remove all keyframes that contain it
   */
  @Override
  public void removeShape(String shapeName) {
    boolean shapeRemoved = false;
    for (int i = 0; i < allKeyframes.size(); i++) {
      if (allKeyframes.get(i).get(0).getShape().getName().equals(shapeName)) {
        allKeyframes.remove(i);
        shapeRemoved = true;
        break;
      }
    }
    if (!shapeRemoved) {
      throw new IllegalArgumentException("Animation does not contain shape");
    }
  }

  /**
   * Setter for width of the frame of the animation.
   *
   * @param width the width of the frame of the animation in pixels
   */
  @Override
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Setter for height of the frame of the animation.
   *
   * @param height the height of the frame of the animation in pixels
   */
  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Returns a list of list of keyframes that represent the full animation.
   *
   * @return list of list of keyframes that represent the full animation
   */
  @Override
  public ArrayList<ArrayList<Keyframe>> getAllKeyframes() {
    return allKeyframes;
  }

  /**
   * Getter for width of the frame of the animation.
   *
   * @return the width of the frame of the animation in pixels
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Getter for height of the frame of the animation.
   *
   * @return the height of the frame of the animation in pixels
   */
  @Override
  public int getHeight() {
    return height;
  }
}
