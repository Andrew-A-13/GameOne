import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.abs;


public abstract class Weapon implements Attack, Drawing, Updating {
    public LinkedList<Bullet> bullets;
    int lifetime;
    boolean shooting = false;
    int speed;
    double spread;
    static Random random = new Random();

    public Weapon(int lifetime, int speed, double spread) {
        this.speed = speed;
        this.spread = spread;
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

    public abstract void shoot(Ship ship, int ticks);



}
