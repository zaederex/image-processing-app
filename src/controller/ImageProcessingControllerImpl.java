package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.Blur;
import controller.command.Checkerboard;
import controller.command.Dither;
import controller.command.Flag;
import controller.command.GeneratorCommand;
import controller.command.Greyscale;
import controller.command.Mosaic;
import controller.command.Rainbow;
import controller.command.Sepia;
import controller.command.Sharpen;
import controller.command.TransformerCommand;
import model.Image;

/**
 * This class implements the methods defined in the ImageProcessingController interface. This
 * implementation of the controller deals with a command based input. Based on the command, the
 * controller signals the model to perform the associated task. The model returns the resultant
 * image to the controller which then waits for the next command on the image object.
 */
public class ImageProcessingControllerImpl extends AbstractController {

  private Image image;
  private final Map<String, Function<Scanner, TransformerCommand>> transformCommands;
  private final Map<String, Function<Scanner, GeneratorCommand>> generatorCommands;
  private final FileReader reader;

  /**
   * Construct the controller object. The commands supported by the application are also defined.
   * The commands that are supported are as follows:
   * <ul>
   *   <li>load</li>
   *   <li>save</li>
   *   <li>sepia</li>
   *   <li>greyscale</li>
   *   <li>sharpen</li>
   *   <li>blur</li>
   *   <li>mosaic</li>
   *   <li>dither</li>
   *   <li>checkerboard</li>
   *   <li>rainbow</li>
   *   <li>flag</li>
   * </ul>
   *
   * @param filePath path to the script
   */
  public ImageProcessingControllerImpl(String filePath) throws IllegalArgumentException {
    try {
      this.reader = new FileReader(new File(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("The passed script file does not exist.");
    }
    this.image = null;
    transformCommands = new HashMap<>();
    transformCommands.put("sepia", s -> new Sepia());
    transformCommands.put("greyscale", s -> new Greyscale());
    transformCommands.put("sharpen", s -> new Sharpen());
    transformCommands.put("blur", s -> new Blur());
    transformCommands.put("mosaic", s -> new Mosaic(s.nextInt()));
    transformCommands.put("dither", s -> new Dither());

    generatorCommands = new HashMap<>();
    generatorCommands.put("checkerboard", s -> new Checkerboard(s.nextInt()));
    generatorCommands.put("rainbow", s -> new Rainbow(s.nextInt(), s.nextInt(), s.next()));
    generatorCommands.put("flag", s -> new Flag(s.nextInt(), s.next()));
  }

  @Override
  public void execute() throws IOException {
    Scanner fileScanner = new Scanner(reader);
    while (fileScanner.hasNext()) {
      String commandLine = fileScanner.nextLine();
      TransformerCommand cmd;
      Scanner scanner = new Scanner(commandLine);
      while (scanner.hasNext()) {
        String commandString = scanner.next();
        Function<Scanner, TransformerCommand> command = transformCommands
                .getOrDefault(commandString, null);
        if (command == null) {
          GeneratorCommand genCmd;
          Function<Scanner, GeneratorCommand> generatorCommand = generatorCommands
                  .getOrDefault(commandString, null);
          if (generatorCommand == null) {
            if (commandString.equalsIgnoreCase("load")) {
              image = this.loadImage(scanner.next());
            } else if (commandString.equalsIgnoreCase("save")) {
              if (image == null) {
                throw new IllegalStateException("Attempted to create an image that has not been"
                        + " loaded/created yet.");
              }
              this.saveImage(image, scanner.next(), scanner.next(), scanner.next());
            } else {
              throw new IllegalArgumentException("The passed command is either invalid or not"
                      + " supported.");
            }
          } else {
            if (image == null) {
              throw new IllegalStateException("An image needs to be created or loaded before" +
                      " applying transformations on it.");
            }
            genCmd = generatorCommand.apply(scanner);
            image = genCmd.execute();
          }
        } else {
          cmd = command.apply(scanner);
          image = cmd.execute(image);
        }
      }
    }
  }
}