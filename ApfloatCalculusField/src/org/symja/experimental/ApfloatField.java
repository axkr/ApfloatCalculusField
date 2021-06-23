package org.symja.experimental;

import org.apfloat.Apfloat;
import org.apfloat.FixedPrecisionApfloatHelper;
import org.hipparchus.Field;

final class ApfloatField implements Field<ApfloatElement> {
  public static final Field<ApfloatElement> APFLOAT_FIELD = new ApfloatField();

  /** {@inheritDoc} */
  private static final transient ThreadLocal<FixedPrecisionApfloatHelper> instance =
      new ThreadLocal<FixedPrecisionApfloatHelper>() {

        @Override
        public FixedPrecisionApfloatHelper initialValue() {
          // default precision = 16
          return new FixedPrecisionApfloatHelper(16);
        }
      };

  /**
   * Get the thread local {@link FixedPrecisionApfloatHelper} instance.
   *
   * @return
   */
  public static FixedPrecisionApfloatHelper get() {
    return instance.get();
  }

  /**
   * Set the thread local {@link FixedPrecisionApfloatHelper} instance.
   *
   * @param helper
   */
  public static void set(final FixedPrecisionApfloatHelper helper) {
    instance.set(helper);
  }

  /**
   * Removes the current thread's value for this thread-local variable. If this thread-local
   * variable is subsequently read by the current thread, its value will be reinitialized by
   * invoking its initialValue method, unless its value is set by the current thread in the interim.
   * This may result in multiple invocations of the initialValue method in the current thread.
   */
  public static void remove() {
    instance.remove();
  }

  /** {@inheritDoc} */
  @Override
  public ApfloatElement getOne() {
    return ApfloatElement.valueOf(new Apfloat(1));
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
