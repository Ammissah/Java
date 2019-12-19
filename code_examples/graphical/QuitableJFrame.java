import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A Frame which provides default implementations for the
 * WindowListener events. These do nothing, except for
 * WindowClosing, which pops up a dialog box to check if
 * we want to quit, and if so destroys the Frame and exits
 * the program.
 */
public class QuitableJFrame extends JFrame implements WindowListener {
    
    /**
     * Construct a quitable JFrame with the given title and size.
     */
    public QuitableJFrame( String title, int xpixels, int ypixels ) {
        super(title);
        this.addWindowListener(this);
        if (xpixels > 0 && ypixels > 0) {
            this.setSize(xpixels, ypixels);
        }
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    public QuitableJFrame( String title ) {
        this(title, 0, 0);
    }
    
    /**
     * Utility method to pop up an InputDialog when we want
     * a value from the user
     */
    public int getValueFromUser(String msg, int defaultValue) {
        String valueString = JOptionPane.showInputDialog(this, msg,
        "input value", JOptionPane.QUESTION_MESSAGE);
        if (valueString == null) return defaultValue;
        else
            try {
                int value = Integer.parseInt(valueString);
                return value;
            }
            catch (NumberFormatException e) {
                return defaultValue;
            }
    } // getValueFromUser
    
    
    /**
     * Test program
     */
    public static void main(String[] args) {
        JFrame f = new QuitableJFrame("Test", 300, 300);
        // We'll handle closing, so...
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);
    }
    
    
    /**
     * Utility to pop up a quit/cancel dialog associated with the frame.
     */
    public void quitOrCancel() {
        int result = JOptionPane.showConfirmDialog(this,
        "Quit " + this.getTitle() + "?",
        "Just making sure...",
        JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(1);
        }
    }
    
    /**
     * Utility to pop up a message dialog
     */
    public void tellUser(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Information",
        JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Utility to pop up a warning message dialog
     */
    public void warnUser(String warning) {
        JOptionPane.showMessageDialog(this, warning, "Warning",
        JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Default implementation does nothing
     */
    public void windowActivated(WindowEvent e){ }
    
    /**
     * Default implementation does nothing
     */
    public void windowClosed( WindowEvent e) { }
    
    /**
     * Default implementation pops up a dialog box, which if accepted
     * destroys the frame and quits the whole application
     * with System.exit.
     */
    public void windowClosing(WindowEvent e)  {
        this.quitOrCancel();
    }
    
    /**
     * Default implementation does nothing.
     */
    public void windowDeactivated(WindowEvent e){ }
    
    /**
     * Default implementation does nothing.
     */
    public void windowDeiconified( WindowEvent e) { }
    
    /**
     * Default implementation does nothing.
     */
    public void windowIconified( WindowEvent e) { }
    
    /**
     * Default implementation does nothing.
     */
    public void windowOpened( WindowEvent e) { }
    
    /**
     * Set the look and feel. The argument is the full name of the
     * desired look&feel class, e.g.
     * com.sun.java.swing.plaf.windows.WindowsLookAndFeel
     */
    public void setLookAndFeel(String lookAndFeel) {
        try {
            // Set the look and feel for the system..
            UIManager.setLookAndFeel(lookAndFeel);
            // .. and tell all the components about it.
            SwingUtilities.updateComponentTreeUI(this);
            // Call the layout manager in case things need shuffling around.
            this.validate();
            // And display the result of all that.
            this.repaint();
            // Indicate success
            // System.out.println("Look and feel set to " + lookAndFeel );
        }
        catch(Exception e) {
            // Indicate if desired L&F wasn't available for some reason.
            System.out.println(e);
        }
    }
}
