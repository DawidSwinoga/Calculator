package com.dawid.calculator.utils;

import android.text.TextUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Dawid Świnoga on 16.03.2017.
 */

public class ReversPolishNotationParser {
    public static List<String> parse(List<String> separatedSigns) throws UnknownSignException {
        Deque<String> stack = new ArrayDeque<>(separatedSigns.size());
        List<String> reversNotation = new ArrayList<>(separatedSigns.size());

        for (String sign : separatedSigns) {
            if (DoubleParser.parse(sign) != null) {
                reversNotation.add(sign);
            } else {
                reversNotation.addAll(getNoNumberSigns(stack, sign));
            }
        }

        addRestSignInStackToReversNotation(stack, reversNotation);

        return reversNotation;
    }

    private static void addRestSignInStackToReversNotation(Deque<String> stack,
                                                           List<String> reversNotation) {
        while (!stack.isEmpty()) {
            reversNotation.add(stack.pollFirst());
        }
    }

    private static List<String> getNoNumberSigns(Deque<String> stack, String sign)
            throws UnknownSignException {
        List<String> signsToOutput = new ArrayList<>();

        throwExceptionIfSingIsUnknow(sign);

        while (stack.size() > 0 && compareSign(stack.peekFirst(), sign) >= 0) {
            signsToOutput.add(stack.pollFirst());
        }

        stack.addFirst(sign);

        return signsToOutput;
    }

    private static int compareSign(String sign, String sign2) throws UnknownSignException {
        return getSignPriority(sign) - getSignPriority(sign2);
    }

    private static int getSignPriority(String sign) throws UnknownSignException {
        switch (sign) {
            case OperationType.ADDITION:
            case OperationType.SUBTRACTION:
                return 1;
            case OperationType.MULTIPLICATION:
            case OperationType.DIVISION:
                return 2;
            default:
                throw new UnknownSignException("The sign: " + sign + " is unknown");

        }
    }

    private static void throwExceptionIfSingIsUnknow(String sign) throws UnknownSignException {
        getSignPriority(sign);
    }
}
