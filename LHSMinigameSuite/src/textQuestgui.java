import javax.swing.*;
import java.awt.*;
import java.util.*;

public class textQuestgui extends JFrame {
    public JPanel panel = new JPanel();
    public JFrame frame = new JFrame("Minigame Suite: Text Quest");
    public JLabel questoutput = new JLabel("");
    public JTextField scannerinput = new JTextField("Type Here?");

    public textQuestgui() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textQuest.opening();
        textQuest.training();
        textQuest.forest1();
    }


    public static void main(String[] args) {SwingUtilities.invokeLater(textQuestgui::new);}
}