/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.rule.internal;

import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;

/**
 * Describes a particular {@linkplain #apply(Object, Object) binary operation}
 * on a type {@code <U>}. That operation must satisfy the following requirements,
 * where {@code ==} represents {@code equals}, and {@code u + v} represents {@code apply(u, v)}:
 * <pre>
 *  Identity element:
 *  u, z : U,
 *  z == zero() =>
 *      u + z == u
 *      z + u == u
 *
 *  Associativity:
 *  u, v, w : U =>
 *      (u + v) + w == u + (v + w)
 * </pre>
 *
 * @param <U> Domain of the operation
 */
interface Monoid<U> extends BinaryOperator<U> {

    /** Combine two U, in a way consistent with {@link #zero()}. This method must not produce side-effects. */
    @Override
    U apply(U u, U u2);


    /** Neutral element for the {@link #apply} operation. */
    U zero();


    static <T> Monoid<Set<T>> forSet() {
        return (Monoid<Set<T>>) MonoidImpl.SET_MONOID;
    }


    static <T> Monoid<List<T>> forList() {
        return (Monoid<List<T>>) MonoidImpl.LIST_MONOID;
    }
}
