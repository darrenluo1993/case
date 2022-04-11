package pers.darren.designpattern.state.player;

public class AudioPlayer {

    private State state;

    public AudioPlayer() {
        this.state = new ReadyState(this);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public void clickPlay() {
        this.state.clickPlay();
    }

    public void clickNext() {
        this.state.clickNext();
    }

    public void clickPrevious() {
        this.state.clickPrevious();
    }

    public void clickLock() {
        this.state.clickLock();
    }

    public void clickShutdown() {
        this.state.clickShutdown();
    }
}