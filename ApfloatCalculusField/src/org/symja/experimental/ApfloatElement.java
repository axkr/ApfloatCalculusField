package org.symja.experimental;

import java.math.RoundingMode;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.FixedPrecisionApfloatHelper;
import org.hipparchus.CalculusFieldElement;
import org.hipparchus.Field;
import org.hipparchus.exception.MathIllegalArgumentException;
import org.hipparchus.exception.MathRuntimeException;
import org.hipparchus.exception.NullArgumentException;
import org.hipparchus.util.FieldSinCos;
import org.hipparchus.util.FieldSinhCosh;

public class ApfloatElement implements CalculusFieldElement<ApfloatElement> {

  Apfloat fApfloat;

  public static ApfloatElement valueOf(final Apfloat value) {
    return new ApfloatElement(value);
  }

  public static ApfloatElement valueOf(final double d) {
    return new ApfloatElement(d);
  }

  public static ApfloatElement valueOf(final String value) {
    return new ApfloatElement(value);
  }

  private ApfloatElement(final String value) {
    fApfloat = ApfloatField.get().valueOf(new Apfloat(value));
  }

  private ApfloatElement(final Apfloat value) {
    fApfloat = ApfloatField.get().valueOf(value);
  }

  private ApfloatElement(final double d) {
    fApfloat = ApfloatField.get().valueOf(new Apfloat(d));
  }

  public Apcomplex apcomplexValue(long precision) {
    return new Apcomplex(fApfloat);
  }

  @Override
  public ApfloatElement cos() {
    return valueOf(ApfloatField.get().cos(fApfloat));
  }

  @Override
  public ApfloatElement cosh() {
    return valueOf(ApfloatField.get().cosh(fApfloat));
  }

  @Override
  public ApfloatElement exp() {
    return valueOf(ApfloatField.get().exp(fApfloat));
  }

  @Override
  public ApfloatElement log() {
    return valueOf(ApfloatField.get().log(fApfloat));
  }

  @Override
  public ApfloatElement pow(int n) {
    return valueOf(ApfloatField.get().pow(fApfloat, n));
  }

  @Override
  public ApfloatElement rootN(int n) {
    return valueOf(ApfloatField.get().root(fApfloat, n));
  }

  @Override
  public ApfloatElement sign() {
    if (isNaN() || isZero()) {
      return this;
    }
    return valueOf(ApfloatField.get().abs(fApfloat));
  }

  @Override
  public ApfloatElement sin() {
    return valueOf(ApfloatField.get().sin(fApfloat));
  }

  @Override
  public ApfloatElement sinh() {
    return valueOf(ApfloatField.get().sinh(fApfloat));
  }

  @Override
  public ApfloatElement tan() {
    return valueOf(ApfloatField.get().tan(fApfloat));
  }

  @Override
  public ApfloatElement tanh() {
    return valueOf(ApfloatField.get().tanh(fApfloat));
  }

  @Override
  public ApfloatElement ulp() {
    return valueOf(ApfloatField.get().ulp(fApfloat));
  }

  @Override
  public ApfloatElement add(ApfloatElement value) throws NullArgumentException {
    return valueOf(ApfloatField.get().add(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement divide(ApfloatElement value)
      throws NullArgumentException, MathRuntimeException {
    return valueOf(ApfloatField.get().divide(fApfloat, value.fApfloat));
  }

  @Override
  public Field<ApfloatElement> getField() {
    return ApfloatField.APFLOAT_FIELD;
  }

  @Override
  public ApfloatElement multiply(int value) {
    return valueOf(ApfloatField.get().multiply(fApfloat, new Apfloat(value)));
  }

  @Override
  public ApfloatElement multiply(ApfloatElement value) throws NullArgumentException {
    return valueOf(ApfloatField.get().multiply(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement negate() {
    return valueOf(ApfloatField.get().negate(fApfloat));
  }

  @Override
  public ApfloatElement subtract(ApfloatElement value) throws NullArgumentException {
    return valueOf(ApfloatField.get().subtract(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement acos() {
    return valueOf(ApfloatField.get().acos(fApfloat));
  }

  @Override
  public ApfloatElement acosh() {
    return valueOf(ApfloatField.get().acosh(fApfloat));
  }

  @Override
  public ApfloatElement add(double value) {
    return valueOf(ApfloatField.get().add(fApfloat, new Apfloat(value)));
  }

  @Override
  public ApfloatElement asin() {
    return valueOf(ApfloatField.get().asin(fApfloat));
  }

  @Override
  public ApfloatElement asinh() {
    return valueOf(ApfloatField.get().asinh(fApfloat));
  }

  @Override
  public ApfloatElement atan() {
    return valueOf(ApfloatField.get().atan(fApfloat));
  }

  @Override
  public ApfloatElement atan2(ApfloatElement value) throws MathIllegalArgumentException {
    return valueOf(ApfloatField.get().atan2(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement atanh() {
    return valueOf(ApfloatField.get().atanh(fApfloat));
  }

  @Override
  public ApfloatElement cbrt() {
    return valueOf(ApfloatField.get().cbrt(fApfloat));
  }

  @Override
  public ApfloatElement ceil() {
    return valueOf(ApfloatField.get().ceil(fApfloat));
  }

  @Override
  public ApfloatElement copySign(ApfloatElement value) {
    return valueOf(ApfloatField.get().copySign(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement copySign(double d) {
    return valueOf(ApfloatField.get().copySign(fApfloat, new Apfloat(d, fApfloat.precision())));
  }

  @Override
  public ApfloatElement divide(double value) {
    return valueOf(ApfloatField.get().divide(fApfloat, new Apfloat(value)));
  }

  @Override
  public ApfloatElement expm1() {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(h.subtract(h.exp(fApfloat), Apfloat.ONE));
  }

  @Override
  public ApfloatElement floor() {
    return valueOf(ApfloatField.get().floor(fApfloat));
  }

  @Override
  public double getReal() {
    return fApfloat.doubleValue();
  }

  @Override
  public ApfloatElement hypot(ApfloatElement y) throws MathIllegalArgumentException {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(h.sqrt(h.multiply(fApfloat, fApfloat)).add(h.multiply(y.fApfloat, y.fApfloat)));
  }

  @Override
  public ApfloatElement linearCombination(ApfloatElement[] a, ApfloatElement[] b)
      throws MathIllegalArgumentException {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    Apfloat result = Apfloat.ZERO;
    for (int i = 0; i < a.length; i++) {
      result = h.add(result, h.multiply(a[i].fApfloat, b[i].fApfloat));
    }
    return valueOf(result);
  }

  @Override
  public ApfloatElement linearCombination(double[] a, ApfloatElement[] b)
      throws MathIllegalArgumentException {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    Apfloat result = Apfloat.ZERO;
    if (b.length > 0) {
      for (int i = 0; i < a.length; i++) {
        result = h.add(result, h.multiply(new Apfloat(a[i]), b[i].fApfloat));
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
    return valueOf(ApfloatField.get().log(fApfloat, new Apfloat(10)));
  }

  @Override
  public ApfloatElement log1p() {
    return valueOf(ApfloatField.get().log(fApfloat.add(Apfloat.ONE)));
  }

  @Override
  public ApfloatElement multiply(double value) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(h.multiply(fApfloat, new Apfloat(value)));
  }

  @Override
  public ApfloatElement pow(double value) {
    return valueOf(ApfloatField.get().pow(fApfloat, new Apfloat(value)));
  }

  @Override
  public ApfloatElement pow(ApfloatElement value) throws MathIllegalArgumentException {
    return valueOf(ApfloatField.get().pow(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement reciprocal() {
    return valueOf(ApfloatField.get().inverseRoot(fApfloat, 1));
  }

  @Override
  public ApfloatElement remainder(double value) {
    return valueOf(ApfloatField.get().mod(fApfloat, new Apfloat(value)));
  }

  @Override
  public ApfloatElement remainder(ApfloatElement value) {
    return valueOf(ApfloatField.get().mod(fApfloat, value.fApfloat));
  }

  @Override
  public ApfloatElement rint() {
    return valueOf(apfloatRint(fApfloat));
  }

  public static Apfloat apfloatRint(Apfloat fApfloat) {
    if (fApfloat.scale() > 0) {
      return ApfloatMath.round(fApfloat, fApfloat.scale(), RoundingMode.HALF_EVEN);
    }
    if (ApfloatMath.abs(fApfloat).compareTo(new Apfloat("0.5")) <= 0) {
      return Apfloat.ZERO;
    }
    return ApfloatMath.copySign(Apfloat.ONE, fApfloat);
  }

  @Override
  public ApfloatElement scalb(int n) {
    FixedPrecisionApfloatHelper h = ApfloatField.get();
    return valueOf(h.multiply(fApfloat, h.pow(new Apfloat(2), n)));
  }

  @Override
  public ApfloatElement sqrt() {
    return valueOf(ApfloatField.get().sqrt(fApfloat));
  }

  @Override
  public ApfloatElement subtract(double value) {
    return valueOf(ApfloatField.get().subtract(fApfloat, new Apfloat(value)));
  }

  @Override
  public ApfloatElement abs() {
    return valueOf(ApfloatField.get().abs(fApfloat));
  }

  @Override
  public ApfloatElement newInstance(double d) {
    return valueOf(d);
  }

  @Override
  public FieldSinCos<ApfloatElement> sinCos() {
    return new FieldSinCos<ApfloatElement>(sin(), cos());
  }

  @Override
  public FieldSinhCosh<ApfloatElement> sinhCosh() {
    return new FieldSinhCosh<ApfloatElement>(sinh(), cosh());
  }

  @Override
  public String toString() {
    return fApfloat.toString();
  }
}
