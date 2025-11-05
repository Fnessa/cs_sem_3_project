import java.util.Random;

public class Cell {

	private static String[] types = {"rock", "empty", "object"};
	private String type;
	public String representation;

	public Cell(String type){
		this.setType(type);

		switch (this.type) {
			case "rock":
				this.representation = "R";
				break;
			case "empty":
				this.representation = "_";
				break;
			case "object":
				this.representation = "x";
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
		return types[r.nextInt(2)];
	}
}
