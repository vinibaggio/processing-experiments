import java.util.List;
import java.util.ArrayList;
import processing.core.PVector;
import processing.core.PApplet;

class FractalBuilder {
    private int iterations;
    private float minAngle;
    private float maxAngle;
    private float minRadius;
    private float maxRadius;
    private PApplet applet;

    FractalBuilder(PApplet applet, int iterations, float minAngle, float maxAngle, float minRadius, float maxRadius) {
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
        this.iterations = iterations;
        this.applet = applet;
    }

    public LineFractal buildFractal(PVector origin, float initialAngle, int direction) {
        return new LineFractal(applet, build(origin, initialAngle, direction, iterations));
    }

    private List<Line> build(PVector origin, double previousAngle, int direction, int iteration) {
        if(iteration > 0) {
            double currentAngle = previousAngle + randomAngle()*direction;
            PolarVector polarVector = new PolarVector(randomRadius(), Math.toRadians(currentAngle));

            PVector fractalVector = polarVector.toPVector();

            fractalVector.add(origin);

            Line newLine = new Line(origin.x, origin.y, fractalVector.x, fractalVector.y);

            List<Line> lines = build(fractalVector, currentAngle, direction, iteration - 1);
            lines.add(newLine);

            return lines;
        } else {
            return new ArrayList<Line>();
        }
    }

    private double randomRadius() {
        return (double)applet.random(minRadius, maxRadius);
    }

    private double randomAngle() {
        return (double)applet.random(minAngle, maxAngle);
    }
}
