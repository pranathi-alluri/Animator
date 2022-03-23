
package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an animation in the form of lists of keyframes.
 */
public interface AnimationModel {

  /**
   * Gets all the shapes at a specific time in the model. If there isn't a keyframe at that time,
   * it interpolates between the two closest keyframes.
   * @param time the time to get the shapes from.
   * @return a list of shapes that exist that the given time in the model
   */
  List<Shape> getAllShapesAtTime(int time);

  /**
   * Gets all the keyframes of a specific shape in the model in order of time.
   * @param shapeName name of the shape to get all the keyframes of
   * @return all the keyframes of a specific shape in the model in order of time
   */
  List<Keyframe> getAllKeyframesOfShape(String shapeName);

  /**
   * Adds the given keyframe to the animation.
   * If it shares its name with a shape already in the animation,
   * it adds it as another keyframe for that shape.
   * If it shares its name and time with a keyframe already in the animation,
   * it overrides that keyframe for that shape.
   * If it does not share its name with a shape already in the animation,
   * it adds it as a new shape to the animation.
   * @param keyframe the Keyframe to add to the animation
   */
  void addKeyframe(Keyframe keyframe);

  /**
   * Removes one Keyframe from the model, if keyframe is not in the model
   * throws an IllegalArgumentException.
   * @param keyframe the Keyframe to remove from the model
   */
  void removeKeyframe(Keyframe keyframe);

  /**
   * Removes one Keyframe from the model using its name and time as identifiers,
   * if keyframe is not in the model throws an IllegalArgumentException.
   * @param shapeName the name of the shape in the keyframe to remove
   * @param time the time of the keyframe to remove
   */
  void removeKeyframe(String shapeName, int time);

  /**
   * removes all keyframes that contain shapes of that name from the model.
   * @param shapeName the name of the shape to remove all keyframes that contain it
   */
  void removeShape(String shapeName);

  /**
   * Returns a list of list of keyframes that represent the full animation.
   * @return list of list of keyframes that represent the full animation
   */
  ArrayList<ArrayList<Keyframe>> getAllKeyframes();

}
