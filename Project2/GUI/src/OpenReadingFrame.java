
import java.io.IOException;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

/**
 * OpenReadingFrame.java is subclass of ProteinSequence
 * This class represents a protein sequences starting with M (the start codon) and ending with the Stop codon * respectively
 * This Class also allows the Open Reading Frames(ORFs) to be sorted in descending order based on their lengths
 */

public class OpenReadingFrame extends ProteinSequence implements Comparable<OpenReadingFrame> {

    private static final String filename = "proteinSequence1.txt";                     // instanc variable for the filename                   
    private static final ArrayList<String> ORFarrayList = new ArrayList<>();           // instance variable to hold the string array of ORFs
    private static ArrayList<Integer> ORFlengthList = new ArrayList<>();               // instance variable to hold the lenghts of ORFs

    private static ArrayList<OpenReadingFrame> OpenReadingFrameList = new ArrayList<>();

    /**
     * constructor for ORF class
     * @param description the description of the ORF
     * @param content     the content of the ORF
     * @throws InvalidSequenceException the invalid sequence Exception for the protein Sequence
     */
    public OpenReadingFrame(String description, String content) throws InvalidSequenceException {
        super(description, content);  //explicit call to super Class Sequence

    }

    /**
     * Method which gets the lengths of ORFs
     * @return ORFlengthList
     */
    public static ArrayList<Integer> GetORFLengths() {

        for (String s : ORFarrayList) {
            ORFlengthList.add(s.length());
        }
        System.out.println(ORFlengthList);
        return ORFlengthList;
    }

    /**
     * Returns an Array List of ORFs sorted in Descending order
     * @param proteinSeq the protein Sequence received
     * 
     * @throws IOException the IOException error occurred
     * @throws InvalidSequenceException the error for the invalid sequence
     * @return OpenReadingFrameList the list of ORFS in ArrayList
     */
    public static ArrayList<OpenReadingFrame> getORFs(ProteinSequence proteinSeq) throws IOException, InvalidSequenceException {

        try {
            proteinSeq.writeToFile(filename);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String theContent = Sequence.getContent(filename);
       
        System.out.println(theContent);
         System.out.println("For the Protein above the ORFs found are: \n");

        String ORFpattern = "M[^*]+\\*";          // We use regular expressions to find start M and end codons *

        Pattern p = Pattern.compile(ORFpattern);  // Create a Pattern object, Compiles the given regular expression into a pattern with the given flags.

        Matcher mORF = p.matcher(theContent);  //Create matcher object.

        while (mORF.find()) {
            ORFarrayList.add(mORF.group());

        }

//        for (String str : ORFarrayList) {
//            System.out.println(str);
//           
//        }
        
//        System.out.println("Found ORFs in Array form " + " " +ORFarrayList);
//        System.out.println("\n");
        Collections.sort(ORFarrayList, Collections.reverseOrder());

//        System.out.println("After Sorting based on Descending Order" + ORFarrayList);

        for (int j = 0; j < ORFarrayList.size(); j++) {
            OpenReadingFrameList.add(j, new OpenReadingFrame(proteinSeq.getDescription(), ORFarrayList.get(j)));
        }

//        System.out.println("The ORFs in array Form sorted in Descending order " + " " + OpenReadingFrameList);
        return OpenReadingFrameList;

    }
    
    public String getORFsSorted(){
    return Arrays.toString(OpenReadingFrameList.toArray());
    
    }

    /**
     * Test Class 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ProteinSequence p = null;
        try {
            p = new ProteinSequence("Protein Sequence from Sperm Whale Gene", "LMLSLLG*EIWRY*NCMVAVCCHDKNAADM*RC*QYVLGQ");
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            getORFs(p);
        } catch (IOException | InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }
        GetORFLengths();
    }

    /**
     * Comparable  natural comparison method
     * @param ORF the ORF
     * @return compare value
     */
    @Override
    public int compareTo(OpenReadingFrame ORF) {

        int compare = 0;
        String ORF1 = ORF.toString();
        for (String ORFarrayList1 : ORFarrayList) {
            if (ORFarrayList1.length() == ORF1.length()) {
                compare = 0;
            }
            if (ORFarrayList1.length() > ORF1.length()) {
                compare = 1;
            } else {
                compare = -1;
            }
        }
        return compare;

    }

}
