public class AutomaticGun extends Weapon {

    public AutomaticGun(int lifetime, int speed, double spread) {
        super(lifetime, speed, spread);
    }

    @Override
    public void shoot(Ship ship, int ticks) {
        if (shooting) {
            bullets.add(new Bullet(ship.x, ship.y, speed, ship.angle + spread * (random.nextDouble() * 2 - 1),
                    5, ticks));
        }
    }
}
