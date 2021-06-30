import Utils.CreatorCurrents;
import Utils.CreatorObstacle;
import base.Current;
import base.ObstacleOneParameter;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CreatorCurrents creatorCurrents = new CreatorCurrents();
        CreatorObstacle creatorObstacle = new CreatorObstacle();

        Current[] currents = new Current[20];

        for (int i = 0; i < currents.length; i++) {
            currents[i] = creatorCurrents.createCurrent();
        }

        ObstacleOneParameter[] obstacles = new ObstacleOneParameter[10];

        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = creatorObstacle.createObstacle();
        }

        Arrays.stream(obstacles).forEach(obstacle -> {
            for (Current current : currents) {
                if (current.isParticipates()) System.out.println(obstacle.accomplishment(current));
            }
        });

        int count = 0;
        for (Current current : currents) {
//            System.out.println(current.toString());
            if (current.isParticipates()) count++;
        }
        System.out.printf("Справилость %d участников", count);
    }
}
