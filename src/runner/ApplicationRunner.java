package runner;

import java.io.IOException;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import controller.InteractiveAppControllerImpl;
import view.ApplicationFrame;

/**
 * This is the entry point to the Image Processing application. To run the application in "script
 * mode", pass the first argument as "-script" followed by a second argument representing the name
 * of the script file that would need to be run. To run the application in "interactive mode", pass
 * the first argument as "-interactive", which opens the Application frame for the user to interact
 * with.
 */

public class ApplicationRunner {
  /**
   * Runs the Image Processing application in either interactive mode or script based mode depending
   * on the user input in the command line.
   *
   * @param args command line arguments (args[0] specifies the mode to run the application in and
   *             args[1] specifies the path of of the command script file)
   * @throws IOException              when a problem is encountered while creating the readable
   *                                  object associated with the command script file
   * @throws IllegalArgumentException when no run mode or no script file or one that does not exist
   *                                  is passed as a command line argument
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException {
    ImageProcessingController controller;
    if (args.length == 0) {
      throw new IllegalArgumentException("Need to specify the the mode to run the application in."
              + " The supported modes are: 1) script 2) interactive");
    }
    if (args[0].equals("-script")) {
      if (args.length == 1) {
        throw new IllegalArgumentException("Need to specify the path of script file to be run.");
      } else {
        controller = new ImageProcessingControllerImpl(args[1]);
        controller.execute();
      }
    } else if (args[0].equals("-interactive")) {
      ApplicationFrame view = new ApplicationFrame();
      controller = new InteractiveAppControllerImpl(view);
      controller.execute();
    } else {
      throw new IllegalArgumentException("Specify either -script or -interactive as the mode.");
    }
  }
}