package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.ViewOnlyAnimationModel;

public class SimpleAnimationCompositeView extends SimpleAnimationVisualView{

  private JCheckBox loopSwitch;
  private JButton start;
  private JButton pause;
  private JButton restart;
  private JButton speed;
  /**
   * Constructor for the Simple animation visual view.
   *
   * @param model the model connected to the view.
   * @param tempo the tempo in ticks per second.
   */
  public SimpleAnimationCompositeView(ViewOnlyAnimationModel model, int tempo) {
    super(model, tempo);

    // buttons
    Box buttonPanel = new Box(BoxLayout.Y_AXIS);

    // start button
    start = new JButton("Play");
    start.setActionCommand("play");
    buttonPanel.add(start);

    // pause button
    pause = new JButton("Pause");
    pause.setActionCommand("pause");
    buttonPanel.add(pause);

    // restart button
    restart = new JButton("Restart");
    restart.setActionCommand("restart");
    buttonPanel.add(restart);


    // speed
    speed = new JButton("Change Speed ");
    speed.setActionCommand("speed");
    buttonPanel.add(speed);

    // begin the loop button
    JLabel loop = new JLabel("loop:");
    loopSwitch = new JCheckBox();
    loopSwitch.setSelected(false);
    loopSwitch.setActionCommand("loop");
    JPanel loopPanel = new JPanel();
    loopPanel.add(loop);
    loopPanel.add(loopSwitch);
    buttonPanel.add(loopPanel);


    buttonPanel.setSize(new Dimension(100,800));
    this.add(buttonPanel, BorderLayout.EAST);

  }

  @Override
  public void setListener(ActionListener event) {
    start.addActionListener(event);
    pause.addActionListener(event);
    restart.addActionListener(event);
    speed.addActionListener(event);
    loopSwitch.addActionListener(event);
  }



  @Override
  public void loop(int finalTick){
    if (loopSwitch.isSelected() && panel.getCurrentTick() > finalTick) {
      panel.setTick(0);
    }
  }

  @Override
  public void restart() {
    panel.setTick(0);
  }

}
