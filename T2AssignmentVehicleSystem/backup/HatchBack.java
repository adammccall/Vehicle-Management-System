package vehicles;

public class HatchBack extends Car {

	public HatchBack(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean satnav,
			boolean parkingSensors, boolean towBar, boolean roofRack) {
		super(make, model, year, gearbox, colour, mileage, vin, satnav,
					parkingSensors, towBar, roofRack);	
	}
	
	@Override
	public void fullInfo() {		
		System.out.println("\nThe type of vehicle is a Hatchback.");
		super.fullInfo();}
		
}