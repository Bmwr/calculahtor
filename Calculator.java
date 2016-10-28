package edu.uab.cis.calc;

/**
  enum TokenKind
  {
    invalid, plus, minus, mul, div, open, close, floatval
  }

  public class CalcTokenizer
  {
  /// Constructs a new tokenizer with the input that needs to be processed
  CalcTokenizer(String s)

  /// reads the next token from the input and matches
  /// the kind of tokens.
  public void nextTokenKind()

  ///
  ///
  public TokenKind currentTokenKind()

  /// Returns the Float representation of the token
  public Float getValue()
}
*/

/**
 * Calculator
 *
 * Floating point calculator. The calculator is invoked through { @link #compute }.
 * Note in order to make the calculator easily extensible towards other types,
 * we use Float objects instead of float values for the computation. Likewise,
 * operations on these objects are encapsulated by a class FloatOperations.
 */
public class Calculator
{
  public Calculator(String expr)
  {
    tokenizer = new CalcTokenizer(expr);
  }

  // calculation

  /**
   * Handles optional parenthesis, otherwise parses the next number
   *
   * @return the next floating point value
   */
  private Float parens()
  {
    Float     res = null;
    TokenKind kind = tokenizer.currentTokenKind();

    if (kind == TokenKind.open)
    {
      tokenizer.nextTokenKind();
      res = plusminus();

      TokenKind closeparen = tokenizer.currentTokenKind();
      assert closeparen == TokenKind.close;
      tokenizer.nextTokenKind();
    }
    else
    {
      assert kind == TokenKind.floatval;

      res = tokenizer.getValue();
      tokenizer.nextTokenKind();
    }

    assert res != null;
    return res;
  }

  /**
   * Tests whether a token is either plus or minus
   *
   * @param k a token
   */
  static boolean isPlusMinusKind(TokenKind k) { return  k == TokenKind.plus || k == TokenKind.minus; }

  /**
   * Tests whether a token is either mul or div
   *
   * @param k a token
   */
  static boolean isMulDivKind   (TokenKind k) { return  k == TokenKind.mul  || k == TokenKind.div;   }

  /**
   * Handles multiplication/division, otherwise returns next number
   *
   * @return computed value
   */
  private Float muldiv()
  {
    Float lhs = parens();

    while (isMulDivKind(tokenizer.currentTokenKind()))
    {
      TokenKind kind = tokenizer.currentTokenKind();

      tokenizer.nextTokenKind();
      Float rhs = parens();

      if (kind == TokenKind.mul)
      {
        lhs = lhs * rhs;
      }
      else
      {
        assert kind == TokenKind.div;
        lhs = lhs / rhs;
      }
    }

    assert lhs != null;
    return lhs;
  }


  /**
   * Handles addition/subtraction, otherwise returns next number
   *
   * @return computed value
   */
  private Float plusminus()
  {
    Float lhs = muldiv();

    while (isPlusMinusKind(tokenizer.currentTokenKind()))
    {
      TokenKind kind = tokenizer.currentTokenKind();

      tokenizer.nextTokenKind();
      Float rhs = muldiv();

      if (kind == TokenKind.plus)
      {
        lhs = lhs + rhs;
      }
      else
      {
        assert kind == TokenKind.minus;
        lhs = lhs - rhs;
      }
    }

    assert lhs != null;
    return lhs;
  }

  /**
   * Invokes start production
   *
   * @return computed value
   */
  private Float calc()
  {
    tokenizer.nextTokenKind();
    return plusminus();
  }

  /**
   * Computes the value for a given expression
   *
   * @param expr input expression
   * @return computed value
   * @throws RuntimeException if the input is not valid
   */
  public Float compute()
  {
    assert tokenizer != null;
    return calc();
  }

  /** We use a simple tokenizer to chop up the input stream. */
  private CalcTokenizer tokenizer;
}
