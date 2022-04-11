package pers.darren.designpattern.state.player;

public interface State {

    String getText();

    void printState();

    void clickLock();

    void clickPlay();

    void clickNext();

    void clickPrevious();

    void clickShutdown();
}