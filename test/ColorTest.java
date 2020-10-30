import org.junit.Test;

import model.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class contains test for testing the methods exposed by the Color class.
 */
public class ColorTest {

  /**
   * Tests constructor of color using the builder. Also tests values for each channel beyond the
   * range.
   */
  @Test
  public void createColor() {
    Color color = Color.getBuilder().red(25).green(55).blue(155).build();
    assertEquals(25, color.getRed());
    assertEquals(55, color.getGreen());
    assertEquals(155, color.getBlue());
    assertEquals(255, color.getAlpha());

    color = Color.getBuilder().red(12).green(213).blue(109).transparency(55).build();
    assertEquals(12, color.getRed());
    assertEquals(213, color.getGreen());
    assertEquals(109, color.getBlue());
    assertEquals(55, color.getAlpha());

    try {
      Color.getBuilder().red(-123).build();
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Red channel value cannot be negative or greater than 255",
              e.getMessage());
    }
    try {
      Color.getBuilder().red(256).build();
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Red channel value cannot be negative or greater than 255",
              e.getMessage());
    }
    try {
      Color.getBuilder().green(-123).build();
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Green channel value cannot be negative or greater than 255",
              e.getMessage());
    }
    try {
      Color.getBuilder().green(423).build();
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Green channel value cannot be negative or greater than 255",
              e.getMessage());
    }
    try {
      Color.getBuilder().blue(-1).build();
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Blue channel value cannot be negative or greater than 255",
              e.getMessage());
    }
    try {
      Color.getBuilder().blue(4123).build();
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Blue channel value cannot be negative or greater than 255",
              e.getMessage());
    }
    try {
      Color.getBuilder().transparency(-4123).build();
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Alpha channel value cannot be negative or greater than 255",
              e.getMessage());
    }
    try {
      Color.getBuilder().transparency(412).build();
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Alpha channel value cannot be negative or greater than 255",
              e.getMessage());
    }
  }
}