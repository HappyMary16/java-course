package com.mborodin.javacourse;

import java.util.List;

/**
 * Implement interface and cover all code by unit tests.
 */
public interface CoffeeShop {

    /**
     * User buys coffee, if there are no coffee throws checked exception.
     *
     * @throws Exception - checked exception.
     * You should create your class which describes exception and use it here.
     * Don't forget to add error message.
     */
    void buyCoffee() throws Exception;

    /**
     * User buys coffee if it is present.
     *
     * @return true - if caffe was present, else false
     */
    boolean buyCoffeeIfPresent();

    /**
     * Saves feedback if it doesn't contain anything bad.
     * Bad words: bad, worst, not tasty, not drinkable.
     *
     * @param feedback - users feedback
     * @throws RuntimeException - throws exception if feedback was bad.
     * You should create your class which describes exception and use it here.
     * Don't forget to add error message but maybe let's not say why we don't save feedback.
     */
    void giveFeedback(String feedback) throws RuntimeException;

    /**
     * Returns all user feedbacks.
     *
     * @return - all user feedbacks
     */
    List<String> readFeedbacks();

    /**
     * Return coffee amount in a coffee shop.
     *
     * @return - coffee amount
     */
    int coffeeAmount();
}
