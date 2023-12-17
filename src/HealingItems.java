public enum HealingItems {
    APPLE("Apple", "Oddly enough, the apple still tastes fresh...?", 15),
    BEAN("Bean", "It's bland, but surprisingly hearty!", 25),
    BONE_HEART("Bone Heart", "Disgusting!", 50);

    private static class HealingItem extends Item {
        final int HEALING_VALUE;

        private HealingItem(String name, String onUseString, int HEALING_VALUE) {
            super(name, "%s is a consumable item that heals %d HP when used!".formatted(name, HEALING_VALUE), onUseString);
            this.HEALING_VALUE = HEALING_VALUE;
        }

        @Override
        public void onUse(Hero player) {
            super.onUse(player);
            System.out.printf(" Recovered: %d hp.%n", HEALING_VALUE);
            player.heal(HEALING_VALUE);
        }
    }

    private final Item ITEM;

    HealingItems(String name, String onUseString, int healingValue) {
        ITEM = new HealingItem(name, onUseString, healingValue);
    }

    public Item getITEM() {
        return ITEM;
    }
}