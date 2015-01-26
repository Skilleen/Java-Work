import java.util.*;
import java.text.DecimalFormat; 
public class Planets {
	/**
	 * 
	 * Scott Killeen
	 * January 14, 2014
	 * 10093303
	 * This creates a program that will receive input of a user's name, age, and weight and display their new
	 * age and weight on another planet.
	 */
	
	//constants
	public static final int MERCURY_YEAR = 88;
	public static final double MERCURY_GRAV = 0.40;
	public static final int VENUS_YEAR = 225;
	public static final double VENUS_GRAV = 0.90;
	public static final int JUPITER_YEAR = 4380;
	public static final double JUPITER_GRAV = 2.50;
	public static final int SATURN_YEAR = 11767;
	public static final double SATURN_GRAV = 1.10;
	public static final int EARTH_YEAR = 365;
	public static final double EARTH_GRAV = 9.80;
	
	public static void main(String args[]){
		String goAgain="y";
		while(goAgain.equalsIgnoreCase("Y")){
			int age=0;
			double weight=0;
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter your name!");
			String name = input.nextLine();
			try
			{
			  System.out.println("Please enter your age in years!");
			  age=input.nextInt();
			}
			catch(InputMismatchException exception)
			{
			  System.out.println("This is not an integer!");
			  System.exit(0);
			}
			try
			{
			   System.out.println("Please enter your weight in kilograms!");
			   weight=input.nextDouble();
			}
			catch(InputMismatchException exception)
			{
			  System.out.println("This is not a Double!");
			  System.exit(0);
			}
			ageandweight(age,weight,name);
			startover(goAgain);

		}
		
	}
	public static double ageandweight(int age,double weight,String name){
		double newAge=0; //Double for calculation, will be turned into an Int on output.
		DecimalFormat df = new DecimalFormat("#.##");
		double newWeight=0;
		Scanner input = new Scanner(System.in);
		System.out.println("Please select your planet!(Mercury, Venus, Jupiter, Saturn)");
		String planet=input.next();
		boolean realplanet=false;
		while(realplanet==false){
			if (planet.equalsIgnoreCase("Mercury")){
				newAge=age*((double)EARTH_YEAR/(double)MERCURY_YEAR);
				newWeight=weight*MERCURY_GRAV;
				realplanet=true;
			}
			else if(planet.equalsIgnoreCase("Venus")){
				newAge=age*((double)EARTH_YEAR/(double)VENUS_YEAR);
				newWeight=weight*VENUS_GRAV;
				realplanet=true;
			
			}
			else if(planet.equalsIgnoreCase("Jupiter")){
				newAge=age*((double)EARTH_YEAR/(double)JUPITER_YEAR);
				newWeight=weight*JUPITER_GRAV;
				realplanet=true;
			}
			else if(planet.equalsIgnoreCase("Saturn")){
				newAge=age*((double)EARTH_YEAR/(double)SATURN_YEAR);
				newWeight=weight*SATURN_GRAV;
				realplanet=true;
			}
			else{
				System.out.println("Sorry, that is not a correct planet, try again");
				planet=input.next();
			}
		}
		System.out.println("On "+planet+", "+name+ " will be "+Math.round(newAge)+
				" years, and will weigh "+df.format(newWeight)+" kilos \n");

		return newAge;
		
	}
	
	public static String startover(String goAgain){
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to try again? (y or n)");
		goAgain=input.next();
		boolean correctchoice=false;
		while(correctchoice==false){
			if(goAgain.equalsIgnoreCase("y")){
				correctchoice=true;
				break;
			}
			else if(goAgain.equalsIgnoreCase("n")){
				correctchoice=true;
				System.out.println("Have a good day!");
				System.exit(0);
			}
			else
				System.out.println("Sorry, that is not a correct answer, try again");
				goAgain=input.next();
				if(goAgain.equalsIgnoreCase("y")){
					correctchoice=true;
				}
		}
		return goAgain;
	}

}
