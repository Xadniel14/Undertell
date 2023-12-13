import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter The Name Of Player");

        Hero player = new Hero(in.nextLine());

        while (player.getCurrentHealth() > 0) {
            player.displayStatus();

            System.out.println("|---------MENU---------|");
            giveChoice(player,
                    Map.entry("EXPLORE", AbstractEvent::explore),
                    Map.entry("INVENTORY", Hero.Inventory::displayInventory),
                    Map.entry("REST", AbstractEvent::rest),
                    Map.entry("QUIT PROGRAM", Main::confirmQuit));
        }
    }

    @SafeVarargs
    public static <T> void giveChoice(T t, Map.Entry<String, Consumer<T>>... choices) {
        for (int i = 0; i < choices.length; ++i) {
            System.out.printf("%d. %s%n", (i + 1), choices[i].getKey());
        }

        while (true) {
            try {
                choices[in.nextInt() - 1].getValue().accept(t);
                break;
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Please Input a Valid Choice.");
            }
        }
    }

    public static void waitForResponse() {
        System.out.println("\nPress Enter To Continue: \nType \"Exit\" if you wish to quit program.");

        if (in.nextLine().equalsIgnoreCase("exit")) confirmQuit(null);
    }

    public static void confirmQuit(Hero player) {
        System.out.println("Are you sure you want to quit game?");

        giveChoice(null,
                Map.entry("Yes", x -> {System.out.println("Shutting Down."); System.exit(0);}),
                Map.entry("No", x -> System.out.println("Cancelled.")));
    }
}