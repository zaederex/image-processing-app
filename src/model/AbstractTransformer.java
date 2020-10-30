package model;

/**
 * This class represents the abstraction of different types of filters. A filter is normally
 * associated with a two dimensional kernel, represented as a 2D matrix.
 */
public abstract class AbstractTransformer implements ImageTransformer {

  protected final float[][] kernel;

  /**
   * Constructs the transformer with the specified kernel.
   *
   * @param kernel kernel associated with the transformer
   * @throws IllegalArgumentException when a filter is attempted to be created with a kernel of even
   *                                  dimensions
   */
  protected AbstractTransformer(float[][] kernel) throws IllegalArgumentException {
    if (kernel.length % 2 == 0) {
      throw new IllegalArgumentException("Kernels can have only odd dimensions");
    }
    this.kernel = kernel;
  }

  /**
   * Prints the kernel of the transformer.
   *
   * @return kernel representation of the transformer
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        builder.append(kernel[i][j]).append(" ");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}