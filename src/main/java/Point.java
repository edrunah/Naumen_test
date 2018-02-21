public class Point {

    private int x;

    private int y;

    private char sign;

    Point(int x, int y, char sign) {
        this.x = x;
        this.y = y;
        this.sign = sign;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

}
