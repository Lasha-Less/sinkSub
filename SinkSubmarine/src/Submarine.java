import java.util.ArrayList;

public class Submarine {

    private int size; //int 1 to 4;
    private Shape shape;
    private Orientation orientation;
    private ArrayList<String> locationCells;
    private ArrayList<String> marginalSpace;

    public Submarine(int size, Shape shape, Orientation orientation, ArrayList<String> locationCells) {
        this.size = size;
        this.shape = shape;
        this.orientation = orientation;
        this.locationCells = locationCells;
    }

    public Submarine(int size, Shape shape, ArrayList<String> locationCells) {
        this.size = size;
        this.shape = shape;
        this.locationCells = locationCells;
    }

    /**
     * Checks if the shot received was a kill, miss or hit.
     * */
    public String hitOrMiss(String coordinates){
        String result = "miss";
        if (locationCells.contains(coordinates)){
            locationCells.remove(coordinates);
            if (locationCells.isEmpty()){
                result = "kill";
//                System.out.println("Congrats! You just sunk the sub.");
            } else {
                result = "hit";
            }
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public Shape getShape() {
        return shape;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public ArrayList<String> getLocationCells() {
        return locationCells;
    }

    public ArrayList<String> getMarginalSpace() {
        return marginalSpace;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    public void setMarginalSpace(ArrayList<String> marginalSpace) {
        this.marginalSpace = marginalSpace;
    }
}
