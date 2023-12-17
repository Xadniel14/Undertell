import java.util.Map;

public class Fight {
    private static boolean didFleeSuccessfully;

    private static boolean usedItemIsNull;

    public static void setDidFleeSuccessfully(boolean didFleeSuccessfully) {
        Fight.didFleeSuccessfully = didFleeSuccessfully;
    }

    public static void setUsedItemIsNull(boolean usedItemIsNull) {
        Fight.usedItemIsNull = usedItemIsNull;
    }

    public static void fight(Hero player, Enemy enemy) {
        didFleeSuccessfully = false;

        System.out.println("|| Fight Begins ||");

        while (true) {
            usedItemIsNull = false;
            player.setDefending(false);

            player.displayStatus();
            enemy.displayStatus();

            Main.giveChoice(enemy,
                    Map.entry("Fight,", player::attack),
                    Map.entry("Defend,", player::defend),
                    Map.entry("Use Item,", e -> Hero.Inventory.displayInventory(player)),
                    Map.entry("Flee.", e -> player.flee(!(e instanceof UndeadLich))));

            if (enemy.getCurrentHealth() <= 0 || didFleeSuccessfully) break;
            else if (usedItemIsNull) continue;

            enemy.decideAction(player);

            if (player.getCurrentHealth() <= 0) {
                break;
            }
        }
    }
}
