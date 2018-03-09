/**
 * This is a class that tests the Deck class.
 */
public class DeckTester {

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
	    Deck[] decks = {
	            new Deck(
                        new String[]{"Jack", "Queen", "King"},
                        new String[]{"Spades", "Diamonds"},
                        new int[]{11, 12, 13}
                ),
                new Deck(
                        new String[]{"Ace", "Two", "Three"},
                        new String[]{"Hearts", "Diamonds", "Spades", "Clubs"},
                        new int[]{1, 2, 3}
                ),
                new Deck(
                        new String[]{"Six", "Seven", "Eight"},
                        new String[]{"Clubs"},
                        new int[]{6, 7, 8}
                )
        };

        for(int i=0; i<decks.length; i++) {
            System.out.println("Deck "+i);
            while(!decks[i].isEmpty()) {
                System.out.printf("decks[%d].size() -> %d\n", i, decks[i].size());
                System.out.printf("deal[%d].deal() -> %s\n", i, decks[i].deal());
            }
            System.out.println();
        }
    }
}
