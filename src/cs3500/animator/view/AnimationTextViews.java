package cs3500.animator.view;

import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 * Represents an abstract class for text views, taking in input from a model and appending it to
 * an appendable.
 */
public abstract class AnimationTextViews implements AnimationText {
  protected final ViewOnlyAnimationModel model;
  protected Appendable out;
  protected final float tempo;

  /**
   * Constructor for textual views which takes in a model, the output source, and the tempo.
   *
   * @param model Model used for the animation.
   * @param out   The output where the view is displayed.
   * @param tempo the tempo of the animation in ticks per second.
   */
  public AnimationTextViews(ViewOnlyAnimationModel model, Appendable out, float tempo) {
    if (tempo < 0) {
      throw new IllegalArgumentException("Temp cannot be negative.");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null.");
    }

    if (out == null) {
      throw new IllegalArgumentException("Appendable cannot be null.");
    }

    this.model = model;
    this.out = out;
    this.tempo = tempo;
  }

  /**
   * Returns a text representation of the animation.
   *
   * @return String description for the simple animation.
   */
  public String getText() {
    return this.out.toString();
  }
}
