import hypermedia.video.*;
import java.util.*;
import processing.core.PVector;
import java.awt.*;

class MotionDetector {
    int threshold;
    private RingBuffer motionBuffer;
    private OpenCV opencv;

    MotionDetector(OpenCV opencv, int numberOfFrames, int threshold) {
        motionBuffer = new RingBuffer(numberOfFrames);
        this.opencv = opencv;
        this.threshold = threshold;
    }

    public void captureMovement(float min, float max) {
        ArrayList<Blob> blobs = filterBlobs(opencv.blobs((int)min, (int)max, 20, false ), 1);
        if(blobs.size() > 0) {
            Blob currentBlob = blobs.get(0);
            motionBuffer.put(new Motion(currentBlob));
        } else {
            motionBuffer.put(new Motion(0, 0, 0, 0));
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

    public RingBuffer getMotionBuffer() {
        return motionBuffer;
    }

    public Motion getFirstMotion() {
        return (Motion)motionBuffer.getFirst();
    }

    public Motion getLastMotion() {
        return (Motion)motionBuffer.getLast();
    }

    public PVector getMovement(int damping) {
        if (damping == 0){
            throw new IllegalArgumentException("Damping must be > 0");
        }

        Motion firstMotion = getFirstMotion();
        Motion lastMotion = getLastMotion();

        if (firstMotion != null && lastMotion != null) {
            Point firstCentroid = firstMotion.centroid;
            Point lastCentroid = lastMotion.centroid;

            return new PVector(-(lastCentroid.x - firstCentroid.x)/damping, -(lastCentroid.y - firstCentroid.y)/damping);
        } else {
            return new PVector(0, 0);
        }
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return this.threshold;
    }
}
