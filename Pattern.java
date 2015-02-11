package uk.ac.cam.rjm232.tick7;

import uk.ac.cam.acr31.life.World;

public class Pattern {
 	private String name;
 	private String author;
 	private int width;
 	private int height;
 	private int startCol;
 	private int startRow;
 	private String cells;
 	private String stringFormat;
 	
 	public String getName() { return name; }
 	public String getAuthor() { return author; }
 	public int getWidth() { return width; }
 	public int getHeight() { return height; }
 	public int getStartCol() { return startCol; }
 	public int getStartRow() { return startRow; }
 	public String getCells() { return cells; }
 	public String toString() { return stringFormat; }
	
 	public Pattern(String format) throws PatternFormatException {
 		stringFormat = format;
 		
  		String[] parts = format.split(":");
  		
  		if (parts.length < 7) {
  			throw new PatternFormatException("Error: Not enough ':' characters in String.");
  		}
  		
  		name = parts[0];
  		author = parts[1];
  		try {
  			width = Integer.parseInt(parts[2]);
  			height = Integer.parseInt(parts[3]);
  			startCol = Integer.parseInt(parts[4]);
  			startRow = Integer.parseInt(parts[5]);
  		}
  		catch (Exception e) {
  			throw new PatternFormatException("Error: One of the required integers was not of the correct form.");
  		}
  		cells = parts[6];
	}
	
 	public void initialise(World world) throws PatternFormatException {
 		String[] liveCells = cells.split(" ");
   		
   		for (int row = 0; row < liveCells.length; row++) {
   			char[] currentStringRow = liveCells[row].toCharArray();
   			for (int col = 0; col < currentStringRow.length; col++) {
   				int value = Character.getNumericValue(currentStringRow[col]);
   				if (value != 0 && value != 1) {
   					throw new PatternFormatException("Error: One of your cells was not of the correct integer form.");
   				}
   				boolean isAlive = (value == 1) ? true : false;
				world.setCell(startCol + col, startRow + row, isAlive);
   			}
   		}
	} 
}