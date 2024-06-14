import java.util.Scanner;

public class Player {
    private final String name;
    private int health;
    private int damage;
    private int money;
    private String charClass;
    Scanner scanner = new Scanner(System.in);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public void selectCharacter() {
        CharacterRole[] roles = {new Samurai(), new Archer(), new Knight()};
        System.out.println("/--------------- Game Classes ---------------\\");
        for (CharacterRole role : roles) {
            System.out.println(role.getId() + " - " + role.getClassName() + "\t"
            + "( Health: " + role.getHealth() + " "
            + "Damage: " + role.getDamage() + " "
            + "Money: " + role.getMoney() + " )");
        }
        System.out.println("----------------------------------------------------------------");
        boolean isClassSelected = false;

        while (!isClassSelected) {
            isClassSelected = true;

            System.out.print("Select your Character: ");
            int selectedCharacter = scanner.nextInt();
            switch (selectedCharacter) {
                case 1:
                    this.initPlayer(new Samurai());
                    break;
                case 2:
                    this.initPlayer(new Archer());
                    break;
                case 3:
                    this.initPlayer(new Knight());
                    break;
                default:
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Please select a valid class!!!");
                    System.out.println("----------------------------------------------------------------");
                    isClassSelected = false;
                    break;
            }
        }
    }

    public void selectLocation() {
        System.out.println("Areas:");
        System.out.println("");
        System.out.println("Please select a place to go: ");
    }

    public void initPlayer(CharacterRole characterRole) {
        this.setHealth(characterRole.getHealth());
        this.setDamage(characterRole.getDamage());
        this.setMoney(characterRole.getMoney());
        this.setCharClass(characterRole.getClassName());
    }

    public void playerInfo() {
        System.out.println("Name: " + this.getName());
        System.out.println("Class: " + this.getCharClass());
        System.out.println("Health: " + this.getHealth());
        System.out.println("Damage: " + this.getDamage());
        System.out.println("Money: " + this.getMoney());
    }
}
