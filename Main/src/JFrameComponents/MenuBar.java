package JFrameComponents;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public static JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        return menuBar;
    }
}
