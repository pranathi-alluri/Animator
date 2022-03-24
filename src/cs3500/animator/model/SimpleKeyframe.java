package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents a shape at a time in an animation.
 */
public class SimpleKeyframe implements Keyframe {

  private final Shape shape;
  private final int time;

  /**
   * Constructor for SimpleKeyframe.
   *
   * @param shape the shape of this keyframe
   * @param time  the time that this keyframe exists at
   */
  public SimpleKeyframe(Shape shape, int time) {
    if (shape == null) {
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
   */
  @Override
  public Shape interpolate(Keyframe otherKeyframe, int interpolateTime) {
    if (otherKeyframe == null) {
      throw new IllegalArgumentException("otherKeyframe must not be null");
    }
    if (interpolateTime < otherKeyframe.getTime() && interpolateTime < time ||
            interpolateTime > otherKeyframe.getTime() && interpolateTime > time) {
      throw new IllegalArgumentException("interpolateTime must be between the two times " +
              "of the keyframes");
    }
    if (!(shape.getName().equals(otherKeyframe.getShape().getName()))) {
      throw new IllegalArgumentException("The shapes of both keyframes must have the same names");
    }
    if (shape.getType() != otherKeyframe.getShape().getType()) {
      throw new IllegalArgumentException("The shapes of both keyframes must be of the same type");
    }
    float x = interpolate(time, interpolateTime, otherKeyframe.getTime(), shape.getX(),
            otherKeyframe.getShape().getX());
    float y = interpolate(time, interpolateTime, otherKeyframe.getTime(), shape.getY(),
            otherKeyframe.getShape().getY());
    float height = interpolate(time, interpolateTime, otherKeyframe.getTime(), shape.getHeight(),
            otherKeyframe.getShape().getHeight());
    float width = interpolate(time, interpolateTime, otherKeyframe.getTime(), shape.getWidth(),
            otherKeyframe.getShape().getWidth());
    int red = (int) interpolate(time, interpolateTime, otherKeyframe.getTime(),
            shape.getColor().getRed(), otherKeyframe.getShape().getColor().getRed());
    int green = (int) interpolate(time, interpolateTime, otherKeyframe.getTime(),
            shape.getColor().getGreen(), otherKeyframe.getShape().getColor().getGreen());
    int blue = (int) interpolate(time, interpolateTime, otherKeyframe.getTime(),
            shape.getColor().getBlue(), otherKeyframe.getShape().getColor().getBlue());

    return SimpleShapeFactory.getShape(shape.getType(), shape.getName(), x, y,
            new Color(red, green, blue), height, width);
  }

  private float interpolate(float t0, float t1, float t2, float value1, float value2) {
    return (value2 - value1) * (1 - ((t2 - t1) / (t2 - t0))) + value1;
  }


  @Override
  public String getDescription(Keyframe other) {
    Shape shapeEnd = other.getShape();
    int timeEnd = other.getTime();
    String move;
    String scale;
    String color;
    String s = this.shape.getName();
    if (this.shape.getX() == shapeEnd.getX() && this.shape.getY() == shapeEnd.getY()) {
      move = s + " stays put at (" + this.shape.getX() + "," + this.shape.getY() + "), ";
    } else {
      move = s + " moves from (" + this.shape.getX() + "," + this.shape.getY() + ") to ("
              + shapeEnd.getX() + "," + shapeEnd.getY() + "), ";
    }
    if (this.shape.getWidth() == shapeEnd.getWidth()
            && this.shape.getHeight() == shapeEnd.getHeight()) {
      scale = "stays size " + this.shape.getWidth() + "x" + this.shape.getHeight() + ", ";
    } else {
      scale = "scales from " + this.shape.getWidth() + "x" + this.shape.getHeight() + " to "
              + shapeEnd.getWidth() + "x" + shapeEnd.getHeight() + ", ";
    }
    if (this.shape.getColor().equals(shapeEnd.getColor())) {
      color = " and the color stays" + this.shape.getColor().toString() + ".";
    } else {
      color = " and the color changes from " + this.shape.getColor().toString() + " to "
              + this.shape.getColor() + ".";
    }
    return "From time " + this.time + " to " + other.getTime() + ", " + move + scale + color;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Keyframe) {
      Keyframe other = (Keyframe) o;
      return this.shape.equals(other.getShape()) &&
              this.time == other.getTime();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.shape.hashCode() + Integer.hashCode(this.time);
  }
}
