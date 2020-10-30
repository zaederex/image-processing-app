import org.junit.Test;

import model.Color;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the ImageImpl implementation of the Image interface.
 */
public class ImageTest {

  /**
   * Tests various testable methods provided by the Image interface.
   */
  @Test
  public void testImageConstruction() {
    Pixel[][] pixels = new Pixel[3][3];

    pixels[0][0] = new PixelImpl(Color.getBuilder().red(0).green(0).blue(255).build(), 0, 0);
    pixels[0][1] = new PixelImpl(Color.getBuilder().red(10).green(0).blue(255).build(), 0, 1);
    pixels[0][2] = new PixelImpl(Color.getBuilder().red(10).green(10).blue(255).build(), 0, 2);

    pixels[1][0] = new PixelImpl(Color.getBuilder().red(0).green(0).blue(255).build(), 0, 0);
    pixels[1][1] = new PixelImpl(Color.getBuilder().red(10).green(0).blue(255).build(), 0, 1);
    pixels[1][2] = new PixelImpl(Color.getBuilder().red(10).green(10).blue(255).build(), 0, 2);

    pixels[2][0] = new PixelImpl(Color.getBuilder().red(0).green(0).blue(255).build(), 0, 0);
    pixels[2][1] = new PixelImpl(Color.getBuilder().red(10).green(0).blue(255).build(), 0, 1);
    pixels[2][2] = new PixelImpl(Color.getBuilder().red(10).green(10).blue(255).build(), 0, 2);

    Image image = new ImageImpl(pixels);

    assertEquals(3, image.getWidth());
    assertEquals(3, image.getHeight());

    assertEquals("-16776961 -16121601 -16119041 \n"
            + "-16776961 -16121601 -16119041 \n"
            + "-16776961 -16121601 -16119041 \n", image.toString());
  }
}
