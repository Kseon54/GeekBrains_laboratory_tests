package ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetNameActionListener implements ActionListener {
    private final JTextField inputArea;

    public SetNameActionListener(JTextField inputField) {
        this.inputArea = inputField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        inputArea.setText(inputArea.getText() + btn.getText());
    }
}
