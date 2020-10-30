package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * This class implements the RainbowGenerationPanel interface. This panel consist of 2 text fields
 * for specifying the rainbow and stripe dimensions and a combo box with values of the orientations
 * of the flag.
 */
public class RainbowGenerationPanelImpl extends JPanel implements RainbowGenerationPanel {
  private JComboBox<String> orientationList;
  private JTextField xDimension;
  private JTextField yDimension;

  private JLabel imageDimension;
  private JLabel stripeDimension;

  /**
   * Construct the rainbow generation panel with the specified orientation.
   *
   * @param orientations supported orientation for rainbow generation
   */
  public RainbowGenerationPanelImpl(String[] orientations) {
    super();

    imageDimension = new JLabel();
    stripeDimension = new JLabel();

    xDimension = new JTextField();
    yDimension = new JTextField();

    this.setLayout(new GridLayout(3, 0, 10, 10));
    this.add(new JLabel("Rainbow Orientation:"));

    orientationList = new JComboBox<>(orientations);
    orientationList.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (orientationList.getSelectedIndex() == 0) {
          imageDimension.setText("Image Height:");
          stripeDimension.setText("Stripe Width:");
        } else {
          imageDimension.setText("Image Width:");
          stripeDimension.setText("Stripe Height:");
        }
      }
    });
    orientationList.setSelectedIndex(0);
    this.add(orientationList);

    imageDimension.setText("Image Height:");

    this.add(imageDimension);
    this.add(xDimension);
    stripeDimension.setText("Stripe Width:");
    this.add(stripeDimension);
    this.add(yDimension);
  }

  @Override
  public String getXDimension() {
    return xDimension.getText();
  }

  @Override
  public String getYDimension() {
    return yDimension.getText();
  }

  @Override
  public int getSelectedIndex() {
    return orientationList.getSelectedIndex();
  }
}