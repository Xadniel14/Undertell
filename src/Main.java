import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    private final static Scanner IN = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        AbstractEvent.loadEvents();

        System.out.println("Enter The Name Of Player");

        Hero player = new Hero(IN.nextLine());

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

        getIntChoice(n -> choices[n - 1].getValue().accept(t));

    }

    public static void getIntChoice(Consumer<Integer> method) {
        while (true) {
            try {
                int n = IN.nextInt();
                IN.nextLine(); // Consumes new line character.

                method.accept(n);
                break;
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                if (e instanceof InputMismatchException) IN.nextLine();

                System.out.println("Please Input a Valid Choice.");
            }
        }
    }

    public static void waitForResponse() {
        System.out.println("\nPress Enter To Continue: \nType \"Exit\" if you wish to quit program.");

        if (IN.nextLine().equalsIgnoreCase("exit")) confirmQuit(null);
    }

    public static void confirmQuit(Hero player) {
        System.out.println("Are you sure you want to quit game?");

        giveChoice(null,
                Map.entry("Yes", x -> {
                    System.out.println("Shutting Down.");
                    System.exit(0);
                }),
                Map.entry("No", x -> System.out.println("Cancelled.")));
    }
}