package block;

/**
 * A special type of block which may be connected to a switch. On player interaction with a switch, the gate will change
 * states between open and closed.
 *
 * @author Filip Mazurek
 */
public class GateBlock extends Block {
    private static final boolean OPEN = true;
    private static final boolean CLOSED = false;
    private static final String closedString = "CLOSED";
    private static final String openString = "OPEN";

    public GateBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(CLOSED);
    }

    public void openGate() {
        setWalkableStatus(OPEN);
    }

    public void closeGate() {
        setWalkableStatus(CLOSED);
    }

    /**
     * Changes whether the gate is open or closed. Difference is whether the player character may walk through the gate
     * and how it should be rendered.
     *
     * @return the rendering change for the front end
     */
    BlockUpdate toggleOpenStatus() {
        String status;
        if(this.isWalkable()) {
            setWalkableStatus(CLOSED);
            status = closedString;
        }
        else {
            setWalkableStatus(OPEN);
            status = openString;
        }
        String image = replaceNameStatus(getName(), status);
        return new BlockUpdate(BlockUpdateType.RE_RENDER, getRow(), getCol(), image);
    }
}
