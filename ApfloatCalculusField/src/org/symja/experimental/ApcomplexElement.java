package org.symja.experimental;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.FixedPrecisionApfloatHelper;
import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.exception.MathIllegalArgumentException;
import org.hipparchus.exception.MathRuntimeException;
import org.hipparchus.exception.NullArgumentException;
import org.hipparchus.util.FieldSinCos;
import org.hipparchus.util.FieldSinhCosh;

public class ApcomplexElement implements CalculusFieldElement<ApcomplexElement> {

  public static ApcomplexElement valueOf(final Apcomplex cmplx) {
    return new ApcomplexElement(cmplx);
  }

  public static ApcomplexElement valueOf(final Apfloat re, final Apfloat im) {
    return new ApcomplexElement(re, im);
  }

  public static ApcomplexElement valueOf(final double d) {
    return new ApcomplexElement(d);
  }

  public static ApcomplexElement valueOf(final String value) {
    return new ApcomplexElement(value);
  }

  public static ApcomplexElement valueOf(final String valueRe, final String valueIm) {
    return new ApcomplexElement(valueRe, valueIm);
  }

  Apcomplex fApcomplex;

  private ApcomplexElement(final Apcomplex value) {
    fApcomplex = value;
  }

  private ApcomplexElement(final Apfloat re, final Apfloat im) {
    fApcomplex = ApfloatField.get().valueOf(new Apcomplex(re, im));
  }

  private ApcomplexElement(final double d) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    fApcomplex = h.valueOf(new Apfloat(d));
  }

  private ApcomplexElement(final String value) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    fApcomplex = h.valueOf(new Apfloat(value));
  }

  private ApcomplexElement(final String valueRe, final String valueIm) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    fApcomplex = new Apcomplex(h.valueOf(new Apfloat(valueRe)), h.valueOf(new Apfloat(valueIm)));
  }

  @Override
  public ApcomplexElement abs() {
    return valueOf(ApfloatField.get().abs(fApcomplex));
  }

  @Override
  public ApcomplexElement acos() {
    return valueOf(ApfloatField.get().acos(fApcomplex));
  }

  @Override
  public ApcomplexElement acosh() {
    return valueOf(ApfloatField.get().acosh(fApcomplex));
  }

  @Override
  public ApcomplexElement add(ApcomplexElement value) throws NullArgumentException {
    return valueOf(ApfloatField.get().add(fApcomplex, value.fApcomplex));
  }

  @Override
  public ApcomplexElement add(double value) {
    return valueOf(ApfloatField.get().add(fApcomplex, new Apfloat(value)));
  }

  public Apcomplex apcomplexValue() {
    return new Apcomplex(fApcomplex.real(), fApcomplex.imag());
  }

  @Override
  public ApcomplexElement asin() {
    return valueOf(ApfloatField.get().asin(fApcomplex));
  }

  @Override
  public ApcomplexElement asinh() {
    return valueOf(ApfloatField.get().asinh(fApcomplex));
  }

  @Override
  public ApcomplexElement atan() {
    return valueOf(ApfloatField.get().atan(fApcomplex));
  }

  @Override
  public ApcomplexElement atan2(ApcomplexElement value) throws MathIllegalArgumentException {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    Apcomplex th = this.fApcomplex;
    Apcomplex x = value.fApcomplex;
    // compute r = sqrt(x^2+y^2)
    final Apcomplex r = h.sqrt(h.add(h.multiply(x, x), h.multiply(th, th)));

    if (x.real().compareTo(Apfloat.ZERO) >= 0) {
      // compute atan2(y, x) = 2 atan(y / (r + x))
      return valueOf(h.multiply(h.atan(h.divide(th, h.add(r, x))), new Apfloat(2)));
    } else {
      // compute atan2(y, x) = +/- pi - 2 atan(y / (r - x))
      return valueOf(
          h.add(h.multiply(h.atan(h.divide(th, h.subtract(r, x))), new Apfloat(-2)), h.pi()));
    }
  }

  @Override
  public ApcomplexElement atanh() {
    return valueOf(ApfloatField.get().atanh(fApcomplex));
  }

  @Override
  public ApcomplexElement cbrt() {
    return valueOf(ApfloatField.get().cbrt(fApcomplex));
  }

  @Override
  public ApcomplexElement ceil() {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(
        h.ceil(fApcomplex.real()), //
        h.ceil(fApcomplex.imag()));
  }

  @Override
  public ApcomplexElement copySign(ApcomplexElement value) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    Apfloat signReal = value.fApcomplex.real();
    Apfloat signImag = value.fApcomplex.imag();
    return valueOf(
        h.copySign(fApcomplex.real(), signReal), //
        h.copySign(fApcomplex.imag(), signImag));
  }

  @Override
  public ApcomplexElement copySign(double d) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    Apfloat sign = h.valueOf(new Apfloat(d));
    return valueOf(
        h.copySign(fApcomplex.real(), sign), //
        h.copySign(fApcomplex.imag(), sign));
  }

  @Override
  public ApcomplexElement cos() {
    return valueOf(ApfloatField.get().cos(fApcomplex));
  }

  @Override
  public ApcomplexElement cosh() {
    return valueOf(ApfloatField.get().cosh(fApcomplex));
  }

  @Override
  public ApcomplexElement divide(ApcomplexElement value)
      throws NullArgumentException, MathRuntimeException {
    return valueOf(ApfloatField.get().divide(fApcomplex, value.fApcomplex));
  }

  @Override
  public ApcomplexElement divide(double value) {
    return valueOf(ApfloatField.get().divide(fApcomplex, new Apfloat(value)));
  }

  @Override
  public ApcomplexElement exp() {
    return valueOf(ApfloatField.get().exp(fApcomplex));
  }

  @Override
  public ApcomplexElement expm1() {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(h.subtract(h.exp(fApcomplex), Apfloat.ONE));
  }

  @Override
  public ApcomplexElement floor() {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(
        h.floor(fApcomplex.real()), //
        h.floor(fApcomplex.imag()));
  }

  @Override
  public Field<ApcomplexElement> getField() {
    return ApcomplexField.APCOMPLEX_FIELD;
  }

  @Override
  public ApcomplexElement getPi() {
    return valueOf(ApfloatField.get().pi());
  }

  @Override
  public double getReal() {
    return fApcomplex.real().doubleValue();
  }

  @Override
  public ApcomplexElement hypot(ApcomplexElement y) throws MathIllegalArgumentException {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(
        h.add(
            h.sqrt( //
                h.multiply(fApcomplex, fApcomplex)), //
            h.multiply(y.fApcomplex, y.fApcomplex)));
  }

  @Override
  public ApcomplexElement linearCombination(
      ApcomplexElement a1, ApcomplexElement b1, ApcomplexElement a2, ApcomplexElement b2) {
    return linearCombination(new ApcomplexElement[] {a1, a2}, new ApcomplexElement[] {b1, b2});
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
  public ApcomplexElement linearCombination(ApcomplexElement[] a, ApcomplexElement[] b)
      throws MathIllegalArgumentException {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    Apcomplex result = Apfloat.ZERO;
    for (int i = 0; i < a.length; i++) {
      result = h.add(result, h.multiply(a[i].fApcomplex, b[i].fApcomplex));
    }
    return valueOf(result);
  }

  @Override
  public ApcomplexElement linearCombination(
      double a1, ApcomplexElement b1, double a2, ApcomplexElement b2) {
    return linearCombination(new double[] {a1, a2}, new ApcomplexElement[] {b1, b2});
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
  public ApcomplexElement linearCombination(double[] a, ApcomplexElement[] b)
      throws MathIllegalArgumentException {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    Apcomplex result = Apfloat.ZERO;
    if (b.length > 0) {
      for (int i = 0; i < a.length; i++) {
        result = h.add(result, h.multiply(new Apfloat(a[i]), b[i].fApcomplex));
      }
    }
    return valueOf(result);
  }

  @Override
  public ApcomplexElement log() {
    return valueOf(ApfloatField.get().log(fApcomplex));
  }

  @Override
  public ApcomplexElement log10() {
    return valueOf(ApfloatField.get().log(fApcomplex, new Apfloat(10)));
  }

  @Override
  public ApcomplexElement log1p() {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(h.log(h.add(fApcomplex, new Apfloat(1))));
  }

  @Override
  public ApcomplexElement multiply(ApcomplexElement value) throws NullArgumentException {
    return valueOf(ApfloatField.get().multiply(fApcomplex, value.fApcomplex));
  }

  @Override
  public ApcomplexElement multiply(double value) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(h.multiply(fApcomplex, new Apfloat(value)));
  }

  @Override
  public ApcomplexElement multiply(int value) {
    return valueOf(ApfloatField.get().multiply(fApcomplex, new Apfloat(value)));
  }

  @Override
  public ApcomplexElement negate() {
    return valueOf(ApfloatField.get().negate(fApcomplex));
  }

  @Override
  public ApcomplexElement newInstance(double d) {
    return valueOf(d);
  }

  @Override
  public ApcomplexElement pow(ApcomplexElement value) throws MathIllegalArgumentException {
    return valueOf(ApfloatField.get().pow(fApcomplex, value.fApcomplex));
  }

  @Override
  public ApcomplexElement pow(double value) {
    return valueOf(ApfloatField.get().pow(fApcomplex, new Apfloat(value)));
  }

  @Override
  public ApcomplexElement pow(int n) {
    return valueOf(ApfloatField.get().pow(fApcomplex, n));
  }

  @Override
  public ApcomplexElement reciprocal() {
    return valueOf(ApfloatField.get().inverseRoot(fApcomplex, 1));
  }

  @Override
  public ApcomplexElement remainder(ApcomplexElement a) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    final Apcomplex complexQuotient = fApcomplex.divide(a.fApcomplex);
    final Apfloat qRInt = ApfloatElement.apfloatRint(complexQuotient.real());
    final Apfloat qIInt = ApfloatElement.apfloatRint(complexQuotient.imag());
    final Apfloat re = fApcomplex.real();
    final Apfloat im = fApcomplex.imag();
    return valueOf(
        h.add(
            h.subtract(re, h.multiply(qRInt, a.fApcomplex.real())),
            h.multiply(qIInt, a.fApcomplex.imag())), //
        h.subtract(
            h.subtract(im, h.multiply(qRInt, a.fApcomplex.imag())),
            h.multiply(qIInt, a.fApcomplex.real())));
  }

  @Override
  public ApcomplexElement remainder(double value) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(
        h.mod(fApcomplex.real(), new Apfloat(value)), //
        h.mod(fApcomplex.imag(), new Apfloat(value)));
  }

  @Override
  public ApcomplexElement rint() {
    return valueOf(
        ApfloatElement.apfloatRint(fApcomplex.real()), //
        ApfloatElement.apfloatRint(fApcomplex.imag()));
  }

  @Override
  public ApcomplexElement rootN(int n) {
    return valueOf(ApfloatField.get().root(fApcomplex, n));
  }

  @Override
  public ApcomplexElement scalb(int n) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(h.multiply(fApcomplex, h.pow(new Apfloat(2), n)));
  }

  @Override
  public ApcomplexElement sign() {
    if (isNaN() || isZero()) {
      return this;
    }
    return valueOf(ApfloatField.get().abs(fApcomplex));
  }

  @Override
  public ApcomplexElement sin() {
    return valueOf(ApfloatField.get().sin(fApcomplex));
  }

  @Override
  public FieldSinCos<ApcomplexElement> sinCos() {
    return new FieldSinCos<ApcomplexElement>(sin(), cos());
  }

  @Override
  public ApcomplexElement sinh() {
    return valueOf(ApfloatField.get().sinh(fApcomplex));
  }

  @Override
  public FieldSinhCosh<ApcomplexElement> sinhCosh() {
    return new FieldSinhCosh<ApcomplexElement>(sinh(), cosh());
  }

  @Override
  public ApcomplexElement sqrt() {
    return valueOf(ApfloatField.get().sqrt(fApcomplex));
  }

  @Override
  public ApcomplexElement subtract(ApcomplexElement value) throws NullArgumentException {
    return valueOf(ApfloatField.get().subtract(fApcomplex, value.fApcomplex));
  }

  @Override
  public ApcomplexElement subtract(double value) {
    return valueOf(ApfloatField.get().subtract(fApcomplex, new Apfloat(value)));
  }

  @Override
  public ApcomplexElement tan() {
    return valueOf(ApfloatField.get().tan(fApcomplex));
  }

  @Override
  public ApcomplexElement tanh() {
    return valueOf(ApfloatField.get().tanh(fApcomplex));
  }

  @Override
  public ApcomplexElement toDegrees() {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(
        ApfloatElement.toDegrees(fApcomplex.real(), h),
        ApfloatElement.toDegrees(fApcomplex.imag(), h));
  }

  @Override
  public ApcomplexElement toRadians() {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(
        ApfloatElement.toRadians(fApcomplex.real(), h),
        ApfloatElement.toRadians(fApcomplex.imag(), h));
  }

  @Override
  public String toString() {
    return fApcomplex.toString();
  }

  @Override
  public ApcomplexElement ulp() {
    return valueOf(ApfloatField.get().ulp(fApcomplex));
  }
}
