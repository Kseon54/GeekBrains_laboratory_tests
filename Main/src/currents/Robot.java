package currents;

import base.Current;

public class Robot extends Current {
    public Robot(float maxRun, float maxJump) {
        setMaxJump(maxJump);
        setMaxRun(maxRun);
    }
}
