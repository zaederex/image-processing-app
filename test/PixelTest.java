import org.junit.Test;

import model.Color;
import model.Pixel;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests to verify the correctness of the methods defined by the Pixel
 * interface.
 */
public class PixelTest {
  /**
   * Tests if the pixel is constructed as expected by testing the various methods exposed by the
   * interface Pixel.
   */
  @Test
  public void testPixelConstruction() {
    Pixel pixel = new PixelImpl(Color.getBuilder().transparency(255).red(255)
            .green(0).blue(0).build(), 0, 0);
    assertEquals(255, pixel.getColor().getAlpha());
    assertEquals(255, pixel.getColor().getRed());
    assertEquals(0, pixel.getColor().getGreen());
    assertEquals(0, pixel.getColor().getBlue());
    assertEquals(0, pixel.getRow());
    assertEquals(0, pixel.getColumn());

    pixel = new PixelImpl(Color.getBuilder().transparency(213).red(131)
            .green(19).blue(29).build(), 75, 86);

    assertEquals(213, pixel.getColor().getAlpha());
    assertEquals(131, pixel.getColor().getRed());
    assertEquals(19, pixel.getColor().getGreen());
    assertEquals(29, pixel.getColor().getBlue());
    assertEquals(75, pixel.getRow());
    assertEquals(86, pixel.getColumn());

    assertEquals(-712830179, pixel.getRGB());

    assertEquals("-712830179", pixel.toString());
  }
}