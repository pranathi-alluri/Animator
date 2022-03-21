package cs3500.animator.model;

/**
 * Represents a color as its red, green, and blue components as ints between (0, 255).
 */
public class Color {
  //INVARIANT: red is in the range (0,255) inclusive
  private final int red;
  //INVARIANT: green is in the range (0,255) inclusive
  private final int green;
  //INVARIANT: blue is in the range (0,255) inclusive
  private final int blue;

  public Color(int red, int green, int blue) {
    if(red < 0 || red > 255) {
      throw new IllegalArgumentException("Red must be between 0 and 255 inclusive");
    }
    if(green < 0 || green > 255) {
      throw new IllegalArgumentException("Green must be between 0 and 255 inclusive");
    }
    if(blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Blue must be between 0 and 255 inclusive");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Getter method for red variable.
   * @return the amount of red this color has
   */
  public int getRed() {
    return red;
  }

  /**
   * Getter method for green variable.
   * @return the amount of green this color has
   */
  public int getGreen() {
    return green;
  }

  /**
   * Getter method for blue variable.
   * @return the amount of blue this color has
   */
  public int getBlue() {
    return blue;
  }

  @Override
  public boolean equals(Object other) {
    if(other instanceof Color) {
      Color c2 = (Color) other;
      return(red == c2.red && green == c2.green && blue == c2.blue);
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return (int) Math.pow(red, Math.pow(green, blue));
  }

  @Override
  public String toString() {
    return "red: " + red + ", green: " + green + ", blue: " + blue;
  }
}
