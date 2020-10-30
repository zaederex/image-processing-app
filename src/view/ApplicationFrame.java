package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


/**
 * This class implements the IView interface and represents the main frame of the application. It
 * provides a menu bar with the different options associated to the different operations that can be
 * performed by the application.
 */
public class ApplicationFrame extends JFrame implements IView {

  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JMenuItem loadFileItem;
  private JMenuItem saveFileItem;
  private JMenuItem checkerboardGenerateItem;
  private JMenuItem mosaicImageItem;
  private JMenuItem ditherImageItem;
  private JMenuItem greyscaleImageItem;
  private JMenuItem sepiaImageItem;
  private JMenuItem blurImageItem;
  private JMenuItem sharpenImageItem;
  private JMenuItem undoItem;
  private JMenuItem redoItem;

  private JMenuItem rainbowMenu;
  private JMenuItem flagMenu;

  /**
   * Construct the main application UI frame.
   */
  public ApplicationFrame() {
    super();
    setTitle("Image Processing App");

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height;
    int width = screenSize.width;
    setSize(width / 2, height / 2);

    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollPane = new JScrollPane(mainPanel);
    mainScrollPane.getVerticalScrollBar().setUnitIncrement(30);
    mainScrollPane.getHorizontalScrollBar().setUnitIncrement(30);
    add(mainScrollPane);


    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenu imageMenu = new JMenu("Image Effects");
    JMenu generateMenu = new JMenu("Generate Images");
    JMenu filterMenu = new JMenu("Apply Filter");
    JMenu transformerMenu = new JMenu("Apply Transformer");
    rainbowMenu = new JMenuItem("Rainbow");
    flagMenu = new JMenuItem("Country Flags");
    loadFileItem = new JMenuItem("Load File");
    saveFileItem = new JMenuItem("Save File");
    sharpenImageItem = new JMenuItem("Sharpen");
    blurImageItem = new JMenuItem("Blur");
    greyscaleImageItem = new JMenuItem("Greyscale");
    sepiaImageItem = new JMenuItem("Sepia");
    ditherImageItem = new JMenuItem("Dither");
    mosaicImageItem = new JMenuItem("Mosaic");
    checkerboardGenerateItem = new JMenuItem("Checkerboard");
    undoItem = new JMenuItem("Undo");
    redoItem = new JMenuItem("Redo");


    saveFileItem.setEnabled(false);
    sepiaImageItem.setEnabled(false);
    sharpenImageItem.setEnabled(false);
    greyscaleImageItem.setEnabled(false);
    blurImageItem.setEnabled(false);
    ditherImageItem.setEnabled(false);
    mosaicImageItem.setEnabled(false);
    undoItem.setEnabled(false);
    redoItem.setEnabled(false);

    loadFileItem.setAccelerator(KeyStroke.getKeyStroke('O',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    saveFileItem.setAccelerator(KeyStroke.getKeyStroke('S',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    undoItem.setAccelerator(KeyStroke.getKeyStroke('Z',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    redoItem.setAccelerator(KeyStroke.getKeyStroke('Y',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

    JMenuItem exitMenuItem = new JMenuItem("Exit");
    exitMenuItem.setToolTipText("Exit application");

    exitMenuItem.addActionListener((event) -> System.exit(0));

    flagMenu.setActionCommand("flag");
    loadFileItem.setActionCommand("load");
    saveFileItem.setActionCommand("save");
    checkerboardGenerateItem.setActionCommand("checkerboard");
    sepiaImageItem.setActionCommand("sepia");
    sharpenImageItem.setActionCommand("sharpen");
    greyscaleImageItem.setActionCommand("greyscale");
    blurImageItem.setActionCommand("blur");
    ditherImageItem.setActionCommand("dither");
    mosaicImageItem.setActionCommand("mosaic");
    rainbowMenu.setActionCommand("rainbow");
    undoItem.setActionCommand("undo");
    redoItem.setActionCommand("redo");

    editMenu.add(undoItem);
    editMenu.add(redoItem);
    filterMenu.add(sharpenImageItem);
    filterMenu.add(blurImageItem);

    transformerMenu.add(greyscaleImageItem);
    transformerMenu.add(sepiaImageItem);
    transformerMenu.add(ditherImageItem);
    transformerMenu.add(mosaicImageItem);


    fileMenu.add(loadFileItem);
    fileMenu.add(saveFileItem);
    fileMenu.addSeparator();
    fileMenu.add(exitMenuItem);

    imageMenu.add(filterMenu);
    imageMenu.add(transformerMenu);

    generateMenu.add(checkerboardGenerateItem);
    generateMenu.add(rainbowMenu);
    generateMenu.add(flagMenu);

    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(imageMenu);
    menuBar.add(generateMenu);

    setJMenuBar(menuBar);
  }


  @Override
  public void renderImage(BufferedImage image) {
    mainScrollPane.revalidate();
    mainPanel.removeAll();
    JPanel imagePanel = new JPanel();

    imagePanel.setLayout(new GridLayout(1, 0, 100, 10));
    mainPanel.add(imagePanel);
    imagePanel.add(new JLabel(new ImageIcon(image)));
    this.repaint();
    this.revalidate();
    saveFileItem.setEnabled(true);
    sepiaImageItem.setEnabled(true);
    sharpenImageItem.setEnabled(true);
    greyscaleImageItem.setEnabled(true);
    blurImageItem.setEnabled(true);
    ditherImageItem.setEnabled(true);
    mosaicImageItem.setEnabled(true);
  }

  @Override
  public void setVisible(boolean flag) {
    super.setVisible(flag);
  }

  @Override
  public void toggleRedo(boolean enable) {
    this.redoItem.setEnabled(enable);
  }

  @Override
  public void toggleUndo(boolean enable) {
    this.undoItem.setEnabled(enable);
  }

  @Override
  public void setListeners(ActionListener listener) {
    loadFileItem.addActionListener(listener);
    saveFileItem.addActionListener(listener);
    checkerboardGenerateItem.addActionListener(listener);
    mosaicImageItem.addActionListener(listener);
    ditherImageItem.addActionListener(listener);
    greyscaleImageItem.addActionListener(listener);
    sepiaImageItem.addActionListener(listener);
    blurImageItem.addActionListener(listener);
    sharpenImageItem.addActionListener(listener);
    rainbowMenu.addActionListener(listener);
    flagMenu.addActionListener(listener);
    undoItem.addActionListener(listener);
    redoItem.addActionListener(listener);
  }
}