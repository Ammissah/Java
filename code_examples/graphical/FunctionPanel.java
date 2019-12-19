import java.awt.*;
import javax.swing.*;

/**
 * Panel containing the function buttons
 */
public class FunctionPanel extends JPanel {
    
    /**
     * Create the function panel, passing the calculator object which will do the actual sums
     */
    public FunctionPanel(Calculator calc) {
        
        // Use the default flow layout, which will simply lay the buttons out left-right.
        
        // We need names for the buttons
        JButton addButton, subButton, mulButton, divButton;
        
        // Add a button, assigning to the relevant variable at the same time
        this.add(addButton = new JButton("Add"));
        
        // Register the calculator as a listener for the button
        addButton.addActionListener(calc);
        
        // Do the same for the other three buttons
        this.add(subButton = new JButton("Subtract"));
        subButton.addActionListener(calc);
        this.add(mulButton= new JButton("Multiply"));
        mulButton.addActionListener(calc);
        this.add(divButton = new JButton("Divide"));
        divButton.addActionListener(calc);
    } // Constructor
    
    // Currently this class needs no methods - the constructor does all that's needed.
    
} // FunctionPanel
