/*
 * A test program for the DogOwner class.  Tests only
 * correct behavior, no errors.
 * 
 * Example for Assignment 3
 * CISC 124, Winter 2014
 * M. Lamb
 */
public class DogWalkingClub {
    public static void main(String args[]) {
        DogOwner mary = new DogOwner("Mary", 1, 1, "morning");
        DogOwner john = new DogOwner("John", 1, 2, "afternoon");
        DogOwner fred = new DogOwner("Fred", 2, 1, "evening");
        DogOwner martha = new DogOwner("Martha", 1, 0, "afternoon");
        DogOwner george = new DogOwner("George", 0, 1, "evening");
        DogOwner peter = new DogOwner("Peter", 2, 1,  "afternoon");
        DogOwner jan = new DogOwner("Jan", 1, 1, "afternoon");
        
        // Display all the dog owners
        DogOwner petClub[] = {mary,john,fred,martha,george,peter,jan};        
        for (int i = 0; i < petClub.length; i++) 
            System.out.println(i + ". " + petClub[i]);
        System.out.println();
        
        
        // Try a few pairs of people and see if they're a good match
        
        // Mary and John are not a good match because they prefer different times of the day
        System.out.print("Mary and John: ");
        if (mary.match(john))
            System.out.println("yes");
        else
            System.out.println("no");
            
        // Peter and Jan should be a match (same time of day and compatible dogs)
        System.out.print("Peter and Jan: ");
        if (peter.match(jan))
            System.out.println("yes");
        else
            System.out.println("no");
        System.out.println();
         
        // Find all matches between the dog owners
        DogOwner.printMatches(petClub);
        System.out.println();        
        
        // Give Martha another small dog.  She should have two now.
        martha.addDog(false); // false means small dog
        System.out.println("martha after adding a small dog: " + martha);
        System.out.println();
        
        // Give Martha her first large dog.  She's no longer prevented from walking with other
        // owners of large dogs.
        martha.addDog(true); // true means large dog
        System.out.println("martha after adding a large dog: " + martha);
        System.out.println();
        
        // Show all pet owners and all possible matches after the changes
        for (int i = 0; i < petClub.length; i++) 
            System.out.println(i + ". " + petClub[i]);
        System.out.println();
        DogOwner.printMatches(petClub);
        System.out.println();  
        
        // Take away one of Peter's small dogs.  Because he has fewer dogs, he matches more
        // people now.
        peter.subtractDog(false);
        System.out.println("peter after losing a small dog: " + peter);
        System.out.println();
        
        // Show all pet owners and all possible matches one more time
        for (int i = 0; i < petClub.length; i++) 
            System.out.println(i + ". " + petClub[i]);
        System.out.println();
        DogOwner.printMatches(petClub);
        System.out.println();  
        
        // Demonstrate error-checking in constructors and methods.
        System.out.println("Creating a dog owner with negative number of large dogs:");
        DogOwner error1 = new DogOwner("Error1", 1, -2, "afternoon");
        System.out.println("New owner is: " + error1);
        System.out.println();
        
        System.out.println("Creating a dog owner with negative number of small dogs:");
        DogOwner error2 = new DogOwner("Error2", -1, 3, "morning");
        System.out.println("New owner is: " + error2);
        System.out.println();
        
        System.out.println("Creating a dog owner with a bad time of day string:");
        DogOwner error3 = new DogOwner("Error3", 1, 2, "midnight");
        System.out.println("New owner is: " + error3);
        System.out.println();
        
        System.out.println("Subtracting a non-existent small dog");
        System.out.println("Before: " + george);
        george.subtractDog(false);
        System.out.println("After: " + george);
        System.out.println();
        
        System.out.println("Subtracting a non-existent large dog");
        DogOwner tiny = new DogOwner("Tiny", 5, 0, "morning");
        System.out.println("Before: " + tiny);
        tiny.subtractDog(true);
        System.out.println("After: " + tiny);
        System.out.println();
        
        System.out.println("TESTING IS FINISHED");
        
        

        
         
        
        
        
        
        
        
        
        
        
        
    } // end main
} // end class DogWalkingClub