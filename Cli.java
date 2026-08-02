import java.util.Scanner;
import java.time.LocalDate; 
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Map;



public class Cli {

    // The main method is the entry point of the program. Rules regarding the main method:
    //     - public: so the JVM can access it from "outside"
    //     - static: so it can be called without creating an object (class scoped)
    //     - void: it doesn't return a value (aka procedure)
    //     - main: the required method name
    //     - String[] args: so it can receive command-line arguments


    public static boolean isCommandExecuted(String userInput, String commandToExecute) {

	if (userInput.equals(commandToExecute)) {

	    return true;
	}
	return false; 

    }


    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // Listen to the standard input (console)
		System.out.print("> "); // Prompt

		// add environment variable and concatenate to the input
		
		while (true) { // Infinite loop
			String command = scanner.nextLine(); // Get input from console as a string

			// Breaking down the command line content (array of strings)

			String[] commandSplitted = command.split(" ", 2);

			// Define each element from this array of strings (define variables)

			String mainCommand = commandSplitted[0];
			String commandArgument;

			// Check argument numbers from the splitted array

			if (commandSplitted.length < 2) {

			    commandArgument = "";

			}
			else {

			    commandArgument = commandSplitted[1];

			}

			String output = ""; // A variable named output of type String

			// Get values returned by the alias method


			boolean isExitExecuted = isCommandExecuted(command,"exit");
			boolean isLogoutExecuted = isCommandExecuted(command,"logout");
			boolean isEchoExecuted = isCommandExecuted(mainCommand,"echo");
			boolean isPrintExecuted = isCommandExecuted(mainCommand,"print");
			boolean isPrintenvExecuted = isCommandExecuted(mainCommand,"printenv");

			
			if (isExitExecuted == true || isLogoutExecuted == true) {

			  break; // Forces exit of the while loop
			
			}
			else if (mainCommand.equals("date")) {
				LocalDate currentDate = LocalDate.now(); // Create the date object
				output = String.valueOf(currentDate); // Display current date into string format
			}
			else if (mainCommand.equals("time")) {
				LocalTime currentTime = LocalTime.now(); // Create the time object
				output = String.valueOf(currentTime); // Display current date into string format
			}
			else if (mainCommand.equals("datetime")) {
				LocalDateTime currentDateTime = LocalDateTime.now(); // Create the date time object
				output = String.valueOf(currentDateTime); // Display current date time into string format
			}
			else if (mainCommand.equals("useraccount")) {

			        // get my user account in the system
			        String myUserAccountname = System.getProperty("user.name");

			        output = myUserAccountname;
		        
			}
			else if (mainCommand.equals("userhome")) {

			        // get my user working directory in the system
			        String userHomeDirectory = System.getProperty("user.home");

			        output = userHomeDirectory;
		        
			}
			else if (mainCommand.equals("os")) {

			        // get system properties (name and version)
			        
			        String systemName = System.getProperty("os.name");
			        String systemRelease = System.getProperty("os.version");

			        output = systemName + "(" + systemRelease + ")";
		        
			}
			else if (isPrintenvExecuted == true){


			   // Get all the user environment variables
				   
			   Map<String, String> environmentVariables = System.getenv();

			   // Display all environment variables's values and keys
			   
			   for (Map.Entry<String, String> environmentVariable : environmentVariables.entrySet()) {

				String environmentVariableKey = environmentVariable.getKey();
				String environmentVariableValue = environmentVariable.getValue();
				
				System.out.println(environmentVariableKey + " : " + environmentVariableValue);				
				
			   }

			}		
			else if (isEchoExecuted == true || isPrintExecuted == true) {

			  // Breaking down the arguments in command line

			  String commandLineArgs = commandSplitted[1];

			  String[] splittedArgs = commandLineArgs.split(" ", 0); // Build an array from arguments with string format


			  /*if (command.equals("echo")) {

			      output = "Cette commande a besoin d'un argument au minimum... Rééssayez";

			  }*/


			  // Checking argument number


			  if (splittedArgs.length == 1) {

			      output = splittedArgs[0];

			  }
			  else if (splittedArgs.length > 1) {

			      for (String arg : splittedArgs) {

				output += arg + " ";

			      }

			  }
			
			}

			else {
				// String concatenation
				output = "Command '" + command + "' not found.";
			}
			System.out.println(output); // Print with new line (ln)
			System.out.print("> "); // Prompt
		}
		scanner.close(); // Best practice, always close a stream when no more needed
		System.out.println("Bye!");
    }

}

