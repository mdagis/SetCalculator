# SetCalculator

SetCalculator is a small set calculation library. It is created to help calculate complex set operations 
using operators like intersect, union, difference and also using nested parenthesis as levels.

## Example

Suppose that you have 4 sets s1, s2, s3, s4.

s1 represents all women in a room.

s2 represents all people in room with age 30

s3 represents all people in room with age 35

s4 represents all people in room that have birthday today


If we want to get all women in that room that have age 30 or 35 but don’t have birthday today we would do a calculation like:

**s1  AND (s2 OR s3) not in s4**

With SetCalculator you could achieve that with:


```java
Set set1;
Set set2; 
Set set3; 
Set set4; 

SetCalculatorBuilder setBuilder = new SetCalculatorBuilder();
Set lastSet = setBuilder
        .addSet(set1).level(0)
        .and()
        .addSet(set2).level(1)
        .or()
        .addSet(set3).level(1)
        .notIn()
        .addSet(set4).level(0)
        .calculate();
```