import java.util.Random;

public class Cell {

	private static String[] types = {"tree", "fire", "rock", "water",}; //types that are generated in the beginning
	private String type;
	public String representation; //character that represents the cell
	public boolean flammable = false;

	//Constructor
	public Cell(String type){
		this.setType(type);
	}

	//Setter for the type. Also sets how it is represented by calling setRepresentation
	public void setType(String type){
		this.type = type;
		this.setRepresentation();
	}

	public String getType(){
		return this.type;
	}
	public String getRepresentation(){
		return this.representation;
	}
	
	//returns a random type from the types array
	public static String randomType(){
		Random r = new Random();
		return types[r.nextInt(types.length)];
	}

	//sets representation according to the type of the instance
	public void setRepresentation(){
		switch (this.type) {
			case "tree":
				this.representation = "\u001B[32m" + "T" + "\u001B[0m";
				break;
			case "rock":
				this.representation = "#";
				break;
			case "water":
				this.representation = "\u001B[34m" + "W" + "\u001B[0m";
				break;
			case "fire":
				this.representation = "\u001B[31m" + "F" + "\u001B[0m";
				break;
			case "empty":
				this.representation = ".";
				break;
			default:
				this.representation = "!";
				break;
		}
	}
}
