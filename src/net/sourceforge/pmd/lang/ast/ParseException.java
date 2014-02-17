package net.sourceforge.pmd.lang.ast;

public class ParseException extends RuntimeException
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -3640909340609155059L;

public ParseException()
  {
  }

  public ParseException(String message)
  {
    super(message);
  }

  public ParseException(Throwable cause) {
    super(cause);
  }

  public ParseException(String message, Throwable cause) {
    super(message, cause);
  }
}