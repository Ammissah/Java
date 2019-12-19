import java.io.*;

/**
 * Program to split up text files created with the Merge program.
 * The filenames - any number - are given on the command line
 * e.g. java Split *
 * Files which don't start with the marker string (or are unreadable etc.)
 * are ignored. For each file processed, a directory is created,
 * called <filename>DIR, and the individual files are placed in
 * that directory.
 */
public class Split {
    
    private static final String MARKER = "###";
    
    
    
    private static boolean isMarkerLine(String line) {
        return (line != null) && line.startsWith(MARKER);
    }
    
    private static String getFileName(String markerLine) {
        return markerLine.substring(3, markerLine.length());
    }
    
    // Write a file, returning the next marker line, or null
    // if the end of the merged file is reached.
    private static String  writeOneFile(String pathName, BufferedReader input)
        throws IOException {
        
        BufferedWriter output = null;
        String line = null;
        
        try {
            output = new BufferedWriter(new FileWriter(pathName));
            line = input.readLine(); // First line after the marker line
            while(line != null && (!isMarkerLine(line))) {
                output.write(line + "\n");
                line = input.readLine();
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
        finally {
            if (output != null) {
                output.close();
            }
        }
        return line;
    }
    
    
    
    private static void split(String fileName, String firstLine, BufferedReader input) {
        
        try {
            String dirName = fileName + "DIR";
            File dir = new File(dirName);
            dir.mkdir();
            String line = firstLine;
            while (isMarkerLine(line)) {
                String outfileName = getFileName(line);
                String fullPathName = dirName + File.separator + outfileName;
                line = writeOneFile(fullPathName, input);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader input = null;
        
        try {
            for (int i=0; i<args.length; i++) {
                FileReader mergedFileIn = new FileReader(args[i]);
                input = new BufferedReader(mergedFileIn);
                String line = input.readLine();
                
                // Skip initial lines containing e.g. mail headers.
                while (line != null && !isMarkerLine(line)) {
                    line = input.readLine();
                }
                if (isMarkerLine(line)) {
                    split(args[i],line,input);
                }
                else {
                    System.out.println("No marker line in " + args[i]);
                }
            }
        }
        
        catch (IOException e) {
            System.out.println(e);
        }
        finally {
            if (input != null) {
                input.close();
            }
        }
    }
}

