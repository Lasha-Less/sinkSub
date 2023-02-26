import java.util.ArrayList;
import java.util.Random;

public class C4Factory {
    private final Random randomizer = new Random();
    private final static String Y_COORDINATES = "abcdefghij";
    private final Flotilla flotilla;

    public C4Factory(Flotilla flotilla) {
        this.flotilla = flotilla;
    }

    /**
     * Creates randomized 4c sub.
     * */
    public Submarine randomizeC4(){
        int number = randomizer.nextInt(19);
        Submarine submarine;
        Orientation fourWay = flotilla.fourWayOrientation();
        if (number==0){
            submarine = new Submarine(4, Shape.SQUARE, Orientation.UP, c4SquareLocationBuilder());
        } else if (number>0 && number < 3) {
            Orientation straightOrientation = flotilla.xyOrientation();
            submarine = new Submarine(4, Shape.FOUR_CELL_STRAIGHT, straightOrientation,
                    c4StraightLocationBuilder(straightOrientation));
        } else if (number > 2 && number<6) {
            Shape shape = flotilla.mirrorLZ();
            submarine = new Submarine(4, shape, c4LMirrorRightZUpLocationBuilder(shape));
        } else if (number>5 && number<9) {
            Shape shape = flotilla.mirrorLZ();
            submarine = new Submarine(4, shape, c4MirrorLLeftZDownLocationBuilder(shape));
        } else if (number>8 && number<12) {
            Shape shape = flotilla.mirrorLZ();
            submarine = new Submarine(4, shape, c4LUpMirrorUpZRightLocationBuilder(shape));
        } else if (number>11 && number<14) {
            submarine = new Submarine(4, Shape.PYRAMID, c4PyrLOrRLocBuilder(flotilla.lOrROrientation()));
        } else if (number>13 && number<16) {
            submarine = new Submarine(4, Shape.PYRAMID, c4PyrUpOrDownLocBuilder(flotilla.upOrDownOrientation()));
        }
        else {
            Shape shape = flotilla.mirrorLZ();
            submarine = new Submarine(4, shape, c4LDownMirrorDownZLeftLocationBuilder(shape));
        }
        return submarine;
    }

    /**
     * Sets location cells for square shaped C4 sub.
     * */
    public ArrayList<String>c4SquareLocationBuilder(){
        ArrayList<String> locations = new ArrayList<>();
        int startLocX = randomizer.nextInt(9);
        int startLocY = randomizer.nextInt(9);
        flotilla.getGrid()[startLocX][startLocY] = 1;
        flotilla.getGrid()[startLocX+1][startLocY] = 1;
        flotilla.getGrid()[startLocX][startLocY+1] = 1;
        flotilla.getGrid()[startLocX+1][startLocY+1] = 1;
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
        locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY));
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+1));
        locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY+1));
        return locations;
    }

    /**
     * Sets location cells for Straight shaped C4 subs.
     * */
    public ArrayList<String> c4StraightLocationBuilder(Orientation xYOrientation){
        ArrayList<String> locations = new ArrayList<>();
        if (xYOrientation == Orientation.UP){
            int startLocX = randomizer.nextInt(10);
            int startLocY = randomizer.nextInt(7);
            flotilla.getGrid()[startLocX][startLocY] = 1;
            flotilla.getGrid()[startLocX][startLocY+1] = 1;
            flotilla.getGrid()[startLocX][startLocY+2] = 1;
            flotilla.getGrid()[startLocX][startLocY+3] = 1;
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+1));
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+2));
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+3));
        } else {
            int startLocX = randomizer.nextInt(7);
            int startLocY = randomizer.nextInt(10);
            flotilla.getGrid()[startLocX][startLocY] = 1;
            flotilla.getGrid()[startLocX+1][startLocY] = 1;
            flotilla.getGrid()[startLocX+2][startLocY] = 1;
            flotilla.getGrid()[startLocX+3][startLocY] = 1;
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY));
            locations.add(Integer.toString(startLocX+2)+Y_COORDINATES.charAt(startLocY));
            locations.add(Integer.toString(startLocX+3)+Y_COORDINATES.charAt(startLocY));
        }
        return locations;
    }

    /**
     * #1
     * Sets location cells for (1) L-shaped, (2) mirror L-shaped (upside-down or) right facing, and
     * (3) upward facing Z-shaped C4 subs.
     * */
    public ArrayList<String> c4LMirrorRightZUpLocationBuilder(Shape shape){
        ArrayList<String> locations = new ArrayList<>();
        int startLocX = randomizer.nextInt(9);
        int startLocY = randomizer.nextInt(8);
        flotilla.getGrid()[startLocX][startLocY] = 1;
        flotilla.getGrid()[startLocX][startLocY+1] = 1;
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+1));
        String xYPlus2 = Integer.toString(startLocX) + Y_COORDINATES.charAt(startLocY + 2);
        String xPlus1YPlus2 = Integer.toString(startLocX + 1) + Y_COORDINATES.charAt(startLocY + 2);
        if (shape == Shape.FOUR_CELL_L){
            flotilla.getGrid()[startLocX][startLocY+2] = 1;
            flotilla.getGrid()[startLocX+1][startLocY+2] = 1;
            locations.add(xYPlus2);
            locations.add(xPlus1YPlus2);
        } else if (shape == Shape.FOUR_CELL_MIRROR_L) {
            flotilla.getGrid()[startLocX][startLocY+2] = 1;
            flotilla.getGrid()[startLocX+1][startLocY] = 1;
            locations.add(xYPlus2);
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY));
        }else {
            flotilla.getGrid()[startLocX+1][startLocY+1] = 1;
            flotilla.getGrid()[startLocX+1][startLocY+2] = 1;
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY+1));
            locations.add(xPlus1YPlus2);
        }
        return locations;
    }

    /**
     * #2
     * Sets location cells for (1) mirror L-shaped, (2) upside-down L (or left-facing L), and
     * (3) downward facing Z-shaped C4 subs.
     * */
    public ArrayList<String>c4MirrorLLeftZDownLocationBuilder(Shape shape){
        ArrayList<String> locations = new ArrayList<>();
        int startLocX = randomizer.nextInt(9);
        int startLocY = randomizer.nextInt(8);
        flotilla.getGrid()[startLocX+1][startLocY] = 1;
        flotilla.getGrid()[startLocX+1][startLocY+1] = 1;
        locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY));
        locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY+1));
        String xPlus1YPlus2 = Integer.toString(startLocX + 1) + Y_COORDINATES.charAt(startLocY + 2);
        String xYPlus2 = Integer.toString(startLocX) + Y_COORDINATES.charAt(startLocY + 2);
        if (shape==Shape.FOUR_CELL_MIRROR_L){
            flotilla.getGrid()[startLocX+1][startLocY+2] = 1;
            flotilla.getGrid()[startLocX][startLocY+2] = 1;
            locations.add(xPlus1YPlus2);
            locations.add(xYPlus2);
        } else if (shape==Shape.FOUR_CELL_L) {
            flotilla.getGrid()[startLocX][startLocY] = 1;
            flotilla.getGrid()[startLocX+1][startLocY+2] = 1;
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
            locations.add(xPlus1YPlus2);
        }else {
            flotilla.getGrid()[startLocX][startLocY+1] = 1;
            flotilla.getGrid()[startLocX][startLocY+2] = 1;
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+1));
            locations.add(xYPlus2);
        }
        return locations;
    }

    /**
     * #3
     * Sets location cells for (1) L-shaped upward facing, (2) mirror L-shaped upward facing, and (3) Z-shaped C4 subs.
     * */
    public ArrayList<String> c4LUpMirrorUpZRightLocationBuilder(Shape shape){
        ArrayList<String> locations = new ArrayList<>();
        int startLocX = randomizer.nextInt(8);
        int startLocY = randomizer.nextInt(9);
        flotilla.getGrid()[startLocX][startLocY+1] = 1;
        flotilla.getGrid()[startLocX+1][startLocY+1] = 1;
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+1));
        locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY+1));
        String xPlus2YPlus1 = Integer.toString(startLocX + 2) + Y_COORDINATES.charAt(startLocY + 1);
        String xPlus2Y = Integer.toString(startLocX + 2) + Y_COORDINATES.charAt(startLocY);
        if (shape==Shape.FOUR_CELL_L){
            flotilla.getGrid()[startLocX+2][startLocY+1] = 1;
            flotilla.getGrid()[startLocX+2][startLocY] = 1;
            locations.add(xPlus2YPlus1);
            locations.add(xPlus2Y);
        } else if (shape==Shape.FOUR_CELL_MIRROR_L) {
            flotilla.getGrid()[startLocX][startLocY] = 1;
            flotilla.getGrid()[startLocX+2][startLocY+1] = 1;
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
            locations.add(xPlus2YPlus1);
        }else {
            flotilla.getGrid()[startLocX+1][startLocY] = 1;
            flotilla.getGrid()[startLocX+2][startLocY] = 1;
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY));
            locations.add(xPlus2Y);
        }
        return locations;
    }

    /**
     * #4
     * Sets location cells for (1) L-shaped downward facing, (2) mirror L-shaped downward facing, and
     * (3) Z-shaped left facing C4 subs.
     * */
    public ArrayList<String>c4LDownMirrorDownZLeftLocationBuilder(Shape shape){
        ArrayList<String>locations = new ArrayList<>();
        int startLocX = randomizer.nextInt(8);
        int startLocY = randomizer.nextInt(9);
        flotilla.getGrid()[startLocX][startLocY] = 1;
        flotilla.getGrid()[startLocX+1][startLocY] = 1;
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
        locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY));
        String xPlus2Y = Integer.toString(startLocX + 2) + Y_COORDINATES.charAt(startLocY);
        String xPlus2YPlus1 = Integer.toString(startLocX + 2) + Y_COORDINATES.charAt(startLocY + 1);
        if (shape==Shape.FOUR_CELL_L){
            flotilla.getGrid()[startLocX+2][startLocY] = 1;
            flotilla.getGrid()[startLocX][startLocY+1] = 1;
            locations.add(xPlus2Y);
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+1));
        } else if (shape==Shape.FOUR_CELL_MIRROR_L) {
            flotilla.getGrid()[startLocX+2][startLocY] = 1;
            flotilla.getGrid()[startLocX+2][startLocY+1] = 1;
            locations.add(xPlus2Y);
            locations.add(xPlus2YPlus1);
        } else {
            flotilla.getGrid()[startLocX+1][startLocY+1] = 1;
            flotilla.getGrid()[startLocX+2][startLocY+1] = 1;
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY+1));
            locations.add(xPlus2YPlus1);
        }
        return locations;
    }

    /**
     * #1
     * Sets location cells for pyramid shaped (1) upward and (2) downward facing 4c subs.
     * */
    public ArrayList<String> c4PyrUpOrDownLocBuilder(Orientation upOrDown){
        ArrayList<String>locations = new ArrayList<>();
        int startLocX = randomizer.nextInt(9-1)+1;
        int startLocY =randomizer.nextInt(9);
        flotilla.getGrid()[startLocX][startLocY] = 1;
        flotilla.getGrid()[startLocX][startLocY+1] = 1;
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+1));
        if (upOrDown==Orientation.UP){
            flotilla.getGrid()[startLocX-1][startLocY+1] = 1;
            flotilla.getGrid()[startLocX+1][startLocY+1] = 1;
            locations.add(Integer.toString(startLocX-1)+Y_COORDINATES.charAt(startLocY+1));
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY+1));
        } else {
            flotilla.getGrid()[startLocX-1][startLocY] = 1;
            flotilla.getGrid()[startLocX+1][startLocY] = 1;
            locations.add(Integer.toString(startLocX-1)+Y_COORDINATES.charAt(startLocY));
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY));
        }
        return locations;
    }

    /**
     * #2
     * Sets location cells for pyramid shaped (1) left facing and (2) right facing 4c subs.
     * */
    public ArrayList<String> c4PyrLOrRLocBuilder(Orientation lOrR){
        ArrayList<String>locations = new ArrayList<>();
        int startLocX = randomizer.nextInt(9);
        int startLocY =randomizer.nextInt(9-1)+1;
        flotilla.getGrid()[startLocX][startLocY] = 1;
        flotilla.getGrid()[startLocX+1][startLocY] = 1;
        locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY));
        locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY));
        if (lOrR==Orientation.RIGHT){
            flotilla.getGrid()[startLocX][startLocY-1] = 1;
            flotilla.getGrid()[startLocX][startLocY+1] = 1;
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY-1));
            locations.add(Integer.toString(startLocX)+Y_COORDINATES.charAt(startLocY+1));
        } else {
            flotilla.getGrid()[startLocX+1][startLocY-1] = 1;
            flotilla.getGrid()[startLocX+1][startLocY+1] = 1;
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY-1));
            locations.add(Integer.toString(startLocX+1)+Y_COORDINATES.charAt(startLocY+1));
        }
        return locations;
    }

}
