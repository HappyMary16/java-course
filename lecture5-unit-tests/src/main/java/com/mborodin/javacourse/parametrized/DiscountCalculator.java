package com.mborodin.javacourse.parametrized;

public class DiscountCalculator {

    /**
     * Розраховує фінальну ціну:
     * - Якщо сума < 100: знижка 0%
     * - Якщо 100 <= сума < 500: знижка 10%
     * - Якщо сума >= 500: знижка 20%
     */
    public double calculateFinalPrice(double originalPrice) {
        if (originalPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (originalPrice >= 500) {
            return originalPrice * 0.8;
        } else if (originalPrice >= 100) {
            return originalPrice * 0.9;
        } else {
            return originalPrice;
        }
    }
}
