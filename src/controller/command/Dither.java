package controller.command;

import model.Image;
import model.ImageTransformerFactory;

/**
 * This class implements the TransformerCommand interface and represents the "dither" command.
 * Dither is an intentionally applied form of noise used to randomize quantization error, preventing
 * large-scale patterns such as color banding in images.
 */
public class Dither implements TransformerCommand {
  @Override
  public Image execute(Image image) {
    return image.transform(ImageTransformerFactory.createDitherTransformer());
  }

  @Override
  public String toString() {
    return "dither";
  }
}
