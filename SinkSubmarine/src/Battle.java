import java.util.ArrayList;
import java.util.Scanner;

public class Battle {

    private final static String Y_COORDINATES = "abcdefghij";
    private final Flotilla flotilla;
    private final C1Factory c1Factory;
    private final C2Factory c2Factory;
    private final C3Factory c3Factory;
    private final C4Factory c4Factory;
    private ArrayList<Submarine> formation = new ArrayList<>();
    private int numOfShots = 0;


    public Battle(Flotilla flotilla, C1Factory c1Factory, C2Factory c2Factory, C3Factory c3Factory,
                  C4Factory c4Factory) {
        this.flotilla = flotilla;
        this.c1Factory = c1Factory;
        this.c2Factory = c2Factory;
        this.c3Factory = c3Factory;
        this.c4Factory = c4Factory;
    }

    /**
     * Setup battlefield formation of ten subs (1x 4cSub, 2x 3cSub, 3x 2cSub, 4x 1cSub).
     * */
    public void setupBattlefield(){
        System.out.println();
        System.out.println("Battlefield formation initiated... ");
        System.out.println();
//        1st sub 4C - create and add to the formation.
        Submarine c4Random = c4Factory.randomizeC4();
        formation.add(c4Random);
//        log location cells of the above sub into the grid.
        flotilla.logLocationCells(c4Random.getLocationCells());
//        Creates ArrayList for marginal space
        ArrayList<String> marginalSpace = flotilla.marginSetter(c4Random.getLocationCells());
        System.out.println();
//        Log marginal space locations to the grid.
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        2nd sub 3C - create and add to the formation.
        Submarine c3SubOne = c3Factory.randomizeC3Sub();
        formation.add(c3SubOne);
//        System.out.println();
//        Add location cells of all existing subs in one array
        ArrayList<String> allLocRound2 = new ArrayList<>(c3SubOne.getLocationCells());
        allLocRound2.addAll(c4Random.getLocationCells());
//        System.out.println();
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound2);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        3rd sub 3C - create and add to the formation.
        Submarine c3SubTwo = c3Factory.randomizeC3Sub();
        formation.add(c3SubTwo);
//        Add location cells of all existing subs in one array
        ArrayList<String>allLocRound3 = new ArrayList<>(c3SubTwo.getLocationCells());
        allLocRound3.addAll(allLocRound2);
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound3);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        4th sub 2C - create and add to the formation.
        Submarine c2SubOne = c2Factory.randomizeC2Sub();
        formation.add(c2SubOne);
//        Add location cells of all existing subs in one array
        ArrayList<String>allLocRound4 = new ArrayList<>(c2SubOne.getLocationCells());
        allLocRound4.addAll(allLocRound3);
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound4);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        5th sub 2C - create and add to the formation.
        Submarine c2SubTwo = c2Factory.randomizeC2Sub();
        formation.add(c2SubTwo);
//        Add location cells of all existing subs in one array
        ArrayList<String>allLocRound5 = new ArrayList<>(c2SubTwo.getLocationCells());
        allLocRound5.addAll(allLocRound4);
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound5);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        6th sub 2C - create and add to the formation.
        Submarine c2SubThree = c2Factory.randomizeC2Sub();
        formation.add(c2SubThree);
//        Add location cells of all existing subs in one array
        ArrayList<String>allLocRound6 = new ArrayList<>(c2SubThree.getLocationCells());
        allLocRound6.addAll(allLocRound5);
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound6);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        7th sub 1C - create and add to the formation.
        Submarine c1SubOne = c1Factory.c1Builder();
        formation.add(c1SubOne);
//        Add location cells of all existing subs in one array
        ArrayList<String>allLocRound7 = new ArrayList<>(c1SubOne.getLocationCells());
        allLocRound7.addAll(allLocRound6);
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound7);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        8th sub 1C - create and add to the formation.
        Submarine c1SubTwo = c1Factory.c1Builder();
        formation.add(c1SubTwo);
//        Add location cells of all existing subs in one array
        ArrayList<String>allLocRound8 = new ArrayList<>(c1SubTwo.getLocationCells());
        allLocRound8.addAll(allLocRound7);
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound8);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        9th sub 1C - create and add to the formation.
        Submarine c1SubThree = c1Factory.c1Builder();
        formation.add(c1SubThree);
//        Add location cells of all existing subs in one array
        ArrayList<String>allLocRound9 = new ArrayList<>(c1SubThree.getLocationCells());
        allLocRound9.addAll(allLocRound8);
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound9);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
//        10th sub 1C - create and add to the formation.
        Submarine c1SubFour = c1Factory.c1Builder();
        formation.add(c1SubFour);
//        Add location cells of all existing subs in one array
        ArrayList<String>allLocRound10 = new ArrayList<>(c1SubFour.getLocationCells());
        allLocRound10.addAll(allLocRound9);
//        Generate marginalSpace array list again for all existing subs.
        marginalSpace.clear();
        marginalSpace = flotilla.marginSetter(allLocRound10);
        System.out.println();
//        Log grid locations of marginalSpace
        flotilla.logMarginalSpace(marginalSpace);
        System.out.println();
        System.out.println("Battlefield ready.");
        System.out.println();
    }

    /**
     * Starts battle by taking shot coordinates and checking the results.
     * When all submarines are sunk it ends the battle.
     * */
    public void startBattle(){
        while (!formation.isEmpty()){
            shoot(takeCoordinates("Please provide the coordinates for the shot"));
        }
        endMessage();
    }

    /**
     * Takes coordinates from the user for a shot and checks them if they are within bounds.
     * !NEEDS TO BE TESTED!
     * */
    public String takeCoordinates(String ask){
        String please = "Please provide valid coordinates.";
        System.out.println(ask + ": ");
        Scanner scan = new Scanner(System.in);
        String coordinates = scan.nextLine().toLowerCase();
        while (coordinates.length()>2){
            System.out.println("The coordinates should only have two symbols, first, a number between 0 and 9, " +
                    "and second a letter between a and j." + please);
            System.out.println(ask + ": ");
            coordinates = scan.nextLine().toLowerCase();
        }
        while (!flotilla.isWithinBounds(Character.getNumericValue(coordinates.charAt(0))) ||
                !flotilla.isWithinBounds(Y_COORDINATES.indexOf(coordinates.charAt(1)))){
            System.out.println("One or both of the characters of your coordinates is out of bounds."+please);
            System.out.println(ask + ": ");
            coordinates = scan.nextLine().toLowerCase();
        }
        return coordinates;
    }

    /**
     * Takes the coordinates of a shot and checks if it was a miss, kill or hit by going through entire formation.
     * */
    public void shoot(String coordinates){
        numOfShots++;
        String result = "miss";
        for (Submarine submarine : formation){
            result = submarine.hitOrMiss(coordinates);
            if (result.equals("hit")){
                break;
            }
            if (result.equals("kill")){
                formation.remove(submarine);
                result = result + ". Congrats! You just sunk the sub.";
                break;
            }
        }
        System.out.println(result);
    }

    /**
     * Displays end messages of the game.
     * */
    public void endMessage(){
        System.out.println("GAME OVER!");
        if (numOfShots < 41){
            System.out.println("Congratulations! You are a promising artillery prodigy.");
        } else {
            System.out.println("We are not impressed. "+ numOfShots +
                    " shots are a lot. If you had your own submarine formation, you would've been obliterated by now.");
        }
    }

}
