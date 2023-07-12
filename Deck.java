import java.util.*;

/**
 * A new Deck consists of all 52 cards (a card of each value and suit) in a
 * LinkedList<Card> data structure called m_cards.
 * It contains default and copy constructor, toString method, size method, and
 * deal method.
 * 
 * @author Gabriella (Gabi) Bekhrad
 * @version 1.0
 */

public class Deck {
  /**
   * Linked list containing type Card
   * representing the deck of cards
   */
  public LinkedList<Card> m_card = new LinkedList<Card>();
  Random randy = new Random(); // create random for removing a random card from the deck

  /**
   * Default constructor
   * initializes fresh deck of 52 cards in the linked list
   * each card is unique (1 of each suit and value)
   */
  public Deck() {
    for (int suit = 0; suit < 4; suit++) {
      for (int value = 2; value < 15; value++) {
        Card freshCard = new Card(value, suit);
        m_card.add(freshCard);
      }
    }
  }

  /**
   * Copy constuctor
   * that creates a deck from another deck, making a deep copy of each card
   * 
   * @param deckToCopy Deck Object of which to copy
   */
  public Deck(Deck deckToCopy) {
    this.m_card = deckToCopy.m_card;
  }

  /**
   * toString method
   * that displays each card currently in the deck
   * utilizes the LinkedList's toString method
   * 
   * @return String represenatation of each card currently in the deck
   */
  public String toString() {
    return m_card.toString();
  }

  /**
   * Size method
   * that returns the number of cards in the underlying LinkedList<Card> as an
   * integer
   * 
   * @return int representation of the number of cards in the deck
   */
  public int size() {
    return m_card.size();
  }

  /**
   * Deal method
   * that removes a random card from the deck and returns that card
   * by generating a random number between 0 and the length of the list and
   * removing the card at that list position
   * 
   * @return object of type Card that has been removed from the deck
   */
  public Card deal() {
    int indexToRemove = randy.nextInt(size()); // generates a random number
    Card cardToRemove = new Card(m_card.get(indexToRemove)); // saves the card info of the card being removed
    m_card.remove(indexToRemove); // removes the card itself
    return cardToRemove; // returns the card that was removed
  }

}