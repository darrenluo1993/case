package pers.darren.designpattern.state;

public class Context {

    private State state;

    public Context() {
        this.state = new StopState();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void execute() {
        this.state.doAction(this);
    }
}