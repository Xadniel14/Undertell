public abstract class Item extends GameObject {
    private final String DESCRIPTION;
    protected final String ON_USE_STRING;

    protected Item(String name, String description, String onUseString) {
        super(name);
        DESCRIPTION = description;
        ON_USE_STRING = onUseString;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public abstract void onUse(Hero player);
}