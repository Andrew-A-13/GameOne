import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame(int size) throws HeadlessException {
        setBounds(100, 100, size, size);
        setVisible(true);
        gamePanel = new GamePanel(this);
        add(gamePanel);
        setFocusable(true);
    }
}
