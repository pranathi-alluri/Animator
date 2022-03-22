package cs3500.animator.model;

/**
 * Represents an abstract version of a simple shape.
 */
public abstract class SimpleShape implements Shape{

  protected final String name;
  protected final double x;
  protected final double y;
  protected final Color color;
  protected final double height;
  protected final double width;
  protected ShapeType type;

  public SimpleShape(String name, double x, double y,
                         Color color, double height, double width) {
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
   * Gets the X position of the top left corner of this shape on the coordinate grid.
   *
   * @return the x of this shape
   */
  @Override
  public Double getX() {
    return x;
  }

  /**
   * Gets the Y position of the top left corner of this shape on the coordinate grid.
   *
   * @return the y of this shape
   */
  @Override
  public Double getY() {
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
  public Double getHeight() {
    return height;
  }

  /**
   * Gets the width of this shape in pixels.
   *
   * @return the width of this shape
   */
  @Override
  public Double getWidth() {
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
}
