public class GoldenApple extends Item {

    public static GoldenApple GoldenApple = new GoldenApple();

    private GoldenApple() {
        super("Golden Apple", "Consuming it will fully heal and permanently increases your max hp by 50!", "Yum!!! So this is what it means to be rich?");
    }

    @Override
    public void onUse(Hero player) {
        super.onUse(player);

        player.setMaxHealth(player.getMaxHealth() + 50);
        player.heal(player.getMaxHealth());

        System.out.printf(" Healed: %d hp!%n", player.getMaxHealth());
    }
}
