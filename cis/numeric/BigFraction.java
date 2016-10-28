package edu.uab.cis;

import java.math.BigInteger;

/**
 * A class representing fractions of BigInteger.
 */
public class BigFraction extends Number
{
  /**
   * Constructs a new BigFraction.
   *
   * @param n numerator
   * @param d denominator
   * @precondition n != null && d != null && d != BigInteger(0)
   */
  public BigFraction(BigInteger n, BigInteger d)
  {
    assert n != null && d != null     : "arguments cannot be null";
    assert !BigInteger.ZERO.equals(d) : "denominator must not be zero";

    num = n;
    denom = d;
  }

  /**
   * returns a String representation for the BigFraction.
   * @return a textual representation of this fraction
   */
  public String toString()
  {
    // do not print Fraction, if denom is 1
    if (BigInteger.ONE.equals(denom)) return num.toString();

    return "(" + num + " / " + denom + ")";
  }

  /* Methods required by Number */

  /**
   * calculates double value of this BigFraction. May lose precision.
   * @return double value of this fraction
   */
  public double doubleValue()
  {
    return num.doubleValue() / denom.doubleValue();
  }

  /**
   * calculates float value of this BigFraction. May lose precision.
   * @return float value of this fraction
   */
  public float floatValue() { return (float)doubleValue(); }

  /**
   * calculates long value of this BigFraction.
   * @return long value of this fraction
   */
  public long  longValue()  { return (long)doubleValue(); }

  /**
   * calculates int value of this BigFraction.
   * @return int value of this fraction
   */
  public int   intValue()   { return (int)doubleValue(); }

  /* equals and hashCode */

  /**
   * equals returns true, iff numerator and denominator are equal.
   *
   * note, this is numeric equality, only iff both fractions are simplified.
   *
   * @param o object to which this is compared
   * @return true, iff numerator and denominator are equal.
   */
  public boolean equals(Object o)
  {
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;

    BigFraction that = (BigFraction)o;
    return (  this.getNumerator().equals(that.getNumerator())
           && this.getDenominator().equals(that.getDenominator())
           );
  }

  /**
   * hashCode implementation in line with equals.
   * @return hash code for this fraction
   */
  public int hashCode()
  {
    return this.getNumerator().hashCode() ^ this.getDenominator().hashCode();
  }

  /* getter methods */

  /**
   * returns the numerator.
   * @return numerator
   */
  public BigInteger getNumerator()   { return num; }

  /**
   * returns the denominator.
   * @return denominator
   */
  public BigInteger getDenominator() { return denom; }

  /* fields */

  /** the numerator. */
  private BigInteger num;

  /** the denominator. */
  private BigInteger denom;

  /** serial version UID for serialization. */
  private static final long serialVersionUID = 161146059432882814L;
}
