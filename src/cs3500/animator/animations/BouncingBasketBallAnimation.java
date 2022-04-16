package cs3500.animator.animations;

/**
 * Creates an animation of a ball bouncing.
 */
public class BouncingBasketBallAnimation {
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();

    //setup
    int end = 500;
    int radius = 50;
    output.append("canvas 500 500\n");
    output.append("oval name ball center-x 100 center-y 100 x-radius ").append(radius).append(" y-radius ").append(radius).append(" color 1 0.5 0 from 1 to ").append(end).append("\n");
    output.append("rectangle name leftwall min-x 0 min-y 0 width 10 height 500 color 0.5 0.5 0.5 from 1 to ").append(end).append("\n");
    output.append("rectangle name rightwall min-x 490 min-y 0 width 10 height 500 color 0.5 0.5 0.5 from 1 to ").append(end).append("\n");
    output.append("rectangle name floor min-x 0 min-y 490 width 500 height 10 color 0.5 0.5 0.5 from 1 to ").append(end).append("\n");

    //animation
    double gravity = 1;
    double xVelocity = 2;
    double yVelocity = 5;
    double xPos = 100;
    double yPos = 100;
    double previousXPos = xPos;
    double previousYPos = yPos;

    int deltaI = 1;
    for(int i = 1; i < end; i+=deltaI) {
      xPos += xVelocity;
      yPos += yVelocity;
      yVelocity += gravity;
      if (xPos <= radius + 10 || xPos >= 500 - radius - 10) {
        xVelocity = -0.9 * xVelocity;
      }
      if (yPos >= 500 - radius - 10) {
        yVelocity = -0.9 * yVelocity;
      }
      output.append("move name ball moveto ").append((int) previousXPos).append(" ").append((int) previousYPos).append(" ").append((int) xPos).append(" ").append((int) yPos).append(" from ").append(i).append(" to ").append(i+deltaI).append("\n");
      previousXPos = xPos;
      previousYPos = yPos;
    }
    return output.toString();
  }
}
