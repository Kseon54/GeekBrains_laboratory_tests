package frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Чат");
        setBounds(calculateSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 8));
        customizationThisPanel();
        addComponentsToFrame();
        setVisible(true);
    }

    private void customizationThisPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);
    }

    private void addComponentsToFrame() {
        ChatPanel chatPanel = new ChatPanel();
        add(chatPanel,BorderLayout.CENTER);
    }

    private Rectangle calculateSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        width /= 2;
        height-=height*0.1;
        return new Rectangle(0, 0, width, height);
    }
}
