/*Name:		Cheri Nadell (ID 79778391)
 *Lab2:		This lab will implement a simulator of a simpler version of
 *			the dice game Craps, which is called the "Passline Bet."
 *Class:	CrapsMetricsMonitor. This class contains all the the 
 *			STATISTICS gathered during a single simulation. 
 ***********************************************************************/

public class CrapsMetricsMonitor 
{
	
	// Declare class variables.
	private int 
	totalGamesPlayed, totalGamesWon, 		// Total games played, total games won, 
	totalGamesLost, maxRollsInAGame,  		// total games lost, maximum number of dice rolls in a single game,
	naturalRollsTotal, crapsRollsTotal,		// natural dice roll total, craps dice roll total,
	maxWinStreak, maxLoseStreak, maxBalInGame,	// maximum games won in a row, maximum simulation balance,
	totBetsBeforeZero, totalGamesToMaxBal;	// total bets before running out of money, & total number of 
											// games when the maximum balance was achieved.
	// Constructor.
	public CrapsMetricsMonitor() 
	{
		// Initial class variables
		totalGamesPlayed = 0;	// Total number of games played.
		totalGamesWon = 0; 		// Total number of games won.
		totalGamesLost = 0;		// Total number of games lost.
		maxRollsInAGame = 0;  	// Maximum number of dice rolls for 1 game.
		naturalRollsTotal = 0;	// Total number of natural dice rolls in a simulation.
		crapsRollsTotal = 0;	// Total number of craps rolls in a simulation.
		maxWinStreak = 0;		// Maximum number of consecutive games won.
		maxLoseStreak = 0;		// Maximum number of consecutive games lost.
		maxBalInGame = 0;		// Maximum balance held for the entire simulation.		
		totBetsBeforeZero = 0;	// Total number of bets made before money ran out.
		totalGamesToMaxBal = 0;	// Game number that held the maximum balance.
	}
	
	// GETTERS
	// Returns the total number of games played.
	public int getTotalGamesPlayed() { return totalGamesPlayed; }

	// Returns the total number of games won.
	public int getTotalGamesWon() { return totalGamesWon; }

	// Returns the total number of games lost.
	public int getTotalGamesLost() { return totalGamesLost; }

	// Returns the highest number of rolls in one game.
	public int getMaxRollsInAGame() { return maxRollsInAGame; }

	// Returns the total number of natural dice rolls in the simulation.
	public int getNaturalRollsTotal() { return naturalRollsTotal; }

	// Returns the total number of craps dice rolls in the simulation.
	public int getCrapsRollsTotal() { return crapsRollsTotal; }

	// Returns the maximum number of wins in the simulation.
	public int getMaxWinStreak() { return maxWinStreak; }

	// Returns the maximum number of losses in the simulation.
	public int getMaxLoseStreak() { return maxLoseStreak; }

	// Returns maximum balance achieved in the simulation.
	public int getMaxBalInGame() { return maxBalInGame; }

	// Returns the total number of bets before running out of money.
	public int getTotBetsBeforeZero() { return totBetsBeforeZero; }

	// Returns the game number with the maximum balance in the simulation. 
	public int getTotalGamesToMaxBal() { return totalGamesToMaxBal; }

	// SETTERS.
	// Sets the number of games played in the simulation.
	public void setTotalGamesPlayed(
			int totalGamesPlayed) { this.totalGamesPlayed = totalGamesPlayed; }

	// Sets the total number of winning games in the simulation.
	public void setTotalGamesWon(int totalGamesWon) {
		this.totalGamesWon = totalGamesWon; }

	// Sets the total number of games lost in the simulation.
	public void setTotalGamesLost(
			int totalGamesLost) { this.totalGamesLost = totalGamesLost; }

	// Sets the maximum number of rolls in a game for the simulation.
	public void setMaxRollsInAGame(int maxRollsInAGame) {
		this.maxRollsInAGame = maxRollsInAGame; }

	// Sets the total number of natural dice rolls in a simulation.
	public void setNaturalRollsTotal(
			int naturalRollsTotal) { this.naturalRollsTotal = naturalRollsTotal; }
	
	// Sets the total number of craps dice rolls in a simulation.
	public void setCrapsRollsTotal(
			int crapsRollsTotal) { this.crapsRollsTotal = crapsRollsTotal; }

	// Sets the maximum number of wins in a simulation.
	public void setMaxWinStreak(int maxWinStreak) { this.maxWinStreak = maxWinStreak; }

	// Sets the maximum number of losses in a simulation.
	public void setMaxLoseStreak(int maxLoseStreak) { this.maxLoseStreak = maxLoseStreak; }

	// Sets the the maximum balance achieved in a simulation
	public void setMaxBalInGame(int maxBalInGame) { this.maxBalInGame = maxBalInGame; }

	// Sets the total number of bets that were made before a zero balance in the simulation.
	public void setTotBetsBeforeZero(
			int totBetsBeforeZero) { this.totBetsBeforeZero = totBetsBeforeZero; }

	// Sets the game number at which the maximum balance was achieved in the simulation.
	public void setTotalGamesToMaxBal(int totalGamesToMaxBal) {
		this.totalGamesToMaxBal = totalGamesToMaxBal; }

	// INCREMENTING METHODS.
	// Increment the total number of games played in the simulation.
	public void incrementTotalGamesPlayed() { ++this.totalGamesPlayed; }
	
	// Increment the total number of games won in the simulation.
	public void incrementTotalGamesWon() { ++this.totalGamesWon; }

	// Increment the total number of games lost in a simulation.
	public void incrementTotalGamesLost() {	++this.totalGamesLost; }
	
	// Increment the total number of natural rolls in the simulation.
	public void incrementNaturalRollsTotal() { ++this.naturalRollsTotal; }
	
	// Increment the total number of craps rolls in the simulation.
	public void incrementCrapsRollsTotal() { ++this.crapsRollsTotal; }
	
	// Increment the maximum balance before zero in the simulation.
	public void incrementTotBetsBeforeZero() { ++this.totBetsBeforeZero; }
	
	/* This method is called and updated throughout the simulation. At the end of 
	the simulation, it will display the statistics.*/
	public void printStatistics() 
	{  
		// Display the simulation statistics to the user.
		System.out.println(
				"\n****************************************\n" + 
				  "*         SIMULATION STATISTICS        *\n" +	
				  "****************************************\n" +		
				"\nGames played: " + totalGamesPlayed +
				"\nGames won: " + totalGamesWon +
				"\nGames lost: " + totalGamesLost +
				"\nMaximum rolls in a single game: " + 
					maxRollsInAGame +
				"\nNatural count: " + naturalRollsTotal +
				"\nCraps count: " + crapsRollsTotal +
				"\nMaximum winning streak: " + maxWinStreak +
				"\nMaximum losing streak: " + maxLoseStreak +
				"\nMaximum balance: $" + maxBalInGame +
					" during game " + totalGamesToMaxBal +
				"\n****************************************\n");
	}
	/* Resets the state of the CrapsMetricsMonitor object. Will
	be called if the user wants to start a new simulation.*/
	public CrapsMetricsMonitor reset() 
	{
		// Return a newly constructed object.
		return new CrapsMetricsMonitor();
	}	
}
