import hypermedia.video.*;
import java.awt.Rectangle;

public class BlobSizeComparator implements java.util.Comparator<Blob> {
    public int compare(Blob a, Blob b) {
        Rectangle boundingA = a.rectangle;
        Rectangle boundingB = b.rectangle;

        double areaA = boundingA.getHeight() * boundingA.getWidth();
        double areaB = boundingB.getHeight() * boundingB.getWidth();

        return (int)Math.round(areaB - areaA);
    }
}
