import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);



    public void start() {
        System.out.println("The game starts!!!");
        System.out.println("----------------------------------------------------------------");
        boolean isNameEmpty = true;
        String name = "";
        while (isNameEmpty) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            if (!name.isEmpty()) {
                isNameEmpty = false;
            }
            System.out.println("----------------------------------------------------------------");
        }
        Player player = new Player(name);
        player.selectCharacter();
        System.out.println("----------------------------------------------------------------");
        player.playerInfo();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Let the adventure begin...");
        System.out.println("----------------------------------------------------------------");
        player.selectLocation();


    }
}
