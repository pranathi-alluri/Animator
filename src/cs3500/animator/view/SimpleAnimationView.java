package cs3500.animator.view;


import java.io.IOException;

/**
 * Represents the interface for the views of a simple animation.
 */
public interface SimpleAnimationView {

  /**
   * Makes the view visible. There are three types of views: Textual, SVG, and visual.
   *
   * @throws IOException if transmission to the provided data destination fails
   */
  void makeVisible() throws IOException;

}
