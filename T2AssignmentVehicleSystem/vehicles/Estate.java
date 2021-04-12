package vehicles;

public class Estate extends Car {
	
	private boolean thirdRowSeat;

	public Estate(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean satnav,
			boolean parkingSensors, boolean towBar, boolean roofRack, boolean thirdRowSeat) {
		super(make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors,
				towBar, roofRack);
		this.thirdRowSeat = thirdRowSeat;
	}
	

	
	public boolean hasThirdRowSeat() {
		return thirdRowSeat;
	}

	public void addThirdRowSeat() {
		if (this.thirdRowSeat) {
			System.out.println("\nCannot add. There's alrady a third row seat fitted.\n");
		} else {
			this.thirdRowSeat = true;
			System.out.println("\nThis vehicle now has a third row seat\n");
			}
	}
	

	@Override
	public String extrasList() {	
		return super.extrasList() +
		(this.thirdRowSeat ? ("[✅ third row seat] ") : ("[❎ No third row seat] "));
		
		}
		
		
		
		
	
	


}
