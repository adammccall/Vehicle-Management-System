package vehicles;

public class Estate extends Car {
	
	private final boolean thirdRowSeat;

	public Estate(Make make, String model, int year, Transmission gearbox,
			Colours colour, int mileage, String vin, boolean satnav,
			boolean parkingSensors, boolean towBar, boolean roofRack, boolean thirdRowSeat) {
		super(make, model, year, gearbox, colour, mileage, vin, satnav, parkingSensors,
				towBar, roofRack);
		this.thirdRowSeat = thirdRowSeat;
	}
	

	
	private boolean hasThirdRowSeat() {
		return thirdRowSeat;
	}

	
	

	@Override
	public String fullInfo() {		
		return super.fullInfo() +(" | Third Row Seat: ") +this.hasThirdRowSeat();
		
		
		
		
	}
	


}
