package cs3500.animator.view;

import java.awt.event.ActionListener;

/**
 * The interface that holds the methods to create a visual view.
 */
public interface AnimationVisual extends SimpleAnimationView {

  /**
   * Draw the animation at the given tick.
   *
   * @param tick the current tick.
   */
  void onTick(int tick);

  /**
   * Check to see if the loop is selected in the interactive view.
   *
   * @param last the final tick of animation
   */
  void loop(int last);

  /**
   * Restarts the animation.
   */

  void restart();

  /**
   * Gets the input from the controller and passes it to the model.
   *
   * @param a stores the actions occuring.
   */
  void setListener(ActionListener a);
}
