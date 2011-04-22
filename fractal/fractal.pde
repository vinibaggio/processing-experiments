int h = 640;
int w = 480;

FractalBuilder fractalBuilder;

float MIN_ANGLE = 5.0;
float MAX_ANGLE = 20.0;
float MIN_RADIUS = 5.0;
float MAX_RADIUS = 20.0;

void setup() {
    smooth();
    size(h, w);
    background(0, 0, 0);
    strokeJoin(ROUND);
    stroke(255, 255, 255);
    noFill();

    fractalBuilder = new FractalBuilder(this, 20, MIN_ANGLE, MAX_ANGLE, MIN_RADIUS, MAX_RADIUS);
}

void draw() {
    PVector seed = new PVector(w/2, h/2);

    float strokeColor = random(32, 255);
    stroke(strokeColor, strokeColor, 0);
    fractalBuilder.buildFractal(seed, 30.0, 30).draw();
}

void mousePressed() {
}