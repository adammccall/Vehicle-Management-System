package vehicles;

// data from: https://gist.github.com/pcgeek86/78f4cad29dd16961ceeeee654127a0db

public enum Make {
	  ALFAROMEO {
		  public String toString() {
			  return "Alfa Romeo";
		  }
	  },
	  AUDI {
		  public String toString() {
			  return "Audi";
		  }
	  },
	  BMW{
		  public String toString() {
			  return "B.M.W.";
		  }
	  },
	  CITROEN
	  {
		  public String toString() {
			  return "Citroen";
		  }
	  },
	  DACIA{
		  public String toString() {
			  return "Dacia";
		  }
	  },
	  FIAT{
		  public String toString() {
			  return "Fiat";
		  }
	  },
	  FORD{
		  public String toString() {
			  return "Ford";
		  }
	  },
	  HONDA{
		  public String toString() {
			  return "Honda";
		  }
	  },
	  HYUNDAI{
		  public String toString() {
			  return "Hyundai";
		  }
	  },
	  JAGUAR{
		  public String toString() {
			  return "Jaguar";
		  }
	  },
	  JEEP{
		  public String toString() {
			  return "Jeep";
		  }
	  },
	  LANDROVER{
		  public String toString() {
			  return "Land Rover";
		  }
	  },
	  LEXUS{
		  public String toString() {
			  return "Lexus";
		  }
	  },
	  MG{
		  public String toString() {
			  return "M.G.";
		  }
	  },
	  NISSAN{
		  public String toString() {
			  return "Nissan";
		  }
	  },
	  PEUGEOT{
		  public String toString() {
			  return "Peugeot";
		  }
	  },
	  RENAULT{
		  public String toString() {
			  return "Renault";
		  }
	  },
	  ROVER{
		  public String toString() {
			  return "Rover";
		  }
	  },
	  SEAT{
		  public String toString() {
			  return "Seat";
		  }
	  },
	  SUBARU{
		  public String toString() {
			  return "Subaru";
		  }
	  },
	  TESLA{
		  public String toString() {
			  return "Tesla";
		  }
	  },
	  TOYOTA{
		  public String toString() {
			  return "Toyota";
		  }
	  },
	  VOLKSWAGEN{
		  public String toString() {
			  return "Volkswagen";
		  }
	  },
	  VOLVO{
		  public String toString() {
			  return "Volvo";
		  }
	  };
	  
	  


public void listColours () {
	for (Make i : Make.values() ) {
		
		System.out.println(i);
		
	
		
	}
	  
	  
}}



