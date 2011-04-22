import hypermedia.video.*;
import java.awt.Rectangle;

public class BlobSizeComparator implements java.util.Comparator<Blob> {
    public int compare(Blob a, Blob b) {
        return (int)Math.round(a.area - b.area);
    }
}
