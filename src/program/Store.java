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
		this.name = "SuperMart";
		this.capital = 100000;
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
