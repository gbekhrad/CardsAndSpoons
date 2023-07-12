import java.util.*;

/**
 * A class that simulates a game of spoons.
 * It contains the following member variables: players, dealer, nSpoons
 * It contains the following methods: default constructor, overloaded
 * constructor, play method, accessor methods
 * 
 * @author Gabriella (Gabi) Bekhrad
 * @version 1.0
 */

public class Game {
  Random randy = new Random(); // for passing along cards chance

  /**
   * players represents each Player object in order of the “circle” so they can
   * pass cards to the right
   */
  private Player[] players;

  /** dealer is a Dealer member variable that holds the game’s Deck of cards */
  private Dealer dealer = new Dealer();

  /**
   * nSpoons is a static member variable that keeps track of how many spoons are
   * left in play
   */
  public static int nSpoons;

  /** 
   * startingSpoons is a member variable that keeps track of how many spoons the game started with 
   */
  public static int startingSpoons;

  /**
   * Default constructor
   * initializes a game of 4 players
   */
  public Game() {
    players = new Player[4];
    this.dealer = new Dealer();
    for (int i = 0; i < 4; i++) {
      players[i] = new Player((i + 1), this.dealer);
    }
    nSpoons = (this.players.length - 1);
  }

  /**
   * Overloaded constructor
   * 
   * @param numPlayers the number of players
   */
  public Game(int numPlayers) {
    players = new Player[numPlayers];
    this.dealer = new Dealer();
    for (int i = 0; i < numPlayers; i++) {
      players[i] = new Player((i + 1), this.dealer);
    }
    nSpoons = (this.players.length - 1);
  }

  /**
   * play method
   * carries out the rules of the game until there are no spoons left.
   * This is the method where you will implement the 33% chance that each player
   * notices a spoon has been stolen
   * and thus steals a spoon.
   * 
   * @return integer of the Player who did not end up with a spoon (the loser).
   */
  public int play() {
    startingSpoons = nSpoons; // keep track of the spoons we started with because the game spoons will change
    
    System.out.println("The game is starting with " + players.length + " players and " + startingSpoons + " spoons.");
    
    LinkedList<Card> discardPile = new LinkedList<Card>(); // create discard pile (to be cycled through later)
    int currPlayer = -1;
    Card cardDealt = null;
    // int numMatches = 0;

    while (nSpoons != 0) {
      if (dealer.size() == 0) { // this code block handles where the dealer gets their cards
        System.out.println("The dealer is out of cards. The discard pile is being recycled.");
        cardDealt = discardPile.get(randy.nextInt(discardPile.size())); // access the dealt card and store it in
        discardPile.remove(cardDealt);                                                              // cardDealt
      } else { // once size of deck is 0, either update dealer to be discard pile or choose
               // from discard pile instead
        LinkedList<Card> listCards = this.dealer.deals(1); // card is dealt
        cardDealt = listCards.get(0); // access the dealt card and store it in cardDealt
      } // if dealer deck empty, access cards through 

      for (Player player : players) { // this loops through all of the players in the game
        System.out.println("It is now Player " + player.getPlayerNum() + "'s turn.");
        System.out.print("Player " + player.getPlayerNum() + " has these cards in their hand: ");
        System.out.println(player.printHand());

        cardDealt = player.takeTurn(cardDealt);
        System.out.println("Player passed along " + cardDealt + ".");

        currPlayer = player.getPlayerNum(); // this stores the player it's last at so in the future when game ends it
                                            // can be // returned
      }
      discardPile.add(cardDealt);
    }
    
    // System.out.println(this.dealer.toString()); // for testing purposes, prints the deck
    System.out.print("All the spoons have been stolen! The loser is player ");
    // System.out.println(discardPile); // for testing purposes, prints the discard pile
    
    return currPlayer;
  }

  /**
   * Accessor methods for member variables
   */

  public Player[] getPlayers() {
    return players;
  }

  public Dealer getDealer() {
    return dealer;
  }

  public int getSpoons(){
    return nSpoons;
  }
}