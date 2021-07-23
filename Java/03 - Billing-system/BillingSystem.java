package practice;

//Billing System Assignment

import java.util.ArrayList;
import java.util.Scanner;

//this class is used to construct what the user has ordered
class Receipt
{
	String name;
	private int quantity;
	private int subtotal;
	private int price;
	
	//constructor for items that have been purchased
	Receipt(String name, int quantity, int price, int subtotal)
	{
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public int getSubtotal()
	{
		return subtotal;
	}
	
	@Override
	public String toString()
	{
		return name + "			" + quantity + "		" + price + "		"  + subtotal + "\n";
		
	}
}


class InternalSystem
{
	
	//Declaration
	private static int serialNum = 0;	//the item's serial number
	private static int quantity = 0;	//How many of said item the user wants to purchase
	private static double total = 0;	//subtotal of the current list of purchased items
	private static double tax = 0;		//will be used to store 6.75% of the total
	private static double totalAfterTax = 0;	//total + tax
	private static double tips = 0;				//minimum tips or user input tips if higher
	private static double totalAfterTips = 0; //grandTotal

	private static ArrayList<Receipt> purchaseList =  new ArrayList<Receipt>();	//Used to store all the ordered items

	private static Scanner sc =  new Scanner(System.in); //because this is used in multiple methods, we will declare here

	//Placed all the methods to run in sequence in the runSystem() method.
	public void runSystem()
	{
		details();	//Ask user details
		menu();		//Display the menu

		addItems();	//ask user for choice and save those choices
		itemsPurchased();	//display what is on the purchase list
		addTax();			//add the 6.75% tax
		displayTax();		//display tax in $
		tipping();			//add minimum tip or ser input tips if higher
		
		itemsPurchased();
		displayTax();
		displayTips();		//displays the tip given
		grandTotal();		//displays grandtotal
	}
	
	private static void details()
	{
		System.out.println("Please enter your Name: ");
		String name = sc.nextLine();
		System.out.println("Please enter your email: ");
		String email = sc.nextLine();
	}
	//Displays the menu
	private static void menu()
	{
		System.out.println("--------------------Menu--------------------");
		System.out.println("S/N		 Item Name 	       Price");
		System.out.println("--------------------------------------------");
		System.out.println("01		 Coffee 	        $1");
		System.out.println("02		 Tea 	                $2");
		System.out.println("03		 Soda 	                $3");
		System.out.println("04		 Cake 	                $4");
		System.out.println("05		 Burger 	        $5");
		System.out.println();
		
	}
	
	//Ask user for their menu choice which includes the serial number and quantity of the item
	private static void userChoice()
	{
		System.out.println("Select the item's S/N of your choice, enter '0' to finish ordering: ");
		serialNum = sc.nextInt();
//		if(serialNum != 0)
//		{
			System.out.println("Enter quantity of item: ");
			quantity = sc.nextInt();
			if (quantity == 0) //if quantity is 0, it will ask user to redo their choice
			{
				System.out.println("You have given 0 quantity for the item, please redo your choice.");
				userChoice();
			}
//		}
	}
	
	//compares the user input with the menu and add those items into purchase list
	private static void addItems()
	{
		boolean finishShopping = true;
		String name = null;
		int subtotal = 0;
		int price = 0;
		
		
		while (finishShopping == true)
		{
			subtotal = 0;	//reset subtotal back to 0 for new items
			userChoice();
			switch(serialNum){
		      case 1:
		    	  price = 1;
		    	  subtotal += (1*quantity);
		    	  name = "Coffee";
		        break;
		      case 2:
		    	  price = 2;
		    	  subtotal += (2*quantity);
		    	  name = "Tea";
		        break;
		      case 3:
		    	  price = 3;
		    	  subtotal += (3*quantity);
		    	  name = "Soda";
		        break;
		      case 4:
		    	  price = 4;
		    	  subtotal += (4*quantity);
		    	  name = "Cake";
		        break;
		      case 5:
		    	  price = 5;
		    	  subtotal += (5*quantity);
		    	  name = "Burger";
		        break;
		      case 0:
		    	  System.out.println("Completed shopping\n");
//		    	  name = "Complete";
//		    	  quantity = 0;
//		    	  subtotal = 0;
		    	  finishShopping = false; //stops the addItems while loop
		    	break;
	
		      default:
		        System.out.println("Please enter a S/N betwen 0 to 5: \n");
		    }
			if (finishShopping == true)
			{
			Receipt rc = new Receipt(name, quantity, price, subtotal);
			purchaseList.add(rc);	//stores the ordered items as an arraylist of items
//			System.out.println(purchaseList);
			System.out.println("Item: " + name + " | Quantity: " + quantity + " | subtotal = $" + subtotal);
			total += subtotal;
			System.out.println("Current total: $" + total+"\n");
			}
		}
		
	}
	
	//adds teh 6.75% tax
	private static void addTax()
	{
		tax = ((total/100)*6.75);
		totalAfterTax = total + tax;
//		System.out.println("" + df2.format(input));	
	}
	
	//displays the tax and total after adding tax
	private static void displayTax()
	{
		System.out.format("Tax Amount: $%.2f", tax);
		System.out.println();
		System.out.format("Total after Tax: $%.2f", totalAfterTax);
		System.out.println("\n");
	}
	
	//displays what items has been purchased
	private static void itemsPurchased()
	{
		System.out.println();
		System.out.println("Items Purchased");
		System.out.println("---------------");
		System.out.println("Item Name	  Quantity	     price		subtotal");
		System.out.println("----------------------------------------------------------------");
		int counter = 0;
		for(Receipt value : purchaseList)
		{
			counter ++;
			System.out.print(value);
			if(counter %4 == 0)	//because 1 order item has 4 elements, we will create a new line after every 4 elements
			{
				System.out.println();
			}
		}
		System.out.println();
//		System.out.println(purchaseList);
		System.out.format("Subtotal: $%.2f", total);
		System.out.println();
		
	}
	
	//adds minimum or higher user tip
	private static void tipping()
	{
		boolean tipped = false;
		
		while (tipped == false)
		{
			tips = ((totalAfterTax/100)*10);	//Minimum tips
			System.out.format("Current tip: $%.2f", tips);
			System.out.println();
			System.out.println("Would you like to tip more?");
			System.out.println("Enter 'y' for Yes and 'n' for No: ");
			
			char  userInput = sc.next().charAt(0);
					
			switch(userInput)
			{
				case 'Y':
					newTips();
					totalAfterTips = totalAfterTax + tips;	//stores tip
					tipped = true;
			        break;
				case 'y':
					newTips();
			      	totalAfterTips = totalAfterTax + tips;
			      	tipped = true;
			        break;
				case 'N':	
					totalAfterTips = totalAfterTax + tips;
					tipped = true;
			        break;
				case 'n':
					totalAfterTips = totalAfterTax + tips;
					tipped = true;
					break;
				default:
		        	System.out.println("\nTipping:\nPlease enter either 'Y' for Yes or 'N' for No.");
	
			}
		}
	}
	
	//checks for higher tip
	private static void newTips()
	{
		System.out.println("How much would you like to tip? ");
		double  newTip = sc.nextDouble();
		if (newTip < tips)	//check if tip amount is below or equal to minimum
		{
			System.out.println("Entered amount lower or equal to minimum, please try the options again.\n");
			tipping();
		}
		else if (newTip >= tips) //check if new tip by user is higher or equal to minimum tips
		{
		tips = newTip;
		}
	}
	
	//displays the 6.75% tip in $
	private static void displayTips()
	{
		System.out.format("You have tipped: $%.2f", tips);
		System.out.println();
	}
	
	//displays the Grand total
	private static void grandTotal()
	{
		System.out.format("Grand Total: $%.2f", totalAfterTips);
	}
	

}




public class BillingSystem {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InternalSystem is = new InternalSystem();
		is.runSystem();
	}

}
