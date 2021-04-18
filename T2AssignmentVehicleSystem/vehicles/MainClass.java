package vehicles;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import uod.gla.io.File;
import uod.gla.menu.Finalisable;
import uod.gla.menu.MenuBuilder;
import uod.gla.menu.MenuItem;
import uod.gla.util.CollectionUtils;
import uod.gla.util.Reader;


public class MainClass implements Finalisable {
	private static List <VehicleSuperclass> vehicleList = new ArrayList <>();

	// This is used to "serialise" the list of persons
	private static File vehicleManagementFile = new File("savedData", "vehicleDatabase");

	// The object on which the name of the method to be invoked is searched for
	// Also used to provide access to the non-static methods e.g. finalise
	private static MainClass appObject = new MainClass();




	public static void main(String[] args) {

		// Welcome message
		System.out.println("Welcome to Chi's Car Showroom \n\"Where All Your Vehicular Dreams Come True\"™ \n© Chi 2021 all rights reserved.");
		MenuBuilder.setDefaultPrompt("Choose an option from the list...");

		// retrieve data from disk at start up
		List<VehicleSuperclass> retrievedVehicleDatabase = vehicleManagementFile.<List<VehicleSuperclass>>retrieve(true);

		System.out.println("\n-----------------System Status----------------");
		System.out.println("The Database size saved to disk has: " +retrievedVehicleDatabase.size() +" vehicles.");
		

		if (retrievedVehicleDatabase.isEmpty()) {
			System.out.println("Error! no vheicles detected, please create one.");
			System.out.println("----------------Checks complete---------------");
			
			// Menu options
			MenuItem a = new MenuItem("A", "Add the first vehicle into the system", appObject, "addNew");
			MenuBuilder.displayMenu(appObject, a); 

			// save to disk at shutdown
			appObject.finalise();
			System.out.println("Thanks for using this app, and remember, sales = success!");

		} else
		{
			vehicleList  = retrievedVehicleDatabase;
			System.out.println("The program has successfully loaded: " +vehicleList.size() +" vehicles into memory.");
			System.out.println("----------------Checks complete---------------");
			
			// Menu options
			MenuItem a = new MenuItem("A", "Add a new vehicle into the system", appObject, "addNew");
			MenuItem d = new MenuItem("D", "Display all Vehicles", appObject, "display");
			MenuItem e = new MenuItem("E", "Edit a vehicle's details", appObject, "edit");
			MenuItem s = new MenuItem("S", "Search for a vehicle to display its details", appObject, "Search");
			MenuItem r = new MenuItem("RM", "Remove a vehicle", appObject, "remove");
			MenuItem v = new MenuItem("V", "Divorce two persons", appObject, "divorce");
			MenuBuilder.displayMenu(appObject, a,d,e,s,r); //, e, m, p, v

			// save to disk at shutdown
			appObject.finalise();
			System.out.println("Thanks for using this app, and remember, sales = success!");
		} 







	}


	@Override public void finalise() {
		vehicleManagementFile.save((Serializable)vehicleList); }



	public static void addNew() {

		Scanner keyboard = new Scanner(System.in); 

		//Vehicle Specific variables
		Make make;
		String model;
		int year;
		Transmission gearbox = Transmission.MANUAL; //made manual , but users will always have to select. 
		Colours colour = null;
		int mileage;
		String vin;

		//car specific variables
		boolean satnav = false;
		boolean parkingSensors = false;
		boolean towBar = false;
		boolean roofRack = false;

		//estate specific variables 
		boolean thirdRowSeat = false;

		//Bike Specific variables
		boolean luggageBox = false;

		//SUV Specific variables
		boolean allWheelDrive = false;




		System.out.println("What type of vehicle would you like to add?"
				+ "\nSelect a vehicle type from the list below:"
				+ "\nE for estate"
				+ "\nH for hatchback"
				+ "\nM for motorbike"
				+ "\nS for saloon"
				+ "\nSUV for SUV");
		String vehicleType = keyboard.next();


		// validates correct entry.
		while (!"e".equalsIgnoreCase(vehicleType)&&!"h".equalsIgnoreCase(vehicleType)&&!"m".equalsIgnoreCase(vehicleType)&&!"s".equalsIgnoreCase(vehicleType)
				&&!"suv".equalsIgnoreCase(vehicleType)) {
			System.out.println("\"" +vehicleType +"\" didn't match any vehicle type... Try again.");
			System.out.println("What type of vehicle would you like to add?\nE for estate\nH for hatchback\nM for motorbike\nS for saloon\nSUV for SUV.");
			vehicleType = keyboard.next();
		}

		//Input needed for ALL VEHICLES
		make=Reader.readEnum("enter the manufacturer of the vehicle",Make.class);
		System.out.println("What is the model of the " +make +"?");
		String modelu = keyboard.next();
		model = modelu.substring(0, 1).toUpperCase() + modelu.substring(1);
		LocalDate date = LocalDate.now();
		int currentYear = date.getYear();
		year = Reader.readInt("What is the registration year of the " +make +" " +model +"?", currentYear-150, currentYear);
		System.out.println("What is mileage of the " +make +" " +model +"?");
		mileage = UserInput.validateInt();
		while (mileage <0) {
			System.out.println("the mileage can't be below 0, try again");
			mileage = UserInput.validateInt();
		}


		System.out.println("Is the " +make +" automatic or manual transmission? "
				+ "\nEnter A for automatic or M for Manual");
		String transmissionS = keyboard.next();


		//validate and selects correct enum.
		while (!transmissionS.equalsIgnoreCase("A")&&!transmissionS.equalsIgnoreCase("M")){
			System.out.println("Entry not recognised. Please enter A or M");
			transmissionS = keyboard.next();
		}

		if (transmissionS.equalsIgnoreCase("A")) {  //manual by default so no need for other statement 
			gearbox = Transmission.AUTOMATIC;
		}



		colour=Reader.readEnum("enter the colour of the vehicle",Colours.class);



		vin = VehicleSuperclass.setVin(vehicleList);


		//Switch to assign the variables to the selected vehicle type.
		//Puts the vehicle into the custom array (As opposed to the predefined one for NO answer)
		//object "name" references start at f as predefined ones below are a-e.
		switch (vehicleType) {
			case "h":  
			case "H":
				HatchBack f = new HatchBack(make, model, year,gearbox, colour, mileage, vin, satnav, parkingSensors, towBar, roofRack);
				vehicleList.add(f);
				break;
			case "e":  
			case "E":
				Estate g = new Estate (make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors, towBar, roofRack,thirdRowSeat);
				vehicleList.add(g);
				break;
			case "m":  
			case "M":
				Motorbike h = new Motorbike(make, model, year,gearbox,colour,mileage,vin,luggageBox);
				vehicleList.add(h);
				break;
			case "s":  
			case "S":
				Saloon i = new Saloon(make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors, towBar, roofRack);
				vehicleList.add(i);
				break;
			case "SUV":  
			case "suv":
			case "Suv":
				Suv j = new Suv (make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors, towBar, roofRack, allWheelDrive);
				vehicleList.add(j);
				break;
			default : System.out.println("error."); //should not be needed, but added for compliance.
		}


		System.out.println("\nCongratulations, you have sucessfully added the:" +make +" " +model +" to the database.\n");
		
		boolean answer = Reader.readBoolean("Would you like to add any extras to the vehicle now?");
		
		
		if (answer) {
			edit(vehicleList.get(vehicleList.size()-1));
		}
		
	}// end of add new vehicle section

	public static void display() {

		if (vehicleList.isEmpty()) {
			System.out.println("\n***Vehicle list is empty, please add a vehicle first!***\n");
		} else {

			boolean extraInfo = Reader.readBoolean("Would you like to see the optional extras?");
			sortOptions sortBy = Reader.readEnum("How would you like to sort the details list?", sortOptions.class);

			System.out.println("\nThere are " +vehicleList.size() +" Vehicles saved to the database, they will be displayed below\n\n");

			if (sortBy == sortOptions.NORMAL) {
				System.out.println("<<<---------------------------------<<<Standard Sort (Make, Model, Year)>>>---------------------------------->>>");
				System.out.println(String.format( "%-9s|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "id", "Vin", "Make", "Model", "Year","Colour","transmission","mileage"));
				System.out.println("----------------------------------------------------------------------------------------------------------------");

				Collections.sort(vehicleList);
				for (VehicleSuperclass i : vehicleList) { 
					System.out.print(String.format("%-9d", vehicleList.indexOf(i)+1));
					System.out.println(i.toString(extraInfo));
				}}

			if (sortBy == sortOptions.BY_YEAR) {
				System.out.println("\n<<<----------------------------------------------<<<by year>>>----------------------------------------------->>>");


				Comparator<VehicleSuperclass> sortByYear = new Comparator<VehicleSuperclass>() {
					public int compare(VehicleSuperclass e1, VehicleSuperclass e2) {
						if(e1.getYear() < e2.getYear()) {
							return -1;
						} else if (e1.getYear() > e2.getYear()) {
							return 1;
						} else {
							return 0;
						}
					}

				};

				System.out.println(String.format( "%-9s|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "ID", "Vin", "Make", "Model", "Year","Colour","transmission","mileage"));
				System.out.println("----------------------------------------------------------------------------------------------------------------");
				Collections.sort(vehicleList,sortByYear);
				for (VehicleSuperclass i : vehicleList) { 
					System.out.print(String.format("%-9d", vehicleList.indexOf(i)+1));
					System.out.println(i.toString(extraInfo));


				}



			}}




	}



	// Notice that this method is private. It is only used internally
	private static VehicleSuperclass search(String key) {
		Collection<VehicleSuperclass> results = CollectionUtils.search(key,vehicleList);
		if (results == null || results.isEmpty()) {
			return null;
		}
		return Reader.readObject("Please select a Vehicle from the list", results);
	}

	
	public static void edit() {
		
		String key = Reader.readLine("Enter a search key for the vehicle you want to edit...");
	System.out.println("the key is " +key);
	VehicleSuperclass vehicle = search(key);
	if (vehicle == null) {
		System.out.println("No Vehicle found!");
		return;
	}

	editHelper(vehicle);}
	
	
	
	public static void edit(VehicleSuperclass prefilled) {
		

		if (prefilled != null ) {
			
			editHelper(prefilled);
			
		} else {

		System.out.println("There's no vehicle to edit.");
		
		}

		
	}
		
		
		private static void editHelper(VehicleSuperclass vehicle){
			boolean edited = false;
			
			if (vehicle instanceof Car) {
				if (((Car) vehicle).hasTowBar()) {

					System.out.println("This vehicle already has a tow bar");

				} else {
					if (Reader.readBoolean("Do you want to add a towbar?")) 
						((Car) vehicle).addTowBar();
					edited = true;
				}
				if (((Car) vehicle).hasSatnav()) {

					System.out.println("This vehicle already has a Sat Nav");

				} else {
					if (Reader.readBoolean("Do you want to add Sat Nav?")) 
						((Car) vehicle).addSatnav();
					edited = true;
				}

				if (((Car) vehicle).hasParkingSensors()) {

					System.out.println("This vehicle already has parking sensors");

				} else {
					if (Reader.readBoolean("Do you want to add parking sensors?")) 
						((Car) vehicle).addParkingSensors();
					edited = true;
				}

				if (((Car) vehicle).hasRoofRack()) {

					System.out.println("This vehicle already has a roof rack");

				} else {
					if (Reader.readBoolean("Do you want to add roof rack?")) 
						((Car) vehicle).addRoofRack();
					edited = true;
				}
			} 
			
	 if (vehicle instanceof Suv) {
	if (((Suv) vehicle).hasAllWheelDrive()) {
	        		
	        		System.out.println("This vehicle already has all wheel drive");
	        		
	        	} else {
	        		if (Reader.readBoolean("Do you want to add all wheel drive?")) 
	           ((Suv) vehicle).addAllWheelDrive();
	            edited = true;
	        }
			}
		
			
				
			{ if (vehicle instanceof Estate) {
	if (((Estate) vehicle).hasThirdRowSeat()) {
	        		
	        		System.out.println("This vehicle already has third row seat");
	        		
	        	} else {
	        		if (Reader.readBoolean("Do you want to add a third row seat?")) 
	           ((Estate) vehicle).addThirdRowSeat();
	            edited = true;
	        }
			}
			
		}

			
			 if (vehicle instanceof Motorbike) {
				 if (((Motorbike) vehicle).hasLuggageBox()) {
				         		
				         		System.out.println("This vehicle already has a luggage box");
				         		if (Reader.readBoolean("\n Do you want to remove the luggage box?")) {
				         			 ((Motorbike) vehicle).removeLuggageBox();
				         		}
				         		
				         	} else {
				         		if (Reader.readBoolean("Do you want to add a luggage box?")) 
				            ((Motorbike) vehicle).addLuggageBox();
				             edited = true;
				         }
				 		}
			 if (Reader.readBoolean("Do you want to change the colour of the " +vehicle.fullName()+"?")) {
				 vehicle.setColour();
				 edited = true;
			
			 }
			 
			 if (Reader.readBoolean("Do you want to change the mileage of the " +vehicle.fullName()+"?")) {
				 
				 vehicle.setMileage();
				 edited = true;
			 }


			if (edited) {
				System.out.println("Person successfully updated!");
			} else {
				System.out.println("No detail was changed!");
			}
			
			
			
		}

	public static void remove() {

		String key = Reader.readLine("Enter a search key for the vehicle you want to remove");
		System.out.println("the key is " +key);
		VehicleSuperclass vehicle = search(key);
		if (vehicle == null) 
		{
			System.out.println("No Vehicle found!");
			return;}

		System.out.println(vehicle);
		if (Reader.readBoolean("Do you want to delete this vehicle?")) {
			vehicleList.remove(vehicle);
			System.out.println("Contact has been successfully deleted!");
		} else {
			System.out.println("Deletion aborted!");
		}


	}


}















