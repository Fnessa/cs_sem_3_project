import java.util.Scanner;
import java.util.Random;

public class Grid {
	//constants
	final static int treeAmount = 43;
	final static int fireAmount = 12;
	final static int rockAmount = 10;
	final static int waterAmount = 5;
	final static int width = 10;
	final static int height = 8;
	final static Random random = new Random();

	public static void main(String[] args) {

		//creates grid
		Cell[][] grid = new Cell[height][width];

		//places objects on the grid in amounts specified above
		placeObjects(grid, treeAmount, fireAmount, rockAmount, waterAmount);

		int turn = 1;
		//runs while there is fire on the grid and goes through all of the steps of the game
		while (isFire(grid)) {
			System.out.printf("=== Game turn %d ===\n", turn);
			outputGrid(grid); 
			playerTurn(grid);
			fireTick(grid);
			turn += 1;
		}

		//prints out final grid and number of remaining trees
		System.out.println("=== Final result ===");
		outputGrid(grid);
		System.out.printf("Remaining trees: %d\n", countObjects(grid)[0]);

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
	public static void playerTurn(Cell[][] grid) {
		Scanner in = new Scanner(System.in);
		//prompts user for input and gets the selected x and y coordinates
		System.out.printf("Column to drop water (1 - %d): ", grid[0].length);
		int x_selected = in.nextInt() - 1;
		System.out.printf("Row to drop water (1 - %d): ", grid.length);
		int y_selected = in.nextInt() - 1;

		//if the selected cell is fire, changes it to water
		if (grid[y_selected][x_selected].getType().equals("fire")) {
			grid[y_selected][x_selected].setType("water");
		}
		wave(grid, y_selected + 1, x_selected, 0, 1);
		wave(grid, y_selected, x_selected + 1, 1, 0);
		wave(grid, y_selected, x_selected -1, -1, 0);
		wave(grid, y_selected -1, x_selected, 0, -1);

	}

	public static boolean wave(Cell[][] grid, int y_selected, int x_selected, int x_direction, int y_direction){
		if (y_selected < 0 || x_selected < 0 || y_selected + 1 >= grid.length || x_selected + 1 >= grid[0].length) {
			return false;
		}
		int next_x = x_selected + x_direction;
		int next_y = y_selected + y_direction;

		//if the current cell is rock or water, return false
		if (grid[y_selected][x_selected].getType().equals("rock") || grid[y_selected][x_selected].getType().equals("water")) {
			return false;
		}
		//if the current cell is fire
		else if (grid[y_selected][x_selected].getType().equals("fire")){
			grid[y_selected][x_selected].setType("water");
			//if the next cell is in grid return recursive wave, else return true
			if (next_x < width && next_x >= 0 && next_y < height && next_y >= 0) {
				return wave(grid, y_selected + y_direction, x_selected + x_direction, x_direction, y_direction);
			} else {return true;}
		}
		else {
			if (next_x < width && next_x >= 0 && next_y < height && next_y >= 0) {
				return wave(grid, y_selected + y_direction, x_selected + x_direction, x_direction, y_direction);
			} else {return true;}
		}
	}

	public static void fireTick(Cell[][] grid){
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].getType().equals("tree")) {
					if (i > 0) {
						if (grid[i - 1][j].getType().equals("fire")) {
							grid[i][j].flammable = true;
						}
					}
					if (i < grid.length - 1) {
						if (grid[i + 1][j].getType().equals("fire")) {
							grid[i][j].flammable = true;
						}
					}
					if (j > 0) {
						if (grid[i][j - 1].getType().equals("fire")) {
							grid[i][j].flammable = true;
						}

					}
					if (j < grid[i].length - 1) {
						if (grid[i][j + 1].getType().equals("fire")) {
							grid[i][j].flammable = true;
						}
					}
				}
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].getType().equals("fire")) {
					grid[i][j].setType("empty");
				}
				if (grid[i][j].flammable == true) {
					if (random.nextDouble() < 0.75) {
						grid[i][j].setType("fire");
					}
					grid[i][j].flammable = false;
				}
			}
		}
	}

	public static boolean isFire(Cell[][] grid){
		boolean fireExists = false;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].getType() == "fire") {
					fireExists = true;
					break;
				}
			}
		}
		return fireExists;
	}
}

