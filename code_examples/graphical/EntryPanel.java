import java.awt.*;
import javax.swing.*;

/**
 * The panel which contains the text fields into which the numbers are input,
 * and in which the result is returned.
 */
public class EntryPanel extends JPanel {
    
    // Instance variables to allow us to access the text fields.
    private JTextField num1, num2, result;
    
    /**
     * Construct the entry panel, consisting of text fields for entry and display,
     * and corresponding labels
     */
    public EntryPanel() {
        
        // Lay the panel out as a grid, with three rows and two columns.
        this.setLayout(new GridLayout(3,2));
        
        // Fill the first row with a label and text field for the first number
        this.add(new JLabel("First number"));
        this.add(num1 = new JTextField());
        
        // Likewise for the second number
        this.add(new JLabel("Second number"));
        this.add(num2 = new JTextField());
        
        // And for the result
        this.add(new JLabel("Result"));
        this.add(result = new JTextField());
        
        // By default text fields are editable, but we want the result field to be read-only
        result.setEditable(false);
    } // Constructor
    
    /**
     * Get the first number.
     * Throws a NumberFormatException if the text entered is not a number
     */
    public int getNumber1() {
        return Integer.parseInt(num1.getText());
    }
    
    /**
     * Get the second number.
     * Throws a NumberFormatException if the text entered is not a number
     */
    public int getNumber2() {
        return Integer.parseInt(num2.getText());
    }
    
    /**
     * Set the result
     */
    public void setResult(int n) {
        result.setText(Integer.toString(n));
    }
    
} // EntryPanel
