package view.grid;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 
 * @author Teddy Franceschi
 *
 */
public class GridPaneNode {
    private int row;
    private int col;

    private String type;
    private int imageNum;
    //private int typeNum;
    private String name;
    private ImageView imageView;

    /**
     * Constructor for GridPaneNode
     *
     * @param row  Row for node to be placed
     * @param col  Column for node to be placed
     * @param name Name of node
     */
    public GridPaneNode(int row, int col, String name) {
        this.row = row;
        this.col = col;
        this.name = name;
        this.imageView = new ImageView();
        setInitialImage();
        //default, hard coded values for now
    }

    /**
     * Sets the image of the node
     * @param image
     */
    public void setImage(ImageView image) {
        imageView = image;
    }

    /**
     * Sets the coordinates of the image in the window
     * @param x
     * @param y
     */
    public void setImageCoord(double x, double y) {
        imageView.setX(x);
        imageView.setY(y);
    }

    /**
     * Sets the image size for the node
     * @param x
     * @param y
     */
    public void setImageSize(double x, double y) {
        this.imageView.setFitWidth(x);
        this.imageView.setFitHeight(y);
    }

    private void setInitialImage() {
        Image image = new Image(name);
        imageView.setImage(image);
    }


    private String getPath() {
        String sol = "";
        return sol;
    }
    
    
    public void swap(GridPaneNode node){
        Image image = new Image(node.getName());
        this.imageView.setImage(image);
        //this.imageNum = node.getImageNum();
        this.name = node.getName();
    }

    //Getters
    /**
     * Gets row value
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets col value
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Gets ImageView
     * @return
     */
    public ImageView getImage() {
        return imageView;
    }

    //Setters

    /**
     * Sets name
     * @param a
     */
    public void setName(String a) {
        this.name = a;
    }

    /**
     * Gets name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Gets image num
     * @return
     */
    public int getImageNum() {
        return this.imageNum;
    }

    /**
     * Gets String
     */
    public String toString() {
        String sol = "row: " + row + "\ncol: " + col + "\nname: " + name;
        return sol;
    }
    
    /**
     * Sets col number
     * @param col
     */
    public void setCol(int col) {
        this.col = col;

    }

    /**
     * Sets row number
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }
}