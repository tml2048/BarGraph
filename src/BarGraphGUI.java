import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BarGraphGUI extends JPanel implements ActionListener {

    // fields
    private BarVisual visual;
    private BarOptions options;
    private BarLogic logic;

    // constructor
    public BarGraphGUI() {
        this.logic = new BarLogic();
        this.visual = new BarVisual(logic);
        this.options = new BarOptions(logic);
        this.setLayout(new BorderLayout());
        this.add(options, BorderLayout.NORTH);
        this.add(visual, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(800, 800));

        Timer t = new Timer(30, this);
        t.setInitialDelay(1000);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        options.update();
        this.repaint();
    }

}
