package cs3500.animator.view;


/**
 * Represents the interface for the view of a simple animation.
 */
public interface SimpleAnimationView {
    /**
     * Return the description of the animation as a string. The string is formatted
     * as follows:
     * <pre>
     * Create red rectangle R with corner at (200,200), width 50 and height 100
     * Create blue oval C with center at (500,100), radius 60 and 30
     *
     * From time 1 to 10, R moves from (200,200) to (10,200), stays size 50x100, and stays (255,0,0)
     * From time 50 to 51, R does nothing.
     * </pre>
     *
     * @return the formatted string as above
     */
    String toString();

  }
