import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;



/**
 *
 * @author acer
 */
public class TranslationPanel extends JPanel {

    private JTextArea inputArea, outputArea;
  

    

    public TranslationPanel() {

        

        this.add(inputArea = new JTextArea("Input Area", 20, 20));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        inputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane jScroll1 = new JScrollPane(inputArea);
        jScroll1.setPreferredSize(new Dimension(300, 350));
        jScroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(jScroll1);

        this.add(outputArea = new JTextArea("Output Area", 20, 20));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        
        outputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane jScroll2 = new JScrollPane(outputArea);
        jScroll2.setPreferredSize(new Dimension(300, 350));
        jScroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(jScroll2);


    }
    
    public String getSequenceFromInputArea(){
        return inputArea.getText();
    
    }
    
    public void setOutputArea(DNASequence dna1){
    outputArea.setText(dna1.toString());
    }
    
     public void setOutputArea(ProteinSequence[] protein){
    outputArea.setText(Arrays.toString(protein));
    }
     
     public void setOutputArea(String s){
    outputArea.setText(s);
    } 
     
     public void setOutputArea(String[] sequenceContent){
             outputArea.setText(Arrays.toString(sequenceContent));
    } 

    public JTextArea getOutputArea(){
        return outputArea;
    
    
    }
    
    public JTextArea getInputArea(){
        return inputArea;
    
    
    }
    public void setOutputArea(ArrayList<OpenReadingFrame> p){
    outputArea.setText(p.toString());
    
    }

}
