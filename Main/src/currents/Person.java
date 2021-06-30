package currents;

import base.Current;

public class Person extends Current {

    public Person(float maxRun, float maxJump) {
        setMaxJump(maxJump);
        setMaxRun(maxRun);
    }
}
