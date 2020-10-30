package model;

import java.awt.image.BufferedImage;

/**
 * This interface extends the Image interface and defines an additional method that allows
 * implementations to return the BufferedImage equivalent of the Image object.
 */
public interface ExtendedImage extends Image {
  /**
   * Return the BufferedImage equivalent of the Image object.
   *
   * @return BufferedImage object
   */
  BufferedImage getAsBufferedImage();
}
