package cs3500.animator.Controller;

/**
 * The interface for controller that deals with visual views of the animation.
 */
public interface VisualController extends Controller {

  /**
   * Lets the controller start the timer based on input, so the view can be run.
   */
  void startTimer();
}
