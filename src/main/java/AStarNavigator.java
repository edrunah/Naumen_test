public class AStarNavigator implements Navigator {

    public char[][] searchRoute(char[][] mapArray) {

        CharMap map = new CharMap(mapArray);
        Node beginNode = map.getBeginNode();
        Node endNode = map.getEndNode();

        //обход массива методом A*
        map.setOpened(beginNode);
        while (endNode.getParentNode() == null) {
            if (map.listIsEmpty()) {
                return null; // если открытый список пуст, а выход не найден
            }
            Node currentNode = map.getOpened();
            Point currentPoint = currentNode.getPoint();
            map.setClosed(currentNode);
            for (int i = 0; i < 4; i++) { // 4 стороны(вверх, вниз, вправо, влево)
                Point newPoint = currentPoint.turnToSide(i);
                if (map.isOutOfMap(newPoint)) {
                    continue;
                }
                if (map.isEmptyRoad(newPoint)) {
                    Node newOpenNode = new Node(newPoint);
                    newOpenNode.setParentNode(currentNode);
                    map.calculateDistances(newOpenNode);
                    map.setOpened(newOpenNode);
                }
                if (map.isEnd(newPoint)) {
                    endNode.setParentNode(currentNode);
                    break;
                }
            }
        }
        return map.getWayArray();

    }
}