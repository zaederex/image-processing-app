package controller.command;

import model.Image;
import model.ImageTransformerFactory;

/**
 * This class implements the TransformerCommand interface and represents the "blur" command. The
 * blur command obscures the image i.e. it makes the image less clear.
 */
public class Blur implements TransformerCommand {

  @Override
  public Image execute(Image image) {
    return image.transform(ImageTransformerFactory.createBlurFilter());
  }

  @Override
  public String toString() {
    return "blur";
  }
}
