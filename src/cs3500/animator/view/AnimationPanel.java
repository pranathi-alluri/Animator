package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.text.View;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 *
 */
public class AnimationPanel extends JPanel {
  private ViewOnlyAnimationModel model;
  private int currentTick;

  /**
   *
   * @param model
   */
  public AnimationPanel(ViewOnlyAnimationModel model) {
    super();
    this.model = model;
    this.currentTick = 0;
    this.setBackground(Color.WHITE);
  }


  /**
   *
   * @param tick
   */
  public void setTick(int tick) {
    this.currentTick = tick;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D graphic = (Graphics2D) g;

    for(Shape s : model.getAllShapesAtTime(this.currentTick)) {
      graphic.setColor(s.getColor());

      switch(s.getType()) {
        case OVAL:
          graphic.fillOval((int)s.getX(), (int)s.getY(), (int)s.getWidth(), (int)s.getHeight());
          break;
        case RECTANGLE:
          graphic.fillRect((int)s.getX(), (int)s.getY(), (int)s.getWidth(), (int)s.getHeight());
          break;
        default:
          throw new IllegalArgumentException("Not a valid shape for animation.");
      }
    }



  }

}
