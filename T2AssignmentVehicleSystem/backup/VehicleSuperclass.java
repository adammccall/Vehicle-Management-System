package vehicles;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author Adam
 *
 */
public abstract class VehicleSuperclass implements Comparable<VehicleSuperclass>, Serializable {

	
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

	public void setColour(Colours colour) {
		this.colour = colour;
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

	
//Getters 	

private Make getMake() {
	return make;
}

public String getModel() {
	return model;
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



	
	

  public String fullInfo() { 
	  return ("");
  }
 

public String vehicleName() {
	return getMake() +" " +getModel();
}

public String toString(boolean extra) {
	
return String.format( "|%-18s|%-12s|%-20s|%-8d|%-13s|%-18s|%-5d", getVin(), getMake(), getModel(), getYear(), getColour(), getGearbox(), getMileage()) + 
		(extra ? ("\n↪↪" +extrasList()) : (""));
}

private String extrasList() {
	if (this instanceof Estate) {
		return fullInfo();
//		}
//	if (this instanceof Motorbike) {
//		return Motorbike.extraInfo();
	}else
	return "";	
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


