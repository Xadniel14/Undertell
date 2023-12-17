public class UndeadLich extends Enemy {
    private final int MAGIC_ATTACK;
    public UndeadLich() {
        super("The Undead Lich", 900, 40, "a devastating spell");
        this.MAGIC_ATTACK = 60;
    }

    public void decideAction(Hero player) {

    }
}
