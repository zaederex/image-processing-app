package controller.command;

import model.Country;
import model.Image;
import model.ImageGeneratorImpl;

/**
 * This class implements the GeneratorCommand interface and represents the "flag" command. This flag
 * generates the flag associated with a configured country with the specified dimensions.
 */
public class Flag implements GeneratorCommand {

  private final int height;
  private final Country country;

  /**
   * Constructs the command by configuring the expected height of the flag and the corresponding
   * country.
   *
   * @param height  height of the image
   * @param country country of the flag
   */
  public Flag(int height, String country) {
    this.height = height;
    this.country = Country.valueOf(country.toUpperCase());
  }

  @Override
  public Image execute() {
    return new ImageGeneratorImpl().generateFlag(height, country);
  }


  @Override
  public String toString() {
    return "flag";
  }
}