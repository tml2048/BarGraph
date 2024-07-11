import java.awt.Color;

public class Bar {

    // fields
    private double height; // (0.1, 1.0)
    private Color color;

    // constructor
    public Bar(double h, Color c) {
        this.height = h;
        this.color = c;
    }

    public Bar() {
        this.height = Math.random() * 0.9 + 0.1;
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        this.color = new Color(r, g, b);
    }

    // methods
    public double getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}
