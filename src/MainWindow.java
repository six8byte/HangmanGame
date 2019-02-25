import javax.swing.*;
import java.awt.*;
import java.io.File;


public class MainWindow extends JFrame {

    public MainWindow(){

        initUI();
        setButtons();
    }

    private void initUI(){
        //JPanel panel = (JPanel)getContentPane();
        //JLabel label = new JLabel();
        //label.setIcon(new ImageIcon("res/r11.png"));// your image here
        //panel.add(label);

        setTitle("Mężczyzna na szubienicy");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
    }

    private void setButtons(){
        Word word = new Word();
        char[] letters = word.getLettersPackage();
        for (int i = 0; i < 26; i++) {

        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            var exe = new MainWindow();
            exe.setVisible(true);
        });
    }



}
