package Utils;

import base.Current;
import currents.Cat;
import currents.Person;
import currents.Robot;

public class CreatorCurrents {
    public final float CAT_MAX_RUN = 10;
    public final float PERSON_MAX_RUN = 5;
    public final float ROBOT_MAX_RUN = 7;

    public final float CAT_MAX_JUMP = 10;
    public final float PERSON_MAX_JUMP = 5;
    public final float ROBOT_MAX_JUMP = 7;

    public Cat createCat() {
        return new Cat(Rnd.randomFloat(CAT_MAX_RUN), Rnd.randomFloat(CAT_MAX_JUMP));
    }

    public Person createPerson() {
        return new Person(Rnd.randomFloat(PERSON_MAX_RUN), Rnd.randomFloat(PERSON_MAX_JUMP));
    }

    public Robot createRobot() {
        return new Robot(Rnd.randomFloat(ROBOT_MAX_RUN), Rnd.randomFloat(ROBOT_MAX_JUMP));
    }

    public Current createCurrent() {
        float type = (float) Math.random();
        if (type <= 0.33) {
            return createCat();
        }
        if (type <= 0.66) {
            return createPerson();
        }
        return createRobot();
    }
}
