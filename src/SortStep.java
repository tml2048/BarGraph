public class SortStep {

    // constants
    public static final int SWAP = 1;
    public static final int COMPARE = 2;
    public static final int GOTOBUFFER = 3;
    public static final int GOTOBARS = 4;
    
    // fields
    private int stepKind;
    private int indexA;
    private int indexB;

    // constructors
    public SortStep(int indexA, int indexB, int stepKind) {
        this.indexA = indexA;
        this.indexB = indexB;
        this.stepKind = stepKind;
    }

    // methods
    public int getStepKind() {
        return stepKind;
    }

    public int getIndexA() {
        return indexA;
    }

    public int getIndexB() {
        return indexB;
    }

}
