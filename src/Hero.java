import java.util.HashMap;

public class Hero extends Entity {
    public static HashMap<Item, Integer> inventory = new HashMap<>();

    public Hero(String name) {
        super(name, 150, 25);
    }
}