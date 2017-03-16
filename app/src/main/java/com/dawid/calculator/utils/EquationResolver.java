package com.dawid.calculator.utils;

import android.support.v4.view.PagerAdapter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Dawid Świnoga on 16.03.2017.
 */

public class EquationResolver {
    public static double resolve(List<String> separatedSigns) throws UnknownSignException,
            WrongAmountOfOperatorsException {

        List<String> rpn = ReversPolishNotationParser.parse(separatedSigns);
        Deque<Double> result = new ArrayDeque<>();

        for (String sign : rpn) {
            Double number = DoubleParser.parse(sign);
            if (number != null) {
                result.addFirst(number);
            } else {

                try {
                    executeOperation(sign, result);
                } catch (NoSuchElementException e) {
                    throw new WrongAmountOfOperatorsException();
                }
            }
        }

        return result.pollFirst();
    }

    private static void executeOperation(String sign, Deque<Double> result)
            throws UnknownSignException, NoSuchElementException {

        Double secondNumber = result.removeFirst();
        switch (sign) {
            case OperationType.ADDITION:
                result.addFirst(result.removeFirst() + secondNumber);
                break;
            case OperationType.SUBTRACTION:
                result.addFirst(result.removeFirst() - secondNumber);
                break;
            case OperationType.MULTIPLICATION:
                result.addFirst(result.removeFirst() * secondNumber);
                break;
            case OperationType.DIVISION:
                result.addFirst(result.removeFirst() / secondNumber);
                break;
            default:
                throw new UnknownSignException("The sign: " + sign + " is unknown");
        }
    }
}
