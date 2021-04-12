package vehicles;

public abstract class Car extends VehicleSuperclass {

	private boolean satnav;
	private boolean parkingSensors;
	private boolean towBar;
	private boolean roofRack;
	
	protected Car(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean satnav, boolean parkingSensors,boolean towBar,boolean roofRack) {
		super(make, model, year, gearbox, colour, mileage, vin);
		this.satnav=satnav;
		this.parkingSensors = parkingSensors;
		this.towBar = towBar;
		this.roofRack = roofRack;
		
	}
	
	//helper methods
	public boolean hasSatnav() {
		return satnav;
	}
	
	public void addSatnav() {
		if (this.satnav) {
			System.out.println("/nCannot add. There is already a satnav added./n");
		} else {
			this.satnav = true;
			System.out.println("/nThe satnav has been attached./n");
			}
	}

	public boolean hasParkingSensors() {
		return parkingSensors;
	}

	public void addParkingSensors() {
		if (this.parkingSensors) {
			System.out.println("/nCannot add. There is already parking sensors added./n");
		} else {
			this.parkingSensors = true;
			System.out.println("/nThe parking sensors have been attached./n");
			}
	}
	
	public boolean hasTowBar() {
		return towBar;
	}
	
	public void addTowBar() {
		if (this.towBar) {
			System.out.println("\nCannot add. There is already a tow bar added.\n");
		} else {
			this.towBar = true;
			System.out.println("\nThe tow bar has been attached.\n");
			}
	}

	public boolean hasRoofRack() {
		return roofRack;
	}

	public void addRoofRack() {
		if (this.roofRack) {
			System.out.println("/nCannot add. There is already a satnav added./n");
		} else {
			this.roofRack = true;
			System.out.println("/nThe satnav has been attached./n");
			}
	}
	

	public String fullInfo() {	
		super.fullInfo();
		return ("This ") +this.getClass().getSimpleName() +(" has Satnav: ") +this.hasSatnav() +(" | Parking Sensors: ") +this.hasParkingSensors();
		
		
	}
	

}
