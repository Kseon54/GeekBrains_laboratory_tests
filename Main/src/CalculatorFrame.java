import ActionListeners.*;
import JFrameComponents.MenuBar;

import javax.swing.*;
import java.awt.*;

import Enum.ActionsOnTwo;
import Enum.ActionsOnOne;

public class CalculatorFrame extends JFrame {

    private static final Font FONT = new Font("TimesRoman", Font.BOLD, 30);

    private JTextField inputArea;

    public CalculatorFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setBounds(100, 100, 600, 500);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

        setJMenuBar(new MenuBar());

        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(3);
        setLayout(borderLayout);

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
            btn.setFont(FONT);
            panel.add(btn);
        }

        JButton clear = new JButton("C");
        clear.addActionListener(e -> inputArea.setText(""));
        clear.setFont(FONT);
        panel.add(clear);

        JButton btn0 = new JButton("0");
        btn0.addActionListener(setNameActionListener);
        btn0.setFont(FONT);
        panel.add(btn0);

        JButton calc = new JButton("=");
        calc.addActionListener(new CalcButtonActionListener(inputArea));
        calc.setFont(FONT);
        panel.add(calc);

        return panel;
    }

    private JPanel createRightMeinPanel() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 3);
        gridLayout.setHgap(3);
        gridLayout.setVgap(3);
        panel.setLayout(gridLayout);


        JButton btnPoint = new JButton(".");
        btnPoint.addActionListener(new SetPointActionListener(inputArea));
        btnPoint.setFont(FONT);
        panel.add(btnPoint);
        ActionSetNameOperatorOnTwoActionListener actionListenerTwo = new ActionSetNameOperatorOnTwoActionListener(inputArea);


        for (int i = 0; i < ActionsOnTwo.length; i++) {
            JButton btn = new JButton(ActionsOnTwo.getTitle(i));
            btn.addActionListener(actionListenerTwo);
            btn.setFont(FONT);
            panel.add(btn);
        }

        ActionSetNameOperatorOnOneActionListener actionListenerOne = new ActionSetNameOperatorOnOneActionListener(inputArea);
        for (int i = 0; i < ActionsOnOne.length; i++) {
            JButton btn = new JButton(ActionsOnOne.getTitle(i));
            btn.addActionListener(actionListenerOne);
            btn.setFont(FONT);
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
