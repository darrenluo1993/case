package pers.darren.jackson;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

public class DisableIfDemoTests {
    @Test
    @DisabledIf("customCondition")
    public void disabledIfTest() {
        System.err.println("disabledIfTest");
    }

    @Test
    @EnabledIf("customCondition")
    public void enabledIfTest() {
        System.err.println("enabledIfTest");
    }

    private boolean customCondition() {
        System.err.println("customCondition");
        return true;
    }
}
