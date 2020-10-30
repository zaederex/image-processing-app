package controller.command;

import model.Image;

/**
 * This interface defines methods that a class representing an Image transformation command needs to
 * implement. An image transformation command takes in an image as the input parameter, modifies the
 * image based on the transformation command and returns the transformed image.
 */
public interface TransformerCommand {

  /**
   * Return the transformed image by executing the command.
   *
   * @return transformed image
   */
  Image execute(Image image);
}