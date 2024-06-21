public class Weapon extends Item {
    private int damage;


    public Weapon(String name, String id, int price, int damage) {
        super(name, id, price);
        this.damage = damage;
    }

    public static Weapon[] weapons() {
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] = new Weapon("Gun", "1", 20, 2);
        weaponList[1] = new Weapon("Great Sword", "2", 35, 3);
        weaponList[2] = new Weapon("Rifle", "3", 45, 7);
        return weaponList;
    }

    public static Weapon getWeaponById(String id) {
        if (weapons().length < Integer.parseInt(id)) {
            return null;
        }

       for (Weapon weapon : weapons()) {
           if (weapon.getId().equals(id)) {
               return weapon;
           }
       }
        return null;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}
