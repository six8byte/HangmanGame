import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Lexicon {

    private String word;
    private String path = "res/lexicon.txt";
    private String[] kontener;

    public void Lexicon() throws FileNotFoundException {

        //Wczytywanie do kontenera na wątkach
        //Cachowanie po 10%
        //
        File f = new File(path);
        System.out.println(f.exists());
        int i = 0;
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            System.out.print(i);
            String line = scanner.nextLine();
            kontener[i] = line;
            i++;
        }

    }
    public void msg(){};

}
