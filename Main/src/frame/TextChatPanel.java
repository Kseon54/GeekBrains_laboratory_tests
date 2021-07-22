package frame;

import frame.ChatComponents.Message;

import javax.swing.*;

public class TextChatPanel extends JPanel {

    public TextChatPanel() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
    }

    public void addMessage(Message message) {
        add(message);
    }

}
