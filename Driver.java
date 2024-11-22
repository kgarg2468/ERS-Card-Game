
public class Driver {
    public static void main(String[] args) {
        //make a game with 3 players
        Game game = new Game(3);

        //start the game to find out who wins
        int winner = game.play();

        //print statemnet to mention who the winner is
        System.out.println("\nA pat on the back to " + winner + " for winning the game!");
    }
}
