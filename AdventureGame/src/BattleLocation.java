import java.lang.reflect.Array;
import java.util.Arrays;
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
        if (Arrays.stream(this.getPlayer().getInventory().getLootedItems()).anyMatch(item -> this.getAward().equals(item))) {
            System.out.println("You already looted the " + this.getName());
            System.out.println("----------------------------------------------------------------");
            return true;
        }
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

            if (selectMove.equals("F")) {
                if (this.combat(monsterNumber)) {
                    System.out.println("You have beaten up all the " + this.getMonster().getName() + "s");
                    System.out.println("----------------------------------------------------------------");

                    if (!this.getAward().equals("")) {
                        this.getPlayer().getInventory().setLootedItems(this.getAward());
                        System.out.println("You have looted the " + this.getAward() + " from the " + this.getName());
                        System.out.println("----------------------------------------------------------------");
                    }
                    return true;
                } else {
                    if (this.getPlayer().getHealth() <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else if (selectMove.equals("R")) {
                System.out.println("----------------------------------------------------------------");
                System.out.println("Your ran away like a COWARD");
                System.out.println("----------------------------------------------------------------");
                return true;
            } else {
                System.out.println("----------------------------------------------------------------");
                System.out.println("Make a valid move!!!");
                System.out.println("----------------------------------------------------------------");
                isSelectionInvalid = false;
            }

            if (this.getPlayer().getHealth() <= 0) {
                return false;
            }
        }
        return true;
    }

    public boolean combat(int monsterNumber) {
        for (int i = 1; i <= monsterNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getOriginalHealth());
            this.playerStats();
            this.monsterStats();

            System.out.println("----------------------------------------------------------------");
            if (monsterNumber > 1) {
                System.out.println("You are facing against " + i + ". " + this.getMonster().getName());
            } else {
                System.out.println("You are facing against the " + this.getMonster().getName());
            }

            boolean playerHitsFirst = playerHitFirst();

            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                boolean isValidMove = false;
                String selectMove = "";

                while (!isValidMove) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.print("Keep Fighting or Run Away (F / R): ");
                    selectMove = scanner.next().toUpperCase();
                    System.out.println("----------------------------------------------------------------");

                    if (!selectMove.equals("F") && !selectMove.equals("R")) {
                        System.out.println("Please enter a valid move!!!");
                    } else {
                        isValidMove = true;
                    }
                }

                if (selectMove.equals("F")) {
                    int playerHitPoint = this.getPlayer().getTotalDamage();
                    int monsterHitPoint = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                    if (monsterHitPoint < 0) monsterHitPoint = 0;
                    int monsterRemainingHealth;
                    int playerRemainingHealth;

                    if (playerHitsFirst) {
                        monsterRemainingHealth = this.getMonster().getHealth() - playerHitPoint;
                        this.getMonster().setHealth(monsterRemainingHealth);

                        System.out.println("You dealt " + playerHitPoint + " damage to " + i + ". " + this.getMonster().getName());

                        if (this.getMonster().getHealth() > 0) {
                            playerRemainingHealth = this.getPlayer().getHealth() - monsterHitPoint;
                            this.getPlayer().setHealth(playerRemainingHealth);
                            System.out.println(i + ". " + this.getMonster().getName() + "'s remaining Health: " + this.getMonster().getHealth());
                            System.out.println("----------------------------------------------------------------");
                            if (monsterHitPoint > 0) {
                                System.out.println(i + ". " + this.getMonster().getName() + " dealt " + monsterHitPoint + " to you");
                            } else {
                                System.out.println("You blocked " + i + ". " + this.getMonster().getName() + "'s attack.");
                            }
                            System.out.println("Your remaining Health: " + this.getPlayer().getHealth());

                            if (this.getPlayer().getHealth() <= 0) {
                                System.out.println(i + ". " + this.getMonster().getName() + " has killed you.");
                                System.out.println("----------------------------------------------------------------");
                                return false;
                            }
                        } else {
                            System.out.println("You killed " + i + ". " + this.getMonster().getName());
                            System.out.println("----------------------------------------------------------------");
                            randomLoot(i);
                            if (monsterNumber == 1) System.out.println("----------------------------------------------------------------");
                        }
                    } else {
                        playerRemainingHealth = this.getPlayer().getHealth() - monsterHitPoint;
                        this.getPlayer().setHealth(playerRemainingHealth);
                        if (monsterHitPoint > 0) {
                            System.out.println(i + ". " + this.getMonster().getName() + " dealt " + monsterHitPoint + " to you");
                        } else {
                            System.out.println("You blocked " + i + ". " + this.getMonster().getName() + "'s attack.");
                        }
                        System.out.println("Your remaining Health: " + this.getPlayer().getHealth());
                        System.out.println("----------------------------------------------------------------");

                        if (this.getPlayer().getHealth() > 0) {
                            monsterRemainingHealth = this.getMonster().getHealth() - playerHitPoint;
                            this.getMonster().setHealth(monsterRemainingHealth);
                            System.out.println("You dealt " + playerHitPoint + " damage to " + i + ". " + this.getMonster().getName());
                            if(this.getMonster().getHealth() <= 0) {
                                System.out.println("You killed " + i + ". " + this.getMonster().getName());
                                System.out.println("----------------------------------------------------------------");
                                randomLoot(i);
                                if (monsterNumber == 1) System.out.println("----------------------------------------------------------------");
                            } else {
                                System.out.println(i + ". " + this.getMonster().getName() + "'s remaining Health: " + this.getMonster().getHealth());
                            }
                        } else {
                            System.out.println(i + ". " + this.getMonster().getName() + " has killed you.");
                            System.out.println("----------------------------------------------------------------");
                            return false;
                        }
                    }
                } else if (selectMove.equals("R")) {
                    System.out.println("You ran away from the " + this.getName());
                    System.out.println("----------------------------------------------------------------");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean playerHitFirst() {
        int randomNumber = (int) (Math.random() * 2);
        if (randomNumber == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void randomLoot(int i) {
        if (this.getAward().equals("")) {
            int randomPercentage = (int) (Math.random() * 100);
            System.out.print("You have looted ");
            if (randomPercentage < 15) {
                int weaponPercentage = (int) (Math.random() * 100);
                if (weaponPercentage < 20) {
                    this.getPlayer().getInventory().setWeapon(Weapon.weapons()[2]);
                } else if (weaponPercentage < 50) {
                    this.getPlayer().getInventory().setWeapon(Weapon.weapons()[1]);
                } else {
                    this.getPlayer().getInventory().setWeapon(Weapon.weapons()[0]);
                }
                System.out.print(this.getPlayer().getWeapon().getName());
            } else if (randomPercentage < 30) {
                int armorPercentage = (int) (Math.random() * 100);
                if (armorPercentage < 20) {
                    this.getPlayer().getInventory().setArmor(Armor.armors()[2]);
                } else if (armorPercentage < 50) {
                    this.getPlayer().getInventory().setArmor(Armor.armors()[1]);
                } else {
                    this.getPlayer().getInventory().setArmor(Armor.armors()[0]);
                }
                System.out.print(this.getPlayer().getArmor().getName());
            } else if (randomPercentage < 55) {
                int dinarPercentage = (int) (Math.random() * 100);
                int money;
                if (dinarPercentage < 20) {
                    money = 10;
                } else if (dinarPercentage < 50) {
                    money = 5;
                } else {
                    money = 1;
                }
                this.getPlayer().setMoney(this.getPlayer().getMoney() + money);
                System.out.print(money + " dinar");
                if (money > 1) System.out.print("s");
            } else {
                System.out.print("nothing");
            }
            System.out.println(" from the " + i + ". " + this.getMonster().getName());
        } else {
            this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getReward());
            System.out.println("You looted " + this.getMonster().getReward() + " dinars from " + i + ". " + this.getMonster().getName());
        }
    }

    public void playerStats() {
        System.out.println("/--------------- Player Stats ---------------\\");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Weapon: " + this.getPlayer().getWeapon().getName() + " | " + "Armor: " + this.getPlayer().getArmor().getName());
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
