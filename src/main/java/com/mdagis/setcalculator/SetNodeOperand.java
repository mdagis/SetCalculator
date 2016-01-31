
package com.mdagis.setcalculator;

import java.util.Set;

//SetNodeCollection
public interface SetNodeOperand {
    
    <T extends SetNodeCollection> T addSet(Set s);
    
    Set calculate();
    
}
