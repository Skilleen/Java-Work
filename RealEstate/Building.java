package assin6;
import java.util.ArrayList;
import java.util.Collections;
/**
 * 
 * Scott Killeen
 * March 9, 2014
 * 10093303
 * The building class is an abstract class that creates objects for three different types of buildings.
 * This class will then be used by a retail class to display information of these buildings.
 */

public abstract class Building implements Comparable<Building>{
	public abstract String toString();
	public abstract int rent();
	public abstract int tax();
	//Sorts the list in accending order by rent.
	public static void sortByRent(ArrayList<Building> List) {
		Collections.sort(List, new RentComparator());
	}
	//Sorts the list in accending order by Tax.
	public static void sortByTax(ArrayList<Building> List){
		Collections.sort(List, new TaxComparator());
	}
	//Sorts the list in accending order by Profit.
	public static void sortByProfit(ArrayList<Building> List) {
		Collections.sort(List);
	}
	//CompareTo method used by the sortByProfit method.
	public int compareTo(Building other){
		Building otherBdg = (Building)other;
		        if ((rent()-tax()) > (otherBdg.rent()-otherBdg.tax()))
		            return 1;
		        else if ((rent()-tax()) < (otherBdg.rent()-otherBdg.tax()))
		            return -1;
		        else 
		            return 0;
		
	}
	
		


}
