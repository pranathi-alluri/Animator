package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;

import javax.swing.JPanel;

import cs3500.animator.model.Shape;
import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 * A panel that can hold the actual simple animation visualization.
 */
public class AnimationPanel extends JPanel {
  private ViewOnlyAnimationModel model;
  private int currentTick;

  /**
   * Constructor for the simple animation panel.
   *
   * @param model the model that is being animated.
   */
  public AnimationPanel(ViewOnlyAnimationModel model) {
    super();
    this.model = model;
    this.currentTick = 0;
    this.setBackground(Color.WHITE);
  }


  /**
   * Sets the current tick.
   *
   * @param tick the current tick.
   */
  public void setTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick must be positive.");
    }
    this.currentTick = tick;
  }

  public int getCurrentTick() {
    return this.currentTick;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D graphic = (Graphics2D) g;

    for (Shape s : model.getAllShapesAtTime(this.currentTick)) {
      graphic.setColor(s.getColor());

      switch (s.getType()) {
        case OVAL:
          int ovalX = (int) (s.getX() - (s.getWidth() / 2));
          int ovalY = (int) (s.getY() - (s.getHeight() / 2));
          graphic.fillOval(ovalX, ovalY, (int) s.getWidth(), (int) s.getHeight());
          break;
        case RECTANGLE:
          graphic.fillRect((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          break;
        default:
          throw new IllegalArgumentException("Not a valid shape for animation.");
      }
    }


  }

}
