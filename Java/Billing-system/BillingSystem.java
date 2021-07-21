
import java.util.ArrayList;
import java.util.Scanner;

//this class is used to construct what the user has ordered
class Receipt
{
	String name;
	int quantity;
	int subtotal;
	int price;
	
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

public class BillingSystem {
	
	//Declaration
	static int serialNum = 0;	//the item's serial number
	static int quantity = 0;	//How many of said item the user wants to purchase
	static double total = 0;	//subtotal of the current list of purchased items
	static double tax = 0;		//will be used to store 6.75% of the total
	static double totalAfterTax = 0;	//total + tax
	static double tips = 0;				//minimum tips or user input tips if higher
	static double totalAfterTips = 0; //grandTotal
//	static String purchaseList[];
	static ArrayList<Receipt> purchaseList =  new ArrayList<Receipt>();	//Used to store all the ordered items
//	static int drinksPrice[] = {1, 2, 3};
	
	static Scanner sc =  new Scanner(System.in); //because this is used in multiple methods, we will declare here

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		for (int i = 0; i < 2; i++)
//		{
		details();	//Ask user details
		menu();		//Display the menu
//		userChoice();
//		System.out.println(serialNum);
//		System.out.println(quantity);
		addItems();	//ask user for choice and save those choices
		itemsPurchased();	//display what is on the purchase list
		addTax();			//add the 6.75% tax
		displayTax();		//display tax in $
		tipping();			//add minimum tip or ser input tips if higher
		
		itemsPurchased();
		displayTax();
		displayTips();		//displays the tip given
		grandTotal();		//displays grandtotal
//		}
	}
	
	
	public static void details()
	{
		System.out.println("Please enter your Name: ");
		String name = sc.nextLine();
		System.out.println("Please enter your email: ");
		String email = sc.nextLine();
	}
	//Displays the menu
	public static void menu()
	{
		System.out.println("--------------------Menu--------------------");
		System.out.println("S/N		 Item Name 	       Price");
		System.out.println("--------------------------------------------");
		System.out.println("01		 Coffee 	        $1");
		System.out.println("02		 Tea 	                $2");
		System.out.println("03		 Soda 	                $3");
		System.out.println("04		 Cake 	                $3");
		System.out.println("05		 Burger 	        $3");
		System.out.println();
		
	}
	
	//Ask user for their menu choice which includes the serial number and quantity of the item
	public static void userChoice()
	{
		System.out.println("Select the item's S/N of your choice, enter '0' to finish ordering: ");
		serialNum = sc.nextInt();
		if(serialNum != 0)
		{
			System.out.println("Enter quantity of item: ");
			quantity = sc.nextInt();
		}
	}
	
	//compares the user input with the menu and add those items into purchase list
	public static void addItems()
	{
		boolean finishShopping = true;
		String name = null;
		int subtotal = 0;
		int price = 0;
		
		while (finishShopping == true)
		{
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
		    	  finishShopping = false;
		    	break;
	
		      default:
		        System.out.println("Invalid option.");
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
	public static void addTax()
	{
		tax = ((total/100)*6.75);
		totalAfterTax = total + tax;
//		System.out.println("" + df2.format(input));	
	}
	
	//displays the tax and total after adding tax
	public static void displayTax()
	{
		System.out.format("Tax Amount: $%.2f", tax);
		System.out.println();
		System.out.format("Total after Tax: $%.2f", totalAfterTax);
		System.out.println("\n");
	}
	
	//displays what items has been purchased
	public static void itemsPurchased()
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
	public static void tipping()
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
		        break;
			case 'y':
				newTips();
		      	totalAfterTips = totalAfterTax + tips;
		        break;
			case 'N':	
				totalAfterTips = totalAfterTax + tips;
		        break;
			case 'n':
				totalAfterTips = totalAfterTax + tips;
				break;
			default:
	        	System.out.println("Invalid option.");

		}
	}
	
	//checks for higher tip
	public static void newTips()
	{
		System.out.println("How much would you like to tip? ");
		double  newTip = sc.nextDouble();
		tips = newTip;
	}
	
	//displays the 6.75% tip in $
	public static void displayTips()
	{
		System.out.format("You have tipped: $%.2f", tips);
		System.out.println();
	}
	
	//displays the Grand total
	public static void grandTotal()
	{
		System.out.format("Grand Total: $%.2f", totalAfterTips);
	}
	

}
