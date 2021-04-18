package vehicles;

import java.util.Scanner;

public final class UserInput {
	
	
	//Repeated strings as variables for reuse:
	private static final String ADDNEW = "Would you like to enter a new vehicle? Enter Y";
	private static final String TRYAGAIN = "You didn't enter the correct input, try again...";
	
	//Make UserInput private so it can't be used as a constructor.
	 private UserInput() {
		 //no need for code, as it can't be called in MainClass
		  }
	
	
private static Scanner keyboard = new Scanner(System.in);	
	
//Allow users to type in Y, N, Yes, No, and validate wrong answers.
	
	public static boolean validateYesNo(String question) {
		
		System.out.println(question);
		String answer = keyboard.next();
		while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("N") && !answer.equalsIgnoreCase("No")) { 
			System.out.println(TRYAGAIN);
			System.out.println(question);
			answer = keyboard.next();	
	}
		if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
		return false;
		}else {
			return true;
					}
		
	}
			
			
	//true or false helper... Allow for T,f,true,false input, and convert these to boolean.
		public static boolean trueOrFalse() {
			String b = keyboard.next();
			while (!b.equalsIgnoreCase("t")&&!b.equalsIgnoreCase("f")&&!b.equalsIgnoreCase("true")&&!b.equalsIgnoreCase("false")) {
				System.out.println("must be T or F, or \"true or false\", try again...");
				b = keyboard.next();
			}


			if (b.equalsIgnoreCase("f")||b.equalsIgnoreCase("false")) {
				return false;
			}

			else {
				return true;}



			

		}
		// inspiration for code to act if not an it provided by:
		//https://coderanch.com/t/593933/java/recovering-InputMismatchException-Scanner-class
		
		// number validator
		public static int validateInt() {
			boolean numberOnly = true;
			int answer = 0;
			
			while (numberOnly) {
				try {
					answer = keyboard.nextInt();
					numberOnly = false;	
					
				}catch(java.util.InputMismatchException ex){
					System.out.println("Sorry, you didn't enter a number. Try again!!");
					System.out.println("Enter a number only...");
					keyboard.nextLine();
					}
				
				
				
			} return answer;
			
		}
		
//Validate input is lower or equal to the current number of elements.
		
		public static int checkArrayMax(int arrayNum) {
			int arrAnswer = keyboard.nextInt();
			while (arrAnswer > arrayNum) {
				System.out.println("Sorry, the element number is too high, try again ");
				arrAnswer = keyboard.nextInt();
		}
			return arrAnswer;
		}
	
		
		public static String addVehicleAddRackChangeColMileOrQuit() {
		System.out.println("Would you like to add another vehicle, add/remove a luggage rack, Change colour or milage, or quit the program?");
		System.out.println("Type Y for new vehicle, R to add/remove a luggage rack, M to change mileage");
		System.out.println("Type C to change vehicle colour, or Q to quit the program");
		
		String answer = keyboard.next();
		while (!answer.equalsIgnoreCase("Y")&&!answer.equalsIgnoreCase("Q")&&!answer.equalsIgnoreCase("R")
				&&!answer.equalsIgnoreCase("M")&&!answer.equalsIgnoreCase("C")) { 
			System.out.println(TRYAGAIN);
			System.out.println(ADDNEW);
			System.out.println("Would you like to add or remove a luggage rack? Enter R");
			System.out.println("To change the colour enter C");
			System.out.println("To change the vehicle milage press M");
			System.out.println("If you want to exit the program, enter Q");
			answer = keyboard.next();
		}
		return answer;
		}
		
		
		public static String addVehicleSeeDetailsOrQuit() {
			System.out.println("\nWould you like to add another vehicle, or print vehicle data to the console?");
			System.out.println("Type Y for new vehicle, or D for show vehicle details (you will also be able to update vehicle details.)");
			System.out.println("You can also enter Q to quit");
			String answer = keyboard.next();
			while (!answer.equalsIgnoreCase("Y")&&!answer.equalsIgnoreCase("D")&&!answer.equalsIgnoreCase("Q")) { 
				System.out.println(TRYAGAIN);
				System.out.println(ADDNEW);
				System.out.println("If you want to see the vehicle details, enter D");
				System.out.println("To exit type Q");
				answer = keyboard.next();
			}
			return answer;
		}
		

		
		
		  public static String noRackOptions() {
		  System.out.println("Cannot remove luggage box as there is no luggage box attached."); 
		  System.out.println("You can add a luggage box to this motorbike by entering A");
		  System.out.println("Would you like to add another vehicle, or print vehicle data to the console?"); 
		  System.out.println("Type Y for new vehicle, or D for show vehicle details (you will also be able to update vehicle details.)"); 
		  System.out.println("You can also enter Q to quit"); String answer = keyboard.next(); while
		  (!answer.equalsIgnoreCase("Y")&&!answer.equalsIgnoreCase("D")&&!
		  answer.equalsIgnoreCase("Q")&&!answer.equalsIgnoreCase("A")) {
		  System.out.println(TRYAGAIN); System.out.println(ADDNEW);
		  System.out.println("If you want to see the vehicle details, enter D"
		  ); answer = keyboard.next(); } return answer; }
		 
		
		  

}




