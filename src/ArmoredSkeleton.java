import java.util.Map;

public class ArmoredSkeleton extends Skeleton {
    public ArmoredSkeleton() {
        super();

        this.setName("Heavy Bones");
        this.setAttack(11);

        this.setMaxHealth(350);
        this.heal(100);

        ACTIONS.set(0, Map.entry(5, p -> {
            if (this.isPrepared()) new EnemyAttack(0, 5, 0.3, " but gets parried", "slashes its sword", "rushed towards you and launched a heavy sword slash").accept(p);
            else new EnemyAttack(0, 5, 0, " but gets parried", "slashes its sword", "rushed towards you and launched a heavy sword slash").accept(p);
        }));
    }
}
