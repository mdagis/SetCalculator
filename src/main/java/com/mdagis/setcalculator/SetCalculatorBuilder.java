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
        
        int defaultLevel = setNodeList.isEmpty()? 0 : setNodeList.peekLast().getLevel();
        
        SetNode sl1 = new SetNode();
        sl1.setNodeSet(s);
        setNodeList.add(sl1);
        setNodeList.getLast().setLevel(defaultLevel);
        
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

    private Set reduce(Deque<SetNode> nodeList) {

        SetNode left = nodeList.pollFirst();

        // reducedSet will hold either the calculation of the adjacent higher levels nodes 
        // or the next node if it is on same level 
        Set reducedSet;
        if (left.getLevel() < nodeList.getFirst().getLevel()) {
            reducedSet = reduce(nodeList);
        } else {
            reducedSet = nodeList.getFirst().getNodeSet();
        }

        // Join left set with reducedSet
        Set outcomeSet = OperatorHandler.calculateSets(left.getNodeSet(), reducedSet, left.getOperator());

        //  ... and the outcome store it on the first node of the list
        nodeList.getFirst().setNodeSet(outcomeSet);

        return outcomeSet;

    }

}
