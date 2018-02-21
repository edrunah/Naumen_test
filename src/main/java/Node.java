public class Node implements Comparable<Node>{

    private Point point;

    private Node parentNode;

    private int distanceFromStart; //Manhattan method

    private int distanceToFinish;

    private int summaryDistance;

    Node(Point point) {
        this.point = point;
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

    public void setDistances(int distanceFromStart, int distanceToFinish) {
        this.distanceFromStart = distanceFromStart;
        this.distanceToFinish = distanceToFinish;
        this.summaryDistance = this.distanceFromStart + this.distanceToFinish;
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
