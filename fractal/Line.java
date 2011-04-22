import processing.core.PApplet;
import processing.core.PVector;

class Line {
    float originX, originY, targetX, targetY;

    Line(float originX, float originY, float targetX, float targetY) {
        this.originX = originX;
        this.originY = originY;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    PVector getOrigin() {
        return new PVector(originX, originY);
    }

    PVector getTarget() {
        return new PVector(targetX, targetY);
    }

    void draw(PApplet applet) {
        applet.line(originX, originY, targetX, targetY);
    }
}