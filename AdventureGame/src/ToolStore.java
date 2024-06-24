public class ToolStore extends NormalLocation{
    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("Welcome to the Explorer's Store.");
            System.out.println("1 - Weapons");
            System.out.println("2 - Armor");
            System.out.println("3 - Exit");
            System.out.println("----------------------------------------------------------------");
            boolean isSelectionValid = false;
            while (!isSelectionValid) {
                isSelectionValid = true;
                System.out.print("Select: ");
                String selectCase = scanner.next();
                switch (selectCase) {
                    case "1":
                        printWeapons();
                        buyWeapon();
                        break;
                    case "2":
                        printArmor();
                        buyArmor();
                        break;
                    case "3":
                        showMenu = false;
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Thanks for shopping! Come back soon!");
                        System.out.println("----------------------------------------------------------------");
                        break;
                    default:
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Make a valid selection!!!");
                        System.out.println("----------------------------------------------------------------");
                        isSelectionValid = false;
                        break;
                }
            }
        }
        return true;
    }

    public void printWeapons() {
        System.out.println("/--------------- Weapons ---------------\\");
        for (Weapon weapon : Weapon.weapons()) {
            System.out.println(weapon.getId() + " - " + weapon.getName() + " (Damage: " + weapon.getDamage() + " | Price: "
                    + weapon.getPrice() + ")");
        }
        System.out.println((Weapon.weapons().length + 1) +" - Exit");
        System.out.println("----------------------------------------------------------------");
    }

    public void buyWeapon() {
        boolean isWeaponValid = false;
        Weapon weapon = null;

        while(!isWeaponValid) {
            isWeaponValid = true;
            System.out.print("Choose a weapon to buy: ");
            String weaponSelection = scanner.next();
            System.out.println("----------------------------------------------------------------");

            if (Weapon.weapons().length + 1 == Integer.parseInt(weaponSelection)) {
                System.out.println("You do not know what you miss...");
                System.out.println("----------------------------------------------------------------");
            } else if (Weapon.getWeaponById(weaponSelection) != null) {
                weapon = Weapon.weapons()[Integer.parseInt(weaponSelection) - 1];
                System.out.println("Your selection: " + weapon.getName());
                System.out.println("----------------------------------------------------------------");

                if (weapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("You do not have enough money!!!");
                    System.out.println("----------------------------------------------------------------");

                } else if (weapon.getName().equals(this.getPlayer().getWeapon().getName())) {
                    System.out.println("You already have " + weapon.getName() + " in your inventory");
                    System.out.println("----------------------------------------------------------------");
                } else if (weapon.getDamage() < this.getPlayer().getWeapon().getDamage()) {
                    System.out.println("You have a better weapon");
                    System.out.println("----------------------------------------------------------------");
                } else {
                    System.out.println("Cost: " + weapon.getPrice());
                    System.out.println("You have purchased ~| " + weapon.getName() + " |~");
                    System.out.println("You can see the weapon in your inventory");
                    int balance = this.getPlayer().getMoney() - weapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your Balance: " + this.getPlayer().getMoney());
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Previous Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(weapon);
                    System.out.println("----------------------------------------------------------------");
                }
            } else {
                System.out.println("Choose a valid weapon!!!");
                System.out.println("----------------------------------------------------------------");
                isWeaponValid = false;
            }
        }
    }

    public void printArmor() {
        System.out.println("/--------------- Armor ---------------\\");
        for (Armor armor : Armor.armors()) {
            System.out.println(armor.getId() + " - " + armor.getName() + " (Armor: " + armor.getBlock() + " | Price: "
                    + armor.getPrice() + ")");
        }
        System.out.println((Armor.armors().length + 1) +" - Exit");
        System.out.println("----------------------------------------------------------------");
    }

    public void buyArmor() {
        boolean isArmorValid = false;
        Armor armor = null;

        while(!isArmorValid) {
            isArmorValid = true;
            System.out.print("Choose an armor to buy: ");
            String armorSelection = scanner.next();
            System.out.println("----------------------------------------------------------------");

            if (Armor.armors().length + 1 == Integer.parseInt(armorSelection)) {
                System.out.println("You do not know what you miss...");
                System.out.println("----------------------------------------------------------------");
            }  else if (Armor.gerArmorById(armorSelection) != null) {
                armor = Armor.armors()[Integer.parseInt(armorSelection) - 1];
                System.out.println("Your selection: " + armor.getName());
                System.out.println("----------------------------------------------------------------");

                if (armor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("You do not have enough money!!!");
                    System.out.println("----------------------------------------------------------------");
                } else if (armor.getName().equals(this.getPlayer().getArmor().getName())) {
                    System.out.println("You already have " + armor.getName() + " in your inventory");
                    System.out.println("----------------------------------------------------------------");
                } else if (armor.getBlock() < this.getPlayer().getArmor().getBlock()) {
                    System.out.println("You have a better armor");
                    System.out.println("----------------------------------------------------------------");
                } else {
                    System.out.println("Cost: " + armor.getPrice());
                    System.out.println("You have purchased ~| " + armor.getName() + " |~");
                    System.out.println("You can see the armor in your inventory");
                    int balance = this.getPlayer().getMoney() - armor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your Balance: " + this.getPlayer().getMoney());
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Previous Armor: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(armor);
                    System.out.println("----------------------------------------------------------------");
                }

            } else {
                System.out.println("Choose a valid armor!!!");
                System.out.println("----------------------------------------------------------------");
                isArmorValid = false;
            }
        }
    }
}
