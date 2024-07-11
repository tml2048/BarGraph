import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class BarOptions extends JPanel implements ActionListener {

    // fields
    private BarLogic logic;
    private JButton add1, add10;
    private JButton sort;
    private JButton step;
    private JButton shuffle;
    private JToggleButton autorun;
    private JLabel compLabel, swapLabel;
    private JComboBox<String> sortAlgs;

    // constructor
    public BarOptions(BarLogic logic) {
        this.logic = logic;
        
        this.add1 = new JButton("Add");
        this.add(add1);
        add1.addActionListener(this);
        
        this.add10 = new JButton("Add 10");
        this.add(add10);
        add10.addActionListener(this);
        
        this.sort = new JButton("Sort");
        this.add(sort);
        sort.addActionListener(this);
        
        this.shuffle = new JButton("Shuffle");
        this.add(shuffle);
        shuffle.addActionListener(this);

        this.step = new JButton("Step");
        this.add(step);        
        step.addActionListener(this);

        this.autorun = new JToggleButton("Autorun");
        this.add(autorun);

        this.compLabel = new JLabel("Comps: 0");
        this.add(compLabel);

        this.swapLabel = new JLabel("Swaps: 0");
        this.add(swapLabel);

        String[] sortChoices = {
            "Bubble Sort",
            "Selection Sort",
            "Insertion Sort",
            "Merge Sort",
            "Quick Sort" };
            

        sortAlgs = new JComboBox<String>(sortChoices);
        this.add(sortAlgs);
    }

    // methods
    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.compLabel.setText("Comps: " + logic.getComps());
        this.swapLabel.setText("Swaps: " + logic.getSwaps());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add1) {
            logic.addBar();
        } else if (e.getSource() == add10) {
            logic.addBar(10);
        } else if (e.getSource() == shuffle) {
            logic.shuffle();
        } else if (e.getSource() == step) {
            logic.step();
        } else if (e.getSource() == sort) {
            switch ((String) sortAlgs.getSelectedItem()) {
                case "Bubble Sort":
                    logic.bubbleSort();
                    break;
                case "Selection Sort":
                    logic.selectionSort();
                    break;
                case "Insertion Sort":
                    logic.insertionSort();
                    break;
                case "Merge Sort":
                    logic.mergeSort();
                    break;
                case "Quick Sort":
                    logic.quickSort();
                    break;

            }    
        }
    }

    public void update() {
        if (autorun.getModel().isSelected()) {
            logic.step();
        }

    }
}
