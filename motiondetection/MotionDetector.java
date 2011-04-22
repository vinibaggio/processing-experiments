import hypermedia.video.*;
import java.util.*;
import processing.core.PVector;

class MotionDetector {
    private RingBuffer blobBuffer;

    MotionDetector(int numberOfFrames) {
        blobBuffer = new RingBuffer(numberOfFrames);
    }

    public void captureMovement(OpenCV opencv, float min, float max) {
        ArrayList<Blob> blobs = filterBlobs(opencv.blobs((int)min, (int)max, 20, false ), 1);
        if(blobs.size() > 0) {
            Blob currentBlob = blobs.get(0);
            blobBuffer.put(currentBlob);
        }
    }

    private ArrayList<Blob> filterBlobs(Blob[] blobs, int numberOfBlobs) {
        ArrayList<Blob> sortedBlobs = new ArrayList<Blob>(Arrays.asList(blobs));

        BlobSizeComparator blobSizeComparator = new BlobSizeComparator();
        Collections.sort(sortedBlobs, (Comparator<Blob>)blobSizeComparator);

        for (int i = sortedBlobs.size() - 1; i >= numberOfBlobs; i--){
            sortedBlobs.remove(i);
        }

        return sortedBlobs;
    }

    public RingBuffer getBlobBuffer() {
        return blobBuffer;
    }

    public Blob getFirstBlob() {
        return (Blob)blobBuffer.getFirst();
    }

    public Blob getLastBlob() {
        return (Blob)blobBuffer.getLast();
    }

    public PVector getMovement(int damping=1) {
        if (damping == 0){
            throw new ArgumentInvalid("Damping must be > 0");
        }

        Blob firstBlob = getFirstBlob();
        Blob lastBlob = getLastBlob();

        if (firstBlob != null && lastBlob != null) {
            Point firstCentroid = firstBlob.centroid;
            Point lastCentroid = lastBlob.centroid;

            return new PVector(-(lastCentroid.x - firstCentroid.x)/damping, -(lastCentroid.y - firstCentroid.y)/damping);
        } else {
            return new PVector(0, 0);
        }
    }
}
