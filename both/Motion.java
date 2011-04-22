import processing.core.PVector;
import hypermedia.video.*;
import java.awt.*;

class Motion {
    public float area;
    public Point centroid;

    public Motion(Blob blob) {
        this.area = blob.area;
        this.centroid = blob.centroid;
    }

    public Motion(int width, int height, int x, int y) {
        this.area = width*height;
        this.centroid = new Point(x, y);
    }

    public PVector getCentroidAsVector() {
        return new PVector(centroid.x, centroid.y);
    }
}
