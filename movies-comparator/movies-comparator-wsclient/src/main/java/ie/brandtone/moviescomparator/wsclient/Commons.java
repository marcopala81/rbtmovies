package ie.brandtone.moviescomparator.wsclient;

import static ie.brandtone.moviescomparator.wsclient.Constants.N_A;

/**
 * This class exposes all the utility methods that can be reused anywhere in the project.
 * <br>
 * TODO: move to the proper module/package...
 * 
 * @author Marco Pala
 * @version 1.0.0
 */
public final class Commons
{
	/**
	 * Given a {@link String} literal, this method checks if it is empty (or null) and eventually returns the {@link Constants#N_A} value;
	 * otherwise returns the literal itself.
	 * 
	 * @param literal The literal to check
	 * @return The {@link Constants#N_A} value (if <code>literal</code> is null or empty) or the literal itself
	 * @since v1.0.0
	 */
	public static String literalOrNa(String literal)
	{
		return (literal == null | literal.isEmpty()) ? N_A : literal;
	}
}
