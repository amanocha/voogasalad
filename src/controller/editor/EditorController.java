/*
 * Aninda Manocha (am548)
 *
 * This entire file is part of my masterpiece. I believe this class, which serves as the editor controller, represents
 * good design because it assumes the role of a proper controller by controlling the flow of data into and out of the
 * model. The model consists of a player manager and a grid manager, which handle the player instance and grid instance
 * respectively. A controller should not implement functions and carry out the logic that a class in the model would be
 * responsible for. It should instead control the model and this class does that by delegating tasks to the player and
 * grid managers. This class catches exceptions that arise in the model so that alert boxes can be created to display
 * the error to the user, so it is also sending information to a class in the view so that the appropriate content is
 * displayed based on the data from the model. This class almost acts like an interface in that the methods contain very
 * little implementation, but make it clear what commands the frontend can call. There are a few actions for each major
 * component in the model and these correspond to actions that the user would want to do in the authoring environment,
 * such as adding a player, player attribute, or grid. The names of methods are very clear and this code is very well-
 * documented and readable. Overall, I think this class exhibits the modularity in our project by serving as a barrier
 * between the frontend and backend, and then containing different parts of the model (each of which is responsible for
 * its own component: player, grid, data) to send and receive data from so that the view (alert display) can be updated.
 */

package controller.editor;

import api.Controller;
import api.Player;
import model.block.blocktypes.BlockType;
import controller.engine.EngineController;
import model.exceptions.*;
import model.grid.GridManager;
import model.grid.GridSizeDirection;
import model.grid.GridWorld;
import model.player.PlayerInstance;
import model.player.PlayerManager;
import view.scenes.editor.GameEditorAlerts;
import model.xml.GridWorldAndPlayer;
import model.xml.GridXMLHandler;

import java.util.List;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other while the editor
 * is being created.
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController implements Controller {

    private GridManager myGridManager;
    private PlayerManager myPlayerManager;
    private GridXMLHandler xmlHandler;
    private GameEditorAlerts myAlerts;

    public EditorController() {
        myGridManager = new GridManager();
        myPlayerManager = new PlayerManager(myGridManager.getCurrentGrid());
        myGridManager.addObserver(myPlayerManager);
        xmlHandler = new GridXMLHandler();
    }

    public void setAlerts(GameEditorAlerts alerts) {
        myAlerts = alerts;
    }

    /* GRID METHODS */

    /**
     * Adds a new model.grid in the controller.editor where the user can place more objects. The model.grid is initially set to contain only
     * blocks of grass.
     * @param numRows - the number of rows in the new model.grid
     * @param numCols - the number of columns in the new model.grid
     */
    public void addGrid(int numRows, int numCols) {
        myGridManager.addGrid(numRows, numCols);
        myPlayerManager.setGrid(myGridManager.getCurrentGrid());
    }

    /**
     * Changes to a different specified model.grid, identified by its index in the list of grids
     * @param index - the index of the model.grid
     */
    public void changeGrid(int index) {
        myGridManager.changeGrid(index);
        myPlayerManager.setGrid(myGridManager.getCurrentGrid());
    }

    /**
     * Changes the size of the current model.grid in a specified direction and by a specified amount. If the amount is
     * positive, the model.grid grows and if the amount is negative, the model.grid shrinks.
     * @param direction - the direction in which the size changes
     * @param amount - the amount by which the model.grid size in the specified direction should change
     * @return whether or not the model.grid was changed
     */
    public boolean changeGridSize(GridSizeDirection direction, int amount) {
        try {
            return myGridManager.changeGridSize(direction, amount, getPlayerRow(), getPlayerCol());
        } catch (LargeGridException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        } catch (DeletePlayerWarning e) {
            if (myAlerts.warnUser(e.getMessage())) {
                return myGridManager.shrinkGrid(direction, amount);
            }
            return false;
        }
        return false;
    }

    /**
     * Gets the number of rows in the current model.grid
     * @return the number of rows
     */
    public int getNumRows() {
        return myGridManager.getCurrentGrid().getNumRows();
    }

    /**
     * Gets the number of columns in the current model.grid
     * @return the number of columns
     */
    public int getNumCols() {
        return myGridManager.getCurrentGrid().getNumCols();
    }

    /**
     * Adds music to the game
     * @param file - the name of the music file
     */
    public void addMusic(String file) {
        myGridManager.addMusic(file);
    }

    /***** BLOCK METHODS *****/

    /**
     * Changes a model.block's properties in the current model.grid
     * @param name - the image path name of the model.block
     * @param blockType - the type of model.block
     * @param row - the row of the new model.block
     * @param col - the column of the new model.block
     */
    public void addBlock(String name, BlockType blockType, int row, int col) {
        try {
            myGridManager.addBlock(name, blockType, row, col);
        }
        catch (BlockCreationException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }

    /**
     * Adds a message to a model.block (to be used for communicator blocks). This method returns false if the selected model.block
     * is not a communicator model.block.
     * @param message - the message to add
     * @param row - the row of the model.block
     * @param col - the column of the model.block
     * @return whether a message was successfully added to the model.block
     */
    public boolean addMessage(String message, int row, int col) {
        return (myGridManager.addMessage(message, row, col));
    }

    /**
     * Change the first setting of the gate in controller.editor. Must set the status to open or closed when making a gate
     * @param row - row of the gate model.block
     * @param col - column of the gate model.block
     * @param isOpen - true if open the gate, false if close the gate
     * @return if the gate status was set correctly
     */
    public boolean setGateStatus(int row, int col, boolean isOpen) {
        return myGridManager.setGateStatus(row, col, isOpen);
    }

    /**
     * Links two blocks (to be used for teleportation or switches). This method returns false if the selected blocks are
     * not linkable (the wrong type of blocks).
     * @param row1 - the row of the first model.block
     * @param col1 - the column of the first model.block
     * @param index1 - the model.grid index in which the first model.block is located
     * @param row2 - the row of the second model.block
     * @param col2 - the column of the second model.block
     * @param index2 - the model.grid index in which the second model.block is located
     * @return whether the blocks were successfully linked
     */
    public boolean linkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        return myGridManager.linkBlocks(row1, col1, index1, row2, col2, index2);
    }

    /**
     * Unlinks two blocks that were previously linked. This method returns false if the selected blocks are not
     * unlinkable blocks (either the wrong type or the blocks were not linked to begin with).
     * @param row1 - the row of the first model.block
     * @param col1 - the column of the first model.block
     * @param index1 - the model.grid index in which the first model.block is located
     * @param row2 - the row of the second model.block
     * @param col2 - the column of the second model.block
     * @param index2 - the model.grid index in which the second model.block is located
     * @return whether the blocks were successfully unlinked
     */
    public boolean unlinkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        return myGridManager.unlinkBlocks(row1, col1, index1, row2, col2, index2);
    }

    /**
     * Gets the model.block located in a specific row and column in the model.grid. The frontend calls this method in order to
     * render a model.grid model.block by model.block.
     * @param row - the specific row
     * @param col - the specific column
     * @return the model.block
     */
    public String getBlock(int row, int col) {
        return myGridManager.getBlock(row, col);
    }

    /***** PLAYER METHODS *****/

    /**
     * Adds a model.player to the model.grid
     * @param names - the image path names of the model.player (one for each of the four cardinal directions)
     * @param playerName - the name of the model.player
     * @param row - the row of the model.player
     * @param col - the column of the model.player
     * @return whether the model.player was successfully added
     */
    public boolean addPlayer(List<String> names, String playerName, int row, int col) {
        try {
            return myPlayerManager.addPlayer(names, playerName, row, col, myGridManager.getCurrentIndex());
        }
        catch (BadPlayerPlacementException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
        catch (DuplicatePlayerException e2) {
            myAlerts.exceptionDisplay(e2.getMessage());
            return false;
        }
    }

    /**
     * Adds an attribute for the model.player
     * @param name - the name of the attribute
     * @param amount - the initial amount of the attribute
     * @param increment - the amount by which the attribute increases
     * @param decrement - the amount by which the attribute decreases
     * @return whether the attribute was successfully added
     */
    public boolean addPlayerAttribute(String name, double amount, double increment, double decrement) {
        try {
            return myPlayerManager.addPlayerAttribute(name, amount, increment, decrement);
        } catch (DuplicateAttributeException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
    }

    /**
     * Deletes the model.player
     */
    public void deletePlayer() {
        myPlayerManager.deletePlayer();
    }

    /**
     * Moves the model.player to a new location on the current model.grid
     * @param row - the row of the new location
     * @param col - the column of the new location
     * @return whether or not the model.player was successfully moved
     */
    public boolean movePlayer(int row, int col) {
        try {
            return myPlayerManager.movePlayer(row, col);
        } catch (BadPlayerPlacementException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
    }

    public int getPlayerRow() {
        return myPlayerManager.getRow();
    }

    /**
     * Gets the column in which the model.player is located
     * @return the column
     */
    public int getPlayerCol() {
        return myPlayerManager.getCol();
    }

    /***** DATA METHODS *****/

    /**
     * Saves the controller.editor by taking in the name of the file to contain the information
     * @param file - the name of the file containing the controller.editor information
     */
    public void saveEditor(String file) {
        GridWorld gridWorld = new GridWorld(myGridManager, myGridManager.getMusic());
        try {
            xmlHandler.saveContents(file, gridWorld, myPlayerManager.getPlayer());
        } catch (NoPlayerException e) {
            xmlHandler.saveContents(file, gridWorld, null);
        }
    }

    /**
     * Loads an controller.editor that is stored in a file containing the controller.editor information
     * @param file - the name of the file containing the controller.editor information
     */
    public void loadEditor(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        myPlayerManager.setPlayer(gridWorldAndPlayer.getPlayer());
        myGridManager = new GridManager(gridWorldAndPlayer.getGridWorld().getGrids());
        changeGrid(myGridManager.getCurrentIndex());
    }

    /**
     * Exports the controller.editor to create a game by taking in the name of the file to contain the information
     * @param file - the name of the file containing the controller.engine information
     */
    public void saveEngine(String file) {
        GridWorld gridWorld = new GridWorld(myGridManager, myGridManager.getMusic());
        try {
            xmlHandler.saveContents(file, gridWorld, myPlayerManager.getPlayer());
        } catch (NoPlayerException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }

    /**
     * Creates an controller.engine to run a game while in the controller.editor
     * @return an controller.engine controller to run the tested game
     */
    public EngineController runEngine() {
        try {
            Player enginePlayer = new PlayerInstance(myPlayerManager.getPlayer());
            EngineController testEngineController = new EngineController(enginePlayer, myGridManager, myGridManager.getMusic());
            return testEngineController;
        } catch (NoPlayerException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return null;
        }
    }
}