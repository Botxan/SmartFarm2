package packfarm.packfarmanimal;

/**
 * Represents a producer farm animal in general.
 * @author Oihan
 * @version 1
 */
public abstract class Producer extends FarmAnimal implements Ecological {
	
	private String product;
	private boolean ecological;
	
	/**
	 * Producer class constructor. Calls to FarmAnimal superclass constructor and
	 * initializes product and ecological attributes.
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weigh
	 * @param departureAge minimum age with which the animal can leave the farm
	 * @param anualFoodPrice anual food price
	 * @param product the product that the animal produces
	 * @param ecological wheter the animal production is ecological or not
	 */
	public Producer(String id, int age, double weight, int departureAge, double anualFoodPrice, String product, boolean ecological) {
		super(id, age, weight, departureAge, anualFoodPrice);
		this.product = product;
		this.ecological = ecological;
	}
	
	/**
	 * {@inheritDoc}
	 * <br> It is taken into account wheter the animal production is ecological or not.
	 */
	@Override
	public double calculateAnnualCost() {
		return super.getAnualFoodPrice() * (isEcological() ? 1.20 : 1);
	}
	
	/**
	 * {@inheritDoc}
	 * <br> Adds product attribute and wheter the animal production is ecological or not at the end of the String.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + product + " " + isEcological();
	}
	
	@Override
	public String inheritancePath() {
		return "Producer < " + super.inheritancePath();
	}

	@Override
	public boolean isEcological() {
		return ecological;
	}

}
