package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

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
import model.ExtendedImage;
import model.ExtendedImageImpl;
import view.FlagGenerationPanel;
import view.FlagGenerationPanelImpl;
import view.IView;
import view.ImageChooser;
import view.RainbowGenerationPanel;
import view.RainbowGenerationPanelImpl;
import view.SplashScreen;

/**
 * This class implements the ActionListener and ImageProcessingController interfaces for the
 * interactive image processing application. This implementation of the controller deals with user
 * interactions. Based on the user input received from the view, the controller signals the model to
 * perform the associated task. The model returns the resultant image to the controller which then
 * waits for the next user input.
 */
public class InteractiveAppControllerImpl extends AbstractController implements ActionListener {

  private IView view;
  private ExtendedImage model;
  private Stack<ExtendedImage> undoStack;
  private Stack<ExtendedImage> redoStack;

  /**
   * Construct the controller for the interactive image processing by initializing the model and
   * view. Also sets this controller as the listener of the actions on the view and initializes the
   * undo and redo stacks.
   *
   * @param view view in which the result of model operations is displayed to the user, also the
   *             interface from which user input is received
   */
  public InteractiveAppControllerImpl(IView view) {
    model = null;
    this.view = view;
    this.view.setListeners(this);
    undoStack = new Stack<>();
    redoStack = new Stack<>();
  }

  /**
   * Displays the view to the user once all configuration is complete.
   */
  @Override
  public void execute() {
    this.view.setVisible(true);
  }

  /**
   * Calls the appropriate model method based on the action command associated with the view element
   * the user interacted with.
   *
   * @param e event received from the view for which this controller was configured as a listener
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "undo":
        if (!undoStack.isEmpty()) {
          undo();
        }
        break;
      case "redo":
        if (!redoStack.isEmpty()) {
          redo();
        }
        break;
      case "load":
        load();
        break;
      case "save":
        save();
        break;
      case "checkerboard":
        checkerboardSide();
        break;
      case "flag":
        flags();
        break;
      case "sepia":
        sepia();
        break;
      case "blur":
        blur();
        break;
      case "greyscale":
        greyscale();
        break;
      case "sharpen":
        sharpen();
        break;
      case "mosaic":
        mosaic();
        break;
      case "dither":
        dither();
        break;
      case "rainbow":
        rainbow();
        break;
      default:
        throw new UnsupportedOperationException("Given action command has no"
                + " supported implementations");
    }
  }

  /**
   * Private helper for the redo command.
   */
  private void redo() {
    undoStack.push(model);
    if (!redoStack.isEmpty()) {
      model = redoStack.pop();
      renderImage();
    }
  }

  /**
   * Private helper for the undo command.
   */
  private void undo() {
    redoStack.push(model);
    if (!undoStack.isEmpty()) {
      model = undoStack.pop();
      renderImage();
    }
  }

  /**
   * Private helper to clear the undo and redo stacks when a new image is loaded or generated.
   */
  private void clearStacks() {
    undoStack.clear();
    redoStack.clear();
  }

  /**
   * Opens a dialog for asking the user about the rainbow dimensions and orientation and based on
   * the input values, signals the model to generate the corresponding rainbow and renders the
   * resultant image on the view.
   */
  private void rainbow() {
    String[] orientations = {"Vertical", "Horizontal"};
    RainbowGenerationPanel rainbowPanel = new RainbowGenerationPanelImpl(orientations);
    int result = JOptionPane.showConfirmDialog(null, rainbowPanel,
            "Rainbow Generator", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION && rainbowPanel.getXDimension() != null
            && rainbowPanel.getYDimension() != null) {
      try {
        int imageHeight = Integer.parseInt(rainbowPanel.getXDimension());
        int stripeWidth = Integer.parseInt(rainbowPanel.getYDimension());
        String orientation = orientations[rainbowPanel.getSelectedIndex()].toLowerCase();
        GeneratorCommand rainbow = new Rainbow(stripeWidth, imageHeight, orientation);
        generateImage(rainbow);
      } catch (NumberFormatException e) {
        rainbow();
      }
    }
  }

  /**
   * Opens a dialog for asking the user about the number of seeds to be considered while
   * transforming the image to its mosaic form and based on the input value, signals the model to
   * transform the image to its corresponding mosaic form and renders the resultant image on the
   * view.
   */
  private void mosaic() {
    undoStack.push(model);
    String seeds = JOptionPane.showInputDialog(null, "Enter "
            + "number of seeds:", "Mosaic Transformer", JOptionPane.PLAIN_MESSAGE);
    TransformerCommand mosaic;
    int noOfSeeds = 0;
    if (seeds != null) {
      try {
        noOfSeeds = Integer.parseInt(seeds);
      } catch (NumberFormatException e) {
        mosaic();
      }
    }
    mosaic = new Mosaic(noOfSeeds);
    executeCommand(mosaic);
  }

  /**
   * Signals the model to transform the image to its corresponding dithered form and renders the
   * resultant image on the view.
   */
  private void dither() {
    undoStack.push(model);
    TransformerCommand dither = new Dither();
    executeCommand(dither);
  }

  /**
   * Execute the color transformation or filter command.
   *
   * @param command command to be executed
   */
  private void executeCommand(TransformerCommand command) {
    SplashScreen splash = new SplashScreen();
    splash.setVisible(true);
    SwingWorker worker = new SwingWorker() {
      @Override
      protected Void doInBackground() {
        model = new ExtendedImageImpl(command.execute(model));
        return null;
      }
    };
    worker.addPropertyChangeListener(event -> {
      if (SwingWorker.StateValue.DONE == event.getNewValue()) {
        splash.setVisible(false);
        renderImage();
      }
    });
    worker.execute();
  }

  /**
   * Signals the model to transform the image to its corresponding sharpened form and renders the
   * resultant image on the view.
   */
  private void sharpen() {
    undoStack.push(model);
    TransformerCommand sharpen = new Sharpen();
    executeCommand(sharpen);
  }

  /**
   * Signals the model to transform the image to its corresponding greyscale form and renders the
   * resultant image on the view.
   */
  private void greyscale() {
    undoStack.push(model);
    TransformerCommand greyscale = new Greyscale();
    executeCommand(greyscale);
  }

  /**
   * Signals the model to transform the image to its corresponding blur form and renders the
   * resultant image on the view.
   */
  private void blur() {
    undoStack.push(model);
    TransformerCommand blur = new Blur();
    executeCommand(blur);
  }

  /**
   * Signals the model to transform the image to its corresponding sepia form and renders the
   * resultant image on the view.
   */
  private void sepia() {
    undoStack.push(model);
    TransformerCommand sepia = new Sepia();
    executeCommand(sepia);
  }

  /**
   * Opens a dialog for asking the user about the associated country and dimensions of the flag and
   * based on the input values, signals the model to generate the corresponding flag and renders the
   * resultant image on the view.
   */
  private void flags() {
    String[] countries = {"France", "Greece", "Switzerland"};
    FlagGenerationPanel flagPanel = new FlagGenerationPanelImpl(countries);
    int result = JOptionPane.showConfirmDialog(null, flagPanel,
            "Flag Generator", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION && flagPanel.getFlagHeight() != null) {
      try {
        GeneratorCommand flag = new Flag(Integer.parseInt(flagPanel.getFlagHeight()),
                countries[flagPanel.getSelectedCountryIndex()].toLowerCase());
        generateImage(flag);
      } catch (NumberFormatException e) {
        flags();
      }
    }
  }

  /**
   * Opens a file chooser which allows the user to navigate to the target directory and specify the
   * file name and format for the image file, while performing a save operation. If no format is
   * specified, it defaults to the PNG format.
   */
  private void save() {
    ImageChooser fileChooser = new ImageChooser("Save");
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == ImageChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        String directory = file.getParent();
        String filename;
        String format;
        if (!file.getName().contains(".")) {
          filename = file.getName();
          format = "png";
        } else {
          int index = file.getName().lastIndexOf('.');
          filename = file.getName().substring(0, index);
          format = file.getName().substring(index + 1);
        }
        this.saveImage(model, directory, filename, format);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Opens a file chooser which allows the user to navigate to the target directory and select the
   * image file to load.
   */
  private void load() {
    ImageChooser fileChooser = new ImageChooser("Load");
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == ImageChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        this.model = new ExtendedImageImpl(this.loadImage(file.getAbsolutePath()));
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      renderImage();
      clearStacks();
    }
  }

  /**
   * Opens a dialog for asking the user about the checkerboard square dimension and based on the
   * input value, signals the model to generate the corresponding rainbow and renders the resultant
   * image on the view.
   */
  private void checkerboardSide() {
    String result = JOptionPane.showInputDialog(null, "Enter " +
            "individual checkerboard side size:", "Checkerboard Size", JOptionPane.PLAIN_MESSAGE);
    if (result != null) {
      try {
        GeneratorCommand checkerboard = new Checkerboard(Integer.parseInt(result));
        generateImage(checkerboard);
      } catch (NumberFormatException e) {
        checkerboardSide();
      }
    }
  }

  /**
   * Private helper for the Image generator command.
   *
   * @param command command to be executed in order to generate the image
   */
  private void generateImage(GeneratorCommand command) {
    SplashScreen splash = new SplashScreen();
    splash.setVisible(true);
    SwingWorker worker = new SwingWorker() {
      @Override
      protected Void doInBackground() {
        model = new ExtendedImageImpl(command.execute());
        clearStacks();
        renderImage();
        return null;
      }
    };
    worker.addPropertyChangeListener(event -> {
      if (SwingWorker.StateValue.DONE == event.getNewValue()) {
        splash.setVisible(false);
        renderImage();
      }
    });
    worker.execute();
  }

  /**
   * Renders the image to the view.
   */
  private void renderImage() {
    this.view.toggleRedo(!redoStack.isEmpty());
    this.view.toggleUndo(!undoStack.isEmpty());
    this.view.renderImage(model.getAsBufferedImage());
  }
}