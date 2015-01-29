package assin6;
/**
 * This class is used to create houses and create several methods for them.
 *
 */
public class House extends Building {
	private int bathrooms;
	private int bedrooms;
	//Constructor
	protected House(int bedrooms, int bathrooms) throws BuildingException{
		this.bedrooms=bedrooms;
		this.bathrooms=bathrooms;
		if (this.bedrooms<2){
			throw new BuildingException("attempt to create a house with fewer than 2 bedrooms!");
		}
		if (this.bathrooms<1){
			throw new BuildingException("attempt to create a house with less than 1 bathroom");
		}
		
		
	}
	//toString method to show the data of the house.
	public String toString(){
		return "house ("+bedrooms+" bedrooms, "+bathrooms+" baths)";
	}
	//Adds bathrooms/bedrooms onto the current house.
	public void extension(int newBedrooms, int newBathrooms){
		this.bedrooms+=newBedrooms;
		this.bathrooms+=newBathrooms;
	}
	//Calculates the rent of the house.
	public int rent(){
		int rent=1000;
		int tempbathrooms=bathrooms;
		int tempbedrooms=bedrooms;
		while(tempbathrooms>1){
			rent+=100;
			tempbathrooms--;
		}
		while(tempbedrooms>2){
			rent+=200;
			tempbedrooms--;
		}	
		return rent;
	}
	//Calculates the tax of the house.
	public int tax(){
		int tax=0;
		tax+=(this.bedrooms*200);
		tax+=(this.bathrooms*50);
		return tax;
	}

}
