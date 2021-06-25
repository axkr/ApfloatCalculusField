package org.symja.experimental;

import java.util.function.Predicate;

import org.apfloat.Apfloat;
import org.apfloat.FixedPrecisionApfloatHelper;
import org.hipparchus.linear.BlockFieldMatrix;
import org.hipparchus.linear.FieldDecompositionSolver;
import org.hipparchus.linear.FieldLUDecomposition;
import org.hipparchus.linear.FieldMatrix;
import org.hipparchus.special.elliptic.carlson.CarlsonEllipticIntegral;

import junit.framework.TestCase;

public class TestHipparchus extends TestCase {

  public TestHipparchus(String name) {
    super(name);
  }

  public void testCarlsonRC001() {
    FixedPrecisionApfloatHelper helper = new FixedPrecisionApfloatHelper(25);
    try {
      ApfloatField.set(helper);

      ApfloatElement x = ApfloatElement.valueOf("3");
      ApfloatElement y = ApfloatElement.valueOf("1.234567890123456789012345");
      Apfloat ulp = helper.ulp(x.fApfloat);
      assertEquals("1e-24", ulp.toString());
      ApfloatElement rC = CarlsonEllipticIntegral.rC(x, y);
      assertEquals("7.62626353743812501642456e-1", rC.toString());
    } finally {
      ApfloatField.remove();
    }
  }

  public void testCarlsonRC002() {
    FixedPrecisionApfloatHelper helper = new FixedPrecisionApfloatHelper(500);
    try {
      ApfloatField.set(helper);
      ApfloatElement x = ApfloatElement.valueOf("3");
      ApfloatElement y = ApfloatElement.valueOf("5");
      Apfloat ulp = helper.ulp(x.fApfloat);
      assertEquals("1e-499", ulp.toString());
      ApfloatElement rC = CarlsonEllipticIntegral.rC(x, y);
      // fails
      assertEquals(
          "4.841695916515624791205082963674247....", //
          rC.toString());
    } finally {
      ApfloatField.remove();
    }
  }

  public void testCarlsonRCComplex001() {
    FixedPrecisionApfloatHelper helper = new FixedPrecisionApfloatHelper(25);
    try {
      ApfloatField.set(helper);
      ApcomplexElement x = ApcomplexElement.valueOf("3");
      ApcomplexElement y = ApcomplexElement.valueOf("1.234567890123456789012345");
      Apfloat ulp = helper.ulp(x.fApcomplex.real());
      assertEquals("1e-24", ulp.toString());
      ApcomplexElement rC = CarlsonEllipticIntegral.rC(x, y);
      assertEquals("7.62626353743812501642456e-1", rC.toString());
    } finally {
      ApfloatField.remove();
    }
  }

  public void testCarlsonRCComplex002() {
    FixedPrecisionApfloatHelper helper = new FixedPrecisionApfloatHelper(25);
    try {
      ApfloatField.set(helper);
      // exp( I*Pi/7)
      ApcomplexElement x =
          ApcomplexElement.valueOf(
              "0.90096886790241912623610231950744505116", //
              "0.43388373911755812047576833284835875461");
      // exp( I*Pi/3)
      ApcomplexElement y =
          ApcomplexElement.valueOf(
              "0.5", //
              "0.86602540378443864676372317075293618347");
      Apfloat ulp = helper.ulp(x.fApcomplex.real());
      assertEquals("1e-25", ulp.toString());
      ApcomplexElement rC = CarlsonEllipticIntegral.rC(x, y);
      assertEquals(
          "(9.187794900307647195211861e-1, -4.146529970365840028803552e-1)", rC.toString());
    } finally {
      ApfloatField.remove();
    }
  }

  public void testInverse001() {
    FixedPrecisionApfloatHelper helper = new FixedPrecisionApfloatHelper(30);
    try {
      ApfloatField.set(helper);
      // 2.161209223472559 + 1.682941969615793
      ApcomplexElement x =
          ApcomplexElement.valueOf(
              "2.161209223472559", //
              "1.682941969615793");
      // 2.161209223472559 - 1.682941969615793
      ApcomplexElement y =
          ApcomplexElement.valueOf(
              "2.161209223472559", //
              "-1.682941969615793");
      ApcomplexElement z = ApcomplexElement.valueOf("2.0");
      ApcomplexElement[][] arr = new ApcomplexElement[2][2];
      arr[0][0] = z;
      arr[0][1] = x;
      arr[1][0] = y;
      arr[1][1] = z;
      FieldMatrix<ApcomplexElement> m = new BlockFieldMatrix<ApcomplexElement>(arr);
      FieldMatrix<ApcomplexElement> s = inverseMatrix(m);
      assertEquals(
          "Array2DRowFieldMatrix{" //
              + "{-5.70919803469126542265150729137e-1,(6.16938572560308485152555018491e-1, 4.80412449271496636143769164508e-1)},"
              + "{(6.16938572560308485152555018491e-1, -4.80412449271496636143769164508e-1),-5.70919803469126542265150729137e-1}}", //
          s.toString());
    } finally {
      ApfloatField.remove();
    }
  }

  private static FieldMatrix<ApcomplexElement> inverseMatrix(FieldMatrix<ApcomplexElement> matrix) {
    final FieldLUDecomposition<ApcomplexElement> lu =
        new FieldLUDecomposition<ApcomplexElement>(matrix);
    FieldDecompositionSolver<ApcomplexElement> solver = lu.getSolver();
    if (!solver.isNonSingular()) {
      // Matrix `1` is singular.
      return null;
    }
    return solver.getInverse();
  }
}
