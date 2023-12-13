import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;

public abstract class AbstractEvent {
    private static ArrayBlockingQueue<Consumer<Hero>> events;

    public static void loadEvents() {
        events = new ArrayBlockingQueue<>(4);
    }

    public static void explore(Hero player) {
        System.out.println("Exploring...");
        events.poll().accept(player);
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
}
