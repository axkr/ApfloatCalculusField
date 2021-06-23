package org.symja.experimental;

import org.apfloat.Apfloat;
import org.hipparchus.Field;

final class ApcomplexField implements Field<ApcomplexElement> {
  public static final Field<ApcomplexElement> APCOMPLEX_FIELD = new ApcomplexField();

  /** {@inheritDoc} */
  @Override
  public ApcomplexElement getOne() {
    return ApcomplexElement.valueOf(new Apfloat(1));
  }

  /** {@inheritDoc} */
  @Override
  public ApcomplexElement getZero() {
    return ApcomplexElement.valueOf(Apfloat.ZERO);
  }

  /** {@inheritDoc} */
  @Override
  public Class<ApcomplexElement> getRuntimeClass() {
    return ApcomplexElement.class;
  }
}
