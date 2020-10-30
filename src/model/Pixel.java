package model;

/**
 * This interface defines all the operations that an image pixel should provide. These operations
 * involve methods that provide description about the pixel.
 */
public interface Pixel {
  /**
   * Return an object of Type Color associated with the pixel.
   *
   * @return color associated with the pixel
   */
  Color getColor();

  /**
   * Return the integer that represents the color by combining the RGB values associated with the
   * pixel.
   *
   * @return rgb value
   */
  int getRGB();

  /**
   * Return the row in which the pixel is stored in the 2D matrix representation of the image.
   *
   * @return row in which the pixel is stored
   */
  int getRow();

  /**
   * Return the column in which the pixel is stored in the 2D matrix representation of the image.
   *
   * @return row in which the pixel is stored
   */
  int getColumn();
}