package program;

import java.text.DecimalFormat;

/**
 * 
 * @author 
 *
 */
public class Store {
	String name;
	double capital;
	
	public Store() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param Takes a double and sets it as the stores capital
	 */
	public void setCapital(double capital) {
		this.capital = capital;
	}
	
	/**
	 * @
	 * @param Takes a string and sets it as the stores name
	 * @
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return The formatted store capital as currency i.e. $1,000,000
	 */
	public String getStoreCapital() {
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		return "$" + formatter.format(capital);
	}
	
	/**
	 * 
	 * @return The Store Name as String
	 */
	public String getStoreName() {
		return this.name;
	}

}
