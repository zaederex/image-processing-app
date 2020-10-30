package view;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * This class implements the FlagGenerationPanel interface. This panel consist of a text field for
 * specifying the height of the flag and a combo box with values of the supported countries.
 */
public class FlagGenerationPanelImpl extends Panel implements FlagGenerationPanel {
  private JTextField flagHeight;
  private JComboBox countryList;

  /**
   * Construct the flag generation panel.
   *
   * @param countriesArray countries that need to populated in the combo box
   */
  public FlagGenerationPanelImpl(String[] countriesArray) {
    flagHeight = new JTextField();
    this.setLayout(new GridLayout(2, 0, 10, 10));
    this.add(new JLabel("Enter height of the flag:"));
    this.add(flagHeight);
    this.add(new JLabel("Choose Country:"));

    countryList = new JComboBox(countriesArray);
    countryList.setSelectedIndex(0);
    this.add(countryList);
  }

  @Override
  public String getFlagHeight() {
    return flagHeight.getText();
  }

  @Override
  public int getSelectedCountryIndex() {
    return countryList.getSelectedIndex();
  }
}