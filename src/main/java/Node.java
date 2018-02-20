public class Node implements Comparable<Node>{

    private Point point;

    private Node parentNode;

    private int distanceFromStart; //Manhattan method

    private int distanceToFinish;

    private int summaryDistance;

    Node(Point point, int distanceFromStart, int distanceToFinish) {
        this.point = point;
        this.distanceFromStart = distanceFromStart;
        this.distanceToFinish = distanceToFinish;
        this.summaryDistance = distanceFromStart + distanceToFinish;
    }

    public Point getPoint() {
        return point;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return "Узел " + point.getX() + "," + point.getY();
    }

    @Override
    public int compareTo(Node anotherNode) {
        return Integer.compare(this.summaryDistance, anotherNode.summaryDistance);
    }
}
