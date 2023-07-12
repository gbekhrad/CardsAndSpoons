/**
 * A class that stores information about a single Card.
 * Includes private member variables for the Card.
 * The value of the card, 1-14, and the suit of the Card.
 * includes toString method, accessors and mutators, default, overloaded, and
 * copy contructors, and equals method.
 * 
 * @author Gabriella (Gabi) Bekhrad
 * @version 1.0
 */

public class Card {

  /**
   * value of the card (2-10, J, Q, K, A) stored as integers
   */
  private int value;
  /**
   * type of suit (hearts, spades, clubs, diamonds) -
   * 0 = hearts, 1 = spades, 3 = clubs, 4 = diamonds
   */
  private int suit;

  /**
   * final values of constants to be used to set the suit and value of face cards
   * these constants will ensure consistency across all classes where Cards are
   * created in your program
   * will help prevent bugs created by accidentally using the incorrect integer
   * value for an expected suit
   */
  public static final int HEARTS = 0;
  public static final int SPADES = 1;
  public static final int CLUBS = 2;
  public static final int DIAMONDS = 3;

  public static final int JACK = 11;
  public static final int QUEEN = 12;
  public static final int KING = 13;
  public static final int ACE = 14;

  /**
   * Accessor for the int representation of the Card suit
   * 
   * @return the int representation of the Card's suit
   */
  public int getSuit() {
    return this.suit;
  }

  /**
   * Mutator for the int representation of the Card suit
   * 
   * @param newSuit the new int representation of the suit
   */
  public void setSuit(int newSuit) {
    this.suit = newSuit;
  }

  /**
   * Accessor for the int representation of the Card value
   * 
   * @return the int representation of the Card's value
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Mutator for the int representation of the Card value
   * 
   * @param newValue the new int representation of the value
   */
  public void setValue(int newValue) {
    this.value = newValue;
  }

  /**
   * Default constructor
   * creates an ace of spades
   */
  public Card() {
    this.value = 14; // sets to an ace (value of 0)
    this.suit = 1; // sets to spades
  }

  /**
   * Overloaded constructor
   * sets member variables for a Card to be created
   * 
   * @param value integer representation of the numerical value on the card
   * @param suit  integer representation of the suit of the card
   */
  public Card(int value, int suit) {
    this.value = value;
    this.suit = suit;
  }

  /**
   * Copy constructor
   * which makes a deep copy of Card having their own references
   * 
   * @param cardToCopy Card Object of which to copy
   */
  public Card(Card cardToCopy) {
    this.value = cardToCopy.value;
    this.suit = cardToCopy.suit;
  }

  /**
   * toString method
   * nicely displays the suit and rank of the card.
   * suits and face cards are represented textually (not by their integer values)
   * 
   * @return String formatted to the card values and suit textually
   */
  public String toString() {
    String textualSuit = "";
    // the following sets certain suits to their textual representations
    if (suit == 0) {
      textualSuit = "Hearts";
    } else if (suit == 1) {
      textualSuit = "Spades";
    } else if (suit == 2) {
      textualSuit = "Clubs";
    } else if (suit == 3) {
      textualSuit = "Diamonds";
    }

    String textualValue = "";
    // the following sets certain values to their textual representations
    if (value < 11) { // if theyre not special, just take the number itself
      textualValue = String.valueOf(value);
    } else if (value == 11) {
      textualValue = "Jack";
    } else if (value == 12) {
      textualValue = "Queen";
    } else if (value == 13) {
      textualValue = "King";
    } else if (value == 14) {
      textualValue = "Ace";
    }

    return textualValue + " of " + textualSuit; // returns formatted card
  }

  /**
   * Equals method
   * assume 2 cards are equal if their values are equal
   */
  public boolean equals(Card c) {
    if ((c.getValue()) == (this.getValue())) {
      // if the values are equal
      return true;
    } else { // if they do not equal
      return false;
    }
  }
}