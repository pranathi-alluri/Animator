package cs3500.animator.view;

import java.awt.event.ActionListener;


import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 * Represents a visual view of the animation using Javax Swing to draw the moved and shapes.
 */
public class SimpleAnimationVisualView extends AnimationVisualViews {

  /**
   * Constructor for the Simple animation visual view.
   *
   * @param model the model connected to the view.
   * @param tempo the tempo in ticks per second.
   */
  public SimpleAnimationVisualView(ViewOnlyAnimationModel model, int tempo) {

    super(model, tempo);
    this.pack();
  }


  @Override
  public void loop(int last) {
    throw new IllegalArgumentException("Looping is not available for this view.");
  }

  @Override
  public void restart() {
    throw new IllegalArgumentException("This function is not available for this view");
  }

  @Override
  public void setListener(ActionListener a) {
    throw new IllegalArgumentException("This view is not interactive.");

  }
}
