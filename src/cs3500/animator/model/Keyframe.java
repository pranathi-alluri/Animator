package cs3500.animator.model;

/**
 * Represents a shape at a specific time in the animation. Used to interpolate where the shape is
 * at any given time.
 */
public interface Keyframe {

  /**
   * Returns the shape of this keyframe.
   *
   * @return the shape of this keyframe.
   */
  Shape getShape();

  /**
   * Returns the time this keyframe exists at.
   *
   * @return the time of this keyframe.
   */
  int getTime();

  /**
   * Interpolates the shape that exists at the time in between.
   *
   * @param otherKeyframe   the other KeyFrame to interpolate the shape from, the name of the shape
   *                        from the other keyframe must be the same.
   * @param interpolateTime the time that the interpolated shape exists at.
   * @return The shape that exists between this keyframe and otherKeyFrame at the time
   */
  Shape interpolate(Keyframe otherKeyframe, int interpolateTime);

  /**
   * Describes the changes that are occurring between two keyframes in a readable manner.
   *
   * @param other the other KeyFrame representing the changed state of the animation.
   * @return string description of model
   */
  String getDescription(Keyframe other);
}
