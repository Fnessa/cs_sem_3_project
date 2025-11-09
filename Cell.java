import java.util.Random;

public class Cell {

	private static String[] types = {"tree", "fire", "rock", "water",};
	private String type;
	public String representation;

	public Cell(String type){
		this.setType(type);

		switch (this.type) {
			case "tree":
				this.representation = "T";
				break;
			case "rock":
				this.representation = "#";
				break;
			case "water":
				this.representation = "W";
				break;
			case "fire":
				this.representation = "F";
				break;
			case "empty":
				this.representation = ".";
				break;
			default:
				this.representation = "!";
				break;
		}
	}

	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public String getRepresentation(){
		return this.representation;
	}
	public static String randomType(){
		Random r = new Random();
		return types[r.nextInt(types.length)];
	}
}
