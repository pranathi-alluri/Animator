package cs3500.animator.Controller;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import cs3500.animator.model.ViewOnlyAnimationModel;
import cs3500.animator.view.AnimationText;
import cs3500.animator.view.AnimationViewFactory;
import cs3500.animator.view.SimpleAnimationView;

public class SimpleTextController implements Controller{
  private ViewOnlyAnimationModel model;
  private int tempo;
  private AnimationText view;

  public SimpleTextController(ViewOnlyAnimationModel model, int tempo, Appendable ap,
                              String type) {
    if (model == null) {
      throw new IllegalArgumentException("Model must not be null.");
    }
    if (tempo < 0) {
      throw new IllegalArgumentException("Tempo must be positive.");
    }

    this.model = model;
    this.tempo = tempo;
    this.view = AnimationViewFactory.getTextView(type, model, ap, tempo);
  }


  @Override
  public void start() {
    try {
      view.makeVisible();
    } catch (IOException e) {
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: was unable to create view");
      e.printStackTrace();
    }
  }


}
