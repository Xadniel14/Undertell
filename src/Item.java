public abstract class Item {
    String name;

    protected String getName() {
        return name;
    }

    public abstract void onUse();
}