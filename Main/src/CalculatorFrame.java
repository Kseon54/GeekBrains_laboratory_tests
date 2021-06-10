import ActionListeners.ActionSetNameOperatorOnOneActionListener;
import ActionListeners.ActionSetNameOperatorOnTwoActionListener;
import ActionListeners.CalcButtonActionListener;
import ActionListeners.SetNameActionListener;
import JFrameComponents.MenuBar;

import javax.swing.*;
import java.awt.*;

import Enum.ActionsOnTwo;
import Enum.ActionsOnOne;

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

        ActionSetNameOperatorOnTwoActionListener actionListenerTwo = new ActionSetNameOperatorOnTwoActionListener(inputArea);
        for (int i = 0; i < ActionsOnTwo.length; i++) {
            JButton btn = new JButton(ActionsOnTwo.getTitle(i));
            btn.addActionListener(actionListenerTwo);
            panel.add(btn);
        }

        ActionSetNameOperatorOnOneActionListener actionListenerOne = new ActionSetNameOperatorOnOneActionListener(inputArea);
        for (int i = 0; i < ActionsOnOne.length; i++) {
            JButton btn = new JButton(ActionsOnOne.getTitle(i));
            btn.addActionListener(actionListenerOne);
            panel.add(btn);
        }

        return panel;
    }

    private void removeLastChar() {
        String s = inputArea.getText();
        s = (s.length() == 0) ? "" : (s.substring(0, s.length() - 1));
        inputArea.setText(s);
    }

}
