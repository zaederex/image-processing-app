package controller.command;

import model.Image;
import model.ImageTransformerFactory;

/**
 * This class implements the TransformerCommand interface and represents the "sharpen" command. The
 * command execution results in an image with accentuated edges.
 */
public class Sharpen implements TransformerCommand {
  @Override
  public Image execute(Image image) {
    return image.transform(ImageTransformerFactory.createSharpenFilter());
  }

  @Override
  public String toString() {
    return "sharpen";
  }
}