//Brandon Broomell
//Task 2 Java
//9/16/2021

import java.io.*;
import java.util.*;

public class Task2 {

	public static void main(String[] args) {
		String filePath;
		String orderBy;
		if(args.length == 2)
		{
			filePath = args[0];
			orderBy = args[1];
			if(orderBy.equals("desc"))
			{
				sortDesc(scanFile(filePath));
			}
			else if(orderBy.equals("asc"))
			{
				sortAsc(scanFile(filePath));
			}
			else
			{
				System.out.println("(Optional)args 2: 'asc' or 'desc' or blank for 'asc'");
				System.out.println("Program Exiting.");
				System.exit(0);
			}

		}
		else if(args.length == 1)
		{
			filePath = args[0];
			sortAsc(scanFile(filePath));
		}
		else
		{
			System.out.println("Number of args passed can either be 1 or 2.");
			System.out.println("args 1: filepath of file containing numbers to be sorted.");
			System.out.println("(Optional)args 2: 'asc' or 'desc' or blank for 'asc'");
			System.out.println("Program Exiting.");
			System.exit(0);
		}

	}

	// Method to sort file numbers in ASC order.
	public static List<Long> scanFile(String filePath)
	{
		String tempStr;
		Long tempLong;
		// Holds numbers that are being read from file.
		List<Long> arr = new ArrayList<Long>();

		// Try to scan each line of file passed in filePath var.
		// If no file can be found throw exception.
		// Scan each line for an long var and if excepted place into List arr.
		// Sort arr in asc order and print to console.
		try
		{
			Scanner scan = new Scanner(new File(filePath));
			while(scan.hasNextLine())
			{
				tempStr = scan.nextLine();
				try
				{
					tempLong = Long.parseLong(tempStr);
					arr.add(tempLong);
				}catch(NumberFormatException e)
				{
					continue;
				}
			}
		}catch(Exception ex)
		{
			System.out.println("File: " + filePath + " cannot be found.");
			ex.printStackTrace();
		}
		return arr;
	}

	// Method to sort file numbers in DESC order.
	public static void sortDesc(List<Long> arr)
	{
		Collections.sort(arr, Collections.reverseOrder());
		arr.forEach(System.out::println);
	}
	public static void sortAsc(List<Long> arr)
	{
		Collections.sort(arr);
		arr.forEach(System.out::println);
	}

}
