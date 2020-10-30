package controller.command;

import model.Image;
import model.ImageTransformerFactory;

/**
 * This class implements the TransformerCommand interface and represents the "sepia" command. The
 * command execution results in an image that has a reddish brown tinge to it.
 */
public class Sepia implements TransformerCommand {
  @Override
  public Image execute(Image image) {
    return image.transform(ImageTransformerFactory.createSepiaFilter());
  }

  @Override
  public String toString() {
    return "sepia";
  }
}