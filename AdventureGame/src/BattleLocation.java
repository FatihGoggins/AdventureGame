import java.util.Random;
import java.util.Scanner;

public abstract class BattleLocation extends Location {
    private Monster monster;
    private String award;
    private int maxMonsterNumber;
    Scanner scanner = new Scanner(System.in);

    public BattleLocation(Player player, String name, Monster monster, int maxMonsterNumber, String award) {
        super(player, name);
        this.monster = monster;
        this.maxMonsterNumber = maxMonsterNumber;
        this.award = award;
    }

    @Override
    public boolean onLocation() {
        int monsterNumber = this.randomMonsterNumber();
        String selectMove = "";
        boolean isSelectionInvalid = false;

        System.out.println("You are at " + this.getName());
        if (monsterNumber > 1) {
            System.out.println("!!! BE CAUTIOUS !!! There are " + monsterNumber + " " + this.getMonster().getName() + "s around here");
        } else {
            System.out.println("!!! BE CAUTIOUS !!! There is a " + this.getMonster().getName() + " around here");
        }
        System.out.println("----------------------------------------------------------------");

        while (!isSelectionInvalid) {
            isSelectionInvalid = true;
            System.out.print("Fight like a HERO or run away like a COWARD (F / R): ");
            selectMove = scanner.next().toUpperCase();
            System.out.println("----------------------------------------------------------------");

            if (selectMove.equals("F")) {
                System.out.println("Fight");
                System.out.println("----------------------------------------------------------------");
                this.combat(monsterNumber);
            } else if (selectMove.equals("R")) {
                System.out.println("Your ran away like a COWARD");
                System.out.println("----------------------------------------------------------------");
            } else {
                System.out.println("Make a valid move!!!");
                System.out.println("----------------------------------------------------------------");
                isSelectionInvalid = false;
            }
        }
        return true;
    }

    public boolean combat(int monsterNumber) {
        for (int i = 1; i <= monsterNumber; i++) {
            this.playerStats();
            this.monsterStats();
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.print("Keep Fighting or Run Away (F / R): ");
                String selectMove = scanner.next().toUpperCase();
                if (selectMove.equals("F")) {
                    int monsterRemainingHealth = this.getMonster().getHealth() - this.getPlayer().getTotalDamage();
                    this.getMonster().setHealth(monsterRemainingHealth);
                    int playerRemainingHealth = this.getPlayer().getHealth() - this.getMonster().getDamage();
                    this.getPlayer().setHealth(playerRemainingHealth);
                    System.out.println("You dealt " + this.getPlayer().getTotalDamage() + " damage to " + this.getMonster().getName());
                    System.out.println(this.getMonster().getName() + " dealt " + this.getMonster().getDamage() + " to you.");
                }
            }
        }
        return false;
    }

    public void playerStats() {
        System.out.println("/--------------- Player Stats ---------------\\");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Weapon: " + this.getPlayer().getWeapon().getName());
        System.out.println("Armor: " + this.getPlayer().getArmor().getName());
        System.out.println("Block: " + this.getPlayer().getArmor().getBlock());
        System.out.println("Money: " + this.getPlayer().getMoney());
    }

    public void monsterStats() {
        System.out.println("/--------------- " + this.getMonster().getName() + " Stats ---------------\\");
        System.out.println("Health: " + this.getMonster().getHealth());
        System.out.println("Damage: " + this.getMonster().getDamage());
        System.out.println("Reward: " + this.getMonster().getReward());
    }

    public int randomMonsterNumber() {
        Random random = new Random();
        return random.nextInt(this.getMaxMonsterNumber()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public int getMaxMonsterNumber() {
        return maxMonsterNumber;
    }

    public void setMaxMonsterNumber(int maxMonsterNumber) {
        this.maxMonsterNumber = maxMonsterNumber;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
