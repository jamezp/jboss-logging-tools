/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2016 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.logging.annotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates the {@linkplain Throwable throwable} should be copied with the message from the method and prepended to the
 * message from this parameter.
 * <p>
 * This annotation is only allowed on one parameter and the parameters type must be the same as the return type. If no
 * {@link Cause @Cause} parameter is used the {@link Throwable#getClass()} is used to initialize the cause of the
 * returned exception.
 * </p>
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
@Retention(CLASS)
@Target(PARAMETER)
@Documented
public @interface CopyFrom {

    /**
     * An array of possible types to check the parameter instance for. If the type matches the parameter that type will
     * be constructed and returned. The types must be assignable to the parameters type.
     * <p>
     * If the parameter type doesn't match any of the types in the array the parameter type will be reconstructed and
     * returned from the method.
     * </p>
     * <p>
     * Example use case:
     * <pre>
     * {@code
     * RuntimeException error(@CopyFrom({IllegalStateException.class, IllegalArgumentException.class} RuntimeException cause);
     * }
     * </pre>
     * If the cause is a {@link IllegalStateException} a new {@link IllegalStateException} would be created and returned.
     * </p>
     *
     * @return an array of types allowed to be created and returned
     */
    Class<? extends Throwable>[] value() default {};
}
