package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents an immutable rectangle in an animation.
 */
public class SimpleRectangle extends SimpleShape implements Shape  {

  public SimpleRectangle(String name, float x, float y,
                         Color color, float height, float width) {
    super(name, x, y, color, height, width);
    this.type = ShapeType.RECTANGLE;
  }
}
