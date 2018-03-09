/**
 * This is a class that tests the Card class.
 */
public class CardTester {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		Card[] cards = {
				new Card("King", "Hearts", 13),
				new Card("Ace", "Spades", 1),
				new Card("Queen", "Diamonds", 12)
		};

		for(int i=0; i<cards.length; i++) {
			int otherCardIndex = (i+1)%cards.length;
			System.out.printf("cards[%d] == cards[%d] ? -> %s\n", i, otherCardIndex, cards[i].matches(cards[otherCardIndex]) ? "true" : "false");
			System.out.printf("cards[%d].suit() -> %s\n", i, cards[i].suit());
			System.out.printf("cards[%d].rank() -> %s\n", i, cards[i].rank());
			System.out.printf("cards[%d].pointValue() -> %d\n", i, cards[i].pointValue());
			System.out.printf("cards[%d].toString() -> %s\n\n", i, cards[i].toString());
		}
	}
}
