package org.symja.experimental;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.hipparchus.special.elliptic.carlson.CarlsonEllipticIntegral;

import junit.framework.TestCase;

public class TestHipparchus extends TestCase {

  public TestHipparchus(String name) {
    super(name);
  }
  
  public void testCarlson() {
	  ApfloatElement x = ApfloatElement.valueOf("3", 25);
	  ApfloatElement y = ApfloatElement.valueOf("1.234567890123456789012345", 25);
	  Apfloat ulp = ApfloatMath.ulp(x.fApfloat);
	  assertEquals("1e-24", ulp.toString());
      ApfloatElement rC = CarlsonEllipticIntegral.rC(x, y);
      assertEquals("", rC.toString());
  }
}
