public class Snake extends Monster{
    public Snake() {
        super("Snake","4",12,12, randomDamage(), 0);
    }

    private static int randomDamage() {
        return  3 + ((int) (Math.random() * 4));
    }
}
