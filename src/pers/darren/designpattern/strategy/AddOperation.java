package pers.darren.designpattern.strategy;

public class AddOperation implements OperationStrategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}