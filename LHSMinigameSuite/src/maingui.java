
import javax.swing.*;
import java.awt.*;

public class maingui { // main class, where everything starts (hopefully)
    public JPanel panel = new JPanel();
    public JFrame frame = new JFrame("Minigame Suite: Main Menu");
    private JButton game1 = makebutton("<HTML>Whack-A-Mole</HTML>", 100, 100, 100, 100, 0);
    private JButton game2 = makebutton("<HTML>Tic Tac Toe</HTML>", 100, 100, 100, 100, 100);
    private JButton game3 = makebutton("<HTML>Text Quest (Requires Console)</HTML>", 100, 100, 100, 100, 200);

    public maingui() { // ran as a sort of setup method, sets sizes, alignment, visibility of swing objects
        game1.setHorizontalAlignment(SwingConstants.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(512,512);
        frame.setResizable(false);
        panel.setLayout(null);
        JLabel menu1 = new JLabel("Select a game!");
        JLabel menu2 = new JLabel("<HTML>Whack-A-Mole by Jack Bellingham <br/>Text Quest by Nick Roth<br/>Tic Tac Toe by Kliment Kolev<br/>Main Menu is a combined effort :)</HTML>");
        menu1.setForeground(Color.WHITE);
        menu1.setSize(300, 100);
        menu1.setLocation(100, 0);
        menu1.setHorizontalAlignment(SwingConstants.CENTER);
        menu1.setVerticalAlignment(SwingConstants.CENTER);
        menu2.setForeground(Color.WHITE);
        menu2.setSize(300, 200);
        menu2.setLocation(100, 250);
        menu2.setHorizontalAlignment(SwingConstants.CENTER);
        menu2.setVerticalAlignment(SwingConstants.TOP);
        game1.addActionListener(gameact1 -> {
            gameaction(game1, true, "Loading Game 1!");
            SwingUtilities.invokeLater(WhackAMole::new);
        });
        game2.addActionListener(gameact2 -> {
            gameaction(game2, true, "Loading Game 2!");
            SwingUtilities.invokeLater(TicTacToe::new);
        });
        game3.addActionListener(gameact3 -> {
            gameaction(game2, false, "<HTML>Loading Game 3! Use the Console.</HTML>");
            textQuest.opening();
            textQuest.training();
            textQuest.forest1();
        });
        panel.add(menu1);
        panel.add(menu2);
        panel.add(game1); panel.add(game2); panel.add(game3);
        frame.add(panel);
        frame.setBackground(Color.DARK_GRAY);
        panel.setBackground(Color.DARK_GRAY);
        panel.setVisible(true);
        frame.setVisible(true);
    }
    static JButton makebutton(String contents, int posx, int posy, int sizex, int sizey, int sep) { // makes buttons
         JButton button = new JButton(contents);
         button.setSize(sizex, sizey);
         button.setLocation(posx+sep, posy);
         return button;
    }
    private void gameaction(JButton button, boolean type, String text)  { // a sort of simplified action method
        int xcoord = button.getX();
        int ycoord = button.getY();
        int xscale = button.getWidth();
        int yscale = button.getHeight();
        JLabel label1 = new JLabel(text);
        panel.add(label1);
        label1.setLocation(xcoord,ycoord);
        label1.setSize(xscale,yscale);
        frame.setVisible(true);
        panel.setVisible(true);
        label1.setVisible(true);
        game1.setVisible(false);
        game2.setVisible(false);
        game3.setVisible(false);
        label1.setForeground(Color.WHITE);
        if (type) {
            frame.dispose();
        }
    }

    public static void main(String[] args) {SwingUtilities.invokeLater(maingui::new);} // it just works
}