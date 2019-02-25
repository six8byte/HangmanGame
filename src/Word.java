import java.util.Random;

public class Word {

    private String word = "oscylator";
    private char[] letters;
    private char[] lettersPackage;


    public Word(){
        //word = lexicon.getRandomWord()
        splitToLetters(word);
    }

    private void splitToLetters(String word){
        letters = word.toCharArray();
    }

    public char[] getLettersPackage() { return letters; }
}
