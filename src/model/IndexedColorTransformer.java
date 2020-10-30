package model;

/**
 * This class represent a transformer that converts the image into its dithered form. The operation
 * of breaking down an image that has many colors into an image that is made of dots from just a few
 * colors is known as dithering. It is first converted to greyscale and then the dithering algorithm
 * is applied.
 */
public class IndexedColorTransformer implements ImageTransformer {
  @Override
  public Image applyOn(Image image) {
    Image greyscaleImage = performGreyScaleConversion(image);

    Pixel[][] pixels = greyscaleImage.getPixels();
    int inputWidth = greyscaleImage.getWidth();
    int inputHeight = greyscaleImage.getHeight();
    int red;
    int green;
    int blue;

    PixelImpl[][] filteredImage = new PixelImpl[inputWidth][inputHeight];
    for (int i = 0; i < inputHeight; i++) {
      for (int j = 0; j < inputWidth; j++) {
        Pixel currentPixel = pixels[j][i];
        red = currentPixel.getColor().getRed();
        int newRed = red < 128 ? 0 : 255;
        int redError = red - newRed;
        blue = currentPixel.getColor().getBlue();
        int newBlue = blue < 128 ? 0 : 255;
        int blueError = blue - newBlue;
        green = currentPixel.getColor().getGreen();
        int newGreen = green < 128 ? 0 : 255;
        int greenError = green - newGreen;
        PixelImpl pixel = new PixelImpl(createColorBuilder(newRed, newRed, newRed), i, j);
        filteredImage[j][i] = pixel;

        //Pixel on the right
        if (j < inputWidth - 1) {
          pixels[j + 1][i] = performErrorCalculation(redError, greenError, blueError,
                  pixels[j + 1][i], 7);
        }
        //Pixel on the next-row-left
        if (j > 0 && i < inputHeight - 1) {
          pixels[j - 1][i + 1] = performErrorCalculation(redError, greenError, blueError,
                  pixels[j - 1][i + 1], 3);
        }
        //Pixel below in the next row
        if (i < inputHeight - 1) {
          pixels[j][i + 1] = performErrorCalculation(redError, greenError, blueError,
                  pixels[j][i + 1], 5);
        }
        //Pixel on the next-row-right
        if (j < inputWidth - 1 && i < inputHeight - 1) {
          pixels[j + 1][i + 1] = performErrorCalculation(redError, greenError, blueError,
                  pixels[j + 1][i + 1], 1);
        }
      }
    }
    return new ImageImpl(filteredImage);
  }


  /**
   * Creates the color model based on the RGB values of each pixel using the ColorBuilder.
   *
   * @param red   red channel of the pixel
   * @param green green channel of the pixel
   * @param blue  blue channel of the pixel
   * @return a color object
   */
  private Color createColorBuilder(int red, int green, int blue) {
    return Color.getBuilder().red(red).green(green).blue(blue).build();
  }

  /**
   * Performs the error calculation for the neighboring pixels.
   *
   * @param redError   error between old red color and new red color
   * @param greenError error between old green color and new green color
   * @param blueError  error between old blue color and new blue color
   * @param pixel      pixel object under consideration
   * @param errorNum   the numerator corresponding to the error factor
   * @return Pixel object representing the modified pixel
   */
  private Pixel performErrorCalculation(int redError, int greenError, int blueError, Pixel pixel,
                                        int errorNum) {
    int errorRed;
    int errorGreen;
    int errorBlue;

    errorRed = Math.max(0, Math.min(pixel.getColor().getRed()
            + (int) ((errorNum / 16.0 * redError)), 255));
    errorGreen = Math.max(0,
            Math.min(pixel.getColor().getGreen() + (int) ((errorNum / 16.0 * greenError)),
                    255));
    errorBlue = Math.max(0,
            Math.min(pixel.getColor().getBlue() + (int) ((errorNum / 16.0 * blueError)),
                    255));
    return new PixelImpl(createColorBuilder(errorRed, errorGreen, errorBlue),
            pixel.getRow(), pixel.getColumn());
  }

  /**
   * Private helper converts the provided image into its greyscale equivalent and returns this
   * image.
   *
   * @param image input image needed to be converted to greyscale
   * @return greyscale image
   */
  private Image performGreyScaleConversion(Image image) {
    return image.transform(ImageTransformerFactory.createGreyScaleFilter());
  }
}