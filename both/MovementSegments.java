import java.util.*;

class MovementSegments {
    /*private static final int MAX_DISTANCE = 30;

    public static List<Line> detectSegments(RingBuffer motionBuffer) {
        ArrayList<Line> lines = new ArrayList<Line>();

        Iterator<Motion> motionIterator = motionIterator.iterator();

        Motion[] linearMotionArray = new Motion[motionBuffer.getCapacity()];
        int i = 0, size = 0;

        while(motionIterator.hasNext()) {
            linearMotionArray[i] = blobsIterator.next();
            i++;
        }
        size = i;
        i = 0;

        if(size == 0) {
            return lines;
        }

        while(i< linearMotionArray.length){
            PVector currentVector = linearMotionArray[i].getCentroidAsVector();
            while (i < linearMotionArray.length && linearMotionArray[i] != null) {

            }


        /*            Motion firstMotion = blobsIterator.next();
                    PVector firstMotionVector = firstMotion.getCentroidAsVector();

                    Motion nextMotion = null, previousMotion = null;

                    PVector nextMotionVector = null;
                    boolean lineComplete = false;

                    while(motionIterator.hasNext()) {
                        nextMotion = motionIterator.next();
                        nextMotionVector = nextMotion.getCentroidAsVector();

                        if(firstMotionVector.dist(nextMotionVector) >= MAX_DISTANCE){
                            lineComplete = true;
                            break;
                        }
                        previousMotion =
                    }
                    if (lineComplete){
                        lines.add(new Line(firstMotionVector.x, firstMotionVector.y, nextMotionVector.x, nextMotionVector.y));
                    }
                }

    }*/
}
