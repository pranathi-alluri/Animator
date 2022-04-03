package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for an AnimationModel that allows for some scopes to not be able to mutate it.
 */
public interface ViewOnlyAnimationModel {
  /**
   * Gets all the shapes at a specific time in the model. If there isn't a keyframe at that time,
   * it interpolates between the two closest keyframes.
   *
   * @param time the time to get the shapes from.
   * @return a list of shapes that exist that the given time in the model
   */
  List<Shape> getAllShapesAtTime(int time);

  /**
   * Gets a shape at a specific time. If there isn't a keyframe at that time,
   * it interpolates between the two closest keyframes.
   * Returns null if there is no shape of that name in the animation.
   *
   * @param name the unique name of the shape to get
   * @param time the time to get the shape from
   * @return
   */
  Shape getShapeAtTime(String name, int time);

  /**
   * Gets all the keyframes of a specific shape in the model in order of time.
   * Returns null if there is no shape of that name in the animation.
   *
   * @param shapeName name of the shape to get all the keyframes of
   * @return all the keyframes of a specific shape in the model in order of time
   */
  List<Keyframe> getAllKeyframesOfShape(String shapeName);

  /**
   * Returns a list of list of keyframes that represent the full animation.
   *
   * @return list of list of keyframes that represent the full animation
   */
  ArrayList<ArrayList<Keyframe>> getAllKeyframes();

  /**
   * Getter for width of the frame of the animation.
   *
   * @return the width of the frame of the animation in pixels
   */
  int getWidth();

  /**
   * Getter for height of the frame of the animation.
   *
   * @return the height of the frame of the animation in pixels
   */
  int getHeight();
}
