import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;

public abstract class AbstractEvent {
    private final static ArrayBlockingQueue<Consumer<Hero>> EVENTS = new ArrayBlockingQueue<>(4);

    public static void loadEvents() throws InterruptedException {
        EVENTS.put(AbstractEvent::applesAndBeans);
    }

    public static void explore(Hero player) {
        System.out.println("Exploring...");
        EVENTS.poll().accept(player);
    }

    public static void rest(Hero player) {
        if (Math.random() <= 0.5) {
            int healthHealed = (int) (Math.random() * 50) + 10;

            System.out.printf("You Rested Successfully and healed: %d HP.%n", healthHealed);
            player.heal(healthHealed);
        } else {
            int damageTaken = (int) (Math.random() * 20) + 5;

            System.out.printf("Failed to rest, you were attacked by bugs in your sleep and lost: %d HP%n", damageTaken);
            player.takeDamage(damageTaken);
        }
    }

    public static void applesAndBeans(Hero player) {
        pickupItem("You found something on the ground.", HealingItems.Bean.getITEM(), 1);
        pickupItem("You see some apples on a table.", HealingItems.Apple.getITEM(), 5);
    }

    private static void pickupItem(String message, Item item, int count) {
        System.out.println(message);
        System.out.println("Pick it up? (1: Yes, 2: No).");

        Main.getIntChoice(n -> {
            if (n == 1) Hero.Inventory.getItem(item, count);
            else System.out.println("You decided not to pick it up.");
        });
    }
}
