public enum HealingItems {
    Apple("Apple", "Oddly enough, the apple still tastes fresh...?", 15),
    Bean("Bean", "It's bland, but surprisingly hearty!", 25),
    BoneHeart("Bone Heart", "Disgusting!", 50);

    private static class HealingItem extends Item {
        int healingValue;

        private HealingItem(String name,String onUseString, int healingValue) {
            super(name, "%s is a consumable item that heals %d HP when used!".formatted(name, healingValue), onUseString);
            this.healingValue = healingValue;
        }

        @Override
        public void onUse(Hero player) {
            super.onUse(player);
            System.out.printf(" Recovered: %d hp.%n", healingValue);
            player.heal(healingValue);
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