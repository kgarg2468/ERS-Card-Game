
public class Card {
    //normal 4 kinds of cards
    public static final int HEARTS = 0;
    public static final int SPADES = 1;
    public static final int CLUBS = 2;
    public static final int DIAMONDS = 3;

    //special face cards
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

    //to differentiate between the number and symbol on cards
    private int rank;
    private int suit;


    //constructors
    public Card() {
        this.rank = ACE;
        this.suit = SPADES;
    }

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    
    //copy constructor
    public Card(Card other) {
        this.rank = other.rank;
        this.suit = other.suit;
    }

    //getter method
    public int getRank() {
        return rank;
    }
    
    //setter method
    public void setRank(int rank) {
        this.rank = rank;
    }
    //getter method
    public int getSuit() {
        return suit;
    }
    //setter method
    public void setSuit(int suit) {
        this.suit = suit;
    }

    //toString method - converting info to words
    public String toString() {
        String[] suits = {"hearts", "spades", "clubs", "diamonds"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
        return ranks[rank - 2] + " of " + suits[suit];
    }

    //equals to method 
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return rank == card.rank;
    }
}
