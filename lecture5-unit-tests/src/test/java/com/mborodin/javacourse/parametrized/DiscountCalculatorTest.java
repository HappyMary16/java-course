package com.mborodin.javacourse.parametrized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountCalculatorTest {

    private final DiscountCalculator calculator = new DiscountCalculator();

    @ParameterizedTest(name = "Ціна {0} має перетворитися на {1}")
    @CsvSource({
            "50.0,   50.0",   // Без знижки
            "100.0,  90.0",   // 10% знижка
            "250.0,  225.0",  // 10% знижка
            "500.0,  400.0",  // 20% знижка
            "1000.0, 800.0"   // 20% знижка
    })
    void calculateFinalPrice_correctDiscountApplied(double input, double expected) {
        assertEquals(expected, calculator.calculateFinalPrice(input),
                     "Розрахунок знижки для суми " + input + " неправильний");
    }

    @Test
    @DisplayName("Має викидати виключення для від'ємних чисел")
    void calculateFinalPrice_negativeOriginalPrice() {
        assertThrows(IllegalArgumentException.class, () ->
                calculator.calculateFinalPrice(-1.0));
    }
}