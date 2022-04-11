package pers.darren.designpattern.state.player;

/**
 * 准备状态
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 4/6/22 5:35 PM
 */
public class ReadyState extends StateAdapter {

    private final String text;

    public ReadyState(AudioPlayer player) {
        super(player);
        this.text = "准备状态";
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
        System.out.println("执行播放，切换至播放状态");
        super.player.changeState(new PlayingState(super.player));
    }

    @Override
    public void clickNext() {
        super.clickNext();
        System.out.println("执行下一首，切换至播放状态");
        super.player.changeState(new PlayingState(super.player));
    }

    @Override
    public void clickPrevious() {
        super.clickPrevious();
        System.out.println("执行上一首，切换至播放状态");
        super.player.changeState(new PlayingState(super.player));
    }

    @Override
    public void clickShutdown() {
        super.clickShutdown();
        System.out.println("执行关机，切换至关机状态");
        super.player.changeState(new ShutdownState(super.player));
    }
}