/*Name:		Cheri Nadell (ID 79778391)
 *Lab2:		This lab will implement a simulator of a simpler version of
 *			the dice game Craps, which is called the "Passline Bet."
 *Class:	CrapsSimulation. This class represents all the information 
 *			for the Simulation.
***********************************************************************/

import java.util.Scanner;

public class CrapsSimulation 
{
	// Declare class variables.
	private CrapsGame crapsGame;  				// Create a Craps Game Object.
	private CrapsMetricsMonitor monitor = 
				new CrapsMetricsMonitor();  	// Construct a Craps Metrics Monitor Object.
	private Scanner readIn = 
			new Scanner(System.in); 			// Construct a Scanner object.
	private static String playerName, playAgain;           // Name of the craps player.
    private static int bankBalance, initialBet;  // Set up Initial Bank Balance and bet amount
	private int currentWinStreak, currentLoseStreak;	// number of games won, and game lost.
    private String finalResponse;

	// Constructor.
	public CrapsSimulation() 
	{	
		// Initialize class variables.
		playerName = null;
		bankBalance = 0;
		initialBet= 0;
		playAgain = "y";
		currentWinStreak = 0;
		currentLoseStreak = 0;
	}
	
	// Incrementing method for player's winning streaks.
	public void incrementCurrentWinStreak() 
	{
		// Increment 
		++this.currentWinStreak;
		this.currentLoseStreak = 0;
	}
	
	// Incrementing method for the player's losing streaks.
	public void incrementCurrentLoseStreak() 
	{
		++this.currentLoseStreak;
		this.currentWinStreak = 0;
	}
	
	public String getName() { return this.playerName;}
	
	public static void setName(String playerName)
	{
		CrapsSimulation.playerName = playerName;
	}

	public static void setBankBalance(int balance)
    {
        CrapsSimulation.bankBalance = balance;
    }

    public static int getBankBalance()
    {
        return CrapsSimulation.bankBalance;
    }

    public static void setInitialBet(int initialBet)
    {
        CrapsSimulation.initialBet = initialBet;
    }
	// Method to start the simulation.
	public void start() throws GameExceptions
	{ 
		// Welcome the user, ask for their name and read it into the Scanner.
		System.out.print(
				"\n****************************************\n" +
				"*           WELCOME TO SIMCRAPS!       *\n" +
				"****************************************\n");
		System.out.print("Please enter your player name: ");
		this.setName(readIn.nextLine());
		
		// Prompt the user to enter the total amount of money they will be playing with.
		System.out.print("Hello " + playerName + "!\nPlease enter the amount " +
				"of money you're\nbringing to the table: $");
		
		bankBalance = readIn.nextInt(); // Initialize a variable to hold the user input.
		
		while(bankBalance != 0) { 		// While user still has money, keep playing.
			
			crapsGame = new CrapsGame(monitor, playerName, bankBalance);	// Resets dice before each game.
			
			if(crapsGame.playGame()) // If playGame is true. 
			{
				// Player won; double the bet and add to the player's balance.
				bankBalance += (initialBet * 2);
				monitor.incrementTotalGamesWon();// Increment number of games won.
				incrementCurrentWinStreak();	 // Increment winning streaks.		
			}
			else // Else the Player lost.
			{
			    if (crapsGame.getPlayAgain().equals("n")) break;
				monitor.incrementTotalGamesLost();// Increment games lost.
				incrementCurrentLoseStreak(); 	  // Increment losing streaks.
			}

            if (crapsGame.getPlayAgain().equals("n")) break;
			// Increment the number of games played.
			monitor.incrementTotalGamesPlayed();  
			
			/*If the number of dice rolls in the current game is > then what
			 *is currently held in the CrapsMetricsMonitor stats . . . */
			if(crapsGame.getDiceRolls() > monitor.getMaxRollsInAGame()) 
			{
				/*Replace the current stats for max rolls in a game 
				 * with the new max rolls number.*/
				monitor.setMaxRollsInAGame(crapsGame.getDiceRolls());
			}
			/*If the number of wins in the current game is > then what
			 *is currently held in the CrapsMetricsMonitor stats . . .*/
			if(this.currentWinStreak > monitor.getMaxWinStreak()) 
			{
				 /*Replace the current stats for win streaks in a game 
				 * with the new number.*/
				monitor.setMaxWinStreak(this.currentWinStreak);
			}
			/*If the number of losses in the current game is > then what
			 *is currently held in the CrapsMetricsMonitor stats . . .*/
			if(this.currentLoseStreak > monitor.getMaxLoseStreak()) 
			{
				/*Replace the current stats for losses in a game with 
				 *the new number.*/
				monitor.setMaxLoseStreak(this.currentLoseStreak);
			}
			/*If the new bank balance is > than the max balance held in 
			 stats . . . */
			if(bankBalance > monitor.getMaxBalInGame()) 
			{
				 /*Replace the current stats for max balances in a game 
				 * with the new number. Then get the game number where 
				 * the highest balance was achieved.*/
				monitor.setMaxBalInGame(bankBalance);
				monitor.setTotalGamesToMaxBal(monitor.getTotalGamesPlayed());
			}
			// Display the player's name and their current balance.
			System.out.println("\n" + playerName + "'s balance $" + bankBalance);



		}
		
		monitor.printStatistics();	// Display the simulation statistics.

        readIn = new Scanner(System.in);
		// Ask the user if they want to replay the simulation.
		System.out.println("Would you like to replay The Stupid SimCraps?\n('y' or 'n'): ");

		finalResponse = readIn.nextLine();	// Read in their response.
        try
        {
            unknownAnswerException(finalResponse);
        }
        catch(UnknownAnswerException ae)
        {
            System.out.println(ae.getMessage());
            askAgain();
        }

		if(finalResponse.equals("y"))	// If they do . . .
		{	
			monitor = monitor.reset();	  // Reset variables and the stats.
            CrapsGame.resetPlayed();
			new CrapsSimulation().start();// Start a new simulation.
		}
		else	// Else end the program by displaying "Bye" to the player.
			System.out.print("\nBYE!");
	
		readIn.close();	// Close the scanner object.
	}

    public void askAgain() throws GameExceptions
    {
        String temp;
        System.out.println("Would you like to replay SimCraps?\n('y' or 'n'): ");
        temp = readIn.nextLine();
        try
        {
            unknownAnswerException(temp);
            finalResponse = temp;
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
}