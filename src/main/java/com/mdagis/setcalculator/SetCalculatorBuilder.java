package com.mdagis.setcalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;


public class SetCalculatorBuilder implements SetNodeCollection, SetNodeOperand{

    private final Deque<SetNode> setNodeList = new ArrayDeque<>();

    public SetCalculatorBuilder() {
    }

    @Override
    public Set calculate() {
        while (setNodeList.size() > 1) {
            reduce(setNodeList);
        }
        return setNodeList.getFirst().getNodeSet();
    }

    @Override
    public <T extends SetNodeCollection> T addSet(Set s) {
        
        int defaultLevel = setNodeList.isEmpty()? 0 : setNodeList.peekLast().getLevel();
        
        SetNode addedSetNode = new SetNode();
        addedSetNode.setNodeSet(s);
        setNodeList.add(addedSetNode);
        setNodeList.getLast().setLevel(defaultLevel);
        
        return (T) this;

    }

    @Override
    public SetCalculatorBuilder level(int setLevel) {
        setNodeList.getLast().setLevel(setLevel);
        return this;
    }

    @Override
    public <T extends SetNodeOperand> T  and() {
        setNodeList.getLast().setOperator(SetCalculateOperators.AND);
        return  (T) this;
    }

    @Override
    public <T extends SetNodeOperand> T  or() {
        setNodeList.getLast().setOperator(SetCalculateOperators.OR);
        return (T) this;
    }
    
    @Override
    public <T extends SetNodeOperand> T  notIn() {
        setNodeList.getLast().setOperator(SetCalculateOperators.NOT_IN);
        return (T)  this;
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
