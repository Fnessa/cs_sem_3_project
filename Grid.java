public class Grid {
	public static void main(String[] args) {
		int height = 9;
		int width = 9;
		//x = rows, width
		//y = columns, height
		Cell[][] grid = new Cell[width][height];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(Cell.randomType());
			}
		}
		getObjectFromCenter(width, height, grid);
		outputGrid(grid);
	}

	public static void outputGrid(Cell[][] grid){
		for (Cell[] column : grid) {
			for (Cell cell : column) {
				System.out.print(" "+cell.getRepresentation()+" ");
			}
			System.out.println("");
		}
	}
	public static void getObjectFromCenter(int width, int height, Cell[][] grid){
		int centerx;
		int centery;
		if (width%2 != 0) {
			centerx = (width + 1)/2;
		} else { centerx = width/2; }
		if (height%2 != 0) {
			centery = (height + 1)/2;
		} else { centery = height/2; }
		grid[centerx-1][centery-1] = new Cell("object");
		System.out.println(centerx + ", " + centery);
	}
}
