package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An AnimationModel that uses a hashmap to store all the shapes in the model to save on processing
 * time.
 */
public class HashmapAnimationModel implements AnimationModel {

  private final HashMap<String, ArrayList<Keyframe>> allKeyframes;
  private int width;
  private int height;

  /**
   * Constructor for SimpleAnimationModel. Sets default width and height to 0
   */
  public HashmapAnimationModel() {
    this.allKeyframes = new HashMap<>();
    this.width = 0;
    this.height = 0;
  }

  /**
   * Constructor for SimpleAnimationModel. Takes in width and height.
   */
  public HashmapAnimationModel(int width, int height) {
    this.allKeyframes = new HashMap<>();
    this.width = width;
    this.height = height;
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
    ArrayList<Keyframe> keyframes = this.allKeyframes.get(keyframe.getShape().getName());
    if (keyframes == null) {
      keyframes = new ArrayList<>();
      keyframes.add(keyframe);
      allKeyframes.put(keyframe.getShape().getName(), keyframes);
      return;
    }
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
    allKeyframes.put(keyframe.getShape().getName(), keyframes);
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
    if (keyframe == null) {
      throw new IllegalArgumentException("keyframe must not be null");
    }
    ArrayList<Keyframe> keyframes = allKeyframes.get(keyframe.getShape().getName());
    if (keyframes == null) {
      throw new IllegalArgumentException("Animation does not contain keyframe");
    } else if (keyframes.size() == 1 && keyframes.get(0).equals(keyframe)) {
      allKeyframes.remove(keyframe.getShape().getName());
    } else {
      boolean keyframeRemoved = keyframes.remove(keyframe);
      if (!keyframeRemoved) {
        throw new IllegalArgumentException("Animation does not contain keyframe");
      }
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
    if (shapeName == null) {
      throw new IllegalArgumentException("shapeName must not be null");
    }
    ArrayList<Keyframe> keyframes = allKeyframes.get(shapeName);
    if (keyframes == null) {
      throw new IllegalArgumentException("Animation does not contain keyframe");
    } else if (keyframes.size() == 1 && keyframes.get(0).getTime() == time) {
      allKeyframes.remove(shapeName);
    } else {
      for(int i = 0; i < keyframes.size(); i++) {
        if(keyframes.get(i).getTime() == time) {
          keyframes.remove(i);
          allKeyframes.put(shapeName, keyframes);
          return;
        }
      }
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
    this.allKeyframes.remove(shapeName);
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
   * Gets all the shapes at a specific time in the model. If there isn't a keyframe at that time,
   * it interpolates between the two closest keyframes.
   *
   * @param time the time to get the shapes from.
   * @return a list of shapes that exist that the given time in the model
   */
  @Override
  public List<Shape> getAllShapesAtTime(int time) {
    ArrayList<Shape> shapesAtTime = new ArrayList<>();
    for (ArrayList<Keyframe> keyframes : allKeyframes.values()) {
      Shape shape = getShapeAtTime(keyframes.get(0).getShape().getName(), time);
      if (shape != null) {
        shapesAtTime.add(shape);
      }
    }
    return shapesAtTime;
  }

  /**
   * Gets a shape at a specific time. If there isn't a keyframe at that time,
   * it interpolates between the two closest keyframes.
   * Returns null if there is no shape of that name in the animation.
   *
   * @param name the unique name of the shape to get
   * @param time the time to get the shape from
   * @return
   */
  @Override
  public Shape getShapeAtTime(String name, int time) {
    if (name == null) {
      throw new IllegalArgumentException("name must not be null");
    } else if (allKeyframes.get(name) == null) {
      return null;
    }
    Shape shape = null;
    ArrayList<Keyframe> keyframes = this.allKeyframes.get(name);
    for (int i = 0; i < keyframes.size() - 1; i++) {
      if (keyframes.get(i).getTime() == time) {
        shape = keyframes.get(i).getShape();
        break;
      } else if (keyframes.get(i).getTime() < time && keyframes.get(i + 1).getTime() > time) {
        shape = keyframes.get(i).interpolate(keyframes.get(i + 1), time);
      }
    }
    if (keyframes.get(keyframes.size() - 1).getTime() == time) {
      shape = keyframes.get(keyframes.size() - 1).getShape();
    }
    return shape;
  }

  /**
   * Gets all the keyframes of a specific shape in the model in order of time.
   * Returns null if there is no shape of that name in the animation.
   *
   * @param shapeName name of the shape to get all the keyframes of
   * @return all the keyframes of a specific shape in the model in order of time
   */
  @Override
  public List<Keyframe> getAllKeyframesOfShape(String shapeName) {
    if (shapeName == null) {
      throw new IllegalArgumentException("shapeName must not be null");
    }
    return this.allKeyframes.get(shapeName);
  }

  /**
   * Returns a list of list of keyframes that represent the full animation.
   *
   * @return list of list of keyframes that represent the full animation
   */
  @Override
  public ArrayList<ArrayList<Keyframe>> getAllKeyframes() {
    ArrayList<ArrayList<Keyframe>> getAllKeys = new ArrayList<>();
    for (ArrayList<Keyframe> keyframes : allKeyframes.values()) {
      getAllKeys.add(keyframes);
    }
    return getAllKeys;
  }

  /**
   * Getter for width of the frame of the animation.
   *
   * @return the width of the frame of the animation in pixels
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Getter for height of the frame of the animation.
   *
   * @return the height of the frame of the animation in pixels
   */
  @Override
  public int getHeight() {
    return this.height;
  }
}
