import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BarVisual extends JPanel implements ActionListener {

    // fields
    private BarLogic logic;

    // constructors
    public BarVisual(BarLogic logic) {
        this.logic = logic;
    }

    // methods
    @Override
    public void paintComponent(Graphics g) {
        drawBars(g);
        drawBuffer(g);
        drawStepIndicator(g);
    }

    private void drawBars(Graphics g) {
        ArrayList<Bar> bars = logic.getBars();

        if (bars.size() == 0) 
            return;

        for (int k = 0; k < bars.size(); k++) {
            Bar b = bars.get(k);
            int barHeight = (int) (b.getHeight() * this.getHeight());
            g.setColor(b.getColor());
            g.fillRect(getXPosition(k), this.getHeight() - barHeight, getBarWidth(), barHeight);
        }
    }

    private void drawBuffer(Graphics g) {
        ArrayList<Bar> buffer = logic.getBuffer();
        for (int k = 0; k < buffer.size(); k++) {
            Bar b = buffer.get(k);
            if (b == null)
                continue;
            g.setColor(b.getColor());
            int barHeight = (int) (b.getHeight() * this.getHeight());
            g.drawRect(getXPosition(k), this.getHeight() - barHeight, getBarWidth(), barHeight);
        }

    }

    private void drawStepIndicator(Graphics g) {
        SortStep step = logic.getCurrentStep();
        if (step == null)
            return;

        int indicatorY = (int) (this.getHeight() * 0.95);
        int indicatorHeight = (int) (this.getHeight() * 0.05);
        switch (step.getStepKind()) {
            case SortStep.COMPARE:
                g.setColor(Color.ORANGE);
                break;
            case SortStep.SWAP:
                g.setColor(Color.RED);
                break;
            case SortStep.GOTOBUFFER:
                g.setColor(Color.BLUE);
                break;
            case SortStep.GOTOBARS:
                g.setColor(Color.GREEN);
                break;
            
        } 
        g.fillRect(getXPosition(step.getIndexA()), indicatorY, getBarWidth(), indicatorHeight);
        g.fillRect(getXPosition(step.getIndexB()), indicatorY, getBarWidth(), indicatorHeight);
    }

    private int getXPosition(int index) {
        return (int) (1.0 * index * this.getWidth() / logic.getBarCount());
    }

    private int getBarWidth() {
        return this.getWidth() / logic.getBarCount();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }


}
