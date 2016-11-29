package ObjectMenuObjects;

import grid.GridPaneNode;
import javafx.scene.image.Image;

public class Dirt1 extends GameObjects {
	 private final String PATH = "resources/images/Sprites/Declaration/Grass/dirt";
	    public Dirt1() {
	        super();
	        Image image = new Image(PATH+".png");
	        this.imageView.setImage(image);
	        type = "flower";
	        path = PATH;
	    }

	    @Override
	    public void populateList () {
	        String name = reName(PATH,1);
	        GridPaneNode tempNode = new GridPaneNode(0,0,name);
	        list.add(tempNode);
	    }
}