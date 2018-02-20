import java.util.PriorityQueue;
import java.util.Queue;

public class CharMap implements AStarSearcher{

    public static final char BEGINNING = '@';

    public static final char END = 'X';

    private char[][] array;

    private Queue<Node> openList = new PriorityQueue<>();

    CharMap(char[][] array) {
        this.array = array;
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

    public CharMap clone() {
        char[][] basis = this.array;
        char[][] cloned = new char[basis.length][basis[0].length];
        for (int i = 0; i < basis.length; i++) {
            cloned[i] = basis[i].clone();
        }
        return new CharMap(cloned);
    }

    public boolean outOfMap(Point point) {
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
    public void addInOpenList(Node node) {
        openList.add(node);
        int x = node.getPoint().getX();
        int y = node.getPoint().getY();
        array[x][y] = 'o';
    }

    @Override
    public Node pollFromOpenList() {
        return openList.poll();
    }

    public void setClosed(Node node) {
        int x = node.getPoint().getX();
        int y = node.getPoint().getY();
        array[x][y] = 'c';
    }

    public boolean listIsEmpty() {
        return openList.isEmpty();
    }

    public void buildWay(Node beginNode, Node endNode) {
        Node plusNode = endNode.getParentNode();
        while (plusNode != beginNode) {
            array[plusNode.getPoint().getX()][plusNode.getPoint().getY()] = '+';
            plusNode = plusNode.getParentNode();
        }
    }

    private char getCharOnPoint(Point point) {
        return array[point.getX()][point.getY()];
    }
}
