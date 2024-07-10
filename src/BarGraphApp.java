import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

public class BarGraphApp implements Runnable {
    public static void main(String[] args) {
        EventQueue.invokeLater(new BarGraphApp());
    }

    @Override
    public void run() {
        JFrame myFrame = new JFrame("Bar Graph App");
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(400, 600));
        mainPanel.setLayout(new BorderLayout());
        myFrame.getContentPane().add(mainPanel);

        myFrame.pack();
        myFrame.setLocationByPlatform(true);
        myFrame.setVisible(true);
    }
}
