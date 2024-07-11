import java.util.ArrayList;

public class BarLogic {
    // fields
    private ArrayList<Bar> bars, starting, buffer;
    private ArrayList<SortStep> stepHistory;
    private int stepIndex;
    private int compSteps, swapSteps;

    // constructor
    public BarLogic() {
        this.bars = new ArrayList<Bar>();
        this.starting = new ArrayList<Bar>();
        this.buffer = new ArrayList<Bar>();
        this.stepHistory = new ArrayList<SortStep>();
        this.resetHistory();
    }

    // methods
    public void addBar() {
        bars.add(new Bar());
        buffer.add(null);
    }

    public void addBar(int count) {
        for (int k = 0; k < count; k++) {
            this.addBar();
        }
    }

    public void bubbleSort() {
        resetHistory();
        storeStartingBars();

        for (int end = bars.size(); end > 0; end--) {
            for (int k = 0; k + 1 < end; k++) {
                Bar b1 = bars.get(k);
                Bar b2 = bars.get(k+1);
                stepHistory.add(new SortStep(k, k+1, SortStep.COMPARE));
                if (b1.getHeight() > b2.getHeight()) {
                    swap(k, k+1);
                    stepHistory.add(new SortStep(k, k+1, SortStep.SWAP));
                }
            }
        }

        retrieveStartingBars();
        stepIndex = 0;
    }
    public void selectionSort() {
        resetHistory();
        storeStartingBars();

        for (int start = 0; start < bars.size(); start++) {
            int minIndex = start;
            double minHeight = bars.get(start).getHeight();
            for (int k = start + 1; k < bars.size(); k++) {
                Bar b = bars.get(k);

                stepHistory.add(new SortStep(minIndex, k, SortStep.COMPARE));
                if (b.getHeight() < minHeight) {
                    minIndex = k;
                    minHeight = b.getHeight();
                }
            }

            swap(start, minIndex);
            stepHistory.add(new SortStep(start, minIndex, SortStep.SWAP));
        }

        retrieveStartingBars();
        stepIndex = 0;
    }

    public void insertionSort() {
        resetHistory();
        storeStartingBars();

        for (int start = 1; start < bars.size(); start++) {

            for (int k = start; k > 0; k--) {
                Bar before = bars.get(k - 1);
                Bar cur = bars.get(k);

                stepHistory.add(new SortStep(k - 1, k, SortStep.COMPARE));
                if (before.getHeight() > cur.getHeight()) {
                    swap(k - 1, k );
                    stepHistory.add(new SortStep(k - 1, k, SortStep.SWAP));
                }
                else
                    break;
            }
        }

        retrieveStartingBars();
        stepIndex = 0;
    }

    public void mergeSort() {
        resetHistory();
        storeStartingBars();

        mergeSort(0, bars.size() - 1);

        retrieveStartingBars();
        stepIndex = 0;
    }  

    private void mergeSort(int lowIndex, int highIndex) {
        // base cases
        if (lowIndex >= highIndex) {
            return;
        }
        if (lowIndex + 1 == highIndex) {
            Bar b1 = bars.get(lowIndex);
            Bar b2 = bars.get(highIndex);
            stepHistory.add(new SortStep(lowIndex, highIndex, SortStep.COMPARE));
            if (b1.getHeight() > b2.getHeight()) {
                swap(lowIndex, highIndex);
                stepHistory.add(new SortStep(lowIndex, highIndex, SortStep.SWAP));
            }
            return;
        }

        // recursive case
        int midIndex = (lowIndex + highIndex) / 2;
        mergeSort(lowIndex, midIndex);
        mergeSort(midIndex + 1, highIndex);

        // merging the two halves, use A to refer to 1st half, B to refer to 2nd half
        int pointerA = lowIndex, pointerB = midIndex + 1;
        boolean aDone = false, bDone = false;
        for (int k = lowIndex; k <= highIndex; k++) {
            if (aDone) {
                stepHistory.add(new SortStep(pointerB, k, SortStep.GOTOBUFFER));
                buffer.set(k, bars.get(pointerB));
                pointerB++;
            } else if (bDone) {
                stepHistory.add(new SortStep(pointerA, k, SortStep.GOTOBUFFER));
                buffer.set(k, bars.get(pointerA));
                pointerA++;
            } else {
                Bar b1 = bars.get(pointerA);
                Bar b2 = bars.get(pointerB);
                stepHistory.add(new SortStep(pointerA, pointerB, SortStep.COMPARE));
                if (b1.getHeight() < b2.getHeight()) {
                    stepHistory.add(new SortStep(pointerA, k, SortStep.GOTOBUFFER));
                    buffer.set(k, b1);
                    pointerA++;
                    if (pointerA == midIndex + 1)
                        aDone = true;
                } else {
                    stepHistory.add(new SortStep(pointerB, k, SortStep.GOTOBUFFER));
                    buffer.set(k, b2);
                    pointerB++;
                    if (pointerB == highIndex + 1)
                        bDone = true;
                }
            }
        }

        for (int k = lowIndex; k <= highIndex; k++) {
            bars.set(k, buffer.get(k));
            buffer.set(k, null);
            stepHistory.add(new SortStep(k, k, SortStep.GOTOBARS));
        }
    }

    public void quickSort() {
        resetHistory();
        storeStartingBars();

        quickSort(0, bars.size() - 1);

        retrieveStartingBars();
        stepIndex = 0;
    }

    private void quickSort(int lowIndex, int highIndex) {
        // base cases
        if (lowIndex >= highIndex) {
            return;
        }
        if (lowIndex + 1 == highIndex) {
            Bar b1 = bars.get(lowIndex);
            Bar b2 = bars.get(highIndex);
            stepHistory.add(new SortStep(lowIndex, highIndex, SortStep.COMPARE));
            if (b1.getHeight() > b2.getHeight()) {
                swap(lowIndex, highIndex);
                stepHistory.add(new SortStep(lowIndex, highIndex, SortStep.SWAP));
            }
            return;
        }

        // recursive case
        int pivot = lowIndex;
        double pivotHeight = bars.get(pivot).getHeight();
        int pointerA = lowIndex + 1;
        int pointerB = highIndex;
        while (pointerA <= pointerB) {

            while (pointerA <= highIndex) {
                stepHistory.add(new SortStep(pointerA, pivot, SortStep.COMPARE));
                if (bars.get(pointerA).getHeight() < pivotHeight) {
                    pointerA++;
                }
                else
                    break;
            }
            while (pointerB >= lowIndex) {
                stepHistory.add(new SortStep(pointerB, pivot, SortStep.COMPARE));
                if (bars.get(pointerB).getHeight() > pivotHeight) {
                    pointerB--;
                }
                else
                    break;
            }
            if (pointerA < pointerB) {
                stepHistory.add(new SortStep(pointerA, pointerB, SortStep.SWAP));
                swap(pointerA, pointerB);
                pointerA++;
                pointerB--;
            }
        }
        stepHistory.add(new SortStep(pointerB, pivot, SortStep.COMPARE));
        if (pivotHeight > bars.get(pointerB).getHeight()) {
            swap(pivot, pointerB);
            stepHistory.add(new SortStep(pointerB, pivot, SortStep.SWAP));
            pivot = pointerB;
        }
        quickSort(lowIndex, pivot - 1);
        quickSort(pivot + 1, highIndex);
    }

    public void step() {
        if (this.stepIndex == -1 || this.stepIndex == stepHistory.size())  {
            return;
        }
        SortStep step = stepHistory.get(stepIndex);
        stepIndex++;
        switch (step.getStepKind()) {
            case SortStep.SWAP:
                swap(step.getIndexA(), step.getIndexB());
                swapSteps++;
                break;
            case SortStep.COMPARE:
                compSteps++;
                break;
            case SortStep.GOTOBUFFER:
            {
                Bar b = bars.get(step.getIndexA());
                buffer.set(step.getIndexB(), b);
                break;
            }
            case SortStep.GOTOBARS:
            {
                Bar b = buffer.get(step.getIndexA());
                bars.set(step.getIndexB(), b);
                buffer.set(step.getIndexA(), null);
                break;
            }
        }

    }

    public SortStep getCurrentStep() {
        if (this.stepIndex == -1 || this.stepIndex == stepHistory.size())
            return null;
        return stepHistory.get(stepIndex);
    }

    private void swap(int index1, int index2) {
        Bar a = bars.get(index1);
        Bar b = bars.get(index2);
        bars.set(index1, b);
        bars.set(index2, a);
    }

    public ArrayList<Bar> getBars() {
        return bars;
    }

    public ArrayList<Bar> getBuffer() {
        return buffer;
    }

    public int getBarCount() {
        return bars.size();
    }

    public void shuffle() {
       resetHistory(); 

        for (int k = 0; k < bars.size(); k++) {
            int randIndex = (int) (bars.size() * Math.random());
            swap(k , randIndex);
        }
    }

    private void resetHistory() {
        stepHistory.clear();
        stepIndex = -1;
        compSteps = 0;
        swapSteps = 0;
    }

    private void storeStartingBars() {
        starting.clear();
        for (Bar b : bars) {
            starting.add(b);
        }
    }

    private void retrieveStartingBars() {
        bars.clear();
        for(Bar b : starting) {
            bars.add(b);
        }
    }

    public int getComps() { return this.compSteps; }
    public int getSwaps() { return this.swapSteps; }
}
