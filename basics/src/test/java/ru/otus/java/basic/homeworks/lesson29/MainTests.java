package ru.otus.java.basic.homeworks.lesson29;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTests {

    @Test
    void listHasItemsAfterOneNumber() {
        assertArrayEquals(
                new int[] {2, 2, 3},
                Main.getItemsAfterLastOneNumber(new int[] {1, 2, 1, 2, 2, 3}));
    }

    @Test
    void listHasNoItemsAfterOneNumber() {
        assertArrayEquals(
                new int[] {},
                Main.getItemsAfterLastOneNumber(new int[] {1, 2, 1, 2, 2, 1}));
    }

    @Test
    void listHasNoOneNumber() {
        assertThrowsExactly(IllegalArgumentException.class, () ->
                Main.getItemsAfterLastOneNumber(new int[] {2, 2, 2, 3}));
    }

    @ParameterizedTest
    @MethodSource("provideArraysForContainsOnlyOnesAndTwos")
    void listHasOnlyOnesAndTwos(int[] input, boolean expected) {
        assertEquals(expected, Main.isContainsOnlyOnesAndTwos(input));
    }

    @Test
    void listHasOnesAndHasNoTwos() {
        assertFalse(Main.isContainsOnlyOnesAndTwos(new int[] {1, 1, 1}));
        assertFalse(Main.isContainsOnlyOnesAndTwos(new int[] {1, 3, 1, 1}));
    }

    @Test
    void listHasTwosAndHasNoOnes() {
        assertFalse(Main.isContainsOnlyOnesAndTwos(new int[] {2, 2}));
        assertFalse(Main.isContainsOnlyOnesAndTwos(new int[] {2, 4, 2}));
    }

    @Test
    void listHasOnesAndHasTwosAndHasOthers() {
        assertFalse(Main.isContainsOnlyOnesAndTwos(new int[] {1, 2, 3, 4}));
    }

    @Test
    void listHasNoOnesAndHasNoTwos() {
        assertFalse(Main.isContainsOnlyOnesAndTwos(new int[] {5, 2, 3, 4}));
        assertFalse(Main.isContainsOnlyOnesAndTwos(new int[] {}));
    }

    private static Stream<Arguments> provideArraysForContainsOnlyOnesAndTwos() {
        return Stream.of(
                Arguments.of(new int[] {5, 2, 3, 4}, false),
                Arguments.of(new int[] {}, false),
                Arguments.of(new int[] {1, 2}, true),
                Arguments.of(new int[] {1, 1}, false),
                Arguments.of(new int[] {1, 3}, false),
                Arguments.of(new int[] {1, 2, 2, 1}, true)
        );
    }
}