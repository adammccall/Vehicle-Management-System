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
			System.out.println("/nCannot add. There is already a luggage box attached/n");
		} else {
			luggageBox = true;
			System.out.println("/nThe luggage box has been attached./n");
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
	public void fullInfo() {		
		System.out.println("\nThe type of vehicle is a Motorbike.");
		super.fullInfo();
		if (luggageBox) {
			System.out.println("There is a luggage box attached to this " +getModel() +".");
		} else {
			System.out.println("There is no luggage box attached to this " +getModel() +".");
			}
	}
	public String extraInfo() {		
		return ("test") +hasLuggageBox();
	
	}
}
	

