package cs3500.animator.model;

/**
 * Static factory class for simple shapes.
 */
public class SimpleShapeFactory {
  /**
   * Static factory method for simple shapes.
   * @param type type of the shape to create
   * @param name unique name of the shape to create
   * @param x x position of the top left corner of the shape
   * @param y y position of the top left corner of the shape
   * @param color color of the shape
   * @param height height of the shape in pixels
   * @param width width of the shape in pixels
   * @return Freshly constructed shape
   */
  public static Shape getShape(ShapeType type, String name, double x, double y,
                               Color color, double height, double width) {
    if(type == ShapeType.OVAL) {
      return new SimpleOval(name, x, y, color, height, width);
    } else if(type == ShapeType.RECTANGLE) {
      return new SimpleRectangle(name, x, y, color, height, width);
    } else {
      return null;
    }
  }
}
