package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * This class implements the methods defined in the PixelCluster interface. Each cluster of pixels
 * consists of one pixel that acts as a seed. Each pixel in the cluster is closest to its seed.
 */
public class PixelClusterImpl implements PixelCluster {

  private List<Pixel> pixelList;
  private final Pixel seed;

  /**
   * Construct the cluster of pixels by assigning the seed and adding the seed to the empty pixel
   * list.
   *
   * @param seed seed associated with the cluster
   */
  public PixelClusterImpl(Pixel seed) {
    this.seed = seed;
    pixelList = new ArrayList<>();
    pixelList.add(seed);
  }

  @Override
  public void addToCluster(Pixel pixel) {
    this.pixelList.add(pixel);
  }

  @Override
  public Double computeDistanceFromSeed(Pixel pixel,
                                        BiFunction<Pixel, Pixel, Double> distanceFunction) {
    return distanceFunction.apply(pixel, seed);
  }

  @Override
  public void assignAverageClusterColor() {
    int size = pixelList.size();
    int sumRed = 0;
    int sumGreen = 0;
    int sumBlue = 0;
    for (Pixel pixel : pixelList) {
      sumRed += pixel.getColor().getRed();
      sumGreen += pixel.getColor().getGreen();
      sumBlue += pixel.getColor().getBlue();
    }
    int avgRed = sumRed / size;
    int avgGreen = sumGreen / size;
    int avgBlue = sumBlue / size;

    List<Pixel> newPixelSet = new ArrayList<>();
    for (Pixel pixel : pixelList) {
      newPixelSet.add(new PixelImpl(Color.getBuilder().red(avgRed)
              .green(avgGreen).blue(avgBlue).build(), pixel.getRow(), pixel.getColumn()));
    }
    this.pixelList = newPixelSet;
  }

  @Override
  public List<Pixel> getAllPixels() {
    return new ArrayList<>(pixelList);
  }
}