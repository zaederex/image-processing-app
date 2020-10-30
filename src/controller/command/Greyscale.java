package controller.command;

import model.Image;
import model.ImageTransformerFactory;

/**
 * This class implements the TransformerCommand interface and represents the "greyscale" command.
 * The command execution results in an image in which the value of each pixel is a single sample
 * representing only an amount of light, that is, it carries only intensity information.
 */
public class Greyscale implements TransformerCommand {
  @Override
  public Image execute(Image image) {
    return image.transform(ImageTransformerFactory.createGreyScaleFilter());
  }

  @Override
  public String toString() {
    return "greyscale";
  }
}