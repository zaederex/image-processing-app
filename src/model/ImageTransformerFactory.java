package model;

/**
 * This class consists of various factory methods that return different types of image transformer
 * objects that can be applied on an image(either color transformers or filters) namely Sepia
 * transformer, Greyscale transformer, Blur Filter, Sharpen filter, Dither transformer and Mosaic
 * transformer.
 */
public class ImageTransformerFactory {

  /**
   * Returns a sepia transformer. Applying this transformation on an image causes the image to have
   * a characteristic reddish brown tone.
   *
   * @return sepia filter
   */
  public static ImageTransformer createSepiaFilter() {
    return new ColorTransformer(new float[][]{
            {0.393f, 0.769f, 0.189f},
            {0.349f, 0.686f, 0.168f},
            {0.272f, 0.534f, 0.131f}
    });
  }

  /**
   * Returns a greyscale transformer. Applying this transformation on an image causes the image to
   * be composed of grey shades.
   *
   * @return greyscale filter
   */
  public static ImageTransformer createGreyScaleFilter() {
    return new ColorTransformer(new float[][]{
            {0.2126f, 0.7152f, 0.0722f},
            {0.2126f, 0.7152f, 0.0722f},
            {0.2126f, 0.7152f, 0.0722f}
    });
  }

  /**
   * Returns a blur filter. Applying this filter on an image causes the image to have pixels that
   * are less distinct (less clear).
   *
   * @return greyscale filter
   */
  public static ImageTransformer createBlurFilter() {
    return new ConvolutionFilter(new float[][]{
            {0.0625f, 0.125f, 0.0625f},
            {0.125f, 0.25f, 0.125f},
            {0.0625f, 0.125f, 0.0625f}
    });
  }

  /**
   * Returns a sharpen filter. Applying this filter on an image causes the image to have accentuated
   * edges which makes it look "sharper".
   *
   * @return sharpen filter
   */
  public static ImageTransformer createSharpenFilter() {
    return new ConvolutionFilter(new float[][]{
            {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f},
            {-0.125f, 0.25f, 0.25f, 0.25f, -0.125f},
            {-0.125f, 0.25f, 1, 0.25f, -0.125f},
            {-0.125f, 0.25f, 0.25f, 0.25f, -0.125f},
            {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f}
    });
  }

  /**
   * Returns a dither transformer.
   *
   * @return dither transformer
   */
  public static ImageTransformer createDitherTransformer() {
    return new IndexedColorTransformer();
  }

  /**
   * Return a mosaic transformer with the given no of seeds.
   *
   * @param noOfSeeds number of seeds considered while applying this mosaic transformer
   * @return mosaic transformer
   */
  public static ImageTransformer createMosaicTransformer(int noOfSeeds) {
    return new MosaicTransformer(noOfSeeds);
  }
}