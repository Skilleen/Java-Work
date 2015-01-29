package assin6;

import java.util.Comparator;
//Comparator used for sorting the list of buildings by tax.
public class TaxComparator implements Comparator<Building>{
	 public int compare(Building bdg1, Building bdg2) {
		    if (bdg1.tax() < bdg2.tax())
		      return -1;
		    else if (bdg1.tax() == bdg2.tax())
		      return 0;
		    else
		      return 1;
		  } 
}
