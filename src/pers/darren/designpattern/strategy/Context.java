package pers.darren.designpattern.strategy;

public class Context {

    private OperationStrategy strategy;

    public OperationStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return this.strategy.doOperation(num1, num2);
    }
}