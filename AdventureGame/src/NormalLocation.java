public abstract class NormalLocation extends Location {
    public NormalLocation(Player player, String name) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}
