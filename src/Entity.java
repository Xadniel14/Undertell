public abstract class Entity extends GameObject {
    private int currentHealth;

    private int maxHealth;

    private int attack;

    public Entity(String name, int health, int attack) {
        super(name);
        currentHealth = health;
        maxHealth = health;
        this.attack = attack;
    }

    public abstract String getDisplayIdentifier();

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
    }

    public void heal(int health) {
        currentHealth = Math.min(currentHealth + health, maxHealth);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void displayStatus(int attack) {
        System.out.printf("%s: %s || ", getDisplayIdentifier(), getName());
        System.out.printf("HP: %d/%d || ", getCurrentHealth(), maxHealth);
        System.out.printf("ATTACK: %d%n", attack);
    }
}