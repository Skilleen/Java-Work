package assin6;
/**
 * This class is used to create office buildings and create several methods for them.
 *
 */
public class OfficeBuilding extends MultiUnitBuilding {
	//Constructor
	public OfficeBuilding(int units, boolean elevator){
		this.units=units;
		this.elevator=elevator;
	}
	//Outputs the data of the office building. 
	public String toString(){
		String elevatorCheck="no elevator";
		if (elevator){
			elevatorCheck="elevator";
		}
		return "offices ("+this.units+" offices, "+elevatorCheck+")";
	}
	//Calculates the rent
	public int rent(){
		int rent=(2000*this.units);
		if(elevator){
			rent+=(50*this.units);
		}
		return rent;
	}
	//Calculates the tax
	public int tax(){
		int tax=1000;
		if(elevator)
			tax+=100;
		for(int i=this.units;i>5;i--){
			tax+=1000;
		}
		return tax;
	}

}
