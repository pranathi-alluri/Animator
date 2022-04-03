package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents an immutable rectangle in an animation.
 * x and y represent the top left corner of the rectangle
 */
public class SimpleRectangle extends SimpleShape implements Shape  {

  public SimpleRectangle(String name, float x, float y,
                         Color color, float height, float width) {
    super(name, x, y, color, height, width);
    this.type = ShapeType.RECTANGLE;
  }
}
