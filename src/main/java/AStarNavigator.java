import java.util.PriorityQueue;
import java.util.Queue;

public class AStarNavigator implements Navigator {

    public char[][] searchRoute(char[][] mapArray) {

        Map map = new Map(mapArray);
        Node beginNode = map.getBeginNode();
        Node endNode = map.getEndNode();
        Point endPoint = endNode.getPoint();

        int[] dx = {-1, 1, 0,  0};
        int[] dy = { 0, 0, 1, -1};
        Queue<Node> openList = new PriorityQueue<>();
        openList.add(beginNode);
        while (endNode.getParentNode() == null) {
            if (openList.isEmpty()) {
                return null;
            }
            Node currentNode = openList.poll();
            Point currentPoint = currentNode.getPoint();
            currentPoint.setSign('c');
            for (int i = 0; i < 4; i++) { // 4 стороны(вверх, вниз, вправо, влево)
                int newX = currentPoint.getX() + dx[i];
                int newY = currentPoint.getY() + dy[i];
                if (map.isOutOfMap(newX, newY)) {
                    continue;
                }
                Point newPoint = map.getPoint(newX, newY);
                if (newPoint.getSign() == '.') {
                    Node newOpenNode = new Node(newPoint);
                    newOpenNode.setParentNode(currentNode);
                    int distanceFromStart = currentNode.getDistanceFromStart() + 1;
                    int distanceToFinish = Math.abs(endPoint.getX() - newX)
                        + Math.abs(endPoint.getY() - newY);
                    newOpenNode.setDistances(distanceFromStart, distanceToFinish);
                    newPoint.setSign('o');
                    openList.add(newOpenNode);
                }
                if (newPoint.getSign() == 'X') {
                    endNode.setParentNode(currentNode);
                    break;
                }
            }
        }
        char[][] result = map.paintWayOn(mapArray);
        return result;
    }
}