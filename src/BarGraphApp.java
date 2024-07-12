import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.EventQueue;

public class BarGraphApp implements Runnable {
    public static void main(String[] args) {
        EventQueue.invokeLater(new BarGraphApp());
    }

    @Override
    public void run() {
        JFrame myFrame = new JFrame("Bar Graph App");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new BarGraphGUI();
        myFrame.getContentPane().add(mainPanel);

        myFrame.pack();
        myFrame.setLocationByPlatform(true);
        myFrame.setVisible(true);
    }
}
