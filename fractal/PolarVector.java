import processing.core.PVector;

public class PolarVector {
    private double r;
    private double theta;

    public PolarVector(double r, double theta) {
        this.r = r;
        this.theta = theta;
    }

    public PVector toPVector() {
        double x = r*Math.cos(theta);
        double y = r*Math.sin(theta);
        return new PVector((int)x, (int)y);
    }

    public double getR() { return r; }
    public double getTheta() { return theta; }
}
