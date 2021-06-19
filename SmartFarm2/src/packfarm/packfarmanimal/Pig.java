package packfarm.packfarmanimal;

/**
 * Represents a pig.
 * @author Oihan
 * @version 1
 */
public class Pig extends FarmAnimal implements Ecological {
	
	private boolean ecological;

	/**
	 * Pig class constructor. Calls to FarmAnimal superclass constructor and
	 * initializes ecological attribute.
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weight
	 * @param ecological wheter the animal production is ecological or not
	 */
	public Pig(String id, int age, double weight, boolean ecological) {
		super(id, age, weight, 1, 134.32);
		this.ecological = ecological;
	}
	
	/**
	 * {@inheritDoc}
	 * <br> It is taken into account wheter the animal production is ecological or not, and its weight.
	 */
	@Override
	public double calculateAnnualCost() {
		return (super.getAnualFoodPrice() + 0.035 * getWeight()) * (isEcological() ? 1.15 : 1);
	}
	
	/**
	 * {@inheritDoc}
	 * <br> Also the animal could be ready to leave if its weight is greater or equal than 100 kilos.
	 * <br> The animal can not be sick for its departure.
	 */
	@Override
	public boolean departure() {
		return (super.departure() || getWeight() >= 100) && !possiblySick();
	}
	
	/**
	 * {@inheritDoc}
	 * <br> Adds wheter the animal production is ecological or not at the end of the String.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + isEcological();
	}

	@Override
	public String inheritancePath() {
		return "Pig < " + super.inheritancePath();
	}
	
	@Override
	public boolean isEcological() {
		return ecological;
	}
	
}
