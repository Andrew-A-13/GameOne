import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Ship orc;
    public Ship gnom;

    public Timer timer;
    int ticks = 0;

    public GamePanel(GameFrame frame) {
        frame.addKeyListener(this);
        timer = new Timer(50, this);
        timer.start();


        try {
            Image image = ImageIO.read(new File("src\\orc.png"));
            Image image2 = ImageIO.read(new File("src\\orcDead.png"));
            orc = new Ship(150, 150, 30, 0, image, image2, new Weapon(15), 50, 0.25,
                    new Rectangle(-20, -40, 40, 80));
        } catch (IOException e) {
            System.out.println("Облом.");
            throw new RuntimeException(e);
        }
        try {
            Image image = ImageIO.read(new File("src\\ROCKET.png"));
            Image image2 = ImageIO.read(new File("src\\ROCKETDEAD.png"));
            gnom = new Ship(450, 450, 10, 0, image, image2, new Weapon(100), 100, 1,
                    new Rectangle(-20, -40, 40, 80));
        } catch (IOException e) {
            System.out.println("Облом2.");
            throw new RuntimeException(e);
        }

        repaint();
        //setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        orc.draw(g);
        gnom.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(orc.isAlive()) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                orc.direction.up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                orc.direction.down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                orc.direction.right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                orc.direction.left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                orc.weapon.shooting = true;
            }
        }

        if(gnom.isAlive()) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                gnom.direction.up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                gnom.direction.down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                gnom.direction.right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                gnom.direction.left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                gnom.weapon.shooting = true;
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            orc.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            orc.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            orc.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            orc.direction.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            orc.weapon.shooting = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            gnom.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gnom.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gnom.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            gnom.direction.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            gnom.weapon.shooting = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        orc.update(ticks);
        orc.weapon.shoot(orc, ticks);
        orc.weapon.toAttack(gnom);
        gnom.update(ticks);
        gnom.weapon.shoot(gnom, ticks);
        gnom.weapon.toAttack(orc);

        repaint();
        ticks++;
    }
}
