package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


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
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);

    int time = 1000 / this.tempo;
    ActionListener listener = new ActionListener() {
      private int tick = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        onTick(tick);
        tick++;
      }
    };
   timer = new Timer(time, listener);
   timer.start();
  }


}
