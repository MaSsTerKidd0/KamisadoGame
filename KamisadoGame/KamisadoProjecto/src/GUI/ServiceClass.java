package GUI;

import javax.swing.*;
import java.awt.*;

public class ServiceClass {

    //black images player
    private static ImageIcon orangetowerB = new ImageIcon("KamisadoProjecto/Images/Black/orangetowerB.png");
    private static ImageIcon redtowerB =new ImageIcon("KamisadoProjecto/Images/Black/redtowerB.png");
    private static ImageIcon greentowerB =new ImageIcon("KamisadoProjecto/Images/Black/greentowerB.png");
    private static ImageIcon pinktowerB =new ImageIcon("KamisadoProjecto/Images/Black/pinktowerB.png");
    private static ImageIcon yellowtowerB =new ImageIcon("KamisadoProjecto/Images/Black/yellowtowerB.png");
    private static ImageIcon bluetowerB =new ImageIcon("KamisadoProjecto/Images/Black/bluetowerB.png");
    private static ImageIcon purpletowerB =new ImageIcon("KamisadoProjecto/Images/Black/purpletowerB.png");
    private static ImageIcon browntowerB =new ImageIcon("KamisadoProjecto/Images/Black/browntowerB.png");

    //white images player
    private static ImageIcon orangetowerW = new ImageIcon( "KamisadoProjecto/Images/White/orangetowerW.png");
    private static ImageIcon redtowerW = new ImageIcon("KamisadoProjecto/Images/White/redtowerW.png");
    private static ImageIcon greentowerW = new ImageIcon("KamisadoProjecto/Images/White/greentowerW.png");
    private static ImageIcon pinktowerW = new ImageIcon("KamisadoProjecto/Images/White/pinktowerW.png");
    private static ImageIcon yellowtowerW = new ImageIcon("KamisadoProjecto/Images/White/yellowtowerW.png");
    private static ImageIcon bluetowerW = new ImageIcon("KamisadoProjecto/Images/White/bluetowerW.png");
    private static ImageIcon purpletowerW = new ImageIcon("KamisadoProjecto/Images/White/purpletowerW.png");
    private static ImageIcon browntowerW = new ImageIcon("KamisadoProjecto/Images/White/browntowerW.png");

    public static ImageIcon getOrangetowerB() {
        orangetowerB=resize(orangetowerB);
        return orangetowerB;
    }

    public static ImageIcon getRedtowerB() {
        redtowerB = resize(redtowerB);
        return redtowerB;
    }

    public static ImageIcon getGreentowerB() {
        greentowerB = resize(greentowerB);
        return greentowerB;
    }

    public static ImageIcon getPinktowerB() {
        pinktowerB = resize(pinktowerB);
        return pinktowerB;
    }

    public static ImageIcon getYellowtowerB() {
        yellowtowerB = resize(yellowtowerB);
        return yellowtowerB;
    }

    public static ImageIcon getBluetowerB() {
        bluetowerB = resize(bluetowerB);
        return bluetowerB;
    }

    public static ImageIcon getPurpletowerB() {
        purpletowerB = resize(purpletowerB);
        return purpletowerB;
    }

    public static ImageIcon getBrowntowerB() {
        browntowerB = resize(browntowerB);
        return browntowerB;
    }

    public static ImageIcon getOrangetowerW() {
        orangetowerW = resize(orangetowerW);
        return orangetowerW;
    }

    public static ImageIcon getRedtowerW() {
        redtowerW = resize(redtowerW);
        return redtowerW;
    }

    public static ImageIcon getGreentowerW() {
        greentowerW = resize(greentowerW);
        return greentowerW;
    }

    public static ImageIcon getPinktowerW() {
        pinktowerW = resize(pinktowerW);
        return pinktowerW;
    }

    public static ImageIcon getYellowtowerW() {
        yellowtowerW = resize(yellowtowerW);
        return yellowtowerW;
    }

    public static ImageIcon getBluetowerW() {
        bluetowerW = resize(bluetowerW);
        return bluetowerW;
    }

    public static ImageIcon getPurpletowerW() {
        purpletowerW = resize(purpletowerW);
        return purpletowerW;
    }

    public static ImageIcon getBrowntowerW() {
        browntowerW = resize(browntowerW);
        return browntowerW;
    }

    public static ImageIcon resize(ImageIcon imageIcon)
    {
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }

}
