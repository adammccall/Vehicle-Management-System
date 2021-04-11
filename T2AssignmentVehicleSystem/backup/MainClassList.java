import java.util.Collections;

import uod.gla.util.Reader;

public static void x(String[] args) {
		
		


//Input needed for ALL Estate, Hatchback, SUV, Saloon cars
				if (vehicleType.equalsIgnoreCase("E") || vehicleType.equalsIgnoreCase("H") || vehicleType.equalsIgnoreCase("S") || vehicleType.equalsIgnoreCase("SUV"))
				{
				
					answer = UserInput.validateYesNo("Would you like to add any optional extras now?"); // made a method to do the validation of the Yes/No answer.

					if(answer) {
					System.out.println("Is the vehicle fitted with a SatvNav? (t/f or true/false)");
					satnav=UserInput.trueOrFalse();
					System.out.println("Is the vehicle fitted with Parking Sensors? (t/f or true/false)");
					parkingSensors=UserInput.trueOrFalse();
					System.out.println("Is the vehicle fitted with a tow bar? (t/f or true/false)");
					towBar=UserInput.trueOrFalse();
					System.out.println("Is the vehicle fitted with a roof rack? (t/f or true/false)");
					roofRack=UserInput.trueOrFalse();
					
					if (vehicleType.equalsIgnoreCase("E")) {
						System.out.println("Is the vehicle fitted with a third row seat? (t/f or true/false)");
						thirdRowSeat=UserInput.trueOrFalse();
					}

					if (vehicleType.equalsIgnoreCase("SUV")) {
						System.out.println("Is the vehicle fitted with all wheel drive? (t/f or true/false)");
						allWheelDrive=UserInput.trueOrFalse();
					}
					
					}
//Input needed for MOTORBIKE ONLY
				} else {	
					answer = UserInput.validateYesNo("Would you like to add any optional extras now?"); // made a method to do the validation of the Yes/No answer.

					if(answer) {
					System.out.println("Is the vehicle fitted with a luggage box? (t/f or true/false)");
					luggageBox=UserInput.trueOrFalse();
					}
				}
					

				



	//start of block for D (print details) input
				while (navAnswer.equalsIgnoreCase("d")){
			
					

	// asks user where they want to go next using method. 
	// (Add Vehicle, Change rack, change colour, change mileage or quit)
					navAnswer=UserInput.addVehicleAddRackChangeColMileOrQuit();


//start luggage rack block (R input)

  while (navAnswer.equalsIgnoreCase("r")) {
	  
	  int addRem =Reader.readInt("Choose and element",0,vehicleList.size()-1);
	 
	  
	 Motorbike a = (Motorbike) vehicleList.get(addRem);
	 a.addLuggageBox();
	  
	  
	  
	  
  } // end of keyboard input R (luggage rack)
 

					
/*
 * //start change mileage block (M input) while (answer.equalsIgnoreCase("M")) {
 * System.out.
 * println("What is the element number of the veichle that you want change the mileage of?"
 * ); //checks to see if element number is valid int arrAnswer =
 * UserInput.checkArrayMax(arrayNum);
 * 
 * /////////////////////////////////////////////////////////////////////////////
 * ///////////todo }
 */





				} // end of details (D input)



	// start quit block (input Q)

				if (navAnswer.equalsIgnoreCase("q")) {
					System.out.println("program exiting... ... ...");
					System.out.println("program has exited.");
					System.exit(0);
				}


			} // end of yes while block.





// all errors should be caught individually, but if not, this catch will fire.
		} catch (Exception a) {
			System.out.println("The program has crashed! The error code is: " +a); }



	}//main method end 

	@Override
	public void finalise() {
		// TODO Auto-generated method stub
		
	}





}// end of class

