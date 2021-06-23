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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MethodSourceTests {

    @ParameterizedTest
    @MethodSource
    public void testEmptyMethodSource(int a, int b) {
        Calculator.testAddition(a, b);
    }

    public static Stream<Arguments> testEmptyMethodSource() {
        return Stream.of(
                Arguments.of(1, 5),
                Arguments.of(7, 12),
                Arguments.of(9, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("getTestInputs")
    public void testSameClassMethodSource(int a, int b) {
        Calculator.testAddition(a, b);
    }

    private static Stream<Arguments> getTestInputs() {
        return Stream.of(
                Arguments.of(31, 32),
                Arguments.of(1, 1),
                Arguments.of(5, 7)
        );
    }

    @ParameterizedTest
    @MethodSource("org.graalvm.junit.jupiter.MethodSourceProvider#getInputs")
    public void testOtherClassMethodSource(int a, int b) {
        Calculator.testAddition(a, b);
    }

    @ParameterizedTest
    @MethodSource({"org.graalvm.junit.jupiter.MethodSourceProvider#getInputs", "getTestInputs", "org.graalvm.junit.jupiter.MethodSourceTests#testEmptyMethodSource"})
    public void combinedTests(int a, int b) {
        Calculator.testAddition(a, b);
    }
}

class MethodSourceProvider {

    public static Stream<Arguments> getInputs() {
        return Stream.of(
                Arguments.of(33, 35),
                Arguments.of(99, 1)
        );
    }

}
