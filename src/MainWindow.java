import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MainWindow extends JFrame {

    private Word word;
    private JButton[] wordButtons;
    private JButton[] alphabet;
    private ArrayList<JButton> guessedButtons;
    private JLabel paintingLabel;
    private int lettersGuessed = 0;
    private int badGuesses = 0;

    private MainWindow() {

        initUI();
    }

    private void initUI() {
        paintingLabel = new JLabel("");
        paintingLabel.setBounds(250, 10, 500, 600);
        setImage();
        getContentPane().add(paintingLabel);


        setTitle("Mężczyzna na szubienicy");
        setSize(1000, 1000);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        wordButtons = setWordButtons();
        for (int i = 0; i < wordButtons.length; i++) {
            add(wordButtons[i]);
        }

        alphabet = setAlphabet();
        for (int i = 0; i < alphabet.length; i++) {
            add(alphabet[i]);
        }

    }

    private void setImage() {
        String filename = "r" + badGuesses + ".png";
        Image image = new ImageIcon(this.getClass().getResource(filename)).getImage();
        Image newImage = image.getScaledInstance(500, 600, Image.SCALE_DEFAULT);
        paintingLabel.setIcon(new ImageIcon(newImage));
    }

    private JButton[] setWordButtons() {
        word = new Word();
        char[] letters = word.getLettersPackage();
        JButton[] buttons = new JButton[letters.length];
        for (int i = 0; i < letters.length; i++) {
            var button = new JButton();
            button.setName(Character.toString(letters[i]));
            button.setSize(30, 30);
            button.setMargin(new Insets(0, 0, 0, 0));
            buttons[i] = button;
        }
        positionButtons(buttons, 700);
        return buttons;
    }

    private void positionButtons(JButton[] buttons, int y) {
        var xes = calculateX(buttons.length);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setLocation(xes[i], y);
        }
    }

    private void positionButtons(JButton[] buttons, int y, int rows) {
        var xes = calculateX(buttons.length, rows);
        int placeToSplit = buttons.length / rows;
        int rowsSpacing = 50;
        for (int i = 0; i < buttons.length; i++) {
            if (i % placeToSplit == 0) {
                y += 50;
            }
            buttons[i].setLocation(xes[i], y);
        }
    }

    private int[] calculateX(int count) {
        int[] xes = new int[count];
        int shift = 900 / count;
        for (int i = 1; i < count + 1; i++) {
            xes[i - 1] = i * shift;
        }
        return xes;
    }

    private int[] calculateX(int count, int rows) {
        int[] xes = new int[count];
        int shift = 900 / count * rows;
        int splitter = count / rows;
        for (int i = 0; i < rows; i++) {
            for (int x = 1; x <= splitter; x++) {
                xes[x - 1 + i * splitter] = x * shift;
            }
        }
        return xes;
    }

    private JButton[] setAlphabet() {


        ActionListener listener = (ActionEvent e) -> {
            final JButton source = (JButton) e.getSource();

            int tmpLettersGuessed = lettersGuessed;
            for (int i = 0; i < wordButtons.length; i++) {
                if (source.getText().equals(wordButtons[i].getName())) {
                    wordButtons[i].setText(wordButtons[i].getName());
                    lettersGuessed++;
                }
            }
            if (tmpLettersGuessed == lettersGuessed) {
                badGuesses++;
            }

            if (badGuesses > 11) {
                resetCounters();
                setImage();
                for (JButton x : wordButtons) {
                    x.setText("");
                }
                word.generateNewWord();
                wordButtons = setWordButtons();
                JOptionPane.showMessageDialog(null, "GAME OVER");

            } else {
                setImage();
            }
            if (lettersGuessed == wordButtons.length + 1) {
                resetCounters();
                setImage();
                for (JButton x : wordButtons) {
                    x.setText("");
                }
                word.generateNewWord();
                wordButtons = setWordButtons();
                JOptionPane.showMessageDialog(null, "YOU WON! CONGRATS!");
            }
        };

        JButton[] buttons = new JButton[91 - 65];

        for (int i = 65; i < 91; i++) {
            var button = new JButton();
            button.setText(Character.toString(i));
            button.setSize(30, 30);
            button.setMargin(new Insets(0, 0, 0, 0));
            button.addActionListener(listener);
            buttons[i - 65] = button;
        }

        positionButtons(buttons, 800, 2);
        return buttons;
    }

    private void resetCounters() {
        badGuesses = 0;
        lettersGuessed = 0;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            var exe = new MainWindow();
            exe.setVisible(true);
        });

    }


}
