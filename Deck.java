

import java.util.LinkedList;
import java.util.Random;

public class Deck {
    private LinkedList<Card> m_cards;


    //defualt constructor to create deck
    public Deck() {
        m_cards = new LinkedList<>();
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 2; rank <= 14; rank++) {
                m_cards.add(new Card(rank, suit));
            }
        }
    }

    // copy constructor
    public Deck(Deck other) {
        m_cards = new LinkedList<>();
        for (Card card : other.m_cards) {
            m_cards.add(new Card(card));
        }
    }

    //converting info to words
    public String toString() {
        return m_cards.toString();
    }

    //info method to get size of deck
    public int size() {
        return m_cards.size();
    }

    //to deal random cards
    public Card deal() {
        if (m_cards.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        return m_cards.remove(rand.nextInt(m_cards.size()));
    }
}
