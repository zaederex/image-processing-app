package model;

import java.awt.image.BufferedImage;

/**
 * This class implements the method defined by the ExtendedImage interface. It provides an
 * implementation for returning the BufferedImage object for the Image object.
 */
public class ExtendedImageImpl implements ExtendedImage {

  private Image image;

  /**
   * Set the instance variable image to the one passed to the construction as an argument.
   *
   * @param image Image object
   */
  public ExtendedImageImpl(Image image) {
    this.image = image;
  }

  @Override
  public BufferedImage getAsBufferedImage() {
    int[] pixelArray = image.getPixelArray();
    BufferedImage buffImage = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_3BYTE_BGR);
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        buffImage.setRGB(i, j, pixelArray[j * image.getWidth() + i]);
      }
    }
    return buffImage;
  }

  @Override
  public int[] getPixelArray() {
    return image.getPixelArray();
  }

  @Override
  public int getWidth() {
    return image.getWidth();
  }

  @Override
  public int getHeight() {
    return image.getHeight();
  }

  @Override
  public Pixel[][] getPixels() {
    return image.getPixels();
  }

  @Override
  public Image transform(ImageTransformer transformer) {
    return image.transform(transformer);
  }
}