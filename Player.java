import java.util.*;

/**
 * Player
 * Player consists of the following member variables: playerNum, hand, hasSpoon
 * and the following methods: 2 overloaded constructors, takeTurn, stealSpoon,
 * and accessors for all member variables
 * 
 * @author Gabriella (Gabi) Bekhrad
 * @version 1.0
 */

public class Player {
  Random randy = new Random(); // for passing along cards

  /** playerNum of type integer (1, 2, etc) to identify which player it is */
  private int playerNum;

  /** hand of type LinkedList which represents the cards in their hand */
  private LinkedList<Card> hand = new LinkedList<Card>();

  /**
   * hasSpoon of type boolean which represents whether or not that Player has a
   * spoon.
   */
  private boolean hasSpoon;

  /**
   * Overloaded constructor 1
   * this constructor retrieves 4 cards from the Dealer and place them in the
   * LinkedList
   * representing the player’s hand, so every player starts the game with 4 cards.
   * 
   * @param playerNum  int value representing which player it is
   * @param gameDealer reference to the game's dealer
   */
  public Player(int playerNum, Dealer gameDealer) {
    this.playerNum = playerNum; // gets the playerNumber passed in
    LinkedList<Card> defaultHand = gameDealer.deals(4); // stores the dealt card in defaultHand
    for (int i = 0; i < 4; i++) { // this copies the cards that have been dealt over to the hand
      hand.add(defaultHand.get(i));
    }
  }

  /**
   * Overloaded constructor 2
   * 
   * @param playerNum   int value representing which player it is
   * @param playerCards LinkedList of Cards representing their entire hand
   */
  public Player(int playerNum, LinkedList<Card> hand) {
    this.playerNum = playerNum;
    this.hand = hand;
    this.hasSpoon = false;
  }

  /**
   * printHand method
   * custom method to print the player's hand
   * 
   * @return the LinkedList of the player's hand
   */
  public LinkedList<Card> printHand() {
    // System.out.println(hand.get(0).getValue());
    return hand;
  }

  /**
   * method takeTurn
   * receives the card that is passed from the player to the left (or the deck)
   * and returns a card that has been chosen to be passed along. 
   * 
   * The “decision” of which card to pass uses the following algorithm:
   * - If the drawn card matches 1+ cards in their hand, they keep that card
   * - If there are more than one sets of equal matching cards, the matches with
   * the highest number of cards is what you will keep
   * (3 Kings and 1 Jack in your hand and you get passed a Jack, you will keep the
   * 3 Kings and pass along the Jack even though it matches)
   * - If no cards match yet, there is an even probability for each card that it
   * will be the one to be passed (implemented with random)
   * 
   * @param passedCard the card that is passed from the player (their deck) from
   *                   the left
   * @return Card that is passed along to the next person
   */
  public Card takeTurn(Card passedCard) {
    LinkedList<Card> potentialPasses = new LinkedList<Card>(); // empty linked list of things to pass along
    LinkedList<Card> potentialKeeps = new LinkedList<Card>();

    Card cardToPass = passedCard; // the card that is passed along

    System.out.println("Player was passed " + passedCard + "."); // reads out the card that the player was passed
    hand.add(passedCard); // add the card to the player's hand

    for (int i = 0; i < 5; i++) { // for each cards in the hand
      for (int j = 0; j < 5; j++) {
        if ((hand.get(i).equals(hand.get(j))) && (hand.get(i).getSuit() != hand.get(j).getSuit())) {
          if (!(potentialKeeps.contains(hand.get(i)))) {
            potentialKeeps.add(hand.get(i));
            potentialKeeps.add(hand.get(j));
          }
        } else if ((hand.get(i).equals(hand.get(j))) && (hand.get(i).getSuit() == hand.get(j).getSuit())) { // if its
                                                                                                            // the card
                                                                                                            // itself
          /* CODE START https://stackoverflow.com/questions/22493765/java-do-nothing */
          ;
          /* CODE END https://stackoverflow.com/questions/22493765/java-do-nothing */
        } else { // if they just dont match
          /* CODE START https://stackoverflow.com/questions/22493765/java-do-nothing */
          ;
          /* CODE END https://stackoverflow.com/questions/22493765/java-do-nothing */
        }
      }
    }

    /* this creates a list of potential cards to keep 
    by sorting through the hand and figuring out what is not in the passes
    if not in passes, add to keeps */
    for (int c = 0; c < hand.size(); c++) {
      if (!(potentialKeeps.contains(hand.get(c)))) {
        potentialPasses.add(hand.get(c));
      }
    } 

    // decide whether to keep or pass a card
    if (potentialPasses.size() == 0) {
      cardToPass = passedCard;
      /* COLLABORATION START WITH DYLAN BARLAVA ID: 2428584 */
    } else if (potentialPasses.size() == 3) {
      Card temp1 = potentialPasses.get(0);
      Card temp2 = potentialPasses.get(1);
      Card temp3 = potentialPasses.get(2);
      if (temp1.equals(temp2) && temp2.equals(temp3)) { // checks to see if the three cards in there are equal
        System.out.println("Passed: " + passedCard);
        System.out.println();
        cardToPass = passedCard;

      } else { // if they are not equal it will pass a random card from possible pass
        if (temp1.equals(temp2)) {
          cardToPass = temp2;

        } else if (temp2.equals(temp3)) {
          cardToPass = temp1;

        } else if (temp3.equals(temp1)) {
          cardToPass = temp2;

        } else {
          Random randy = new Random();
          int index = randy.nextInt(potentialPasses.size());
          Card pass = potentialPasses.get(index);
          if (pass.equals(passedCard)) {
            cardToPass = pass;
            cardToPass = pass;
          } else {
            cardToPass = pass;
          }
        }
      }
    } else if (potentialPasses.size() == 4) {
      return cardToPass;
    } else {
      int chance = randy.nextInt(potentialPasses.size());
      Card newCard = potentialPasses.get(chance);
      cardToPass = newCard;
    }
    /* COLLABORATION END WITH DYLAN BARLAVA ID: 2428584 */

    // before the card is passed on, make sure the player can steal a spoon!
    if (hasSpoon == true) { // if they have a spoon already
      System.out.println("Player already has a spoon.");
    } else if (
      (hand.get(0).equals(hand.get(1)) &&
       hand.get(0).equals(hand.get(2)) &&
       hand.get(0).equals(hand.get(3)))){
          // checks for equivilancy of values
          System.out.println("Player " + playerNum + " has four of a kind!");
          stealSpoon();
          System.out.println("Player " + playerNum + " has stolen a spoon. " + Game.nSpoons + " spoon(s) remain.");
    } else if ((Game.nSpoons < Game.startingSpoons)) { // if a spoon has been stolen
        int chance = randy.nextInt(3); // 33% chance generator
        if (chance == 0) { // if it lands on 0
          // they notice the spoon and take it
          System.out.println("Player " + playerNum + " noticed a missing spoon and stole one.");
          stealSpoon();
          System.out.println(Game.nSpoons + " spoon(s) remain.");
        } else {
          System.out.println("Player " + playerNum + " did not notice a missing spoon!");
        }
      }
    
    // this removes the card they are passing from the hand and returns it 
    hand.remove(cardToPass);
    return cardToPass;
  }

  /**
   * method stealSpoon
   * executes either when the first person receives 4 of a kind, or when a spoon
   * has been stolen and the other Players “notice” (33% chance of them noticing)
   * that a spoon is gone and steal their own spoon.
   * This should set their boolean member variable to true and decrease the number
   * of remaining spoons in the game by 1.
   *
   */
  public void stealSpoon() {
    /*
     * BEGIN CODE FROM SOURCE:
     * https://stackoverflow.com/questions/10279553/accesing-static-variable-from-
     * another-class-in-java
     */
    Game.nSpoons--;
    /*
     * END CODE FROM SOURCE:
     * https://stackoverflow.com/questions/10279553/accesing-static-variable-from-
     * another-class-in-java
     */
    this.hasSpoon = true;
  }

  /**
   * Accessor method for playerNum member variable
   * 
   * @return int playerNum, representing which player it is
   */
  public int getPlayerNum() {
    return playerNum;
  }

  /**
   * Accessor method for hand member variable
   * 
   * @return LinkedList of Cards, representing hand
   */
  public LinkedList<Card> getHand() {
    return hand;
  }

  /**
   * Accessor method for hasSpoon member variable
   * 
   * @return boolean whether the user has a spoon
   */
  public boolean hasSpoon() {
    return hasSpoon;
  }
}