
import java.io.*;

/**
 * Sequence.java  represent the notion of a sequence in general.
 * This class also allows the Sequence to be written to file and read from
 * as well as providing validation to the Sequence being entered.
 */

public abstract class Sequence {

    private String description; // The Description of the Sequence
    private String content;     // The Content of the Sequence

    /**
     *
     * @param description the Sequence Description
     * @param content the Sequence content
     * @throws InvalidSequenceException the Exception for an invalid entered
     * Sequence
     */
    public Sequence(String description, String content) throws InvalidSequenceException {
        this.description = description;
        this.content = content;

        validate();

    }
    
    public Sequence(String description, byte content) throws InvalidSequenceException {
        this.description = description;
        byte content2 = content;

        validate();

    }
    

    /**
     * Gets the Description of the Sequence
     *
     * @return the Description of the Sequence
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the Content of the Sequence
     *
     * @return the Content of the Sequence
     */
    public String getContent() {
        return this.content;

    }

    /**
     *
     * @return
     */
    public abstract String validLetters();

    /**
     *
     * @throws InvalidSequenceException
     */
    public void validate() throws InvalidSequenceException {

        String sequenceContent = getContent();
        String theseletters = this.validLetters();

        for (int i = 0; i < sequenceContent.length(); i++) {
            char j = sequenceContent.charAt(i);
            if (theseletters.indexOf(j) == -1) {
                throw new InvalidSequenceException(sequenceContent.substring(0, i + 1), i + 1);

            }
        }
    }

    /**
     * Writes the Sequence to a file
     *
     * @param filename the filename for the Sequence
     * @throws IOException the IOxception encountered
     */
    public void writeToFile(String filename) throws IOException {

        FileWriter fileWriter = null;

        try {
            File fileWriterFile = new File(filename);
            fileWriter = new FileWriter(fileWriterFile);

            fileWriter.write(toString());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    /**
     * Gets Sequence Description from filename
     *
     * @param filename the filename of the sequence
     * @return the line of sequences read
     * @throws FileNotFoundException the File Not Found Exception
     * @throws IOException the IOException encountered
     */
    public static String getDescription(String filename) throws FileNotFoundException, IOException {

        String line = null;

        FileReader fileReader = new FileReader(filename);                        //create filereader object

        BufferedReader bufferedReader = new BufferedReader(fileReader);          // create buffer reader object

        line = bufferedReader.readLine();                                       // Read the first line of the file

        if (line == null) {
            throw new IOException(filename + " is an empty file");              // Checks if file is an empty file
        }

        if (line.charAt(0) != '>') {
            throw new IOException("First line of " + filename // Checks if file is a valid FASTA format
                    + " should start with '>'");
        }

        return line.trim();                                                      // return the line read eliminating leading and trailing spaces.
    }

    /**
     * Gets the Content of the Sequence from the file
     *
     * @param filename the filename of the sequence
     * @return the content of the sequence
     * @throws FileNotFoundException the File Not Found Exception Error
     * @throws IOException the IOEXception Error
     */
    public static String getContent(String filename) throws FileNotFoundException, IOException {
        String line = null;

        FileReader fileReader = new FileReader(filename);

        StringBuilder sb;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            line = bufferedReader.readLine();
            if (line == null) {
                throw new IOException(filename + " is an empty file");
            }
            if (line.charAt(0) != '>') {
                throw new IOException("First line of " + filename
                        + " should start with '>'");      //                     // Checks if file is a valid FASTA format
            }
            line = bufferedReader.readLine();
            sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());                               // Appends a new platform independent line to StringBuffer                   
                line = bufferedReader.readLine();
            }
        }
        return sb.toString().trim();                                             // return the line read eliminating leading and trailing spaces.

    }

    /**
     * Gets the Description and Content for a Sequence
     *
     * @return Description and Content
     */
    public String toString() {
        return (">" + this.getDescription() + System.lineSeparator() + this.getContent());

    }

}
