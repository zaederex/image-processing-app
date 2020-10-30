package model;

/**
 * This class represents a pixel in an image. A pixel is typically associated with a specific row
 * and column in the image. It implements the operations defined by the Pixel interface that return
 * details about the Pixel like the RGB value or the Color of the pixel.
 */
public class PixelImpl implements Pixel {

  private Color color;
  private int row;
  private int col;

  /**
   * Construct the pixel with the specified parameters.
   *
   * @param color color of the pixel
   * @param row   the row in which the pixel is stored in the 2D matrix representation of the image
   * @param col   the column in which the pixel is stored in the 2D matrix representation of the
   *              image
   */
  public PixelImpl(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  @Override
  public Color getColor() {
    return Color.getBuilder().red(color.getRed()).green(color.getGreen())
            .blue(color.getBlue()).transparency(color.getAlpha()).build();
  }

  @Override
  public int getRGB() {
    return (color.getAlpha() << 24) | (color.getRed() << 16)
            | (color.getGreen() << 8) | color.getBlue();
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getColumn() {
    return col;
  }

  /**
   * Returns a string representation of the pixel consisting of its RGB value.
   *
   * @return string representation of the pixel
   */
  @Override
  public String toString() {
    return String.valueOf(getRGB());
  }
}