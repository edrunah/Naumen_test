public class AStarNavigator implements Navigator {

    public char[][] searchRoute(char[][] mapArray) {

        //поиск начала и конца
        CharMap map = new CharMap(mapArray);
        CharMap mapClone = map.clone();
        Node beginNode = mapClone.getBeginNode();
        Node endNode = mapClone.getEndNode();

        //обход массива методом A*
        mapClone.setInOpenList(beginNode);

        while (endNode.getParentNode() == null) {
            if (mapClone.listIsEmpty()) {
                return null; // если открытый список пуст, а выход не найден
            }
            Node currentNode = mapClone.getFromOpenList();
            mapClone.setClosed(currentNode);
            for (int i = 0; i < 4; i++) { // 4 стороны(вверх, вниз, вправо, влево)
                Point currentPoint = currentNode.getPoint();
                Point newPoint = currentPoint.turnToSide(i);
                if (mapClone.isOutOfMap(newPoint)) {
                    continue;
                }
                if (mapClone.isEmptyRoad(newPoint)) {
                    Node newOpenNode = new Node(newPoint);
                    newOpenNode.setParentNode(currentNode);
                    mapClone.calculateDistances(newOpenNode);
                    mapClone.setInOpenList(newOpenNode);
                }
                if (mapClone.isEnd(newPoint)) {
                    endNode.setParentNode(currentNode);
                    break;
                }
            }
        }

        //заполнение плюсами
        CharMap mapOfPluses = map.clone();
        mapOfPluses.buildWay(beginNode, endNode);
        return mapOfPluses.getArray();
    }
}