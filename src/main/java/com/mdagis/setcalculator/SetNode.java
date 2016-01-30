package com.mdagis.setcalculator;

import java.util.HashSet;
import java.util.Set;

public class SetNode {

    private String nodeName;
    private Set nodeSet = new HashSet<>();
    private SetCalculateOperators operator;
    private int level;

    public SetNode() {
    }

    public SetNode(String nodeName, SetCalculateOperators operator, int level) {
        this.nodeName = nodeName;
        this.operator = operator;
        this.level = level;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public SetCalculateOperators getOperator() {
        return operator;
    }

    public void setOperator(SetCalculateOperators operator) {
        this.operator = operator;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set getNodeSet() {
        return nodeSet;
    }

    public void setNodeSet(Set nodeSet) {
        this.nodeSet = nodeSet;
    }

}
