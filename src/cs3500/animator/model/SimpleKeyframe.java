package cs3500.animator.model;

/**
 * Represents a shape at a time in an animation.
 */
public class SimpleKeyframe implements Keyframe{

  private final Shape shape;
  private final int time;

  /**
   * Constructor for SimpleKeyframe.
   * @param shape the shape of this keyframe
   * @param time the time that this keyframe exists at
   */
  public SimpleKeyframe(Shape shape, int time) {
    if(shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    this.shape = shape;
    this.time = time;
  }

  /**
   * Returns the shape of this keyframe.
   *
   * @return the shape of this keyframe.
   */
  @Override
  public Shape getShape() {
    return this.shape;
  }

  /**
   * Returns the time this keyframe exists at.
   *
   * @return the time of this keyframe.
   */
  @Override
  public int getTime() {
    return this.time;
  }

  /**
   * Interpolates the shape that exists at the time in between.
   *
   * @param otherKeyframe   the other KeyFrame to interpolate the shape from, the name of the shape
   *                        from the other keyframe must be the same.
   * @param interpolateTime the time that the interpolated shape exists at.
   * @return The shape that exists between this keyframe and otherKeyFrame at the time
   * interpolateTime
   */
  @Override
  public Shape interpolate(Keyframe otherKeyframe, int interpolateTime) {
    return null;
  }
}
