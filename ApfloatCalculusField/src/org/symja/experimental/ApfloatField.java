package org.symja.experimental;

import org.apfloat.Apfloat;
import org.hipparchus.Field;

final class ApfloatField implements Field<ApfloatElement> {
  public static final Field<ApfloatElement> EXPR_FIELD = new ApfloatField();

  @Override
  public ApfloatElement getOne() {
    return ApfloatElement.valueOf(new Apfloat(1, 25));
  }

  /** {@inheritDoc} */
  @Override
  public ApfloatElement getZero() {
    return ApfloatElement.valueOf(Apfloat.ZERO);
  }

  /** {@inheritDoc} */
  @Override
  public Class<ApfloatElement> getRuntimeClass() {
    return ApfloatElement.class;
  }
}
