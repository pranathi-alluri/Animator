package cs3500.animator.model;

/**
 * Represents an immutable rectangle in an animation.
 */
public class SimpleRectangle extends SimpleShape implements Shape  {

  public SimpleRectangle(String name, double x, double y,
                         Color color, double height, double width) {
    super(name, x, y, color, height, width);
    this.type = ShapeType.RECTANGLE;
  }
}
