package cs3500.animator.view;

import java.awt.event.ActionListener;

public interface AnimationVisual extends SimpleAnimationView{

  /**
   * Draw the animation at the given tick.
   *
   * @param tick the current tick.
   */
  void onTick(int tick);

  void loop(int last);

  void restart();

  void setListener(ActionListener a);
}
