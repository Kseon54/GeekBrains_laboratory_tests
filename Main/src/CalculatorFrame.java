import ActionListeners.ActionSetNameActionListener;
import ActionListeners.CalcButtonActionListener;
import ActionListeners.SetNameActionListener;
import JFrameComponents.MenuBar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import Enum.ActionsOnTwo;

public class CalculatorFrame extends JFrame {

    private JTextField inputArea;

    public CalculatorFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setBounds(100, 100, 600, 500);

        setJMenuBar(new MenuBar());

        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);
        add(createMeinPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        inputArea = new JTextField();
        inputArea.setEditable(false);
        inputArea.setText("1+2*3-4/2+√25");

        panel.add(inputArea, BorderLayout.CENTER);

        JButton button = new JButton(String.valueOf((char) 8592));
        button.addActionListener(e -> removeLastChar());
        panel.add(button, BorderLayout.EAST);

        return panel;
    }

    private JPanel createMeinPanel() {
        JPanel panel = new JPanel();

        GridLayout gridLayout = new GridLayout(1, 2);
        gridLayout.setHgap(10);


        panel.setLayout(gridLayout);
        panel.add(createLeftMeinPanel());
        panel.add(createRightMeinPanel());

        return panel;
    }

    private JPanel createLeftMeinPanel() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 3);
        gridLayout.setHgap(3);
        gridLayout.setVgap(3);
        panel.setLayout(gridLayout);

        SetNameActionListener setNameActionListener = new SetNameActionListener(inputArea);

        for (int i = 1; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.addActionListener(setNameActionListener);
            panel.add(btn);
        }

        JButton clear = new JButton("C");
        clear.addActionListener(e -> inputArea.setText(""));
        panel.add(clear);

        JButton btn0 = new JButton("0");
        btn0.addActionListener(setNameActionListener);
        panel.add(btn0);

        JButton calc = new JButton("=");
        calc.addActionListener(new CalcButtonActionListener(inputArea));
        panel.add(calc);

        return panel;
    }

    private JPanel createRightMeinPanel() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 2);
        gridLayout.setHgap(3);
        gridLayout.setVgap(3);
        panel.setLayout(gridLayout);

        ActionSetNameActionListener actionSetNameActionListener = new ActionSetNameActionListener(inputArea);

        for (int i = 0; i < ActionsOnTwo.length; i++) {
            JButton btn = new JButton(ActionsOnTwo.getTitle(i));
            btn.addActionListener(actionSetNameActionListener);
            panel.add(btn);
        }

        JButton btn = new JButton(String.valueOf((char) 8730));
        btn.addActionListener(e -> {
            if (inputArea.getText().equals("")) {
                inputArea.setText(inputArea.getText() + btn.getText());
                return;
            }
            char c = inputArea.getText().charAt(inputArea.getText().length() - 1);
            if (String.valueOf(c).equals(btn.getText()) ||
                    !Character.isDigit(c))
                inputArea.setText(inputArea.getText() + btn.getText());
        });
        panel.add(btn);

        return panel;
    }

    private void removeLastChar() {
        String s = inputArea.getText();
        s = (s.length() == 0) ? "" : (s.substring(0, s.length() - 1));
        inputArea.setText(s);
    }

}
