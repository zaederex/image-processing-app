package controller;

import java.io.IOException;

import model.Image;

/**
 * This interface declares all the methods that a controller for the image processing application
 * must provide. The controller is responsible for reading the input, and based on the image
 * returned from the model, render the results on the view.
 */
public interface ImageProcessingController {
  /**
   * Signal the controller to perform its task. Called after the controller is configured.
   *
   * @throws IOException when a problem occurs during the IO operation
   */
  void execute() throws IOException;

  /**
   * Reads an image and returns an object of type Image consisting of data representing the image.
   * The pathName parameter is expected to be fully qualified path or path relative to the current
   * directory. Example: If an image "hello.png" stored in directory "/usr/data", then path should
   * be specified as "/usr/data/hello.png".
   *
   * @param pathName path to the image
   * @return an object of type image
   * @throws IOException when a problem occurs while reading the image file
   */
  Image loadImage(String pathName) throws IOException;

  /**
   * Save the image object as a file at the specified location on drive with the specified name and
   * format.
   *
   * @param image     object of type Image to be stored
   * @param directory path to the destination
   * @param filename  name of the image file (excluding the format)
   * @param format    format of the image
   * @throws IOException when a problem occurs while saving the image
   */
  void saveImage(Image image, String directory, String filename, String format) throws IOException;
}