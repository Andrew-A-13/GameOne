import java.awt.*;

public class Bullet extends Unit implements Attack {
    public int damage;
    public int creationTime;


    public Bullet(int x, int y, int speed, double angle, int damage, int creationTime) {
        super(x, y, speed, angle);
        this.damage = damage;
        this.creationTime = creationTime;
    }

    @Override
    public void draw(Graphics g) {
        //double scale = 1;
        Graphics2D graphics2D = (Graphics2D) g;
        //graphics2D.scale(1 / scale, 1 / scale);

        graphics2D.rotate(angle, x + 3 / 2,
                y + 3 / 2);
        //g.drawImage(image, x, y, null);
        g.drawRect(x, y, 3, 3);
        graphics2D.rotate(-angle, x + 3 / 2,
                y + 3 / 2);

        //graphics2D.scale(scale, scale);
    }

    @Override
    public void update(int ticks) {
        int dx = (int) -(Math.sin(angle) * speed);
        int dy = (int) (Math.cos(angle) * speed);
        x -= dx;
        y -= dy;
    }

    @Override
    public void toAttack(Ship enemy) {
        if (enemy.hitbox.contains(x - enemy.x, y - enemy.y)) {
            //System.out.println("hit");
            creationTime = -99999999;
            enemy.setHP(enemy.getHP() - damage);
        }
    }
}
