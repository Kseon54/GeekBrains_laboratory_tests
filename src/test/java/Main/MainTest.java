package Main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MainTest {
    @ParameterizedTest
    @MethodSource("valuesForReturnArrayAfterFour")
    void shouldReturnArrayAfterFour_whenPassingArray(int[] expected, int[] array) {
        Assertions.assertArrayEquals(expected, array);
    }

    private static Stream<Arguments> valuesForReturnArrayAfterFour() {
        return Stream.of(
                Arguments.of(new int[]{1, 7}, Main.returnArrayAfterFour(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})),
                Arguments.of(new int[]{}, Main.returnArrayAfterFour(new int[]{3, 4})),
                Arguments.of(new int[]{-4, 7}, Main.returnArrayAfterFour(new int[]{1, 3, 4, -4, 7}))
        );
    }

    @Test
    void shouldThrowRuntimeException_whenFourNotFound() {
        Assertions.assertThrows(RuntimeException.class, () -> Main.returnArrayAfterFour(new int[]{1, 3, -4, -4, 7}));
    }

    @ParameterizedTest
    @MethodSource("valuesIsFountOneAntFourTrue")
    void shouldIsFountOneAntFourTrue_whenPassingArray(boolean bool) {
        Assertions.assertTrue(bool);
    }

    private static Stream<Arguments> valuesIsFountOneAntFourTrue() {
        return Stream.of(
                Arguments.of(Main.isFountOneAntFour(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})),
                Arguments.of(Main.isFountOneAntFour(new int[]{1,2,3,6,5,4}))
        );
    }

    @ParameterizedTest
    @MethodSource("valuesIsFountOneAntFourFalse")
    void shouldIsFountOneAntFourFalse_whenPassingArray(boolean bool) {
        Assertions.assertFalse(bool);
    }

    private static Stream<Arguments> valuesIsFountOneAntFourFalse() {
        return Stream.of(
                Arguments.of(Main.isFountOneAntFour(new int[]{1, 2, -4, 2, 3, -4, 1, 7})),
                Arguments.of(Main.isFountOneAntFour(new int[]{3,2,3,6,5,4}))
        );
    }
}
