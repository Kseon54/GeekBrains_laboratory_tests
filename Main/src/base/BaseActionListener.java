package base;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class BaseActionListener implements ActionListener {
    protected JTextField inputArea;

    public BaseActionListener(JTextField inputArea) {
        this.inputArea = inputArea;
    }
}
