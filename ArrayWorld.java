package uk.ac.cam.rjm232.tick7;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class ArrayWorld extends WorldImpl {
	
	private boolean[][] cells;
	
	public ArrayWorld (int width, int height) {
		super(width, height);
		cells = new boolean[height][width];
	}
	public ArrayWorld (ArrayWorld prev) {
		super (prev);
		cells = new boolean[getHeight()][getWidth()];
	}
	
	@Override
	public boolean getCell(int col, int row) {
		if (row<0 || row >= getHeight()) {
			return false;
		}
		if (col<0 || col >= getWidth()) {
			return false;
		}
		
		return cells[row][col];
	}
	
	@Override
	public void setCell(int col, int row, boolean alive) {
		if (row<0 || row >= getHeight()) { 
			return;
		}
		if (col<0 || col >= getWidth()) {
			return;
		}
		
		cells[row][col] = alive; 
	}
	
	@Override 
	protected ArrayWorld nextGeneration() {
		ArrayWorld nextWorld = new ArrayWorld(this);
		for (int x = 0; x < nextWorld.getWidth(); x++) {
			for (int y = 0; y < nextWorld.getHeight(); y++) {
				nextWorld.setCell(x, y, computeCell(x, y));
			}
		}
		return nextWorld;
	}
}