package pers.darren.designpattern.state.player;

public class PlayerDemo {

    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.clickPlay();
        player.clickLock();
        player.clickNext();
        player.clickLock();
        player.clickNext();
        player.clickPrevious();
        player.clickPlay();
        player.clickPrevious();
        player.clickLock();
        player.clickPlay();
        player.clickNext();
        player.clickPrevious();
        player.clickShutdown();
        player.clickLock();
        player.clickPrevious();
        player.clickPlay();
        player.clickNext();
        player.clickShutdown();
    }
}