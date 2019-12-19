
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import java.util.*;
/**
 * Ribosome.java handles the translation of a DNA to Protein Sequence
 * This Class contains the translation table (genetic code), stored in a Map(HashMap)
 * The Class first does a simple Translation based on the both the first and first frames of the Sequence
 * This Class also does a Translation based on the 6 frames of a DNASequence
 */

public class Ribosome {

    private static final Map<String, String> translation = new HashMap<>();                          // instance variable to hold dtring of codons and amino acids

    private static final String[] codons = {"TTT", "TTC", "TTA", "TTG", "CTT", "CTC", "CTA", "CTG", "ATT", "ATC", "ATA", "ATG",
        "GTT", "GTC", "GTA", "GTG", "TCT", "TCC", "TCA", "TCG", "CCT", "CCC", "CCA", "CCG", "ACT", "ACC", "ACA", "ACG",
        "GCT", "GCC", "GCA", "GCG", "TAT", "TAC", "TAA", "TAG", "CAT", "CAC", "CAA", "CAG", "AAT", "AAC", "AAA", "AAG",
        "GAT", "GAC", "GAA", "GAG", "TGT", "TGC", "TGA", "TGG", "CGT", "CGC", "CGA", "CGG", "AGT", "AGC", "AGA", "AGG",
        "GGT", "GGC", "GGA", "GGG"};                                                                // instance variable which holds codons

    private static final String[] aminoAcids = {"F", "F", "L", "L", "L", "L", "L", "L", "I", "I", "I", "M", "V", "V", "V", "V", "S", "S",
        "S", "S", "P", "P", "P", "P", "T", "T", "T", "T", "A", "A", "A", "A", "Y", "Y", "*", "*", "H", "H", "Q", "Q", "N", "N", "K", "K", "D", "D",
        "E", "E", "C", "C", "*", "W", "R", "R", "R", "R", "S", "S", "R", "R", "G", "G", "G", "G"};  // instance variable to hold aminoACids

    private final String filename = "DNA2.txt";                                     // the name of the file for the DNA
    private final String filenameR = "DNA2ReversedComp.txt";                        // the name of the file for the Reversed Complement DNA

//    private DNASequence[] theDNASequence;
    private ProteinSequence[] returnedProteins, returnedProteinsSimple;             // An array which holds the returned proteins for both the simple and Six frame Translations
    private String[] returnedAminos, returnedAminosSimple;                          // An array which holds a String array of the returned Aminos

    private String[] theCodons, theCodonsReversedComp;                              // An array to hold the codons for both front and reverse

    private String codonsAtFirstFrame, codonsAtSecondFrame, codonsAtThirdFrame, // A string to hold the codons at various six frames
            codonsAtFirstFrameReverse, codonsAtSecondFrameReverse,
            codonsAtThirdFrameReverse;

    private StringBuffer aminoAcidSequence, aminoAcidSequence2, aminoAcidSequence3, // A String buffer to hold the Amino Sequences at each frame
            aminoAcidSequence4, aminoAcidSequence5, aminoAcidSequence6;

    /**
     * initiates and fills the hashMap with the String of codons and Amino Acids
     */
    public static void initiateHashMap() {
        for (int c = 0; c < codons.length; c++) {
            translation.put(codons[c], aminoAcids[c]);
        }
    }

    /**
     * Constructor for Ribosome Class Initiate instance variables
     */
    public Ribosome() {
        this.returnedProteins = new ProteinSequence[6];                          // initiate instance variable for returned Proteins for the Six frames
        this.returnedAminos = new String[6];                                     // initiate instance variable for returned Aminos 
        this.returnedAminosSimple = new String[2];                               // initiate intsanve variable for returnedAminos for the Simple translation
        this.returnedProteinsSimple = new ProteinSequence[2];                    // initiate instance variable for the reurned proteins in a ProteinSequence format for the simple translation
    }

    /**
     * Translates a DNASequence based on a simple translation based on first
     * frames for a DNA and its reverse complement
     *
     * @param dna the DNASequence
     *
     * @throws InvalidSequenceException
     * @throws IOException the IOException error found
     * @return ProteinSequence[]
     */
    public ProteinSequence[] translate(DNASequence dna) throws InvalidSequenceException, IOException {
        initiateHashMap();

        //Simple Translation Forward
        dna.writeToFile(filename);

        DNASequence.makeSequence(filename);
        String content = dna.getContent();
        String description = dna.getDescription();

        StringBuffer aminoAcidSequenceF = new StringBuffer();
        int i = 0;
        while (i < content.length() - 2) {
            aminoAcidSequenceF.append(translation.get(content.substring(i, i + 3)));        // uses hashmap for translation
            i += 3;
        }
//        System.out.println("Simple Translation on Both Forward and Reverse Strand: ");
//        System.out.println("Amino acid 5'3' Frame 1: " + aminoAcidSequenceF);

        //Simple Translation Reverse
        DNASequence revComp = dna.revComp();
        revComp.writeToFile(filenameR);
        DNASequence makeSequence = DNASequence.makeSequence(filenameR);
        String contentOfReVComp = revComp.getContent();
        String descriptionOfRevComp = revComp.getDescription();

        StringBuffer aminoAcidSequenceR = new StringBuffer();
        int j = 0;
        while (j < contentOfReVComp.length() - 2) {
            aminoAcidSequenceR.append(translation.get(contentOfReVComp.substring(j, j + 3)));   // Uses hashmap for translation
            j += 3;
        }
//        System.out.println("Amino acid 3'5' Frame 1: " + aminoAcidSequenceR);
//        System.out.println("");
        returnedAminosSimple[0] = aminoAcidSequenceF.toString();
        returnedAminosSimple[1] = aminoAcidSequenceR.toString();

        for (int p = 0; p < returnedAminosSimple.length; p++) {
            returnedProteinsSimple[p] = new ProteinSequence("Simple Translation", returnedAminosSimple[p]);
        }
//        System.out.println("Returning Protein Sequence of Simple Translation in array form \n" + Arrays.toString(returnedProteinsSimple));
       
        return returnedProteinsSimple;
    }

    /**
     *
     * @param dna the DNASequence
     *
     * @throws InvalidSequenceException the InvalidSequence Exception for a
     * Sequence encountered
     * @throws IOException the IOException error found
     * @return the ProteinSequence[]
     */
    public ProteinSequence[] translateSix(DNASequence dna) throws InvalidSequenceException, IOException {
        initiateHashMap();

        try {

            dna.writeToFile(filename);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String content = DNASequence.getContent(filename);
        String contentReversedComp = DNASequence.getContent(filenameR);

        theCodons = new String[content.length()];
        theCodonsReversedComp = new String[contentReversedComp.length()];

        DNASequence dnaR = new DNASequence(DNASequence.getDescription(filename), DNASequence.getContent(filename));

        DNASequence dnaReverseComplement = dnaR.revComp();

        dnaReverseComplement.writeToFile(filenameR);

        int start = 0;
        if (start > -1) {
            for (int i = start; i < start + 3; i++) {

                theCodons[i] = content.substring(i);
            }
        }

        codonsAtFirstFrame = theCodons[0];
        codonsAtSecondFrame = theCodons[1];
        codonsAtThirdFrame = theCodons[2];

//        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        aminoAcidSequence = new StringBuffer();
        int i = 0;
        while (i < codonsAtFirstFrame.length() - 2) {
            aminoAcidSequence.append(translation.get(codonsAtFirstFrame.substring(i, i + 3)));
            i += 3;
        }
//        System.out.println("Amino acid 5'3' Frame 1: " + aminoAcidSequence);

        aminoAcidSequence2 = new StringBuffer();
        int j = 0;
        while (j < codonsAtSecondFrame.length() - 2) {
            aminoAcidSequence2.append(translation.get(codonsAtSecondFrame.substring(j, j + 3)));
            j += 3;
        }
//        System.out.println("Amino acid 5'3' Frame 2: " + aminoAcidSequence2);

        aminoAcidSequence3 = new StringBuffer();
        int k = 0;
        while (k < codonsAtThirdFrame.length() - 2) {
            aminoAcidSequence3.append(translation.get(codonsAtThirdFrame.substring(k, k + 3)));
            k += 3;
        }
//        System.out.println("Amino acid 5'3' Frame 3: " + aminoAcidSequence3);

        getFramesReverse();                              //Get the translation from the reversed Strand
        getAminoChain();

        for (int p = 0; p < returnedAminos.length; p++) {
            returnedProteins[p] = new ProteinSequence("6 Frames", returnedAminos[p]);

        }

//        System.out.println("**************************************************************************************************************************************************************************************************************");
//        System.out.println("Summary of the Proteins  in Array Form \n" + Arrays.toString(returnedProteins));

        return returnedProteins;

    }

    /**
     * Returns the translation from the reversed Strand
     *
     * @throws IOException
     * @return th Amino Sequences
     */
    public String getFramesReverse() throws IOException {
        String contentReversedComp = DNASequence.getContent(filenameR);
        int startR = 0;
        if (startR > -1) {
            for (int m = startR; m < startR + 3; m++) {

                theCodonsReversedComp[m] = contentReversedComp.substring(m);
            }
        }
        //System.out.println(Arrays.toString(theCodonsReversedComp));

        codonsAtFirstFrameReverse = theCodonsReversedComp[0];
        codonsAtSecondFrameReverse = theCodonsReversedComp[1];
        codonsAtThirdFrameReverse = theCodonsReversedComp[2];

        //
        aminoAcidSequence4 = new StringBuffer();
        int p = 0;
        while (p < codonsAtFirstFrameReverse.length() - 2) {
            aminoAcidSequence4.append(translation.get(codonsAtFirstFrameReverse.substring(p, p + 3)));
            p += 3;
        }
//        System.out.println("Amino acid 3'5' Frame 1: " + aminoAcidSequence4);

        aminoAcidSequence5 = new StringBuffer();
        int q = 0;
        while (q < codonsAtSecondFrameReverse.length() - 2) {
            aminoAcidSequence5.append(translation.get(codonsAtSecondFrameReverse.substring(q, q + 3)));
            q += 3;
        }
        System.out.println("Amino acid 3'5' Frame 2: " + aminoAcidSequence5);

        aminoAcidSequence6 = new StringBuffer();
        int x = 0;
        while (x < codonsAtThirdFrameReverse.length() - 2) {
            aminoAcidSequence6.append(translation.get(codonsAtThirdFrameReverse.substring(x, x + 3)));
            x += 3;
        }
//        System.out.println("Amino acid 3'5' Frame 3: " + aminoAcidSequence6);

        return ("The values obtained from the various frames are" + aminoAcidSequence4 + aminoAcidSequence5 + aminoAcidSequence6);

    }

    /**
     * Converts the AminoAcidSequence to a String to be used to construct a
     * ProteinSequence[]
     *
     * @return the returnedAminos
     */
    public String[] getAminoChain() {

        returnedAminos[0] = aminoAcidSequence.toString();
        returnedAminos[1] = aminoAcidSequence2.toString();
        returnedAminos[2] = aminoAcidSequence3.toString();
        returnedAminos[3] = aminoAcidSequence4.toString();
        returnedAminos[4] = aminoAcidSequence5.toString();
        returnedAminos[5] = aminoAcidSequence6.toString();

        return returnedAminos;
    }
    
    
    public String[] getProteinSequenceContentSimpleTranslation(){
//        String[] proteinSequence = null;
//        for (int k = 0; k< returnedAminos.length; k ++)
//            proteinSequence[k]= returnedAminos[k].c;
        
        return returnedAminosSimple;
    
    
    }
    
    public String[] getProteinSequenceContentSixFrame(){
        return returnedAminos;
    
    }

    /**
     * Test Class for Ribosome Class
     *
     * @param args the command line arguments
     * @throws IOException the Input Output Exception encountered
     * @throws InvalidSequenceException the Invalid Sequence for a sequence
     */
    public static void main(String[] args) throws IOException, InvalidSequenceException {
        Ribosome ribosome = new Ribosome();

        DNASequence dna1 = new DNASequence("Myoglobin gene from sperm whale", "CTGATGCTGAGCCTGCTAGGTTGAGAAATATGGCGGTACTGAAATTGCATGGTAGCTGTATGCTGCCATGATAAAAATGCTGCTGACATGTGACGGTGTTAACAGTACGTACTTGGCCAAG");

        try {
            ribosome.translateSix(dna1);
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }

        DNASequence dna2 = dna1.revComp();

    }

}
