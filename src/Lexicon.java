import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Lexicon {

    private String word;
    private String path = "res/lexicon.txt";
    private String[] kontener;
    private String[] lines;

    public void Lexicon() throws FileNotFoundException {

        try {
            lines = readLines("res/words.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }

    public String getWord(){
        Random r = new Random();
        int line = r.nextInt(10);
        return lines[line];
    }

}
