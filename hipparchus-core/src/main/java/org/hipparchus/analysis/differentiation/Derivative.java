/*
 * Licensed to the Hipparchus project under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The Hipparchus project licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hipparchus.analysis.differentiation;

import org.hipparchus.RealFieldElement;
import org.hipparchus.exception.MathIllegalArgumentException;

/** Interface representing both the value and the differentials of a function.
 * @param <T> the type of the field elements
 * @since 1.7
 */
public interface Derivative<T extends RealFieldElement<T>> extends RealFieldElement<T> {

    /** Get the number of free parameters.
     * @return number of free parameters
     */
    int getFreeParameters();

    /** Get the derivation order.
     * @return derivation order
     */
    int getOrder();

    /** Get the value part of the function.
     * @return value part of the value of the function
     */
    double getValue();

    /** Get a partial derivative.
     * @param orders derivation orders with respect to each variable (if all orders are 0,
     * the value is returned)
     * @return partial derivative
     * @see #getValue()
     * @exception MathIllegalArgumentException if the numbers of variables does not
     * match the instance
     * @exception MathIllegalArgumentException if sum of derivation orders is larger
     * than the instance limits
     */
    double getPartialDerivative(final int ... orders)
        throws MathIllegalArgumentException;

}
