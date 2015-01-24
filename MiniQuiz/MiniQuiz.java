
public class finalprogram3 {
	public static void main(String args[]) {
		java.util.Scanner Myscan = new java.util.Scanner(System.in);
		switch(Menu()){ 
		case 1: 				
		int num1;
		System.out.println("Hello! Can you tell me what 8 times 17 is?");
		num1=Myscan.nextInt();
		if(num1==136){
			System.out.println("Well done! You must be Jeremey!");
		}
		else{
			System.out.println("Fool! You know nothing!");
		}
			
			
		break;
		case 2: 
		String ans;
		System.out.println("Hello! What is the most Southern country in the South America?");
		ans=Myscan.nextLine();
		if(ans.equalsIgnoreCase("Argentina")){
			System.out.println("Well done! You must be Jeremey!");
		}
		else{
			System.out.println("Fool! You know nothing!");
		}
			
		
		break;
		case 3:
			
		break;
		case 4:
			String ans1;
			System.out.println("Hello! What is the name of the Dwarf in the Fellowship?");
			ans=Myscan.nextLine();
			if(ans.equalsIgnoreCase("Gimli")){
				System.out.println("Well done! You must be Jeremey!");
			}
			else{
				System.out.println("Fool! You know nothing!");
			}
		break;
		case 5: // Quit
			System.out.println("Thanks for stoppinng by!");
			System.exit(0);
		break;
		
		default:
		}
		
		
	}
	public static int Menu() {
		int select;
		// creating the Scanner object for keyboard input.
		java.util.Scanner s = new java.util.Scanner(System.in);
		System.out.println("Enter the number of your selection");
		System.out.println("1. Arithemetic Question");
		System.out.println("2. Trivia Question");
		System.out.println("3. Random number guessing!");
		System.out.println("4. Lord of the Rings Trivia!");
		System.out.println("5. Quit");
		System.out.println("-------------------------------");
		select = s.nextInt();
		
		
		return (select);

	}
}
