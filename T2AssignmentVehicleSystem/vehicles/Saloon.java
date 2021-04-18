package vehicles;

public class Saloon extends Car {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6448689997585342495L;

	public Saloon(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean satnav,
			boolean parkingSensors, boolean towBar, boolean roofRack) {
		super(make, model, year, gearbox, colour, mileage, vin, satnav,
				parkingSensors, towBar, roofRack);

	}
	



}
