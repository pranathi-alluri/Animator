package cs3500.animator.view;

import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 * Factory class to create a simple animation view.
 */
public class AnimationViewFactory {

  /**
   * Creates a new view of the given type.
   *
   * @param viewType the type of view to create.
   * @param model    the model that is being animated.
   * @param ap       the output the view should return to.
   * @param tempo    the tempo in ticks per second provided by the user.
   * @return a new view.
   */
  public static AnimationText getTextView(String viewType, ViewOnlyAnimationModel model,
                                          Appendable ap, int tempo) {
    switch (viewType) {
      case "text":
        return new SimpleAnimationTextualView(model, ap, tempo);
      case "svg":
        return new SimpleAnimationSVGView(model, ap, tempo);
      default:
        throw new IllegalArgumentException("Not a valid view type.");
    }
  }

  public static AnimationVisual getVisualView(String viewType, ViewOnlyAnimationModel model,
                                              int tempo) {
    switch (viewType) {
      case "visual":
        return new SimpleAnimationVisualView(model, tempo);
      case "interactive":
        return new SimpleAnimationCompositeView(model, tempo);
      default:
        throw new IllegalArgumentException("Not a valid view type.");
    }

  }
}
