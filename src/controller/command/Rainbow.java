package controller.command;

import model.Image;
import model.ImageGeneratorImpl;

/**
 * This class implements the GeneratorCommand interface and represents the "rainbow" command. This
 * command generates a rainbow with the specified dimension and orientation.
 */
public class Rainbow implements GeneratorCommand {
  private final int stripeDimension;
  private final int imageDimension;
  private final boolean horizontal;

  /**
   * Creates the rainbow based on the user defined strip dimension, image dimension and orientation
   * in which the rainbow needs to be created.
   *
   * @param stripeDimension width of the stripe in case of vertical striped rainbow, height of the
   *                        stripe in case of horizontal striped rainbow
   * @param imageDimension  height of the image in case of vertical striped rainbow, width of the
   *                        image in case of vertical striped rainbow
   * @param orientation     set to horizontal to generate horizontal striped rainbow, vertical for a
   *                        vertical striped rainbow
   */
  public Rainbow(int stripeDimension, int imageDimension, String orientation) {
    this.stripeDimension = stripeDimension;
    this.imageDimension = imageDimension;
    this.horizontal = orientation.equalsIgnoreCase("horizontal");
  }

  @Override
  public Image execute() {
    return new ImageGeneratorImpl().generateRainbow(stripeDimension, imageDimension, horizontal);
  }

  @Override
  public String toString() {
    return "rainbow";
  }
}