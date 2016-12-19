package model.interactions;

import api.Player;
import model.block.BlockUpdate;
import model.block.BlockUpdateType;

import java.util.ArrayList;
import java.util.List;

/**
 * Interaction which triggers the successful end of the current game.
 *
 * @author Filip Mazurek,
 */
public class WinInteraction implements Interaction {

    @Override
    public List<BlockUpdate> act(Player player) {
        List<BlockUpdate> updateList = new ArrayList<>();
        updateList.add(new BlockUpdate(BlockUpdateType.WIN_GAME, player.getRow(), player.getCol(), ""));
        return updateList;
    }
}
