package vehicles;

public class HatchBack extends Car {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5821665642283210796L;

	public HatchBack(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean satnav,
			boolean parkingSensors, boolean towBar, boolean roofRack) {
		super(make, model, year, gearbox, colour, mileage, vin, satnav,
					parkingSensors, towBar, roofRack);	
	}
	

		
}