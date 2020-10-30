import org.junit.Test;

import model.ImageTransformer;
import model.ImageTransformerFactory;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests associated with the ImageTransformerFactory class.
 */
public class ImageTransformerFactoryTest {

  private ImageTransformer transformer;

  /**
   * Tests the creation of the Sepia filter.
   */
  @Test
  public void testCreateSepiaFilter() {
    transformer = ImageTransformerFactory.createSepiaFilter();
    assertEquals("0.393 0.769 0.189 \n"
            + "0.349 0.686 0.168 \n"
            + "0.272 0.534 0.131 \n", transformer.toString());
  }

  /**
   * Tests the creation of the GreyScale filter.
   */
  @Test
  public void testCreateGreyScaleFilter() {
    transformer = ImageTransformerFactory.createGreyScaleFilter();
    assertEquals("0.2126 0.7152 0.0722 \n"
            + "0.2126 0.7152 0.0722 \n"
            + "0.2126 0.7152 0.0722 \n", transformer.toString());

  }


  /**
   * Tests the creation of the Blur filter.
   */
  @Test
  public void testCreateBlurFilter() {
    transformer = ImageTransformerFactory.createBlurFilter();
    assertEquals("0.0625 0.125 0.0625 \n"
            + "0.125 0.25 0.125 \n"
            + "0.0625 0.125 0.0625 \n", transformer.toString());
  }


  /**
   * Tests the creation of the Sharpen filter.
   */
  @Test
  public void testSharpenFilter() {
    transformer = ImageTransformerFactory.createSharpenFilter();
    assertEquals("-0.125 -0.125 -0.125 -0.125 -0.125 \n"
            + "-0.125 0.25 0.25 0.25 -0.125 \n"
            + "-0.125 0.25 1.0 0.25 -0.125 \n"
            + "-0.125 0.25 0.25 0.25 -0.125 \n"
            + "-0.125 -0.125 -0.125 -0.125 -0.125 \n", transformer.toString());
  }
}