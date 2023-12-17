public abstract class Item extends GameObject {
    private final String DESCRIPTION;
    public final String ON_USE_STRING;

    public Item(String name, String description, String onUseString) {
        super(name);
        DESCRIPTION = description;
        ON_USE_STRING = onUseString;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public  void onUse(Hero player) {
        System.out.printf("You ate: %s%n", getName());
        System.out.print(ON_USE_STRING);
    }
}