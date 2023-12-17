public class BoneMeal extends Item {

    public final static BoneMeal BONE_MEAL = new BoneMeal();

    private BoneMeal() {
        super("Bone Meal", "a RARE consumable item that permanently increases your atk by 10!", "Tastes like protein...?");
    }

    @Override
    public void onUse(Hero player) {
        super.onUse(player);

        player.setAttack(player.getAttack() + 10);
        System.out.println(" Player attack permanently raised by 10!");
    }
}
