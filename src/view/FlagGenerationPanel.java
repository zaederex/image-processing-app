package view;

/**
 * This interface defines the operations that the Panel for taking inputs related to the Flag
 * generation operation must implement.
 */
public interface FlagGenerationPanel {
  /**
   * Return the height of the flag entered by the user.
   *
   * @return height of the flag
   */
  String getFlagHeight();

  /**
   * Return the index of value selected from the Country selection dropdown.
   *
   * @return index of selected value
   */
  int getSelectedCountryIndex();
}