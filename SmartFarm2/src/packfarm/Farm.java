package packfarm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import packfarm.packfarmanimal.Chicken;
import packfarm.packfarmanimal.FarmAnimal;
import packfarm.packfarmanimal.Pig;
import packfarm.packfarmanimal.Producer;

/**
 * Farm class includes all necessary information in order to monitorize farm animals
 * The class uses Singleton pattern, taking into account that the information
 * relative to the monitorization must be centralized and unique.
 * @author Oihan
 * @version 1
 */
public class Farm {
	private static Farm instance;
	private final static Sensor SENSOR = new Sensor("ID00");
	private ArrayList<FarmAnimal> farmAnimalSet;
	private ArrayList<Sensor> sensorList;	
	
	/**
	 * Farm Class constructor. Initializes animal and sensor lists.
	 */
	private Farm() {
		farmAnimalSet = new ArrayList<FarmAnimal>();
		sensorList = new ArrayList<Sensor>();
	}
	
	/**
	 * Returns the Farm class instance (following Singleton pattern).
	 * @return the Farm class instance
	 */
	public static Farm getInstance() {
		if (instance == null) instance = new Farm();
		return instance;
	}

	/**
	 * Adds an animal to the farm animal set.
	 * @param animal the farm animal to be added.
	 * @throws IndexOutOfBoundsException if no sensors are available (empty list)
	 * It is not mandatory to declare IndexOutOfBoundsException in the header because it
	 * is a runtime exception, so the compiler is aware that it can be raised
	 */
	public void addFarmAnimal(FarmAnimal animal) throws IndexOutOfBoundsException {
		if (animal.getClass().equals(Chicken.class)) {
			animal.setMySensor(SENSOR);
		} else if (sensorList.size() > 0) {
			animal.setMySensor(sensorList.remove(0));
		} else {
			throw new IndexOutOfBoundsException("No sensors available");
		}
		farmAnimalSet.add(animal);
	}
	
	/**
	 * Returns the amount of animals in the farm animal set.
	 * @return the amount of animals in the farm animal set
	 */
	public int howManyAnimals() {
		return farmAnimalSet.size();
	}
	
	/**
	 * Adds a sensor to the sensor list.
	 * @param sensor the sensor to add
	 */
	public void addSensor(Sensor sensor) {
		sensorList.add(sensor);
	}
	
	/**
	 * Returns the amount of sensors in the sensor list (+1 because of the general sensor "ID00").
	 * @return the amount of sensors in the sensor list (+1 because of the general sensor "ID00")
	 */
	public int howManySensor() {
		return sensorList.size();
	}
	
	
	/**
	 * Gets a physiological register from every farm animal.
	 */
	public void register() {
		for (FarmAnimal animal: farmAnimalSet) {
			animal.register();
		}
	}
	
	/**
	 * Returns an array of all the animals that could be sick.
	 * @return an array of all the animals that could be sick
	 */
	public ArrayList<FarmAnimal> obtainPossiblySick() {
		ArrayList<FarmAnimal> sickAnimals = new ArrayList<FarmAnimal>();
		for (FarmAnimal animal: farmAnimalSet) {
			if (animal.possiblySick()) {
				sickAnimals.add(animal);
			}			
		}
		
		return sickAnimals;
	}
	
	/**
	 * Returns from farm animal set the animal passed by parameter.
	 * (*) We are supposing that the animal passed by parameter is already registered in the farm
	 * @param animal the farm animal to be retrieved from farm animal set
	 * @return the farm animal passed by parameter, from farm animal set
	 * the farm is passed by parameter 
	 */
	public FarmAnimal obtainFarmAnimal(FarmAnimal animal) {
		int index = farmAnimalSet.indexOf(animal);
		return farmAnimalSet.get(index);
	}
	
	/**
	 * Removes from farm animal set the animal passed by parameter (*).
	 * (*) We are supposing that the animal passed by parameter is already registered in the farm
	 * @param animal the animal to be removed
	 * @return the animal that has been deleted
	 * the farm is passed by parameter 
	 */
	public FarmAnimal removeFarmAnimal(FarmAnimal animal){
		int index = farmAnimalSet.indexOf(animal);
		sensorList.add(animal.getMySensor());
		return farmAnimalSet.remove(index);
	}
	
	/**
	 * Given an age, returns an array with every animal that is older than the age passed
	 * by parameter.
	 * @param age animal age
	 * @return an array with every animal that is older than the age passed by parameter
	 */
	public ArrayList<String> obtainFarmAnimalOlder(int age) {
		ArrayList<String> olderAnimalIds = new ArrayList<String>();		
		for (FarmAnimal animal: farmAnimalSet) {
			if (animal.getAge() > age) olderAnimalIds.add(animal.getId());
		}
		return olderAnimalIds;
	}
	
	/**
	 * Removes and returns every animal that is ready to leave the farm. Also, it retrieves the
	 * sensors from removed animals.
	 * @return an array with all the animals that left the farm
	 */
	public ArrayList<FarmAnimal> farmAnimalDeparture() {
		ArrayList<FarmAnimal> departureAnimalIds = new ArrayList<FarmAnimal>(); 
		Iterator<FarmAnimal> it = farmAnimalSet.iterator();
		
		while (it.hasNext()) {
			FarmAnimal animal = it.next();
			if (animal.departure()) {
				departureAnimalIds.add(animal);
				if (!animal.getClass().equals(Chicken.class)) {
					sensorList.add(animal.getMySensor());
					it.remove();
				}
			}
		}
		
		return departureAnimalIds;
	}
	
	/**
	 * Sorts the farm animal set based on animal id.
	 */
	public void sortFarm() {
		Collections.sort(farmAnimalSet);
	}
	
	/**
	 * Prints all the information of every animal in the Farm.
	 */
	public void whoIsInFarm() {
		System.out.println("Animals in the Farm:");
		for (FarmAnimal animal: farmAnimalSet) {
			System.out.println(animal.toString());
		}
	}
	
	/**
	 * Returns the sum of total annual cost of each animal.
	 * @return the sum of total annual cost of each animal
	 */
	public double calculateAnnualCost() {
		double totalCost = 0;
		for (FarmAnimal animal: farmAnimalSet) {
			totalCost += animal.calculateAnnualCost();
		}
		
		return totalCost;
	}
	
	/**
	 * Returns the number of animals that its production is ecological.
	 * @return the number of animals that its production is ecological
	 */
	public int farmEcologicalAnimalAccount() {
		int count = 0;
		
		for (FarmAnimal animal: farmAnimalSet) {
			// Check if animal is Pig
			if (animal.getClass().equals(Pig.class)) {
				if (((Pig) animal).isEcological()) count++;
			}
			// Check if animal is Producer (or subclass of Producer)
			else if (animal instanceof Producer)
				if(((Producer) animal).isEcological()) count++;
		}
		
		return count;
	}
}