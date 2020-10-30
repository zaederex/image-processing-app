package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * This interface define the methods that the main Application frame of the Image processing
 * application must implement.
 */
public interface IView {

  /**
   * Set the listeners of input components to the passed listener.
   *
   * @param listener listener to be configured
   */
  void setListeners(ActionListener listener);

  /**
   * Render the image on the view.
   *
   * @param image image object to rendered
   */
  void renderImage(BufferedImage image);

  /**
   * Display or hide the View based on the value of the flag.
   *
   * @param flag true to set to visible, else false
   */
  void setVisible(boolean flag);

  /**
   * Toggle(enable or disable) the redo item in the edit menu.
   *
   * @param enable true when redo item should be enabled, else false
   */
  void toggleRedo(boolean enable);

  /**
   * Toggle(enable or disable) the undo item in the edit menu.
   *
   * @param enable true when undo item should be enabled, else false
   */
  void toggleUndo(boolean enable);
}