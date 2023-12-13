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

    protected String getName() {
        return NAME;
    }

    protected int getCurrentHealth() {
        return currentHealth;
    }

    protected void takeDamage(int damage) {
        currentHealth -= damage;
    }

    protected void heal(int health) {
        currentHealth = Math.min(currentHealth + health, maxHealth);
    }

    protected int getMaxHealth() {
        return maxHealth;
    }

    protected int getAttack() {
        return attack;
    }
}