import java.util.HashMap;
import java.util.Scanner;

public class Hero extends Entity {
    public static class Inventory {
        public static void displayInventory(Hero player) {
            System.out.println("The Items currently in your inventory:");

            for (Item item : INVENTORY.keySet()) {
                int count = INVENTORY.get(item);
                System.out.printf("%s x%d%n", item.getNAME(), count);
            }

            selectItem(player);
        }

        public static void selectItem(Hero player) {
            Scanner in = new Scanner(System.in);

            System.out.println("Please Enter The Name Of Item You Want To Use. Type \"Back\" to return.");

            String choice = in.nextLine();
            if (choice.equalsIgnoreCase("back")) return;

            for (Item item : INVENTORY.keySet()) {
                if (choice.equalsIgnoreCase(item.getNAME())) {
                    useItem(item, player);
                    return;
                }
            }

            System.out.println("You don't have that item.");
        }

        public static void getItem(Item item, int count) {
            INVENTORY.merge(item, count, (k, v) -> v + count);

            System.out.printf("You picked up: %s x%d%n", item.getNAME(), INVENTORY.get(item));
            System.out.println(item.getDESCRIPTION());
        }

        public static void useItem(Item item, Hero player) {
            item.onUse(player);

            if (INVENTORY.get(item) == 1) INVENTORY.remove(item);
            else INVENTORY.put(item, INVENTORY.get(item) - 1);
        }
    }

    private final static HashMap<Item, Integer> INVENTORY = new HashMap<>();

    public Hero(String name) {
        super(name, 150, 25);
    }

    @Override
    protected String getDisplayIdentifier() {
        return "NAME";
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);

        int hp = getCurrentHealth();
        if (hp <= 0) {
            System.out.printf("%s's hp dropped to %d and has fallen.%n", getNAME(), hp);
            Main.waitForResponse();
        }
    }
}