/**
	 * 
	 * Scott Killeen
	 * Feburary 6th, 2014
	 * 10093303
	 * This creates a program that will help set up matches for local dog walkers by analysing how many
	 * dogs each owner has, and when they like to walk.
	 */
public class DogOwner {
	private String name;
	private int small;
	private int big;
	private String time;
	public static void main(String args[]){
		
	}
	//Constructor 
	public DogOwner(String theName, int smallDogs, int bigDogs, String timeofDay){
		name=theName;
		small=smallDogs;
		big=bigDogs;
		time=timeofDay;
		if (small<0){
			System.out.println("Error, cannot have negative puppies!");
			small=0;
		}
		if (big<0){
			System.out.println("Error, cannot have negative puppies!");
			big=0;
		}
		if (time.equalsIgnoreCase("morning")){
		}
			else if(time.equalsIgnoreCase("afternoon")){
			}
			else if(time.equalsIgnoreCase("evening")){
			}
			else{
			System.out.println("Error,"+time+" is not a valid time of day!");
			time="morning";
		} //end Constructor 
		
	}
	//Checks to see if the two owners match.
	public boolean match(DogOwner person){
		boolean matching=false;
		
		if(person.time.equals(DogOwner.this.time)){
			if((person.small+person.big)+(DogOwner.this.small+DogOwner.this.big)<=5){
				matching=true;
			}
		}
		else{
			matching=false;
		}
		if((person.big<=0 && DogOwner.this.big>0) || (person.big>0 && DogOwner.this.big<=0)){
			matching=false;
		}
		return matching;
	}
	//Adds either a small or large dog to the owner. 
	public boolean addDog(boolean add){
		if(add){
			big++;
		}
		else{
			small++;
		}
		return add;
	}
	//Removes either a small or large dog to the owner.
	public boolean subtractDog(boolean sub){
		if(sub){
			big--;
		}
		else{
			small--;
		}
		if (small<0){
			System.out.println("Error! Cannot subtract past zero puppies!");
			small=0;
		}
		if (big<0){
			System.out.println("Error! Cannot subtract past zero puppies!");
			big=0;
		}
		return sub;
	}
	//Neatly displays each dog Owner and data about them.
	public String toString(){
		String plural="";
		String pluralTwo="";
		if(big!=1){
			pluralTwo="s";
		}
		if(small!=1){
			plural="s";
		}
		String output=name+"("+time+", "+small+" small dog"+plural+", "+big+" large dog"+pluralTwo+")";
		return output;
	}
	//Prints all matches between the dog owners.
	public static void printMatches(DogOwner[] list){
		for(int i=0;i<=list.length-1;i++){
			for(int j=i+1;j<=list.length-1;j++)
			if(list[i].match(list[j])==true){
				System.out.println(list[i]+" and "+list[j]+" match!");
				
			}
		}
	}
}
