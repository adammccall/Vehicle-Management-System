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
		  BY_COLOUR{
			  public String toString() {
				  return "by colour";
			  }
		  }

}
