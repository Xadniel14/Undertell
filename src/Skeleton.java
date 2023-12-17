import java.util.Map;

public class Skeleton extends Enemy {
    public Skeleton() {
        super("Bones", 250, 13, "to launch a heavy attack.");

        ACTIONS.add(Map.entry(5, p -> {
            if (this.isPrepared()) new EnemyAttack(0, 5, 0.1, " but gets parried", "slashes its sword", "rushed towards you and launched a heavy sword slash").accept(p);
            if (this.isPrepared() && p.isDefending())
                new EnemyAttack(0, 5, 0.3, " but gets parried", "slashes its sword", "rushed towards you and launched a heavy sword slash").accept(p);
            else new EnemyAttack(0, 5, 0, " but gets parried", "slashes its sword", "rushed towards you and launched a heavy sword slash").accept(p);
        }));
        ACTIONS.add(Map.entry(2, this::prepare));
        ACTIONS.add(Map.entry(1, new EnemyAttack(5, 3, 0.3, " but was able to deflect it", "picked a bone from its body and threw it at you", null)));
        ACTIONS.add(Map.entry(1, p -> System.out.println("The enemy remains still and growls at you.")));
        ACTIONS.add(Map.entry(1, this::randomHeal));
    }

    public void randomHeal(@SuppressWarnings("unused") Hero player) {
        int healthHealed = (int) (Math.random() * 30);

        System.out.printf("The enemy consumes: ?? and healed: %d hp!%n", healthHealed);
        super.heal(healthHealed);
    }
}