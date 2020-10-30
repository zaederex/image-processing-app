package view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class represents a custom file chooser for the Image processing application.
 */
public class ImageChooser extends JFileChooser {
  /**
   * Construct the file chooser with the label for the approve button set to the value passed as the
   * argument.
   *
   * @param buttonLabel desired label of the approve button
   */
  public ImageChooser(String buttonLabel) {
    super();
    this.setApproveButtonText(buttonLabel);
    File workingDirectory = new File(System.getProperty("user.dir"));
    this.setCurrentDirectory(workingDirectory);
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image files", "jpg", "png", "bmp");
    this.setFileFilter(filter);
  }
}