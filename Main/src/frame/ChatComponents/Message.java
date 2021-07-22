package frame.ChatComponents;

import javax.swing.*;
import java.awt.*;

public class Message extends JPanel {

    private final String message;
    private final JTextArea text;

    public Message(String message) {
        this.message = message.trim();
        text = new JTextArea(this.message);
        customization();
    }

    private void customization(){
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        add(text);
    }

    public String getMessage() {
        return message;
    }
}
