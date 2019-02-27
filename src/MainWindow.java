import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    private String choosenLetter;

    public MainWindow(){

        initUI();
        setWordButtons();
    }

    private void initUI(){
        //JPanel panel = (JPanel)getContentPane();
        //JLabel label = new JLabel();
        //label.setIcon(new ImageIcon("res/r11.png"));
        //panel.add(label);

        Lexicon lex = new Lexicon();
        lex.msg();

        setTitle("Mężczyzna na szubienicy");
        setSize(1000, 1000);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        var wordButtons = setWordButtons();
        for (int i = 0; i < wordButtons.length; i++) {
            add(wordButtons[i]);
        }

        var alphabet = setAlphabet();
        for (int i = 0; i < alphabet.length; i++) {
            add(alphabet[i]);
        }
    }

    private JButton[] setWordButtons(){
        Word word = new Word();
        char[] letters = word.getLettersPackage();
        JButton[] buttons = new JButton[letters.length];
        for (int i = 0; i < letters.length; i++) {
            var button = new JButton();
            button.setName(Character.toString(letters[i]));
            button.setSize(20,20);
            button.setMargin(new Insets(0, 0, 0, 0));
            buttons[i] = button;
        }
        positionButtons(buttons, 700);
        return buttons;
    }

    private void positionButtons(JButton[] buttons, int y){
        var xes = calculateX(buttons.length);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setLocation(xes[i], y);
        }
    }

    private void positionButtons(JButton[] buttons, int y, int rows){
        var xes = calculateX(buttons.length, rows);
        int placeToSplit = buttons.length/rows;
        int rowsSpacing = 50;
        for (int i = 0; i < buttons.length; i++) {
            if (i%placeToSplit == 0) {
                y += 50;
            }
            buttons[i].setLocation(xes[i], y);
        }
    }

    private int[] calculateX(int count) {
        int[] xes = new int[count];
        int shift = 900/count;
        for (int i = 1; i < count+1; i++) {
            xes[i-1] = i*shift;
        }
        return xes;
    }

    private int[] calculateX(int count, int rows) {
        int[] xes = new int[count];
        int shift = 900/count*rows;
        int splitter = count/rows;
        for (int i = 0; i < rows; i++) {
            for (int x = 1; x <= splitter; x++) {
                xes[x - 1 + i*splitter] = x * shift;
            }
        }
        return xes;
    }

    private JButton[] setAlphabet() {

        JButton[] buttons = new JButton[91-65];

        for (int i = 65; i < 91; i++) {
            var button = new JButton();
            button.setText(Character.toString(i));
            button.setSize(30,30);
            button.setMargin(new Insets(0, 0, 0, 0));
            buttons[i-65] = button;
        }

        positionButtons(buttons, 800, 2);
        return buttons;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            var exe = new MainWindow();
            exe.setVisible(true);
        });
    }



}
