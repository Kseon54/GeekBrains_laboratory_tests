package ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Enum.ActionsOnTwo;
import Enum.ActionsOnOne;
import base.BaseActionListener;

public class CalcButtonActionListener extends BaseActionListener {

    public CalcButtonActionListener(JTextField inputArea) {
        super(inputArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = inputArea.getText();

        if (text.equals("")) return;

        List<String> list = parsText(text);

        ActionsOnOne[] actionsOnOnes = ActionsOnOne.getSortPriority();
        for (ActionsOnOne action : actionsOnOnes) {
            list = calculationActionOnOne(list, action);
        }

        list = dropExtraCharacters(list);

        if (list.isEmpty()) return;

        ActionsOnTwo[] actionsOnTwos = ActionsOnTwo.getSortPriority();
        for (ActionsOnTwo action : actionsOnTwos) {
            list = calculationActionOnTwo(list, action);
        }

        list = calculationActionOnTwo(list, ActionsOnTwo.MULTIPLY);

        double tmp = Double.parseDouble(list.get(0));
        String result;
        if (tmp % 1 == 0) result = String.valueOf(((int) tmp));
        else result = String.valueOf(tmp);

        inputArea.setText(result);
    }

    private List<String> dropExtraCharacters(List<String> list) {
        if (list.isEmpty()) return list;
        List<String> text = new ArrayList<>(list);
        if (ActionsOnTwo.isThereSuchTitle(text.get(text.size() - 1))) text.remove(text.size() - 1);
        if (ActionsOnTwo.isThereSuchTitle(text.get(text.size() - 1))) text.remove(text.size() - 1);
        return text;
    }

    private List<String> parsText(String text) {
        List<String> list = new ArrayList<>();
        char[] chars = text.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            if (ActionsOnTwo.isThereSuchTitle(String.valueOf(aChar))) {
                list.add(stringBuilder.toString());
                list.add(String.valueOf(aChar));
                stringBuilder.setLength(0);
                continue;
            }
            stringBuilder.append(aChar);
        }
        list.add(stringBuilder.toString());
        list.removeAll(Arrays.asList("", null));
        return list;
    }

    private List<String> calculationActionOnOne(List<String> strings, ActionsOnOne action) {
        List<String> list = new ArrayList<>(strings);
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.contains(action.getTitle())) {
                if (s.length() <= action.getTitle().length()) {
                    list.remove(i);
                    i--;
                    continue;
                }
                s = s.substring(action.getTitle().length());
                list.set(i, String.valueOf(action.getMathematicalAction().calc(Double.parseDouble(s))));
            }
        }
        return list;
    }

    private List<String> calculationActionOnTwo(List<String> list, ActionsOnTwo actions) {
        List<String> resList = new ArrayList<>(list);
        for (int i = 0; i < resList.size(); i++) {
            if (actions.getTitle().equals(resList.get(i))) {
                if (i >= 3 &&
                        resList.get(i - 2).equals(ActionsOnTwo.MINUS.getTitle()) &&
                        ActionsOnTwo.isThereSuchTitle(resList.get(i - 3))) {
                    resList.set(i - 1, resList.get(i - 2) + resList.get(i - 1));
                    resList.remove(i - 2);
                    i--;
                }
                if (i < resList.size() - 2 && resList.get(i + 1).equals(ActionsOnTwo.MINUS.getTitle())) {
                    resList.set(i + 1, resList.get(i + 1) + resList.get(i + 2));
                    resList.remove(i + 2);
                }
                double b = Double.parseDouble(resList.get(i + 1));
                resList.remove(i + 1);
                resList.remove(i);
                double a = Double.parseDouble(resList.get(i - 1));

                resList.set(i - 1, String.valueOf(actions.getMathematicalAction().calc(a, b)));
                i--;
            }
        }
        return resList;
    }
}

