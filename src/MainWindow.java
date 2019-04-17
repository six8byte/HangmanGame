import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

//TODO: CLICKS ON SAME CORRECT LETTER RESULTS IN SCORE INCREMENTATION
//TODO: WORD LETTERS ARE NOT DELETED FROM VIEW BEFORE ADDING NEW ONES

@SuppressWarnings("ALL")
public class MainWindow extends JFrame {

    private Word word;
    private ArrayList<JButton> wordButtons;
    private JLabel paintingLabel;
    private int lettersGuessed = 0;
    private int badGuesses = 0;

    private MainWindow() throws IOException {

        initUI();
    }

    private void initUI() throws IOException {
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
        for (JButton wordButton : wordButtons) {
            add(wordButton);
        }

        ArrayList<JButton> alphabet = setAlphabet();
        for (JButton button : alphabet) {
            add(button);
        }

    }

    private void setImage() {
        String filename = "r" + badGuesses + ".png";
        Image image = new ImageIcon(this.getClass().getResource(filename)).getImage();
        Image newImage = image.getScaledInstance(500, 600, Image.SCALE_DEFAULT);
        paintingLabel.setIcon(new ImageIcon(newImage));
    }

    private ArrayList<JButton> setWordButtons() throws IOException {
        word = new Word();
        char[] letters = word.getLettersPackage();
        ArrayList<JButton> buttons = new ArrayList<JButton>(letters.length);
        for (int i = 0; i < letters.length; i++) {
            var button = new JButton();
            button.setName(Character.toString(letters[i]));
            button.setSize(30, 30);
            button.setMargin(new Insets(0, 0, 0, 0));
            buttons.add(button);
        }
        positionButtons(buttons, 700);
        return buttons;
    }

    private void positionButtons(ArrayList<JButton> buttons, int y) {
        var xes = calculateX(buttons.size());
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation(xes[i], y);
        }
    }

    private void positionButtons(ArrayList<JButton> buttons, int y, int rows) {
        var xes = calculateX(buttons.size(), rows);
        int placeToSplit = buttons.size() / rows;
        int rowsSpacing = 50;
        for (int i = 0; i < buttons.size(); i++) {
            if (i % placeToSplit == 0) {
                y += rowsSpacing;
            }
            buttons.get(i).setLocation(xes[i], y);
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

    private ArrayList<JButton> setAlphabet() {


        ActionListener listener = (ActionEvent e) -> {
            final JButton source = (JButton) e.getSource();

            int tmpLettersGuessed = lettersGuessed;
            for (int i = 0; i < wordButtons.size(); i++) {
                if (source.getText().equals(wordButtons.get(i).getName())) {
                    wordButtons.get(i).setText(wordButtons.get(i).getName());
                    lettersGuessed++;
                }
            }
            if (tmpLettersGuessed == lettersGuessed) {
                badGuesses++;
            }

            if (badGuesses > 11) {
                try {
                    cleanUp();
                    initUI();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "GAME OVER");

            } else {
                setImage();
            }
            if (lettersGuessed == wordButtons.size()) {
                try {
                    JOptionPane.showMessageDialog(null, "YOU WON! CONGRATS!");
                    cleanUp();
                    initUI();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        ArrayList<JButton> buttons;
        buttons = new ArrayList<JButton>(91 - 65);

        for (int i = 65; i < 91; i++) {
            var button = new JButton();
            button.setText(Character.toString(i));
            button.setSize(30, 30);
            button.setMargin(new Insets(0, 0, 0, 0));
            button.addActionListener(listener);
            buttons.add(button);
        }

        positionButtons(buttons, 800, 2);
        return buttons;
    }

    private void cleanUp() throws IOException {
        word = null;
        for(JButton button : wordButtons ){
            button.setVisible(false);
        }
        wordButtons.clear();
        lettersGuessed = 0;
        badGuesses = 0;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MainWindow exe = null;
            try {
                exe = new MainWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
            exe.setVisible(true);
        });

    }


}

