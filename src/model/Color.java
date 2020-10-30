package model;

/**
 * This class represents the color of a pixel in an image. Each color consist of 4 channels: alpha
 * (associated with the transparency of the pixel), red, green and blue.
 */
public class Color {
  private int alpha;
  private int red;
  private int green;
  private int blue;

  /**
   * Return the builder object for this color.
   *
   * @return builder
   */
  public static ColorBuilder getBuilder() {
    return new ColorBuilder();
  }

  /**
   * This class contains methods to create an object of the class Color.
   */
  public static class ColorBuilder {
    private int alpha;
    private int red;
    private int green;
    private int blue;

    /**
     * Set the default values for each channel.
     */
    private ColorBuilder() {
      this.alpha = 255;
      this.red = 255;
      this.green = 255;
      this.blue = 255;
    }

    /**
     * Return the builder object after setting the transparency channel value.
     *
     * @param alpha transparency of the pixel
     * @return ColorBuilder object
     * @throws IllegalArgumentException when passed transparency value doesn't lie within the range
     *                                  0-255
     */
    public ColorBuilder transparency(int alpha) throws IllegalArgumentException {
      if (alpha < 0 || alpha > 255) {
        throw new IllegalArgumentException("Alpha channel value cannot "
                + "be negative or greater than 255");
      }
      this.alpha = alpha;
      return this;
    }

    /**
     * Return the builder object after setting the red channel value.
     *
     * @param red value associated with red channel of the pixel
     * @return resultant builder
     * @throws IllegalArgumentException when passed red value doesn't lie within the rand 0-255
     */
    public ColorBuilder red(int red) {
      if (red < 0 || red > 255) {
        throw new IllegalArgumentException("Red channel value cannot "
                + "be negative or greater than 255");
      }
      this.red = red;
      return this;
    }

    /**
     * Return the builder object after setting the green channel value.
     *
     * @param green value associated with red channel of the pixel
     * @return resultant ColorBuilder object
     * @throws IllegalArgumentException when passed green value doesn't lie within the range 0-255
     */
    public ColorBuilder green(int green) {
      if (green < 0 || green > 255) {
        throw new IllegalArgumentException("Green channel value cannot "
                + "be negative or greater than 255");
      }
      this.green = green;
      return this;
    }

    /**
     * Return the builder object after setting the blue channel value.
     *
     * @param blue value associated with red channel of the pixel
     * @return resultant ColorBuilder object
     * @throws IllegalArgumentException when passed blue value doesn't lie within the rand 0-255
     */
    public ColorBuilder blue(int blue) {
      if (blue < 0 || blue > 255) {
        throw new IllegalArgumentException("Blue channel value cannot "
                + "be negative or greater than 255");
      }
      this.blue = blue;
      return this;
    }

    /**
     * Build the Color object with the values set currently for the alpha, red, green and blue
     * channels.
     *
     * @return color
     */
    public Color build() {
      Color color = new Color();
      color.alpha = this.alpha;
      color.red = this.red;
      color.green = this.green;
      color.blue = this.blue;
      return color;
    }
  }

  /**
   * Private default constructor to construct the color.
   */
  private Color() {
    this.alpha = 255;
    this.red = 255;
    this.green = 255;
    this.blue = 255;
  }

  /**
   * Returns the value associated with the red channel in the color.
   *
   * @return value of red channel
   */
  public int getRed() {
    return red;
  }

  /**
   * Returns the value associated with the green channel in the color.
   *
   * @return value of green channel
   */
  public int getGreen() {
    return green;
  }

  /**
   * Returns the value associated with the blue channel in the color.
   *
   * @return value of blue channel
   */
  public int getBlue() {
    return blue;
  }

  /**
   * Returns the value associated with the transparency channel in the color.
   *
   * @return value of transparency channel
   */
  public int getAlpha() {
    return alpha;
  }

  /**
   * Returns the color in the string form of "Alpha,Red,Green,Blue".
   *
   * @return string representing the color
   */
  @Override
  public String toString() {
    return alpha + "," + red + "," + green + "," + blue;
  }
}