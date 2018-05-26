/*Name:		Cheri Nadell (ID 79778391)
 *Lab2:		This lab will implement a simulator of a simpler version of
 *			the dice game Craps, which is called the "Passline Bet."
 *Class:	CrapsGame. This class represents all the information for a
 *			SINGLE craps game.
***********************************************************************/
import java.util.Random; 	// Import the random number generator
import java.util.Scanner;

public class CrapsGame 
{	
	// Declare class variables.
    private String nameCheck;
    private static String playAgain;         // Holds whether or not the user wants to play again
    private int checkBalance;
    private static boolean played = false;
    private int numOfDiceRolls;				// Total number of dice rolls.
    private int initialBet;                 // Initial Bet
	int pointNumber;  						// Initial roll if not craps or natural.
	private CrapsMetricsMonitor gameStats;	// CrapsMetricsMonitor object.
	private Scanner readIn =
			new Scanner(System.in);         //Scanner object to take in questions
	private Random rand1 = new Random();		// Create a Random object for dice rolling.
	private Random rand2 = new Random();		// Create a second Random object for the second dice.


	
	// Constructor
	public CrapsGame() { numOfDiceRolls = 0; }

	// Parameterized Constructor
	public CrapsGame(CrapsMetricsMonitor monitorObject, String name, int bankBalance)
	{
		this.pointNumber = 0;				// Initialize point number flag for the 1st roll.
		this.numOfDiceRolls = 0;            // Initialize the number of dice rolls.
        this.initialBet = 0;
		this.gameStats = monitorObject;		// Initialize the CrapsMetricsMonitor Object.
		nameCheck = name;
		checkBalance = bankBalance;
		playAgain = "t";
	}
	 
	// Increments the number of dice rolls that have occurred in the current game.
	public void incrementDiceRolls() { ++this.numOfDiceRolls;} 
	
	// Getter method for the number of dice rolls.
	public int getDiceRolls() { return numOfDiceRolls; }

	public void setCheckBalance(int testBalance)
    {
        checkBalance = testBalance;
    }

	public void changeBet() throws GameExceptions
    {
        System.out.print("Enter your passline bet (must be\nbetween $1 and $" +
                checkBalance + "): $");
        initialBet = readIn.nextInt(); // Set initalBet to the new amount.
        try
        {
            balanceLimitException(initialBet);
            negativeBetException(initialBet);
            CrapsSimulation.setInitialBet(initialBet);
            CrapsSimulation.setBankBalance(CrapsSimulation.getBankBalance() - initialBet);
            System.out.println("\n" + this.nameCheck + " bets $" + initialBet);
        }
        catch (BalanceLimitException ae)
        {
            System.out.println(ae.getMessage());
            changeBet();
        }
        catch (NegativeBetException ae2)
        {
            System.out.println(ae2.getMessage());
            changeBet();
        }
    }

	public void balanceLimitException(int bet) throws GameExceptions
	{
		 if (bet == 0)
         {
             throw new BalanceLimitException("Invalid Bet! You cannot bet nothing.");
         }
         else if (bet > checkBalance)
         {
             throw new BalanceLimitException("Invalid Bet! You cannot bet more money than you have.");
         }
	}
	
	public void negativeBetException(int bet) throws GameExceptions
	{
		if(bet < 0)
        {
            throw new NegativeBetException("Invalid Bet! You cannot bet a negative number!");
        }
	}

	public static void resetPlayed()
    {
        played = false;
    }

	public void changeBalance() throws GameExceptions
    {
	    //Allows the player to change the balance that they start with (if the original was a negative)
        System.out.println("Please enter how much money you are going to bring to the table");
        checkBalance = readIn.nextInt();
        try
        {
            negativeBalanceException(checkBalance);
            CrapsSimulation.setBankBalance(checkBalance);
        }
        catch(NegativeBalanceException ae)
        {
            System.out.println(ae.getMessage());
            changeBalance();
        }
    }

	public void negativeBalanceException(int bankBal) throws GameExceptions
	{
		//Thrown when user tries to start the game with a negative Balance.

		if(bankBal < 0) throw new NegativeBalanceException("You cannot come to the table with a negative balance");

	}

	public void changeName() throws GameExceptions
    {
        System.out.println("Please enter another name: ");
        nameCheck = readIn.nextLine();
        try
        {
            invalidPlayerNameException(nameCheck);
            CrapsSimulation.setName(nameCheck);
        }
        catch (InvalidPlayerNameException ae)
        {
            System.out.println(ae.getMessage());
            changeName();
        }

    }

	public void invalidPlayerNameException(String nameCheck) throws GameExceptions
	{
        if(nameCheck.equals(" ") || nameCheck.equals(""))
        {
            throw new InvalidPlayerNameException("Invalid Player Name Detected.");
        }
	}

	public void askAgain() throws GameExceptions
    {
        System.out.println("Would you like to play again? Please enter 'y' or 'n': ");
        playAgain = readIn.nextLine();
        try
        {
            unknownAnswerException(playAgain);
        }
        catch (UnknownAnswerException ae)
        {
            System.out.println(ae.getMessage());
            askAgain();
        }

    }

    public void unknownAnswerException(String response) throws GameExceptions
	{
		if(!(response.equals("y") || response.equals("n")))
        {
            throw new UnknownAnswerException( "\"" + response + "\"" +  " is not a valid response to the question.");
        }
	}

	public String getPlayAgain()
    {
        return playAgain;
    }
	
	// Boolean method with the logic for playing the game.
	public boolean playGame() throws GameExceptions
	{

	    if(played) {
            System.out.println("Would you like to play again?\n('y' or 'n'): ");
            playAgain = readIn.nextLine();
            try
            {
                unknownAnswerException(playAgain);
            }
            catch (UnknownAnswerException ae)
            {
                System.out.println(ae.getMessage());
                askAgain();
            }
            if(playAgain.equals("n")) return false;
        }

	    played = true;
		try
        {
		    invalidPlayerNameException(nameCheck);

        } catch (InvalidPlayerNameException ae)
        {
            System.out.println(ae.getMessage());
		    changeName();
        }

        try
        {
            negativeBalanceException(checkBalance);

        } catch (NegativeBalanceException ae)
        {
            System.out.println(ae.getMessage());
		    changeBalance();
        }

        // Prompt the user to enter their initial bet amount,
        System.out.print("Enter your passline bet (must be\nbetween $1 and $" +
                checkBalance + "): $");
        initialBet = readIn.nextInt(); 		// Read in their response.

        try
        {
            balanceLimitException(initialBet);
            negativeBetException(initialBet);
            CrapsSimulation.setInitialBet(initialBet);
            CrapsSimulation.setBankBalance(CrapsSimulation.getBankBalance() - initialBet);
            System.out.println("\n" + this.nameCheck + " bets $" + initialBet);
        }
        catch (BalanceLimitException ae)
        {
            System.out.println(ae.getMessage());
            changeBet();
        }
        catch (NegativeBetException ae2)
        {
            System.out.println(ae2.getMessage());
            changeBet();
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
