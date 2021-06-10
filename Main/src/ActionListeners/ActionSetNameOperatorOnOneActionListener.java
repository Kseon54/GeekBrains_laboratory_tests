package ActionListeners;

import base.BaseActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class ActionSetNameOperatorOnOneActionListener extends BaseActionListener {

    public ActionSetNameOperatorOnOneActionListener(JTextField inputArea) {
        super(inputArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (inputArea.getText().equals("")) {
            inputArea.setText(inputArea.getText() + btn.getText());
            return;
        }
        char c = inputArea.getText().charAt(inputArea.getText().length() - 1);
        if (String.valueOf(c).equals(btn.getText()) ||
                !Character.isDigit(c))
            inputArea.setText(inputArea.getText() + btn.getText());
    }
}
