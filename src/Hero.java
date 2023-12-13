import java.util.HashMap;

public class Hero extends Entity {
    public static class Inventory {
        public static void displayInventory(Hero player) {
            System.out.println("The Items currently in your inventory:");

            for (Item item : inventory.keySet()) {
                int count = inventory.get(item);
                System.out.printf("%s x%d%n", item, count);
            }
        }
    }

    private static HashMap<Item, Integer> inventory;

    public Hero(String name) {
        super(name, 150, 25);
        Hero.inventory = new HashMap<>();
    }

    public void displayStatus() {
        System.out.printf("NAME: %s || ", getName());
        System.out.printf("HP: %d/%d || ", getCurrentHealth(), getMaxHealth());
        System.out.printf("ATTACK: %d%n", getAttack());
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);

        int hp = getCurrentHealth();
        if (hp <= 0) {
            System.out.printf("%s's hp dropped to %d and has fallen.%n", getName(), hp);
            Main.waitForResponse();
        }
    }
}