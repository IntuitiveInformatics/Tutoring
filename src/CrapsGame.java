/*Name:		Cheri Nadell (ID 79778391)
 *Lab2:		This lab will implement a simulator of a simpler version of
 *			the dice game Craps, which is called the "Passline Bet."
 *Class:	CrapsGame. This class represents all the information for a
 *			SINGLE craps game.
***********************************************************************/
import java.util.Random; 	// Import the random number generator
//import java.util.Scanner;

public class CrapsGame 
{	
	// Declare class variables.
	private int numOfDiceRolls;				// Total number of dice rolls.
	int pointNumber;  						// Initial roll if not craps or natural.
	private CrapsMetricsMonitor gameStats;	// CrapsMetricsMonitor object.
	/*private Scanner readIn = 
			new Scanner(System.in); */
	private Random rand1 = new Random();		// Create a Random object for dice rolling.
	private Random rand2 = new Random();											
	private static String nameCheck;
	
	// Constructor
	public CrapsGame() { numOfDiceRolls = 0; }

	// Parameterized Constructor
	public CrapsGame(CrapsMetricsMonitor monitorObject, String name) 
	{
		this.pointNumber = 0;				// Initialize point number flag for the 1st roll.
		this.numOfDiceRolls = 0;			// Initialize the number of dice rolls.
		this.gameStats = monitorObject;		// Initialize the CrapsMetricsMonitor Object.
		nameCheck = name;
	}
	 
	// Increments the number of dice rolls that have occurred in the current game.
	public void incrementDiceRolls() { ++this.numOfDiceRolls;} 
	
	// Getter method for the number of dice rolls.
	public int getDiceRolls() { return numOfDiceRolls; }
	
	/*public void balanceLimitException(int bet) throws CustomExceptions
	{
		 Initial Bet: Exception is thrown when the user attempts to 
		bet an amount that is outside the valid range; initial > 1000, 
		< 0., or when betting above the balance in their account.
	}
	
	public void negativeBetException(int bet) throws CustomExceptions
	{
		Thrown when the player tries to bet < 0
	}
	
	public void negativeBalanceException(int bet, int bankBal)
	{
		//Thrown when user tries to bet with a 0 balance.
	
		if(bet == 0 || bet > bankBal) 
			throw CustomExceptions;
	}
	
	public void invalidPlayerNameException(String player) throws CustomExceptions
	{
		Thrown when the player attempts to enter a single space.
		
	}
	
	public void unknownAnswerException() throws CustomExceptions
	{
		Thrown when the player attempts to enter an invalid answer 
		 * to betting again or replaying the game.
	}
	*/
	
	// Boolean method with the logic for playing the game.
	public boolean playGame() throws GameExceptions
	{	
		System.out.println("[" + nameCheck + "]");
		if(nameCheck.equals(" ") || nameCheck.equals(""))
		{
			throw new InvalidPlayerNameException("Invalid: Please enter name.");
		} else
		{
			System.out.println("Here");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		while(true) 
		{	
			/* Seed and generate the random die roll2. Display the 
			number rolled to the user.*/			
			int die1 = rand1.nextInt(6) + 1;
			int die2 = rand2.nextInt(6) + 1;
			int diceRoll = die1 + die2;
			
			System.out.println("-> Rolled a " + diceRoll);
			
			// Call to increment the number of dice rolls.
			incrementDiceRolls();
			
			// If point number equals 0, it's the 1st roll of the simulation.
			if(pointNumber == 0) 
			{	
				// If 7 or 11 is rolled . . .
				if(diceRoll == 7 || diceRoll == 11) 
				{	
					// Increment the natural dice roll count. 
					gameStats.incrementNaturalRollsTotal();
					
					// Display to the user they rolled a natural & they won.
					System.out.println("\n*******Natural. You win!********");
					
					return true; /* Returns true allowing the craps game object
					 			 to update the bank balance and other statistics.*/	
				}
				// Else the user rolled a 2, 3 or a 12 . . .
				else if(diceRoll == 2 || diceRoll == 3 || diceRoll == 12 ) 
				{
					// Increment the craps dice roll count.
					gameStats.incrementCrapsRollsTotal();
					
					// Display to the user that got craps and they lost.
					System.out.println("\n********Craps. You lose!********");
					
					return false; /* Returns false allowing the craps game object
		 			 to update the bank balance and other statistics.*/	
				}
				else	// Else the initial roll is the point number.
					
					pointNumber = diceRoll;
			}
			else 	// Else this is not the first roll . . .
			{
				if(diceRoll == 7)	// If a 7 is rolled, it was 
				{					// after the point number . . .
					// Display that the player lost.
					System.out.println("\n*******Crap out. You lose!******");
					return false; 	// Stop the while statement.
				}
				else if(pointNumber == diceRoll) // Else the rolled the point number.
				{
					// Display that the player one.
					System.out.println("\n***Rolled the point, you win!***");
					return true; 	// Continue the while statement.
				} 
			}
		}
	}
	
	
	
	
}
