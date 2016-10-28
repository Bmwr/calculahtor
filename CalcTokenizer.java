package edu.uab.cis.calc;

import java.util.StringTokenizer;

/**
 * Simple Tokenizer that generates a token stream of an input stream.
 *
 *  The implementation attempts to match the token descriptions
 *  against the input. For any given input, only a single token description can be
 *  successfully matched; otherwise the Tokenizer reports the ambiguity through
 *  an exception.
 */
public class CalcTokenizer
{
  /** Constructs a new tokenizer with the input that needs to be processed
   *
   *  @param s input
   */
  CalcTokenizer(String s)
  {
    assert s != null                : "Invalid input string";

    tokenizer = new StringTokenizer(s, " +-*/()", true /* return delimiters */);
  }

  private boolean isFloat(String s)
  {
    boolean res = false;

    try
    {
      Float.parseFloat(token);
      res = true;
    }
    catch (NumberFormatException ex)
    {}

    return res;
  }

  /**
   * reads the next token from the input and matches
   * the kind of tokens.
   *
   * @precondition token == null
   * @postcondition token != null
   */
  public void nextTokenKind()
  {
    if (!tokenizer.hasMoreTokens())
    {
      tokenKind = TokenKind.invalid;
      return;
    }

    token = tokenizer.nextToken();

    if (" ".equals(token)) nextTokenKind();
    else if ("+".equals(token)) tokenKind = TokenKind.plus;
    else if ("-".equals(token)) tokenKind = TokenKind.minus;
    else if ("*".equals(token)) tokenKind = TokenKind.mul;
    else if ("/".equals(token)) tokenKind = TokenKind.div;
    else if ("(".equals(token)) tokenKind = TokenKind.open;
    else if (")".equals(token)) tokenKind = TokenKind.close;
    else
    {
      if (!isFloat(token)) throw new RuntimeException("invalid input");

      tokenKind = TokenKind.floatval;
    }
  }

  /**
   * returns the current token kind
   */
  public TokenKind currentTokenKind()
  {
    return tokenKind;
  }

  /**
   * Returns the Float representation of the token
   *
   * @return Float object for the current token
   */
  public Float getValue()
  {
    return Float.parseFloat(token);
  }

  /**
   * Returns the current token string
   *
   * @return string of the current token
   */
  public String theToken()
  {
    return token;
  }


  /** Tokenizer that chops up the input string */
  private StringTokenizer tokenizer;

  /** Textual representation of last token matched */
  private String          token;

  /** Describes the kind of token that was matched */
  private TokenKind       tokenKind;
}
