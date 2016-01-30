package com.mdagis.setcalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;


public class SetCalculatorBuilder {

    private final Deque<SetNode> setNodeList = new ArrayDeque<>();

    public SetCalculatorBuilder() {
    }

    public Set calculate() {
        while (setNodeList.size() > 1) {
            reduce(setNodeList);
        }
        return setNodeList.getFirst().getNodeSet();
    }

    public SetCalculatorBuilder addSet(Set s) {

        SetNode sl1 = new SetNode();
        sl1.setNodeSet(s);
        setNodeList.add(sl1);
        return this;

    }

    public SetCalculatorBuilder level(int setLevel) {
        setNodeList.getLast().setLevel(setLevel);
        return this;
    }

    public SetCalculatorBuilder and() {
        setNodeList.getLast().setOperator(SetCalculateOperators.AND);
        return this;
    }

    public SetCalculatorBuilder or() {
        setNodeList.getLast().setOperator(SetCalculateOperators.OR);
        return this;
    }
    
    public SetCalculatorBuilder notIn() {
        setNodeList.getLast().setOperator(SetCalculateOperators.NOT_IN);
        return this;
    }

    private Set reduce(Deque<SetNode> selectionList) {

        // Remove the first selection from selection list
        SetNode left = selectionList.pollFirst();

        // reducedSet will hold either the product of the adjastent higher levels selections 
        // or the next selection if it is on same level 
        Set reducedSet;
        if (left.getLevel() < selectionList.getFirst().getLevel()) {
            reducedSet = reduce(selectionList);
        } else {
            reducedSet = selectionList.getFirst().getNodeSet();
        }

        // Join left set with reducedSet
        Set iset = OperatorHandler.calculateSets(left.getNodeSet(), reducedSet, left.getOperator());

        //  ... and the outcome store it on the first selection of the list
        selectionList.getFirst().setNodeSet(iset);

        return iset;

    }

}
