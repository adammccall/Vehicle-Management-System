package vehicles;

public class Suv extends Car {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7422662824685490123L;
	private boolean allWheelDrive;

	public Suv(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean satnav,
			boolean parkingSensors, boolean towBar, boolean roofRack,boolean allWheelDrive) {
		super(make, model, year, gearbox, colour, mileage, vin, satnav,
				parkingSensors, towBar, roofRack);
		this.allWheelDrive = allWheelDrive;
	}
	
	
	
	public boolean hasAllWheelDrive() {
		return allWheelDrive;
	}

	public void addAllWheelDrive() {
		if (this.allWheelDrive) {
			System.out.println("\nCannot add. All wheel drive is already added.\n");
		} else {
			this.allWheelDrive = true;
			System.out.println("\nThis vehicle now has all wheel drived attached\n");
			}
	}

@Override	
	public String extrasList() {	
		return super.extrasList() +
		(this.allWheelDrive ? ("[✅ All wheel drive fitted] ") : ("[❎ No all wheel drive] "));
		
		
	}
	
}

