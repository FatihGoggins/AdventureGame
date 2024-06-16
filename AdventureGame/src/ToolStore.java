public class ToolStore extends NormalLocation{
    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome to the Explorer's Store");
        return true;
    }

}
