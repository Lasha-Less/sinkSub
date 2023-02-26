import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.TreeSet;

public class Flotilla {
    private static final int X_LENGTH = 10;
    private static final int Y_LENGTH = 10;
    private final static String Y_COORDINATES = "abcdefghij";
    private final static int GRID_SIZE = 100;
    private final static int GRID_LIMIT = 99;
    private final int[][] grid = new int[10][10];

    private final Random randomizer = new Random();

    public int[][] getGrid() {
        return grid;
    }


    /**
     * randomizes orientation of a sub along two axes.
     * */
    public Orientation xyOrientation(){
        int xy = randomizer.nextInt(2);
        if (xy == 1){
            return Orientation.UP;
        } else {
            return Orientation.LEFT;
        }
    }

    /**
     * randomizes orientation of a sub between UP and DOWN.
     * */
    public Orientation upOrDownOrientation(){
        int xy = randomizer.nextInt(2);
        if (xy == 1){
            return Orientation.UP;
        } else {
            return Orientation.DOWN;
        }
    }

    /**
     * randomizes orientation of a sub between LEFT and RIGHT.
     * */
    public Orientation lOrROrientation(){
        int xy = randomizer.nextInt(2);
        if (xy == 1){
            return Orientation.RIGHT;
        } else {
            return Orientation.LEFT;
        }
    }

    /**
     * Randomizes orientation of a sub among four directions.
     * */
    public Orientation fourWayOrientation(){
        int fourWay = randomizer.nextInt(4);
        if (fourWay == 0){
            return Orientation.UP;
        } else if (fourWay==1){
            return Orientation.LEFT;
        } else if (fourWay==2){
            return Orientation.DOWN;
        } else {
            return Orientation.RIGHT;
        }
    }

    /**
     * Randomizes shape of a 3c sub.
     * */
    public Shape shapeOf3(){
        int xy = randomizer.nextInt(2);
        if (xy == 1){
            return Shape.THREE_CELL_STRAIGHT;
        } else {
            return Shape.THREE_CELL_CURVED;
        }
    }


    /**
     * randomizes shape of 4c sub between L, Mirror and Z.
     * */
    public Shape mirrorLZ(){
        Shape shape;
        int threeWay = randomizer.nextInt(3);
        if (threeWay==0){
            shape = Shape.FOUR_CELL_L;
        } else if (threeWay==1) {
            shape = Shape.FOUR_CELL_MIRROR_L;
        } else {
            shape = Shape.FOUR_CELL_ZIG;
        }
        return shape;
    }

    /**
     * Builds String array with marginal space cells for any collection of cells.
     * */
    public ArrayList<String> marginSetter(ArrayList<String> locationCells){
        TreeSet<String>checkedCells = new TreeSet<>();
        for (int i = 0; i < locationCells.size(); i++) {
            checkedCells.addAll(circleLocationCell(locationCells.get(i), locationCells));
        }
        ArrayList<String>marginalSpace = new ArrayList<>(checkedCells);
        return marginalSpace;
    }

    /**
     * Logs coordinates based on marginal space arrayList String objects to the grid.
     * */
    public void logMarginalSpace(ArrayList<String>marginalSpace){
        for (int i = 0; i < marginalSpace.size(); i++) {
            int x = Character.getNumericValue(marginalSpace.get(i).charAt(0));
            int y = Y_COORDINATES.indexOf(marginalSpace.get(i).charAt(1));
            grid[x][y] = 2;
            System.out.println("MARGINAL SPACE. cell location: [x]"+x + ", [y]"+y+", value: "+ grid[x][y]);
        }
    }

    /**
     * Logs coordinates based on marginal space arrayList String objects to the grid.
     * */
    public void logLocationCells(ArrayList<String>locationCells){
        for (int i = 0; i < locationCells.size(); i++) {
            int x = Character.getNumericValue(locationCells.get(i).charAt(0));
            int y = Y_COORDINATES.indexOf(locationCells.get(i).charAt(1));
            grid[x][y] = 1;
            System.out.println("LOCATION CELLS. cell location: [x]"+x + ", [y]"+y+", value: "+ grid[x][y]);
        }
    }

    /**
     * Checks if the given coordinate is within bounds of the grid.
     * */
    public boolean isWithinBounds(int coordinate){
        if (coordinate < 0 || coordinate > 9){
            return false;
        }else {
            return true;
        }
    }

    /**
     * Checks if the given location equals 0 in the grid.
     * */
    public boolean checkLocation(int x, int y){
        if (grid[x][y]==0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Circles a location cell and checks whether each of the neighboring cells is available and within bounds.
     * */
    public TreeSet<String> circleLocationCell(String locationCell, ArrayList<String> locationCells){
        TreeSet<String>adjacentCells = new TreeSet<>();
        //        Checks top neighbor
        if (isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))-1) &&
                !locationCells.contains(Integer.toString(Character.getNumericValue(locationCell.charAt(0))) +
                        Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) - 1))){
            adjacentCells.add(Integer.toString(Character.getNumericValue(locationCell.charAt(0))) +
                    Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) - 1));
        }
//        Checks top right neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)+1)) &&
                isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))-1) &&
                !locationCells.contains(Integer.toString(Character
                        .getNumericValue(locationCell.charAt(0) + 1)) +
                        Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) - 1))){
            adjacentCells.add(Integer.toString(Character.getNumericValue(locationCell.charAt(0) + 1)) +
                    Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) - 1));
        }
//        Check right neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)+1)) &&
                !locationCells.contains(Integer.toString(Character
                        .getNumericValue(locationCell.charAt(0) + 1))+locationCell.charAt(1))){
            String firstCellXPlus1 = Integer.toString(Character.getNumericValue(locationCell.charAt(0) + 1));
            adjacentCells.add(firstCellXPlus1 + locationCell.charAt(1));
        }
//        Check low right neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)+1)) &&
                isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1)+1)) &&
                !locationCells.contains(Integer.toString(Character
                        .getNumericValue(locationCell.charAt(0) + 1)) +
                        Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) + 1))){
            adjacentCells.add(Integer.toString(Character.getNumericValue(locationCell.charAt(0) + 1)) +
                    Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) + 1));
        }
//        Check lower neighbor
        if (isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))+1) &&
                !locationCells.contains(Integer.toString(Character.getNumericValue(locationCell.charAt(0))) +
                        Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) + 1))){
            adjacentCells.add(Integer.toString(Character.getNumericValue(locationCell.charAt(0))) +
                    Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) + 1));
        }
//        Check low left neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)-1)) &&
                isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))+1) &&
                !locationCells.contains(Integer.toString(Character
                        .getNumericValue(locationCell.charAt(0) - 1)) +
                        Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) + 1))){
            adjacentCells.add(Integer.toString(Character.getNumericValue(locationCell.charAt(0) - 1)) +
                    Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) + 1));
        }
//        Check left neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)-1))
                && !locationCells.contains(Integer.toString(Character
                .getNumericValue(locationCell.charAt(0) - 1))+locationCell.charAt(1))){
            String firstCellXMin1 = Integer.toString(Character.getNumericValue(locationCell.charAt(0) - 1));
            adjacentCells.add(firstCellXMin1 + locationCell.charAt(1));
        }
//        Check top left neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)-1)) &&
                isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))-1) &&
                !locationCells.contains(Integer.toString(Character
                        .getNumericValue(locationCell.charAt(0) - 1)) +
                        Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) - 1))){
            adjacentCells.add(Integer.toString(Character.getNumericValue(locationCell.charAt(0) - 1)) +
                    Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) - 1));
        }
        return adjacentCells;
    }

    /**
     * this is a working copy of above method. Use this to modify it so that the yellow underlining warnings
     * disappear. Hint: optional
     * RESULT:
     * I altered the method, but I still do not like it. I only managed to make it around 8 lines shorter, and it just
     * seems not to be worth it to risk all the possible bugs that may spring into existence if I actually replace the
     * above method with this one.
     * */
    public TreeSet<String> aroundCell (String locationCell, ArrayList<String> locationCells){
        TreeSet<String>adjacentCells = new TreeSet<>();
//        Checks top neighbor
        String xOfTopNeighbor = Integer.toString(Character.getNumericValue(locationCell.charAt(0)));
        char yOfTopNeighbor = Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) - 1);
        String topNeighbor = xOfTopNeighbor + yOfTopNeighbor;
        Optional<String> opTopNeighbor = Optional.of(topNeighbor);

        if (isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))-1) &&
                !locationCells.contains(topNeighbor)){
            adjacentCells.add(opTopNeighbor.get());
        }
//        Checks top right neighbor
        String xOfTopRightNeighbor = Integer.toString(Character.getNumericValue(locationCell.charAt(0) + 1));
        String topRightNeighbor = xOfTopRightNeighbor + yOfTopNeighbor;
        Optional<String>opTopRighNeighbor = Optional.of(topRightNeighbor);
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)+1)) &&
                isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))-1) &&
                !locationCells.contains(topRightNeighbor)){
            adjacentCells.add(opTopRighNeighbor.get());
        }
//        Check right neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)+1)) &&
                !locationCells.contains(xOfTopRightNeighbor +locationCell.charAt(1))){
            String firstCellXPlus1 = xOfTopRightNeighbor;
            adjacentCells.add(firstCellXPlus1 + locationCell.charAt(1));
        }
//        Check low right neighbor
        char yOfLowRightNeighbor = Y_COORDINATES.charAt(Y_COORDINATES.indexOf(locationCell.charAt(1)) + 1);
        String lowRightNeighbor = xOfTopRightNeighbor + yOfLowRightNeighbor;
        Optional<String>opLowRightNeighbor = Optional.of(lowRightNeighbor);
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)+1)) &&
                isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1)+1)) &&
                !locationCells.contains(lowRightNeighbor)){
            adjacentCells.add(opLowRightNeighbor.get());
        }
//        Check lower neighbor
        if (isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))+1) &&
                !locationCells.contains(xOfTopNeighbor + yOfLowRightNeighbor)){
            adjacentCells.add(xOfTopNeighbor + yOfLowRightNeighbor);
        }
//        Check low left neighbor
        String xOfLowLeftNeighbor = Integer.toString(Character.getNumericValue(locationCell.charAt(0) - 1));
        String lowLeftNeighbor = xOfLowLeftNeighbor + yOfLowRightNeighbor;
        Optional<String>opLowLeftNeighbor = Optional.of(lowLeftNeighbor);
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)-1)) &&
                isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))+1) &&
                !locationCells.contains(lowLeftNeighbor)){
            adjacentCells.add(opLowLeftNeighbor.get());
        }
//        Check left neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)-1))
                && !locationCells.contains(xOfLowLeftNeighbor +locationCell.charAt(1))){
            String firstCellXMin1 = xOfLowLeftNeighbor;
            adjacentCells.add(firstCellXMin1 + locationCell.charAt(1));
        }
//        Check top left neighbor
        if (isWithinBounds(Character.getNumericValue(locationCell.charAt(0)-1)) &&
                isWithinBounds(Y_COORDINATES.indexOf(locationCell.charAt(1))-1) &&
                !locationCells.contains(xOfLowLeftNeighbor + yOfTopNeighbor)){
            adjacentCells.add(xOfLowLeftNeighbor + yOfTopNeighbor);
        }
        return adjacentCells;
    }

}
