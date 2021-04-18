package vehicles;

public class Motorbike extends VehicleSuperclass {
	
	private boolean luggageBox;

	public Motorbike(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean luggageBox) {
		super(make, model, year, gearbox, colour, mileage, vin);
		this.luggageBox =luggageBox;

	}
	
	
	public boolean hasLuggageBox() {
		return luggageBox; 
	}
	
	public void addLuggageBox() {
		if (luggageBox) {
			System.out.println("\nCannot add. There is already a luggage box attached\n");
		} else {
			luggageBox = true;
			System.out.println("\nThe luggage box has been attached.\n");
			}
	}
	
	public void removeLuggageBox() {
		if (luggageBox) {
			luggageBox = false;
			System.out.println(" The luggage box has been removed.");
		} else {
			System.out.println(" Cannot Remove. There is no luggage box attached.");
			}
		}
	
	
	

	@Override
	public String extrasList() {		
		return super.extrasList() +
		
	(this.luggageBox ? ("[✅ luggage box fitted.] ") : ("[❎ no luggage box fitted.]"));		
			
	}
	
}
	

