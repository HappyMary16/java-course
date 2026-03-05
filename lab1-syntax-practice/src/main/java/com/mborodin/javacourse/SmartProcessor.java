package com.mborodin.javacourse;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public interface SmartProcessor {

    /**
     * Потрібно повернути список рядків, перетворених у верхній регістр,
     * довжина яких більша за заданий поріг.
     */
    List<String> filterAndUppercase(List<String> input, int minLength);

    /**
     * Потрібно порахувати суму всіх парних чисел у списку.
     * Якщо список порожній, повернути 0.
     */
    long sumOfEvens(List<Integer> numbers);

    /**
     * Потрібно згрупувати список рядків за їхньою довжиною.
     * Наприклад: ["a", "bb", "c"] -> {1: ["a", "c"], 2: ["bb"]}
     */
    Map<Integer, List<String>> groupByLength(List<String> strings);

    /**
     * Потрібно знайти перший елемент, що відповідає умові (Predicate),
     * і повернути його у вигляді Optional.
     */
    <T> Optional<T> findFirstMatching(List<T> elements, Predicate<T> condition);

    /**
     * Потрібно об'єднати список списків в один плоский список (Flattening).
     */
    <T> List<T> flattenList(List<List<T>> nestedList);

    /**
     * Потрібно повернути опис дня тижня за його номером (1-7).
     * Використовуй switch expression.
     * 1-5 -> "Working Day"
     * 6-7 -> "Weekend"
     * Будь-яке інше число -> "Invalid Day"
     * <p>
     * Потрібно використати switch case  оператор.
     */
    String getDayType(int dayNumber);
}
