package Enum;

import actions.MathematicalOnOneAction;

import javax.rmi.CORBA.Util;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum ActionsOnOne {
    ROOT(String.valueOf((char) 8730), Math::sqrt, 0),
    FACT("!", (e)->{
        int n =1;
        int a=1;
        while (n<e)
        {
            n++;
            a*=n;
        }
        return a;
    }, 0),
    LOG("log",Math::log,0);

    public final static int length = values().length;

    private static final Map<String, ActionsOnOne> map = new HashMap<>();

    static {
        for (ActionsOnOne actionsOnOne : ActionsOnOne.values()) {
            map.put(actionsOnOne.title, actionsOnOne);
        }
    }

    private final MathematicalOnOneAction mathematicalAction;
    private final String title;
    private final int priority;

    ActionsOnOne(String title, MathematicalOnOneAction mathematicalAction, int priority) {
        this.mathematicalAction = mathematicalAction;
        this.title = title;
        this.priority = priority;
    }

    public static boolean isThereSuchTitle(String title) {
        ActionsOnOne a = map.get(title);
        return a != null;
    }

    public static ActionsOnOne[] getSortPriority() {
        ActionsOnOne[] actions = ActionsOnOne.values();
        actions = Arrays.stream(actions).sorted(Comparator.comparingInt(o -> o.priority)).collect(Collectors.toList()).toArray(actions);
        return actions;
    }

    public static ActionsOnOne getActionsOnOne(String title) {
        return map.get(title);
    }

    public String getTitle() {
        return title;
    }

    public MathematicalOnOneAction getMathematicalAction() {
        return mathematicalAction;
    }

    public int getPriority() {
        return priority;
    }

    public static String getTitle(int number) {
        return getByIndex(number).title;
    }

    public static ActionsOnOne getByIndex(int index) {
        return ActionsOnOne.values()[index];
    }


    @Override
    public String toString() {
        return ActionsOnOne.class + ": title=" + title + "; " + "priority=" + priority + ";";
    }

}
