public class Point {

    private int x;

    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point turnToSide(int i) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        int newX = this.x + dx[i];
        int newY = this.y + dy[i];
        return new Point(newX, newY);
    }
}
