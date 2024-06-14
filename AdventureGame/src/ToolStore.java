public class ToolStore extends NormalLocation{
    public ToolStore(Player player, String name) {
        super(player, name);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome to the Explorer's Store");
        return true;
    }

}
