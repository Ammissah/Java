
import java.awt.*;
import javax.swing.*;


/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */ 
public class InformationPanel extends JPanel {
    
    private JTextField description, status;
    
    public InformationPanel() {
        
        this.setLayout(new GridLayout(2, 2));
        
        this.add(new JLabel("Sequence Description"));
        this.add(new JLabel("Status"));
        
        
        this.add(description = new JTextField());
        this.add(status = new JTextField());
         
        
        
        status.setEditable(false);
        
    }
    public String getSequenceDescription(){
        return description.getText();
    
    }
    
    public  void setStatus(String theStatus){
        status.setText(theStatus);
        
    
    }
    
    public JTextField setSequenceDescription(){
        return description;
    
    }
}
