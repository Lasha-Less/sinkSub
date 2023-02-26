import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Flotilla flotilla = new Flotilla();
        Random randomizer = new Random();
        C4Factory c4Factory = new C4Factory(flotilla);
        C3Factory c3Factory = new C3Factory(flotilla);
        C2Factory c2Factory = new C2Factory(flotilla);
        C1Factory c1Factory = new C1Factory(flotilla);
        Battle battle = new Battle(flotilla, c1Factory, c2Factory, c3Factory, c4Factory);

        battle.setupBattlefield();
        battle.startBattle();



        /**
         * Useful way to convert char to int
         * */
//        String secondCell = "5b";
//        int firstChar = Character.getNumericValue(secondCell.charAt(0));
//        System.out.println(firstChar-1);


    }
}