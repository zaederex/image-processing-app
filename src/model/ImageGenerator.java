package model;

/**
 * This interface defines all the different methods that allow computer generated images to be
 * created. Checkerboards, rainbows and flags are the images that can be generated.
 */
public interface ImageGenerator {

  /**
   * Creates a Checkerboard of size based on the user's input of an individual square.
   *
   * @param side dimension of a single square on the checkerboard (number of pixels along the side)
   * @return the generated checkerboard image
   * @throws IllegalArgumentException if the dimension is not a positive integer
   */
  Image generateCheckerboard(int side) throws IllegalArgumentException;

  /**
   * Creates rainbow of a user specified dimension and orientation.
   *
   * @param stripeDimension width of the stripe in case of vertical striped rainbow, height of the
   *                        stripe in case of horizontal striped rainbow
   * @param imageDimension  height of the image in case of vertical striped rainbow, width of the
   *                        image in case of vertical striped rainbow
   * @param horizontal      set to true to generate horizontal striped rainbow, false for a vertical
   *                        striped rainbow
   * @return the generated rainbow image
   * @throws IllegalArgumentException if any or both dimension is not a positive integer
   */
  Image generateRainbow(int stripeDimension, int imageDimension, boolean horizontal)
          throws IllegalArgumentException;

  /**
   * Creates flags of different countries by incorporating the height passed as a parameter. If the
   * specified height causes the flag to have dimensions that violate the standard proportions
   * defined for that country's flag, it is converted to the closest best fit flag.
   *
   * @param height  height of the flag image
   * @param country an enum associated with the name of the country that the flag belongs to
   * @return the generated flag image for the specified country
   * @throws IllegalArgumentException if the dimension is not a positive integer
   */
  Image generateFlag(int height, Country country)
          throws IllegalArgumentException;
}