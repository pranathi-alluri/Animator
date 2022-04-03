package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents an immutable oval in an animation.
 * x and y represent the center of the oval.
 */
public class SimpleOval extends SimpleShape implements Shape {
  public SimpleOval(String name, float x, float y, Color color, float height, float width) {
    super(name, x, y, color, height, width);
    this.type = ShapeType.OVAL;
  }
}
