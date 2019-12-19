// For a Swing GUI we typically need to import AWT stuff as well,
// e.g. for the layout managers. In addition, if we need to handle events,
// we need classes from the java.awt.event package.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The outer framework of the calculator GUI. It is a subclass of QuitableJFrame
 * in order to make use of the window event handling and other gooodies provided.
 * It implements ActionListener so it can deal with file menu options
 * (currently only "quit".)
 */
public class CalcFrame extends QuitableJFrame
implements ActionListener {
    
    // Often, we have references to the subcomponents here as private instance variables.
    // As it happens this isn't necessary in this case.
    
    /**
     * Set up the calculator GUI.
     */
    public CalcFrame() {
        // Use the superclass (QuitableJFrame) constructor to set the title bar.
        super("Calculator");
        
        // Specify a border layout for the frame overall. Note that we set the layout
        // of the content pane, not the frame itself.
        this.getContentPane().setLayout(new BorderLayout());
        
        // Set up the menu bar, using a private utility method to keep the constructor tidy.
        this.setUpMenubar();
        
        // Construct the entry panel. The details of this are delegated to the EntryPanel class
        EntryPanel entries = new EntryPanel();
        
        // Create a new calculator object, which will do the actual sums.
        // It needs a reference to the frame so that it can put up dialog boxes
        // and a reference to the entry panel so it can get and set the entries.
        Calculator theCalculator = new Calculator(this, entries);
        
        // Create the panel containing the function buttons.
        // This needs a reference to the calculator object for event handling.
        FunctionPanel functions = new FunctionPanel(theCalculator);
        
        // Add the panels to the content pane.
        this.getContentPane().add(entries, BorderLayout.CENTER);
        this.getContentPane().add(functions, BorderLayout.SOUTH);
        
        // Tell the layout manager to sort out the layout of the frame
        // and of its subcomponennts (the panels)
        this.pack();
        
        // Make the frame visible.
        this.setVisible(true);
    } // constructor
    // Note that the constructor contains a total of 10 executable statements
    // - by delegating much of the functionality we've made it very simple.
    
    
    
    // Private utility method to set up the menu bar.
    // Currently this has just one menu - file - with one item - quit.
    private void setUpMenubar() {
        
        // Create a menubar, a menu, and a menu item
        JMenuBar theBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitItem = new JMenuItem("Quit");
        
        // The easiest place to listen for the quit event is in the frame itself.
        quitItem.addActionListener(this);
        fileMenu.add(quitItem);
        theBar.add(fileMenu);
        
        // The menu bar is treated specially - we don't add it to the content pane.
        this.setJMenuBar(theBar);
    } // setUpMenuBar
    
    /**
     * Handle the quit event from the file menu, by putting up a quit-or-cancel dialog box
     */
    public void actionPerformed(ActionEvent e) {
        // quitOrCancel is defined in QuitableJFrame to put up an appropriate dialog box
        this.quitOrCancel();
    }
    
    /**
     * Start up the application.
     */
    public static void main(String[] args) {
        // All we need to do is create the frame, as the constructor does everything required.
        CalcFrame theFrame = new CalcFrame();
    }
    
} // CalcFrame
