package packfarm.packfarmanimal;

/**
 * Interface to evaluate wheter an animal has ecological production or not.
 * @author Oihan
 * @version 1
 */
public interface Ecological {
	/**
	 * Evaluates wheter an animal has ecological production or not.
	 * @return true if the animal has ecological production. Otherwise returns false
	 */
	public abstract boolean isEcological();
}
