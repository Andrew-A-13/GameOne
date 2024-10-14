import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

//import static com.sun.tools.doclint.Entity.image;

public class Weapon implements Attack, Drawing, Updating {
    public LinkedList<Bullet> bullets;
    int lifetime;
    boolean shooting = false;

    public Weapon(int lifetime) {
        this.bullets = new LinkedList<>();
        this.lifetime = lifetime;
    }

    @Override
    public void toAttack(Ship enemy) {
        for (var bullet : bullets) {
            bullet.toAttack(enemy);
        }
    }

    @Override
    public void draw(Graphics g) {
        for (var bullet : bullets) {
            bullet.draw(g);
        }
    }


    @Override
    public void update(int ticks) {
        bullets.removeIf((b) -> abs(ticks - b.creationTime) > lifetime);
        for (var bullet : bullets) {
            bullet.update(ticks);
        }
    }

    public void shoot(Ship ship, int ticks) {
        if (shooting) {
            bullets.add(new Bullet(ship.x, ship.y, 20, ship.angle, 5, ticks));
        }
    }
}
