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
  
  
  @Override
  public String getDescription(Keyframe other) {
    Shape shapeEnd = other.getShape();
    int timeEnd = other.getTime();
    String move;
    String scale;
    String color;
    String s = this.shape.getName();
    if (this.shape.getX() == shapeEnd.getX() && this.shape.getY() == shapeEnd.getY()) {
      move = s + "stays put at (" + this.shape.getX() + "," + this.shape.getY() + "), ";
    } else {
      move = s + "moves from (" + this.shape.getX() + "," + this.shape.getY() + ") to ("
      + shapeEnd.getX() + "," + shapeEnd.getY() + "), ";
    }
    if (this.shape.getWidth() == shapeEnd.getWidth()
            && this.shape.getHeight() == shapeEnd.getHeight()) {
      scale = "stays size " + this.shape.getWidth() + "x" + this.shape.getHeight() + ", ";
    } else {
      scale = "scales from " + this.shape.getWidth() + "x" + this.shape.getHeight() + "to "
              + shapeEnd.getWidth() + "x" + shapeEnd.getHeight() + ", ";
    }
    if (this.shape.getColor().equals(shapeEnd.getColor())) {
      color = " and the color stays" + this.shape.getColor() + ".";
    } else {
      color = " and the color changes from " + this.shape.getColor() + " to "
              + this.shape.getColor() + ".";
    }
    return "From time " + this.time + " to " + other.getTime() + ", " + move + scale + color;
  }

  @Override
  public boolean equals(Object o) {
    if(o instanceof Keyframe) {
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
