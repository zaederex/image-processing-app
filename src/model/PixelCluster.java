package model;

import java.util.List;
import java.util.function.BiFunction;

/**
 * This interface defines all methods that an implementation of the cluster of pixels needs to
 * implement. These operations are instrumental in image processing operations that require
 * clustering of pixels.
 */
public interface PixelCluster {

  /**
   * Add a pixel to the cluster.
   *
   * @param pixel pixel to be added to the cluster
   */
  void addToCluster(Pixel pixel);

  /**
   * Compute the distance of a pixel from the seed associated with the cluster.
   *
   * @param pixel            pixel whose distance from seed needs to be determined
   * @param distanceFunction BiFunction representing the distance computation logic
   * @return distance of pixel from the seed
   */
  Double computeDistanceFromSeed(Pixel pixel,
                                 BiFunction<Pixel, Pixel, Double> distanceFunction);

  /**
   * Set the color of all the pixels that are part of the cluster as the average color of all pixels
   * within the cluster. The average is computed for each channel (Red, Green and Blue).
   */
  void assignAverageClusterColor();

  /**
   * Return a list containing all the pixels that are part of the cluster.
   *
   * @return list of pixels part of the cluster
   */
  List<Pixel> getAllPixels();
}