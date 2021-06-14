package ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Enum.ActionsOnTwo;
import Enum.ActionsOnOne;
import base.BaseActionListener;

public class SetPointActionListener extends BaseActionListener {

    public SetPointActionListener(JTextField inputArea) {
        super(inputArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = ((JButton) e.getSource());
        String text = inputArea.getText();

        if (text.equals("")) return;

        int lastSign = -1;
        for (int i = 0; i < ActionsOnTwo.length; i++) {
            int temp = text.lastIndexOf(ActionsOnTwo.getByIndex(i).getTitle());
            lastSign = Math.max(lastSign, temp);
        }
        for (int i = 0; i < ActionsOnOne.length; i++) {
            int temp = text.lastIndexOf(ActionsOnOne.getByIndex(i).getTitle());
            lastSign = Math.max(lastSign, temp);
        }

        if (lastSign == -1 && !text.contains(btn.getText())) {
            inputArea.setText(inputArea.getText() + btn.getText());
            return;
        }

        if (lastSign != -1) {
            String s = text.substring(lastSign + 1);
            if (s.contains(btn.getText())) return;
            if (!s.equals("")) inputArea.setText(inputArea.getText() + btn.getText());
        }
    }
}
