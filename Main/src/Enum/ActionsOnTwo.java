package Enum;

import ActionListeners.MathematicalAction;

import java.util.*;
import java.util.stream.Collectors;

public enum ActionsOnTwo {
    MULTIPLY("*", (a, b) -> a * b, 1),
    DIVIDE("/", (a, b) -> a / b, 2),
    MINUS("-", (a, b) -> a - b, 3),
    PLUS("+", Double::sum, 3),
    EXPONENTIATION("^", Math::pow,0);

    public final static int length = values().length;

    private static final Map<String, ActionsOnTwo> map = new HashMap<>();

    static {
        for (ActionsOnTwo actionsOnTwo : ActionsOnTwo.values()) {
            map.put(actionsOnTwo.title, actionsOnTwo);
        }
    }

    private final MathematicalAction mathematicalAction;
    private final String title;
    private final int priority;

    ActionsOnTwo(String title, MathematicalAction mathematicalAction, int priority) {
        this.mathematicalAction = mathematicalAction;
        this.title = title;
        this.priority = priority;
    }

    public static boolean isThereSuchTitle(String title) {
        ActionsOnTwo a = map.get(title);
        return a != null;
    }

    public static ActionsOnTwo[] getSortPriority() {
        ActionsOnTwo[] actions = ActionsOnTwo.values();
        actions = Arrays.stream(actions).sorted(Comparator.comparingInt(o -> o.priority)).collect(Collectors.toList()).toArray(actions);
        return actions;
    }

    public static ActionsOnTwo getActionsOnTwo(String title) {
        return map.get(title);
    }

    public String getTitle() {
        return title;
    }

    public MathematicalAction getMathematicalAction() {
        return mathematicalAction;
    }

    public int getPriority() {
        return priority;
    }

    public static String getTitle(int number) {
        return getByIndex(number).title;
    }

    public static ActionsOnTwo getByIndex(int index) {
        return ActionsOnTwo.values()[index];
    }


    @Override
    public String toString() {
        return ActionsOnTwo.class + ": title=" + title + "; " + "priority=" + priority + ";";
    }
}
