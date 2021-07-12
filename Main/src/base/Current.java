package base;

import actions.Jumping;
import actions.Running;

public interface Current extends Running, Jumping {

    boolean isParticipates();
}
