import java.util.ArrayList;
import java.util.Random;

public class C1Factory {

    private final Random randomizer = new Random();
    private final static String Y_COORDINATES = "abcdefghij";
    private final Flotilla flotilla;

    public C1Factory(Flotilla flotilla) {
        this.flotilla = flotilla;
    }

    /**
     * Creates c1Sub
     * */
    public Submarine c1Builder(){
        ArrayList<String> c1Locations = new ArrayList<>();
        Submarine submarine = new Submarine(1, Shape.SINGLE_CELL, c1LocGen(c1Locations));;
        return submarine;
    }

    /**
     * Generates c1Sub location
     * */
    public ArrayList<String> c1LocGen(ArrayList<String> locations){
        int x = randomizer.nextInt(10);
        System.out.println("x = "+x);
        int y = randomizer.nextInt(10);
        System.out.println("y = "+y);
        if (flotilla.checkLocation(x, y)){
            locations.add(Integer.toString(x)+Y_COORDINATES.charAt(y));
        } else {
            c1LocGen(locations);
        }
        flotilla.logLocationCells(locations);
        return locations;
    }
}
