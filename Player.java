
import java.util.LinkedList;
import java.util.Random;

public class Player {
    private int playerNum;
    private LinkedList<Card> hand;
    private String pattern;

    //constructor method
    public Player(int playerNum, LinkedList<Card> hand, String pattern) {
        this.playerNum = playerNum;
        this.hand = hand;
        this.pattern = pattern;
    }

    //gets rid of card and returns top card
    public Card playCard() {
        if (!hand.isEmpty()) {
            return hand.removeFirst();
        }
        return null; //no more cards
    }

    public boolean slaps(LinkedList<Card> pile) {
        switch (pattern) {
            case "doubles":
                return Game.doubles(pile);
            case "sandwich":
                return Game.sandwich(pile);
            case "top bottom":
                return Game.topBottom(pile);
            default:
                return false; //no real pattern
        }
    }

    //getter method
    public int getPlayerNum() {
        return playerNum;
    }
    //getter method
    public LinkedList<Card> getHand() {
        return hand;
    }
    //getter method
    public String getPattern() {
        return pattern;
    }

    // toString method
    public String toString() {
        return "Player " + playerNum + " (Pattern: " + pattern + ") - Hand: " + hand;
    }
}
