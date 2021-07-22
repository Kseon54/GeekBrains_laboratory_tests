package frame;

import frame.action.ButtonSendAction;
import frame.action.KeyListenerChatTextArea;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    private final JTextArea textArea;
    private final JButton sendButton;
    private final TextChatPanel textChatPanel;

    public ChatPanel() {
        textArea = new JTextArea();
        sendButton = new JButton("Отправить");
        textChatPanel = new TextChatPanel();
        customizationComponents();
        customizationThisPanel();
    }

    private void customizationThisPanel() {
        LayoutManager layout = new BorderLayout();
        setLayout(layout);
        textChatPanel.setBackground(Color.GRAY);
        JPanel panelInputText = createPanelInputText();
        JPanel panelChat = createPanelChat();

        add(panelChat, BorderLayout.CENTER);
        add(panelInputText, BorderLayout.SOUTH);
    }

    private JPanel createPanelChat(){
        JPanel panel = new JPanel();
//        panel.setBackground(Color.blue);
        panel.add(textChatPanel);

        return panel;
    }

    private JPanel createPanelInputText() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        BorderLayout layout = new BorderLayout();
        layout.setHgap(5);
        panel.setLayout(layout);

        JScrollPane scrollText = new JScrollPane();
        scrollText.setViewportView(textArea);
        scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollText, BorderLayout.CENTER);

        panel.add(sendButton, BorderLayout.EAST);
        return panel;
    }

    private void customizationComponents() {
        customizationButton();
        customizationTextAria();
    }

    private void customizationButton() {
        sendButton.addActionListener(new ButtonSendAction(textChatPanel, textArea));
    }

    private void customizationTextAria() {
        textArea.setRows(3);
        textArea.setToolTipText("Ваше сообщение");
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setBackground(Color.lightGray);
        textArea.addKeyListener(new KeyListenerChatTextArea(sendButton));
    }
}
