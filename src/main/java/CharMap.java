import java.util.PriorityQueue;
import java.util.Queue;

public class CharMap implements AStarSearchable {

    public static final char BEGINNING = '@';

    public static final char END = 'X';

    private char[][] array;

    private Node beginNode;

    private Node endNode;

    private Queue<Node> openList = new PriorityQueue<>();

    CharMap(char[][] array) {
        this.array = array;
        Point beginPoint = this.pointForChar(CharMap.BEGINNING);
        Point endPoint = this.pointForChar(CharMap.END);
        beginNode = new Node(beginPoint);
        endNode = new Node(endPoint);
    }

    public Point pointForChar(char symbol) {
        int rows = array.length;
        int columns = array[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (array[i][j] == symbol) {
                    return new Point(i, j);
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public char[][] getArray() {
        return array;
    }

    public Node getBeginNode() {
        return beginNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public CharMap clone() {
        char[][] basis = this.array;
        char[][] cloned = new char[basis.length][basis[0].length];
        for (int i = 0; i < basis.length; i++) {
            cloned[i] = basis[i].clone();
        }
        return new CharMap(cloned);
    }

    public boolean isOutOfMap(Point point) {
        return point.getX() < 0 || point.getY() < 0
            || point.getX() > array.length - 1 || point.getY() > array[0].length - 1;

    }

    public boolean isEmptyRoad(Point point) {
        return this.getCharOnPoint(point) == '.';
    }

    public boolean isEnd(Point point) {
        return this.getCharOnPoint(point) == 'X';
    }

    @Override
    public void setInOpenList(Node node) {
        openList.add(node);
        int x = node.getPoint().getX();
        int y = node.getPoint().getY();
        array[x][y] = 'o';
    }

    @Override
    public Node getFromOpenList() {
        return openList.poll();
    }

    public void setClosed(Node node) {
        int x = node.getPoint().getX();
        int y = node.getPoint().getY();
        array[x][y] = 'c';
    }

    @Override
    public void calculateDistances(Node node) {
        int distanceFromStart = node.getParentNode().getDistanceFromStart() + 1;
        Point endPoint = endNode.getPoint();
        Point thisPoint = node.getPoint();
        int distanceToFinish = Math.abs(endPoint.getX() - thisPoint.getX())
            + Math.abs(endPoint.getY() - thisPoint.getY());
        node.setDistances(distanceFromStart, distanceToFinish);
    }

    public boolean listIsEmpty() {
        return openList.isEmpty();
    }

    public void buildWay(Node beginNode, Node endNode) {
        Node plusNode = endNode.getParentNode();
        while (plusNode != beginNode) {
            int x = plusNode.getPoint().getX();
            int y = plusNode.getPoint().getY();
            array[x][y] = '+';
            plusNode = plusNode.getParentNode();
        }
    }

    private char getCharOnPoint(Point point) {
        return array[point.getX()][point.getY()];
    }
}
