import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Fight {
    private static boolean didFleeSuccessfully;
    private static boolean usedItemIsNull;

    private static Enemy lastFledEnemy;

    public static boolean didFleeSuccessfully() {
        return didFleeSuccessfully;
    }

    public static void setDidFleeSuccessfully(boolean didFleeSuccessfully) {
        Fight.didFleeSuccessfully = didFleeSuccessfully;
    }

    public static void setUsedItemIsNull(boolean usedItemIsNull) {
        Fight.usedItemIsNull = usedItemIsNull;
    }

    public static Enemy getLastFledEnemy() {
        return lastFledEnemy;
    }

    public static void setLastFledEnemy(Enemy lastFledEnemy) {
        Fight.lastFledEnemy = lastFledEnemy;
    }

    public static void fight(Hero player, Enemy enemy) {
        didFleeSuccessfully = false;

        System.out.println("|| Fight Begins ||");

        while (true) {
            usedItemIsNull = false;
            player.setDefending(false);

            player.displayStatus(player.getAttack());

            int enemyAttack = enemy.getAttack();
            if (enemy.isPrepared()) enemyAttack *= 2;

            enemy.displayStatus(enemyAttack);

            Main.giveChoice(enemy,
                    Map.entry("Fight,", player::attack),
                    Map.entry("Defend,", player::defend),
                    Map.entry("Use Item,", e -> Hero.Inventory.displayInventory(player)),
                    Map.entry("Flee.", e -> player.flee(!(e instanceof UndeadLich))));

            if (enemy.getCurrentHealth() <= 0) break;

            if (usedItemIsNull) continue;

            if (didFleeSuccessfully) {
                try {
                    setLastFledEnemy((Enemy) enemy.getClass().getConstructors()[0].newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                break;
            }

            enemy.decideAction(player);

            if (player.getCurrentHealth() <= 0) {
                break;
            }
        }
    }
}
