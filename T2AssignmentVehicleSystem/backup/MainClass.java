package vehicles;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import uod.gla.io.File;
import uod.gla.menu.Finalisable;
import uod.gla.menu.MenuBuilder;
import uod.gla.menu.MenuItem;
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
    	System.out.println("Welcome to Chi's Car Showroom");
        
        // retrieve data from disk at start up
        List<VehicleSuperclass> retrievedVehicleDatabase = vehicleManagementFile.<List<VehicleSuperclass>>retrieve(true);
        if (retrievedVehicleDatabase != null) {
        	vehicleList  = retrievedVehicleDatabase;
        }
        
        // Menu options
        MenuItem a = new MenuItem("A", "Add a new vehicle into the system", appObject, "addNew");
        MenuItem d = new MenuItem("D", "Display all Vehicles", appObject, "display");
        MenuItem e = new MenuItem("E", "Edit a person's details", appObject, "edit");
        MenuItem m = new MenuItem("M", "Marry two persons", appObject, "marry");
        MenuItem p = new MenuItem("P", "Procreate a new person", appObject, "procreate");
        MenuItem v = new MenuItem("V", "Divorce two persons", appObject, "divorce");
        MenuBuilder.displayMenu(appObject, a, d); //, e, m, p, v
        
        // save to disk at shutdown
        appObject.finalise();
        System.out.println("Thanks for using this app!");
        
        
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

	


				System.out.println("What type of vehicle would you like to add?\nE for estate\nH for hatchback\nM for motorbike\nS for saloon\nSUV for SUV.");
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


				System.out.println("Is the " +make +" automatic or manual transmission. Enter A or M");
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
				


				vin = UserInput.verifyVin(vehicleList);
	
    
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

  				
  				System.out.println(make +" " +model +" has been successfully added.\n");
    }// end of add new vehicle section
    
    public static void display() {
    	
    	if (vehicleList.isEmpty()) {
			System.out.println("\n***Vehicle list is empty, please add a vehicle first!***\n");
		} else {
    	
    	boolean extraInfo = Reader.readBoolean("Would you like to see the optional extras?");
    	sortOptions sortBy = Reader.readEnum("How would you like to sort the details list?", sortOptions.class);
    	
    	if (sortBy == sortOptions.NORMAL) {
    	System.out.println("<<<---------------------------------<<<Standard Sort (Make, Model, Year)>>>---------------------------------->>>");
		System.out.println(String.format( "%-9s|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "element", "Vin", "Make", "Model", "Year","Colour","transmission","mileage"));
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		
		Collections.sort(vehicleList);
		for (VehicleSuperclass i : vehicleList) { 
			System.out.print(String.format("%-9d", vehicleList.indexOf(i)));
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
    		
    		System.out.println(String.format( "%-9s|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "element", "Vin", "Make", "Model", "Year","Colour","transmission","mileage"));
    		System.out.println("----------------------------------------------------------------------------------------------------------------");
    		Collections.sort(vehicleList,sortByYear);
    		for (VehicleSuperclass i : vehicleList) { 
    			System.out.print(String.format("%-9d", vehicleList.indexOf(i)));
    			System.out.println(i.toString(extraInfo));
    			
    		
    		}
    		
    		
		
		}
		
	
			
    
	}
    }



 
    
}




    


    







