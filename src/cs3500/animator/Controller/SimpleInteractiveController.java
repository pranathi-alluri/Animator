package cs3500.animator.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 * The controller for an interactive animation, that is able to receive inputs when the buttons are
 * clicked.
 */
public class SimpleInteractiveController extends SimpleVisualController implements ActionListener {

  /**
   * The constructor for the interactive controller.
   *
   * @param model the model to animate
   * @param tempo the speed
   * @param type  the type of view
   */
  public SimpleInteractiveController(ViewOnlyAnimationModel model, int tempo, String type) {
    super(model, tempo, type);
    view.setListener(this);
  }

  @Override
  public void startTimer() {
    int delay = 1000 / tempo;
    int FINAL_TICK = finalTick();
    ActionListener listener = new ActionListener() {
      private int tick = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        view.onTick(tick);
        tick++;

        view.loop(FINAL_TICK);
      }
    };
    timer = new Timer(delay, listener);
    timer.start();

  }

  /**
   * Get's the last tick of the keyframe .
   *
   * @return the final tick value
   */
  private int finalTick() {
    int lastTick = 0;
    for (ArrayList<Keyframe> keyframe : model.getAllKeyframes()) {
      if (keyframe.get(keyframe.size() - 1).getTime() > lastTick) {
        lastTick = keyframe.get(keyframe.size() - 1).getTime();
      }
    }
    return lastTick;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "play":
        timer.start();
        break;
      case "pause":
        timer.stop();
        break;
      case "restart":
        view.restart();
        break;
      case "speed":
        try {
          tempo = 1000 / Integer.parseInt(JOptionPane.showInputDialog("Please input new speed."));
          if (tempo >= 1) {
            timer.setDelay(tempo);
          } else {
            JOptionPane.showMessageDialog(null, "Invalid speed.",
                    "WARNING", JOptionPane.ERROR_MESSAGE);
          }
        } catch (NumberFormatException fe) {
          JOptionPane.showMessageDialog(null, "Invalid number format.",
                  "WARNING", JOptionPane.ERROR_MESSAGE);
        }
        break;

      default:
        throw new IllegalArgumentException("Not a supported function");
    }
  }
}
