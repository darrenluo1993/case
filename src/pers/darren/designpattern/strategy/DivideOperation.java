package pers.darren.designpattern.strategy;

public class DivideOperation implements OperationStrategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 / num2;
    }
}