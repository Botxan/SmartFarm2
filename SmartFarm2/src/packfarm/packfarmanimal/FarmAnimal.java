package packfarm.packfarmanimal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import packfarm.PhysiologicalValues;
import packfarm.Sensor;

/**
 * Represents any animal on the farm. Every animal is monitorized until it leaves the farm.
 * @author Oihan
 * @version 2
 */
public abstract class FarmAnimal implements Comparable<FarmAnimal> {
	private final String ID;
	private int age;
	private double weight;
	private int departureAge;
	private double anualFoodPrice;
	private Sensor mySensor;
	private PhysiologicalValues[] myValues;
	private final int MAX_VALUE = 7;
	private int length;
	
	/**
	 * FarmAnimal class constructor. Initializes ID attribute.
	 * @param id animal id
	 */
	public FarmAnimal(String id) {
		ID = id;
		
		myValues = new PhysiologicalValues[MAX_VALUE];
		length = 0;
	}
	
	/**
	 * FarmAnimal class constructor. Initializes ID, age, weight and mySensor.
	 * @param id animal id
	 * @param age animal age
	 * @param weight animal weight
	 * @param departureAge minimum age with which the animal can leave the farm
	 * @param anualFoodPrice anual food price
	 */
	public FarmAnimal(String id, int age, double weight, int departureAge, double anualFoodPrice) {
		this(id);
		this.age = age;
		this.weight = weight;
		this.departureAge = departureAge;
		this.anualFoodPrice = anualFoodPrice;
	}
	
	/**
	 * Getter for ID.
	 * @return animal identification string
	 */
	public String getId() {
		return ID;
	}
	
	/**
	 * Getter for age.
	 * @return animal age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Getter for weight.
	 * @return animal weigth
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Getter for mySensor.
	 * @return the sensor associated to the animal
	 */
	public Sensor getMySensor() {
		return mySensor;
	}
	
	/**
	 * Returns the usual temperature that the animal should have.
	 * @return the usual temperature that the animal should have
	 */
	public double getUsualTemperature() {
		return 39;
	}
	
	/**
	 * Returns the minimum age with which the animal can leave the farm.
	 * @return the minimum age with which the animal can leave the farm
	 */
	public int getDepartureAge() {
		return departureAge;
	}
	
	/**
	 * Returns the anual food price.
	 * @return anual food price
	 */
	public double getAnualFoodPrice() {
		return anualFoodPrice;
	}
	
	/**
	 * Setter for age.
	 * @param age animal age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Setter for weight.
	 * @param weight animal weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/** Setter for mySensor.
	 * @param mySensor the sensor associated to the animal
	 */
	public void setMySensor(Sensor mySensor) {
		this.mySensor = mySensor;
	}
	
	/**
	 * Registers and returns physiological values of the animal by using its sensor. If physiological data array is full,
	 * then all data stored is bulked to an external file, and the array is reset.
	 * @return physiological values of the animal
	 */
	public PhysiologicalValues register() {
		boolean collected = false;
		PhysiologicalValues phyValues = null;
		
		if (length == MAX_VALUE) {
			storeValuesInFile();
			initWeek();
		}
		while (!collected) {
			try {
				phyValues = mySensor.collectValues(getUsualTemperature());
				myValues[length] = phyValues;
				length++;
				collected = true;
			} catch(Sensor.CollectErrorException e) {
				// Omitting the print cause it would saturate the stdout
				// System.out.println(e.getMessage());
			}
		}
		
		return phyValues;
	}
	
	/**
	 * Stores all physiological values of myValues array in a file.
	 */
	private void storeValuesInFile() {
		try {
			FileWriter wr = new FileWriter(new File("./data/historicalValues.txt"), true);
			wr.write(toString() + "\n");
			for (int i = 0; i < MAX_VALUE; i++) wr.write(myValues[i].toString() + "\n");
			wr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Resets myValues array
	 */
	private void initWeek() {
		length = 0;
	}
	
	/**
	 * Returns the average of all temperatures of the animal stored until now.
	 * @return the average of the temperatures registered in the external file
	 * if no temperature is registered, the returned value is 0
	 */
	public double avgTemperature() {
		double sum = 0;
		for (int i = 0; i < length; i++) {
			sum += myValues[i].getTemperature();
		}
		return length == 0 ? 0 : sum/length;
	}
	
	/**
	 * Determines if an animal could be sick.
	 * @return true if the animal could be sick. Otherwise returns false
	 */
	public boolean possiblySick() {
		return avgTemperature() > getUsualTemperature() + 1.5 || 
			   avgTemperature() < getUsualTemperature() - 1.5;		
	}
	
	/**
	 * Checks if the animal is ready to leave the farm.
	 * @return true if the animal is ready to leave the farm. Otherwise returns false
	 */
	public boolean departure() {
		return age >= departureAge;
	}
	
	/**
	 * Returns the total annual cost of the animal.
	 * @return total annual cost of the animal
	 */
	public double calculateAnnualCost() {
		return anualFoodPrice;
	}
	
	/**
	 * Returns the inheritance path of the current class.
	 * @return the inheritance path of the current class.
	 */
	public String inheritancePath() {
		return "FarmAnimal";
	}
	
	/**
	 * Overwritten from Object superclass in order to return a propper String with FarmAnimal data.
	 */
	@Override
	public String toString() {
		return ID + " " + age + " " + weight + " " + mySensor;
	}
	
	/**
	 * Overwritten from Object superclass in order to properly compare 2 farm animals.
	 */
	@Override
	public boolean equals(Object obj) {
		// self check
		boolean equals = false;
		// null check
		if (this == obj) equals = true;
		else
			// type check
			if (getClass() != obj.getClass()) equals = false;
			else {
				// cast
				FarmAnimal animal = (FarmAnimal) obj;
				// field comparison
				equals = ID.equals(animal.getId());
			}
		return equals;
	}

	/**
	 * Overwritten from Comparable interface in order to properly order 2 farm animals by its ID.
	 */
	@Override
	public int compareTo(FarmAnimal animal) {
		return ID.compareTo(animal.getId());
	}
}