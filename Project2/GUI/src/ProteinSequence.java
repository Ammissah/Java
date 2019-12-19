
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * ProteinSequence.java represents a concrete class of Sequence.
 * This class gets the ThreeLetterCode and full name of an Amino Acid, class also validate a protein sequence entered
 * This class also gets the content and description of a Protein Sequence from a file
 */


public class ProteinSequence extends Sequence {

    private String validProteinLetters;                                                  //instance variable for valisProteinLetters
    private static final Map<String, String> aminoAcidInformation = new HashMap<>();     // instance variable of a Map to hold AminoAcids and ints information
    

    private static final String[] threeLetterAminoAcidCode = {"Gly", "Ala", "Leu", "Met", "Phe", "Trp", "Lys", "Ser", "Asn", "Asp", "Pro",
        "Val", "Ile", "Cys", "Tyr", "His", "Arg", "Thr", "Gln", "Glu"};                  // instance variable string array to hold the three letter code for Amino Acids

    private static final String[] fullAminoAcidName = {"Glycine", "Alanine", "Leucine", "Methionine", "Phenylalanine", "Tryptophan", "Lysine",
        "Serine", "Asparagine", "Aspartic acid", "Proline", "Valine", "Isoleucine", "Cystine", "Tyrosine", "Histidine", "Arginine", "Threonine",
        "Glutamine", "Glutamic acid"};                                                  //  instance variable string array to hold the full names for Amino Acids

    private static String filename ="protein.txt";                                      // the instance variable for the filename

    /**
     * Constructor for protein sequence
     * @param description the description of the Protein Sequence
     * @param content     the content of the protein sequence
     * @throws InvalidSequenceException  the invalid sequence exception for an invalid protein sequence encountered
     */
    public ProteinSequence(String description, String content) throws InvalidSequenceException {
        super(description, content);    //explicit call to super Class Sequence
        try {
            writeToFile(filename);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    /**
     * Checks the valid protein letters
     * @return the valid protein letters
     */
    @Override
    public String validLetters() {
        return validProteinLetters = "GALMFWKSNDPVICYHRTQE*";
    }

    /**
     * Initialises and fills the Map
     * @return the map filled
     */
    private static void initializeMap() {
        for (int m = 0; m < fullAminoAcidName.length; m++) {
            aminoAcidInformation.put(threeLetterAminoAcidCode[m], fullAminoAcidName[m]);

        }

    }

    /**
     * Gets three letter Code for an Amino Acid
     * @param fullName the fullName of the Amino Acid
     * @return threeLetterCode the threeLetterCode
     */
    public static String getThreeLetterCode(String fullName) {
        initializeMap();
        String threeLetterCode = null;
        for (Map.Entry<String, String> entry : aminoAcidInformation.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (fullName.equals(value)) {
                threeLetterCode = key;
            }
        }

        return threeLetterCode;
    }

    /**
     * Gets the full name of an Amino Acid
     * @param threeLetterCode the threeLeterCode
     * @return the fullName
     */
    public static String getFullName(String threeLetterCode) {
        initializeMap();
        String fullName = null;
        for (Map.Entry<String, String> entry : aminoAcidInformation.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (threeLetterCode.equals(key)) {
                fullName = value;
                
            }
        }
        return fullName;
    } 

    /**
     * Makes a Sequence from a file using its description and content
     * @param filename the filename of the Sequence
     * 
     * @throws IOException the IOException encountered
     * @throws InvalidSequenceException the Invalid Protein Sequence encountered
     * @return protein1 the ProteinSequence
     */
    public static ProteinSequence makeSequence(String filename) throws IOException, InvalidSequenceException {

        ProteinSequence protein1 = new ProteinSequence(Sequence.getDescription(filename), Sequence.getContent(filename));

        return protein1;

    }

    /**
     * Test class for ProteinSequence
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            ProteinSequence proteinSequence = new ProteinSequence("Drosophila melanogaster Epidermal growth factor receptor", "MLLRRRNGPCPFPLLLLLLAHCICIWPASAARDRYARQNNRQRHQDIDRDRDRDRFLYRS");
            System.out.println(proteinSequence.toString());
            System.out.println(getFullName("Ala"));
            System.out.println(getThreeLetterCode("Leucine"));
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            ProteinSequence proteinSequence2 = new ProteinSequence("Mus musculus SOX9_MOUSE Transcription factor SOX-9", "MNLLDPFMKMTDEQEXKGLSGAPSPTMSEDSAGSPCPSGSGSDTENTRPQENTFPKGEPDL");
            System.out.println(proteinSequence2.toString());
        } catch (InvalidSequenceException ex) {

            System.out.println(ex.getMessage());
        }
       
    }
}
