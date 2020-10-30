package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * This class represent a transformer that converts the image into its mosaic form. The transformer
 * is associated with a number of seeds that determine the number of clusters. ALl pixels get
 * assigned to a cluster based on the distance from the seed and are assigned color that is the
 * average of all the colors belonging to that cluster (each channel is average to determine the
 * average color of the pixel).
 */
public class MosaicTransformer implements ImageTransformer {
  private List<PixelClusterImpl> clusterList;
  private int noOfSeeds;

  /**
   * Construct the mosaic transformer object.
   *
   * @param noOfSeeds number of seeds selected for the mosaic transformer
   */
  public MosaicTransformer(int noOfSeeds) {
    this.clusterList = new ArrayList<>();
    this.noOfSeeds = noOfSeeds;
  }

  @Override
  public Image applyOn(Image image) {
    Pixel[][] pixels = image.getPixels();

    int inputWidth = image.getWidth();
    int inputHeight = image.getHeight();

    Set<Pixel> seeds = new HashSet<>();
    Random random = new Random();
    // seeds selection
    while (seeds.size() != noOfSeeds) {
      int randomY = random.nextInt(inputHeight - 1);
      int randomX = random.nextInt(inputWidth - 1);
      seeds.add(pixels[randomX][randomY]);
    }
    for (Pixel pixel : seeds) {
      clusterList.add(new PixelClusterImpl(pixel));
    }

    assignPixelsToClusters(inputHeight, inputWidth, pixels);

    Pixel[][] imagePixels = new Pixel[inputWidth][inputHeight];
    for (PixelClusterImpl cluster : clusterList) {
      cluster.assignAverageClusterColor();
      for (Pixel pixel : cluster.getAllPixels()) {
        imagePixels[pixel.getColumn()][pixel.getRow()] = pixel;
      }
    }
    return new ImageImpl(imagePixels);
  }

  /**
   * Private method that performs cluster calculations corresponding to each pixel in the image.
   *
   * @param inputHeight height of the image
   * @param inputWidth  width of the image
   * @param pixels      2-D Pixel array containing the pixels
   */
  private void assignPixelsToClusters(int inputHeight, int inputWidth, Pixel[][] pixels) {
    for (int i = 0; i < inputHeight; i++) {
      for (int j = 0; j < inputWidth; j++) {
        Pixel currentPixel = pixels[j][i];
        Double bestDistance = Double.POSITIVE_INFINITY;
        int bestIndex = -1;
        for (int k = 0; k < clusterList.size(); k++) {
          Double tentativeDistance = clusterList.get(k).computeDistanceFromSeed(currentPixel,
              (pixel1, pixel2) -> Math.sqrt(Math.pow(pixel1.getColumn() - pixel2.getColumn(), 2)
                      + Math.pow(pixel1.getRow() - pixel2.getRow(), 2)));
          if (tentativeDistance == 0) {
            bestDistance = tentativeDistance;
            break;
          }
          if (tentativeDistance < bestDistance) {
            bestDistance = tentativeDistance;
            bestIndex = k;
          }
        }
        if (bestDistance > 0) {
          clusterList.get(bestIndex).addToCluster(currentPixel);
        }
      }
    }
  }
}