import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Enemy extends Entity {
    public class EnemyAttack implements Consumer<Hero> {
        private final int ATTACK_PENALTY;
        private final int DEFENCE_PENALTY;
        private double MISS_CHANCE;

        private final String DEFENDED_SUFFIX;

        private String hitString;
        private String critString;

        public EnemyAttack(int ATTACK_PENALTY, int DEFENCE_PENALTY, double MISS_CHANCE, String DEFENDED_SUFFIX, String hitString, String critString) {
            this.ATTACK_PENALTY = ATTACK_PENALTY;
            this.DEFENCE_PENALTY = DEFENCE_PENALTY;
            this.DEFENDED_SUFFIX = DEFENDED_SUFFIX;
            this.MISS_CHANCE = MISS_CHANCE;
            this.hitString = hitString;
            this.critString = critString;
        }

        @Override
        public void accept(Hero player) {
            String result = "";
            int damage = Enemy.this.getAttack() - ATTACK_PENALTY;

            if (player.isDefending()) {
                damage -= DEFENCE_PENALTY;
                result = DEFENDED_SUFFIX;
            }

            if (Enemy.this.isPrepared && critString != null) {
                Enemy.this.setPrepared(false);
                damage *= 2;
                result = critString + result;
            } else result = hitString + result;

            if (Math.random() <= MISS_CHANCE) {
                System.out.printf("%s missed its attack!%n", Enemy.this.getName());
                if (!(Enemy.this instanceof UndeadLich)) Enemy.this.setPrepared(false);
                return;
            }

            player.takeDamage(damage);
            System.out.printf("%s %s and deals: %d damage! You now have: %d hp left.%n", Enemy.this.getName(), result, damage, player.getCurrentHealth());
        }
    }

    private boolean isPrepared;

    private final String PREPARED_STRING;

    public ArrayList<Map.Entry<Integer, Consumer<Hero>>> actions;

    public Enemy(String name, int health, int attack, String PREPARED_STRING) {
        super(name, health, attack);
        this.PREPARED_STRING = PREPARED_STRING;

        actions = new ArrayList<>();
        isPrepared = false;
    }

    @Override
    public String getDisplayIdentifier() {
        return "ENEMY";
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public void decideAction(Hero player) {
        double unitRange = 1d / actions.stream().mapToInt(Map.Entry::getKey).reduce(0, Integer::sum);
        double cumulativeSum = 0;

        double roll = Math.random();

        for (Map.Entry<Integer, Consumer<Hero>> action : actions) {
            double currentChance = action.getKey() * unitRange;

            if (roll <= currentChance + cumulativeSum) {
                action.getValue().accept(player);
                break;
            }

            cumulativeSum += currentChance;
        }
    }

    public void prepare(Hero player) {
        System.out.printf("%s is preparing %s%n", getName(), PREPARED_STRING);
        setPrepared(true);
    }
}