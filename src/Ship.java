import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Ship extends Unit implements HealthPoint {
    public Weapon weapon;
    public int hp = 1;
    public Image image;
    public Image deadImage;
    public double scale = 1;

    Rectangle hitbox;


    public Ship(int x, int y, int speed, double angle, Image image, Image deadImage, Weapon weapon, int hp, double scale, Rectangle hitbox) {
        super(x, y, speed, angle);
        this.weapon = weapon;
        this.hp = hp;
        this.image = image;
        this.deadImage = deadImage;
        this.scale = scale;
        this.hitbox = hitbox;
    }


    @Override
    public void update(int ticks) {
        if (!isAlive()) {
            direction.up = false;
            direction.right = false;
            direction.down = false;
            direction.left = false;
            weapon.shooting = false;
        }


        int dx = (int) -(Math.sin(angle) * speed);
        int dy = (int) (Math.cos(angle) * speed);

        if (direction.up) {
            x -= dx;
            y -= dy;
        }
        if (direction.down) {
            x += dx / 2;
            y += dy / 2;
        }
        if (direction.left) {
            angle -= 0.1;
        }
        if (direction.right) {
            angle += 0.1;
        }
        weapon.update(ticks);
    }


    @Override
    public void draw(Graphics g) {
        weapon.draw(g);

        Graphics2D graphics2D = (Graphics2D) g;
        Image currentImage = isAlive() ? image : deadImage;
        //graphics2D.scale(1 / scale, 1 / scale);
        //graphics2D.translate();
        //graphics2D.rotate(angle, x /*+ image.getWidth(null) / 2*/,
        //y /*+ image.getHeight(null) / 2*/);
        //g.drawImage(image, (x-image.getWidth(null) / 2), (y-image.getHeight(null) / 2), null);
        //graphics2D.rotate(-angle, x /*+ image.getWidth(null) / 2*/,
        //y /*+ image.getHeight(null) / 2*/);

        //graphics2D.scale(scale, scale);

        graphics2D.translate(x, y);
        graphics2D.rotate(angle);
        graphics2D.scale(scale, scale);
        graphics2D.translate(-currentImage.getWidth(null) / 2, -currentImage.getHeight(null) / 2);

        g.drawImage(currentImage, 0, 0, null);

        graphics2D.translate(currentImage.getWidth(null) / 2, currentImage.getHeight(null) / 2);
        graphics2D.scale(1 / scale, 1 / scale);
        graphics2D.rotate(-angle);
        graphics2D.translate(-x, -y);
    }

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public void setHP(int healthPoint) {
        hp = healthPoint;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
