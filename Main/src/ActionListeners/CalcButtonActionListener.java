package ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Enum.ActionsOnTwo;

public class CalcButtonActionListener implements ActionListener {
    private final JTextField textField;

    public CalcButtonActionListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();

        if (text.equals("")) return;

        text = dropExtraCharacters(text);

        List<String> list = parsText(text);

        list = countingSquareRoots(list);

        ActionsOnTwo[] actions = ActionsOnTwo.getSortPriority();

        for (ActionsOnTwo action : actions) {
            list = calculation(list, action);
        }
        list = calculation(list, ActionsOnTwo.MULTIPLY);

        double tmp = Double.parseDouble(list.get(0));
        String result;
        if (tmp % 1 == 0) result = String.valueOf(((int) tmp));
        else result = String.valueOf(tmp);

        textField.setText(result);
    }

    private String removeLastChar(String s) {
        return (s.length() == 0) ? "" : (s.substring(0, s.length() - 1));
    }

    private String dropExtraCharacters(String text) {
        if (String.valueOf(text.charAt(text.length() - 1)).equals(String.valueOf((char) 8730)))
            text = removeLastChar(text);
        if (ActionsOnTwo.isThereSuchTitle(String.valueOf(text.charAt(text.length() - 1)))) text = removeLastChar(text);
        if (ActionsOnTwo.isThereSuchTitle(String.valueOf(text.charAt(text.length() - 1)))) text = removeLastChar(text);
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

    private List<String> countingSquareRoots(List<String> strings) {
        List<String> list = new ArrayList<>(strings);
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.charAt(0) == (char) 8730) {
                s = s.substring(1);
                list.set(i, String.valueOf(Math.sqrt(Double.parseDouble(s))));
            }
        }
        return list;
    }

    private List<String> calculation(List<String> list, ActionsOnTwo actions) {
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

