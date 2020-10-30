package view;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.JWindow;
import javax.swing.ImageIcon;

/**
 * This class represents a splash screen that is displayed when an operation that typically takes a
 * long time to complete is in process. The splash screen acts as a visual cue for the user to
 * indicate that an operation is currently being performed by the application.
 */
public class SplashScreen extends JWindow {
  private Image splashScreen;

  /**
   * Construct the splash screen.
   */
  public SplashScreen() {
    URL url = getClass().getClassLoader().getResource("ajax-loader.gif");
    splashScreen = Toolkit.getDefaultToolkit().getImage(url);
    ImageIcon imageIcon = new ImageIcon(splashScreen);

    setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int x = (screenSize.width - getSize().width) / 2;

    int y = (screenSize.height - getSize().height) / 2;

    setAlwaysOnTop(true);

    setLocation(x, y);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    g.drawImage(splashScreen, 0, 0, this);
  }
}