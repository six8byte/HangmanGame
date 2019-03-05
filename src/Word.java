import java.util.Random;

public class Word {

    private String word;
    private char[] letters;
    private Lexicon lexicon;


    public Word(){
        lexicon = new Lexicon();
        word = lexicon.getWord();
        splitToLetters(word);
    }

    private void splitToLetters(String word){
        letters = word.toCharArray();
    }

    public char[] getLettersPackage() { return letters; }

    public void generateNewWord(){
        word = lexicon.getWord();
        splitToLetters(word);
    }
}
