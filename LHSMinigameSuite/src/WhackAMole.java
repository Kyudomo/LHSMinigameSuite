import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class WhackAMole extends maingui {
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final int MOLES = 2;
    private static final int GAME_TIME = 30;

    private JFrame frame = new JFrame();
    private JButton[][] buttons;
    private int score;
    private int timeLeft;
    private Timer timer;
    private JLabel scoreLabel;
    private JLabel timeLabel;

    public WhackAMole() {
        frame.setTitle("Whack-a-Mole");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(ROWS, COLS));
        buttons = new JButton[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ButtonListener());
                panel.add(buttons[i][j]);
            }
        }

        scoreLabel = new JLabel("Score: 0");
        timeLabel = new JLabel("Time Left: " + GAME_TIME);

        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.add(scoreLabel);
        infoPanel.add(timeLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.SOUTH);

        score = 0;
        timeLeft = GAME_TIME;

        timer = new Timer(1000, new TimerListener());
        timer.start();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        startGame();
    }

    private void startGame() {
        resetButtons();
        score = 0;
        timeLeft = GAME_TIME;
        timer.restart();
        spawnMoles();
    }

    private void spawnMoles() {
        Random random = new Random();
        int moleCount = 0;

        while (moleCount < MOLES) {
            int row = random.nextInt(ROWS);
            int col = random.nextInt(COLS);

            if (!buttons[row][col].getText().equals("M")) {
                buttons[row][col].setText("M");
                buttons[row][col].setEnabled(true);
                moleCount++;
            }
        }
    }

    private void resetButtons() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();

            if (source.getText().equals("M")) {
                score++;
            } else {
                score--;
            }

            source.setEnabled(false);
            scoreLabel.setText("Score: " + score);

            resetButtons();
            spawnMoles();
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timeLeft--;
            timeLabel.setText("Time Left: " + timeLeft);

            if (timeLeft <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Game Over! Your score is: " + score);
                startGame();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WhackAMole::new);
    }
}