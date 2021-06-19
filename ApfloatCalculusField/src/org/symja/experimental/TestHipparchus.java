package org.symja.experimental;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.hipparchus.special.elliptic.carlson.CarlsonEllipticIntegral;

import junit.framework.TestCase;

public class TestHipparchus extends TestCase {

  public TestHipparchus(String name) {
    super(name);
  }

  public void testCarlsonRC001() {
    ApfloatElement x = ApfloatElement.valueOf("3", 25);
    ApfloatElement y = ApfloatElement.valueOf("1.234567890123456789012345", 25);
    Apfloat ulp = ApfloatMath.ulp(x.fApfloat);
    assertEquals("1e-24", ulp.toString());
    ApfloatElement rC = CarlsonEllipticIntegral.rC(x, y);
    assertEquals("7.62626353743812501642456e-1", rC.toString());
  }

  public void testCarlsonRC002() {
    ApfloatElement x = ApfloatElement.valueOf("3", 500);
    ApfloatElement y = ApfloatElement.valueOf("5", 500);
    Apfloat ulp = ApfloatMath.ulp(x.fApfloat);
    assertEquals("1e-499", ulp.toString());
    ApfloatElement rC = CarlsonEllipticIntegral.rC(x, y);
    // fails
    assertEquals(
        "4.841695916515624791205082963674247....", //
        rC.toString());
  }

  public void testCarlsonRCComplex001() {
    ApcomplexElement x = ApcomplexElement.valueOf("3", 25);
    ApcomplexElement y = ApcomplexElement.valueOf("1.234567890123456789012345", 25);
    Apfloat ulp = ApfloatMath.ulp(x.fApcomplex.real());
    assertEquals("1e-24", ulp.toString());
    ApcomplexElement rC = CarlsonEllipticIntegral.rC(x, y);
    assertEquals("7.62626353743812501642456e-1", rC.toString());
  }

  public void testCarlsonRCComplex002() {
    // exp( I*Pi/7)
    ApcomplexElement x =
        ApcomplexElement.valueOf(
            "0.90096886790241912623610231950744505116", //
            "0.43388373911755812047576833284835875461",
            25);
    // exp( I*Pi/3)
    ApcomplexElement y =
        ApcomplexElement.valueOf(
            "0.5", //
            "0.86602540378443864676372317075293618347",
            25);
    Apfloat ulp = ApfloatMath.ulp(x.fApcomplex.real());
    assertEquals("1e-25", ulp.toString());
    ApcomplexElement rC = CarlsonEllipticIntegral.rC(x, y);
    assertEquals("(9.187794900307647195211861e-1, -4.146529970365840028803552e-1)", rC.toString());
  }
}
