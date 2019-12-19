
import java.io.IOException;

/**
 * DNAToProteinTester.java translates a DNA Sequence to Protein Sequence based on the Simple Translation and the Six Frame Translation
 * This class also perform miscellaneous test such as Finding Reverse Complement of a DNA, 
 * The Class also tests if a sequence is valid or not
 */

public class DNAToProteinTester {

    /**
     * Test Class
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DNASequence dna = null;
        try {
            dna = new DNASequence("Myoglobin gene from sperm whale", "CTGATGCTGAGCCTGCTAGGTTGAGAAATATGGCGGTACTGAAATTGCATGGTAGCTGTATGCTGCCATGATAAAAATGCTGCTGACATGTGACGGTGTTAACAGTACGTACTTGGCCAAG");
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            dna.writeToFile("DNATest.txt");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            DNASequence makeSequence = DNASequence.makeSequence("DNATest.txt");
            String toUpperCase = makeSequence.getContent().toUpperCase();
            System.out.println("The DNA Sequence is" + " " + toUpperCase );
            System.out.println("\n");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }
        String description = dna.getDescription();
        String content = dna.getContent();

        try {
            DNASequence revComp = dna.revComp();

            System.out.println("the Reverse Complement of the DNA is" + " " + revComp);
            System.out.println("\n");
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }

        Ribosome ribosome1 = new Ribosome();

        try {
            ribosome1.translate(dna);
        } catch (InvalidSequenceException ex) {
         System.out.println(ex.getMessage());
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        try {
            System.out.println("Below is a Translation on the 6 Frames: \n");
            ribosome1.translateSix(dna);
            System.out.println("\n");
        } catch (InvalidSequenceException | IOException ex) {
            System.out.println(ex.getMessage());
        }

        //ORF
        ProteinSequence p = null;
        try {
            p = new ProteinSequence("Protein Sequence from Sperm Whale Gene", "LMLSLLG*EIWRY*NCMVAVCCHDKNAADM*RC*QYVLGQ");
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            OpenReadingFrame.getORFs(p);
        } catch (IOException | InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("***********************************************************MISCELLANEOUS TESTING***********************************************************************************************************************************************");

        //DNA
        System.out.println("Testing Invalid Sequence Exception in the DNA Class \n");
        try {
            DNASequence DNA = new DNASequence("HSGLTH1 Human theta 1-globin gene", "GGCAGATTAZCCCCCT");
            System.out.println(DNA.revComp());

        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            DNASequence DNA2 = new DNASequence(" Homo sapiens chromosome 17, GRCh38.p7 Primary Assembly", "GTACCTTGATTTCGTATTCTGAGAGGCTGCTGCTTAGCGGTAGCCCCTTGGTTTCCGTGGCAACGGAAAAGCGCGGGAATTACAGATAAATTAAAACTGCGACTGCGCGGCGTGAGCTCGCTGAGACTTCCTGGACGGGG");
            System.out.println(DNA2.toString());
            System.out.println("");
            System.out.println("The Reverse Complement of this DNA is" + "  " + DNA2.revComp());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

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
//        System.out.println(d);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        //Protein
        try {
            ProteinSequence proteinSequence = new ProteinSequence("Drosophila melanogaster Epidermal growth factor receptor", "MLLRRRNGPCPFPLLLLLLAHCICIWPASAARDRYARQNNRQRHQDIDRDRDRDRFLYRS");
            System.out.println(proteinSequence.toString());
            System.out.println("Getting full name for 'Ala'" + ProteinSequence.getFullName("Ala"));
            System.out.println("Getting three Letter Code for 'Leucine'\n" + ProteinSequence.getThreeLetterCode("Leucine"));
        } catch (InvalidSequenceException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Testing Invalid Sequence Exception in the Protein Class \n");
        try {
            ProteinSequence proteinSequence2 = new ProteinSequence("Mus musculus SOX9_MOUSE Transcription factor SOX-9", "MNLLDPFMKMTDEQEXKGLSGAPSPTMSEDSAGSPCPSGSGSDTENTRPQENTFPKGEPDL");
            System.out.println(proteinSequence2.toString());
        } catch (InvalidSequenceException ex) {

            System.out.println(ex.getMessage());
        }

    }

}
