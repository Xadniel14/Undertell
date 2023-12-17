import java.util.ArrayDeque;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;

public abstract class AbstractEvent {
    private final static ArrayDeque<Consumer<Hero>> EVENTS = new ArrayDeque<>();

    public static boolean noEventsLeft() {
        return (EVENTS.isEmpty());
    }

    public static void loadEvents()  {
        EVENTS.addLast(AbstractEvent::applesAndBeans);
        EVENTS.addLast(AbstractEvent::tutorialFight);
        EVENTS.addLast(AbstractEvent::trappedRoom);
        EVENTS.addLast(AbstractEvent::lichEvent);
    }

    public static void explore(Hero player) {
        System.out.println("Exploring...");
        Objects.requireNonNull(EVENTS.poll()).accept(player);
        if (Fight.didFleeSuccessfully()) EVENTS.addFirst(AbstractEvent::fight);
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

    public static void fight(Hero player) {
        Fight.fight(player, Fight.getLastFledEnemy());
    }

    public static void tutorialFight(Hero player) {
        Scanner in = new Scanner(System.in);

        System.out.println("\nThe game will begin now.");

        System.out.println("NOTICE: If you wish to skip the tutorial, write \"skip\"");
        if (!in.nextLine().equalsIgnoreCase("skip")) startTutorial(player);

        Fight.fight(player, new Skeleton());
        System.out.print("\n");
    }

    private static void startTutorial(Hero player) {
        System.out.println("Tutorial: You are " + player.getName() + " and was tasked to go gather some lumber.");
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

    public static void trappedRoom(Hero player) {
        System.out.println("Part 2 of the story will now begin...");
        System.out.println("You continue to venture within the cavern.");
        Main.waitForResponse();

        System.out.println("As you walked through the cavern, you noticed something glowing within a room-like section of the cavern.");
        System.out.println("You think hard to yourself, and decides: ");
        Main.waitForResponse();

        Main.giveChoice(player,
                Map.entry("Check it out,", n -> {
                    System.out.println("You decided to see the origin of the light.");
                    treasureRoom(player);
                }), Map.entry("Avoid it as it could be a trap.", n -> System.out.println("You decided to refrain from approaching it.")));

        System.out.println("\nYou continued to traverse through the cavern.");
        System.out.println("When suddenly, the wall collapses and an Armored Skeleton appeared!");

        Fight.fight(player, new ArmoredSkeleton());

        System.out.println("A final moment of respite before the end.\n");
    }

    public static void treasureRoom(Hero player) {
        System.out.println("You carefully followed through the room-like section and found... an odd glowing chest.");
        System.out.println("You looked at the chest expectantly, what will you do?\nOpen it?");

        Main.giveChoice(player,
                Map.entry("Yes", n -> {
                    System.out.println("\nYou decided to approach the chest and open it. As you opened the chest, you feel a hot burning sensation on your right waist.");
                    System.out.println("You've been hit by a spike trap! Lost: 80 Hp!");

                    player.takeDamage(80);
                    if (player.getCurrentHealth() <= 0) System.exit(0);

                    System.out.print("Injured, but you successfully grabbed the item. ");
                    Hero.Inventory.getItem(GoldenApple.GoldenApple, 1);
                }), Map.entry("No", n -> System.out.println("You decided to stop at the last minute as it was too risky.")));
    }

    public static void lichEvent(Hero player) {
        System.out.println("\nPart 3 of the story will now begin...\n");
        System.out.println("You wonder to yourself, \"How long has it been since I first fell on this cavern?...\"");
        System.out.println("Your vision begins to blur... You are hungry and thirsty.");
        Main.waitForResponse();

        System.out.println("walking...");
        System.out.println("You hear loud footsteps...");
        Main.waitForResponse();

        System.out.println("You looked up and was horrified in what you saw.");
        System.out.println("Right before you, is the throne room of what seems to be an undead king.");
        Main.waitForResponse();

        System.out.println("The Undead Lich appears before you!");
        System.out.println("You shiver in fear, but is left with no choice.");
        Main.waitForResponse();

        System.out.println("Once more, you held your battle-worn axe, as the fight begins!");

        Fight.fight(player, new UndeadLich());

        System.out.println("...");
        Main.waitForResponse();

        System.out.println("Did you make it, " + player.getName() + "?");
        System.out.println("If yes, you truly are a wonderful player.");
        Main.waitForResponse();

        System.out.println("Thank you for playing :)");
        System.out.println("This took years to do");
        Main.waitForResponse();

        System.out.println("Goodbye! :)");
    }
}
