package vehicles;

public enum sortOptions {

		  NORMAL {
			  public String toString() {
				  return "Standard sort (By make, then model, then year)";
			  }
		  },
		  BY_YEAR {
			  public String toString() {
				  return "By year";
			  }
		  },
		  BY_MILEAGE{
			  public String toString() {
				  return "by Mileage";
			  }
		  }

}
