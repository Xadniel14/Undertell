import java.util.Map;

public class UndeadLich extends Enemy {
    private final int MAGIC_ATTACK;
    public UndeadLich() {
        super("The Undead Lich", 900, 40, "a devastating spell");
        this.MAGIC_ATTACK = 60;

        actions.add(Map.entry(7, p -> {
            if (this.isPrepared())
                new EnemyAttack(0, 20, 0.1, " but gets parried", "lunges towards you with its claws", "lunges towards you with its claws catching you off guard").accept(p);
            else
                new EnemyAttack(0, 20, 0, " but gets parried", "lunges towards you with its claws", "lunges towards you with its claws catching you off guard").accept(p);
        }));
        actions.add(Map.entry(4, this::prepare));
        actions.add(Map.entry(2, new EnemyAttack(0, 0, 0.2, "\nYou were able to block some of the bone spears but gets hit nonetheless", "'s bone spears pierces your body", null)));
        actions.add(Map.entry(2, p -> System.out.println("The Lich recuperates for a moment.")));
        actions.add(Map.entry(2, this::heal));
    }

    @Override
    public void displayStatus(int attack) {
        System.out.printf("%s: %s || ", getDisplayIdentifier(), getName());
        System.out.printf("HP: %d/%d || ", getCurrentHealth(), getMaxHealth());
        System.out.printf("ATTACK: %d || ", attack);
        System.out.printf("MAGIC ATTACK: %d%n", MAGIC_ATTACK);
    }

    public void heal(Hero player) {
        int damage = (int) (Math.random() * 30);

        player.takeDamage(damage);
        System.out.printf("The Lich consumes some of your health: %d and healed: %d hp!%n", damage, damage);

        heal(damage);
    }

    public void magicAttack(Hero player) {
        System.out.printf("The Lich casts a spell upon you causing: %d damage! You now have: %d hp left.%n", MAGIC_ATTACK, player.getCurrentHealth());
        player.takeDamage(MAGIC_ATTACK);
    }

    @Override
    public void decideAction(Hero player) {
        if (!this.isPrepared() && !player.isDefending()) actions.add(Map.entry(3, this::magicAttack));
        super.decideAction(player);
    }
}
