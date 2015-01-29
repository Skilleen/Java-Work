package assin6;
/**
 * This class is used to create apartment buildings and create several methods for them.
 *
 */
public class ApartmentBuilding extends MultiUnitBuilding {
	private int rating;
	//Constructor
	public ApartmentBuilding(int units, int rating, boolean elevator){
		this.units=units;
		this.rating=rating;
		this.elevator=elevator;
	}
	//toString method used to output the data of the current apartment. 
	public String toString(){
		String elevatorCheck="no elevator";
		if (elevator){
			elevatorCheck="elevator";
		}
		return "apartments ("+units+" units, location rating "+rating+", "+elevatorCheck+")";
	}
	//Calculates the rent of the apartment.
	public int rent(){
		int rent=500*this.units;
		if(elevator){
			rent+=(50*this.units);
		}
		for(int i=this.rating;i>1;i--){
			rent+=(100*this.units);
		}
		return rent;
	}
	//Calculates the tax of the apartment.
	public int tax(){
		int tax=200*this.units;
		if(elevator)
			tax+=100;
		return tax;
	}

}
