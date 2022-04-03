package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


import cs3500.animator.model.ViewOnlyAnimationModel;

/**
 *
 */
public class SimpleAnimationVisualView extends JFrame implements SimpleAnimationView {
  protected ViewOnlyAnimationModel model;
  protected AnimationPanel panel;
  protected int tempo;
  protected Timer timer;

  /**
   *
   * @param model
   * @param tempo
   */
  public SimpleAnimationVisualView(ViewOnlyAnimationModel model, int tempo) {
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
    this.pack();
    }

    @Override
  public void makeVisible(){
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
      new Timer(time, listener).start();
    }

  /**
   * Draw the animation at the given tick.
   * @param tick the current tick.
   */
  private void onTick(int tick) {
    panel.setTick(tick);
    panel.revalidate();
    panel.repaint();
    }


}
