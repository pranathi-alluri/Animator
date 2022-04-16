package cs3500.animator.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import cs3500.animator.model.ViewOnlyAnimationModel;
import cs3500.animator.view.AnimationViewFactory;
import cs3500.animator.view.AnimationVisual;

/**
 * The controller for a visual view.
 */
public class SimpleVisualController implements VisualController {
  protected ViewOnlyAnimationModel model;
  protected int tempo;
  protected AnimationVisual view;
  protected Timer timer;

  /**
   * The constructor that creates a controller to be able to run a visual view.
   *
   * @param model the model being animated.
   * @param tempo the speed.
   * @param type  the type of view.
   */
  public SimpleVisualController(ViewOnlyAnimationModel model, int tempo, String type) {
    if (model == null) {
      throw new IllegalArgumentException("Model must not be null.");
    }
    if (tempo < 0) {
      throw new IllegalArgumentException("Tempo must be positive.");
    }

    this.model = model;
    this.tempo = tempo;
    this.view = AnimationViewFactory.getVisualView(type, model, tempo);
  }

  @Override
  public void start() {
    try {
      this.view.makeVisible();
      this.startTimer();
    } catch (IOException e) {
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: was unable to create view");
      e.printStackTrace();
    }
  }

  @Override
  public void startTimer() {
    int delay = 1000 / tempo;

    ActionListener listener = new ActionListener() {
      private int tick = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        view.onTick(tick);
        tick++;
      }
    };
    timer = new Timer(delay, listener);
    timer.start();
  }


}
