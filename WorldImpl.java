package uk.ac.cam.rjm232.tick7;

import java.awt.Color;
import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;
//TODO: insert other appropriate "import" statements here

public abstract class WorldImpl implements World {
 	private int width;
 	private int height;
 	public int generation;
 	
 	protected WorldImpl(int width, int height) {
  		this.width = width;
  		this.height = height;
  		this.generation = 0;
	}
	
 	protected WorldImpl(WorldImpl prev) {
  		this.width = prev.width;
  		this.height = prev.height;
  		this.generation = prev.generation + 1;
 	}
 	
 	public int getWidth() { return this.width; }
 	public int getHeight() { return this.height; }
 	public int getGeneration() { return this.generation; }
 	public int getPopulation() { return 0; }
 	protected String getCellAsString(int col,int row) { return getCell(col,row) ? "#" : "_"; }
 	protected Color getCellAsColour(int col,int row) { return getCell(col,row) ? Color.BLACK : Color.WHITE; }
 	
 	public void draw(Graphics g,int width, int height) {
  		int worldWidth = getWidth();
  		int worldHeight = getHeight();
  		double colScale = (double)width/(double)worldWidth;
  		double rowScale = (double)height/(double)worldHeight;
  		
  		for(int col=0; col<worldWidth; ++col) {
   			for(int row=0; row<worldHeight; ++row) {
    			int colPos = (int)(col*colScale);
    			int rowPos = (int)(row*rowScale);
    			int nextCol = (int)((col+1)*colScale);
    			int nextRow = (int)((row+1)*rowScale);
    			if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
     				g.setColor(getCellAsColour(col, row));
     				g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
				} 
			}
		} 
	}

 	public World nextGeneration(int log2StepSize) {
 		World world = this;
  	 	for (int i = 0; i < (1 << log2StepSize); i++) {
    		world = ((WorldImpl) world).nextGeneration();
   		}	
   		return world;
 	}
 	
 	//TODO: Complete here in parent
 	public void print(Writer w) {
  		//Use getCellAsString to get text representation of the cell
  		PrintWriter pw = new PrintWriter(w);
 		pw.println("-");
 		for (int row = 0; row < height; row++) {
  			for (int col = 0; col < width; col++) {
   				pw.print(getCellAsString(col, row));
			}
  			pw.println();
 		}
 		pw.flush();
 	}
 	
 	//TODO: Complete here in parent
 	protected int countNeighbours(int col, int row) {
  		int nCount = 0;
		for (int x = col-1; x<=col+1; x++) {
			for (int y = row-1; y<=row+1; y++) {
				if (getCell(x, y)) {
					nCount++;
				}
			}
		}
		if(getCell(col, row)) {
			nCount--;        //Accounts for counting the current cell in the for loop.
		}
		return nCount;	
 	}
 	
 	//TODO: Complete here in parent
 	protected boolean computeCell(int col, int row) {
  		// liveCell is true if the cell at position (col,row) in world is live
 		boolean liveCell = getCell(col, row);
 		
 		// neighbours is the number of live neighbours to cell (col,row)
 		int neighbours = countNeighbours(col, row);
 		
 		// we will return this value at the end of the method to indicate whether
 		// cell (col,row) should be live in the next generation	
 		boolean nextCell = false;
 		
 		//A live cell with less than two neighbours dies (underpopulation)
 		if (neighbours < 2) {
  			nextCell = false;
 		}
 		
 		//A live cell with two or three neighbours lives (a balanced population)
 		//neighbours == 3 case covered below
 		if (liveCell && neighbours == 2) {
 			nextCell = true;
 		}
 		
 		//A live cell with with more than three neighbours dies (overcrowding)
 		if (neighbours > 3) {
 			nextCell = false;
 		}
 		
 		//A dead cell with exactly three live neighbours comes alive
 		if (neighbours == 3) {
 			nextCell = true;
 		}
 		 		
 		return nextCell;
 	}
 	
 	// Will be implemented by child class. Return true if cell (col,row) is alive.
 	public abstract boolean getCell(int col,int row);
 	// Will be implemented by child class. Set a cell to be live or dead.
 	public abstract void setCell(int col, int row, boolean alive);
 	// Will be implemented by child class. Step forward one generation.
 	protected abstract WorldImpl nextGeneration();
}