import processing.core.PVector;
import hypermedia.video.Blob;
import java.awt.*;

public class BlobUtils {
    private final static int DAMPING = 100;

    private BlobUtils () {}

    public static PVector blobPosition(Blob blob) {
        if(blob != null) {
            return new PVector(blob.centroid.x, blob.centroid.y);
        } else {
            return new PVector(0, 0);
        }
    }
}
