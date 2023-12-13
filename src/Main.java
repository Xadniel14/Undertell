import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter The Name Of Player");
        Hero player = new Hero(in.nextLine());
    }
}