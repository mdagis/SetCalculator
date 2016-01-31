package com.mdagis.setcalculator;

import java.util.Set;

public interface SetNodeCollection {
    
    SetCalculatorBuilder level(int setLevel);
    
    <T extends SetNodeOperand> T and();

    <T extends SetNodeOperand> T or();

    <T extends SetNodeOperand> T notIn();

    Set calculate();

}
