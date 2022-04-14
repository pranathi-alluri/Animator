package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.ViewOnlyAnimationModel;

public class SimpleAnimationCompositeView extends AnimationVisualViews
        implements ActionListener {

  private JCheckBoxMenuItem loop;

  /**
   * Constructor for the Simple animation visual view.
   *
   * @param model the model connected to the view.
   * @param tempo the tempo in ticks per second.
   */
  public SimpleAnimationCompositeView(ViewOnlyAnimationModel model, int tempo) {
    super(model, tempo);

    JMenuBar menu = new JMenuBar();

    JMenuItem start = new JMenuItem("Start");
    start.setActionCommand("start");
    start.addActionListener(this);
    menu.add(start);

    JMenuItem pause = new JMenuItem("Pause");
    pause.setActionCommand("pause");
    pause.addActionListener(this);
    menu.add(pause);

    JMenuItem restart = new JMenuItem("Restart");
    restart.setActionCommand("restart");
    restart.addActionListener(this);
    menu.add(restart);

    JMenuItem speed = new JMenuItem("Change speed");
    speed.setActionCommand("speed");
    speed.addActionListener(this);
    menu.add(speed);

    loop = new JCheckBoxMenuItem("Loop");
    loop.setSelected(false);
    loop.addActionListener(this);
    menu.add(loop);

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
    final int FINAL_TICK = finalTick();

    // Calculate the delay of the timer based on the given tempo.
    int delay = 1000 / tempo;
    ActionListener listener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        onTick(panel.getCurrentTick() + 1);

        if (loop.getState() && panel.getCurrentTick() > FINAL_TICK) {
          panel.setTick(0);
        }
      }
    };
    timer = new Timer(delay, listener);
    timer.start();
  }

//  private int finalTick() {
//    int lastTick = 0;
//    for(ArrayList<Keyframe> keyframe : model.getAllKeyframes()){
//      for(Keyframe k : keyframe) {
//        if(k.getTime() > lastTick) {
//          lastTick = k.getTime();
//        }
//      }
//    }
//    return lastTick;
//  }

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
      case "start":
        timer.start();
        break;
      case "pause":
        timer.stop();
        break;
      case "restart":
        panel.setTick(0);
        break;
      case "speed":
        try {
          tempo = 1000 / Integer.parseInt(JOptionPane.showInputDialog("Please input new speed."));
          if (tempo >= 1) {
            timer.setDelay(tempo);
          } else {
            JOptionPane.showMessageDialog(null, "Invalid speed.", "WARNING", JOptionPane
                    .ERROR_MESSAGE);
          }
        } catch (NumberFormatException fe) {
          JOptionPane.showMessageDialog(null, "Invalid number format.", "WARNING", JOptionPane
                  .ERROR_MESSAGE);
        }
        break;
      default:
        throw new IllegalArgumentException("Not a supported function");
    }
  }
}
