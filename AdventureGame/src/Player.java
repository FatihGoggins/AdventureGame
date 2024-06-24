import java.util.Scanner;

public class Player {
    private final String name;
    private int health;
    private int initialHealth;
    private int damage;
    private int money;
    private String charClass;
    private Inventory inventory;
    Scanner scanner = new Scanner(System.in);

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
            String selectedCharacter = scanner.next();
            switch (selectedCharacter) {
                case "1":
                    this.initPlayer(new Samurai());
                    break;
                case "2":
                    this.initPlayer(new Archer());
                    break;
                case "3":
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
    public void initPlayer(CharacterRole characterRole) {
        this.setHealth(characterRole.getHealth());
        this.setInitialHealth(characterRole.getHealth());
        this.setDamage(characterRole.getDamage());
        this.setMoney(characterRole.getMoney());
        this.setCharClass(characterRole.getClassName());
    }

    public void playerInfo() {
        System.out.println("Name: " + this.getName());
        System.out.println("Class: " + this.getCharClass());
        System.out.println("Health: " + this.getHealth() + " | "
                + "Damage: " + this.getTotalDamage() + " | "
                + "Money: " + this.getMoney() + " | "
                + "Block: " + this.getInventory().getArmor().getBlock());
        System.out.println("Weapon: " + this.getInventory().getWeapon().getName());
        System.out.println("Armor: " + this.getInventory().getArmor().getName());
        System.out.print("Looted Items: { ");

        for (int i = 0; i < this.getInventory().getLootedItems().length; i++) {
            String lootedItem = this.getInventory().getLootedItems()[i];
            if(lootedItem != null ) {
                if (i == 0) {
                    System.out.print(lootedItem);
                } else {
                    System.out.print(", " + lootedItem);
                }
            }
        }
        System.out.println(" }");
    }

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            health = 0;
        }
        this.health = health;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getTotalDamage() {
        return this.damage + this.getInventory().getWeapon().getDamage();
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

    public String getName() {
        return name;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Weapon getWeapon() {
        return this.getInventory().getWeapon();
    }

    public Armor getArmor() {
        return this.getInventory().getArmor();
    }
}
