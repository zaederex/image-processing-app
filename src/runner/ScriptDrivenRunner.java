package runner;

import java.io.IOException;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;

/**
 * This is the entry point to the Image Processing application. The commands are sent to the
 * controller via a script file that contains all of the commands.
 */
public class ScriptDrivenRunner {
  /**
   * Runs the command driven Image Processing application. Here the readable object is a file reader
   * that is associated with the command script file that is specified as the argument. It reads the
   * script file line by line and passes the line to the controller to perform the operation.
   *
   * @param args command line arguments (args[0] specifies the name of the command script file)
   * @throws IOException              when a problem is encountered while creating the readable
   *                                  object associated with the command script file
   * @throws IllegalArgumentException when no script file or one that does not exist is passed as a
   *                                  command line argument
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException {
    if (args.length != 1) {
      throw new IllegalArgumentException("Need to specify the script file as a command"
              + " line argument");
    }
    ImageProcessingController controller =
            new ImageProcessingControllerImpl(args[0]);
    controller.execute();
  }
}