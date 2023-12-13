public abstract class Entity {
    private final String NAME;
    private int currentHealth;
    private int maxHealth;
    private int attack;

    protected Entity(String name, int health, int attack) {
        NAME = name;
        currentHealth = health;
        maxHealth = health;
        this.attack = attack;
    }
}