package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents an immutable oval in an animation.
 */
public class SimpleOval extends SimpleShape implements Shape{
  public SimpleOval(String name, float x, float y, Color color, float height, float width) {
    super(name, x, y, color, height, width);
    this.type = ShapeType.OVAL;
  }
}
