package cs3500.animator.view;

import java.awt.*;

import javax.swing.*;

import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 * An abstract class that creates a common panel of a visual view.
 */
public abstract class AnimationVisualViews extends JFrame implements AnimationVisual {
  protected ViewOnlyAnimationModel model;
  protected AnimationPanel panel;
  protected int tempo;

  /**
   * Constructor for the Simple animation visual view.
   *
   * @param model the model connected to the view.
   * @param tempo the tempo in ticks per second.
   */
  public AnimationVisualViews(ViewOnlyAnimationModel model, int tempo) {
    super();

    if (model == null) {
      throw new IllegalArgumentException("Model must not be null.");
    }
    if (tempo < 0) {
      throw new IllegalArgumentException("Tempo must be positive.");
    }
    this.model = model;
    this.tempo = tempo;


    this.setTitle("SimpleAnimator");
    this.setSize(model.getWidth(), model.getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.panel = new AnimationPanel(model);
    panel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));

    JScrollPane scroll = new JScrollPane(panel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.add(scroll, BorderLayout.CENTER);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Draw the animation at the given tick.
   * @param tick the current tick.
   */
  @Override
  public void onTick(int tick) {
    panel.setTick(tick);
    panel.revalidate();
    panel.repaint();
  }


}
