package frame.action;

import frame.ChatComponents.Message;
import frame.TextChatPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSendAction implements ActionListener {

    TextChatPanel textChatPanel;
    JTextArea textArea;

    public ButtonSendAction(TextChatPanel textChatPanel, JTextArea textArea) {
        this.textChatPanel = textChatPanel;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!checkText()) {
            textArea.setText("");
            return;
        }

        Message message = new Message(textArea.getText());
        textChatPanel.addMessage(message);
        textArea.setText("");
        textChatPanel.repaint();
        textChatPanel.revalidate();
    }

    private boolean checkText() {
        String s = textArea.getText().trim();
        if (s.equals("")) return false;
        return true;
    }
}
