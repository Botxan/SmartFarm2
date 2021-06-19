package packfarm.packfarmanimal;

/**
 * Represents a sheep that produces wool.
 * @author Oihan
 * @version 1
 */
public class WoolSheep extends Sheep {
	
	/**
	 * WoolSheep class constructor. Calls to Sheep superclass constructor. 
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weight
	 * @param ecological wheter the animal production is ecological or not
	 */
	public WoolSheep(String id, int age, double weight, boolean ecological) {
		super(id, age, weight, "wool", ecological);		
	}
	
	@Override
	public String inheritancePath() {
		return "WoolShep < " + super.inheritancePath();
	}
}
