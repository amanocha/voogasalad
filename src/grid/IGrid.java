package grid;

import boardObjects.Block;

public interface IGrid {
	public int getNumRows();
	public int getNumCols();
	public IGrid getGrid();
	public Block getBlock(int row, int col);
}