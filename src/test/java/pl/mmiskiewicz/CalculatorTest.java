package pl.mmiskiewicz;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private Calculator calculator;

    static Stream<Arguments> getTwoArguments() {
        return Stream.of(
                Arguments.arguments("", "20"),
                Arguments.arguments("10", "wrong"),
                Arguments.arguments("wrong", "20")
        );
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Should add two correct numbers")
    void shouldAddTwoCorrectNumbers() {
        //given
        String a = "10";
        String b = "20";

        //when
        Integer result = calculator.add(a, b);

        //then
        assertEquals(30, result);
    }

    @ParameterizedTest
    @MethodSource("getTwoArguments")
    @DisplayName("Should throw an exception on invalid argument")
    void shouldThrowAnExceptionOnInvalidArgument(String a, String b) {
        //when
        Throwable throwable = catchThrowable(() -> calculator.add(a, b));

        //then
        assertThat(throwable)
                .isInstanceOf(NumberFormatException.class)
                .extracting(Throwable::getMessage, as(InstanceOfAssertFactories.STRING))
                .containsAnyOf(
                        "For input string: wrong",
                        "For input string: "
                );
    }

    @Test
    @DisplayName("Should throw exception on integer overflow")
    void shouldThrowExceptionOnIntegerOverflow() {
        //given
        String a = Integer.MAX_VALUE + "";
        String b = "11";

        //when
        Throwable throwable = catchThrowable(() -> calculator.add(a, b));

        //then
        assertThat(throwable)
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("integer overflow");
    }
}