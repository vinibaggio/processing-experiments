import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

class LineFractal {
    List<Line> lines;
    PApplet applet;

    LineFractal(PApplet applet, List<Line> lines) {
        this.lines = lines;
        this.applet = applet;
    }

    void draw() {
        Line firstLine = lines.get(0);
        Line lastLine = lines.get(lines.size()-1);

        applet.beginShape();
        PVector origin = firstLine.getOrigin();
        applet.curveVertex(origin.x, origin.y);

        for(Line line: lines) {
            PVector lineOrigin = line.getOrigin();
            applet.curveVertex(lineOrigin.x, lineOrigin.y);
        }

        PVector target = lastLine.getTarget();
        applet.curveVertex(target.x, target.y);
        applet.endShape();
    }
}
