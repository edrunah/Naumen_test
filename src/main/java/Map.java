public class Map {

    public static final char BEGINNING = '@';

    public static final char END = 'X';

    private Point[][] pointArray;

    private Node beginNode;

    private Node endNode;

    Map(char[][] charArray) {
        this.pointArray = new Point[charArray.length][charArray[0].length];
        for(int i = 0; i < pointArray.length; i++) {
            for(int j = 0; j < pointArray[0].length; j++) {
                pointArray[i][j] = new Point(i, j, charArray[i][j]);
            }
        }
        Point beginPoint = this.pointForChar(Map.BEGINNING);
        Point endPoint = this.pointForChar(Map.END);
        beginNode = new Node(beginPoint);
        endNode = new Node(endPoint);
    }

    public Node getBeginNode() {
        return beginNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public Point getPoint(int x, int y) {
        return pointArray[x][y];
    }

    public boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0
            || x > pointArray.length - 1 || y > pointArray[0].length - 1;

    }

    public char[][] paintWayOn(char[][] array) {
        char[][] way = new char[array.length][array[0].length];
        for(int i = 0; i < way.length; i++) {
            way[i] = array[i].clone();
        }
        Node plusNode = endNode.getParentNode();
        while (plusNode != beginNode) {
            int x = plusNode.getPoint().getX();
            int y = plusNode.getPoint().getY();
            way[x][y] = '+';
            plusNode = plusNode.getParentNode();
        }
        return way;
    }

    private Point pointForChar(char sign) {
        int rows = pointArray.length;
        int columns = pointArray[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (pointArray[i][j].getSign() == sign) {
                    return pointArray[i][j];
                }
            }
        }
        throw new IllegalArgumentException("Искомый символ не найден");
    }
}
