package cs3500.animator.model;
import java.awt.*;
import java.util.ArrayList;

import cs3500.animator.io.TweenModelBuilder;
import cs3500.animator.io.AnimationFileReader;

public class AnimationBuilder implements TweenModelBuilder<AnimationModel> {

  private final AnimationModel model;

  public AnimationBuilder() {
    this.model = new SimpleAnimationModel();
  }

  public AnimationBuilder(AnimationModel model) {
    if(model == null) {
      throw new IllegalArgumentException("model must not be null");
    }
    this.model = model;
  }

  /**
   * Set the bounds of the canvas for the animation.
   *
   * @param width  the width in pixels of the canvas
   * @param height the height in pixels of the canvas
   */
  @Override
  public TweenModelBuilder<AnimationModel> setBounds(int width, int height) {
    this.model.setWidth(width);
    this.model.setHeight(height);
    return this;
  }

  /**
   * Add a new oval to the model with the given specifications.
   *
   * @param name        the unique name given to this shape
   * @param cx          the x-coordinate of the center of the oval
   * @param cy          the y-coordinate of the center of the oval
   * @param xRadius     the x-radius of the oval
   * @param yRadius     the y-radius of the oval
   * @param red         the red component of the color of the oval
   * @param green       the green component of the color of the oval
   * @param blue        the blue component of the color of the oval
   * @param startOfLife the time tick at which this oval appears
   * @param endOfLife   the time tick at which this oval disappears
   * @return the builder object
   */
  @Override
  public TweenModelBuilder<AnimationModel> addOval(String name, float cx, float cy, float xRadius, float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
    if(name == null) {
      throw new IllegalArgumentException("name must not be null");
    } else if (startOfLife >= endOfLife) {
      throw new IllegalArgumentException("startOfLife must be less than endOfLife");
    }
    Shape oval = new SimpleOval(name, cx, cy, new Color(red, green, blue), yRadius, xRadius);
    Keyframe keyframe1 = new SimpleKeyframe(oval, startOfLife);
    Keyframe keyframe2 = new SimpleKeyframe(oval, endOfLife);
    this.model.addKeyframe(keyframe1);
    this.model.addKeyframe(keyframe2);
    return this;
  }

  /**
   * Add a new rectangle to the model with the given specifications.
   *
   * @param name        the unique name given to this shape
   * @param lx          the minimum x-coordinate of a corner of the
   *                    rectangle
   * @param ly          the minimum y-coordinate of a corner of the
   *                    rectangle
   * @param width       the width of the rectangle
   * @param height      the height of the rectangle
   * @param red         the red component of the color of the rectangle
   * @param green       the green component of the color of the rectangle
   * @param blue        the blue component of the color of the rectangle
   * @param startOfLife the time tick at which this rectangle appears
   * @param endOfLife   the time tick at which this rectangle disappears
   * @return the builder object
   */
  @Override
  public TweenModelBuilder<AnimationModel> addRectangle(String name, float lx, float ly, float width, float height, float red, float green, float blue, int startOfLife, int endOfLife) {
    if(name == null) {
      throw new IllegalArgumentException("name must not be null");
    } else if (startOfLife >= endOfLife) {
      throw new IllegalArgumentException("startOfLife must be less than endOfLife");
    }
    Shape rectangle = new SimpleOval(name, lx, ly, new Color(red, green, blue), height, width);
    Keyframe keyframe1 = new SimpleKeyframe(rectangle, startOfLife);
    Keyframe keyframe2 = new SimpleKeyframe(rectangle, endOfLife);
    this.model.addKeyframe(keyframe1);
    this.model.addKeyframe(keyframe2);
    return this;
  }

  /**
   * Move the specified shape to the given position during the given time
   * interval.
   *
   * @param name      the unique name of the shape to be moved
   * @param moveFromX the x-coordinate of the initial position of this shape.
   *                  What this x-coordinate represents depends on the shape.
   * @param moveFromY the y-coordinate of the initial position of this shape.
   *                  what this y-coordinate represents depends on the shape.
   * @param moveToX   the x-coordinate of the final position of this shape. What
   *                  this x-coordinate represents depends on the shape.
   * @param moveToY   the y-coordinate of the final position of this shape. what
   *                  this y-coordinate represents depends on the shape.
   * @param startTime the time tick at which this movement should start
   * @param endTime   the time tick at which this movement should end
   */
  @Override
  public TweenModelBuilder<AnimationModel> addMove(String name, float moveFromX, float moveFromY, float moveToX, float moveToY, int startTime, int endTime) {
    if(name == null) {
      throw new IllegalArgumentException("name must not be null");
    } else if (startTime >= endTime) {
      throw new IllegalArgumentException("startTime must be less than endTime");
    }
    Shape startShape = model.getShapeAtTime(name, startTime);
    Shape endShape = model.getShapeAtTime(name, endTime);
    Shape newStartShape = SimpleShapeFactory.getShape(startShape.getType(), name, moveFromX,
            moveFromY, startShape.getColor(), startShape.getHeight(), startShape.getWidth());
    Shape newEndShape = SimpleShapeFactory.getShape(endShape.getType(), name, moveToX, moveToY,
            endShape.getColor(), endShape.getHeight(), endShape.getWidth());
    Keyframe startKeyframe = new SimpleKeyframe(newStartShape, startTime);
    Keyframe endKeyframe = new SimpleKeyframe(newEndShape, endTime);
    model.addKeyframe(startKeyframe);
    model.addKeyframe(endKeyframe);
    return this;
  }

  /**
   * Change the color of the specified shape to the new specified color in the
   * specified time interval.
   *
   * @param name      the unique name of the shape whose color is to be changed
   * @param oldR      the r-component of the old color
   * @param oldG      the g-component of the old color
   * @param oldB      the b-component of the old color
   * @param newR      the r-component of the new color
   * @param newG      the g-component of the new color
   * @param newB      the b-component of the new color
   * @param startTime the time tick at which this color change should start
   * @param endTime   the time tick at which this color change should end
   */
  @Override
  public TweenModelBuilder<AnimationModel> addColorChange(String name, float oldR, float oldG, float oldB, float newR, float newG, float newB, int startTime, int endTime) {
    if(name == null) {
      throw new IllegalArgumentException("name must not be null");
    } else if (startTime >= endTime) {
      throw new IllegalArgumentException("startTime must be less than endTime");
    }
    Shape startShape = model.getShapeAtTime(name, startTime);
    Shape endShape = model.getShapeAtTime(name, endTime);
    Shape newStartShape = SimpleShapeFactory.getShape(startShape.getType(), name, startShape.getX(), startShape.getY(), new Color(oldR, oldG, oldB), startShape.getHeight(), startShape.getWidth());
    Shape newEndShape = SimpleShapeFactory.getShape(endShape.getType(), name, endShape.getX(), endShape.getY(), new Color(newR, newG, newB), endShape.getHeight(), endShape.getWidth());
    Keyframe startKeyframe = new SimpleKeyframe(newStartShape, startTime);
    Keyframe endKeyframe = new SimpleKeyframe(newEndShape, endTime);
    model.addKeyframe(startKeyframe);
    model.addKeyframe(endKeyframe);
    return this;
  }

  /**
   * Change the x and y extents of this shape from the specified extents to the
   * specified target extents. What these extents actually mean depends on the
   * shape, but these are roughly the extents of the box enclosing the shape
   *
   * @param name
   * @param fromSx
   * @param fromSy
   * @param toSx
   * @param toSy
   * @param startTime
   * @param endTime
   */
  @Override
  public TweenModelBuilder<AnimationModel> addScaleToChange(String name, float fromSx, float fromSy, float toSx, float toSy, int startTime, int endTime) {
    if(name == null) {
      throw new IllegalArgumentException("name must not be null");
    } else if (startTime >= endTime) {
      throw new IllegalArgumentException("startTime must be less than endTime");
    }
    Shape startShape = model.getShapeAtTime(name, startTime);
    Shape endShape = model.getShapeAtTime(name, endTime);
    Shape newStartShape = SimpleShapeFactory.getShape(startShape.getType(), name, startShape.getX(), startShape.getY(), startShape.getColor(), fromSy, fromSx);
    Shape newEndShape = SimpleShapeFactory.getShape(endShape.getType(), name, endShape.getX(), endShape.getY(), endShape.getColor(), toSx, toSy);
    Keyframe startKeyframe = new SimpleKeyframe(newStartShape, startTime);
    Keyframe endKeyframe = new SimpleKeyframe(newEndShape, endTime);
    model.addKeyframe(startKeyframe);
    model.addKeyframe(endKeyframe);
    return this;
  }

  /**
   * Return the model built so far.
   *
   * @return the model that was constructed so far
   */
  @Override
  public AnimationModel build() {
    return model;
  }
}