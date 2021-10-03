package current.client.controllers;

import current.client.workingWithLocalHistory.LocalHistory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowController extends WindowAdapter {

    private JTextArea textArea;

    public WindowController(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        LocalHistory.saveHistory(textArea.getText());
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        String str = textArea.getText();
        textArea.setText("");
        textArea.append(LocalHistory.downloadHistory());
        textArea.append(str);
    }
}
