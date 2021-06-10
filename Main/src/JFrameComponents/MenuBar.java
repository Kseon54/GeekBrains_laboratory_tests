package JFrameComponents;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        this.add(fileMenu);
    }
}
