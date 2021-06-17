package ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Enum.ActionsOnTwo;
import base.BaseActionListener;

public class ActionSetNameOperatorOnTwoActionListener extends BaseActionListener {

    public ActionSetNameOperatorOnTwoActionListener(JTextField inputArea) {
        super(inputArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String text = inputArea.getText();
        String t = btn.getText();

        if (text.equals("") && t.equals(ActionsOnTwo.MINUS.getTitle())) inputArea.setText(removeLastChar(text) + t);
        if (text.equals("")) return;

        if (addChar(btn.getText())) {
            if (text.charAt(text.length() - 1) == ActionsOnTwo.PLUS.getTitle().charAt(0) &&
                    t.equals(ActionsOnTwo.MINUS.getTitle())
            ) text = removeLastChar(text);
            inputArea.setText(text + t);
        } else inputArea.setText(removeLastChar(text) + t);
    }

    private boolean addChar(String str) {
        String txt = inputArea.getText();

        String lostChar = String.valueOf(txt.charAt(txt.length() - 1));
        if (ActionsOnTwo.isThereSuchTitle(lostChar)) {
            return str.equals(ActionsOnTwo.MINUS.getTitle()) && !lostChar.equals(ActionsOnTwo.MINUS.getTitle());
        }
        return true;
    }

    private String removeLastChar(String s) {
        return (s.length() == 0) ? "" : (s.substring(0, s.length() - 1));
    }
}
