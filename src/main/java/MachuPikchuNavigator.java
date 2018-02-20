public class MachuPikchuNavigator implements Navigator {

    public char[][] searchRoute(char[][] mapArray) {

        //поиск начала и конца
        CharMap map = new CharMap(mapArray);
        CharMap mapClone = map.clone();
        Point beginPoint = mapClone.pointForChar(CharMap.BEGINNING);
        Point endPoint = mapClone.pointForChar(CharMap.END);

        //обход массива методом A*
        Node beginNode = new Node(beginPoint, 0, 0);
        beginNode.setParentNode(null);
        Node endNode = new Node(endPoint, 0, 0);
        mapClone.addInOpenList(beginNode);

        while (endNode.getParentNode() == null) {
            if (mapClone.listIsEmpty()) {
                return null; // если открытый список пуст, а выход не найден
            }
            Node currentNode = mapClone.pollFromOpenList();
            mapClone.setClosed(currentNode);
            for (int i = 0; i < 4; i++) { // 4 стороны(вверх, вниз, вправо, влево)
                Point currentPoint = currentNode.getPoint();
                Point newPoint = currentPoint.turnToSide(i);
                if (mapClone.outOfMap(newPoint)) {
                    continue;
                }
                if (mapClone.isEmptyRoad(newPoint)) {
                    int distanceFromStart = currentNode.getDistanceFromStart() + 1;
                    int distanceToFinish = Math.abs(endPoint.getX() - newPoint.getX())
                        + Math.abs(endPoint.getY() - newPoint.getY());
                    Node newOpenNode = new Node(newPoint, distanceFromStart, distanceToFinish);
                    newOpenNode.setParentNode(currentNode);
                    mapClone.addInOpenList(newOpenNode);
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