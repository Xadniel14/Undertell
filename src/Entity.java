public abstract class Entity extends GameObject {
    private int currentHealth;
    private int maxHealth;

    private int attack;

    protected Entity(String name, int health, int attack) {
        super(name);
        currentHealth = health;
        maxHealth = health;
        this.attack = attack;
    }

    protected abstract String getDisplayIdentifier();

    public int getCurrentHealth() {
        return currentHealth;
    }

    protected int getAttack() {
        return attack;
    }

    protected void takeDamage(int damage) {
        currentHealth -= damage;
    }

    public void heal(int health) {
        currentHealth = Math.min(currentHealth + health, maxHealth);
    }

    public void displayStatus() {
        System.out.printf("%s: %s || ", getDisplayIdentifier(), getNAME());
        System.out.printf("HP: %d/%d || ", getCurrentHealth(), maxHealth);
        System.out.printf("ATTACK: %d%n", attack);
    }
}