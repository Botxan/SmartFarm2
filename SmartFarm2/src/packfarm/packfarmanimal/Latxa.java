package packfarm.packfarmanimal;

/**
 * Represents a sheep that produces milk.
 * @author Oihan
 * @version 1
 */
public class Latxa extends Sheep {
	
	/**
	 * Latxa class constructor. Calls to Sheep superclass constructor.
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weight
	 * @param ecological wheter the animal production is ecological or not
	 */
	public Latxa(String id, int age, double weight, boolean ecological) {
		super(id, age, weight, "milk", ecological);		
	}
	
	@Override
	public String inheritancePath() {
		return "Latxa < " + super.inheritancePath();
	}
}
