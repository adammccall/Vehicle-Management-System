package vehicles;

public class Suv extends Car {
	
	private final boolean allWheelDrive;

	public Suv(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean satnav,
			boolean parkingSensors, boolean towBar, boolean roofRack,boolean allWheelDrive) {
		super(make, model, year, gearbox, colour, mileage, vin, satnav,
				parkingSensors, towBar, roofRack);
		this.allWheelDrive = allWheelDrive;
	}
	
	
	
	private boolean hasAllWheelDrive() {
		return allWheelDrive;
	}


@Override
	public void fullInfo() {		
		System.out.println("\nThe type of vehicle is an SUV.");
		super.fullInfo();
		System.out.println("\nThe estate has more optional accessories, these are listed below:"
				+ "\n4 wheel drive: " +hasAllWheelDrive());
		}
	
}

