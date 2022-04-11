package pers.darren.designpattern.state.player;

/**
 * 播放状态
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 4/6/22 5:37 PM
 */
public class PlayingState extends StateAdapter {

    private final String text;

    public PlayingState(AudioPlayer player) {
        super(player);
        this.text = "播放状态";
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void clickLock() {
        super.clickLock();
        System.out.println("执行锁定，切换至锁定状态");
        super.player.changeState(new LockedState(super.player));
    }

    @Override
    public void clickPlay() {
        super.clickPlay();
        System.out.println("执行暂停，切换至准备状态");
        super.player.changeState(new ReadyState(super.player));
    }

    @Override
    public void clickNext() {
        super.clickNext();
        System.out.println("执行下一首");
    }

    @Override
    public void clickPrevious() {
        super.clickPrevious();
        System.out.println("执行上一首");
    }

    @Override
    public void clickShutdown() {
        super.clickShutdown();
        System.out.println("执行关机，切换至关机状态");
        super.player.changeState(new ShutdownState(super.player));
    }
}