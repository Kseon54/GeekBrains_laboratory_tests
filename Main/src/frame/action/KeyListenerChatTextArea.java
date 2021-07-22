package frame.action;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerChatTextArea implements KeyListener {

    JButton sendButton;

    public KeyListenerChatTextArea(JButton sendButton) {
        this.sendButton = sendButton;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER && e.isShiftDown()) sendButton.doClick();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
