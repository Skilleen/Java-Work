package assin6;
//This class is used to deal with exceptions that have occurred in the program.
public class BuildingException extends Exception {
	//Constructor for Error with a message.
	public BuildingException(String messege){
		super(messege);
	}
	//Constructor for error with no message. 
	public BuildingException(){		
	}

}
