public abstract class CharacterRole {
    private String className;
    private int id;
    private int health;
    private int damage;
    private int money;

    public CharacterRole(String className,int id, int health, int damage, int money) {
        this.className = className;
        this.id = id;
        this.health = health;
        this.damage = damage;
        this.money = money;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
