package pers.darren.designpattern.strategy;

public class StrategyPatternDemo {

    public static void main(String[] args) {
        Context context = new Context();

        context.setStrategy(new AddOperation());
        System.out.println("1 + 5 = " + context.executeStrategy(1, 5));
        context.setStrategy(new SubtractOperation());
        System.out.println("5 - 1 = " + context.executeStrategy(5, 1));
        context.setStrategy(new MultiplyOperation());
        System.out.println("2 * 3 = " + context.executeStrategy(2, 3));
        context.setStrategy(new DivideOperation());
        System.out.println("6 / 3 = " + context.executeStrategy(6, 3));
    }
}