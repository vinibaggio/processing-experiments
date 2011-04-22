import hypermedia.video.*;

OpenCV opencv;
PFont font;

int h = 640;
int w = 480;

float MIN_ANGLE = 5.0;
float MAX_ANGLE = 20.0;
float MIN_RADIUS = 5.0;
float MAX_RADIUS = 20.0;

int currentFrame = 0;
int TOTAL_LOOP_FRAMES = 10;

PVector lastMovement;

MotionDetector motionDetector;
FractalBuilder fractalBuilder;

void drawBlobCentroids(Iterable<Motion> blobs) {
    Iterator<Motion> blobsIterator = blobs.iterator();

    while(blobsIterator.hasNext()) {
        Motion blob = blobsIterator.next();
        ellipse(blob.centroid.x, blob.centroid.y, 20, 20);
    }
}

void drawPoint(PVector point) {
    ellipse(point.x, point.y, 10, 10);
}

void drawMovement(PVector a, PVector b) {
    line(a.x, a.y, b.x, b.y);
    /*if(movement.mag() > 5) {
        drawPoint(movement);
        PVector x = new PVector(1, 0);
        PVector seed = new PVector(w/2, h/2);

        float strokeColor = random(32, 255);
        stroke(strokeColor, strokeColor, 0);
        fractalBuilder.buildFractal(seed, angle, 1).draw();
    }*/
}

void showThreshold(int threshold) {
    text("Current threshold: " + threshold, 10, height - 10);
}

void setup() {
    // Basic screen initialization
    size(h, w);
    smooth();
    background(0, 0, 0);
    strokeJoin(ROUND);
    stroke(255, 255, 255);
    noFill();

    opencv = new OpenCV(this);
    opencv.capture(h, w);

    lastMovement = new PVector(w/2, h/2);

    motionDetector = new MotionDetector(opencv, 10, 30);
    fractalBuilder = new FractalBuilder(this, 20, MIN_ANGLE, MAX_ANGLE, MIN_RADIUS, MAX_RADIUS);

    font = loadFont("AndaleMono.vlw");
    textFont(font);
}

void draw() {
    opencv.read();

    image(opencv.image(), 0, 0);
    opencv.absDiff();
    opencv.blur(OpenCV.BLUR, 30); // Muito blur para evitar blobs de pequenos dedos, p.e
    opencv.threshold(motionDetector.getThreshold()); // Grayscale

    motionDetector.captureMovement(100, w*h/2);
    PVector currentMovement = motionDetector.getMovement(1);
    float angle = (float)Math.toDegrees(PVector.angleBetween(new PVector(1, 0), currentMovement));

//    println("Theta: " + angle + " Mag: (" + currentMovement.mag() + " (" + currentMovement.x + "," + currentMovement.y +")");
    /*currentFrame += 1;
    if(currentFrame == TOTAL_LOOP_FRAMES) {
        background(0);
        drawMovement(motionDetector.getFirstMotion().getCentroidAsVector(),
                     motionDetector.getLastMotion().getCentroidAsVector());
        currentFrame = 0;
    }*/

    opencv.remember();

    //showBlobs(blobs);
    drawBlobCentroids(motionDetector.getMotionBuffer());
    //showThreshold(motionDetector.getThreshold());
}

void mouseClicked() {
    int value = 10;

    if (mouseButton == RIGHT){
        value = -10;
    }

    motionDetector.setThreshold(motionDetector.getThreshold() + value);
}

public void stop() {
    opencv.stop();
    super.stop();
}
