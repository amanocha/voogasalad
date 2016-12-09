package battle;

import java.util.Observable;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Created by Bill Xiong on 12/7/16. abstract class for views, such as itemView,
 * enemyview, etc.
 * 
 * @author Bill Xiong
 * 
 */
public abstract class ItemView {

	private Label itemHP;
	private Label name;
	// TODO change to ImageView
	private ImageView itemView;
	private int hp;

	public ItemView(String name, int hp, int x, int y, String filePath) {
		//itemHP = new Label("HP: " + hp);
		//set image
		setImageView(filePath, 150, 150,x,y);
		/*
		//name
		this.name = new Label(name);
		this.name.setLayoutX(x + BattleView.OFFSET);
		this.name.setLayoutY(y + BattleView.OFFSET_Y);

		//temHP
		itemHP.setLayoutX(x + BattleView.OFFSET);
		itemHP.setLayoutY(y);*/
		this.hp = hp;
	}
	
	protected void setImageView(String filePath, int width, int height,int x, int y) {
		Image image = new Image(filePath);
        itemView = new ImageView();
        itemView.setFitWidth(width);
        itemView.setFitHeight(height);
        itemView.setImage(image);
        setLocation(x,y);
	}
	
	protected void addToGroup(Group root) {
		//root.getChildren().add(itemHP);
		root.getChildren().add(itemView);
        //root.getChildren().add(name);
	}

	protected void setLocation(int x, int y) {
		itemView.setLayoutX(x);
		itemView.setLayoutY(y);
	}

	protected int getHP() {
		return hp;
	}

	protected Label getItemHP() {
		return itemHP;
	}

	protected ImageView getView() {
		return itemView;
	}

	protected void setHP(int hp) {
		this.hp = hp;
		//itemHP.setText("HP: " + hp);
		//System.out.println("hp changed!");
	}

}