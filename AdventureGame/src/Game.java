import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);



    public void start() {
        boolean isGameOn = true;
        System.out.println("The game starts!!!");
        System.out.println("----------------------------------------------------------------");
        boolean isNameEmpty = true;
        String name = "";
        while (isNameEmpty) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            if (!name.isEmpty()) {
                isNameEmpty = false;
            } else {
                System.out.println("----------------------------------------------------------------");
            }

        }
        Player player = new Player(name);
        player.selectCharacter();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Let the adventure begin...");
        System.out.println("----------------------------------------------------------------");

        while (isGameOn) {
            player.playerInfo();
            Location location = null;
            System.out.println("/--------------- Areas ---------------\\");
            System.out.println("0 - Exit Game");
            System.out.println("1 - Safe House: There are no monsters here.");
            System.out.println("2 - Tool Store: You can buy armor and weapons.");
            System.out.println("3 - Cave (Monster: Zombie | Reward: Food)");
            System.out.println("4 - Forest (Monster: Vampire | Reward: Firewood)");
            System.out.println("5 - River: (Monster: Bear | Reward: Water)");
            System.out.println("----------------------------------------------------------------");

            boolean isLocationSelected = false;

            while (!isLocationSelected) {
                isLocationSelected = true;
                System.out.print("Please select a place to go: ");
                String selectedLocation = scanner.next();
                System.out.println("----------------------------------------------------------------");

                switch (selectedLocation) {
                    case "0":
                        isGameOn = false;
                        break;
                    case "1":
                        location = new SafeHouse(player);
                        break;
                    case "2":
                        location = new ToolStore(player);
                        break;
                    case "3":
                        location = new Cave(player);
                        break;
                    case "4":
                        location = new Forest(player);
                        break;
                    case "5":
                        location = new River(player);
                        break;
                    default:
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Please select a valid area!!!");
                        System.out.println("----------------------------------------------------------------");
                        isLocationSelected = false;
                        break;

                }
            }
            if (location == null) {
                System.out.println("You Gave Up Quickly");
                System.out.println("----------------------------------------------------------------");
            } else if (!location.onLocation()) {
                System.out.println("You Died");
                System.out.println("Game Over");
                break;
            }
        }

    }
}
