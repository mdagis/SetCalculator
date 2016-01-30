package com.mdagis.setcalculator;

import java.util.Set;

public class OperatorHandler {

    public static Set calculateSets(Set set1, Set set2, SetCalculateOperators operator) {
        switch (operator) {
            case AND:
                return andOperator(set1, set2);
            case OR:
                return orOperator(set1, set2);
            case NOT_IN:
                return notInOperator(set1, set2);
            // In case an invalid operator is passed return the left operant.
            default:
                return set1;
        }

    }

    private static Set andOperator(Set set1, Set set2) {

        set1.retainAll(set2);
        return set1;

    }

    private static Set orOperator(Set set1, Set set2) {

        set1.addAll(set2);
        return set1;

    }

    private static Set notInOperator(Set set1, Set set2) {

        set1.removeAll(set2);
        return set1;

    }

}
