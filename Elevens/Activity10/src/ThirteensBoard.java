import java.util.List;
import java.util.ArrayList;

/**
 * The ThirteensBoard class represents the board in a game of Thirteens.
 */
public class ThirteensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 10;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;


	/**
	 * Creates a new <code>ThirteensBoard</code> instance.
	 */
	 public ThirteensBoard() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Thirteens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 13, or (2) a single card is king
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		int sum = 0;

		if(selectedCards.size() > 2)
		    return false;

		for(int selectedCard: selectedCards) {
		    try {
                sum += cardAt(selectedCard).pointValue();
            } catch(NullPointerException ex) {
		        System.out.print("Nullpointerexception at "+selectedCard);
            }
        }

		return (sum == 13);
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Thirteens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 13, or (2) a king
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
	    List<Integer> cIndexes = cardIndexes();
	    boolean isLegalPairSum = false;

        for(int i=0, size=cIndexes.size(); !isLegalPairSum && i<size; i++) {
            try {
                if(cardAt(cIndexes.get(i)).pointValue() == 13) {
                    isLegalPairSum = true;
                }
            } catch(NullPointerException ex) {
                System.out.print("Nullpointerexception at i="+i);
            }
            for(int j=0; !isLegalPairSum && j<size; j+=(j==i-1)?2:1) {
                List<Integer> pair = new ArrayList<>();
                pair.add(cIndexes.get(i));
                pair.add(cIndexes.get(j));
                isLegalPairSum = isLegal(pair);
            }
        }

        return isLegalPairSum;
	}

	/**
	 * Check for an 13-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 13-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	private boolean containsPairSum13(List<Integer> selectedCards) {
        for(int i=0, size=selectedCards.size(); i<size; i++) {
            for(int j=0; j<size; j += (j-1==i)?2:1) {
                List<Integer> pair = new ArrayList<>();
                pair.add(i);
                pair.add(j);
                if(isLegal(pair))
                    return true;
            }
        }

        return false;
	}

	/**
	 * Check for a King in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a King.
	 * @return true if the board entries in selectedCards
	 *              include a king; false otherwise.
	 */
	private boolean containsKing(List<Integer> selectedCards) {
		for(int selectedCard: selectedCards)
		    if(cardAt(selectedCard).pointValue() == 13)
		        return true;

		return false;
	}
}
