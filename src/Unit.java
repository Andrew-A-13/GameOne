public abstract class Unit implements Updating, Drawing {
    public int x = 0;
    public int y = 0;
    public int speed = 0;
    public double angle = 0;

    public Direction direction;

    public Unit(int x, int y, int speed, double angle) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        direction = new Direction();
    }


}
