package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents an immutable shape in the animation model.
 */
public interface Shape {

  /**
   * Gets the unique name of this shape. Used as an identifier.
   * @return the name of this shape
   */
  String getName();

  /**
   * Gets the X position of the top left corner of this shape on the coordinate grid.
   * @return the x of this shape
   */
  float getX();

  /**
   * Gets the Y position of the top left corner of this shape on the coordinate grid.
   * @return the y of this shape
   */
  float getY();

  /**
   * Gets the color of this shape.
   * @return the color of this shape
   */
  Color getColor();

  /**
   * Gets the height of this shape in pixels.
   * @return the height of this shape
   */
  float getHeight();

  /**
   * Gets the width of this shape in pixels.
   * @return the width of this shape
   */
  float getWidth();

  /**
   * Gets the ShapeType of this shape.
   * @return the ShapeType of this shape.
   */
  ShapeType getType();
}
