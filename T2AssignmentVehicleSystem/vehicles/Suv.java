package vehicles;

public class Suv extends Car {
	
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
	public String fullInfo() {		
	 super.fullInfo();
	 return ("Has all wheel drive") +this.hasAllWheelDrive();
		}
	
}

