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
  private static List < VehicleSuperclass > vehicleList = new ArrayList < >();

  // Where the serialised vehicle data will be stored
  private static File vehicleManagementFile = new File("savedData", "vehicleDatabase");

  // The object on which the name of the method to be invoked is searched for
  // Also used to provide access to the non-static methods e.g. finalise
  private static MainClass appObject = new MainClass();

  static int ATTEMPTS = 3; //set the amount of attempts before an error.

  public static void main(String[] args) {
//tell the reader utility how many attempts can be done before abandoning 
    Reader.setAttempts(ATTEMPTS);
    


    // Welcome message
    System.out.println("Welcome to Chi's Car Showroom \n\"Where All Your Vehicular Dreams Come True\"™ \n© Chi 2021 all rights reserved.");
    MenuBuilder.setDefaultPrompt("Choose an option from the list...");

    // retrieve data from disk at start up
    List < VehicleSuperclass > retrievedVehicleDatabase = vehicleManagementFile. < List < VehicleSuperclass >> retrieve(true);
    

//quick test to display how many vehices are saved
    System.out.println("\n-----------------System Status----------------");
    System.out.println("The Database size saved to disk has: " + retrievedVehicleDatabase.size() + " vehicles.");
//if no vehicles are found, report this and show a redcuced menu.
    if (retrievedVehicleDatabase.isEmpty()) {
      System.out.println("No vheicles have been loaded into memory. Please create one.");
      System.out.println("----------------Checks complete---------------");

      // Menu options only with "Add" options
      MenuItem a = new MenuItem("A", "Add the first vehicle into the system", appObject, "addNew");
      MenuItem z = new MenuItem("Z", "Add demo vehicles", appObject, "demoVehicles");
      MenuBuilder.displayMenuOnce(a, z);
      
   // Menu options that will be displayed after the previous one has complete 
      MenuItem m = new MenuItem("A", "Add a new vehicle into the system", appObject, "addNew");
      MenuItem d = new MenuItem("D", "Display all Vehicles, and see all thier details", appObject, "display");
      MenuItem s = new MenuItem("S", "Search a vehicle, and edit its details", appObject, "edit");
      MenuItem v = new MenuItem("V", "Search for, and display a single vehicle's full details", appObject, "displaySingle");
      MenuItem r = new MenuItem("RM", "Remove a vehicle", appObject, "remove");
      MenuBuilder.displayMenu(appObject, m, d, s, v, r); //, e, m, p, v

      // save to disk at shutdown
      appObject.finalise();
      System.out.println("Thanks for using this app, and remember, sales = success!");

      
      //if > 1 vehicle in system
    } else {
      vehicleList = retrievedVehicleDatabase;
      System.out.println("The program has successfully loaded: " + vehicleList.size() + " vehicles into memory.");
      System.out.println("----------------Checks complete---------------");

      // Menu options
      MenuItem a = new MenuItem("A", "Add a new vehicle into the system", appObject, "addNew");
      MenuItem d = new MenuItem("D", "Display all Vehicles, and see all thier details", appObject, "display");
      MenuItem v = new MenuItem("V", "Search for, and display a single vehicle's full details", appObject, "displaySingle");
      MenuItem s = new MenuItem("S", "Search a vehicle, and edit its details", appObject, "edit");
      MenuItem r = new MenuItem("RM", "Remove a vehicle", appObject, "remove");
      MenuBuilder.displayMenu(appObject, a, d,v, s, r); 
      // save to disk at shutdown
      appObject.finalise();
      System.out.println("Thanks for using this app, and remember, sales = success!");
    }

  }

  @Override public void finalise() {
    vehicleManagementFile.save((Serializable) vehicleList);
  }

  
  //sub menu generated to add vehicles by type.
  public static void addNew() {

    System.out.println("What type of vehicle would you like to add?" + "\nSelect a vehicle type from the list below:");

    // Vehicle type menu options
    MenuItem a = new MenuItem("E", "estate", appObject, "estate");
    MenuItem d = new MenuItem("H", "hatchback", appObject, "hatchback");
    MenuItem s = new MenuItem("M", "motorbike", appObject, "motorbike");
    MenuItem r = new MenuItem("S", "saloon", appObject, "saloon");
    MenuItem su = new MenuItem("SUV", "SUV", appObject, "suv");
    MenuBuilder.displayMenuOnce(a, d, s, r, su); //, e, m, p, v

  } 

  // calls the vehicle builder method and tells it what vehicle types have been added
  public static void estate() {
    vehicleBuilder("e");

  }

  public static void hatchback() {
    vehicleBuilder("h");

  }
  public static void motorbike() {
    vehicleBuilder("m");

  }
  public static void saloon() {
    vehicleBuilder("h");

  }
  public static void suv() {
    vehicleBuilder("suv");

  }
// gets the information about the vehicle to be added
  private static void vehicleBuilder(String vehicleType) {

    Scanner keyboard = new Scanner(System. in );

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

    //Input needed for ALL VEHICLES
    make = Reader.readEnum("enter the manufacturer of the vehicle", Make.class);
    model = toTitleCase(Reader.readLine("What is the model of the " + make + "?", 1, 20));
    LocalDate date = LocalDate.now();
    int currentYear = date.getYear();
    year = Reader.readInt("What is the registration year of the " + make + " " + model + "?", currentYear - 150, currentYear);
    System.out.println("What is mileage of the " + make + " " + model + "?");
    mileage = UserInput.validateInt();
    while (mileage < 0) {
      System.out.println("the mileage can't be below 0, try again");
      mileage = UserInput.validateInt();
    }

    System.out.println("Is the " + make + " automatic or manual transmission? " + "\nEnter A for automatic or M for Manual");
    String transmissionS = keyboard.next();

    //validate and selects correct enum.
    while (!transmissionS.equalsIgnoreCase("A") && !transmissionS.equalsIgnoreCase("M")) {
      System.out.println("Entry not recognised. Please enter A or M");
      transmissionS = keyboard.next();
    }

    if (transmissionS.equalsIgnoreCase("A")) { //manual by default so no need for other statement 
      gearbox = Transmission.AUTOMATIC;
    }

    colour = Reader.readEnum("enter the colour of the vehicle", Colours.class);

    vin = VehicleSuperclass.setVin(vehicleList, year);

    //Switch to assign the variables to the selected vehicle type.
    //Puts the vehicle into the vehicleList
 
    switch (vehicleType) {
    case "h":
      HatchBack f = new HatchBack(make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors, towBar, roofRack);
      vehicleList.add(f);
      break;
    case "e":
      Estate g = new Estate(make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors, towBar, roofRack, thirdRowSeat);
      vehicleList.add(g);
      break;
    case "m":
      Motorbike h = new Motorbike(make, model, year, gearbox, colour, mileage, vin, luggageBox);
      vehicleList.add(h);
      break;
    case "s":
      Saloon i = new Saloon(make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors, towBar, roofRack);
      vehicleList.add(i);
      break;
    case "suv":

      Suv j = new Suv(make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors, towBar, roofRack, allWheelDrive);
      vehicleList.add(j);
      break;
    default:
      System.out.println("error."); //should not be needed, but added for compliance.
    }

    System.out.println("\nCongratulations, you have sucessfully added the: " + make + " " + model + " to the database.\n");

    boolean answer = Reader.readBoolean("Would you like to add any extras to the vehicle now?");

    //if true, go to the edit method to add/removed options for selected vehicle
    if (answer) {
      edit(vehicleList.get(vehicleList.size() - 1));
    }
    
    //keyboard.close();
  }

  
  //displays all vehicles without searching
  public static void display() {

	  //alert the user if vehicle list is empty. Only would be needed if all vehicles were removed.
    if (vehicleList.isEmpty()) {
      System.out.println("\n***Vehicle list is empty, please add a vehicle first!***\n");
    } else {

    	//gives user the option to see the extras or just display the vehicles
      boolean extraInfo = Reader.readBoolean("Would you like to see the optional extras?");
      
      //give the user option to sort the list in different ways
      sortOptions sortBy = Reader.readEnum("How would you like to sort the details list?", sortOptions.class);

      //displays how many vehicles are in the database.
      System.out.println("\nThere are " + vehicleList.size() + " Vehicles saved to the database, they will be displayed below\n\n");

      //normal sort
      if (sortBy == sortOptions.NORMAL) {
        System.out.println("<<<---------------------------------<<<Standard Sort (Make, Model, Year)>>>---------------------------------->>>");
        System.out.println(String.format("%-9s|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "id", "Vin", "Make", "Model", "Year", "Colour", "transmission", "mileage"));
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        Collections.sort(vehicleList);
        for (VehicleSuperclass i: vehicleList) {
          System.out.print(String.format("%-9d", vehicleList.indexOf(i) + 1));
          System.out.println(i.toString(extraInfo));
        }
      }

      //sort by year
      if (sortBy == sortOptions.BY_YEAR) {
        System.out.println("\n<<<----------------------------------------------<<<by year>>>----------------------------------------------->>>");

        //sort edit
        Comparator < VehicleSuperclass > sortByYear = new Comparator < VehicleSuperclass > () {
          public int compare(VehicleSuperclass e1, VehicleSuperclass e2) {
            if (e1.getYear() < e2.getYear()) {
              return - 1;
            } else if (e1.getYear() > e2.getYear()) {
              return 1;
            } else {
              return 0;
            }
          }

        };

        System.out.println(String.format("%-9s|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "ID", "Vin", "Make", "Model", "Year", "Colour", "transmission", "mileage"));
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        Collections.sort(vehicleList, sortByYear);
        for (VehicleSuperclass i: vehicleList) {
          System.out.print(String.format("%-9d", vehicleList.indexOf(i) + 1));
          System.out.println(i.toString(extraInfo));

        }
      }
//sort by mileage
      if (sortBy == sortOptions.BY_MILEAGE) {
        System.out.println("\n<<<----------------------------------------------<<<by Mileage>>>----------------------------------------------->>>");

        
        // sort edit
        Comparator < VehicleSuperclass > sortByMileage = new Comparator < VehicleSuperclass > () {
          public int compare(VehicleSuperclass e1, VehicleSuperclass e2) {
            if (e1.getMileage() < e2.getMileage()) {
              return - 1;
            } else if (e1.getMileage() > e2.getMileage()) {
              return 1;
            } else {
              return 0;
            }
          }

        };

        System.out.println(String.format("%-9s|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "ID", "Vin", "Make", "Model", "Year", "Colour", "transmission", "mileage"));
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        Collections.sort(vehicleList, sortByMileage);
        for (VehicleSuperclass i: vehicleList) {
          System.out.print(String.format("%-9d", vehicleList.indexOf(i) + 1));
          System.out.println(i.toString(extraInfo));

        }
      }

    }

  }

// private method to search. USed in multiple methods internally.
  private static VehicleSuperclass search(String key) {
    Collection < VehicleSuperclass > results = CollectionUtils.search(key, vehicleList);
    if (results == null || results.isEmpty()) {
      return null;
    }
    return readObject("Please select a Vehicle from the list", results);
  }
  
  //method to search and display only one vehicle.
  public static void displaySingle() {

	    String key = Reader.readLine("Enter a search key for the vehicle you want to display the details for");
	    VehicleSuperclass vehicle = search(key);
	    if (vehicle == null) {
	      System.out.println("No Vehicle found!");
	      return;
	    }
	    System.out.println("\n<<<---------------------------------------<<<" +vehicle.fullName() +">>>---------------------------------------->>>");
	    System.out.println(String.format("|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "Vin", "Make", "Model", "Year", "Colour", "transmission", "mileage"));
	    System.out.println(vehicle.toString(true));
	  }

  
  // method to edit a vehicle. This one is called by the menu builder only
  public static void edit() {

    String key = Reader.readLine("Enter a search key for the vehicle you want to edit...");
    VehicleSuperclass vehicle = search(key);
    if (vehicle == null) {
      System.out.println("No Vehicle found!");
      return;
    }

    editHelper(vehicle);
  }

  //this method is called from the add method.
  public static void edit(VehicleSuperclass prefilled) {

    if (prefilled != null) {

      editHelper(prefilled);

    } else {

      System.out.println("Error! There's no vehicle to edit.");

    }

  }
  
  
  //private as it is only called upon by the edit classes.
  private static void editHelper(VehicleSuperclass vehicle) {
    boolean edited = false;

    if (vehicle instanceof Car) {
      if (((Car) vehicle).hasTowBar()) {

        System.out.println("This vehicle already has a tow bar");

      } else {
        if (Reader.readBoolean("Do you want to add a towbar?"))((Car) vehicle).addTowBar();
        edited = true;
      }
      if (((Car) vehicle).hasSatnav()) {

        System.out.println("This vehicle already has a Sat Nav");

      } else {
        if (Reader.readBoolean("Do you want to add Sat Nav?"))((Car) vehicle).addSatnav();
        edited = true;
      }

      if (((Car) vehicle).hasParkingSensors()) {

        System.out.println("This vehicle already has parking sensors");

      } else {
        if (Reader.readBoolean("Do you want to add parking sensors?"))((Car) vehicle).addParkingSensors();
        edited = true;
      }

      if (((Car) vehicle).hasRoofRack()) {

        System.out.println("This vehicle already has a roof rack");

      } else {
        if (Reader.readBoolean("Do you want to add roof rack?"))((Car) vehicle).addRoofRack();
        edited = true;
      }
    }

    if (vehicle instanceof Suv) {
      if (((Suv) vehicle).hasAllWheelDrive()) {

        System.out.println("This vehicle already has all wheel drive");

      } else {
        if (Reader.readBoolean("Do you want to add all wheel drive?"))((Suv) vehicle).addAllWheelDrive();
        edited = true;
      }
    }

    {
      if (vehicle instanceof Estate) {
        if (((Estate) vehicle).hasThirdRowSeat()) {

          System.out.println("This vehicle already has third row seat");

        } else {
          if (Reader.readBoolean("Do you want to add a third row seat?"))((Estate) vehicle).addThirdRowSeat();
          edited = true;
        }
      }

    }

    if (vehicle instanceof Motorbike) {
      if (((Motorbike) vehicle).hasLuggageBox()) {

        System.out.println("This vehicle already has a luggage box");
        if (Reader.readBoolean("\n Do you want to remove the luggage box?")) { ((Motorbike) vehicle).removeLuggageBox();
        }

      } else {
        if (Reader.readBoolean("Do you want to add a luggage box?"))((Motorbike) vehicle).addLuggageBox();
        edited = true;
      }
    }
    if (Reader.readBoolean("Do you want to change the colour of the " + vehicle.fullName() + "?")) {
      vehicle.setColour();
      edited = true;

    }

    if (Reader.readBoolean("Do you want to change the mileage of the " + vehicle.fullName() + "?")) {

      vehicle.setMileage();
      edited = true;
    }

    if (edited) {
      System.out.println("vehicle successfully updated!");
    } else {
      System.out.println("No updates were made!");
    }

  }

  public static void remove() {
	  
	  removeOptions answer = Reader.readEnum("What would you like to do?", MainClass.removeOptions.class);

	  //this is just for Chi to quickly clear the loaded vehicles from memory.
	  if (answer == removeOptions.DELETE_ALL) {
		  String pw = Reader.readLine("enter the password");
		  if (pw.equals("chi")) {
			  boolean confirm = Reader.readBoolean("Are you sure to delete everything??");
			  if (confirm) {
				  vehicleList.clear();
				  System.out.println("Vehicle list cleared. it now has " +vehicleList.size() +" object's stored.");
				  System.out.println("Please exit the program and reload if you'd like to use the demo objects");
			  }
			  
		  }else {
			  throw new IllegalArgumentException("Wrong Password!");
		  }
		  
	  }else {
    String key = Reader.readLine("Enter a search key for the vehicle you want to remove");
    VehicleSuperclass vehicle = search(key);
    if (vehicle == null) {
      System.out.println("No Vehicle found!");
      return;
    }

    if (Reader.readBoolean("Do you want to delete this vehicle?")) {
      vehicleList.remove(vehicle);
      System.out.println("Contact has been successfully deleted!");
    } else {
      System.out.println("No change was made and no deletions have occured.");
    }

  }}

  public enum removeOptions {

	  DELETE_ALL{
		  public String toString() {
			  return "Delete all of the vehicle records?";
		  }
	  },
	  REMOVE_SINGLE {
		  public String toString() {
			  return "Remove a single vehicle";
		  }}}
  
  
  /*
   *The below code uses the readObject method from the Utility API so that it can display
   * the header, and the returned list looks nice.
   */

  public static < T > T readObject(String prompt, Collection < T > objects)
  throws IllegalArgumentException {
    if (objects == null || objects.isEmpty()) {
      throw new IllegalArgumentException("Collection is null or empty!");
    } else if (objects.size() == 1) {
      return new ArrayList < >(objects).get(0);
    }
    boolean ceaseLoop = false;
    T selection = null;
    List < T > list = new ArrayList < >(objects);
    while (!ceaseLoop) {
      System.out.println(prompt == null ? "Please select an object": prompt);
      System.out.println("<<<---------------------------------<<<Standard Sort (Make, Model, Year)>>>---------------------------------->>>");
      System.out.println(String.format("%-9s|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "id", "Vin", "Make", "Model", "Year", "Colour", "transmission", "mileage"));
      System.out.println("----------------------------------------------------------------------------------------------------------------");
      int count = 0;
      for (T object: list) {
        System.out.println(++count + ":\t " + object.toString().replace("\n", "\n\t"));
      }
      int objectIndex = Reader.readInt("Enter the option number", 1, list.size());
      selection = list.get(objectIndex - 1);
      System.out.println("You have selected...\n");
      System.out.println(String.format("|%-18s|%-12s|%-20s|%-8s|%-13s|%-18s|%-5s", "Vin", "Make", "Model", "Year", "Colour", "transmission", "mileage"));
      System.out.println("--------------------------------------------------------------------------------------------------------");
      System.out.println(selection);
      ceaseLoop = Reader.readBoolean("Is that correct? (Y/N)");
    }
    return selection;
  }
  
  // Changes lowercase text to capitalise the first letter. Thanks to Vilpe89 for this code 
  public static String toTitleCase(String givenString) {
	    String[] arr = givenString.split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }          
	    return sb.toString().trim();
	} 

  
  //method to load in 10x demo vehicle's into the system
  public static void demoVehicles() {

    HatchBack a = new HatchBack(Make.AUDI, "TT", 1992, Transmission.MANUAL, Colours.ORANGE, 23, "K5HMK4U5D4RYB", false, false, true, false);
    vehicleList.add(a);
    Estate b = new Estate(Make.VOLKSWAGEN, "Estate", 2011, Transmission.MANUAL, Colours.PURPLE, 4510, "4WHQ83T5T3UL", true, false, false, true, false);
    vehicleList.add(b);
    Motorbike c = new Motorbike(Make.BMW, "F 900", 2018, Transmission.MANUAL, Colours.RUSTY, 95601, "GYM5BD8ANBE7H", true);
    vehicleList.add(c);
    Saloon d = new Saloon(Make.HONDA, "Civic", 2011, Transmission.AUTOMATIC, Colours.RED, 62, "LM9B8R4Z4XE7V", true, true, false, false);
    vehicleList.add(d);
    Suv e = new Suv(Make.LANDROVER, "SUV", 2000, Transmission.MANUAL, Colours.WHITE, 1, "VB5LG8CGUTASY", true, true, false, false, true);
    vehicleList.add(e);
    HatchBack f = new HatchBack(Make.ALFAROMEO, "Nice Car", 2020, Transmission.AUTOMATIC, Colours.BLUE, 23, "CXBV2UN67WZPM", false, false, true, false);
    vehicleList.add(f);
    Estate g = new Estate(Make.FORD, "Safari", 1999, Transmission.MANUAL, Colours.PURPLE, 4510, "4KCXPEQGMU66G", true, false, false, true, false);
    vehicleList.add(g);
    Motorbike h = new Motorbike(Make.HYUNDAI, "I3", 1985, Transmission.AUTOMATIC, Colours.RUSTY, 95601, "H5UG78HZHKF9S", true);
    vehicleList.add(h);
    Saloon i = new Saloon(Make.VOLVO, "Oldy", 2000, Transmission.AUTOMATIC, Colours.RED, 62, "2YCV45B5TWVQU", true, true, false, false);
    vehicleList.add(i);
    Suv j = new Suv(Make.JAGUAR, "Cat", 2005, Transmission.MANUAL, Colours.ORANGE, 1, "GJLSD2X8C4Q64", true, true, false, false, true);
    vehicleList.add(j);
    
    System.out.println("10x demo vehicles have been added to the system");

  }
  


}

