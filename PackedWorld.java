package uk.ac.cam.rjm232.tick7;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class PackedWorld extends WorldImpl {
	private long cells;
	
	public PackedWorld() {
		super(8,8);
		cells = 0L;
	}
	public PackedWorld(PackedWorld prev) {
		super(prev);
		cells = 0L;
	}
	
	@Override
	public boolean getCell(int col, int row) {
		if (col<0 || col>7 || row<0 || row >7) {
			return false;
		}
		
		int bit = 8*row + col;
		
		return PackedLong.get(cells, bit);
	}
	
	@Override
	public void setCell(int col, int row, boolean alive) {
		if (col<0 || col>7 || row<0 || row >7) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		int bit = 8*row + col;
		
		cells = PackedLong.set(cells, bit, alive);
	}
	
	@Override 
	protected PackedWorld nextGeneration() {
		PackedWorld nextWorld = new PackedWorld(this);
		for (int x = 0; x < nextWorld.getWidth(); x++) {
			for (int y = 0; y < nextWorld.getHeight(); y++) {
				nextWorld.setCell(y, x, computeCell(y, x));
			}
		}
		return nextWorld;
	}
}