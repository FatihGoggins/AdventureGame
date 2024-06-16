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
            } else {
                System.out.println("----------------------------------------------------------------");
            }

        }
        Player player = new Player(name);
        player.selectCharacter();
        System.out.println("----------------------------------------------------------------");
        player.playerInfo();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Let the adventure begin...");

        while (true) {
            Location location = null;
            System.out.println("/--------------- Areas ---------------\\");
            System.out.println("1 - Safe House");
            System.out.println("2 - Tool Store");
            System.out.println("----------------------------------------------------------------");

            boolean isLocationSelected = false;

            while (!isLocationSelected) {
                isLocationSelected = true;
                System.out.print("Please select a place to go: ");
                int selectedLocation = scanner.nextInt();

                switch (selectedLocation) {
                    case 1:
                        location = new SafeHouse(player);
                        break;
                    case 2:
                        location = new ToolStore(player);
                        break;
                    default:
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Please select a valid area!!!");
                        System.out.println("----------------------------------------------------------------");
                        isLocationSelected = false;
                        break;

                }
            }
            location.onLocation();
        }

    }
}
