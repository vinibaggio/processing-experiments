import hypermedia.video.*;
import java.awt.*;

OpenCV opencv;
PFont font;

int h = 640;
int w = 480;

int threshold = 30;

MotionDetector motionDetector;

void drawBlobCentroids(Iterable<Blob> blobs) {
    Iterator<Blob> blobsIterator = blobs.iterator();

    fill(255, 255, 255);
    while(blobsIterator.hasNext()) {
        Blob blob = blobsIterator.next();
        ellipse(blob.centroid.x, blob.centroid.y, 20, 20);
    }
}

void drawEntity() {
    PVector lastPosition = BlobUtils.blobPosition(motionDetector.getLastBlob());

    ellipse(lastPosition.x, lastPosition.y, 50, 50);
}


void showThreshold(int threshold) {
    text("Current threshold: " + threshold, 10, height - 10);
}

void setup() {
    size(h, w);
    opencv = new OpenCV(this);
    opencv.capture(h, w);
    smooth();

    motionDetector = new MotionDetector(10);

    font = loadFont("AndaleMono.vlw");
    textFont(font);
}

void draw() {
    background(0);
    opencv.read();

    PImage originalImage = opencv.image();

    opencv.convert(OpenCV.GRAY);
    opencv.absDiff();
    opencv.blur(OpenCV.BLUR, 3); // Muito blur para evitar blobs de pequenos dedos, p.e
    opencv.threshold(threshold); // Grayscale

//    image(opencv.image(), 0, 0);
    opencv.remember();

    image(originalImage, 0, 0);

    motionDetector.captureMovement(opencv, 100, w*h/2);

    //showBlobs(blobs);
    drawBlobCentroids(motionDetector.getBlobBuffer());
    //drawEntity();
    showThreshold(threshold);
}

void mouseClicked() {
    if (mouseButton == LEFT){
        threshold += 10;
    } else {
        threshold -= 10;
    }
}

public void stop() {
    opencv.stop();
    super.stop();
}
