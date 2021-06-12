package org.symja.experimental;

import java.math.RoundingMode;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.exception.MathIllegalArgumentException;
import org.hipparchus.exception.MathRuntimeException;
import org.hipparchus.exception.NullArgumentException;
import org.hipparchus.util.FastMath;
import org.hipparchus.util.FieldSinCos;
import org.hipparchus.util.FieldSinhCosh;

public class ApfloatElement implements CalculusFieldElement<ApfloatElement> {

  Apfloat fApfloat;

  public static ApfloatElement valueOf(final Apfloat value) {
    return new ApfloatElement(value);
  }

  public static ApfloatElement valueOf(final double d, long precision) {
    return new ApfloatElement(d, precision);
  }

  public static ApfloatElement valueOf(final String value, long precision) {
    return new ApfloatElement(value, precision);
  }

  private ApfloatElement(final String value, long precision) {
    fApfloat = new Apfloat(value, precision);
  }

  private ApfloatElement(final Apfloat value) {
    fApfloat = value;
  }

  private ApfloatElement(final double d, long precision) {
    fApfloat = new Apfloat(d, precision);
  }

  public Apcomplex apcomplexValue(long precision) {
    return new Apcomplex(fApfloat);
  }

  @Override
  public ApfloatElement cos() {
    return valueOf(ApfloatMath.cos(fApfloat));
  }

  @Override
  public ApfloatElement cosh() {
    return valueOf(ApfloatMath.cosh(fApfloat));
  }

  @Override
  public ApfloatElement exp() {
    return valueOf(ApfloatMath.exp(fApfloat));
  }

  @Override
  public ApfloatElement log() {
    return valueOf(ApfloatMath.log(fApfloat));
  }

  @Override
  public ApfloatElement pow(int n) {
    return valueOf(ApfloatMath.pow(fApfloat, n));
  }

  @Override
  public ApfloatElement rootN(int n) {
    return valueOf(ApfloatMath.root(fApfloat, n));
  }

  @Override
  public ApfloatElement sign() {
    if (isNaN() || isZero()) {
      return this;
    }
    return valueOf(ApfloatMath.abs(fApfloat));
  }

  @Override
  public ApfloatElement sin() {
    return valueOf(ApfloatMath.sin(fApfloat));
  }

  @Override
  public ApfloatElement sinh() {
    return valueOf(ApfloatMath.sinh(fApfloat));
  }

  @Override
  public ApfloatElement tan() {
    return valueOf(ApfloatMath.tan(fApfloat));
  }

  @Override
  public ApfloatElement tanh() {
    return valueOf(ApfloatMath.tanh(fApfloat));
  }

  @Override
  public ApfloatElement ulp() {
    return valueOf(ApfloatMath.ulp(fApfloat));
  }

  @Override
  public ApfloatElement add(ApfloatElement value) throws NullArgumentException {
    return valueOf(fApfloat.add(value.fApfloat));
  }

  @Override
  public ApfloatElement divide(ApfloatElement value)
      throws NullArgumentException, MathRuntimeException {
    return valueOf(fApfloat.divide(value.fApfloat));
  }

  @Override
  public Field<ApfloatElement> getField() {
    return ApfloatField.EXPR_FIELD;
  }

  @Override
  public ApfloatElement multiply(int value) {
    return valueOf(fApfloat.multiply(new Apfloat(value, fApfloat.precision())));
  }

  @Override
  public ApfloatElement multiply(ApfloatElement value) throws NullArgumentException {
    return valueOf(fApfloat.multiply(value.fApfloat));
  }

  @Override
  public ApfloatElement negate() {
    return valueOf(fApfloat.negate());
  }

  @Override
  public ApfloatElement subtract(ApfloatElement value) throws NullArgumentException {
    return valueOf(fApfloat.subtract(value.fApfloat));
  }

  @Override
  public ApfloatElement acos() {
    return valueOf(ApfloatMath.acos(fApfloat));
  }

  @Override
  public ApfloatElement acosh() {
    return valueOf(ApfloatMath.acosh(fApfloat));
  }

  @Override
  public ApfloatElement add(double value) {
    return valueOf(fApfloat.add(new Apfloat(value, fApfloat.precision())));
  }

  @Override
  public ApfloatElement asin() {
    return valueOf(ApfloatMath.asin(fApfloat));
  }

  @Override
  public ApfloatElement asinh() {
    return valueOf(ApfloatMath.asinh(fApfloat));
  }

  @Override
  public ApfloatElement atan() {
    return valueOf(ApfloatMath.atan(fApfloat));
  }

  @Override
  public ApfloatElement atan2(ApfloatElement value) throws MathIllegalArgumentException {
    return valueOf(ApfloatMath.atan2(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement atanh() {
    return valueOf(ApfloatMath.atanh(fApfloat));
  }

  @Override
  public ApfloatElement cbrt() {
    return valueOf(ApfloatMath.cbrt(fApfloat));
  }

  @Override
  public ApfloatElement ceil() {
    return valueOf(ApfloatMath.ceil(fApfloat));
  }

  @Override
  public ApfloatElement copySign(ApfloatElement value) {
    return valueOf(ApfloatMath.copySign(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement copySign(double d) {
    return valueOf(ApfloatMath.copySign(fApfloat, new Apfloat(d, fApfloat.precision())));
  }

  @Override
  public ApfloatElement divide(double value) {
    return valueOf(fApfloat.divide(new Apfloat(value, fApfloat.precision())));
  }

  @Override
  public ApfloatElement expm1() {
    return valueOf(ApfloatMath.exp(fApfloat).subtract(Apfloat.ONE));
  }

  @Override
  public ApfloatElement floor() {
    return valueOf(ApfloatMath.floor(fApfloat));
  }

  @Override
  public double getReal() {
    return fApfloat.doubleValue();
  }

  @Override
  public ApfloatElement hypot(ApfloatElement y) throws MathIllegalArgumentException {
    return valueOf(
        ApfloatMath.sqrt(fApfloat.multiply(fApfloat)).add(y.fApfloat.multiply(y.fApfloat)));
  }

  @Override
  public ApfloatElement linearCombination(ApfloatElement[] a, ApfloatElement[] b)
      throws MathIllegalArgumentException {
    Apfloat result = Apfloat.ZERO;
    for (int i = 0; i < a.length; i++) {
      result = result.add(a[i].fApfloat.multiply(b[i].fApfloat));
    }
    return valueOf(result);
  }

  @Override
  public ApfloatElement linearCombination(double[] a, ApfloatElement[] b)
      throws MathIllegalArgumentException {
    Apfloat result = Apfloat.ZERO;
    if (b.length > 0) {
      long precision = b[0].fApfloat.precision();
      for (int i = 0; i < a.length; i++) {
        result = result.add(new Apfloat(a[i], precision).multiply(b[i].fApfloat));
      }
    }
    return valueOf(result);
  }

  @Override
  public ApfloatElement linearCombination(
      ApfloatElement a1, ApfloatElement b1, ApfloatElement a2, ApfloatElement b2) {
    return linearCombination(new ApfloatElement[] {a1, a2}, new ApfloatElement[] {b1, b2});
  }

  @Override
  public ApfloatElement linearCombination(
      double a1, ApfloatElement b1, double a2, ApfloatElement b2) {
    return linearCombination(new double[] {a1, a2}, new ApfloatElement[] {b1, b2});
  }

  @Override
  public ApfloatElement linearCombination(
      ApfloatElement a1,
      ApfloatElement b1,
      ApfloatElement a2,
      ApfloatElement b2,
      ApfloatElement a3,
      ApfloatElement b3) {
    return linearCombination(new ApfloatElement[] {a1, a2, a3}, new ApfloatElement[] {b1, b2, b3});
  }

  @Override
  public ApfloatElement linearCombination(
      double a1, ApfloatElement b1, double a2, ApfloatElement b2, double a3, ApfloatElement b3) {
    return linearCombination(new double[] {a1, a2, a3}, new ApfloatElement[] {b1, b2, b3});
  }

  @Override
  public ApfloatElement linearCombination(
      ApfloatElement a1,
      ApfloatElement b1,
      ApfloatElement a2,
      ApfloatElement b2,
      ApfloatElement a3,
      ApfloatElement b3,
      ApfloatElement a4,
      ApfloatElement b4) {
    return linearCombination(
        new ApfloatElement[] {a1, a2, a3, a4}, new ApfloatElement[] {b1, b2, b3, b4});
  }

  @Override
  public ApfloatElement linearCombination(
      double a1,
      ApfloatElement b1,
      double a2,
      ApfloatElement b2,
      double a3,
      ApfloatElement b3,
      double a4,
      ApfloatElement b4) {
    return linearCombination(new double[] {a1, a2, a3, a4}, new ApfloatElement[] {b1, b2, b3, b4});
  }

  @Override
  public ApfloatElement log10() {
    return valueOf(ApfloatMath.log(fApfloat, new Apfloat(10, fApfloat.precision())));
  }

  @Override
  public ApfloatElement log1p() {
    return valueOf(ApfloatMath.log(fApfloat.add(Apfloat.ONE)));
  }

  @Override
  public ApfloatElement multiply(double value) {
    return valueOf(fApfloat.multiply(new Apfloat(value, fApfloat.precision())));
  }

  @Override
  public ApfloatElement pow(double value) {
    return valueOf(ApfloatMath.pow(fApfloat, new Apfloat(value, fApfloat.precision())));
  }

  @Override
  public ApfloatElement pow(ApfloatElement value) throws MathIllegalArgumentException {
    return valueOf(ApfloatMath.pow(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement reciprocal() {
    return valueOf(ApfloatMath.inverseRoot(fApfloat, 1));
  }

  @Override
  public ApfloatElement remainder(double value) {
    return valueOf(fApfloat.mod(new Apfloat(value, fApfloat.precision())));
  }

  @Override
  public ApfloatElement remainder(ApfloatElement value) {
    return valueOf(fApfloat.mod(value.fApfloat));
  }

  @Override
  public ApfloatElement rint() {
    return valueOf(ApfloatMath.round(fApfloat, fApfloat.precision(), RoundingMode.HALF_EVEN));
  }

  @Override
  public ApfloatElement scalb(int n) {
    return valueOf(fApfloat.multiply(ApfloatMath.pow(new Apfloat(2), n)));
  }

  @Override
  public ApfloatElement sqrt() {
    return valueOf(ApfloatMath.sqrt(fApfloat));
  }

  @Override
  public ApfloatElement subtract(double value) {
    return valueOf(fApfloat.subtract(new Apfloat(value, fApfloat.precision())));
  }

  @Override
  public ApfloatElement abs() {
    return valueOf(ApfloatMath.abs(fApfloat));
  }

  @Override
  public ApfloatElement newInstance(double d) {
    return valueOf(d, fApfloat.precision());
  }

  @Override
  public FieldSinCos<ApfloatElement> sinCos() {
    return new FieldSinCos<ApfloatElement>(sin(), cos());
  }

  @Override
  public FieldSinhCosh<ApfloatElement> sinhCosh() {
    return new FieldSinhCosh<ApfloatElement>(sinh(), cosh());
  }
}
