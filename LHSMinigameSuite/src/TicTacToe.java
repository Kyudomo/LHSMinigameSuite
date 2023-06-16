import jdk.jshell.JShellException;
import jdk.jshell.tool.JavaShellToolBuilder;

import javax.swing.*;
import java.util.Objects;
import java.awt.event.*;

public class TicTacToe extends maingui {
    public final ImageIcon imagething = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/board.png")));
    private static final JPanel panel = new JPanel();
    private static final JFrame frame = new JFrame("Minigame Suite: Tic Tac Toe");
    private static final JLabel menu1 = new JLabel("Tic Tac Toe");

    private final JLabel visualboard = new JLabel();
    private JLabel playerindic = new JLabel("Current Player: X");
    private boolean whichturn = true;
    private static final JButton startbutton = makebutton("<HTML>Press To Start!</HTML>", 200, 200, 100, 100, 0);

    private JButton bu00 = makebutton(" ", 100, 100, 60, 60, 0);
    private JButton bu01 = makebutton(" ", 200, 100, 60, 60, 0);
    private JButton bu02 = makebutton(" ", 300, 100, 60, 60, 0);
    private JButton bu10 = makebutton(" ", 100, 200, 60, 60, 0);
    private JButton bu11 = makebutton(" ", 200, 200, 60, 60, 0);
    private JButton bu12 = makebutton(" ", 300, 200, 60, 60, 0);
    private JButton bu20 = makebutton(" ", 100, 300, 60, 60, 0);
    private JButton bu21 = makebutton(" ", 200, 300, 60, 60, 0);
    private JButton bu22 = makebutton(" ", 300, 300, 60, 60, 0);


    private boolean setupped = false;

    private boolean gamerunning = false;

    private String[][] tictacboard = { {"", "", ""},
                                       {"", "", ""},
                                       {"", "", ""} };

    public TicTacToe() { // start of the program, is activated through the main() and maingui events
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(512,512);
        frame.setResizable(false);
        panel.setLayout(null);
        setupmainmenu();
        panel.setVisible(true);
        frame.setVisible(true);
    }


    private void setupmainmenu() { // runs once, and does one-time commands to start the setup
        menu1.setSize(300, 100);
        menu1.setLocation(100, 100);
        menu1.setHorizontalAlignment(SwingConstants.CENTER);
        menu1.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(menu1);
        panel.add(startbutton);
        frame.add(panel);
        startbutton.addActionListener(startgame -> {setup();});
    }
    private void setup() { // this also runs once in the program, and sets up ActionListener for buttons
        visualboard.setIcon(imagething);
        visualboard.setSize(260, 260);
        visualboard.setLocation(100, 100);
        playerindic.setSize(260, 100);
        playerindic.setLocation(100, 300);
        togglegame(true);
        bu00.addActionListener(b00 -> {playerturn(bu00, 0, 0);});
        bu01.addActionListener(b01 -> {playerturn(bu01, 1, 0);});
        bu02.addActionListener(b02 -> {playerturn(bu02, 2, 0);});
        bu10.addActionListener(b10 -> {playerturn(bu10, 0, 1);});
        bu11.addActionListener(b11 -> {playerturn(bu11, 1, 1);});
        bu12.addActionListener(b12 -> {playerturn(bu12, 2, 1);});
        bu20.addActionListener(b20 -> {playerturn(bu20, 0, 2);});
        bu21.addActionListener(b21 -> {playerturn(bu21, 1, 2);});
        bu22.addActionListener(b22 -> {playerturn(bu22, 2, 2);});
    }

    private void playerturn(JButton place, int coordx, int coordy) { // swaps between which player has a turn
        if (gamerunning) {
            if (whichturn && (place.getText() != "X" && place.getText() != "O")) {
                place.setText("X");
                tictacboard[coordx][coordy] = "X";
                whichturn = false;
            } else if (!whichturn && (place.getText() != "X" && place.getText() != "O")){
                place.setText("O");
                tictacboard[coordx][coordy] = "O";
                whichturn = true;
            }
        }
        checkwin(); // this is run at the end to check if this move was the winning move
    }
    private void checkwin() { // very redundant-looking method that checks if combinations equate to "XXX" or "OOO"
        for (int var = 0; var <= 2; var++) {
            if ((tictacboard[0][var] + tictacboard[1][var] + tictacboard[2][var]).equals("XXX")
            || (tictacboard[var][0] + tictacboard[var][1] + tictacboard[var][2]).equals("XXX")
            || (tictacboard[0][0] + tictacboard[1][1] + tictacboard[2][2]).equals("XXX")
            || (tictacboard[0][2] + tictacboard[1][1] + tictacboard[2][0]).equals("XXX")) {
                JOptionPane.showMessageDialog(null, "Game Over! Winner is \"X\"!!!");
                togglegame(false);
                return;
            } else if ((tictacboard[0][var] + tictacboard[1][var] + tictacboard[2][var]).equals("OOO")
                    || (tictacboard[var][0] + tictacboard[var][1] + tictacboard[var][2]).equals("OOO")
                    || (tictacboard[0][0] + tictacboard[1][1] + tictacboard[2][2]).equals("OOO")
                    || (tictacboard[0][2] + tictacboard[1][1] + tictacboard[2][0]).equals("OOO")) {
                JOptionPane.showMessageDialog(null, "Game Over! Winner is \"O\"!!!");
                togglegame(false);
                return;
            }
        }
        if (!Objects.equals(tictacboard[0][0], "") && !Objects.equals(tictacboard[1][0], "") && !Objects.equals(tictacboard[2][0], "")
        && !Objects.equals(tictacboard[0][1], "") && !Objects.equals(tictacboard[1][1], "") && !Objects.equals(tictacboard[2][1], "")
        && !Objects.equals(tictacboard[0][2], "") && !Objects.equals(tictacboard[1][2], "") && !Objects.equals(tictacboard[2][2], "")
        ) {
            JOptionPane.showMessageDialog(null, "Game Over! It's a draw!!!");
            togglegame(false);
        }
    }

    private void togglegame(boolean yes) { // this method is called when a game is over, or when it starts, a toggle
        if (yes) {
            gamerunning = true;
            menu1.setVisible(false);
            startbutton.setVisible(false);
            playerindic.setVisible(true);
            visualboard.setVisible(true);
            bu00.setVisible(true); bu01.setVisible(true); bu02.setVisible(true);
            bu10.setVisible(true); bu11.setVisible(true); bu12.setVisible(true);
            bu20.setVisible(true); bu21.setVisible(true); bu22.setVisible(true);
            if (!setupped) {
                panel.add(visualboard);
                frame.add(bu00); frame.add(bu01); frame.add(bu02);
                frame.add(bu10); frame.add(bu11); frame.add(bu12);
                frame.add(bu20); frame.add(bu21); frame.add(bu22);
                setupped = true;
                JButton CHEATER = makebutton("STOP READING MY CODE", 300, 300, 60, 60, 0);
                frame.add(CHEATER); CHEATER.setVisible(false); CHEATER.setEnabled(false);
            }
        } else {
            gamerunning = false;
            bu00.setVisible(false); bu01.setVisible(false); bu02.setVisible(false);
            bu10.setVisible(false); bu11.setVisible(false); bu12.setVisible(false);
            bu20.setVisible(false); bu21.setVisible(false); bu22.setVisible(false);
            tictacboard = new String[][]{{"", "", ""},
                    {"", "", ""},
                    {"", "", ""}};
            bu00.setText(""); bu01.setText(""); bu02.setText("");
            bu10.setText(""); bu11.setText(""); bu12.setText("");
            bu20.setText(""); bu21.setText(""); bu22.setText("");
            playerindic.setVisible(false);
            visualboard.setVisible(false);
            whichturn = true;
            menu1.setVisible(true);
            startbutton.setVisible(true);
        }
    }
    // no idea what the argument in main() does, IntelliJ just said I can simplify it to this, and it just works
    public static void main(String[] args) {SwingUtilities.invokeLater(TicTacToe::new);}
}