/**
 * InvalidSequenceException.java is a Checked Exception Class
 * which signals if  the sequence does not comply with the specification
 * either for a DNASequence or a ProteinSequence
 */

public class InvalidSequenceException extends /* Checked */ Exception {

    /**
     *
     * @param content The Sequence Content
     * @param index The index of the invalid sequence
     */
    public InvalidSequenceException(String content, int index) {
        super("Invalid sequence:" + content + " " + " at position" + " " + index);
    }

}
