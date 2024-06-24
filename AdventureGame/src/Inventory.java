public class Inventory {
    private  Weapon weapon;
    private Armor armor;
    private String[] lootedItems = new String[3];

    public Inventory() {
        this.weapon = new Weapon("Hand", "-1",0,0);
        this.armor = new Armor("Naked", "-1", 0,0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        if (weapon.getDamage() > this.getWeapon().getDamage()) {
            this.weapon = weapon;
        }
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        if (armor.getBlock() > this.getArmor().getBlock()) {
            this.armor = armor;
        }
    }

    public String[] getLootedItems() {
        return lootedItems;
    }

    public void setLootedItems(String lootedItem) {
        for (int i = 0; i < lootedItems.length; i++) {
            if (lootedItems[i] == null) {
                this.lootedItems[i] = lootedItem;
                break;
            } else {
                continue;
            }
        }
    }

    public boolean isLootEmpty() {
        for (String item : this.getLootedItems()) {
            if (item != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isLootFull() {
        for (String item : this.getLootedItems()) {
            if (item == null) {
                return false;
            }
        }
        return true;
    }
}
