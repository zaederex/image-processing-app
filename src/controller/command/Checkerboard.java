package controller.command;

import model.Image;
import model.ImageGeneratorImpl;


/**
 * This class implements the GeneratorCommand interface and represents the "checkerboard" command.
 * This command generates a checkerboard with each square's side equal to the configured dimension.
 */
public class Checkerboard implements GeneratorCommand {
  private final int side;

  /**
   * Construct the checkerboard command by configuring the side of each square in the checkerboard.
   *
   * @param side length of the side of an individual square
   */
  public Checkerboard(int side) {
    this.side = side;
  }

  @Override
  public Image execute() {
    return new ImageGeneratorImpl().generateCheckerboard(side);
  }

  @Override
  public String toString() {
    return "checkerboard";
  }
}