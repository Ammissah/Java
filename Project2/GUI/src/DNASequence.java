
import java.io.IOException;

/**
 * DNASequence.java represents a concrete subclass of Sequence
 * DNASequence.java returns its own reverse complement
 * This class also gets the content and description of a DNASequence from a file
 */

public class DNASequence extends Sequence {

    private String validDNALetters;         //instance variable for validLetters

    private String filename = "DNA2.txt";   //instance variable for filename

    /**
     * Constructor for DNASequence
     *
     * @param description // The description of the DNA Sequence
     * @param content // The content of the DNA Sequence
     * @throws InvalidSequenceException // The invalid Sequence Exception for an
     * invalid Sequence
     */
    public DNASequence(String description, String content) throws InvalidSequenceException {
        super(description, content);//explicit call to super Class Sequence

        try {
            writeToFile(filename);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Concrete method which overrides the abstract method in Sequence
     *
     * @return a String of the Valid DNA Letters
     */
    @Override
    public String validLetters() {
        return validDNALetters = "ACGT";
    }

    /**
     * Gets the reverse complement of the DNA Sequence
     *
     * @throws InvalidSequenceException the Invalid Sequence EXception error for
     * a wrong DNA Sequence
     * @return the ReversedComplement
     */
    public DNASequence revComp() throws InvalidSequenceException {
        String DNASequence = this.getContent();

        String theComplement = "";
        for (int s = 0; s < DNASequence.length(); s++) {

            switch (DNASequence.charAt(s)) {
                case 'A':
                    theComplement += "T";
                    break;
                case 'C':
                    theComplement += "G";
                    break;
                case 'G':
                    theComplement += "C";
                    break;
                case 'T':
                    theComplement += "A";
                    break;

            }

        }
        String theReverseComplement = "";
        for (int r = theComplement.length() - 1; r >= 0; r--) {
            theReverseComplement += theComplement.charAt(r);
        }

        DNASequence ReversedComplement = new DNASequence(this.getDescription(), theReverseComplement);  // Create new DNA Sequence
        return ReversedComplement;
    }

    /**
     * makes a DNA Sequence from the filename
     *
     * @param filename the filename of the DNA Sequence
     *
     * @throws IOException the IOException Error encountered
     * @throws InvalidSequenceException InvalidSequence Error for an invalid DNA
     * Sequence encountered
     * @return the DNASequence
     */
    public static DNASequence makeSequence(String filename) throws IOException, InvalidSequenceException {

        DNASequence dna = new DNASequence(Sequence.getDescription(filename), Sequence.getContent(filename));

        return dna;

    }

    /**
     * Test class for DNASequence
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            DNASequence DNA = new DNASequence("HSGLTH1 Human theta 1-globin gene", "GGCAGATTAZCCCCCT");
            System.out.println(DNA.revComp());

        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            DNASequence DNA2 = new DNASequence("HSBGPG Human gene for bone gla protein (BGP)", "GGCAGATTCCCCCTAGACCCGCCCGCACCATGG");
            System.out.println(DNA2.toString());
            System.out.println(DNA2.revComp());
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }

        DNASequence d = null;
        try {
            d = DNASequence.makeSequence("DNA2.txt");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Checking make Sequence" + d);

    }

   

}
