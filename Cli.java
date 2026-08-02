import java.util.Scanner;
import java.time.LocalDate; 
import java.time.LocalTime;
import java.time.LocalDateTime;


public class Cli {

    // The main method is the entry point of the program. Rules regarding the main method:
    //     - public: so the JVM can access it from "outside"
    //     - static: so it can be called without creating an object (class scoped)
    //     - void: it doesn't return a value (aka procedure)
    //     - main: the required method name
    //     - String[] args: so it can receive command-line arguments


    public static boolean isExit(String userInput, String commandToExecute) {

	if (userInput.equals(commandToExecute)) {

	    return true;
	}
	return false; 

    }

    public static boolean isEcho(String userInput, String commandToExecute) {

	// Checking the command line         

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

			// Get the value returned by the exit function


			boolean isExitCommandExecuted = isExit(command,"exit");
			boolean isLogoutCommandExecuted = isExit(command,"logout");
			boolean isEchoCommandExecuted = isEcho(mainCommand,"echo");
			boolean isPrintCommandExecuted = isEcho(mainCommand,"print");
			
			if (isExitCommandExecuted == true || isLogoutCommandExecuted == true) {

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
			else if (mainCommand.equals("printenv")) {

				// Build the entire command
			
				String entireCommand = "printenv" + " " + commandArgument;

				
				if (command.equals(entireCommand)) {

			        	String environmentVariable = System.getenv(commandArgument);
			        
			        	if (environmentVariable != null) {

			            		output = environmentVariable;
			        	}

				   
				}
						


			}			
			else if (isEchoCommandExecuted == true || isPrintCommandExecuted == true) {

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

