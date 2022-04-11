package pers.darren.designpattern.state;

public class StartState implements State {

    private Context context;

    public Context getContext() {
        return context;
    }

    @Override
    public void doAction(Context context) {
        this.context = context;
        System.out.println("Player is in " + this + "!");
    }

    @Override
    public String toString() {
        return "Start State";
    }
}