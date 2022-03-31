package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents an abstract version of a simple shape.
 */
public abstract class SimpleShape implements Shape {

  protected final String name;
  protected final float x;
  protected final float y;
  protected final Color color;
  protected final float height;
  protected final float width;
  protected ShapeType type;

  /**
   * Constructor for SimpleShape, does not instantiate ShapeType as that is done in the child
   * constructor.
   *
   * @param name   the name of this shape, used as an id
   * @param x      the x position of the top left corner of this shape
   * @param y      the y position of the top left corner of this shape
   * @param color  the color of this shape
   * @param height the height of this shape in pixels
   * @param width  the width of this shape in pixels
   */
  public SimpleShape(String name, float x, float y,
                     Color color, float height, float width) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.name = name;
    this.x = x;
    this.y = y;
    this.color = color;
    this.height = height;
    this.width = width;
  }

  /**
   * Gets the unique name of this shape. Used as an identifier.
   *
   * @return the name of this shape
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the X position of the center of this shape on the coordinate grid.
   *
   * @return the x of this shape
   */
  @Override
  public float getX() {
    return x;
  }

  /**
   * Gets the Y position of the center of this shape on the coordinate grid.
   *
   * @return the y of this shape
   */
  @Override
  public float getY() {
    return y;
  }

  /**
   * Gets the color of this shape.
   *
   * @return the color of this shape
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Gets the height of this shape in pixels.
   *
   * @return the height of this shape
   */
  @Override
  public float getHeight() {
    return height;
  }

  /**
   * Gets the width of this shape in pixels.
   *
   * @return the width of this shape
   */
  @Override
  public float getWidth() {
    return width;
  }

  /**
   * Gets the ShapeType of this shape.
   *
   * @return the ShapeType of this shape.
   */
  @Override
  public ShapeType getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Create " + this.type + " named " + this.name + " with center at ("
            + this.x + "," + this.y + "), width " + this.width + " and height "
            + this.height;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Shape) {
      Shape other = (Shape) o;
      return (this.name.equals(other.getName()) &&
              this.type == other.getType() &&
              Math.round(this.x) == Math.round(other.getX()) &&
              Math.round(this.y) == Math.round(other.getY()) &&
              Math.round(this.height) == Math.round(other.getHeight()) &&
              Math.round(this.width) == Math.round(other.getWidth()) &&
              this.color.equals(other.getColor()));
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return (this.name.hashCode() +
            this.type.hashCode() +
            Integer.hashCode(Math.round(this.x)) +
            Integer.hashCode(Math.round(this.y)) +
            Integer.hashCode(Math.round(this.height)) +
            Integer.hashCode(Math.round(this.width)) +
            this.color.hashCode());
  }
}
