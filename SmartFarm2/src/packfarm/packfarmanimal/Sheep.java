package packfarm.packfarmanimal;

/**
 * Represents a sheep in general.
 * @author Oihan
 * @version 1
 */
public abstract class Sheep extends Producer {

	/**
	 * Sheep class constructor. Calls to Producer superclass constructor.
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weight
	 * @param product the product that the animal produces
	 * @param ecological wheter the animal production is ecological or not
	 */
	public Sheep(String id, int age, double weight, String product, boolean ecological) {
		super(id, age, weight, 12, 133.23, product, ecological);		
	}
	
	@Override
	public String inheritancePath() {
		return "Sheep < " + super.inheritancePath();
	}
}