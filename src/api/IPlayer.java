package api;
import java.util.List;

import editor.backend.Battle;
import interactions.Interaction;
import editor.backend.Item;
import player.PlayerAttribute;
import player.PlayerDirection;
/**
 * The player interface
 * @author Aninda Manocha
 */
public interface IPlayer {

	/**
	 * Gets the direction of the player
	 * @return the direction
	 */
	public PlayerDirection getDirection();
	/**
	 * Gets the row that the player is located in
	 * @return the row
	 */
	public int getRow();
	
	/**
	 * Gets the column that the player is located in
	 * @return the column
	 */
	public int getCol();
	/**
	 * Gets a list of all of the attributes of the player
	 * @return the list of attributes
	 */
	public List<PlayerAttribute> getAttributes();
 	/**
	 * Gets the list of all items that the player possesses
	 * @return the list of items
	 */
	public List<Item> getInventory();
	
	/**
	 * Gets the list of all the battles that the player has had
	 * @return the list of battles
	 */
	public List<Battle> getBattleHistory();
	
	/**
	 * Gets the list of all the interactions that the player has had
	 * @return the list of interactions
	 */
	public List<Interaction> getInteractionHistory();

	/**
	 * Sets the direction (orientation) of the player
	 * @param direction - the direction of the player
	 */
	public void setDirection(PlayerDirection direction);
	/**
	 * Sets the row of the player
	 * @param row - the row of the grid that the player should be moved to
	 */
	public void setRow(int row);
	/**
	 * Sets the column of the player
	 * @param col - the column of the grid that the player should be moved to
	 */
	public void setCol(int col);

	/**
	 * Adds an attribute to the list of attributes. This method returns false if the attribute type already exists.
	 * @param attribute the attribute to add
	 * @return whether an attribute was successfully added
	 */
	public boolean addAttribute(PlayerAttribute attribute);
	/**
	 * Adds an item to the inventory 
	 * @param item - the item to add
	 */
	public void addItem(Item item);
	
	/**
	 * Adds an interaction to the interaction history
	 * @param interaction - the interaction to add
	 */
	public void addInteraction(Interaction interaction);
}