public class Armor extends Item {
    private int block;

    public Armor(String name, String id,int block, int price) {
        super(name, id, price);
        this.block = block;
    }

    public static Armor[] armors() {
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor("Light Armor", "1", 1, 15);
        armorList[1] = new Armor("Medium Armor", "2", 2, 25);
        armorList[2] = new Armor("Heavy Armor", "3", 3, 40);
        return armorList;
    }

    public static Armor gerArmorById(String id) {
        if (armors().length < Integer.parseInt(id)) {
            return null;
        }

        for (Armor armor : armors()) {
            if (armor.getId().equals(id)) {
                return armor;
            }
        }
        return null;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }
}
