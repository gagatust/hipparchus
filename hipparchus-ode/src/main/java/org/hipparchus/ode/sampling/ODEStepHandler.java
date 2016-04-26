/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
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

package org.hipparchus.ode.sampling;

import org.hipparchus.exception.MathIllegalStateException;
import org.hipparchus.ode.ODEStateAndDerivative;


/**
 * This interface represents a handler that should be called after
 * each successful step.
 *
 * <p>The ODE integrators compute the evolution of the state vector at
 * some grid points that depend on their own internal algorithm. Once
 * they have found a new grid point (possibly after having computed
 * several evaluation of the derivative at intermediate points), they
 * provide it to objects implementing this interface. These objects
 * typically either ignore the intermediate steps and wait for the
 * last one, store the points in an ephemeris, or forward them to
 * specialized processing or output methods.</p>
 *
 * @see org.hipparchus.ode.ODEIntegrator
 * @see ODEStateInterpolator
 */

public interface ODEStepHandler {

    /** Initialize step handler at the start of an ODE integration.
     * <p>
     * This method is called once at the start of the integration. It
     * may be used by the step handler to initialize some internal data
     * if needed.
     * </p>
     * <p>
     * The default implementation does nothing
     * </p>
     * @param initialState initial time, state vector and derivative
     * @param finalTime target time for the integration
     */
    default void init(ODEStateAndDerivative initialState, double finalTime) {
        // nothing by default
    }

    /**
     * Handle the last accepted step
     * @param interpolator interpolator for the last accepted step. For
     * efficiency purposes, the various integrators reuse the same
     * object on each call, so if the instance wants to keep it across
     * all calls (for example to provide at the end of the integration a
     * continuous model valid throughout the integration range, as the
     * {@link org.hipparchus.migration.ode.ContinuousOutputModel
     * ContinuousOutputModel} class does), it should build a local copy
     * using the clone method of the interpolator and store this copy.
     * Keeping only a reference to the interpolator and reusing it will
     * result in unpredictable behavior (potentially crashing the application).
     * @param isLast true if the step is the last one
     * @exception MathIllegalStateException if the interpolator throws one because
     * the number of functions evaluations is exceeded
     */
    void handleStep(ODEStateInterpolator interpolator, boolean isLast)
        throws MathIllegalStateException;

}
