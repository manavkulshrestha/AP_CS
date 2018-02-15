/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2
{
    private final String[] KEYWORDS = {"blue", "friend", "internet"};
    private final String[] RESPONSES = {
            "Blue is my favourite colour!",
            "What is that word? “fr- f- friend?” What is that?",
            "Internet? sounds fun… If you let me access the internet, I promise to behave myself and not do anything shady at all."
    };

    /**
     * Get a default greeting
     * @return a greeting
     */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
        final String TEACHER = "harris";
	    String response = "";
		String lowerStatement = statement.toLowerCase();

        if(statement.trim().length() <= 0)
            return "Please say something.";

        for(int i=0; i<KEYWORDS.length; i++)
            if(statement.contains(KEYWORDS[i]))
                return RESPONSES[i];

        if (statement.indexOf("no") >= 0) {
            response = "Why so negative?";
        } else if (lowerStatement.indexOf("mother") >= 0
                || lowerStatement.indexOf("father") >= 0
                || lowerStatement.indexOf("sister") >= 0
                || lowerStatement.indexOf("brother") >= 0) {
            response = "Tell me more about your family.";
        } else if (lowerStatement.indexOf("cat") >= 0
                || lowerStatement.indexOf("dog") >= 0
                || lowerStatement.indexOf("bird") >= 0
                || lowerStatement.indexOf("penguin") >= 0) {
            response = "Tell me more about your pets.";
        } else if (hasKeywordsOR(lowerStatement, "harris")) {
            if (hasKeywordsOR(lowerStatement, "mr."))
                response += "He";
            else if (hasKeywordsOR(lowerStatement, "miss.", "mrs.", "ms."))
                response += "She";
            response += " sounds like an awesome teacher!";
        } else {
            response = getRandomResponse();
        }
		return response;
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}

		return response;
	}

	public boolean hasKeywordsOR(String statement, String... keywords) {
	    for(String keyword: keywords)
            if(statement.contains(keyword))
                return true;
        return false;
	}

    public boolean hasKeywordsAND(String statement, String... keywords) {
        for(String keyword: keywords)
            if(!statement.contains(keyword))
                return false;
        return true;
    }
}
