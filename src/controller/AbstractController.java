package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import model.Color;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

/**
 * This class represents the abstraction of the controllers for the image processing application. It
 * abstracts out the methods common to all the different controllers.
 */
public abstract class AbstractController implements ImageProcessingController {

  @Override
  public Image loadImage(String pathName) throws IOException {
    BufferedImage image = ImageIO.read(new File(pathName));
    int width = image.getWidth();
    int height = image.getHeight();
    Pixel[][] pixels = new PixelImpl[width][height];
    boolean hasAlpha = image.getColorModel().hasAlpha();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int rgb = image.getRGB(i, j);
        int red = (rgb >> 16) & 0xff;
        int green = (rgb >> 8) & 0xff;
        int blue = (rgb) & 0xff;
        Color color;
        if (hasAlpha) {
          int alpha = (rgb >> 24) & 0xff;
          color = Color.getBuilder().red(red).green(green).blue(blue).transparency(alpha).build();
        } else {
          color = Color.getBuilder().red(red).green(green).blue(blue).build();
        }
        Pixel pixel = new PixelImpl(color, j, i);
        pixels[i][j] = pixel;
      }
    }
    return new ImageImpl(pixels);
  }

  @Override
  public void saveImage(Image image, String directory,
                        String filename, String format) throws IOException {
    int[] pixels = image.getPixelArray();

    BufferedImage writeImage = new BufferedImage(image.getWidth(),
            image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
    writeImage.setRGB(0, 0, image.getWidth(), image.getHeight(),
            pixels, 0, image.getWidth());
    if (!((directory.endsWith("/") && (directory.endsWith("\\"))))) {
      directory = directory.concat("/");
    }
    this.createDirectoryIfNotExists(directory);
    ImageIO.write(writeImage, format, new File(directory.concat(filename)
            .concat(".").concat(format)));
  }

  /**
   * Private helper that creates the output directory if it does not exist.
   *
   * @param directory directory that needs to exist
   */
  private void createDirectoryIfNotExists(String directory) throws IOException {
    Path path = Paths.get(directory);
    if (!Files.exists(path)) {
      Files.createDirectories(path);
    }
  }
}