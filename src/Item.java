public abstract class Item extends GameObject {
    private String description;

    protected Item(String name, String description) {
        setName(name);
        this.description = description;
    }

    public abstract void onUse();
}