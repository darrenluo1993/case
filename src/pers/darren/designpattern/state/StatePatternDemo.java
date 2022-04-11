package pers.darren.designpattern.state;

public class StatePatternDemo {

    public static void main(String[] args) {
        Context context = new Context();
        context.execute();
        context.setState(new StartState());
        context.execute();
    }
}