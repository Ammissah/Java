import java.io.*;
/**
 * Program to merge a bunch of text files into one,
 * with blatant separators so that splitting them up again is easy.
 * The filenames - any number - are given on the command line
 * e.g. java Merge README.txt *.java
 * and the result is in a file called merged.files
 * (Allowing the output file to be specified on the command line could
 * lead to unfortunate accidents).
 * Tabs are replaces by just two spaces, to avoid overly long lines.
 */

public class Merge {
    
    private static final String MARKER = "###";
    
    private static String startFile(String fileName) {
        return MARKER + fileName + "\n";
    }
    
    // Replace tabs by just two spaces, to minimise problems caused
    // by overly long lines.
    private static String detab(String s) {
        String result = "";
        char ch;
        for (int i=0; i < s.length(); i++) {
            if ((ch=s.charAt(i)) == '\t') {
                result += "  ";
            }
            else {
                result += ch;
            }
        }
        return result;
    }

    private static void copyFile(BufferedReader input,  BufferedWriter output)
        throws IOException {
            
        String line = null;
        while((line = input.readLine()) != null) {
            output.write(detab(line) + "\n");
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedWriter output = null;
        BufferedReader input = null;
        try {
            FileWriter mergedFiles = new FileWriter("merged_files.txt");
            output = new BufferedWriter(mergedFiles);
            for (int i=0; i<args.length; i++) {
                FileReader fileIn = new FileReader(args[i]);
                input = new BufferedReader(fileIn);
                output.write(startFile(args[i]));
                copyFile(input, output);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        finally {
            if (output != null) {
                output.close();
            }
            if (input != null)
                input.close();
        }
    }
}
