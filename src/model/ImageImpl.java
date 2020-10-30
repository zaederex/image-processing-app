package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents an image. The image is represented using a two dimensional matrix of
 * pixels. The dimensions of the matrix describe the aspect ration of the image. It implements the
 * methods part of the Image interface.
 */
public class ImageImpl implements Image {
  private final int width;
  private final int height;

  private final Pixel[][] pixels;

  /**
   * Construct the image with the specified parameters.
   *
   * @param pixels pixels that constitute image
   */
  public ImageImpl(Pixel[][] pixels) {
    this.width = pixels.length;
    this.height = pixels[0].length;
    this.pixels = pixels;
  }

  @Override
  public int[] getPixelArray() {
    List<Pixel> pixelList = new ArrayList<>();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        pixelList.add(pixels[j][i]);
      }
    }
    List<Integer> rgbList = pixelList.stream().map(s -> s.getRGB()).collect(Collectors.toList());
    return rgbList.stream().mapToInt(i -> i).toArray();
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Pixel[][] getPixels() {
    Pixel[][] returnPixels = new PixelImpl[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        returnPixels[i][j] = pixels[i][j];
      }
    }
    return returnPixels;
  }

  @Override
  public Image transform(ImageTransformer transformer) {
    return transformer.applyOn(this);
  }

  /**
   * Returns the representation of the image as a 2D matrix of its pixels.
   *
   * @return 2D matrix representation of pixels stored within the image
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        builder.append(pixels[i][j]).append(" ");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}