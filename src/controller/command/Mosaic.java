package controller.command;

import model.Image;
import model.ImageTransformerFactory;

/**
 * This class implements the TransformerCommand interface and represents the "mosaic" command. The
 * command execution results in an image consisting of patterns resembling an arrangement of small
 * colored pieces of hard material, such as stone, tile, or glass.
 */
public class Mosaic implements TransformerCommand {
  private final int noOfSeeds;

  /**
   * Creates the command by configuring the number of seeds provided considered while transforming
   * the image to its mosaic form.
   *
   * @param noOfSeeds number of seeds selected for the mosaic transformer
   */
  public Mosaic(int noOfSeeds) {
    this.noOfSeeds = noOfSeeds;
  }

  @Override
  public Image execute(Image image) {
    return image.transform(ImageTransformerFactory.createMosaicTransformer(noOfSeeds));
  }

  @Override
  public String toString() {
    return "mosaic";
  }
}