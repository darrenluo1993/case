package pers.darren.designpattern.state.player;

public abstract class StateAdapter implements State {

    protected AudioPlayer player;

    protected StateAdapter(AudioPlayer player) {
        this.player = player;
    }

    @Override
    public void printState() {
        System.out.print("当前" + this.player.getState().getText() + "，");
    }

    @Override
    public void clickLock() {
        this.printState();
    }

    @Override
    public void clickPlay() {
        this.printState();
    }

    @Override
    public void clickNext() {
        this.printState();
    }

    @Override
    public void clickPrevious() {
        this.printState();
    }

    @Override
    public void clickShutdown() {
        this.printState();
    }
}