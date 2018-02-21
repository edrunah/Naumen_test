import java.util.PriorityQueue;
import java.util.Queue;

public class CharMap implements AStarSearchable {

    public static final char BEGINNING = '@';

    public static final char END = 'X';

    private char[][] array;

    private char[][] arrayClone; // для создания пометок

    private Node beginNode;

    private Node endNode;

    private Queue<Node> openList = new PriorityQueue<>();

    CharMap(char[][] array) {
        this.array = array;
        arrayClone = clone(array);
        Point beginPoint = this.pointForChar(CharMap.BEGINNING);
        Point endPoint = this.pointForChar(CharMap.END);
        beginNode = new Node(beginPoint);
        endNode = new Node(endPoint);
    }

    public Node getBeginNode() {
        return beginNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public boolean isOutOfMap(Point point) {
        return point.getX() < 0 || point.getY() < 0
            || point.getX() > arrayClone.length - 1 || point.getY() > arrayClone[0].length - 1;

    }

    public boolean isEmptyRoad(Point point) {
        return arrayClone[point.getX()][point.getY()] == '.';
    }

    public boolean isEnd(Point point) {
        return arrayClone[point.getX()][point.getY()] == 'X';
    }

    @Override
    public void setOpened(Node node) {
        openList.add(node);
        int x = node.getPoint().getX();
        int y = node.getPoint().getY();
        arrayClone[x][y] = 'o';
    }

    @Override
    public Node getOpened() {
        return openList.poll();
    }

    public void setClosed(Node node) {
        int x = node.getPoint().getX();
        int y = node.getPoint().getY();
        arrayClone[x][y] = 'c';
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

    public char[][] getWayArray() {
        char[][] way = clone(array);
        Node plusNode = endNode.getParentNode();
        while (plusNode != beginNode) {
            int x = plusNode.getPoint().getX();
            int y = plusNode.getPoint().getY();
            way[x][y] = '+';
            plusNode = plusNode.getParentNode();
        }
        return way;
    }

    private char[][] clone(char[][] basis) {
        char[][] cloned = new char[basis.length][basis[0].length];
        for (int i = 0; i < basis.length; i++) {
            cloned[i] = basis[i].clone();
        }
        return cloned;
    }

    private Point pointForChar(char symbol) {
        int rows = array.length;
        int columns = array[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (array[i][j] == symbol) {
                    return new Point(i, j);
                }
            }
        }
        throw new IllegalArgumentException("Искомый символ не найден");
    }
}
