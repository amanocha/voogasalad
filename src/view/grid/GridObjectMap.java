package view.grid;

import java.awt.Point;
import java.util.*;


/**
 * 
 * @author Teddy Franceschi-
 *
 */
public class GridObjectMap extends Observable {

    private Map<Point, ArrayList<Point>> data;
    private int numRows;
    private int numCols;

    public GridObjectMap (int numCols, int numRows) {
        data = new HashMap<Point, ArrayList<Point>>();
        this.numRows = numRows;
        this.numCols = numCols;
        initMap();
    }

    private void initMap () {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                data.put(new Point(i, j), new ArrayList<Point>());
            }
        }
    }

    private List<Point> sharesObjectWith (int x, int y) {
        try {
            ArrayList<Point> neighbors = data.get(new Point(x, y));
            return neighbors;
        }
        catch (Exception e) {
            return new ArrayList<Point>();
        }
    }

    /**
     * Returns list of grid positions that are filled with the same object that fills point (x,y)
     * 
     * @param x X coordinate of position
     * @param y Y cooridnate of position
     * @return List of Integers that are ordered (x1,y1,x2,y2....)
     */
    public List<Integer> sharesObjWith (int x, int y) {
        List<Point> neighbors = sharesObjectWith(x, y);
        List<Integer> points = new ArrayList<Integer>();
        for (int i = 0; i < neighbors.size(); i++) {
            points.add((int) neighbors.get(i).getX());
            points.add((int) neighbors.get(i).getY());
        }
        return points;
    }

    /**
     * Sets list of positions as filled which indicates object is there
     * 
     * @param List is the list of GridPaneNodes which mark cells filled by object
     * @return Status of addition
     */
    public boolean storeObject (List<GridPaneNode> list) {
        List<Point> temp = new ArrayList<Point>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(nodeToPoint(list.get(i)));
        }
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.size(); j++) {
                if (data.containsKey(temp.get(i))) {
                    data.get(temp.get(i)).add(temp.get(j));
                }
            }
        }
        return true;
    }

    /**
     * Removes all cells that are contained by the object which fills point (
     * 
     * @param row Row value
     * @param col Column value
     */
    public void collisionRemoval (int row, int col) {
        Point a = new Point(row, col);
        List<Point> temp = data.get(a);
        for (int i = 0; i < temp.size(); i++) {
            data.put(temp.get(i), new ArrayList<Point>());
        }
    }

    /**
     * Indicates if position is available
     * 
     * @param x X position
     * @param y Y position
     * @return True if position is available, false if not
     */
    public boolean available (int x, int y) {
        Point temp = new Point(x, y);
        if (data.containsKey(temp)) {
            if (data.get(temp).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles addition resizing
     * 
     * @param row how much to add to row
     * @param col how much to add to col
     */
    void resizeAdd (int row, int col) {
        Point a = new Point(row, col);
        if (!data.containsKey(a)) {
            data.put(a, new ArrayList<Point>());
        }
    }

    /**
     * Handles removal resizing
     * 
     * @param row how much to remove from row
     * @param col how much to remove from col
     */
    void resizeRemove (int row, int col) {
        Point a = new Point(row, col);
        if (data.containsKey(a)) {
            if (data.get(a).isEmpty()) {
                data.remove(a);
            }
            else {
                collisionRemoval(row, col);
            }
        }
    }

    private Point nodeToPoint (GridPaneNode node) {
        return new Point(node.getRow(), node.getCol());
    }

    /**
     * @return Returns the map which contains all points and the points it shares an object with
     */
    Map<Point, ArrayList<Point>> getMap () {
        return data;
    }
}