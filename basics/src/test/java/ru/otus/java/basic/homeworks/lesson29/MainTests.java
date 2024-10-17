package ru.otus.java.basic.homeworks.lesson29;


import org.junit.jupiter.api.Test;

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
        assertThrowsExactly(RuntimeException.class, () ->
                Main.getItemsAfterLastOneNumber(new int[] {2, 2, 2, 3}));
    }

    @Test
    void listHasOnlyOnesAndTwos() {
        assertTrue(Main.isContainsOnlyOnesAndTwos(new int[] {1, 2, 1, 2, 2, 1}));
        assertFalse(Main.isContainsOnlyOnesAndTwos(new int[] {1, 2, 1, 3, 2, 1}));
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
}