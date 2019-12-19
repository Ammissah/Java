
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.ImageIcon;


/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
public class TranslationTool extends QuitableJFrame implements ActionListener {

    private InformationPanel informationFromTheInfoPanel;
    private TranslationPanel theTranslationPanel;
    private ControlPanel theControlPanel;

    private Translator translator;

    public TranslationTool(String title, int xpixels, int ypixels) {
        super(title, xpixels, ypixels);

        translator = new Translator(this);

        this.getContentPane().setLayout(new BorderLayout());
        this.setUpMenubar();

        informationFromTheInfoPanel = new InformationPanel();
        this.getContentPane().add(informationFromTheInfoPanel, BorderLayout.NORTH);

        theTranslationPanel = new TranslationPanel();
        this.getContentPane().add(theTranslationPanel, BorderLayout.CENTER);

        theControlPanel = new ControlPanel(translator, this);
        this.getContentPane().add(theControlPanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);

    }

    private void setUpMenubar() {

        // Create a menubar, a menu, and a menu item
        JMenuBar theBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_N);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.ALT_MASK));
        openMenuItem.addActionListener(translator);
        openMenuItem.setIcon(new ImageIcon("open.gif"));

        JMenuItem saveInputMenuItem = new JMenuItem("Save Input", KeyEvent.VK_S);
        saveInputMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        saveInputMenuItem.addActionListener(translator);

        JMenuItem saveInputAsMenuItem = new JMenuItem("Save Input As", KeyEvent.VK_I);
        saveInputAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        saveInputAsMenuItem.addActionListener(translator);

        JMenuItem saveOutputMenuItem = new JMenuItem("Save Output", KeyEvent.VK_O);
        saveOutputMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        saveOutputMenuItem.addActionListener(translator);

        JMenuItem saveOutputAsMenuItem = new JMenuItem("Save Output As", KeyEvent.VK_T);
        saveOutputAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
        saveOutputAsMenuItem.addActionListener(translator);

        JMenuItem quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
        quitItem.addActionListener(translator);

        // The easiest place to listen for the quit event is in the frame itself.
        fileMenu.add(openMenuItem);
        fileMenu.add(saveInputMenuItem);
        fileMenu.add(saveInputAsMenuItem);
        fileMenu.add(saveOutputMenuItem);
        fileMenu.add(saveOutputAsMenuItem);
        fileMenu.add(quitItem);

        theBar.add(fileMenu);

        // The menu bar is treated specially - we don't add it to the content pane.
        this.setJMenuBar(theBar);
    } // setUpMenuBar

    public InformationPanel getInformationFromTheInfoPanel() {
        return informationFromTheInfoPanel;

    }

    public TranslationPanel getTheTranslationPanel() {
        return theTranslationPanel;

    }

    public ControlPanel getTheControlPanel(Translator translator) {
        return theControlPanel;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }

    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            // handle exception
            JOptionPane.showMessageDialog(null, ex);

        }

        TranslationTool t = new TranslationTool("TranslationTool", 200, 200);
    }

}
