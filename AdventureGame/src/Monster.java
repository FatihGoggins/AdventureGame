public abstract class Monster {
    private String name;
    private String id;
    private int health;
    private int damage;
    private int reward;

    public Monster(String name, String id, int health, int damage, int reward) {
        this.name = name;
        this.id = id;
        this.health = health;
        this.damage = damage;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
