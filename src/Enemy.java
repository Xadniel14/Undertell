public abstract class Enemy extends Entity {
    protected Enemy(String name, int health, int attack) {
        super(name, health, attack);
    }

    @Override
    protected String getDisplayIdentifier() {
        return "ENEMY";
    }

    protected abstract void decideAction(Hero player);
}