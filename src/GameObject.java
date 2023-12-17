public abstract class GameObject {
    private final String NAME;
    
    protected GameObject(String name) {
        NAME = name;
    }

    protected String getNAME() {
        return NAME;
    }
}