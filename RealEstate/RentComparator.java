package assin6;

import java.util.Comparator;
//Comparator used for sorting the list of buildings by rent.
public class RentComparator implements Comparator<Building>{
	 public int compare(Building bdg1, Building bdg2) {
	   if (bdg1.rent() < bdg2.rent())
		      return -1;
		    else if (bdg1.rent() == bdg2.rent())
		      return 0;
		    else
		      return 1;
		  } 

}
