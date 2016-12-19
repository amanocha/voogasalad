package controller.battle;

import api.Player;
import controller.battle.BattleController;
import model.battle.BattleModel;
import model.battle.Difficulty;
import view.battle.BattleView;
import model.block.blocktypes.EnemyBlock;
import javafx.stage.Stage;

/**
 * Created by Bill Xiong on 12/12/16.
 * @author Bill Xiong
 * This class is well designed because it encapsulates entering a battle
 * in a flexible and extensible way, so the user can change it however he wants.
 */
public class BattleHandler {
    private Player player;
    private EnemyBlock enemyBlock;
    private static final String PATH = "resources/images/battles/background/background-1.jpg";
    public BattleHandler(Player p, EnemyBlock e){
        player = p;
        enemyBlock = e;
    }

    public void enterBattle(Difficulty diff) {
        Stage primaryStage = new Stage();
        BattleView view = new BattleView(diff, PATH);
        BattleModel model = new BattleModel(player, enemyBlock);
        BattleController controller = new BattleController(view, model);
        primaryStage.setScene(controller.getView().getScene());
        primaryStage.show();
    }
}
