public class Node implements Comparable<Node>{

    private int x;

    private int y;

    private Node parentNode;

    private int g; //Manhattan method

    private int h; //Manhattan method

    private int f; //Manhattan method

    Node(int x, int y, int g, int h) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public int getG() {
        return  g;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return "Узел " + x + "," + y;
    }

    @Override
    public int compareTo(Node anotherNode) {
        return Integer.compare(this.f, anotherNode.f);
    }
}
