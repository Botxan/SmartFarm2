package packfarm.packfarmanimal;

/**
 * Reprents a horse.
 * @author Oihan
 * @version 1
 */
public class Horse extends FarmAnimal {
	private String type;

	/**
	 * Horse animal constructor. Calls to FarmAnimal superclass constructor and
	 * initalizes type attribute.
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weigh
	 * @param type horse type
	 */
	public Horse(String id, int age, double weight, String type) {
		super(id, age, weight, 5, 3175.5);
		this.type = type;
	}
	
	/**
	 * Getter for type attribute.
	 * @return horse type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * {@inheritDoc}
	 * <br> It is taken into account the animal weight.
	 */
	@Override
	public double calculateAnnualCost() {
		return super.getAnualFoodPrice() + 0.01 * getWeight();
	}

	/**
	 * <br> The animal can not be sick for its departure. 
	 */
	@Override
	public boolean departure() {
		return super.departure() && !possiblySick();
	}
	
	/**
	 * {@inheritDoc}
	 * <br> Adds Horse type at the end of the String.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + type;
	}
	
	@Override
	public String inheritancePath() {
		return "Horse < " + super.inheritancePath();
	}
}
