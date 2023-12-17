import java.util.Map;
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
            if (choice.equalsIgnoreCase("back")) {
                Fight.setUsedItemIsNull(true);
                return;
            }

            for (Item item : INVENTORY.keySet()) {
                if (choice.equalsIgnoreCase(item.getNAME())) {
                    useItem(item, player);
                    return;
                }
            }

            Fight.setUsedItemIsNull(true);
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

    private boolean isDefending;

    private final static HashMap<Item, Integer> INVENTORY = new HashMap<>();

    public Hero(String name) {
        super(name, 150, 25);
    }

    @Override
    protected String getDisplayIdentifier() {
        return "NAME";
    }

    public void setDefending(boolean defending) {
        isDefending = defending;
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

    protected void attack(Enemy enemy) {
        String attackString = "You slashed your axe and dealt";
        int damage = getAttack();

        if (Math.random() <= 0.2) {
            damage = (int) (damage * 1.5);
            attackString += " a critical damage of";
        }

        System.out.printf("%s: %d damage!", attackString, damage);

        enemy.takeDamage(damage);
        System.out.printf("The enemy was hit and lost: %d hp and now has: %d hp left.%n", damage, enemy.getCurrentHealth());
    }

    public void defend(Enemy enemy) {
        System.out.println("You prepared for an attack.");
        isDefending = true;
    }

    public void flee(boolean canFlee) {
        if (!canFlee) {
            System.out.println("Fleeing is impossible.");
            Main.waitForResponse();

            System.out.println("Impending doom approaches.");
            return;
        }

        System.out.println("You Tried To Flee The Battle...");

        if (Math.random() <= 0.15) {
            System.out.println("Fled Successfully!\n");
            System.out.println("NOTE: THERE IS NO FLEE OPTION DURING THE FINAL BATTLE.");
            System.out.println("GUIDE: THIS IS ONLY A TEMPORARY SAFE ZONE. EXPLORING WILL RESUME THE PREVIOUS FIGHT.");
            Fight.setDidFleeSuccessfully(true);
        } else System.out.println("Failed to escape!");
    }
}