package model.interactions;

import api.Player;
import model.block.BlockUpdate;
import model.block.BlockUpdateType;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows the model.player to be teleported to a destination model.block from a teleporter.
 *
 * @author Filip Mazurek, Aninda Manocha, Harshil Garg
 */

public class TeleportInteraction implements Interaction {
    private int myDestinationRow;
    private int myDestinationCol;
    private int myDestinationGrid;

    public TeleportInteraction(int row, int col, int grid) {
        myDestinationRow = row;
        myDestinationCol = col;
        myDestinationGrid = grid;
    }

    @Override
    public List<BlockUpdate> act(Player player) {
        List<BlockUpdate> updateList = new ArrayList<>();
        updateList.add(new BlockUpdate(BlockUpdateType.TELEPORT, myDestinationRow - player.getRow(), myDestinationCol - player.getCol(), "teleport"));
        player.setRow(myDestinationRow);
        player.setCol(myDestinationCol);
        player.setGridIndex(myDestinationGrid);
        return updateList;
    }
}
