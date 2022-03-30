
package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an animation in the form of lists of keyframes.
 */
public interface AnimationModel extends ViewOnlyAnimationModel{

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
   * throws an IllegalArgumentException. If the keyframe removed was the last one of that shape in
   * the model, this removes the empty list left behind.
   * @param keyframe the Keyframe to remove from the model
   */
  void removeKeyframe(Keyframe keyframe);

  /**
   * Removes one Keyframe from the model using its name and time as identifiers,
   * if keyframe is not in the model throws an IllegalArgumentException. If the keyframe removed
   * was the last one of that shape in the model, this removes the empty list left behind.
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
   * Setter for width of the frame of the animation.
   * @param width the width of the frame of the animation in pixels
   */
  void setWidth(int width);

  /**
   * Setter for height of the frame of the animation.
   * @param height the height of the frame of the animation in pixels
   */
  void setHeight(int height);
}
