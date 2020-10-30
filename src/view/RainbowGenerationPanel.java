package view;

/**
 * This interface defines the operations that the Panel for taking inputs related to the Rainbow
 * generation operation must implement.
 */
public interface RainbowGenerationPanel {

  /**
   * Get the value inputted by the user associated with the horizontal dimension (width of the flag
   * in case of horizontal flag or width of stripe in case of vertical flag).
   *
   * @return x dimension of the flag
   */
  String getXDimension();

  /**
   * Get the value inputted by the user associated with the vertical dimension (height of the stripe
   * in case of horizontal flag or height of flag in case of vertical flag).
   *
   * @return y dimension of the flag
   */
  String getYDimension();

  /**
   * Return index associated with the drop down for orientation of the flag.
   *
   * @return index associated with selected item from drop down
   */
  int getSelectedIndex();
}