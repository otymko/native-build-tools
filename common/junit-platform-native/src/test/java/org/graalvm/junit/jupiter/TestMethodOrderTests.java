/*
 * Copyright (c) 2021, 2021 Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.graalvm.junit.jupiter;

import org.graalvm.junit.util.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class TestMethodOrderTests {

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public static class ArgumentOrderer {

        @Test
        @Order(2)
        public void testTwo() {
            Calculator.testAddition(2, 5);
        }

        @Test
        @Order(1)
        public void testOne() {
            Calculator.testAddition(9, 1);
        }
    }

    @TestMethodOrder(MethodOrderer.DisplayName.class)
    public static class DisplayNameOrderer {

        @Test
        @DisplayName("B - I must be second")
        public void testTwo() {
            Calculator.testAddition(3, 5);
        }

        @Test
        @DisplayName("A - I must be first")
        public void testOne() {
            Calculator.testAddition(1, 1);
        }
    }

    @TestMethodOrder(MethodOrderer.MethodName.class)
    public static class MethodNameOrderer {

        @Test
        public void testB() {
            Calculator.testAddition(1, 7);
        }

        @Test
        public void testA() {
            Calculator.testAddition(0, 1);
        }
    }

    @TestMethodOrder(MethodOrderer.Random.class)
    public static class RandomOrderer {

        @Test
        public void testOne() {
            Calculator.testAddition(2, 5);
        }

        @Test
        public void testTwo() {
            Calculator.testAddition(50, 51);
        }
    }
}
