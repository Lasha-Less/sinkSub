import java.util.ArrayList;
import java.util.Random;

public class C3Factory {

    private final Random randomizer = new Random();
    private final static String Y_COORDINATES = "abcdefghij";
    private final Flotilla flotilla;

    public C3Factory(Flotilla flotilla) {
        this.flotilla = flotilla;
    }

    /**
     * Creates randomized c3Sub
     * */
    public Submarine randomizeC3Sub(){
        int number = randomizer.nextInt(6);
        Submarine submarine;
        ArrayList<String>curvedLoc = new ArrayList<>();
        if (number < 2){
            submarine = new Submarine(
                    3, Shape.THREE_CELL_STRAIGHT, c3StraightLocBuilder(flotilla.xyOrientation()));
        } else if (number==2) {
            submarine = new Submarine(3, Shape.THREE_CELL_CURVED, c3CurveRightLocBuilder(curvedLoc));
        } else if (number==3) {
            submarine = new Submarine(3, Shape.THREE_CELL_CURVED, c3CurveDownLocBuilder(curvedLoc));
        } else if (number==4) {
            submarine = new Submarine(3, Shape.THREE_CELL_CURVED, c3CurveLeftLocBuilder(curvedLoc));
        } else {
            submarine = new Submarine(3, Shape.THREE_CELL_CURVED, c3CurveUpLocBuilder(curvedLoc));
        }
        return submarine;
    }

    /**
     * Sets location cells for Straight shaped C3 subs.
     * */
    public ArrayList<String> c3StraightLocBuilder(Orientation xYOrientation){
        System.out.println("Orientation: "+xYOrientation);
        ArrayList<String> locations = new ArrayList<>();
        if (xYOrientation == Orientation.UP){
            locations = c3VertLocBuilder(locations);
        } else {
            locations = c3HorLocBuilder(locations);
        }
        return locations;
    }

    /**
     * Simple method to set location cells for straight shaped horizontal C3 subs. It accepts an empty ArrayList
     * that it fills with the locations and returns.
     * */
    public ArrayList<String> c3HorLocBuilder(ArrayList locations){
        int x = randomizer.nextInt(8);
        System.out.println("x = "+x);
        int y = randomizer.nextInt(10);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y) && flotilla.checkLocation(x+1, y) && flotilla.checkLocation(x+2, y)) {
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x+1)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x+2)+Y_COORDINATES.charAt(y));
//            System.out.println("The size of the locations at this point is: "+locations.size());
        } else {
            c3HorLocBuilder(locations);
        }
        flotilla.logLocationCells(locations);
        System.out.println("The print of LOCATIONS ArrayList: " + locations);
        return  locations;
    }

    /**
     * Simple method to set location cells for straight shaped vertical C3 subs.
     * */
    public ArrayList<String> c3VertLocBuilder(ArrayList locations){
        int x = randomizer.nextInt(10);
        System.out.println("x = "+x);
        int y = randomizer.nextInt(8);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y) && flotilla.checkLocation(x, y+1) && flotilla.checkLocation(x, y+2)) {
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y+1));
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y+2));
//            System.out.println("The size of the locations at this point is: "+locations.size());
        } else {
            c3HorLocBuilder(locations);
        }
        flotilla.logLocationCells(locations);
        System.out.println("The print of LOCATIONS ArrayList: " + locations);
        return  locations;
    }

    /**
     * Simple method to set location cells for curved right facing (down-facing L) C3 subs.
     * */
    public ArrayList<String> c3CurveRightLocBuilder(ArrayList locations){
        int x = randomizer.nextInt(9);
        System.out.println("x = "+x);
        int y = randomizer.nextInt(9);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y) && flotilla.checkLocation(x+1, y) && flotilla.checkLocation(x, y+1)){
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x+1)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y+1));
        } else {
            c3CurveRightLocBuilder(locations);
        }
        flotilla.logLocationCells(locations);
        return locations;
    }

    /**
     * Simple method to set location cells for curved down facing (down-facing mirror-L) C3 subs.
     * */
    public ArrayList<String> c3CurveDownLocBuilder(ArrayList locations){
        int x = randomizer.nextInt(9);
        System.out.println("x = "+x);
        int y = randomizer.nextInt(9);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y) && flotilla.checkLocation(x+1, y) &&
                flotilla.checkLocation(x+1, y+1)){
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x+1)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x+1)+Y_COORDINATES.charAt(y+1));
        } else {
            c3CurveDownLocBuilder(locations);
        }
        flotilla.logLocationCells(locations);
        return locations;
    }

    /**
     * Simple method to set location cells for curved left facing (mirror-L) C3 subs.
     * */
    public ArrayList<String> c3CurveLeftLocBuilder(ArrayList locations){
        int x = randomizer.nextInt(10-1)+1;
        System.out.println("x = "+x);
        int y = randomizer.nextInt(9);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y) && flotilla.checkLocation(x-1, y+1) &&
                flotilla.checkLocation(x, y+1)){
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x-1)+Y_COORDINATES.charAt(y+1));
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y+1));
        } else {
            c3CurveLeftLocBuilder(locations);
        }
        flotilla.logLocationCells(locations);
        return locations;
    }

    /**
     * Simple method to set location cells for curved left facing (mirror-L) C3 subs.
     * */
    public ArrayList<String> c3CurveUpLocBuilder(ArrayList locations){
        int x = randomizer.nextInt(9);
        System.out.println("x = "+x);
        int y = randomizer.nextInt(9);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y) && flotilla.checkLocation(x, y+1) &&
                flotilla.checkLocation(x+1, y+1)){
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y+1));
            locations.add(Integer.toString(x+1)+Y_COORDINATES.charAt(y+1));
        } else {
            c3CurveUpLocBuilder(locations);
        }
        flotilla.logLocationCells(locations);
        return locations;
    }

}
