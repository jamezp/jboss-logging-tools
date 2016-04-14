/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.logging.processor.generated;

import java.util.Arrays;
import java.util.Objects;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Cause;
import org.jboss.logging.annotations.CopyFrom;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.annotations.Param;
import org.jboss.logging.annotations.Pos;
import org.jboss.logging.annotations.Property;
import org.jboss.logging.annotations.Signature;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
@MessageBundle(projectCode = "MSG")
public interface CopyMessages {

    CopyMessages MESSAGES = Messages.getBundle(CopyMessages.class);

    String SIMPLE_MESSAGE = "Simple exception message";

    @Message(SIMPLE_MESSAGE)
    EqualityException simpleException(@CopyFrom EqualityException original);

    EqualityException simpleException(@CopyFrom EqualityException original, @Cause Throwable cause);

    @Message(SIMPLE_MESSAGE)
    @Signature(String.class)
    EqualityException simpleInitCause(@CopyFrom EqualityException original, @Cause Throwable cause);

    String SIMPLE_ARG_MESSAGE = "Simple message with value '%s'";

    @Message(SIMPLE_ARG_MESSAGE)
    ValueEqualityException simpleValueException(@Param @Pos(1) String value, @CopyFrom ValueEqualityException original);

    ValueEqualityException simpleValueException(@Cause Throwable cause, @Property @Pos(1) String value, @CopyFrom ValueEqualityException original);

    @Message(SIMPLE_MESSAGE)
    EqualityException parameterTypeCheck(@CopyFrom(ValueEqualityException.class) EqualityException original);

    @Message(SIMPLE_MESSAGE)
    RuntimeException multiTypeCheck(@CopyFrom({IllegalArgumentException.class, IllegalStateException.class}) RuntimeException original);

    class EqualityException extends IllegalArgumentException {

        public EqualityException() {
        }

        public EqualityException(final String message) {
            super(message);
        }

        public EqualityException(final String message, final Throwable cause) {
            super(message, cause);
        }

        public EqualityException(final Throwable cause) {
            super(cause);
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EqualityException)) {
                return false;
            }
            final EqualityException other = (EqualityException) obj;
            return Objects.equals(getCause(), other.getCause())
                    && Arrays.equals(getStackTrace(), other.getStackTrace());
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = 31 * hash + Objects.hashCode(getCause());
            hash = 31 * hash + Arrays.hashCode(getStackTrace());
            return hash;
        }
    }

    class ValueEqualityException extends EqualityException{
        private Object value;

        public ValueEqualityException() {
        }

        public ValueEqualityException(final String message) {
            super(message);
        }

        public ValueEqualityException(final Object value, final String message) {
            super(message);
            this.value = value;
        }

        public ValueEqualityException(final String message, final Throwable cause) {
            super(message, cause);
        }

        public ValueEqualityException(final Object value, final String message, final Throwable cause) {
            super(message, cause);
            this.value = value;
        }

        public ValueEqualityException(final Throwable cause) {
            super(cause);
        }

        public ValueEqualityException(final Object value, final Throwable cause) {
            super(cause);
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(final Object value) {
            this.value = value;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ValueEqualityException)) {
                return false;
            }
            final ValueEqualityException other = (ValueEqualityException) obj;
            return Objects.equals(value, other.value)
                    && Objects.equals(getCause(), other.getCause())
                    && Arrays.equals(getStackTrace(), other.getStackTrace());
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = 31 * hash + Objects.hashCode(value);
            hash = 31 * hash + Objects.hashCode(getCause());
            hash = 31 * hash + Arrays.hashCode(getStackTrace());
            return hash;
        }
    }
}
