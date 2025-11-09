public class Grid {
	public static void main(String[] args) {
		final int treeAmount = 43;
		final int fireAmount = 12;
		final int rockAmount = 10;
		final int waterAmount = 5;
		//x = rows, width
		//y = columns, height
		int width = 10;
		int height = 8;

		//creates grid
		Cell[][] grid = new Cell[height][width];

		placeObjects(grid, treeAmount, fireAmount, rockAmount, waterAmount);

		outputGrid(grid);

		System.out.printf("Trees: %d, Rocks: %d, Water: %d, Burning trees: %d \n", countObjects(grid)[0], countObjects(grid)[1], countObjects(grid)[2], countObjects(grid)[3]);
	}
	public static void outputGrid(Cell[][] grid){
		System.out.println("   1 2 3 4 5 6 7 8 9 10");
		int line = 1;
		for (Cell[] column : grid) {
			System.out.printf("%2d", line);
			for (Cell cell : column) {
				if (cell == null) {
					System.out.print(" n");
				}
				else { System.out.print(" "+cell.getRepresentation()); }
			}
			line += 1;
			System.out.println("");
		}
	}
	public static void placeObjects(Cell[][] grid, int treeAmount, int fireAmount, int rockAmount, int waterAmount){
		int treesAdded = 0, fireAdded = 0, rocksAdded = 0, waterAdded = 0;
		while (treesAdded < treeAmount || fireAdded < fireAmount|| rocksAdded < rockAmount || waterAdded < waterAmount) {
			//adds random cells to grid
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j] == null) {
						String type = Cell.randomType();
						switch (type) {
							case "tree":
								if (treesAdded < treeAmount) {
									grid[i][j] = new Cell(type);
									treesAdded += 1;
								}
								break;
							case "fire":
								if (fireAdded < fireAmount) {
									grid[i][j] = new Cell(type);
									fireAdded += 1;
								}
							case "rock":
								if (rocksAdded < rockAmount) {
									grid[i][j] = new Cell(type);
									rocksAdded += 1;
								}
								break;
							case "water":
								if (waterAdded < waterAmount) {
									grid[i][j] = new Cell(type);
									waterAdded += 1;
								}
								break;
							case "empty":
								grid[i][j] = new Cell(type);
								break;
							default:
								grid[i][j] = new Cell("aAAAA");
								break;
						}
					}
				}
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == null) {
					grid[i][j] = new Cell("empty");
				}
			}
		}
	}
	public static int[] countObjects(Cell[][] grid) {
		int[] objects = {
			0, //trees
			0, //rocks
			0, //water
			0 //fire
		};
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != null) {
					switch (grid[i][j].getType()) {
						case "tree":
							objects[0] += 1;
							break;
						case "rock":
							objects[1] += 1;
							break;
						case "water":
							objects[2] += 1;
							break;
						case "fire":
							objects[3] += 1;
							break;
						default:
							break;
					}

				}
			}
		}
		return objects;
	}
}
