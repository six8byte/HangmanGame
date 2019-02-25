import javax.swing.*;
import java.awt.*;

public class Drawing {

    private ImageIcon[] allImages;
    private int imageCounter = 1;

    public void Drawing(){
        for (int image = 1; image < 12; image++) {
            String imgId;
            imgId = String.format("res/r%d.png", image);
            ImageIcon img = new ImageIcon(imgId);
            allImages[image] = img;
        }
    }

    public void incrementScore(){
        if (imageCounter<12){
            imageCounter++;
        }
        else {
            imageCounter = 1; //reset score
        }
    }

    private ImageIcon resizeImage(ImageIcon img){
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }

    public ImageIcon getNextImage(){
       var nextImg = allImages[imageCounter];
       nextImg = resizeImage(nextImg);
       return nextImg;
    }
}
