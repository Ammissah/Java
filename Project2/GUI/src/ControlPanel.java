import java.awt.*;
import java.awt.event.ActionListener;
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
public class ControlPanel extends JPanel {
    private Translator translator;
    private TranslationTool t;
    
    public ControlPanel(Translator translator, TranslationTool t) {
        this.translator = translator;
        
        translator = new Translator(t );
        
        JButton revCompButton, simpleButton, sixFrameButton, getORFsButton, clearButton;
        
        this.add(revCompButton = new JButton("revComp"));
        revCompButton.addActionListener(translator);
        
        this.add(simpleButton = new JButton("Simple"));
        simpleButton.addActionListener(translator);
        
        this.add(sixFrameButton = new JButton("sixFrame"));
        sixFrameButton.addActionListener(translator);
        
        this.add(getORFsButton = new JButton("getORFs"));
        getORFsButton.addActionListener(translator);
        
        this.add(clearButton = new JButton("Clear"));
        clearButton.addActionListener(translator);
        
    }
    
}
