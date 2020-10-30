package model;

/**
 * This interface represents all operations that an Image transformer supports. The behavior of this
 * method on an image varies depending upon the type of transformer. There are 2 basic types of
 * transformers.
 * <table summary="Types of image transformers">
 *   <tr>
 *     <td>Convolution filters</td> <td>These consist of simple 3x3 or 5x5 matrix convolution
 *     filters. These filters are applied by replacing each pixel intensity by a weighted average
 *     of its neighbouring pixels. The weights that are applied to the neighbouring pixel
 *     intensities are contained in a matrix called the convolution matrix</td>
 *   </tr>
 *   <tr>
 *     <td>Color transformers</td> <td>These involve modification of the color of a
 *     pixel based on its own color. Consider a pixel with color (r,g,b). A color transformation
 *     results in the new color of this pixel to be (r’,g’,b’) such that each of them are dependent
 *     only on the values (r,g,b)</td>
 *   </tr>
 * </table>
 */
public interface ImageTransformer {
  /**
   * Apply transformation on the image and return a new image that is the result of the
   * transformation operation.
   *
   * @param image image on which the filter is supposed to be applied
   * @return resultant image obtained by applying the filter
   */
  Image applyOn(Image image);
}