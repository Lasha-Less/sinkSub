import java.util.ArrayList;
import java.util.Random;

public class C2Factory {

    private final Random randomizer = new Random();
    private final static String Y_COORDINATES = "abcdefghij";
    private final Flotilla flotilla;

    public C2Factory(Flotilla flotilla) {
        this.flotilla = flotilla;
    }

    /**
     * Creates randomized c2Sub
     * */
    public Submarine randomizeC2Sub(){
        int number = randomizer.nextInt(2);
        Submarine submarine;
        ArrayList<String>c2Locations = new ArrayList<>();
        if (number==0){
            submarine = new Submarine(2, Shape.TWO_CELL, c2Horizontal(c2Locations));
        } else {
            submarine = new Submarine(2, Shape.TWO_CELL, c2Vertical(c2Locations));
        }
        return submarine;
    }

    /**
     * Sets location cells for horizontal c2 subs.
     * */
    public ArrayList<String> c2Horizontal(ArrayList locations){
        int x = randomizer.nextInt(9);
        System.out.println("x = "+x);
        int y = randomizer.nextInt(10);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y) && flotilla.checkLocation(x+1, y)) {
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x+1)+Y_COORDINATES.charAt(y));
        } else {
            c2Horizontal(locations);
        }
        flotilla.logLocationCells(locations);
        System.out.println("The print of LOCATIONS ArrayList: " + locations);
        return  locations;
    }

    /**
     * Sets location cells for vertical c2 subs.
     * */
    public ArrayList<String> c2Vertical(ArrayList locations){
        int x = randomizer.nextInt(10);
        System.out.println("x = "+x);
        int y = randomizer.nextInt(9);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y) && flotilla.checkLocation(x, y+1)) {
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y+1));
        } else {
            c2Vertical(locations);
        }
        flotilla.logLocationCells(locations);
        System.out.println("The print of LOCATIONS ArrayList: " + locations);
        return  locations;
    }
}
