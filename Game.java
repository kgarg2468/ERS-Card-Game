
import java.util.LinkedList;
import java.util.Random;

public class Game {
    private LinkedList<Player> players;
    private LinkedList<Card> pile;
    private Dealer dealer;
    private String[] patterns = {"doubles", "sandwich", "top bottom"};
    private Random random = new Random();

    //default constructor starting with 2 players
    public Game() {
        this(2);
    }

    //overloaded constructor for custom amount of people
    public Game(int numPlayers) {
        players = new LinkedList<>();
        pile = new LinkedList<>();
        dealer = new Dealer();

        //give cards to people
        LinkedList<Card> deck = new LinkedList<>();
        for (int i = 0; i < numPlayers; i++) {
            //give each player some amount of the deck and a pattern
            LinkedList<Card> hand = dealer.deals(dealer.size() / (numPlayers - i));
            String pattern = patterns[random.nextInt(patterns.length)];
            players.add(new Player(i + 1, hand, pattern));
        }
    }

    //returns next player in rotation
    private Player nextPlayer(Player current) {
        int index = players.indexOf(current);
        return players.get((index + 1) % players.size());
    }

    //game
    public int play() {
        System.out.println("Starting game with " + players.size() + " players!");
        Player currentPlayer = players.get(0);

        while (players.size() > 1) {
            System.out.println("\nCurrent player: " + currentPlayer.getPlayerNum());
            Card playedCard = currentPlayer.playCard();

            if (playedCard != null) {
                pile.add(playedCard);
                System.out.println("Played card: " + playedCard);
                System.out.println("Pile: " + pile);

                //face card 
                if (playedCard.getRank() >= Card.JACK) {
                    int chances = getChances(playedCard.getRank());
                    currentPlayer = handleFaceCard(currentPlayer, chances);
                } else {
                    currentPlayer = nextPlayer(currentPlayer);
                }

                //slapping 
                for (Player player : players) {
                    if (player.slaps(pile)) {
                        System.out.println("Player " + player.getPlayerNum() + " slapped the pile!");
                        player.getHand().addAll(pile);
                        pile.clear();
                        currentPlayer = player;
                        break;
                    }
                }
            } else {
                System.out.println("Player " + currentPlayer.getPlayerNum() + " has no more cards!");
                players.remove(currentPlayer);
                if (!players.isEmpty()) {
                    currentPlayer = nextPlayer(currentPlayer);
                }
            }
        }

        System.out.println("Player " + players.get(0).getPlayerNum() + " wins the game!");
        return players.get(0).getPlayerNum();
    }

    //face card 
    private Player handleFaceCard(Player currentPlayer, int chances) {
        Player nextPlayer = nextPlayer(currentPlayer);
        for (int i = 0; i < chances; i++) {
            Card playedCard = nextPlayer.playCard();
            if (playedCard != null) {
                pile.add(playedCard);
                System.out.println("Played card: " + playedCard);
                if (playedCard.getRank() >= Card.JACK) {
                    return handleFaceCard(nextPlayer, getChances(playedCard.getRank()));
                }
            } else {
                players.remove(nextPlayer);
                return currentPlayer;
            }
        }
        currentPlayer.getHand().addAll(pile);
        pile.clear();
        return currentPlayer;
    }

    //num of chances based on card rank
    private int getChances(int rank) {
        switch (rank) {
            case Card.JACK:
                return 1;
            case Card.QUEEN:
                return 2;
            case Card.KING:
                return 3;
            case Card.ACE:
                return 4;
            default:
                return 0;
        }
    }

    //pattern check methods
    public static boolean doubles(LinkedList<Card> pile) {
        if (pile.size() < 2) return false;
        return pile.getLast().getRank() == pile.get(pile.size() - 2).getRank();
    }

    public static boolean sandwich(LinkedList<Card> pile) {
        if (pile.size() < 3) return false;
        return pile.getLast().getRank() == pile.get(pile.size() - 3).getRank();
    }

    public static boolean topBottom(LinkedList<Card> pile) {
        if (pile.size() < 2) return false;
        return pile.getLast().getRank() == pile.getFirst().getRank();
    }
}
