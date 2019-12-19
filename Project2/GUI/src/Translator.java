
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
public class Translator implements ActionListener {

    private InformationPanel informationPanel;
    private TranslationPanel translationPanel;
    private TranslationTool parent;

    private File fileNameUsedForInput, fileNameUsedForOutput;
    private String dirLastTimeInput;
    private String fileLastTime = "";
    
    private String dirLastTimeOutput;
    private String fileLastTimeOutput = "";

    public Translator(TranslationTool parent) {

        this.parent = parent;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        informationPanel = parent.getInformationFromTheInfoPanel();
        translationPanel = parent.getTheTranslationPanel();
        String content = translationPanel.getSequenceFromInputArea();
        String description = informationPanel.getSequenceDescription();



        if (command.equals("Open")) {
            String line = null;
            translationPanel.setOutputArea("");
            try {
                JFileChooser open = new JFileChooser();
                int option = open.showOpenDialog(parent);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File fileName = new File(open.getSelectedFile().getPath());

                    DNASequence dna = new DNASequence(description, content);
                    DNASequence makeSequence = DNASequence.makeSequence(fileName.toString());
                    System.out.println(makeSequence);

                    try (FileReader fr = new FileReader(fileName)) {
                        BufferedReader br = new BufferedReader(fr);
                        String s;
                        while ((s = br.readLine()) != null) {
                            translationPanel.getOutputArea().append(s);
                        }
                    }
                }
                if (option == JFileChooser.CANCEL_OPTION) {
                    informationPanel.setStatus("File Open operation was cancelled");
                    translationPanel.getOutputArea().setText("");
                }

            } catch (HeadlessException | IOException e) {
                System.out.println(e);
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
           

        }
        if (command.equals("Save Input")) {

            if ("".equals(fileLastTime)) {
                saveAsForInputArea();
                fileLastTime = fileNameUsedForInput.getName();

                dirLastTimeInput = fileNameUsedForInput.getParent();

            } else {

                writeFileForInput(dirLastTimeInput + fileLastTime);
            }

        }

        if (command.equals("Save Input As")) {

            JFileChooser save = new JFileChooser("user.home");
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
            save.setFileFilter(extensionFilter);
            save.setApproveButtonText("Save Input As");

            fileNameUsedForInput = save.getSelectedFile();
            BufferedWriter writer = null;
            int option = save.showSaveDialog(parent);

            if (option == JFileChooser.APPROVE_OPTION) {
                fileNameUsedForInput = new File(save.getSelectedFile() + ".txt");
                if (fileNameUsedForInput.exists()) {
                    option = JOptionPane.showConfirmDialog(parent, "Replace the existing file");
                    while (option == JOptionPane.NO_OPTION) {
                        option = save.showSaveDialog(parent);
                    }

                }
                BufferedWriter outFile = null;
                try {
                    outFile = new BufferedWriter(new FileWriter(fileNameUsedForInput));

                    translationPanel.getInputArea().write(outFile);
                    fileLastTime = fileNameUsedForInput.getName();

                    dirLastTimeInput = fileNameUsedForInput.getParent();
                    String selectedFilePath = fileNameUsedForInput.getPath();
                    this.writeFileForInput(selectedFilePath);

                } catch (IOException ex) {
                } finally {
                    if (outFile != null) {
                        try {
                            outFile.close();
                        } catch (IOException e) {

                        }
                    }
                }
            } else {
                return;
            }

        }

        //************************************************************************* Output Menu    
        if (command.equals("Save Output")) {

            if ("".equals(fileLastTimeOutput)) {
                saveAsForOutputArea();
                fileLastTimeOutput = fileNameUsedForOutput.getName();

                dirLastTimeOutput = fileNameUsedForOutput.getParent();
            


            } else {

                writeFileForOutput(dirLastTimeOutput + fileLastTimeOutput);
            }

        }

        if (command.equals("Save Output As")) {

            JFileChooser save = new JFileChooser("user.home");
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
            save.setFileFilter(extensionFilter);
            save.setApproveButtonText("Save Output As");

            fileNameUsedForOutput = save.getSelectedFile();
            BufferedWriter writer = null;
            int option = save.showSaveDialog(parent);

            if (option == JFileChooser.APPROVE_OPTION) {
                fileNameUsedForOutput = new File(save.getSelectedFile() + ".txt");
                if (fileNameUsedForOutput.exists()) {
                    option = JOptionPane.showConfirmDialog(parent, "Replace the existing file");
                    while (option == JOptionPane.NO_OPTION) {
                        option = save.showSaveDialog(parent);
                    }

                }
                BufferedWriter outFile = null;
                try {
                    outFile = new BufferedWriter(new FileWriter(fileNameUsedForOutput));

                    translationPanel.getOutputArea().write(outFile);
                    dirLastTimeOutput = fileNameUsedForOutput.getParent();
                    fileLastTimeOutput = fileNameUsedForOutput.getName();
                    String selectedFilePath = fileNameUsedForOutput.getPath();

                } catch (IOException ex) {
                } finally {
                    if (outFile != null) {
                        try {
                            outFile.close();
                        } catch (IOException e) {

                        }
                    }
                }

            } else {
                return;
            }
            
        }

        //***********************************************
        if (command.equals("revComp")) {

            DNASequence dna = null;
            try {
                dna = new DNASequence(description, content);
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            DNASequence revComplement = null;
            try {
                revComplement = dna.revComp();
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            informationPanel.setStatus("Reverse Complement");
            translationPanel.setOutputArea(revComplement.getContent());
        }

        if (command.equals("Simple")) {

            DNASequence dna = null;
            try {
                dna = new DNASequence(description, content);
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            DNASequence revComplement = null;
            try {
                revComplement = dna.revComp();
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            Ribosome ribosome = new Ribosome();
            ProteinSequence[] translate = null;
            try {
                translate = ribosome.translate(dna);
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            informationPanel.setStatus("Simple");
//            translationPanel.setOutputArea(translate);

//                theTranslationPanel.setOutputArea(Arrays.toString(translate));
            translationPanel.setOutputArea(ribosome.getProteinSequenceContentSimpleTranslation());
        }

        if (command.equals("sixFrame")) {

            DNASequence dna = null;
            try {
                dna = new DNASequence(description, content);
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            DNASequence revComplement = null;
            try {
                revComplement = dna.revComp();
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            Ribosome ribosome = new Ribosome();
            ProteinSequence[] translate = null;
            try {
                translate = ribosome.translateSix(dna);
            } catch (InvalidSequenceException | IOException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            informationPanel.setStatus("TranslateSix");
//            translationPanel.setOutputArea(translate);

//                theTranslationPanel.setOutputArea(Arrays.toString(translate));
            translationPanel.setOutputArea(ribosome.getProteinSequenceContentSixFrame());
        }

        if (command.equals("getORFs")) {
            informationPanel.setStatus("getORFs");
            ProteinSequence p = null;
            try {

                p = new ProteinSequence(description, content);
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            String content1 = p.getContent();
            try {
                ArrayList<OpenReadingFrame> theORFs = OpenReadingFrame.getORFs(p);
                translationPanel.setOutputArea(Arrays.toString(theORFs.toArray()));
            } catch (IOException | InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (command.equals("Clear")) {
            informationPanel.setStatus("Clear");

            translationPanel.setOutputArea("");

        }

        if (command.equals("getORFs")) {
            try {
                ProteinSequence pr1 = new ProteinSequence(description, content);
            } catch (InvalidSequenceException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
            informationPanel.setStatus("getORFs");

        }

    }

    public void saveAsForInputArea() {

JFileChooser save = new JFileChooser("user.home");
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
            save.setFileFilter(extensionFilter);
            save.setApproveButtonText("Save Output As");

            fileNameUsedForInput = save.getSelectedFile();
            BufferedWriter writer = null;
            int option = save.showSaveDialog(parent);

            if (option == JFileChooser.APPROVE_OPTION) {
                fileNameUsedForInput = new File(save.getSelectedFile() + ".txt");
                if (fileNameUsedForInput.exists()) {
                    option = JOptionPane.showConfirmDialog(parent, "Replace the existing file");
                    while (option == JOptionPane.NO_OPTION) {
                        option = save.showSaveDialog(parent);
                    }

                }
                BufferedWriter outFile = null;
                try {
                    outFile = new BufferedWriter(new FileWriter(fileNameUsedForInput));

                    translationPanel.getInputArea().write(outFile);

                } catch (IOException ex) {
                } finally {
                    if (outFile != null) {
                        try {
                            outFile.close();
                        } catch (IOException e) {

                        }
                    }
                }

            } else {
                return;
            }
            
            
    }

    public void saveAsForOutputArea() {

        
JFileChooser save = new JFileChooser("user.home");
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
            save.setFileFilter(extensionFilter);
            save.setApproveButtonText("Save Output As");

            fileNameUsedForOutput = save.getSelectedFile();
            BufferedWriter writer = null;
            int option = save.showSaveDialog(parent);

            if (option == JFileChooser.APPROVE_OPTION) {
                fileNameUsedForOutput = new File(save.getSelectedFile() + ".txt");
                if (fileNameUsedForOutput.exists()) {
                    option = JOptionPane.showConfirmDialog(parent, "Replace the existing file");
                    while (option == JOptionPane.NO_OPTION) {
                        option = save.showSaveDialog(parent);
                    }

                }
                BufferedWriter outFile = null;
                try {
                    outFile = new BufferedWriter(new FileWriter(fileNameUsedForOutput));

                    translationPanel.getOutputArea().write(outFile);

                } catch (IOException ex) {
                } finally {
                    if (outFile != null) {
                        try {
                            outFile.close();
                        } catch (IOException e) {

                        }
                    }
                }

            } else {
                return;
            }
    }

    public void writeFileForInput(String file) {
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(file));

            translationPanel.getInputArea().write(outFile);

        } catch (IOException ex) {
        } finally {
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public void writeFileForOutput(String file) {
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(file));

            translationPanel.getOutputArea().write(outFile);

        } catch (IOException ex) {
        } finally {
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException e) {

                }
            }
        }
    }
    
    
    protected void openAction() {
        translationPanel.getInputArea().setText("");
        String line = null;
        translationPanel.setOutputArea("");
        try {
            JFileChooser open = new JFileChooser();
            if (!dirLastTime.equals("")) {
                open.setCurrentDirectory(new File(dirLastTimeForInput));
            }
            if (!fileLastTimeForInput.equals("")) {
                open.setSelectedFile(new File(fileLastTimeForInput));
            }
            int option = open.showOpenDialog(parent);

            if (option == JFileChooser.APPROVE_OPTION) {
                File fileName = new File(open.getSelectedFile().getPath());

                DNASequence makeSequence = DNASequence.makeSequence(fileName.toString());
                String content1 = makeSequence.getContent();
                String description1 = makeSequence.getDescription();

                DNASequence dna = new DNASequence(description1, content1);

                try (FileReader fr = new FileReader(fileName)) {
                    BufferedReader br = new BufferedReader(fr);
                    String s;
                    br.readLine();
                    while ((s = br.readLine()) != null) {

                        translationPanel.getInputArea().append(s.trim());  // trim leading white space

                    }
                    informationPanel.setSequenceDescription().setText(description1);  // add sequence description to information panel
                    fileName = open.getSelectedFile();

                    fileLastTimeForInput = fileName.getName();

                    dirLastTimeForInput = fileName.getParent();

                }
            }
            if (option == JFileChooser.CANCEL_OPTION) {
                informationPanel.setStatus("File Open operation was cancelled");
                translationPanel.getOutputArea().setText("");
            }

        } catch (HeadlessException | IOException | InvalidSequenceException ex) {
            JOptionPane.showMessageDialog(parent, ex);
        }

    }

}
