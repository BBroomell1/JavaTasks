//Brandon Broomell
//Task 1 Java
//9/16/2021

import java.io.*;
import java.util.Random;

public class Task1 {

	public static void main(String[] args) {

		// General Variables
		int num = 0;
		Random rand = new Random();
	    int i = 0;

	    // Check for proper amount of args passed to main.
		if(args.length != 2)
	    {
	      System.out.println("Incorrect number of args passed to main.");
	      System.out.println("Args 1 should be a number 'N'");
	      System.out.println("Args 2 should be a 'filepath' ");
	      System.out.println("Where N is number of random numbers to be generated.");
	      System.out.println("'filepath' is path where new file with random numbers is to be saved.");
	      System.out.println("Or existing file will be overwritten.");
	      System.out.println("Program Exiting.");
	      System.exit(0);
	    }

		// Gets the number from the input args[0] and makes sure it can be cast to an Int.
	    try {
	    	num = Integer.parseInt(args[0]);
	    }catch(Exception ex)
	    {
	    	System.out.println(ex);
	    	System.exit(0);
	    }

	    // Gets the filepath that was passed in args[1] and creates a file.
	    // If file was not created throws exception and ends program.
	    try
	    {
	    	File filObj = new File(args[1]);

	    	// If file doesnt exist create a new file
	    	// Add "num" or "args[0]" amount of random numbers to file and close file.
	    	if(filObj.createNewFile())
	    	{
	    		System.out.println("File: " + filObj.getName() + " created");
	    		try {
	    			FileWriter filWriter = new FileWriter(args[1], true);
	    			for(i = 0; i < num; i++)
	    			{
	    				filWriter.write(i+1 + ": " + String.valueOf(rand.nextInt()));
	    				filWriter.write("\n");
	    			}
	    			filWriter.close();

	    			}catch(IOException ex)
	    			{
	    				System.out.println("Could not write to the file!");
	    				ex.printStackTrace();
	    				System.exit(0);
	    			}
	    	}

	    	// If file does exist overwrite existing file
	    	// Add "num" or "args[0]" amount of random numbers to file and close file.
	    	else
	    	{
	    		System.out.println("File: " + filObj.getName() + " already exists.\nOverwriting file.");
	    		try {
	    			FileWriter filWriter = new FileWriter(args[1], false);
	    			for(i = 0; i < num; i++)
	    			{
	    				filWriter.write(i+1 + ": " + String.valueOf(rand.nextInt()));
	    				filWriter.write("\n");
	    			}
	    			filWriter.close();

	    			}catch(IOException ex)
	    			{
	    				System.out.println("Could not write to the file!");
	    				ex.printStackTrace();
	    				System.exit(0);
	    			}
	    	}
	    }catch(IOException ex)
	    {
	    	System.out.println("File could not be created!");
	    	ex.printStackTrace();
	    	System.exit(0);
	    }
	}
}
