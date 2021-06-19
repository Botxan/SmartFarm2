package packfarm.packfarmanimal;

/**
 * Represents a cow
 * @author Oihan
 * @version 1
 */
public class Cow extends Producer {

	/**
	 * Cow class constructor. Calls to Producer superclass constructor.
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weight
	 * @param ecological wheter the animal production is ecological or not
	 */
	public Cow(String id, int age, double weight, boolean ecological) {
		super(id, age, weight, 20, 492.75, "milk", ecological);		
	}
	
	@Override
	public String inheritancePath() {
		return "Cow < " + super.inheritancePath();
	}
}
