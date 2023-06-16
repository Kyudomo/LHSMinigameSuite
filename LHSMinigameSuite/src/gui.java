import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class gui {
    public static void main(String args[]){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setResizable(false);
        JButton button = new JButton("Press");
        JLabel label = new JLabel("Hai -REMOVED-");
        panel.setLayout(null);
        label.setSize(120, 40);
        button.setLocation(50,10);
        button.setSize(120,30);
        button.setLocation(50,50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setVisible(false);
                JLabel label = new JLabel("yay");
                panel.add(label);
                label.setLocation(100,100);
                label.setSize(100,40);
                label.setVisible(true);
            }
        });
        panel.add(label);
        panel.add(button);
        frame.add(panel);
        panel.setVisible(true);
        //frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
    }
}