package packsimulator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import packfarm.*;
import packfarm.packfarmanimal.*;

/**
 * The FarmSimulatorHierarchy class takes the management of the smart farm by simulating in its main method:
 * <ul>
 * 		<li>The behaviour of the farm creation.</li>
 * 		<li>The correct operation of the sensors.</li>
 * 		<li>The creation and control of different type of animals</li>
 * </ul>
 * @author Oihan
 * @version 1
 */
public class FarmSimulatorHierarchy {
	private static Farm farm;
	
	/**
	 * Main method
	 * @param args No parameters required
	 */
	public static void main(String[] args) {		
		farm = Farm.getInstance();
		
		loadSensors();
		System.out.println("Available sensors: " + farm.howManySensor() + " + the general sensor for chickens.");
		
		System.out.print("\nRegistering animals...");
		initializeFarmAnimals();		
		System.out.println("Done.\n");

		farm.whoIsInFarm();
		
		System.out.print("\nRegistering the physiological values of each animal 7 times...");
		// Register physiological values of each animal 7 times
		for (int i = 0; i < 7; i++) farm.register();
		System.out.println("Done");
		
		System.out.println("\nPrinting possibly sick animals and it's inheritance path: ");
		ArrayList<FarmAnimal> possiblySickList = farm.obtainPossiblySick();
		for (FarmAnimal animal: possiblySickList) {
			System.out.println(animal.toString());
			System.out.println(animal.inheritancePath());
		}
		System.out.println("Done.");
		
		System.out.print("\nSorting animals based on its ID...");
		farm.sortFarm();
		System.out.println("Done. Printing animals ordered by ID:");
		farm.whoIsInFarm();		
		System.out.println("Done.");
		
		System.out.println("\nTotal annual feed cost for farm animals: " + farm.calculateAnnualCost());
		
		System.out.println("\nAmount of ecological animals: " + farm.farmEcologicalAnimalAccount());
		
		System.out.println("\nNumber of sensors available: " + farm.howManySensor());
		
		System.out.print("\nRemoving animals that meet the defined conditions...");
		ArrayList<FarmAnimal> departureList = farm.farmAnimalDeparture();
		System.out.println("Done. \nAmount of animals that left the farm: " + departureList.size());
		System.out.println("Printing the identifiers of the animals that have left the farm:");
		for (FarmAnimal animal: departureList) {
			System.out.println(animal.getId());
		}
		
		System.out.println("\nNumber of sensors available after departure: " + farm.howManySensor() + " + the general sensor for chickens.");
	}
	
	/**
	 * Reads the sensor file and adds all the sensors to the farm sensor list.
	 */
	private static void loadSensors() {
		Scanner sc;
		try {
			sc = new Scanner(new FileReader("./data/availableSensors.txt"));
			while (sc.hasNext()) {
				farm.addSensor(new Sensor(sc.nextLine()));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: available sensors file not found");
		}
	}
	
	/**
	 * Initializes a bunch of animals
	 */
	public static void initializeFarmAnimals() {
		// 5 chickens (3 ecological and two not ecological. In general of 1 year old but 1 of 4
		farm.addFarmAnimal(new Chicken("CHI34", 1, 1.5, true));
		farm.addFarmAnimal(new Chicken("CHI35", 1, 1.5, true));
		farm.addFarmAnimal(new Chicken("CHI36", 1, 1.5, true));
		farm.addFarmAnimal(new Chicken("CHI37", 4, 1.5, false));
		farm.addFarmAnimal(new Chicken("CHI38", 1, 1.5, false));

		// 2 ecological Latxa sheep and 2 non-ecological WoolSheep, about 45 kilos and 3-5 years old. One of them of 12 years old
		farm.addFarmAnimal(new Latxa("SHE80", 5, 48, true));
		farm.addFarmAnimal(new Latxa("SHE81", 3, 46, true));
		farm.addFarmAnimal(new WoolSheep("SHE82", 12, 45, false));
		farm.addFarmAnimal(new WoolSheep("SHE83", 4, 44, false));

		// 5 cows of about 450-600 kilos and of about 15 years (one of 20 years), 3 ecological and 2 not ecological
		farm.addFarmAnimal(new Cow("COW51", 18, 446, true));
		farm.addFarmAnimal(new Cow("COW52", 16, 500, true));
		farm.addFarmAnimal(new Cow("COW53", 15, 470, true));
		farm.addFarmAnimal(new Cow("COW54", 11, 550, false));
		farm.addFarmAnimal(new Cow("COW55", 10, 600, false));

		// 3 pigs with id "PIG"+n of all between 80 and 200 kilos, all of them of 1 year old, two of them ecological
		farm.addFarmAnimal(new Pig("PIG91", 1, 80, true));
		farm.addFarmAnimal(new Pig("PIG92", 1, 150, true));
		farm.addFarmAnimal(new Pig("PIG93", 1, 200, false));
		
		// 3 horses, one of each type, with identifiers "HOR"+n, 7 years old the ones of ride and show types,
		//  and the work type of 10 years old. Approximate weight 300-400 kilos
		farm.addFarmAnimal(new Horse("HOR41", 7, 340, "show"));
		farm.addFarmAnimal(new Horse("HOR42", 7, 380, "ride"));
		farm.addFarmAnimal(new Horse("HOR43", 10, 400, "work"));
	}
}
