package org.symja.experimental;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
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
  public ApfloatElement cbrt() { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement ceil() { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement copySign(ApfloatElement arg0) { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement copySign(double arg0) { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement divide(double value) {
    return valueOf(fApfloat.divide(new Apfloat(value, fApfloat.precision())));
  }

  @Override
  public ApfloatElement expm1() { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement floor() { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getReal() {
    return fApfloat.doubleValue();
  }

  @Override
  public ApfloatElement hypot(ApfloatElement arg0)
      throws MathIllegalArgumentException { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement linearCombination(ApfloatElement[] arg0, ApfloatElement[] arg1)
      throws MathIllegalArgumentException { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement linearCombination(double[] arg0, ApfloatElement[] arg1)
      throws MathIllegalArgumentException { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement linearCombination(
      ApfloatElement arg0,
      ApfloatElement arg1,
      ApfloatElement arg2,
      ApfloatElement arg3) { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement linearCombination(
      double arg0,
      ApfloatElement arg1,
      double arg2,
      ApfloatElement arg3) { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement linearCombination(
      ApfloatElement arg0,
      ApfloatElement arg1,
      ApfloatElement arg2,
      ApfloatElement arg3,
      ApfloatElement arg4,
      ApfloatElement arg5) { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement linearCombination(
      double arg0,
      ApfloatElement arg1,
      double arg2,
      ApfloatElement arg3,
      double arg4,
      ApfloatElement arg5) { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement linearCombination(
      ApfloatElement arg0,
      ApfloatElement arg1,
      ApfloatElement arg2,
      ApfloatElement arg3,
      ApfloatElement arg4,
      ApfloatElement arg5,
      ApfloatElement arg6,
      ApfloatElement arg7) { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement linearCombination(
      double arg0,
      ApfloatElement arg1,
      double arg2,
      ApfloatElement arg3,
      double arg4,
      ApfloatElement arg5,
      double arg6,
      ApfloatElement arg7) { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement log10() { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement log1p() { // TODO Auto-generated method stub
    return null;
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
  public ApfloatElement reciprocal() { // TODO Auto-generated method stub
    return null;
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
  public ApfloatElement rint() { // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ApfloatElement scalb(int arg0) { // TODO Auto-generated method stub
    return null;
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
  public ApfloatElement abs() { // TODO Auto-generated method stub
    return valueOf(ApfloatMath.abs(fApfloat));
  }

  @Override
  public ApfloatElement newInstance(double arg0) { // TODO Auto-generated method stub
    return null;
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
