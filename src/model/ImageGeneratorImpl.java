package model;

/**
 * This class is responsible for performing all operations required to generate various images
 * including checkerboard, rainbows and flags of certain countries.
 */
public class ImageGeneratorImpl implements ImageGenerator {

  @Override
  public Image generateCheckerboard(int side) {
    if (side < 1) {
      throw new IllegalArgumentException("Dimensions must be positive values.");
    }
    int checkerBoardWidth = 8 * side;
    int checkerBoardHeight = 8 * side;
    int switcher;
    int red;
    int green;
    int blue;
    Pixel[][] checkerboard = new Pixel[checkerBoardWidth][checkerBoardHeight];
    for (int i = 0; i < checkerBoardWidth; i++) {
      if ((i / side) % 2 == 0) {
        switcher = 0;
      } else {
        switcher = 1;
      }
      for (int j = 0; j < checkerBoardHeight; j++) {
        if (switcher == 0) {
          if (j % side == 0 && j != 0) {
            switcher = 1;
            red = 0;
            green = 0;
            blue = 0;
          } else {
            red = 255;
            green = 255;
            blue = 255;
          }
          Pixel pixel = new PixelImpl(createColorBuilder(red, green, blue), j, i);
          checkerboard[i][j] = pixel;
        } else {
          if (j % side == 0 && j != 0) {
            switcher = 0;
            red = 255;
            green = 255;
            blue = 255;
          } else {
            red = 0;
            green = 0;
            blue = 0;
          }
          Pixel pixel = new PixelImpl(createColorBuilder(red, green, blue), j, i);
          checkerboard[i][j] = pixel;
        }
      }
    }
    return new ImageImpl(checkerboard);
  }

  @Override
  public Image generateRainbow(int stripeDimension, int imageDimension, boolean horizontal) {
    if (stripeDimension < 1 || imageDimension < 1) {
      throw new IllegalArgumentException("Dimensions must be positive values.");
    }
    int red;
    int green;
    int blue;
    int updatedWidth;
    int updatedHeight;
    int currentIndex;
    Pixel[][] rainbow;

    updatedWidth = 7 * stripeDimension;
    updatedHeight = imageDimension;

    if (!horizontal) {
      rainbow = new Pixel[updatedWidth][updatedHeight];
    } else {
      rainbow = new Pixel[updatedHeight][updatedWidth];
    }

    for (int i = 0; i < updatedWidth; i++) {
      currentIndex = (i / (stripeDimension));
      for (int j = 0; j < updatedHeight; j++) {
        switch (currentIndex) {
          case 0:
            red = 148;
            green = 0;
            blue = 211;
            break;
          case 1:
            red = 75;
            green = 0;
            blue = 130;
            break;
          case 2:
            red = 0;
            green = 0;
            blue = 255;
            break;
          case 3:
            red = 0;
            green = 255;
            blue = 0;
            break;
          case 4:
            red = 255;
            green = 255;
            blue = 0;
            break;
          case 5:
            red = 255;
            green = 127;
            blue = 0;
            break;
          case 6:
            red = 255;
            green = 0;
            blue = 0;
            break;
          default:
            red = 255;
            green = 255;
            blue = 255;
            break;
        }
        Pixel pixel = new PixelImpl(createColorBuilder(red, green, blue), j, i);
        if (horizontal) {
          rainbow[j][i] = pixel;
        } else {
          rainbow[i][j] = pixel;
        }
      }
    }
    return new ImageImpl(rainbow);
  }


  @Override
  public Image generateFlag(int height, Country country) throws IllegalArgumentException {
    if (height < 1) {
      throw new IllegalArgumentException("Dimensions must be positive values.");
    }
    Image countryFlag;
    switch (country) {
      case FRANCE:
        countryFlag = generateFrenchFlag(height);
        break;
      case SWITZERLAND:
        countryFlag = generateSwissFlag(height);
        break;
      case GREECE:
        countryFlag = generateGreekFlag(height);
        break;
      default:
        throw new IllegalArgumentException("Generation of flag for the specified country is not"
                + " yet supported.");
    }
    return countryFlag;
  }

  /**
   * Generates the flag of Greece.
   *
   * @param height height of the flag
   * @return image of the Greek Flag
   */
  private Image generateGreekFlag(int height) {
    int red;
    int green;
    int blue;
    int prescribedHeight = 18;
    if (height < prescribedHeight) {
      height = prescribedHeight;
    } else if (height % prescribedHeight > 9) {
      height = height + (prescribedHeight - (height % prescribedHeight));
    } else {
      height = height - ((height % prescribedHeight));
    }
    int width = (int) (1.5 * height);
    Pixel[][] greekFlag = new Pixel[width][height];
    int leftSquareEndHeight = (height * 10) / 18;
    int leftSquareEndWidth = (width * 10) / 27;
    int leftCrossStart = (width * 4) / 27;
    int leftCrossStop = (width * 6) / 27;
    int currentIndex;
    int lineSize = (height / 9);

    //This is for the non-cross patterns
    for (int i = 0; i < height; i++) {
      currentIndex = (i / lineSize) % 2;
      for (int j = 0; j < width; j++) {
        if (currentIndex == 0) {
          red = 13;
          green = 94;
          blue = 175;
        } else {
          red = 255;
          green = 255;
          blue = 255;
        }
        if (i < leftSquareEndWidth && j < leftSquareEndHeight) {
          red = 13;
          green = 94;
          blue = 175;
        }
        Pixel pixel = new PixelImpl(createColorBuilder(red, green, blue), i, j);
        greekFlag[j][i] = pixel;
      }
    }
    //This is for the cross pattern
    for (int i = 0; i < leftSquareEndWidth; i++) {
      for (int j = 0; j < leftSquareEndHeight; j++) {
        if (j >= leftCrossStart && j < leftCrossStop) {
          red = 255;
          green = 255;
          blue = 255;
        } else if (i >= leftCrossStart && i < leftCrossStop) {
          red = 255;
          green = 255;
          blue = 255;
        } else {
          red = 13;
          green = 94;
          blue = 175;
        }
        Pixel pixel = new PixelImpl(createColorBuilder(red, green, blue), j, i);
        greekFlag[i][j] = pixel;
      }
    }
    return new ImageImpl(greekFlag);
  }

  /**
   * Generates the flag of Switzerland.
   *
   * @param height height of the flag
   * @return image of the Swiss Flag
   */
  private Image generateSwissFlag(int height) {
    int red;
    int green;
    int blue;
    int prescribedHeight = 32;
    if (height < prescribedHeight) {
      height = prescribedHeight;
    } else if (height % prescribedHeight > 16) {
      height = height + (prescribedHeight - (height % prescribedHeight));
    } else {
      height = height - ((height % prescribedHeight));
    }
    int width = height;
    Pixel[][] swissFlag = new Pixel[width][height];
    int leftCross = (width * 6) / 32;
    int leftCrossStart = (width * 13) / 32;
    int leftCrossStop = (width * 19) / 32;
    int rightCross = width - leftCross;

    //This is for the background
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        red = 255;
        green = 0;
        blue = 0;
        Pixel pixel = new PixelImpl(createColorBuilder(red, green, blue), j, i);
        swissFlag[i][j] = pixel;
      }
    }
    //This is for the cross
    for (int i = leftCross; i < rightCross; i++) {
      for (int j = leftCross; j < rightCross; j++) {
        if ((i >= leftCrossStart && i < leftCrossStop)) {
          red = 255;
          green = 255;
          blue = 255;
        } else if ((j >= leftCrossStart && j < leftCrossStop)) {
          red = 255;
          green = 255;
          blue = 255;
        } else {
          red = 255;
          green = 0;
          blue = 0;
        }
        Pixel pixel = new PixelImpl(createColorBuilder(red, green, blue), j, i);
        swissFlag[i][j] = pixel;
      }
    }
    return new ImageImpl(swissFlag);
  }

  /**
   * Generates the flag of France.
   *
   * @param height height of the flag
   * @return image of the French Flag
   */
  private Image generateFrenchFlag(int height) {
    int red;
    int green;
    int blue;
    int width = (int) (height * 1.5);
    if (width < 3) {
      width = 3;
    } else if (width % 3 > 1) {
      width = width + (width % 3);
    } else {
      width = width - (width % 3);
    }
    height = (int) (width / 1.5);
    Pixel[][] frenchFlag = new Pixel[width][height];
    int currentIndex;
    int lineSize = width / 3;
    for (int i = 0; i < width; i++) {
      currentIndex = (i / (lineSize));
      for (int j = 0; j < height; j++) {
        switch (currentIndex) {
          case 0:
            red = 0;
            green = 85;
            blue = 164;
            break;
          case 2:
            red = 239;
            green = 65;
            blue = 53;
            break;
          default:
            red = 255;
            green = 255;
            blue = 255;
            break;
        }
        Pixel pixel = new PixelImpl(createColorBuilder(red, green, blue), j, i);
        frenchFlag[i][j] = pixel;
      }
    }
    return new ImageImpl(frenchFlag);
  }

  /**
   * Creates the color model based on the RGB values of each pixel using the ColorBuilder.
   *
   * @param red   red channel of the pixel
   * @param green green channel of the pixel
   * @param blue  blue channel of the pixel
   * @return a color object
   */
  private Color createColorBuilder(int red, int green, int blue) {
    return Color.getBuilder().red(red).green(green).blue(blue).build();
  }
}