
/** Dealer
 * The Dealer class is responsible for managing the cards in the deck during gameplay. 
 * It consists of a single attribute m_deck of type Deck. It consists of the following methods:
 * default constructor, deals method, size method, toString method. 
 * A default constructor that initializes m_deck to a new Deck of 52 cards
 * @author Gabriella (Gabi) Bekhrad
 * @version 1.0
 */
import java.util.LinkedList;

public class Dealer {
  /** Attribute m_deck is of type Deck, single attribute of the Dealer class */
  public Deck m_deck; // creates fresh deck

  /**
   * Default constructor
   * which initializes m_deck to a new Deck of 52 cards
   */
  public Dealer() {
    m_deck = new Deck();
  }

  /**
   * deals method
   * 
   * @param n the size of the deck
   * @return LinkedList consisting of n cards dealt randomly from the deck. If the
   *         deck is empty, it returns a LinkedList of length 0.
   */
  public LinkedList<Card> deals(int n) {
    LinkedList<Card> dealtCards = new LinkedList<Card>();

    if (m_deck.size() == 0) {
      return dealtCards;
    }

    for (int i = 0; i < n; ++i) {
      // dealtCards.add(m_deck.get(i).deal());
      dealtCards.add(m_deck.deal());
    }
    return dealtCards;
  }

  /**
   * size method
   * which returns the number of cards in m_deck
   */
  public int size() {
    return m_deck.size(); // uses size method from Deck class
  }

  /**
   * toString method
   * calls toString on m_deck and returns the result
   */
  public String toString() {
    return m_deck.toString();
  }
}
