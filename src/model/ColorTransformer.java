package model;

/**
 * This class represents the different types of color transformers. These involve modification of
 * the color of a pixel based on its own color. Consider a pixel with color (r,g,b). A color
 * transformation results in the new color of this pixel to be (r’,g’,b’) such that each of them are
 * dependent only on the values (r,g,b).
 */
public class ColorTransformer extends AbstractTransformer {
  /**
   * Constructs the filter with the specified kernel.
   *
   * @param kernel kernel associated with the filter
   * @throws IllegalArgumentException when filter is attempted to be created with a kernel of even
   *                                  dimensions
   */
  public ColorTransformer(float[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  @Override
  public Image applyOn(Image image) {
    Pixel[][] pixels = image.getPixels();
    int inputWidth = image.getWidth();
    int inputHeight = image.getHeight();

    PixelImpl[][] filteredImage = new PixelImpl[inputWidth][inputHeight];
    for (int i = 0; i < inputWidth; i++) {
      for (int j = 0; j < inputHeight; j++) {
        Pixel currentPixel = pixels[i][j];
        int[][] rgbArray = new int[3][1];
        rgbArray[0][0] = currentPixel.getColor().getRed();
        rgbArray[1][0] = currentPixel.getColor().getGreen();
        rgbArray[2][0] = currentPixel.getColor().getBlue();
        int[][] newRgbArray = new int[3][1];
        for (int k = 0; k < kernel.length; k++) {
          for (int l = 0; l < rgbArray[0].length; l++) {
            int sum = 0;
            for (int m = 0; m < rgbArray.length; m++) {
              sum += kernel[k][m] * rgbArray[m][l];
            }
            newRgbArray[k][l] = sum;
          }
        }
        filteredImage[i][j] = new PixelImpl(Color.getBuilder()
                .red(Math.max((Math.min(newRgbArray[0][0], 255)), 0))
                .green(Math.max((Math.min(newRgbArray[1][0], 255)), 0))
                .blue(Math.max((Math.min(newRgbArray[2][0], 255)), 0))
                .transparency(currentPixel.getColor().getAlpha())
                .build(), i, j);
      }
    }
    return new ImageImpl(filteredImage);
  }
}