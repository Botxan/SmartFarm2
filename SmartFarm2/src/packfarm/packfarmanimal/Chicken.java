package packfarm.packfarmanimal;

/**
 * Represents a chicken
 * @author Oihan
 * @version 1
 */
public class Chicken extends Producer {
	
	/**
	 * Chicken class constructor. Calls to Producer superclass constructor.
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weight
	 * @param ecological wheter the animal production is ecological or not
	 */
	public Chicken(String id, int age, double weight, boolean ecological) {
		super(id, age, weight, 4, 547.5, "eggs", ecological);
	}
	
	@Override
	public double getUsualTemperature() {
		return 42;
	}
	
	@Override
	public String inheritancePath() {
		return "Chicken < " + super.inheritancePath();
	}
}