package tictactoe;

import java.util.Random;
import java.util.Scanner;

class Player{
	//ask user for input on where to place the tic/tac
	private int[][] table = new int [3][3]; // 3x3 board
	private int player_1_piece = 1;	//Player 1's board piece
	private int player_2_piece = 2;	//Player 2's board piece
	private boolean trueEndGame = false;
	private int playerNum = 1; 	//Starting player
	private int input = 0;	//input set to 0 so that it can be used by both player and robot later
	
	//Starts the game
	//Ask user for their input
	//Player 1 starts first then player 2
	//Game will end if winning condition has been activated or no more empty spaces left 
	void askPlayerForLocation() 
	{
		
		while (trueEndGame == false) //while the game has not ended, continue asking for input
		{
			System.out.println("Board placement: Please pick spaces between 1 to 9");
			System.out.println(" 1 | 2 | 3\n---+---+---\n 4 | 5 | 6\n---+---+---\n 7 | 8 | 9\n"); //Displays the board placement from 1-9 for easy readability.
			
			if (playerNum == 1) { //if it's a player ask for input			
				humanPlayer();
			}
			
			else //else if it's a robot, robotPlayer() method will generate player's 2 input
			{
				robotPlayer();
			}
			
			//checkGame(); //Check the pieces on the board for winning rows
			//showBoard(); //Display what is currently on the board
		}
		
		System.out.println("End Game");
	}
	
	//Player's turn
	void humanPlayer()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Player " + playerNum + ", Please enter your tic/tac on the board: ");
		input = sc.nextInt();
		
		//placeTic sends the user input and will check which player is playing i.e. 1 or 2
		placeTic(input, playerNum); //board choice and the player num will be sent to method
		checkGame(); //Check the pieces on the board for winning rows
		showBoard(); //Display what is currently on the board
	}
	
	//robot's turn
	void robotPlayer()
	{
		input = robotPlayerInput();
		placeTic(input, playerNum);
		checkGame(); //Check the pieces on the board for winning rows
		showBoard(); //Display what is currently on the board
	}
	
	
	//robot's random input
	int robotPlayerInput()
	{
		Random r = new Random();
		int ranNum = r.nextInt(9)+1; //pick location number between 1-9
		return ranNum;
	}
	
	//places the player's choice onto the board
	void placeTic(int location, int player)//location = board placement, player =  player 1 or 2
	{
		
		int boardPiece = 0;
		if (player == 1) //Checks if player 1 or 2, so that will know what value to place on the board
		{
			boardPiece = player_1_piece;
		}
		else if (player == 2)
		{
			boardPiece = player_2_piece;
		}
			
		
		switch(location)
		{
			case 1:
				placeTicTac(0, 0, boardPiece);
			    break;
			case 2:
				placeTicTac(0, 1, boardPiece);
			    break;
			case 3:
				placeTicTac(0, 2, boardPiece);
			    break;
			case 4:
				placeTicTac(1, 0, boardPiece);
			    break;
			case 5:
				placeTicTac(1, 1, boardPiece);
			    break;
			case 6:
				placeTicTac(1, 2, boardPiece);
			    break;
			case 7:
				placeTicTac(2, 0, boardPiece);
			    break;
			case 8:
				placeTicTac(2, 1, boardPiece);
			    break;
			case 9:
				placeTicTac(2, 2, boardPiece);
			    break;
			default:
				System.out.println("Invalid input, please try again");//If the input position is out of range, user will try again
				askPlayerForLocation();
				
		}
		
	}
	
	//places the piece onto the board, checks if empty else retry
	void placeTicTac(int row, int column, int value)
	{
		if (table[row][column] == 0)
		{
			table[row][column] = value;
			//System.out.println(value);
			if (playerNum == 1) //Here we will switch the players if they have successfully placed piece on board
				playerNum = 2;
			else if (playerNum == 2)
				playerNum = 1;
		}
		else
		{
			//Player will have to redo placement if there is already a piece on the board
			System.out.println("Placement already has a piece, please try again");
			askPlayerForLocation();
		}
	}
	
	//Display what is currently on the board
	void showBoard()
	{
		System.out.println("Current pieces on the board");
		int counter = 0; //count the number if empty spaces on the board
		for (int i=0; i<table.length; i++)
		{
			for (int k=0; k<table.length; k++)
			{
				if (k == 0)
				{
					System.out.print(" ");//gives the spacing on the left side on the left most number for anesthetics reasons
				}
				//table[i][k] = 1;
				System.out.print(table[i][k] + " "); 
				if (table[i][k] == 0)
				{
					counter++;//counts how many empty spaces left on the board
				}
				if (k == 1 || k == 0)
				{
					System.out.print("| "); //Draws the vertical grid lines
				}
			}
			System.out.println();
			if (i == 0 || i == 1)
			{
			System.out.println("---+---+---"); //Draws the horizontal grid lines
			}
			
		}
		System.out.println();
		if (counter < 1) //If there is 0 available space on the board, the game is over
		{
			System.out.println("Draw!");
			trueEndGame = true;
		}
	}
	
	//Check the pieces on the board for winning rows
	void checkGame()
	{		
		for (int p=0; p<table.length; p++)
		{
			//Vertical Wins
			if ((table[0][p] == player_1_piece) && (table[1][p] == player_1_piece)  && (table[2][p] == player_1_piece))
			{
				trueEndGame = true;
				System.out.println("Player 1 Wins");
			}
			else if (table[0][p] == player_2_piece && table[1][p] == player_2_piece  && table[2][p] == player_2_piece)
			{
				trueEndGame = true;
				System.out.println("Player 2 Wins");
			}
			
			//Horizontal Wins
			else if ((table[p][0]==player_1_piece) && (table[p][1]==player_1_piece)  && (table[p][2] == player_1_piece))
			{
				trueEndGame = true;
				System.out.println("Player 1 Wins");
			}
			else if (table[p][0] == player_2_piece && table[p][1] == player_2_piece  && table[p][2] == player_2_piece)
			{
				trueEndGame = true;
				System.out.println("Player 2 Wins");
			}
		}
		//diagonal top left to bottom right
		if ((table[0][0] == player_1_piece) && (table[1][1] == player_1_piece)  && (table[2][2] == player_1_piece))
		{
			trueEndGame = true;
			System.out.println("Player 1 Wins");
		}
		else if (table[0][0] == player_2_piece && table[1][1] == player_2_piece  && table[2][2] == player_2_piece)
		{
			trueEndGame = true;
			System.out.println("Player 2 Wins");
		}
		
		
		//diagonal top right to bottom left
		else if ((table[0][2] == player_1_piece) && (table[1][1] == player_1_piece)  && (table[2][0] == player_1_piece))
		{
			trueEndGame = true;
			System.out.println("Player 1 Wins");
		}
		else if (table[0][2] == player_2_piece && table[1][1] == player_2_piece  && table[2][0] == player_2_piece)
		{
			trueEndGame = true;
			System.out.println("Player 2 Wins");
		}
	}
	
}


public class Main {
	public static void main(String[] args)
	{
	Player p = new Player(); //creates the Player object
	p.askPlayerForLocation(); //starts the game
	}
}
