public class Shotgun extends Weapon {
    private boolean prevShooting = false;
    private double basicSpread;

    public Shotgun(int lifetime, int speed, double spread) {
        super(lifetime, speed, spread);
        basicSpread = 0.35;
    }

    @Override
    public void shoot(Ship ship, int ticks) {
        if (shooting && !prevShooting) {
            for (int i = 0; i < 2; i++) {
                bullets.add(new Bullet(ship.x, ship.y, speed, ship.angle - basicSpread + spread * (random.nextDouble() * 2 - 1),
                        5, ticks));
                bullets.add(new Bullet(ship.x, ship.y, speed, ship.angle + spread * (random.nextDouble() * 2 - 1),
                        5, ticks));
                bullets.add(new Bullet(ship.x, ship.y, speed, ship.angle + basicSpread + spread * (random.nextDouble() * 2 - 1),
                        5, ticks));
            }
        }
        prevShooting = shooting;
    }
}
