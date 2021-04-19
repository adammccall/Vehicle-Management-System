package vehicles;

import java.io.Serializable;
import java.util.List;

import uod.gla.util.Reader;

/**
 * @author Adam
 *
 */
public abstract class VehicleSuperclass implements Comparable<VehicleSuperclass>, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7156778326812624144L;
	//most are final, as the fields are not changed in the superclass. 
	private final Make make;
	private final String model;
	private final int year;
	private final Transmission gearbox;
	private Colours colour; // not final, as this can change in this class
	private int mileage; // not final, as this can change in this class
	private final String vin;
	
	protected VehicleSuperclass(Make make, String model, int year, Transmission gearbox, 
			Colours colour, int mileage, String vin){
		
		this.make=make;
		this.model=model;
		this.year=year;
		this.gearbox=gearbox;
		this.colour=colour;
		this.mileage=mileage;
		this.vin=vin;
		
	}

	//set colour. Tone of voice is as if they have already selected a colour which is correct.
	public void setColour() {
		System.out.println("The current colour is " +this.getColour());
		 Colours newColour = Reader.readEnum("What colour is the vehicle now?", Colours.class);
		
		 boolean check = false;
		 if (this.getColour() == newColour) {
		 check = true;
		 System.out.println("This colour is the same as the previous one! Try again");
		check = Reader.readBoolean("Do you wish to add another colour?");
		}
			 
			 while (check && (this.getColour() == newColour)) {
			
			newColour = Reader.readEnum("What colour is the vehicle now?", Colours.class);
		 } 
		 this.colour = newColour;
	}

	public void setMileage() {
		System.out.print("the current mileage of the " +this.getModel());
		System.out.println(" is: " +this.getMileage());
		System.out.println("Enter the new milage:");
		int newMileage=UserInput.validateInt();
		//checks to see if the amount is greater than current levels
		while (newMileage <= this.getMileage()) {
			System.out.println("Sorry the mileage cannot be less or equal to the cuerrent milage (" +this.getMileage() +"), try again...");
			newMileage =UserInput.validateInt();	
		}
	
		this.mileage = newMileage;	
		System.out.println("The mileage has been updated. It is now: " +newMileage);
	}
	
	//setVin takes the answer, and makes it all upper case.
	public static String setVin(List<VehicleSuperclass> list, int year) {
		
		if (year < 1985) {
			String answer = Reader.readLine("Please enter the VIN, because this is pre-1984, this can between 11-17 charecters", 11, 17);
			String upperAnswer = answer.toUpperCase();
		//This cycles through all other VIN's in the list to check for a match. Tries 3 times.
		 
			for (VehicleSuperclass e : list) {
				if (e.getVin().equals(upperAnswer)) {
					
					//shows what the match is.
					System.out.println("There is a match in the System, it is a " +e.vehicleName() +"\nTry Again."); 
					answer = Reader.readLine("Please enter the VIN:", 11, 17);
					upperAnswer = answer.toUpperCase();

					for (VehicleSuperclass f : list) { 
						if (f.getVin().equals(upperAnswer)) { 
							System.out.println("There is a match in the System, it is a " +f.vehicleName());
							answer = Reader.readLine("Please enter the VIN:", 11, 17);
							upperAnswer = answer.toUpperCase();
						} 
					}
					for (VehicleSuperclass g : list) { 
						if (g.getVin().equals(upperAnswer)) { 
							System.out.println("There is a match in the System, it is a " +g.vehicleName()
							+"\nToo many attempts."); 
							throw new IllegalArgumentException("Too many attempts of the VIN but still a match!");
						} 
					}

				}


			}
				 return upperAnswer;
			
		}else {
		String answer = Reader.readLine("Please enter the VIN because this is post-1984, this needs to be 17 charecters:", 17);
		String upperAnswer = answer.toUpperCase();
	//This cycles through all other VIN's in the list to check for a match. Tries 3 times.
	 
		for (VehicleSuperclass e : list) {
			if (e.getVin().equals(upperAnswer)) {
				
				//shows what the match is.
				System.out.println("There is a match in the System, it is a " +e.vehicleName() +"\nTry Again."); 
				answer = Reader.readLine("Please enter the VIN:",17);
				upperAnswer = answer.toUpperCase();

				for (VehicleSuperclass f : list) { 
					if (f.getVin().equals(upperAnswer)) { 
						System.out.println("There is a match in the System, it is a " +f.vehicleName());
						answer = Reader.readLine("Please enter the VIN:", 17);
						upperAnswer = answer.toUpperCase();
					} 
				}
				for (VehicleSuperclass g : list) { 
					if (g.getVin().equals(upperAnswer)) { 
						System.out.println("There is a match in the System, it is a " +g.vehicleName()
						+"\nToo many attempts."); 
						throw new IllegalArgumentException("Too many attempts of the VIN but still a match!");
					} 
				}

			}


		}
			 return upperAnswer;
		}
		  
	  }

	
//Getters 	

private Make getMake() {
	return make;
}

public String getModel() {
	return model;
}

public String fullName() {
	return this.make +" " +getModel();
}

int getYear() {
	return year;
}

private Transmission getGearbox() {
	return gearbox;
}

public Colours getColour() {
	return colour;
}

public int getMileage() {
	return mileage;
}

public String getVin() {
	return vin;
}	
	

//extras list is used to show the optional extras in the toSring method that has a boolean argument.

  public String extrasList() { 
	  return ("This ") +this.fullName() +" is a "+this.getClass().getSimpleName()  +"\n↪↪↪ It has: ";
  }
 

public String vehicleName() {
	return getMake() +" " +getModel();
}

//standard toString
public String toString() {
	return String.format( "|%-18s|%-12s|%-20s|%-8d|%-13s|%-18s|%-5d", getVin(), getMake(), getModel(), getYear(), getColour(), getGearbox(), getMileage());

	
}

// overloaded toString requesting more info.
public String toString(boolean extra) {
	
return toString() + 
		(extra ? ("\n↪↪" +extrasList()) : (""));
}



@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((make == null) ? 0 : make.hashCode());
	result = prime * result + ((model == null) ? 0 : model.hashCode());
	result = prime * result + year;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	VehicleSuperclass other = (VehicleSuperclass) obj;
	if (make != other.make)
		return false;
	if (model == null) {
		if (other.model != null)
			return false;
	} else if (!model.equals(other.model))
		return false;
	if (year != other.year)
		return false;
	return true;
}

public int compareTo(VehicleSuperclass o) {
    if (!this.make.equals(o.make)) {
        return this.make.compareTo(o.make);
    } else if (!this.model.equals(o.model)) {
        return this.model.compareTo(o.model);
    } else {
        return this.year - o.year; 
    }
}



}


