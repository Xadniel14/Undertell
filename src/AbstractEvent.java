import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;

public abstract class AbstractEvent {
    private final static ArrayBlockingQueue<Consumer<Hero>> EVENTS = new ArrayBlockingQueue<>(4);

    public static void loadEvents() throws InterruptedException {
        EVENTS.put(AbstractEvent::applesAndBeans);
        EVENTS.put(AbstractEvent::tutorialFight);
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

    public static void tutorialFight(Hero player) {
        Scanner in = new Scanner(System.in);

        System.out.println("\nThe game will begin now.");

        System.out.println("NOTICE: If you wish to skip the tutorial, write \"skip\"");
        if (!in.nextLine().equalsIgnoreCase("skip")) startTutorial(player);

        Fight.fight(player, new Skeleton());
    }

    private static void startTutorial(Hero player) {
        System.out.println("Tutorial: You are " + player.getNAME() + " and was tasked to go gather some lumber.");
        System.out.println("Tutorial: However, as you went to the mountain, you were struck with an unfortunate event.");
        System.out.println("As you stepped on the snowy surface of the mountain. You suddenly fell..?");
        Main.waitForResponse();

        System.out.println("You woke up feeling nauseous. You look at your surroundings, you're in a cavern.");
        System.out.println("You looked up to see where you have fallen from. It's a miracle you somehow survived the fall.");
        Main.waitForResponse();

        System.out.println("...You hear some odd rattling.");
        System.out.println("You begin to shake as you see a deathly figure emerge from within the darkness.");
        Main.waitForResponse();

        System.out.println("You immediately grabbed your wood-cutting axe that fell along with you");
        System.out.println("The figure dashed towards you!");
        Main.waitForResponse();

        System.out.println("Since this is your first time having an attack. A brief introduction will be provided.");
        System.out.println("During a attack you have 4 options:");
        System.out.println("1, Fight: This is used to deal damage against the enemy. The damage is dependent on your character's attack status. There's also a chance of dealing critical damage.");
        System.out.println("2, Defend: a tricky move. It uses the same concept as \"parrying\", if you believe that the enemy will be launching a heavy hit on his turn, use this to reduce damage taken.");
        System.out.println("3, Use Item: this option will allow you to use items that are in your inventory. However, this also ends your current turn. Use it wisely.");
        System.out.println("4, Flee: If things go south and you believe it is certain death. Flee. However, there's a chance that fleeing may be unsuccessful and puts you vulnerable on the enemy's turn.");
        System.out.println("4, Flee: IMPORTANT: successful flee will only bring you to Menu, once you explore, you'll be brought back to the previous attack until you finish it.");
        System.out.println("That covers the basics of the game mechanics. Survive. See what lies within the darkness.");
        Main.waitForResponse();
    }
}
