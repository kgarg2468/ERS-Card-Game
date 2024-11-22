
import java.util.LinkedList;

public class Dealer {
    private Deck m_deck;

    public Dealer() {
        m_deck = new Deck();
    }

    //deals the cards
    public LinkedList<Card> deals(int n) {
        LinkedList<Card> dealtCards = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Card card = m_deck.deal();
            if (card != null) {
                dealtCards.add(card);
            }
        }
        return dealtCards;
    }

    //info method to get size of deck
    public int size() {
        return m_deck.size();
    }

    //putting info into words using toString method
    public String toString() {
        return m_deck.toString();
    }
}
