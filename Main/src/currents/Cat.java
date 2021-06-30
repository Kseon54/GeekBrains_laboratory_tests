package currents;

import base.Current;

public class Cat extends Current {
    public Cat(float maxRun, float maxJump) {
        setMaxJump(maxJump);
        setMaxRun(maxRun);
    }
}
