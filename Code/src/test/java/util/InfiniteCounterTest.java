package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfiniteCounterTest {

    @Test
    void getFewConsecutiveValues() {
        // Given
        InfiniteCounter counter = new InfiniteCounter(0);

        // When
        int a = counter.next();
        int b = counter.next();
        int c = counter.next();

        // Then
        assertEquals(0, a);
        assertEquals(1, b);
        assertEquals(2, c);
    }
}