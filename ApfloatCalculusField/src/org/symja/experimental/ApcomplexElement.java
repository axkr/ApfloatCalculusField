package org.symja.experimental;

import java.math.RoundingMode;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.exception.MathIllegalArgumentException;
import org.hipparchus.exception.MathRuntimeException;
import org.hipparchus.exception.NullArgumentException;
import org.hipparchus.util.FieldSinCos;
import org.hipparchus.util.FieldSinhCosh;

public class ApcomplexElement implements CalculusFieldElement<ApcomplexElement> {

  Apcomplex fApcomplex;

  public static ApcomplexElement valueOf(final Apfloat re, final Apfloat im) {
    return new ApcomplexElement(re, im);
  }

  public static ApcomplexElement valueOf(final Apcomplex cmplx) {
    return new ApcomplexElement(cmplx);
  }

  public static ApcomplexElement valueOf(final double d, long precision) {
    return new ApcomplexElement(d, precision);
  }

  public static ApcomplexElement valueOf(final String value, long precision) {
    return new ApcomplexElement(value, precision);
  }

  public static ApcomplexElement valueOf(
      final String valueRe, final String valueIm, long precision) {
    return new ApcomplexElement(valueRe, valueIm, precision);
  }

  private ApcomplexElement(final String value, long precision) {
    fApcomplex = new Apfloat(value, precision);
  }

  private ApcomplexElement(final String valueRe, final String valueIm, long precision) {
    fApcomplex = new Apcomplex(new Apfloat(valueRe, precision), new Apfloat(valueIm, precision));
  }

  private ApcomplexElement(final Apcomplex value) {
    fApcomplex = value;
  }

  private ApcomplexElement(final Apfloat re, final Apfloat im) {
    fApcomplex = new Apcomplex(re, im);
  }

  private ApcomplexElement(final double d, long precision) {
    fApcomplex = new Apfloat(d, precision);
  }

  public Apcomplex apcomplexValue(long precision) {
    return new Apcomplex(fApcomplex.real(), fApcomplex.imag());
  }

  @Override
  public ApcomplexElement cos() {
    return valueOf(ApcomplexMath.cos(fApcomplex));
  }

  @Override
  public ApcomplexElement cosh() {
    return valueOf(ApcomplexMath.cosh(fApcomplex));
  }

  @Override
  public ApcomplexElement exp() {
    return valueOf(ApcomplexMath.exp(fApcomplex));
  }

  @Override
  public ApcomplexElement log() {
    return valueOf(ApcomplexMath.log(fApcomplex));
  }

  @Override
  public ApcomplexElement pow(int n) {
    return valueOf(ApcomplexMath.pow(fApcomplex, n));
  }

  @Override
  public ApcomplexElement rootN(int n) {
    return valueOf(ApcomplexMath.root(fApcomplex, n));
  }

  @Override
  public ApcomplexElement sign() {
    if (isNaN() || isZero()) {
      return this;
    }
    return valueOf(ApcomplexMath.abs(fApcomplex));
  }

  @Override
  public ApcomplexElement sin() {
    return valueOf(ApcomplexMath.sin(fApcomplex));
  }

  @Override
  public ApcomplexElement sinh() {
    return valueOf(ApcomplexMath.sinh(fApcomplex));
  }

  @Override
  public ApcomplexElement tan() {
    return valueOf(ApcomplexMath.tan(fApcomplex));
  }

  @Override
  public ApcomplexElement tanh() {
    return valueOf(ApcomplexMath.tanh(fApcomplex));
  }

  @Override
  public ApcomplexElement ulp() {
    return valueOf(ApcomplexMath.ulp(fApcomplex));
  }

  @Override
  public ApcomplexElement add(ApcomplexElement value) throws NullArgumentException {
    return valueOf(fApcomplex.add(value.fApcomplex));
  }

  @Override
  public ApcomplexElement divide(ApcomplexElement value)
      throws NullArgumentException, MathRuntimeException {
    return valueOf(fApcomplex.divide(value.fApcomplex));
  }

  @Override
  public Field<ApcomplexElement> getField() {
    return ApcomplexField.APCOMPLEX_FIELD;
  }

  @Override
  public ApcomplexElement multiply(int value) {
    return valueOf(fApcomplex.multiply(new Apfloat(value, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement multiply(ApcomplexElement value) throws NullArgumentException {
    return valueOf(fApcomplex.multiply(value.fApcomplex));
  }

  @Override
  public ApcomplexElement negate() {
    return valueOf(fApcomplex.negate());
  }

  @Override
  public ApcomplexElement subtract(ApcomplexElement value) throws NullArgumentException {
    return valueOf(fApcomplex.subtract(value.fApcomplex));
  }

  @Override
  public ApcomplexElement acos() {
    return valueOf(ApcomplexMath.acos(fApcomplex));
  }

  @Override
  public ApcomplexElement acosh() {
    return valueOf(ApcomplexMath.acosh(fApcomplex));
  }

  @Override
  public ApcomplexElement add(double value) {
    return valueOf(fApcomplex.add(new Apfloat(value, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement asin() {
    return valueOf(ApcomplexMath.asin(fApcomplex));
  }

  @Override
  public ApcomplexElement asinh() {
    return valueOf(ApcomplexMath.asinh(fApcomplex));
  }

  @Override
  public ApcomplexElement atan() {
    return valueOf(ApcomplexMath.atan(fApcomplex));
  }

  @Override
  public ApcomplexElement atan2(ApcomplexElement value) throws MathIllegalArgumentException {
    Apcomplex th = this.fApcomplex;
    final long precision = th.precision();
    Apcomplex x = value.fApcomplex;

    // compute r = sqrt(x^2+y^2)
    final Apcomplex r = ApcomplexMath.sqrt(x.multiply(x).add(th.multiply(th)));

    if (x.real().compareTo(Apfloat.ZERO) >= 0) {
      // compute atan2(y, x) = 2 atan(y / (r + x))
      return valueOf(ApcomplexMath.atan(th.divide(r.add(x))).multiply(new Apfloat(2, precision)));
    } else {
      // compute atan2(y, x) = +/- pi - 2 atan(y / (r - x))
      return valueOf(
          ApcomplexMath.atan(th.divide(r.subtract(x)))
              .multiply(new Apfloat(-2, precision))
              .add(ApfloatMath.pi(precision)));
    }
  }

  @Override
  public ApcomplexElement atanh() {
    return valueOf(ApcomplexMath.atanh(fApcomplex));
  }

  @Override
  public ApcomplexElement cbrt() {
    return valueOf(ApcomplexMath.cbrt(fApcomplex));
  }

  @Override
  public ApcomplexElement ceil() {
    return valueOf(
        ApfloatMath.ceil(fApcomplex.real()), //
        ApfloatMath.ceil(fApcomplex.imag()));
  }

  @Override
  public ApcomplexElement copySign(ApcomplexElement value) {
    Apfloat signReal = value.fApcomplex.real();
    Apfloat signImag = value.fApcomplex.imag();
    return valueOf(
        ApfloatMath.copySign(fApcomplex.real(), signReal), //
        ApfloatMath.copySign(fApcomplex.imag(), signImag));
  }

  @Override
  public ApcomplexElement copySign(double d) {
    Apfloat sign = new Apfloat(d, fApcomplex.precision());
    return valueOf(
        ApfloatMath.copySign(fApcomplex.real(), sign), //
        ApfloatMath.copySign(fApcomplex.imag(), sign));
  }

  @Override
  public ApcomplexElement divide(double value) {
    return valueOf(fApcomplex.divide(new Apfloat(value, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement expm1() {
    return valueOf(ApcomplexMath.exp(fApcomplex).subtract(new Apfloat(1, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement floor() {
    return valueOf(
        ApfloatMath.floor(fApcomplex.real()), //
        ApfloatMath.floor(fApcomplex.imag()));
  }

  @Override
  public double getReal() {
    return fApcomplex.real().doubleValue();
  }

  @Override
  public ApcomplexElement hypot(ApcomplexElement y) throws MathIllegalArgumentException {
    return valueOf(
        ApcomplexMath.sqrt(fApcomplex.multiply(fApcomplex))
            .add(y.fApcomplex.multiply(y.fApcomplex)));
  }

  @Override
  public ApcomplexElement linearCombination(ApcomplexElement[] a, ApcomplexElement[] b)
      throws MathIllegalArgumentException {
    Apcomplex result = Apfloat.ZERO;
    for (int i = 0; i < a.length; i++) {
      result = result.add(a[i].fApcomplex.multiply(b[i].fApcomplex));
    }
    return valueOf(result);
  }

  @Override
  public ApcomplexElement linearCombination(double[] a, ApcomplexElement[] b)
      throws MathIllegalArgumentException {
    Apcomplex result = Apfloat.ZERO;
    if (b.length > 0) {
      long precision = b[0].fApcomplex.precision();
      for (int i = 0; i < a.length; i++) {
        result = result.add(new Apfloat(a[i], precision).multiply(b[i].fApcomplex));
      }
    }
    return valueOf(result);
  }

  @Override
  public ApcomplexElement linearCombination(
      ApcomplexElement a1, ApcomplexElement b1, ApcomplexElement a2, ApcomplexElement b2) {
    return linearCombination(new ApcomplexElement[] {a1, a2}, new ApcomplexElement[] {b1, b2});
  }

  @Override
  public ApcomplexElement linearCombination(
      double a1, ApcomplexElement b1, double a2, ApcomplexElement b2) {
    return linearCombination(new double[] {a1, a2}, new ApcomplexElement[] {b1, b2});
  }

  @Override
  public ApcomplexElement linearCombination(
      ApcomplexElement a1,
      ApcomplexElement b1,
      ApcomplexElement a2,
      ApcomplexElement b2,
      ApcomplexElement a3,
      ApcomplexElement b3) {
    return linearCombination(
        new ApcomplexElement[] {a1, a2, a3}, new ApcomplexElement[] {b1, b2, b3});
  }

  @Override
  public ApcomplexElement linearCombination(
      double a1,
      ApcomplexElement b1,
      double a2,
      ApcomplexElement b2,
      double a3,
      ApcomplexElement b3) {
    return linearCombination(new double[] {a1, a2, a3}, new ApcomplexElement[] {b1, b2, b3});
  }

  @Override
  public ApcomplexElement linearCombination(
      ApcomplexElement a1,
      ApcomplexElement b1,
      ApcomplexElement a2,
      ApcomplexElement b2,
      ApcomplexElement a3,
      ApcomplexElement b3,
      ApcomplexElement a4,
      ApcomplexElement b4) {
    return linearCombination(
        new ApcomplexElement[] {a1, a2, a3, a4}, new ApcomplexElement[] {b1, b2, b3, b4});
  }

  @Override
  public ApcomplexElement linearCombination(
      double a1,
      ApcomplexElement b1,
      double a2,
      ApcomplexElement b2,
      double a3,
      ApcomplexElement b3,
      double a4,
      ApcomplexElement b4) {
    return linearCombination(
        new double[] {a1, a2, a3, a4}, new ApcomplexElement[] {b1, b2, b3, b4});
  }

  @Override
  public ApcomplexElement log10() {
    return valueOf(ApcomplexMath.log(fApcomplex, new Apfloat(10, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement log1p() {
    return valueOf(ApcomplexMath.log(fApcomplex.add(new Apfloat(1, fApcomplex.precision()))));
  }

  @Override
  public ApcomplexElement multiply(double value) {
    return valueOf(fApcomplex.multiply(new Apfloat(value, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement pow(double value) {
    return valueOf(ApcomplexMath.pow(fApcomplex, new Apfloat(value, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement pow(ApcomplexElement value) throws MathIllegalArgumentException {
    return valueOf(ApcomplexMath.pow(fApcomplex, value.fApcomplex));
  }

  @Override
  public ApcomplexElement reciprocal() {
    return valueOf(ApcomplexMath.inverseRoot(fApcomplex, 1));
  }

  @Override
  public ApcomplexElement remainder(double value) {
    return valueOf(
        fApcomplex.real().mod(new Apfloat(value, fApcomplex.precision())), //
        fApcomplex.imag().mod(new Apfloat(value, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement remainder(ApcomplexElement a) {
    final Apcomplex complexQuotient = fApcomplex.divide(a.fApcomplex);
    final Apfloat qRInt = ApfloatElement.apfloatRint(complexQuotient.real());
    final Apfloat qIInt = ApfloatElement.apfloatRint(complexQuotient.imag());
    final Apfloat re = fApcomplex.real();
    final Apfloat im = fApcomplex.imag();
    return valueOf(
        re.subtract(qRInt.multiply(a.fApcomplex.real()))
            .add(qIInt.multiply(a.fApcomplex.imag())), //
        im.subtract(qRInt.multiply(a.fApcomplex.imag()))
            .subtract(qIInt.multiply(a.fApcomplex.real())));
  }

  @Override
  public ApcomplexElement rint() {
    return valueOf(
        ApfloatElement.apfloatRint(fApcomplex.real()), //
        ApfloatElement.apfloatRint(fApcomplex.imag()));
  }

  @Override
  public ApcomplexElement scalb(int n) {
    return valueOf(fApcomplex.multiply(ApcomplexMath.pow(new Apfloat(2), n)));
  }

  @Override
  public ApcomplexElement sqrt() {
    return valueOf(ApcomplexMath.sqrt(fApcomplex));
  }

  @Override
  public ApcomplexElement subtract(double value) {
    return valueOf(fApcomplex.subtract(new Apfloat(value, fApcomplex.precision())));
  }

  @Override
  public ApcomplexElement abs() {
    return valueOf(ApcomplexMath.abs(fApcomplex));
  }

  @Override
  public ApcomplexElement newInstance(double d) {
    return valueOf(d, fApcomplex.precision());
  }

  @Override
  public FieldSinCos<ApcomplexElement> sinCos() {
    return new FieldSinCos<ApcomplexElement>(sin(), cos());
  }

  @Override
  public FieldSinhCosh<ApcomplexElement> sinhCosh() {
    return new FieldSinhCosh<ApcomplexElement>(sinh(), cosh());
  }

  @Override
  public String toString() {
    return fApcomplex.toString();
  }
}
