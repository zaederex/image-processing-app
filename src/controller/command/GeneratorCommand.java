package controller.command;

import model.Image;

/**
 * This interface defines methods that a class representing an image generator command needs to
 * implement. An image generator command takes in no parameters and returns an object of type
 * Image.
 */
public interface GeneratorCommand {

  /**
   * Return the generated image by executing the command.
   *
   * @return generated image
   */
  Image execute();
}