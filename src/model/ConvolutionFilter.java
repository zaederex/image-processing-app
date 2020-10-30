package model;

/**
 * This class represents the convolution filters. These filters are applied by replacing each pixel
 * intensity by a weighted average of its neighbouring pixels. The weights that are applied to the
 * neighbouring pixel intensities are contained in a matrix called the convolution matrix.
 */
public class ConvolutionFilter extends AbstractTransformer {

  /**
   * Constructs the convolution filter with the specified kernel.
   *
   * @param kernel 2D matrix representing the kernel of this filter
   * @throws IllegalArgumentException when filter is attempted to be created with a kernel of even
   *                                  dimensions
   */
  public ConvolutionFilter(float[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  @Override
  public ImageImpl applyOn(Image image) {
    Pixel[][] pixels = image.getPixels();
    int inputWidth = image.getWidth();
    int inputHeight = image.getHeight();
    int kernelWidth = kernel.length;
    int kernelHeight = kernel[0].length;
    int kernelWidthRadius = kernelWidth >>> 1;
    int kernelHeightRadius = kernelHeight >>> 1;

    Pixel[][] outputImage = new PixelImpl[inputWidth][inputHeight];
    for (int i = inputWidth - 1; i >= 0; i--) {
      for (int j = inputHeight - 1; j >= 0; j--) {
        double newRed = 0.0;
        double newGreen = 0.0;
        double newBlue = 0.0;
        for (int k = kernelWidth - 1; k >= 0; k--) {
          for (int l = kernelHeight - 1; l >= 0; l--) {
            newRed += performChannelComputation(k, l, pixels, i, j, kernelWidthRadius,
                    kernelHeightRadius, inputWidth, inputHeight)[0][0];
            newGreen += performChannelComputation(k, l, pixels, i, j, kernelWidthRadius,
                    kernelHeightRadius, inputWidth, inputHeight)[1][0];
            newBlue += performChannelComputation(k, l, pixels, i, j, kernelWidthRadius,
                    kernelHeightRadius, inputWidth, inputHeight)[2][0];
          }
        }
        outputImage[i][j] = new PixelImpl(Color.getBuilder()
                .red((int) Math.max((Math.min(newRed, 255)), 0))
                .green((int) Math.max((Math.min(newGreen, 255)), 0))
                .blue((int) Math.max((Math.min(newBlue, 255)), 0))
                .transparency(pixels[i][j].getColor().getAlpha())
                .build(), i, j);
      }
    }
    return new ImageImpl(outputImage);
  }

  /**
   * Private helper that returns the appropriate pixel index associated with the neighboring pixels
   * in the image. It handles the scenario of kernel overlaps going beyond the image boundaries.
   *
   * @param value    overlap row or column
   * @param endIndex image width or height
   * @return row or column index of the image
   */
  private int returnBorderingPixelIndex(int value, int endIndex) {
    if (value < 0) {
      return 0;
    }
    if (value < endIndex) {
      return value;
    }
    return endIndex - 1;
  }

  /**
   * Private Helper that returns an rgb array for the appropriate pixel index taking into account
   * the neighboring pixels in the image.
   *
   * @param k                  iterator variable corresponding to the kernel width
   * @param l                  iterator variable corresponding to the kernel height
   * @param pixels             2-D Pixel array containing the pixel information
   * @param i                  iterator variable corresponding to the image width
   * @param j                  iterator variable corresponding to the image height
   * @param kernelWidthRadius  the values of the neighboring kernel values corresponding to its
   *                           width
   * @param kernelHeightRadius the values of the neighboring kernel values corresponding to its
   *                           height
   * @param inputWidth         width of the image
   * @param inputHeight        height of the image
   * @return double 2-D array containing rgb values for that specific pixel
   */
  private double[][] performChannelComputation(int k, int l, Pixel[][] pixels, int i, int j,
                                               int kernelWidthRadius, int kernelHeightRadius,
                                               int inputWidth, int inputHeight) {
    double[][] rgbArray = new double[3][1];
    rgbArray[0][0] = kernel[k][l] * pixels[returnBorderingPixelIndex(i + k - kernelWidthRadius,
            inputWidth)][returnBorderingPixelIndex(j + l - kernelHeightRadius,
            inputHeight)].getColor().getRed();
    rgbArray[1][0] = kernel[k][l] * pixels[returnBorderingPixelIndex(i + k - kernelWidthRadius,
            inputWidth)][returnBorderingPixelIndex(j + l - kernelHeightRadius,
            inputHeight)].getColor().getGreen();
    rgbArray[2][0] = kernel[k][l] * pixels[returnBorderingPixelIndex(i + k - kernelWidthRadius,
            inputWidth)][returnBorderingPixelIndex(j + l - kernelHeightRadius,
            inputHeight)].getColor().getBlue();
    return rgbArray;
  }
}