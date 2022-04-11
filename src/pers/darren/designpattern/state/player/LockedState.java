package pers.darren.designpattern.state.player;

/**
 * 锁定状态
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 4/7/22 3:10 PM
 */
public class LockedState extends StateAdapter {

    private final String text;

    public LockedState(AudioPlayer player) {
        super(player);
        this.text = "锁定状态";
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void clickLock() {
        super.clickLock();
        System.out.println("执行解锁，切换至准备状态");
        super.player.changeState(new ReadyState(super.player));
    }

    @Override
    public void clickPlay() {
        super.clickPlay();
        System.out.println("已锁定，无法执行播放");
    }

    @Override
    public void clickNext() {
        super.clickNext();
        System.out.println("已锁定，无法执行下一首");
    }

    @Override
    public void clickPrevious() {
        super.clickPrevious();
        System.out.println("已锁定，无法执行上一首");
    }

    @Override
    public void clickShutdown() {
        super.clickShutdown();
        System.out.println("执行关机，切换至关机状态");
        super.player.changeState(new ShutdownState(super.player));
    }
}