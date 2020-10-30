package model;

/**
 * This interface represents all the operations that an image supports. These methods are related to
 * providing information about the image like the pixels stored within the image, the image
 * dimensions, etc as well as a method to apply modifications on the image (filtering, color
 * transformations, etc).
 */
public interface Image {
  /**
   * Returns all the pixels in the image as a 1 dimensional array.
   *
   * @return array consisting of pixels stored within the image
   */
  int[] getPixelArray();

  /**
   * Returns the width of the image. The width represents the number of pixels stored in each row of
   * the image matrix.
   *
   * @return width of the image
   */
  int getWidth();

  /**
   * Returns the height of the image. The height represents the number of pixels stored in each
   * column of the image matrix.
   *
   * @return height of the image
   */
  int getHeight();

  /**
   * Returns all the pixels in the image as a 2 dimensional matrix.
   *
   * @return matrix consisting of pixels stored within the image
   */
  Pixel[][] getPixels();

  /**
   * Applies transformation on the image and return a new image that is the result of the
   * transformation. The transformation could be a filtering or color transformation.
   *
   * @param transformer filter which is supposed to be applied on the image
   * @return transformed image
   */
  Image transform(ImageTransformer transformer);
}