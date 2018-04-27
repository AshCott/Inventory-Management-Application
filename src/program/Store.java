package program;

import java.text.DecimalFormat;

/**
 * 
 * @author 
 *
 */
public class Store {
	double storeCapital;
	
	public Store() {
		// TODO Auto-generated constructor stub
		this.storeCapital = 1000000; 
	}
	
	/**
	 * 
	 * @return The formatted store capital as currency i.e. $1,000,000
	 */
	public String getStoreCapital() {
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		return "$" + formatter.format(storeCapital);
	}

}
