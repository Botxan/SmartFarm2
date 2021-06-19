package packfarm;

/**
 * PhysiologicalValues class represents the physiological parameters of an animal.
 * @author Oihan
 * @version 1
 */
public class PhysiologicalValues {
	private int heartRate;
	private double temperature;
	private int activity; // From 1 to 5
	
	/**
	 * PhysiologicalValues Constructor. Initializes every attribute.
	 * @param heartRate animal heart rate
	 * @param temperature animal body temperature
	 * @param activity animal activity (must be from 1 to 5)
	 */
	public PhysiologicalValues(int heartRate, double temperature, int activity) {
		this.heartRate = heartRate;
		this.temperature = temperature;
		this.activity = activity;
	}
	
	/**
	 * Getter for heartRate.
	 * @return the heart rate
	 */
	public int getHeartRate() {
		return heartRate;
	}
	
	/**
	 * Getter for temperature.
	 * @return the animal body temperature
	 */
	public double getTemperature() {
		return temperature;
	}
	
	/**
	 * Getter for activity.
	 * @return animal activity (range 1..5)
	 */
	public int getActivity() {
		return activity;
	}
	
	/**
	 * Overwritten from Object superclass in order to return a propper String with all the physiological data.
	 */
	@Override
	public String toString() {
		return heartRate + " " + temperature + " " + activity;
	}
}